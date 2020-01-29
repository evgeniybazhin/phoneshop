<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Product Detail</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<c:if test="${phone ne null}">
    <div class="container mt-3">
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/productList">Back to product List</a>
    </div>

    <div class="container mt-3">
        <div class="float-left">
            <div>
                <div>
                    <h4><c:out value="${phone.model}"/></h4>
                    <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}"/>
                </div>
            </div>
            <div class="mt-3" style="width: 350px;">
                <p><c:out value="${phone.description}"/></p>
            </div>
        </div>
        <div class="container mt-3">
            <div class="mt-4" style="width: 350px;">
                <input type="text" class="form-control" id="phone${phone.id}Quantity" value="1" style="width:70px;"/>
                <button class="btn btn-info" onclick="addToCart(${phone.id})">Add to cart</button>
            </div>
        </div>
        <div class="float-right" style="width: 40%;">
            <h4><b>Display</b></h4>
            <table class="table table-bordered">
                <tbody>
                <tr><td>Size: </td><td><c:out value="${phone.displaySizeInches}"/>''</td></tr>
                <tr><td>Resolution: </td><td><c:out value="${phone.displayResolution}"/></td></tr>
                <tr><td>Technology: </td><td><c:out value="${phone.displayTechnology}"/></td></tr>
                <tr><td>Pixel Density: </td><td><c:out value="${phone.pixelDensity}"/></td></tr>
                </tbody>
            </table>
            <br/>
            <h4><b>Dimensions & weight</b></h4>
            <table class="table table-bordered">
                <tbody>
                <tr><td>Length: </td><td><c:out value="${phone.lengthMm.intValue()}"/>mm</td></tr>
                <tr><td>Width: </td><td><c:out value="${phone.widthMm.intValue()}"/>mm</td></tr>
                <tr><td>Weight: </td><td><c:out value="${phone.weightGr.intValue()}"/>g</td></tr>
                </tbody>
            </table>
            <br/>
            <h4><b>Camera</b></h4>
            <table class="table table-bordered">
                <tbody>
                <tr><td>Front: </td><td><c:out value="${phone.frontCameraMegapixels}"/> MP</td></tr>
                <tr><td>Back: </td><td><c:out value="${phone.backCameraMegapixels}"/> MP</td></tr>
                </tbody>
            </table>
            <br/>
            <h4><b>Battery</b></h4>
            <table class="table table-bordered">
                <tbody>
                <tr><td>Talk time: </td><td><c:out value="${phone.talkTimeHours.intValue()}"/>h</td></tr>
                <tr><td>Stand by time: </td><td><c:out value="${phone.standByTimeHours.intValue()}"/>h</td></tr>
                <tr><td>Battery capacity: </td><td><c:out value="${phone.batteryCapacityMah}"/>mAh</td></tr>
                </tbody>
            </table>
            <br/>
            <h4><b>Other</b></h4>
            <table class="table table-bordered">
                <tbody>
                <tr><td>Colors: </td><td><c:forEach items="${phone.colors}" var="color" varStatus="loop"><c:out value="${color.code}"/><c:if test="${not loop.last}">, </c:if></c:forEach></td></tr>
                <tr><td>Device type: </td><td><c:out value="${phone.deviceType}"/></td></tr>
                <tr><td>Bluetooth: </td><td><c:out value="${phone.bluetooth}"/></td></tr>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<script>
    var addToCart = function (id) {
        var quantityField = $('#phone' + id + 'Quantity');
        var quantity = quantityField.val();
        $.post({
            url: "${pageContext.request.contextPath}/ajaxCart",
            data : JSON.stringify({
                quantity: quantity,
                phoneId: id
            }),
            contentType: "application/json",
            dataType: "json",
            success: updateCartStatus
        });
    };

    var updateCartStatus = function(status) {
        if(status.message != null) {
            alert(status.message)
        }
    };
</script>
</body>
</html>
