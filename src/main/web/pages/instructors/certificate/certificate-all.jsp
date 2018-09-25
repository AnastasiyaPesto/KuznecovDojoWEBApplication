<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="certificateListBean" scope="request" type="web.certificates.CertificateListBean"/>
<html>
<head>
    <title>Сертификаты</title>
</head>
<body>
<h1>Сетификаты тренера с фамилией ${certificateListBean.firstName}</h1>

<c:choose>
    <c:when test="${not empty certificateListBean.certificates}">
    <form method="post">
        <input type="submit" value="Удалить">
        <br/>
        <br/>
        <table cellpadding="4" border="1">
            <tr>
                <th></th>
                <th>Номер</th>
                <th>Полученная степень</th>
            </tr>
            <c:forEach items="${certificateListBean.certificates}" var="certificate">
                <tr>
                    <td align="center"><input type="checkbox" name="selectedCertificate" value="${certificate.certificateId}"></td>
                    <td>${certificate.number}</td>
                    <td>${certificate.degree}</td>
                </tr>
            </c:forEach>
        </table>
        </c:when>
        <c:otherwise>
            <p>Нет информации о сертификатах.</p>
        </c:otherwise>
</c:choose>

</body>
</html>
