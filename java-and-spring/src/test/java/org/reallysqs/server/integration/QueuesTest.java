/*
 * Copyright 2008-2009 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.reallysqs.server.integration;

import java.net.URI;

import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.web.client.RestTemplate;
 
/**
 * This test exercises the currently completed functionality
 * in the REALLY simple queue service implementation.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public class QueuesTest {

	RestTemplate restTemplate = new RestTemplate();
	
	private static final String GET_QUEUE_LIST_URI = "http://localhost:8080/reallysqs-server/reallysqs/queues";
	private static final String CREATE_A_NEW_QUEUE_URI = "http://localhost:8080/reallysqs-server/reallysqs/queues?QueueName={queueName}";
	private static final String GET_MESSAGES_URI = "http://localhost:8080/reallysqs-server/reallysqs/queues/{queueName}";
	
	/**
	 * Tests retrieving a list of all available queues.
	 */
	@Test
	public void testGetAListOfQueues() {
		
		String queues = restTemplate.getForObject(GET_QUEUE_LIST_URI, String.class);
		
		assertNotNull(queues);
		
		System.out.println("Queues listed: ");
		System.out.println(queues);
	}
	
	/**
	 * Tests adding a new queue to the system.
	 */
	@Test
	public void testAddANewQueue() {
		
		final String queueName = "myQueue";
		
		URI queueUri = restTemplate.postForLocation(CREATE_A_NEW_QUEUE_URI, null, queueName);
		
		assertNotNull(queueUri);
		
		System.out.println("Queue created at: ");
		System.out.println(queueUri);
	}
	
	/**
	 * Tests adding a new queue and then using the queue's location,
	 * returned as part of the response headers, to then retrieve
	 * the messages for that new queue.
	 */
	@Test
	public void testAddANewQueueAndGetMessages() {
		
		final String queueName = "myQueue2";
		
		URI queueUri = restTemplate.postForLocation(CREATE_A_NEW_QUEUE_URI, null, queueName);
		
		assertNotNull(queueUri);
		
		System.out.println("Queue created at: ");
		System.out.println(queueUri);
		
		String messages = restTemplate.getForObject(queueUri.toString(), String.class);
		
		assertNotNull(messages);
		
		System.out.println("Messages returned from new queue (" + queueName + ": ");
		System.out.println(messages);
		
		messages = restTemplate.getForObject(GET_MESSAGES_URI, String.class, "queue1");
		
		assertNotNull(messages);
		
		System.out.println("Messages returned from queue (queue1): ");
		System.out.println(messages);
	}
}
