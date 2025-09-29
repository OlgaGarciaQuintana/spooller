<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Detalle de Usuario</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow mx-auto" style="max-width: 500px;">
            <div class="card-body">
                <h2 class="card-title text-center text-primary mb-4">Detalle de Usuario</h2>
                <ul class="list-group list-group-flush mb-4">
                    <li class="list-group-item"><strong>ID:</strong> ${usuario.id}</li>
                    <li class="list-group-item"><strong>Username:</strong> ${usuario.username}</li>
                    <li class="list-group-item"><strong>Nombre:</strong> ${usuario.nombre}</li>
                    <li class="list-group-item"><strong>Primer Apellido:</strong> ${usuario.apellido1}</li>
                    <li class="list-group-item"><strong>Segundo Apellido:</strong> ${usuario.apellido2}</li>
                </ul>
                <div class="d-grid">
                    <a href="index.jsp" class="btn btn-secondary">Volver a Inicio</a>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS (opcional) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>