package com.acception.fakesmtp

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.subethamail.smtp.TooMuchDataException;
import org.subethamail.smtp.helper.SimpleMessageListener
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

class MessageListener implements SimpleMessageListener {
	private final static Logger log = LoggerFactory.getLogger(MessageListener.class);
	private static Session session;
	
		public boolean accept(String from, String recipient) {
			return true;
		}
	
		public void deliver(String from, String recipient, InputStream data) throws TooMuchDataException, IOException {
	
			// I use this to process bounce messages, but it's just an
			// email message that could be processed however you like
	
				if (log.isDebugEnabled())
					log.debug("Processing mail from " + from + " to " + recipient);
				log.debug "Processing mail from " + from + " to " + recipient
				def p=processData(data)
				p.fromAddress=from
				p.recipient=recipient
				FmMailMessage.withTransaction {
					def m=new FmMailMessage(p)
					m.save(failOnError:true,flush:true)
				}
		}
		def processData(InputStream data) throws IOException {
			def result=[:]
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buffer = new byte[8 * 1024];
			int count = 0;
			while (count != -1) {
				out.write(buffer, 0, count);
				count = data.read(buffer, 0, buffer.length);
			} 
			out.flush();
			
			String theMessageData = new String(out.toByteArray());
			log.debug("-------------------MESSAGE DATA----------------------");
			log.debug(theMessageData);
			log.debug("-------------------END MESSAGE DATA----------------------");

			result.data=out.toByteArray()
			
			try {
				//parse the message to get some other info and save it parsed
				//into the database
				MimeMessage message = new MimeMessage(getMailSession(), new ByteArrayInputStream(out.toByteArray()));
				result.subject=message.getSubject()
				String receivedHost = getReceivedServer(message.getHeader("Received",","));
				log.debug("Message received header: "+receivedHost);
				result.fromServer=receivedHost
				result
			} catch (Exception e) {
				e.printStackTrace();
				throw new IOException(e.getMessage());
			}
			
		}

		private String getReceivedServer(String pReceivedHeader){
			String receivedHost = "null"
			if (pReceivedHeader == null) {
				log.debug("Received Header was null")
				return receivedHost
			}
			pReceivedHeader = pReceivedHeader.trim()
			// parse out the first instance of "from"
			int start = pReceivedHeader.indexOf("from ")
			log.debug("Found start of from at position: "+start)
			if (start >= 0) {
				start = start + 5; // add 5 for the "from " string to get to end
									// of word
				int end = pReceivedHeader.indexOf(" ", start)
				log.debug("Found end of from at position: "+end)
				if (end > start) {
					receivedHost = pReceivedHeader.substring(start, end)
					log.debug("returning host=["+receivedHost+"]")
				}
			}
			return receivedHost;
		}
		private static Session getMailSession() {
			if (session != null) {
				return session;
			}
			Properties mprops = new Properties();
			session = Session.getInstance(mprops);
			return session;
		}
}
