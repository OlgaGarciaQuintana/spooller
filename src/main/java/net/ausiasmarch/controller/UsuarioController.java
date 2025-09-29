package net.ausiasmarch.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;
import net.ausiasmarch.service.UsuarioService;

@WebServlet("/usuario")
public class UsuarioController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        String action = request.getParameter("action");
        if ("getAll".equals(action)) {
            UsuarioService usuarioService = new UsuarioService();
            try {
                java.util.List<UsuarioBean> usuarios = usuarioService.getAll();
                request.setAttribute("usuarios", usuarios);
                request.getRequestDispatcher("getAll.jsp").forward(request, response);
            } catch (Exception e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error al cargar usuarios");
            }
        } else {
            UsuarioService oUsuarioService = new UsuarioService();
            String idParam = request.getParameter("id");
            try {
                if (idParam != null) {
                    Long id = Long.valueOf(idParam);
                    UsuarioBean oUsuarioBean = oUsuarioService.get(id);
                    request.setAttribute("usuario", oUsuarioBean);
                    request.getRequestDispatcher("usuario.jsp").forward(request, response);
                } else {
                    java.util.List<UsuarioBean> usuarios = oUsuarioService.getAll();
                    request.setAttribute("usuarios", usuarios);
                    request.getRequestDispatcher("getAll.jsp").forward(request, response);
                }
            } catch (java.sql.SQLException | ResourceNotFoundException e) {
                request.setAttribute("errorMessage", "Error al obtener el usuario: " + e.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request, response);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        UsuarioService oUsuarioService = new UsuarioService();
        UsuarioBean usuario = new UsuarioBean();
        usuario.setUsername(request.getParameter("username"));
        usuario.setNombre(request.getParameter("nombre"));
        usuario.setApellido1(request.getParameter("apellido1"));
        usuario.setApellido2(request.getParameter("apellido2"));
        try {
            oUsuarioService.create(usuario);
            response.sendRedirect("usuario?id=" + usuario.getId());
        } catch (java.sql.SQLException e) {
            request.setAttribute("errorMessage", "Error al crear el usuario: " + e.getMessage());
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        UsuarioService oUsuarioService = new UsuarioService();
        // Leer el cuerpo de la petición y parsear los parámetros manualmente
        String body = "";
        try (java.io.BufferedReader reader = new java.io.BufferedReader(new java.io.InputStreamReader(request.getInputStream()))) {
            body = reader.lines().reduce("", (acc, line) -> acc + line);
        }
        java.util.Map<String, String> params = new java.util.HashMap<>();
        for (String pair : body.split("&")) {
            String[] kv = pair.split("=", 2);
            if (kv.length == 2) {
                params.put(java.net.URLDecoder.decode(kv[0], "UTF-8"), java.net.URLDecoder.decode(kv[1], "UTF-8"));
            }
        }
        UsuarioBean usuario = new UsuarioBean();
        try {
            usuario.setId(Long.valueOf(params.get("id")));
            usuario.setUsername(params.get("username"));
            usuario.setNombre(params.get("nombre"));
            usuario.setApellido1(params.get("apellido1"));
            usuario.setApellido2(params.get("apellido2"));
            oUsuarioService.update(usuario);
            response.getWriter().write("Usuario actualizado correctamente");
        } catch (Exception e) {
            response.getWriter().write("Error al actualizar el usuario: " + e.getMessage());
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws javax.servlet.ServletException, java.io.IOException {
        UsuarioService oUsuarioService = new UsuarioService();
        String idParam = request.getParameter("id");
        try {
            if (idParam != null) {
                Long id = Long.valueOf(idParam);
                oUsuarioService.delete(id);
                response.getWriter().write("Usuario eliminado correctamente");
            } else {
                response.getWriter().write("ID de usuario no proporcionado");
            }
        } catch (java.sql.SQLException | ResourceNotFoundException e) {
            response.getWriter().write("Error al eliminar el usuario: " + e.getMessage());
        }
    }

}
