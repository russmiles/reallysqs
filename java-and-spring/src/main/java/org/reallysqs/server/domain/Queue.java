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

package org.reallysqs.server.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Represents a queue, implemented simple using an array list.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public class Queue {
	
	private final String uuid;
	private final String name;
	
	private List<Message> messages = new ArrayList<Message>();

	/**
	 * Constructs a new simple queue.
	 * 
	 * @param name The name of the queue as specific by the user.
	 */
	public Queue(String name) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.name = name;
	}

	/**
	 * Returns the name of the queue.
	 * 
	 * @return The queue's name.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * Returns the unique identifier for this queue.
	 * 
	 * @return The queue's UUID.
	 */
	public String getUuid() {
		return uuid;
	}
	
	/**
	 * Sets the current list of messages for this queue.
	 * 
	 * @param messages The new list of messages for this queue.
	 */
	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}
	
	/**
	 * Returns the complete set of all messages from this queue.
	 * 
	 * @return The list of messages from this queue.
	 */
	public List<Message> getMessages() {
		return this.messages;
	}
}
