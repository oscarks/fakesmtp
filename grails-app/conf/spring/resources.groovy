// Place your Spring DSL code here
beans = {
    // Create an in-process SMTP server for processing bounces
    smtpMessageListener(com.acception.fakesmtp.MessageListener)

    smtpMessageListenerAdapter(org.subethamail.smtp.helper.SimpleMessageListenerAdapter, ref('smtpMessageListener'))

    smtpServer(org.subethamail.smtp.server.SMTPServer, ref('smtpMessageListenerAdapter')) {
        port = 2500
//        hostName = "my.server.name"
        disableTLS = true
        maxConnections = 10
    }
}
