<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Order Overview</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="container">
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
        </tbody>
    </table>

    First name ${order.firstName}<br>
    Last name ${order.lastName}<br>
    Address ${order.deliveryAddress}<br>
    Phone ${order.contactPhoneNo}
</div>
</body>
</html>
