<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quick Order</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <div>
        <c:if test="${cartInfo.totalPrice > 0}">
            <span class="badge badge-secondary">${cartInfo.totalPrice}</span>
        </c:if>
    </div>
    <spring:form id="quickOrder" method="post" modelAttribute="quickOrder">
        <table class="table table-bordered table-striped">
            <thead style="background-color: #828082;">
            <tr class="d-table-row text-light text-center">
                <th scope="col">Brand</th>
                <th scope="col">Model</th>
                <th scope="col">Quantity</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${currentItems == null}">
                    <c:forEach var="item" items="${itemsQuickOrder}">
                        <tr>
                            <td style="vertical-align: middle!important"><c:out value="${item.brand}"/></td>
                            <td style="vertical-align: middle!important"><c:out value="${item.model}"/></td>
                            <td style="vertical-align: middle!important">
                                <spring:input path="quickOrderItems['${item.id}']" type="number" class="form-control" value="1"/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <c:forEach var="item" items="${currentItems}">
                        <tr>
                            <td style="vertical-align: middle!important"><c:out value="${item.brand}"/></td>
                            <td style="vertical-align: middle!important"><c:out value="${item.model}"/></td>
                            <td style="vertical-align: middle!important">
                                <spring:input path="quickOrderItems['${item.id}']" type="number" class="form-control" value="1"/>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </spring:form>
    <button type="submit" form="quickOrder">Add to cart</button>
    <form action="${pageContext.request.contextPath}/">
        <button>Back</button>
    </form>
</div>
</body>
</html>
