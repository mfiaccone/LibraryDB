<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white" >MY BOOKSHELF</h1>
            <h2 class="text-center raleway-normal" style="color:white">Hello, <sec:authentication property="name"/></h2>
        </div>
    </div>
</section>

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
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">${books[status.index].title}</h5>
                                <h6 class="card-subtitle mb-2 text-muted">${books[status.index].author}</h6>
                                <p class="card-text">
                                    <strong>ISBN:</strong> ${books[status.index].isbn}<br>
                                    <strong>Genre:</strong> ${books[status.index].genre}<br>
                                    <strong>Borrowed:</strong> <fmt:formatDate value="${borrowedBook.borrowDate}" pattern="MM/dd/yyyy"/><br>
                                    <strong>Due:</strong> <fmt:formatDate value="${borrowedBook.dueDate}" pattern="MM/dd/yyyy"/>
                                </p>
                                <form action="/user/return" method="post">
                                    <input type="hidden" name="borrowId" value="${borrowedBook.borrowId}">
                                    <button type="submit" class="btn btn-primary">Return Book</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:otherwise>
    </c:choose>
</section>



<jsp:include page="../include/footer.jsp" />