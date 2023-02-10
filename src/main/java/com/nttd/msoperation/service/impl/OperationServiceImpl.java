package com.nttd.msoperation.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.nttd.msoperation.dto.OperationDto;
import com.nttd.msoperation.dto.ResponseDto;
import com.nttd.msoperation.entity.Operation;
import com.nttd.msoperation.repository.OperationRepository;
import com.nttd.msoperation.service.OperationService;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class OperationServiceImpl implements OperationService {

	@Inject
	OperationRepository operationRepository;

	@Override
	@Transactional
	public ResponseDto processOperation(OperationDto operationDto) {
		try {
			
			if("T".equals(operationDto.getFlagdDescription())) {
				// registrando  retiro cuenta origen
				operationDto.setFlagOperation("R");
				Operation operacionOrigen =	saveOperation(operationDto);
				// registrando  retiro cuenta destino
				operationDto.setFlagOperation("D");
				operationDto.setIdAccountCustomer(operationDto.getIdAccountCustomerDestiny());
				operationDto.setIdAOperationOrigin(operacionOrigen.getIdOperation());
				saveOperation(operationDto);
			} else {
				saveOperation(operationDto);
			}
			
			return new ResponseDto(201, "Exitoso.");
		} catch (Exception ex) {
			return new ResponseDto(400, "Bad Request.", ex.getMessage());
		}
	}

	/**
	 * registra la operacion en la base de datos
	 */
	private Operation saveOperation(OperationDto operationDto) {
		Operation operationEntity = new Operation();
		operationEntity.setAmmount(operationDto.getAmmount());
		operationEntity.setIdAOperationOrigin(operationDto.getIdAOperationOrigin());
		operationEntity.setFlagOperation(operationDto.getFlagOperation());
		operationEntity.setFlagdDescription(operationDto.getFlagdDescription());
		operationEntity.setDescription(operationDto.getDescription());
		operationEntity.setIdAccountCustomer(operationDto.getIdAccountCustomer());
		operationRepository.persist(operationEntity);
		return operationEntity;
	}

	/**
	 * Actualizar el monto en la cuenta principal
	 */
	private void updateAccountCustomer(Integer idAccountCustomer, Double ammount) {

	}

	public List<Operation> retrieveOperations(OperationDto filter) {
		Map<String, Object> parametros = new HashMap<>();
		if (filter.getFlagOperation() != null && filter.getFlagOperation().length()!=0)
			parametros.put("flagOperation", filter.getFlagOperation());

		if (parametros.isEmpty())
			return operationRepository.listAll();

		String query = parametros.entrySet().stream().map(entry -> entry.getKey() + "=:" + entry.getKey())
				.collect(Collectors.joining(" and "));

		return operationRepository.list(query, parametros);
		
		

	}
	
	
	
	
	

}
