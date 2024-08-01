<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="../include/header.jsp" />

<!-- a page header -->
<section class="custom-section">
    <div class="container">
        <div class="row pt-5 pb-5">
            <h1 class="text-center raleway-normal" style="color:white" >BOOK DETAILS</h1>
            <h2 class="text-center raleway-normal" style="color:white">Title: ${book.title}</h2>
        </div>
    </div>
</section>

<section>

        <div class="row justify-content-center custom-section">
            <table class="table table-success table-striped table-bordered table-responsive" style="width: 750pt">
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

            </table>
        </div>



</section>



<jsp:include page="../include/footer.jsp" />