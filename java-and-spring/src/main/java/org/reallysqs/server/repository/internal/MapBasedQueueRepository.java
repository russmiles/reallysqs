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

package org.reallysqs.server.repository.internal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.reallysqs.server.domain.Queue;
import org.reallysqs.server.domain.Message;
import org.reallysqs.server.repository.QueueRepository;
import org.springframework.stereotype.Repository;

/**
 * A very simple, in-memory hash map based repository
 * for queues.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
@Repository
public class MapBasedQueueRepository implements QueueRepository{

	private Map<String, Queue> queues = new HashMap<String, Queue>();

	/**
	 * Sets the map of queues at startup.
	 * 
	 * @param queues A simple map of queues indexed by a string.
	 */
	public void setQueues(Map<String, Queue> queues) {
		this.queues = queues;
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.repository.QueueRepository#getQueues()
	 */
	public Map<String, Queue> getQueues() {
		return this.queues;
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.repository.QueueRepository#createQueue(java.lang.String)
	 */
	public Queue createQueue(String queueName) {
		Queue queue = new Queue(queueName);
		this.queues.put(queueName, queue);
		return queue;
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.repository.QueueRepository#getMessagesForQueue(java.lang.String)
	 */
	public List<Message> getMessagesForQueue(String queueName) {
		Queue queue = this.queues.get(queueName);
		return queue != null ? queue.getMessages() : null;
	}

}
