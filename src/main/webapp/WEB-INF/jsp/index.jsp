<%--
  Created by IntelliJ IDEA.
  User: Anson. R
  Date: 7/28/2024
  Time: 3:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<jsp:include page="include/header.jsp" />

<html>
<head>
    <link rel="stylesheet" href="/css/global.css">

</head>
<body>

<div style="position: relative; height: 100vh; margin: 0;">
    <section style="position: absolute; left: 2%; bottom: 12%; padding: 20px; text-align: left;">
        <h1 class="raleway-bold" style="color:white; font-size: 175px; margin: 0;">WELCOME</h1>
        <h1 class="raleway-bold" style="color:white; font-size: 100px; margin: 0;">TO</h1>
        <h1 class="raleway-bold" style="color:white; font-size: 100px; margin: 0;">MY LIBRARY</h1>

        <div style="position: absolute; right: 0%; bottom: 6%; padding: 20px; text-align: right; display: flex; align-items: center;">
            <a href="/login" style="margin-right: 10px;"><button type="button" class="btn btn-light">Log In</button></a>
            <h2 class="raleway-bold" style="color:white; font-size: 20px; margin: 0;">OR</h2>
            <a href="/book/search" style="margin-left: 10px;"><button type="button" class="btn btn-light">Search For A Book</button></a>
        </div>
    </section>
</div>


</body>
</html>

<jsp:include page="include/footer.jsp" />