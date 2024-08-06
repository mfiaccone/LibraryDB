<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white" >USER DETAILS</h1>
            <h2 class="text-center raleway-normal" style="color:white">Hello, <sec:authentication property="name"/></h2>
        </div>
    </div>
</section>

<section>

    <div class="row justify-content-center custom-section">
        <table class="table table-success table-striped table-bordered table-responsive" style="width: 750pt">
            <tr>
                <td style="font-weight: bolder;">User ID:</td>
                <td>${user.id}</td>
            </tr>
            <tr>
                <td style="font-weight: bolder;">Name:</td>
                <td>${user.name}</td>
            </tr>
            <tr>
                <td style="font-weight: bolder;">Email:</td>
                <td>${user.email}</td>
            </tr>
            <tr>
                <td style="font-weight: bolder;">Books Checked Out:</td>
<%--                <td>${user.isbn}</td> put # of books checked out here later--%>
            </tr>
            <tr>
                <td style="font-weight: bolder;">Account Created:</td>
                <td>${user.createDate}</td>
            </tr>
            <tr>
                <td style="font-weight: bolder;">Actions</td>
                <td><a href="bookshelf?id=${user.id}">Bookshelf</a>    <a href="editUser">Edit</a></td>
            </tr>

        </table>
    </div>



</section>



<jsp:include page="../include/footer.jsp" />