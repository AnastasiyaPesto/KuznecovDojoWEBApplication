<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<jsp:useBean id="formAddBean" scope="request" type="web.instructors.InstructorAddFormBean"/>
<html>
<head>
    <title>Добавление инструктора</title>
    <style type="text/css">
        .error {
            color: red;
        }
    </style>
</head>
<body>
    <h1>Добавить нового инструктора</h1>
    <form:form
            action="/instructors/add"
            method="post"
            enctype="application/x-www-form-urlencoded"
            modelAttribute="formAddBean">
        <p>
            <%--<label></label>--%>
            Фамилия: <form:input type="text" path="firstName"/>
            <form:errors path="firstName" cssClass="error"/>
        </p>
        <p>
            Имя: <form:input type="text" path="secondName"/>
            <form:errors path="secondName" cssClass="error"/>
        </p>
        <p>
            Возраст: <form:input type="number" path="age"/>
            <form:errors path="age" cssClass="error"/>
        </p>
        <p>
            Телефон: <form:input type="text" path="phone"/>
        </p>
        <p>
            <input type="submit"/>
        </p>
    </form:form>
</body>
</html>
