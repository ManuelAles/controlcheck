<%-- form.jsp --%>

<%@page language="java"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" tagdir="/WEB-INF/tags"%>

<acme:form>


	<acme:form-textbox code="employer.duty.form.label.title" path="title"/>
	<acme:form-double code="employer.duty.form.label.percentage" path="percentage" />
	<acme:form-textarea code="employer.duty.form.label.description" path="description"/>

	<acme:form-submit test="${command == 'create'}" 
	code="employer.duty.form.button.create" 
	action="/employer/duty/create?jobId=${param.jobId}" />

	<acme:form-return code="employer.duty.form.button.return"/>	
</acme:form>