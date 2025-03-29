package com.knowledge.KafkaAnalysisKnowledgeProducer.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.knowledge.KafkaAnalysisKnowledgeProducer.constants.KafkaProducerConstants;
import com.knowledge.KafkaAnalysisKnowledgeProducer.dto.Employee;
import com.knowledge.KafkaAnalysisKnowledgeProducer.service.ProducerService;

@RestController
@RequestMapping(value = KafkaProducerConstants.CONTROLLER)
public class ProducerController {

	@Autowired
	ProducerService producerService;

	@PostMapping(KafkaProducerConstants.PRODUCER_ENDPOINT)
	public ResponseEntity<String> postMethodName(@RequestBody Employee employee) {

		return producerService.produceEvent(employee);
	}

}
