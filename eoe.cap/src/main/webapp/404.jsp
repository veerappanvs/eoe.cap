<%@page import="java.util.Enumeration"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
    <title>Page not found</title>
</head>
<body>
 <%=request.getAttribute("javax.servlet.error.request_uri") %>
    <h1>404 Page not found </h1>
</body>
</html>