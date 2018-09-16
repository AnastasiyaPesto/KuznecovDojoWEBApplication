<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="instructorListBean" type="web.InstructorListBean" scope="request" />
<html>
<head>
    <title>Результат поиска</title>
</head>
<body>
<h2>Найденные инструктора по запросу</h2>
    <c:choose>
        <c:when test="${not empty instructorListBean.instructors}">
            <table>
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
