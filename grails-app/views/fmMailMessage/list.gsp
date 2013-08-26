
<%@ page import="com.acception.fakesmtp.FmMailMessage" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'fmMailMessage.label', default: 'FmMailMessage')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-fmMailMessage" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-fmMailMessage" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<g:sortableColumn property="id" title="${message(code: 'fmMailMessage.id.label', default: 'id')}" />
						<g:sortableColumn property="subject" title="${message(code: 'fmMailMessage.subject.label', default: 'Subject')}" />
						<g:sortableColumn property="fromAddress" title="${message(code: 'fmMailMessage.fromAddress.label', default: 'From Address')}" />
						<g:sortableColumn property="recipient" title="${message(code: 'fmMailMessage.recipient.label', default: 'Recipient')}" />
						<g:sortableColumn property="fromServer" title="${message(code: 'fmMailMessage.fromServer.label', default: 'From Server')}" />
						<g:sortableColumn property="dateCreated" title="${message(code: 'fmMailMessage.dateCreated.label', default: 'Date Created')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${fmMailMessageInstanceList}" status="i" var="fmMailMessageInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${fmMailMessageInstance.id}">${fieldValue(bean: fmMailMessageInstance, field: "id")}</g:link></td>
					
						<td>${fieldValue(bean: fmMailMessageInstance, field: "subject")}</td>
					
						<td>${fieldValue(bean: fmMailMessageInstance, field: "fromAddress")}</td>
					
						<td>${fieldValue(bean: fmMailMessageInstance, field: "recipient")}</td>
					
						<td>${fieldValue(bean: fmMailMessageInstance, field: "fromServer")}</td>
					
						<td><g:formatDate date="${fmMailMessageInstance.dateCreated}" /></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${fmMailMessageInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
