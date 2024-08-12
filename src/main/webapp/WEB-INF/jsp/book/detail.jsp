<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h2 class="text-center raleway-bold" style="color:white; font-size: 60px">${book.title}</h2>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row justify-content-center custom-section raleway-normal">
            <!-- Column for the cover image -->
            <div class="col-md-4 d-flex align-items-center">
                <img src="${book.coverImageUrl}" alt="Cover Image" class="img-fluid"/>
            </div>

            <!-- Column for the table -->
            <div class="col-md-8">
                <table class="table" style="color: white;">
                    <tr>
                        <td style="font-weight: bolder;">Book ID:</td>
                        <td>${book.bookId}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Title:</td>
                        <td>${book.title}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Author:</td>
                        <td>${book.author}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">ISBN:</td>
                        <td>${book.isbn}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Available Copies:</td>
                        <td>${book.availableCopies}</td>
                    </tr>
                    <tr>
                        <td style="font-weight: bolder;">Reviews:</td>
                        <td>
                            <c:forEach var="review" items="${book.reviews}">
                                <div>
                                    <strong>Rating:</strong> ${review.rating}/5<br>
                                    <strong>Review:</strong> "${review.reviewText}"<br>
                                    <strong>Date:</strong> <fmt:formatDate value="${review.reviewDate}" pattern="dd/MM/yyyy" /><br>
                                    <hr>
                                </div>
                            </c:forEach>
                        </td>
                    <tr>
                        <td colspan="2">
                            <form action="/user/checkout" method="post" class="text-center">
                                <input type="hidden" name="bookId" value="${book.bookId}">
                                <button type="submit" class="btn btn-light">Checkout</button>
                            </form>
                        </td>
                    </tr>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />