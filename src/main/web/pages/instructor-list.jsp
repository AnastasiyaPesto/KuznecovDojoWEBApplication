<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="instructorListBean" type="web.InstructorListBean" scope="request" />
<html>
<head>
    <title>Школа карате - Kuznecov Dojo</title>
</head>
<body>
<h1>Привет, будущий чемпион!</h1>
<c:choose>
    <c:when test="${not empty instructorListBean.instructors}">
        <table border="1">
            <tbody>
            <c:forEach items="${instructorListBean.instructors}" var="instructor">
                <tr>
                    <td>${instructor.firstName}</td>
                    <td>${instructor.secondName}</td>
                    <td>${instructor.age}</td>
                    <td>${instructor.phone}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Нет инструкторов.</p>
    </c:otherwise>
</c:choose>

<p>
    <a href="/instructors/add">Добавить инструктора</a>
</p>

</body>
</html>
