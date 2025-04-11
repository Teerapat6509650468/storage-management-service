package com.example.storagemanagement.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {
	
	@KafkaListener(topics = "warehouse-topic", groupId = "warehouse-group")
	public void listen(String message) {
		System.out.println("Recieved message: " + message);
	}
}
