<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>

<%@ include file="header.jsp"%>

<body>


<h1>Volunteers.com </h1>

Find the right volunteer

or Post a project and see who is offering to help.
<% 
    response.sendRedirect(request.getContextPath()+"/volunteer?pageId=-1"); 

%>

</body>
</html>
