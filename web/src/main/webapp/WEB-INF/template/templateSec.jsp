<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <script src="//cdnjs.cloudflare.com/ajax/libs/numeral.js/1.4.1/numeral.min.js"></script>
    <link href="//stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <script src="//code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="//ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
</head>
<body>
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <c:url value="/j_spring_security_logout" var="logoutUrl"/>
    <form style="float: right" action="${logoutUrl}" method="post">
        <button type="submit" class="btn btn-primary">Logout</button>
    </form>
    <form style="float: right" action="/admin/orders">
        <button type="submit" class="btn btn-primary">Admin</button>
    </form>
</sec:authorize>
<sec:authorize access="!hasRole('ROLE_ADMIN')">
    <form style="float: right" action="${pageContext.request.contextPath}/admin">
        <button type="submit" class="btn btn-primary">Login</button>
    </form>
</sec:authorize>
</body>
</html>