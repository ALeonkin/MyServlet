<%@page import="ibs.appline.logic.Model" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1>Домашная страница по работе с пользователями</h1>
Введите ID пользователя(0 - для вывода всего списка пользователей)
<br/>
Доступно: <%
    Model model = Model.getInstance();
    int size = model.getFromList().size();
%>
<%= size %>
<form method="get" action="get">
    <label>ID:
        <input type="text" name="id"><br/>
    </label>
    <button type="submit">Поиск</button>
</form>
<br/>
<a href="putUser.html">Изменить текущего пользователя</a>
<br/>
<a href="addUser.html">Создать нового пользователя</a>
<br/>
<a href="deleteUser.html">Удаление пользователя</a>
</body>
</html>
