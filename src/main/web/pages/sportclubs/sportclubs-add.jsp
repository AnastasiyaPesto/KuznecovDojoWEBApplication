<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="formAddBean" scope="request" type="web.sportclubs.add.SportClubsAddFormBean"/>
<html>
<head>
    <title>Добавление спортивного клуба</title>
</head>
<body>
    <h1>Добавить новый спортивный клуб</h1>
    <form:form
            action="/sportclubs/add"
            method="post"
            enctype="application/x-www-form-urlencoded"
            modelAttribute="formAddBean">
        <p>
                <%--<label></label>--%>
            Метро: <form:input type="text" path="metro"/>
            <form:errors path="metro" cssClass="error"/>
        </p>
        <p>
            Адрес: <form:input type="text" path="address"/>
            <form:errors path="address" cssClass="error"/>
        </p>
        <p>
            Телефон: <form:input type="text" path="phone"/>
            <form:errors path="phone" cssClass="error"/>
        </p>
        <p>
            <input type="submit" value="Готово"/>
        </p>
    </form:form>
</body>
</html>
