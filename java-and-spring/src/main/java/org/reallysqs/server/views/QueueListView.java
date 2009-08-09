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

import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.AbstractView;

import org.reallysqs.server.domain.Queue;

/**
 * Responsible for rendering the response to a request
 * to list all the current queues.
 * 
 * In this case a SQS standard XML document is rendered and returned.
 * 
 * See http://docs.amazonwebservices.com/AWSSimpleQueueService/2006-04-01/REST_RestOperations.html
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
public class QueueListView extends AbstractView {
	
	private static final String prelude = "<ListQueuesResponse " +
	  "xmlns=\"http://queue.amazonaws.com/doc/2006-04-01/\" " +
	  "xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" " +
	  "xsi:type=\"ListQueuesResponse\" >" +
	    "<Queues>";
	        
	private static final String epilogue = "</Queues>" +
	    "<ResponseStatus>" +
	      "<StatusCode>Success</StatusCode>" +
	      "<RequestId>cb919c0a-9bce-4afe-9b48-9bdf2412bb67</RequestId>" +
	    "</ResponseStatus>" +
	"</ListQueuesResponse>";

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.view.AbstractView#renderMergedOutputModel(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setContentType("text/xml");
		
		Map<String, Queue> queues = (Map<String, Queue>) model.get("queues");
		
		Collection<Queue> queueList = queues.values();
		
		ServletOutputStream outputStream = response.getOutputStream();
		outputStream.print(prelude);
		
		for (Queue queue : queueList) {
			outputStream.print("<QueueUrl>");
            outputStream.print("http://" + request.getLocalName() + ":" +
            		request.getLocalPort() + request.getContextPath() + 
            		request.getServletPath() + "/queues/" + queue.getName());
            outputStream.print("</QueueUrl>");
		}
		
		outputStream.print(epilogue);

	}

}
