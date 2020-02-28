<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Orders</title>
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
                <th scope="col">Id</th>
                <th scope="col">Customer</th>
                <th scope="col">Phone</th>
                <th scope="col">Address</th>
                <th scope="col">Total price</th>
                <th scope="col">Status</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="order" items="${orders}">
                <tr>
                    <td style="vertical-align: middle!important"><a href="/admin/orders/${order.id}"><c:out value="${order.id}"/></a></td>
                    <td style="vertical-align: middle!important"><c:out value="${order.firstName}"/> <c:out value="${order.lastName}"/></td>
                    <td style="vertical-align: middle!important">$<c:out value="${order.contactPhoneNo}"/></td>
                    <td style="vertical-align: middle!important"><c:out value="${order.deliveryAddress}"/></td>
                    <td style="vertical-align: middle!important"><c:out value="${order.totalPrice}"/></td>
                    <td style="vertical-align: middle!important"><c:out value="${order.status}"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
