<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
    <jsp:include page="../template/templateSec.jsp"/>
    <table class="table table-bordered table-striped">
        <thead style="background-color: #828082;">
        <tr class="d-table-row text-light text-center">
            <th scope="col">Brand</th>
            <th scope="col">Model</th>
            <th scope="col">Price</th>
            <th scope="col">Quantity</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="orderItem" items="${order.orderItems}">
            <tr>
                <td style="vertical-align: middle!important"><c:out value="${orderItem.phone.brand}"/></td>
                <td style="vertical-align: middle!important"><c:out value="${orderItem.phone.model}"/></td>
                <td style="vertical-align: middle!important">$<c:out value="${orderItem.phone.price}"/></td>
                <td style="vertical-align: middle!important">
                    <c:out value="${orderItem.quantity}"/>
                </td>
            </tr>
        </c:forEach>
        <tr>
            <td style="vertical-align: middle!important">Subtotal</td>
            <td style="vertical-align: middle!important"><c:out value="${order.subtotal}"/></td>
        </tr>
        <tr>
            <td style="vertical-align: middle!important">Delivery</td>
            <td style="vertical-align: middle!important"><c:out value="${order.deliveryPrice}"/></td>
        </tr>
        <tr>
            <td style="vertical-align: middle!important"><c:out value="${order.totalPrice}"/></td>
        </tr>
        <tr>
            <td style="vertical-align: middle!important"><c:out value="${order.status}"/></td>
        </tr>
        </tbody>
    </table>
    <c:if test="${order.status.toString().equals('NEW')}">
        <form method="post">
            <c:url value="/admin/orders/${order.id}/updateStatus?status=DELIVERED" var="updateStatusDeliveredUrl"/>
            <button type="submit" formaction="${updateStatusDeliveredUrl}" class="btn btn-primary mb-2">Delivered</button>
            <c:url value="/admin/orders/${order.id}/updateStatus?status=REJECTED" var="updateStatusRejectedUrl"/>
            <button type="submit" formaction="${updateStatusRejectedUrl}" class="btn btn-primary mb-2">Rejected</button>
        </form>
    </c:if>
    <form action="${pageContext.request.contextPath}/admin/orders">
        <button>Back</button>
    </form>
</div>
</body>
</html>
