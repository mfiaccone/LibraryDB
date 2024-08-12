<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- Page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center merriweather-bold" style="color:white; font-size: 60px">Admin Dashboard</h1>
<%--            <h2 class="text-center raleway-normal" style="color:white">Hello, <sec:authentication property="name"/></h2>--%>
        </div>
    </div>
</section>

<section>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-12">
                <div class="form-container-two mx-auto">
                 <div>
                         <a href="/admin/editBook?bookId=${borrowedBook.book.bookId}" class="btn btn-primary btn-sm">Edit Book</a>
                         <a href="/admin/editUser?id=${borrowedBook.user.id}" class="btn btn-secondary btn-sm">Edit User</a>
                 </div>

                <table class="table table-bordered table-responsive raleway-normal" style="color:white">
                    <thead class="table-dark">
                    <p class="text-align-left merriweather-bold" style="color:white; font-size: 30px">Books Currently Checked Out:</p>
                    <tr>
                        <th>Borrow ID</th>
                        <th>Book Title</th>
                        <th>User Name</th>
                        <th>Borrow Date</th>
                        <th>Due Date</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${borrowedBooks}" var="borrowedBook">
                        <tr>
                            <td>${borrowedBook.borrowId}</td>
                            <td>${borrowedBook.book.title}</td>
                            <td>${borrowedBook.user.name}</td>
                            <td>${borrowedBook.borrowDate}</td>
                            <td>${borrowedBook.dueDate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp" />