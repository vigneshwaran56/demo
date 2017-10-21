package com.builder;

import com.library.Library;
import com.model.AlertMessage;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletContext;

import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * @author vignesh
 */
public class AlertMessageBuilder {
    public Set<String> keySet;

	public Set<String> actionSet;

	AlertMessage alertMessage;

	HashMap<String, AlertMessage> hashmap = new HashMap<String, AlertMessage>();

	public static AlertMessageBuilder alertMessageBuilder;

	public static  AlertMessageBuilder getInstance(ServletContext context) {
		if (alertMessageBuilder == null) {
			alertMessageBuilder = new AlertMessageBuilder(context);
		}
		return alertMessageBuilder;
	}

	AlertMessageBuilder(ServletContext context) {

		try {
			byte[] data = Library.getBytesFromStream(context.getResourceAsStream("/alertmessage.json"));
			String json = new String(data);
                        System.out.println("json"+json);
				
			JSONArray alertArray = new JSONArray(json);
                        System.out.println("json array"+alertArray);
				
			for (int index = 0; index < alertArray.length(); index++) {
				JSONObject object = alertArray.getJSONObject(index);
                                System.out.println("com.builder.AlertMessageBuilder.<init>()"+object);
				System.out.println("promptcode"+object.getString("PromptCode"));
				AlertMessage alertMessages = new AlertMessage();
                                try{
                                alertMessages.code = object.getString("PromptCode");
				alertMessages.title = object.getString("Title");
				alertMessages.message = object.getString("Message");
				alertMessages.description = object.getString("Description");
				alertMessages.actionMap = new HashMap<String, String>();
				alertMessages.status = object.getString("Status");
				alertMessages.actionSet = object.getString("ActionSet");
				alertMessages.keySet = object.getString("KeySet");
                                }catch(Exception e){
                                  	Logger.getLogger("LOG").warning(Library.getStackTraceAsString(e));
		  
                                }
				hashmap.put(alertMessages.code, alertMessages);
			}
		} catch (Exception e) {
			Logger.getLogger("LOG").warning(Library.getStackTraceAsString(e));
		}
	}

	public  AlertMessageBuilder setCode(String code) {
		synchronized (this) {
			alertMessage = hashmap.get(code);
			if (alertMessage != null) {
				Logger.getLogger("LOG").warning(alertMessage.code);
				alertMessage = alertMessage.cloneMe();
				String[] actionItems = alertMessage.actionSet.split(",");
				actionSet = new HashSet<String>(Arrays.asList(actionItems));
				actionSet.remove("");
				String[] keyItems = alertMessage.keySet.split(",");

				keySet = new HashSet<String>(Arrays.asList(keyItems));

				keySet.remove("");
				return this;
			}
			alertMessage = new AlertMessage();
			alertMessage.status = "failure";
			alertMessage.description = "scenario was missing";
			alertMessage.code = "jini-def";
			alertMessage.message = "Alert message of this is not configured";
		}
		
		return this;
	}

	public  AlertMessage build() {
		/*
		 * if ((actionSet != null && !actionSet.isEmpty()) && (keySet != null &&
		 * !keySet.isEmpty())) { throw new IllegalArgumentException(
		 * "Alert message is not completly build"); }
		 */
		return alertMessage;
	}

	public  AlertMessageBuilder setActionLink(String action, String link) {
		Logger.getLogger("jinify").warning(alertMessage.code + "----for message---"+actionSet);
		synchronized (this) {
			if (actionSet == null || actionSet.isEmpty()) {
				throw new IllegalArgumentException("Action is not required");
			}
			if (!actionSet.contains(action)) {
				throw new IllegalArgumentException("Action is not exist");
			}
			alertMessage.actionMap.put(action, link);
			actionSet.remove(action);
		}
		
		return this;
	}

	public AlertMessageBuilder setMessage(String key, String message) {

		synchronized (this) {
			if (keySet == null | keySet.isEmpty()) {
				throw new IllegalArgumentException("Key is not required");
			}

			if (!keySet.contains(key)) {
				throw new IllegalArgumentException("Key is not exist in message");
			}
			Logger.getLogger("jinify").warning(keySet + "for message");
			
			Logger.getLogger("jinify").warning(key + "to message"+message);
			
			if(isSpecialChar(message)){
				synchronized (alertMessage.message) {
					alertMessage.message = replaceMessage(alertMessage.message,key,message);
				}
			}
			else{
				synchronized (alertMessage.message) {
					alertMessage.message = alertMessage.message.replaceAll(key,message);
					
				}

			}
			
			Logger.getLogger("jinify").warning("alertMessage.message"+alertMessage.message);
				
			
			keySet.remove(key);
		}
		

		return this;
	}

	public static String replaceMessage(String alertMessage, String key, String modifyMessage) {
		StringBuilder returnMessage = new StringBuilder();
		try{
		String[] StringArray = alertMessage.split(key);
       
		System.out.println(StringArray.length);
		if(StringArray.length != 0){
        int i=0;
        for (; i < StringArray.length - 1; i++)
        returnMessage.append(StringArray[i]).append(modifyMessage);

        returnMessage.append(StringArray[i]);
        if (alertMessage.substring(alertMessage.lastIndexOf(" ")).equalsIgnoreCase(" " + key))
            returnMessage.append(modifyMessage);
        return returnMessage.toString();
		}
        System.out.println(returnMessage.toString());
		}catch(Exception e){
		 	 Logger.getLogger("jinify").warning("Exception"+e);
				
		}
		 Logger.getLogger("jinify").warning("modify Message"+modifyMessage);
		return modifyMessage;
		
	}

	public static boolean isSpecialChar(String message) {
		
		 Pattern pttern = Pattern.compile("[^A-Za-z0-9]");
	     Matcher matcher = pttern.matcher(message);
	    // boolean b = m.matches();
	     boolean isSpecialChar = matcher.find();
	     if (isSpecialChar == true)
	    	 Logger.getLogger("jinify").warning("There is a special character in my string ");
	     else
	    	 Logger.getLogger("jinify").warning("There is no special char.");
	     
		return isSpecialChar;
	}
}