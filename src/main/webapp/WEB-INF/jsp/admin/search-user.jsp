<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<section style="background-color:darkgray">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center">User Search Page</h1>
        </div>
    </div>
</section>

<!-- a search form -->
<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center">
                <form action="/admin/userSearch">
                    <div class="mb-3">
                        <label for="search" class="form-label"><h4>Employee Search</h4></label>
                        <input type="text" value="${searchTerm}" class="form-control" id="search" name="search" placeholder="Enter User Id, Name, or Email"/>
                    </div>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>

<section>

    <div class="row justify-content-center custom-section">
        <table class="table table-success table-striped table-bordered table-responsive" style="width: 750pt">
            <c:forEach var="user" items="${users}">
                <tr>
                    <td style="font-weight: bolder;">ID:</td>
                    <td><a href="/admin/editUser?id=${user.id}">${user.id}</a></td>
                </tr>
                <tr>
                    <td style="font-weight: bolder;">Name:</td>
                    <td>${user.name}</td>
                </tr>
                <tr>
                    <td style="font-weight: bolder;">Email:</td>
                    <td>${user.email}</td>
                </tr>
            </c:forEach>
        </table>
    </div>


</section>

<jsp:include page="../include/footer.jsp"/>