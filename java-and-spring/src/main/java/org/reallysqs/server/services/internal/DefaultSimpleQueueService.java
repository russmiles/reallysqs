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

package org.reallysqs.server.services.internal;

import java.util.List;
import java.util.Map;

import org.reallysqs.server.domain.Message;
import org.reallysqs.server.domain.Queue;
import org.reallysqs.server.repository.QueueRepository;
import org.reallysqs.server.services.SimpleQueueService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * The default implementation of the internal simple queue service.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
@Service
public class DefaultSimpleQueueService implements SimpleQueueService {

	private QueueRepository queueRepository;
	
	public DefaultSimpleQueueService(QueueRepository queueRepository) {
		super();
		this.queueRepository = queueRepository;
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.services.SimpleQueueService#getQueues()
	 */
	@Transactional 
	public Map<String, Queue> getQueues() {
		return queueRepository.getQueues();
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.services.SimpleQueueService#createQueue(java.lang.String)
	 */
	@Transactional
	public Queue createQueue(String queueName) {
		return this.queueRepository.createQueue(queueName);
	}

	/* (non-Javadoc)
	 * @see org.reallysqs.server.services.SimpleQueueService#getMessagesForQueue(java.lang.String)
	 */
	public List<Message> getMessagesForQueue(String queueName) {
		return this.queueRepository.getMessagesForQueue(queueName);
	}
}
