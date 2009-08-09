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

package org.reallysqs.server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import org.reallysqs.server.services.SimpleQueueService;

/**
 * The main Queue controller responsible for meeting the RESTful SQS
 * service contract.
 * 
 * @author Russ Miles (russ@russmiles.com)
 *
 */
@Controller
public class QueueController {

	@Autowired
	private SimpleQueueService simpleQueueService;
	
	/**
	 * Returns a list of queues for the appropriate URL signature.
	 * In this case a request for the /queues URI, using the HTTP
	 * GET method.
	 * 
	 * @return An object composed of a model and view, the model containing the list of queues.
	 */
	@RequestMapping(value="/queues", method=RequestMethod.GET)
	public ModelAndView listQueues() {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("queuesList");
		mav.addObject("queues", this.simpleQueueService.getQueues());
		return mav;
	}
    
	/**
	 * Creates a new queue when a POST request to /queues is invoked.
	 * 
	 * @param queueName The name of the new queue to create.
	 * @return An object composed of a model and view, the model containing the newly created queue.
	 */
	@RequestMapping(value="/queues", method=RequestMethod.POST)
	public ModelAndView createQueue(@RequestParam("QueueName") String queueName) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("createQueueResponse");
		mav.addObject("queue", this.simpleQueueService.createQueue(queueName));
		return mav;
	}
	
	/**
	 * Returns a list of messages for a specific queue.
	 * 
	 * @param queueName The name of the queue from which the messages should be retrieved.
	 * @return An object composed of a model and view, the model containing the list of messages.
	 */
	@RequestMapping(value="/queues/{queueName}", method=RequestMethod.GET)
	public ModelAndView getMessagesFromQueue(@PathVariable String queueName) {
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("receivedMessagesResponse");
		mav.addObject("messages", this.simpleQueueService.getMessagesForQueue(queueName));
		return mav;
	}
}
