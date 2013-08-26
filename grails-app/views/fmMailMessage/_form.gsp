<%@ page import="com.acception.fakesmtp.FmMailMessage" %>



<div class="fieldcontain ${hasErrors(bean: fmMailMessageInstance, field: 'data', 'error')} ">
	<label for="data">
		<g:message code="fmMailMessage.data.label" default="Data" />
		
	</label>
	<input type="file" id="data" name="data" />
</div>

<div class="fieldcontain ${hasErrors(bean: fmMailMessageInstance, field: 'subject', 'error')} ">
	<label for="subject">
		<g:message code="fmMailMessage.subject.label" default="Subject" />
		
	</label>
	<g:textField name="subject" value="${fmMailMessageInstance?.subject}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fmMailMessageInstance, field: 'fromAddress', 'error')} ">
	<label for="fromAddress">
		<g:message code="fmMailMessage.fromAddress.label" default="From Address" />
		
	</label>
	<g:textField name="fromAddress" value="${fmMailMessageInstance?.fromAddress}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fmMailMessageInstance, field: 'recipient', 'error')} ">
	<label for="recipient">
		<g:message code="fmMailMessage.recipient.label" default="Recipient" />
		
	</label>
	<g:textField name="recipient" value="${fmMailMessageInstance?.recipient}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: fmMailMessageInstance, field: 'fromServer', 'error')} ">
	<label for="fromServer">
		<g:message code="fmMailMessage.fromServer.label" default="From Server" />
		
	</label>
	<g:textField name="fromServer" value="${fmMailMessageInstance?.fromServer}"/>
</div>

