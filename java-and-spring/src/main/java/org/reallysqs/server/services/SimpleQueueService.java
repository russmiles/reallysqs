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

package org.reallysqs.server.services;

import java.util.List;
import java.util.Map;

import org.reallysqs.server.domain.Message;
import org.reallysqs.server.domain.Queue;

/**
 * Defines the contract for the internal business role 
 * of a simple queue service.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public interface SimpleQueueService {
	
	/**
	 * Returns map containing a collection of queues, keyed by their name.
	 * 
	 * @return The map of queues.
	 */
	public Map<String, Queue> getQueues();

	/**
	 * Creates a new queue within the system with the specified name.
	 * 
	 * @param queueName The name of the queue to be created.
	 * @return The newly created queue.
	 */
	public Queue createQueue(String queueName);
	
	/**
	 * Gets the messages from a specified queue.
	 * 
	 * @param queueName The name of the queue from which the messages should be retrieved
	 * @return A list of the messages from the queue.
	 */
	public List<Message> getMessagesForQueue(String queueName);

}
