package com.acception.fakesmtp

class FmMailMessage {
	Date dateCreated
	Date lastUpdated
	
	byte[] data;
	String subject;
	String fromAddress;
	String recipient;
	String fromServer;
    
	static constraints = {
    	data nullable:true
		subject nullable:true
		fromAddress nullable:true
		recipient nullable:true
		fromServer nullable:true
	}
	static transients = ['text']
	
	public String getText(){
		if(data == null){
			return null;
		}else{
			try{
				return new String(data);
			}catch(Exception e){
				e.printStackTrace();
			}
			return null;
		}
	}
}
