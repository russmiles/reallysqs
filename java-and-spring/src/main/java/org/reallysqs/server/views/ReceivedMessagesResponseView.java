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

package org.reallysqs.server.views;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import org.reallysqs.server.domain.Message;

/**
 * Responsible for rendering a list of messages.
 * 
 * In this case a SQS standard XML ocument is rendered and returned.
 * 
 * See http://docs.amazonwebservices.com/AWSSimpleQueueService/2006-04-01/REST_RestOperations.html
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public class ReceivedMessagesResponseView extends AbstractView {
	
	private static final String prelude = "<ReceiveMessageResponse>";
	    
	        
	private static final String epilogue = "<ResponseStatus>" +
	    "<StatusCode>Success</StatusCode>" +
	    "<RequestId>b5bf2332-e983-4d3e-941a-f64c0d21f00f</RequestId>" +
	  "</ResponseStatus>" +
	"</ReceiveMessageResponse>";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml");
		
		List<Message> messages = (List<Message>) model.get("messages");
		
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.print(prelude);
		if (messages != null) {
			for (Message message : messages) {
				outputStream.print("<Message><MessageId>");
				outputStream.print(message.getUuid());
				outputStream.print("</MessageId><MessageBody>");
	            outputStream.print(message.getContent());
	            outputStream.print("</MessageBody></Message>");
			}
		}
		
		outputStream.print(epilogue);

	}

}
