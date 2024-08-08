<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp"/>

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white;">LEAVE A REVIEW</h1>
            <h2 class="text-center raleway-normal" style="color:white">Title: ${book.title}</h2>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5 ">
            <div class="col-12">
                <form action="/review/create" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

<%--                    <input type="hidden" name="reviewId" value="${form.reviewId}">--%>
<%--                    <input type="hidden" name="userId" value="${form.userId}">--%>
                    <input type="hidden" name="bookId" value="${form.bookId}">
<%--                    <input type="hidden" name="reviewDate" value="${form.reviewDate}">--%>

                    <%--Star Rating Input Field--%>
<%--                    <select class="form-select" aria-label="Default select example" name="rating">--%>
<%--                        <option selected>Select A Star Rating</option>--%>
<%--                        <option value="1">One</option>--%>
<%--                        <option value="2">Two</option>--%>
<%--                        <option value="3">Three</option>--%>
<%--                        <option value="4">Four</option>--%>
<%--                        <option value="5">Five</option>--%>
<%--                    </select>--%>

                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="rating" id="oneStar" value="1">
                        <label class="form-check-label" for="oneStar">
                            <i class="fa-regular fa-star gradient-icon"></i>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="rating" id="twoStar" value="2">
                        <label class="form-check-label" for="twoStar">
                            <i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="rating" id="threeStar" value="3">
                        <label class="form-check-label" for="threeStar">
                            <i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="rating" id="fourStar" value="4">
                        <label class="form-check-label" for="fourStar">
                            <i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i>
                        </label>
                    </div>
                    <div class="form-check">
                        <input class="form-check-input" type="radio" name="rating" id="fiveStar" value="5">
                        <label class="form-check-label" for="fiveStar">
                            <i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i><i class="fa-regular fa-star gradient-icon"></i>
                        </label>
                    </div>


                    <%--Review Body Input Field--%>
                    <div class="row align-items-center justify-content-center pb-3">
                        <div class="col-2">
                            <div class="input-group" >
                                <span class="input-group-text" >With textarea</span>
                                <textarea class="form-control" aria-label="With textarea" name="reviewText"></textarea>
                            </div>
                        </div>
                    </div>


                    <div class="row justify-content-center ">
                        <div class="col-auto text-center">
                            <button type="submit" class="btn btn-primary">Submit</button>
                        </div>
                    </div>
                </form>
                <p style="color:white">Book ID: ${form.bookId}</p>
            </div>
        </div>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />