
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <h1>Usuario</h1>
    <p>ID: ${usuario.id}</p>
    <p>Username: ${usuario.username}</p>
    <p>Nombre: ${usuario.nombre}</p>
    <p>Primer Apellido: ${usuario.apellido1}</p>
    <p>Segundo Apellido: ${usuario.apellido2}</p>
</body>
</html>