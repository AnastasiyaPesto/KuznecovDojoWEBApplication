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
        <form method="post" action="/sportclubs/all">
            <input type="submit" value="Удалить">
            <br/>
            <br/>
            <table cellpadding="4" border="1">
                <tr>
                    <th></th>
                    <th>Метро</th>
                    <th>Адрес</th>
                    <th>Телефон</th>
                </tr>
                <c:forEach items="${sporClubsAllBean.sportClubs}" var="sportclub">
                    <tr>
                        <td align="center"><input type="checkbox" name="selectedClubs" value="${sportclub.sportClubId}"></td>
                        <td>${sportclub.metro}</td>
                        <td>${sportclub.address}</td>
                        <td>${sportclub.phone}</td>
                    </tr>
                </c:forEach>
            </table>
    </c:when>
    <c:otherwise>
        <p>Нет спортивных клубов.</p>
    </c:otherwise>
</c:choose>
<p>
    <a href="/sportclubs/add">Добавить спортивный клуб</a>
</p>
<p>
    <a href="/instructors/all">Вернуться на ГЛАВНУЮ</a>
</p>

</body>
</html>
