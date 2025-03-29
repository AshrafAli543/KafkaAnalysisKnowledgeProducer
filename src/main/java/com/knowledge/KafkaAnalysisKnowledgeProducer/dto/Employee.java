package com.knowledge.KafkaAnalysisKnowledgeProducer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown=true)
public class Employee {
	
	@JsonProperty(value = "id")
    private String id;

	@JsonProperty(value = "name")
	private String name;

	@JsonProperty(value = "department")
	private String department;

	@JsonProperty(value = "salary")
	private Double salary;
	  
	@JsonProperty(value = "employment_status")
	private String employmentStatus;

}
