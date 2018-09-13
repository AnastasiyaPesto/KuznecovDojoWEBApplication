<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Добавление инструктора</title>
</head>
<body>
    <h1>Добавить нового инструктора</h1>
    <form action="/instructors/add" method="post" enctype="application/x-www-form-urlencoded">
        <p>
            <%--<label></label>--%>
            Фамилия: <input type="text" name="firstName">
        </p>
        <p>
            Имя: <input type="text" name="secondName">
        </p>
        <p>
            Возраст: <input type="number" min="16" max="80" name="age">
        </p>
        <p>
            Телефон: <input type="text" name="phone">
        </p>
        <p>
            <input type="submit"/>
        </p>
    </form>
</body>
</html>
