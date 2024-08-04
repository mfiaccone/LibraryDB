<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white;">Create Book</h1>
        </div>
    </div>
</section>

<section>
    <div class="container custom-section">
        <div class="row pt-5 ">
            <div class="col-12 ">
                <form action="/admin/createBook" method="post" enctype="multipart/form-data" modelAttribute="form">


                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                    <!-- Title Input Field -->
                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="title" class="col-form-label" style="color: white;">Title</label>
                        </div>
                        <div class="col-4">
                            <input type="text"
                                   id="title"
                                   name="title"
                                   class="form-control <c:if test="${bindingResult.hasFieldErrors('title')}">is-invalid</c:if>"
                                   value="${form.title}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('title')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('title')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Author Input Field -->

                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="author" class="col-form-label" style="color: white;">Author Full Name</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="author" name="author" class="form-control <c:if test="${bindingResult.hasFieldErrors('author')}">is-invalid</c:if>" value="${form.author}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('author')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('author')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- ISBN Input Field -->

                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="isbn" class="col-form-label" style="color: white;">ISBN</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="isbn" name="isbn" class="form-control <c:if test="${bindingResult.hasFieldErrors('isbn')}">is-invalid</c:if>" value="${form.isbn}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('isbn')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('isbn')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Genre Input Field -->

                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="genre" class="col-form-label" style="color: white;">Genre</label>
                        </div>
                        <div class="col-4">
                            <input type="text" id="genre" name="genre" class="form-control <c:if test="${bindingResult.hasFieldErrors('genre')}">is-invalid</c:if>" value="${form.genre}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('genre')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('genre')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Available Copies Input Field -->

                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="availableCopies" class="col-form-label" style="color: white;">Available Copies</label>
                        </div>
                        <div class="col-4">
                            <input type="number" id="availableCopies" name="availableCopies" class="form-control <c:if test="${bindingResult.hasFieldErrors('availableCopies')}">is-invalid</c:if>" value="${form.availableCopies}">
                        </div>
                    </div>
                    <c:if test="${bindingResult.hasFieldErrors('availableCopies')}">
                        <div class="row align-items-center justify-content-center">
                            <div class="offset-2 col-4">
                                <div style="color:red">
                                    <c:forEach items="${bindingResult.getFieldErrors('availableCopies')}" var="error">
                                        ${error.defaultMessage}<br>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                    </c:if>

                    <!-- Cover Upload Field -->

                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <label for="cover" class="col-form-label" style="color: white;">Cover</label>
                        </div>
                        <div class="col-4">
                            <input type="file" id="cover" name="cover" class="form-control">
                        </div>
                    </div>


                    <div class="row justify-content-center ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />