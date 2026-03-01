<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
   
</head>
<body>
    <div class="container">
        <h2>Connexion</h2>
        <% if (request.getParameter("success") != null) { %>
            <p class="success"><%= request.getParameter("success") %></p>
        <% } %>
        <% if (request.getParameter("error") != null) { %>
            <p class="error"><%= request.getParameter("error") %></p>
        <% } %>
        <form action="Login" method="post">
            <label>Email :</label>
            <input type="email" name="email" required><br>

            <label>Mot de passe :</label>
            <input type="password" name="password" required><br>

            <input type="submit" value="Se connecter">
        </form>
        <p>Pas encore de compte ? <a href="Inscriptionfrm.jsp">Inscrivez-vous</a></p>
    </div>
</body>
</html>