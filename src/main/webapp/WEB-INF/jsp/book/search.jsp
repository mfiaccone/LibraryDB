<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white" >BOOK SEARCH</h1>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row justify-content-center pt-5 pb-3">
            <div class="col-8 text-center" >
                <form action="/book">
                    <div class="mb-3">
                        <input type="text" value="${searchTerm}" class="form-control" id="bookSearch" name="search" placeholder="Search For A Book"/>
                    </div>
                    <select class="form-select" aria-label="Default select example" name="genre">
                        <option selected>Or Select A Genre</option>
                        <option value="Fiction" ${selectedGenre == 'Fiction' ? 'selected' : ''}>Fiction</option>
                        <option value="Classic" ${selectedGenre == 'Classic' ? 'selected' : ''}>Classic</option>
                        <option value="Fantasy" ${selectedGenre == 'Fantasy' ? 'selected' : ''}>Fantasy</option>
                        <option value="Dystopian Fiction" ${selectedGenre == 'Dystopian Fiction' ? 'selected' : ''}>Dystopian Fiction</option>
                        <option value="Science Fiction" ${selectedGenre == 'Science Fiction' ? 'selected' : ''}>Science Fiction</option>
                        <option value="Thriller" ${selectedGenre == 'Thriller' ? 'selected' : ''}>Thriller</option>
                        <option value="Historical Fiction" ${selectedGenre == 'Historical Fiction' ? 'selected' : ''}>Historical Fiction</option>
                        <option value="Romance" ${selectedGenre == 'Romance' ? 'selected' : ''}>Romance</option>
                    </select>
                    <button type="submit" class="btn btn-primary">Search</button>
                </form>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row pt-5">
            <div class="col-12">
                <h2 class="text-center raleway-normal" style="color: white;">Books Found (${books.size()})</h2>
            </div>
        </div>
    </div>
    <div class="row raleway-normal custom-section">
        <div class="col-12">
            <table class="table">
                <tr>
                    <th style="color: white;">Book Id</th>
                    <th style="color: white;">Title</th>
                    <th style="color: white;">Author</th>
                    <th style="color: white;">ISBN</th>
                    <th style="color: white;">Available Copies</th>
                    <th style="color: white;">Actions</th>
                </tr>
                <c:forEach items="${books}" var="book">
                    <tr>
                        <td style="color: white;">${book.bookId}</td>
                        <td style="color: white;">${book.title}</td>
                        <td style="color: white;">${book.author}</td>
                        <td style="color: white;">${book.isbn}</td>
                        <td style="color: white;">${book.availableCopies}</td>
                        <td style="color: white;">
                            <a href="${pageContext.request.contextPath}/book/detail?bookId=${book.bookId}" class="btn btn-sm btn-info me-2">View Details</a>
                            <form action="/user/checkout" method="post" class="d-inline">
                                <input type="hidden" name="bookId" value="${book.bookId}">
                                <button type="submit" class="btn btn-sm btn-primary">Checkout</button>
                            </form>
                        </td>
                    </tr>

                </c:forEach>
            </table>
        </div>
    </div>
</section>



<jsp:include page="../include/footer.jsp" />