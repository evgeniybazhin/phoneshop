<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Product List</title>
    <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
            crossorigin="anonymous"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<form method="get" id="sortByForm" hidden>
    <input type="hidden" name="page" value="1"/>
    <input type="hidden" name="search" value=""/>
    <input type="hidden" name="sortBy" id=""/>
</form>
<div class="bg-light w-100">
    <div class="container p-3">
        <div class="page-header text-primary">
            <h1>Phone Shop</h1>
        </div>
    </div>
</div>
<div class="container mt-3 py-1">
    <div class="d-inline-block">
        <h3>Phones</h3>
    </div>
    <div class="d-inline-block float-right">
        <form class="form-inline my-2 my-lg-0" method="get" id="searchForm">
            <c:if test="${not empty param.sortBy}">
            <input type="hidden" name="sortBy" value="${param.sortBy}"/>
            </c:if>
            <input class="form-control mr-sm-2" type="search" placeholder="Search..." aria-label="Search" name="search" value="${param.search}"/>
        </form>
    </div>
</div>
<c:url var="patternUrl" value="/productList?&search=${param.search}"/>
<div class="container">
    <table class="table table-bordered table-striped">
        <thead style="background-color: #828082;">
        <tr class="d-table-row text-light text-center">
            <th scope="col">Image</th>
            <th scope="col">Brand</th>
            <th scope="col"><a href="${patternUrl}&sortBy=${param.sortBy.contains('_desc') ? "model_asc" : "model_desc"}">Model</a></th>
            <th scope="col">Colors</th>
            <th scope="col">Display size</th>
            <th scope="col"><a href="${patternUrl}&sortBy=${param.sortBy.contains('_desc') ? "price_asc" : "price_desc"}">Price</a></th>
            <th scope="col">Quantity</th>
            <th scope="col">Action</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${phones}" var="phone">
            <tr>
                <td class="p-0 m-0" style="width:1px;"><img width="100px" height="100px"
                                                            src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
                </td>
                <td style="vertical-align: middle!important"><c:out value="${phone.brand}"/></td>
                <td style="vertical-align: middle!important"><c:out value="${phone.model}"/></td>
                <td style="vertical-align: middle!important">
                    <c:forEach items="${phone.colors}" var="color">
                        <c:out value="${color.code}"/>
                    </c:forEach></td>
                <td style="vertical-align: middle!important"><c:out value="${phone.displaySizeInches}''"/></td>
                <td style="vertical-align: middle!important">$<c:out value="${phone.price}"/></td>
                <td style="vertical-align: middle!important"></td>
                <td class="text-center" style="vertical-align: middle!important">
                    <button class="btn btn-info">Add to cart</button>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<div class="container">
    <div class="float-left">
        <form method="get" id="gotoPageForm">
            <label>
                <c:if test="${not empty param.search}">
                    <input type="hidden" name="search" value="${param.search}"/>
                </c:if>
                <button class="btn" type="submit">Goto page</button>
                <input id="gotoPageInput" class="text-field" type="number" min="1" max="${pagesTotal}" name="page" value="${currentPage}"/>
            </label>
        </form>
    </div>
    <nav aria-label="..." class="float-right">
        <ul class="pagination">
            <li class="page-item ${currentPage eq 1 ? "disabled" : ""}">
                <a class="page-link" href="?page=${currentPage-1}&search=${param.search}&sortBy=${param.sortBy}">Previous</a>
            </li>
            <c:choose>
                <c:when test="${pagesTotal le 10}">
                    <c:forEach var="page" begin="1" end="${pagesTotal}">
                        <li class="page-item ${page eq currentPage ? "active" : ""}"><a class="page-link"
                                                                                        href="?page=${page}&search=${param.search}&sortBy=${param.sortBy}">${page}</a>
                        </li>
                    </c:forEach>
                </c:when>
                <c:when test="${pagesTotal gt 10 && (currentPage le 3 || currentPage gt pagesTotal-3)}">
                    <c:forEach var="page" begin="1" end="3">
                        <li class="page-item ${page eq currentPage ? "active" : ""}"><a class="page-link"
                                                                                        href="?page=${page}&search=${param.search}&sortBy=${param.sortBy}">${page}</a>
                        </li>
                    </c:forEach>
                    <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                    <c:forEach var="page" begin="${pagesTotal-2}" end="${pagesTotal}">
                        <li class="page-item ${page eq currentPage ? "active" : ""}"><a class="page-link"
                                                                                        href="?page=${page}&search=${param.search}&sortBy=${param.sortBy}">${page}</a>
                        </li>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link" href="?page=1&search=${param.search}&sortBy=${param.sortBy}">1</a></li>
                    <li class="page-item"><a class="page-link" href="?page=2&search=${param.search}&sortBy=${param.sortBy}">2</a></li>
                    <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                    <li class="page-item active"><a class="page-link" href="?page=${currentPage}&sortBy=${param.sortBy}">${currentPage}</a>
                    </li>
                    <li class="page-item disabled"><a class="page-link" href="#">...</a></li>
                    <li class="page-item"><a class="page-link" href="?page=${pagesTotal-1}&search=${param.search}&sortBy=${param.sortBy}">${pagesTotal-1}</a></li>
                    <li class="page-item"><a class="page-link" href="?page=${pagesTotal}&search=${param.search}&sortBy=${param.sortBy}">${pagesTotal}</a></li>
                </c:otherwise>
            </c:choose>
            <li class="page-item ${currentPage eq pagesTotal ? "disabled" : ""}">
                <a class="page-link" href="?page=${currentPage+1}&search=${param.search}&sortBy=${param.sortBy}">Next</a>
            </li>
        </ul>
    </nav>
</div>
</body>
</html>