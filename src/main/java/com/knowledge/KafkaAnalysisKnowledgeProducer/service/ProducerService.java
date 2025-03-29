package com.knowledge.KafkaAnalysisKnowledgeProducer.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.knowledge.KafkaAnalysisKnowledgeProducer.constants.KafkaProducerConstants;
import com.knowledge.KafkaAnalysisKnowledgeProducer.dto.Employee;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProducerService {

	private static final Logger logger = LogManager.getLogger(ProducerService.class);
	
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
	
	public ResponseEntity<String> produceEvent(Employee employee) {
		
		ResponseEntity<String> response = null;
		
		try {
			logger.info("In Producer Service", employee);
		
			if(!employee.getId().isEmpty() && !employee.getName().isEmpty() && !employee.getEmploymentStatus().isEmpty()) {

				kafkaTemplate.send(KafkaProducerConstants.EMPLOYEE_PRODUCER,employee);
				
				response = new ResponseEntity<String>("Employee Object Publised to producer",HttpStatus.OK);
			}
			
			else
				response = new ResponseEntity<String>("Invalid Employee Data",HttpStatus.BAD_REQUEST);
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return response;
	}

}
