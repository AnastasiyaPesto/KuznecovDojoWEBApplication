<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="instructorListBean" type="web.instructors.InstructorListBean" scope="request" />
<html>
<head>
    <title>Школа карате - Kuznecov Dojo</title>
</head>
<body>
<h1>Привет, будущий чемпион!</h1>
<c:choose>
    <c:when test="${not empty instructorListBean.instructors}">
        <form method="post" action="/instructors/all">
            <input type="submit" value="Удалить">
            <br/>
            <br/>
            <table cellpadding="4" border="1">
                <tr>
                    <th></th>
                    <th>Фамилия</th>
                    <th>Имя</th>
                    <th>Возраст</th>
                    <th>Телефон</th>
                    <th>Сертификат</th>
                </tr>
                <c:forEach items="${instructorListBean.instructors}" var="instructor">
                    <tr>
                        <td align="center"><input type="checkbox" name="selectedInstr" value="${instructor.instructorId}"></td>
                        <td>${instructor.firstName}</td>
                        <td>${instructor.secondName}</td>
                        <td>${instructor.age}</td>
                        <td>${instructor.phone}</td>
                        <td><a href="/instructors/certificate?id=${instructor.instructorId}"/>Перейти</td>
                    </tr>
                </c:forEach>
            </table>
        </form>
    </c:when>
    <c:otherwise>
        <p>Нет инструкторов.</p>
    </c:otherwise>
</c:choose>

<p>
    <a href="/instructors/add">Добавить инструктора</a>
</p>
<p>
    <a href="/instructors/find/firstName">Найти иструктора</a>
</p>
<p>
    <a href="/sportclubs/all">Посмотреть все спортивные клубы, где проходят тренировки</a>
</p>

</body>
</html>
