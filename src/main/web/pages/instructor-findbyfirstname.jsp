<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Поиск инструктора</title>
</head>
<body>
    <h1>Поиск инструктора по фамилии</h1>
    <form action="/instructors/findByFirstName" method="post" enctype="application/x-www-form-urlencoded">
        <p><b>Введите фамилию инструктора:</b><br>
            <input type="text" size="40" name="firstName">
        </p>
        <p>
            <input type="submit" value="Найти"/>
        </p>
    </form>
</body>
</html>
