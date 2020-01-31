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
    <spring:form id="updateForm" method="post" modelAttribute="updateForm">
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
                        <spring:input path="itemsForUpdate['${cartItem.phone.id}']" value="${cartItem.quantity}"/>
                    </td>
                    <td class="text-center" style="vertical-align: middle!important">
                        <c:url value="/cart/delete/${cartItem.phone.id}" var="deletePhoneUrl"/>
                        <button onclick="deleteCartItem('${deletePhoneUrl}');" class="btn btn-primary mb-2">
                            Delete
                        </button>
                    </td>
                </tr>
            </c:forEach>

        </tbody>
    </table>
    </spring:form>
    <c:if test="${cartList.size() > 0}">
        <button type="submit" form="updateForm">Update</button>
    </c:if>
</div>
<p>Total price - ${priceTotal}</p>
<script>
    function deleteCartItem(url) {
        $.ajax({
            url: url,
            type: "DELETE"
        });
        return false;
    }
</script>
</body>
</html>
