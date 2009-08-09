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

package org.reallysqs.server.repository;

import java.util.List;
import java.util.Map;

import org.reallysqs.server.domain.Queue;
import org.reallysqs.server.domain.Message;

/**
 * Defines the role of a queue repository for storing and managing
 * the system's queues.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public interface QueueRepository {

	/**
	 * Returns all the queues currently in the system.
	 * 
	 * @return The map of all the current queues in the system.
	 */
	public Map<String, Queue> getQueues();

	/**
	 * Creates a new queue in the system.
	 * 
	 * @param queueName The name of the new queue.
	 * @return The newly created queue.
	 */
	public Queue createQueue(String queueName);

	/**
	 * Returns a list of messages from a specified queue.
	 * 
	 * @param queueName The name of the queue to return the messages from.
	 * @return The list of messages from the indicated queue.
	 */
	public List<Message> getMessagesForQueue(String queueName);
	
}
