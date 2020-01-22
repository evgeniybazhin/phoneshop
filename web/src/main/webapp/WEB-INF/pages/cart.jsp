<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cart</title>
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
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach var="cartItem" items="${cartList}">
                <tr>
                     <td style="vertical-align: middle!important"><c:out value="${cartItem.phone.brand}"/></td>
                     <td style="vertical-align: middle!important"><c:out value="${cartItem.phone.model}"/></td>
                     <td style="vertical-align: middle!important">$<c:out value="${cartItem.phone.price}"/></td>
                     <td style="vertical-align: middle!important">
                         <input type="text" class="form-control" id="phone${cartItem.phone.id}Quantity" value="${cartItem.quantity}" style="width:70px;"/>
                     </td>
                     <td class="text-center" style="vertical-align: middle!important">
                         <button class="btn btn-info" onclick="updateCart(${cartItem.phone.id})">Update</button>
                         <form method="post" action="${pageContext.request.contextPath}/cart">
                             <input name="id" type="hidden" value="${cartItem.phone.id}">
                             <button class="btn btn-info">Remove</button>
                         </form>
                     </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</div>
<p>Total price - ${priceTotal}</p>
<script>
    var updateCart = function (id) {
        var quantityField = $('#phone' + id + 'Quantity');
        var quantity = quantityField.val();
        $.ajax({
            type: "put",
            url: "${pageContext.request.contextPath}/cart",
            data : JSON.stringify({
                quantity: quantity,
                phoneId: id
            }),
            contentType: "application/json",
            dataType: "json",
        });
    }
</script>
</body>
</html>
