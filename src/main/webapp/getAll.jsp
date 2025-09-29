<html>
<head>
	<title>Lista de Usuarios</title>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
	<script>
	function deleteUser(id) {
		if (confirm('¿Seguro que quieres borrar el usuario?')) {
			var xhr = new XMLHttpRequest();
			xhr.open('DELETE', 'usuario?id=' + id, true);
			xhr.onload = function () {
				alert(xhr.responseText);
				location.reload();
			};
			xhr.send();
		}
	}
	</script>
</head>
<body class="bg-light">
	<div class="container mt-5">
		<h2 class="mb-4">Lista de Usuarios</h2>
		<table class="table table-bordered table-striped">
			<thead class="table-dark">
				<tr>
					<th>ID</th>
					<th>Username</th>
					<th>Nombre</th>
					<th>Apellido 1</th>
					<th>Apellido 2</th>
					<th>Acciones</th>
				</tr>
			</thead>
			<tbody>
			<%-- Iterar usuarios desde request --%>
			<% 
				java.util.List usuarios = (java.util.List)request.getAttribute("usuarios");
				if (usuarios != null) {
					for (Object obj : usuarios) {
						net.ausiasmarch.model.UsuarioBean usuario = (net.ausiasmarch.model.UsuarioBean)obj;
			%>
				<tr>
					<td><%= usuario.getId() %></td>
					<td><%= usuario.getUsername() %></td>
					<td><%= usuario.getNombre() %></td>
					<td><%= usuario.getApellido1() %></td>
					<td><%= usuario.getApellido2() %></td>
					<td>
						<button onclick="deleteUser('<%= usuario.getId() %>')" class="btn btn-danger btn-sm">DELETE</button>
					</td>
				</tr>
			<% 
					}
				}
			%>
			</tbody>
		</table>
		<a href="index.jsp" class="btn btn-secondary">Volver al inicio</a>
	</div>
</body>
</html>