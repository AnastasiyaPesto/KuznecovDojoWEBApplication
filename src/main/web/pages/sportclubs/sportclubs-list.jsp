<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="sporClubsAllBean" type="web.sportclubs.SportClubsListBean" scope="request"/>
<html>
<head>
    <title>Спортивные клубы</title>
</head>
<body>
<h1>Все спортивные клубы, где проходят тренировки</h1>
<c:choose>
    <c:when test="${not empty sporClubsAllBean.sportClubs}">
        <table border="1">
            <tbody>
            <c:forEach items="${sporClubsAllBean.sportClubs}" var="sportclub">
                <tr>
                    <td>${sportclub.metro}</td>
                    <td>${sportclub.address}</td>
                    <td>${sportclub.phone}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:when>
    <c:otherwise>
        <p>Нет спортивных клубов.</p>
    </c:otherwise>
</c:choose>
<p>
    <a href="/sportclubs/add">Добавить спортивный клуб</a>
</p>
</body>
</html>
