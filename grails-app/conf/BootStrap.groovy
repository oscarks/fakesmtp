class BootStrap {
	def smtpServer
	
    def init = { servletContext ->
		log.debug("Starting SMTP server")
		try {
			smtpServer.start()
		}
		catch(Exception e) {
			// Starting the web application is more important
			// than SMTP, so just log the failure and carry on
			log.error("SMTP server could not be started")
		}
		
    }
    def destroy = {
       log.debug("Stopping SMTP server")
        smtpServer.stop()
	}
}
