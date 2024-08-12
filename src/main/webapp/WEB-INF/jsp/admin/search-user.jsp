<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white" >SEARCH   FOR   A   USER   TO   EDIT</h1>
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
                        <input type="text" value="${searchTerm}" class="form-control" id="search" name="search" placeholder="Enter Name or Email"/>
                    </div>
                    <button type="submit" class="btn btn-light">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container custom-section">
        <div class="row pt-5">
            <div class="col-12">
                <h2 class="text-center raleway-normal" style="color: white;">User Information</h2>
            </div>
        </div>
    </div>
    <div class="row raleway-normal custom-section">
        <div class="col-12">
            <table class="table">
                <thead>
                <tr>
                    <th style="color: white;">Attribute</th>
                    <th style="color: white;">Value</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="user" items="${users}">
                    <tr>
                        <td style="font-weight: bolder; color: white;">ID:</td>
                        <td><a href="/admin/editUser?id=${user.id}" class="text-light">${user.id}</a></td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder; color: white;">Name:</td>
                        <td class="text-light">${user.name}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder; color: white;">Email:</td>
                        <td class="text-light">${user.email}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<jsp:include page="../include/footer.jsp"/>