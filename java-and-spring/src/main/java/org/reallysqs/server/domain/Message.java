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

import java.util.UUID;

/**
 * A domain class that represents a simple text message.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public class Message {
	
	private final String uuid;
	private final String content;
	
	/**
	 * Creates a new message with the specific text content.
	 * 
	 * @param content The initial content of this message.
	 */
	public Message(String content) {
		super();
		this.uuid = UUID.randomUUID().toString();
		this.content = content;
	}

	/**
	 * Returns the unique identifier for this message.
	 * 
	 * @return The UUID for this message.
	 */
	public String getUuid() {
		return uuid;
	}

	/**
	 * Returns the simple text content of this message.
	 * 
	 * @return The message content.
	 */
	public String getContent() {
		return content;
	}
}
