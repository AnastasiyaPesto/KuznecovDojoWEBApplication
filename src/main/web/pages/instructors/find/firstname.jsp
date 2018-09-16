<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<jsp:useBean id="instructorListBean" type="web.InstructorListBean" scope="request" />
<html>
<head>
    <title>Поиск инструктора</title>
</head>
<body>
    <h1>Поиск инструктора по фамилии</h1>
    <form action="/instructors/find/firstName" method="post" enctype="application/x-www-form-urlencoded">
        <p><b>Введите фамилию инструктора:</b><br>
            <input type="text" size="40" name="firstName">
        </p>
        <p>
            <input type="submit" value="Найти"/>
        </p>
    </form>
    <c:choose>
        <c:when test="${not empty instructorListBean.instructors}">
        <table>
            <h2>Найденные инструктора по запросу</h2>
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
            <p>Не найдено инструкторов</p>
        </c:otherwise>
    </c:choose>
    <p>
        <a href="/instructors/all">Вернуться к списку всех инструкторов</a>
    </p>
</body>
</html>
