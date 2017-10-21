package com.model;

public class Response {
    
    private String response;
   
    private int responseCode;
	
    public boolean isNextpage;

   
    
    public AlertMessage alertMessage;
    
    public com.concretepage.entity.User User;    
    
   
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public boolean isIsNextpage() {
        return isNextpage;
    }

    public void setIsNextpage(boolean isNextpage) {
        this.isNextpage = isNextpage;
    }
   
    
    public AlertMessage getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(AlertMessage alertMessage) {
        this.alertMessage = alertMessage;
    }

    public com.concretepage.entity.User getUser() {
        return User;
    }

    public void setUser(com.concretepage.entity.User user) {
        this.User = user;
    }
    
     public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    
    public Response(AlertMessage alertMessage) {
		
		responseCode = 400;
		if ("success".equalsIgnoreCase(alertMessage.status)) {
			responseCode = 200;
		}
		this.alertMessage = alertMessage;
	}
    
    public <T> Response(T object) {
        
		responseCode = 200;
		if (object instanceof String) {
			this.response = (String) object;
		} else {
			if (object instanceof com.concretepage.entity.User) {
				this.User = (com.concretepage.entity.User) object;
			} else if(object instanceof AlertMessage){
                            this.alertMessage = (AlertMessage) object;
                        } 
		}
	}
    
   public <T> Response(T object, AlertMessage alertMessage) {
		this(object);
		
		if ("success".equalsIgnoreCase(alertMessage.status)) {
			responseCode = 200;
		}
		this.alertMessage = alertMessage;
	}
}

