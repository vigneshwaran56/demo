package com.model;

import java.util.Map;

/**
 *
 * @author vignesh
 */
public class AlertMessage {
    public String code;

	public String title;

	public String description;

	public String message;

	public String status;
	
	public Map<String, String> actionMap;
	
	public String keySet;
	
	public String actionSet;
        public AlertMessage cloneMe(){
		AlertMessage message = new AlertMessage();
		message.code = code;
		message.title = title;
		message.description = description;
		message.message = this.message;
		message.status = status;
		message.actionMap = actionMap;
		message.keySet = keySet;
		message.actionSet = actionSet;
		return message;
	}
}

