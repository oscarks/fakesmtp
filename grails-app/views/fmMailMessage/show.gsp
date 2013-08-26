
<%@ page import="com.acception.fakesmtp.FmMailMessage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fmMailMessage.label', default: 'FmMailMessage')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-fmMailMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-fmMailMessage" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list fmMailMessage">
			
				<g:if test="${fmMailMessageInstance?.subject}">
				<li class="fieldcontain">
					<span id="subject-label" class="property-label"><g:message code="fmMailMessage.subject.label" default="Subject" /></span>
					<span class="property-value" aria-labelledby="subject-label"><g:fieldValue bean="${fmMailMessageInstance}" field="subject"/></span>
				</li>
				</g:if>
			
				<g:if test="${fmMailMessageInstance?.fromAddress}">
				<li class="fieldcontain">
					<span id="fromAddress-label" class="property-label"><g:message code="fmMailMessage.fromAddress.label" default="From Address" /></span>
					<span class="property-value" aria-labelledby="fromAddress-label"><g:fieldValue bean="${fmMailMessageInstance}" field="fromAddress"/></span>
				</li>
				</g:if>
			
				<g:if test="${fmMailMessageInstance?.recipient}">
				<li class="fieldcontain">
					<span id="recipient-label" class="property-label"><g:message code="fmMailMessage.recipient.label" default="Recipient" /></span>
					
						<span class="property-value" aria-labelledby="recipient-label"><g:fieldValue bean="${fmMailMessageInstance}" field="recipient"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fmMailMessageInstance?.fromServer}">
				<li class="fieldcontain">
					<span id="fromServer-label" class="property-label"><g:message code="fmMailMessage.fromServer.label" default="From Server" /></span>
					
						<span class="property-value" aria-labelledby="fromServer-label"><g:fieldValue bean="${fmMailMessageInstance}" field="fromServer"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${fmMailMessageInstance?.dateCreated}">
				<li class="fieldcontain">
					<span id="dateCreated-label" class="property-label"><g:message code="fmMailMessage.dateCreated.label" default="Date Created" /></span>
					<span class="property-value" aria-labelledby="dateCreated-label"><g:formatDate date="${fmMailMessageInstance?.dateCreated}" /></span>
				</li>
				</g:if>
			
				<g:if test="${fmMailMessageInstance?.data}">
				<li class="fieldcontain">
					<span id="data-label" class="property-label"><g:message code="fmMailMessage.text.label" default="Text" /></span>
					<span class="property-value" aria-labelledby="data-label">
					<pre>${fmMailMessageInstance?.text}</pre>
					</span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${fmMailMessageInstance?.id}" />
					<g:link class="edit" action="edit" id="${fmMailMessageInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
