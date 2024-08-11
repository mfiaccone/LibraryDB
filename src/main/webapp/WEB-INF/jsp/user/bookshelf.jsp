<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center merriweather-bold" style="color:white; font-size: 80px" >MY BOOKSHELF</h1>
            <h2 class="text-center raleway-normal" style="color:white">Hello, <sec:authentication property="name"/></h2>
        </div>
    </div>
</section>

<body>
<!-- Borrowed Books List -->
<section class="container mt-5">
    <c:choose>
        <c:when test="${empty borrowedBooks}">
            <p style="color:white">You haven't borrowed any books yet.</p>
        </c:when>
        <c:otherwise>
            <div class="row">
                <c:forEach items="${borrowedBooks}" var="borrowedBook" varStatus="status">
                    <div class="col-md-4 mb-4">
                        <div class="flip-card">
                            <div class="flip-card-inner">
                                <div class="flip-card-front">
                                    <img src="${pageContext.request.contextPath}${books[status.index].coverImageUrl}" class="card-img-top" alt="Image Coming Soon">
                                </div>
                                <div class="flip-card-back">
                                    <div class="card h-100">
                                        <div class="card-body d-flex flex-column">
                                            <h5 class="card-title">${books[status.index].title}</h5>
                                            <h6 class="card-subtitle mb-2 text-muted">${books[status.index].author}</h6>
                                            <p class="card-text">
                                                <strong>ISBN:</strong> ${books[status.index].isbn}<br>
                                                <strong>Genre:</strong> ${books[status.index].genre}<br>
                                                <strong>Borrowed:</strong> <fmt:formatDate value="${borrowedBook.borrowDate}" pattern="MM/dd/yyyy"/><br>
                                                <strong>Due:</strong> <fmt:formatDate value="${borrowedBook.dueDate}" pattern="MM/dd/yyyy"/>
                                            </p>
                                            <div class="mt-auto">
                                                <div class="d-flex justify-content-between">
                                                    <form action="/user/return" method="post" class="mr-2">
                                                        <input type="hidden" name="borrowId" value="${borrowedBook.borrowId}">
                                                        <button type="submit" class="btn btn-outline-secondary return-book-button">Return Book</button>
                                                    </form>
                                                    <div>
                                                    <a href="/review/create?bookId=${books[status.index].bookId}" class="btn btn-outline-secondary">Review Book</a>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>

<script src="${pageContext.request.contextPath}/pub/script/javascript.js"></script>

</body>

<jsp:include page="../include/footer.jsp" />