<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Menú Principal</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container mt-5">
        <div class="card shadow mx-auto" style="max-width: 400px;">
            <div class="card-body">
                <h1 class="card-title text-center mb-4 text-primary">Menú de Usuarios</h1>
                <nav>
                    <ul class="list-group">
                        <li class="list-group-item text-center">
                            <a href="form-create.html" class="btn btn-success w-100">Crear Usuario</a>
                        </li>
                        <li class="list-group-item text-center">
                            <a href="form-update.html" class="btn btn-warning w-100">Actualizar Usuario</a>
                        </li>
                        <li class="list-group-item text-center">
                            <a href="usuario" class="btn btn-info w-100">Ver Todos los Usuarios</a>
                        </li>
                    </ul>
                </nav>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS (opcional, solo si usas componentes JS de Bootstrap) -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
