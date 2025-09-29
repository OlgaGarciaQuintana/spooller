package net.ausiasmarch.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioDao {

    private final Connection oConnection;

    public UsuarioDao(Connection oConnection) {
        this.oConnection = oConnection;
    }

    public UsuarioBean get(Long id) throws SQLException, ResourceNotFoundException {
        String strSQL = "SELECT * FROM usuario WHERE id = ?";
        UsuarioBean oUsuarioBean = null;
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
            oPreparedStatement.setLong(1, id);
            try (ResultSet oResultSet = oPreparedStatement.executeQuery()) {
                if (oResultSet.next()) {
                    oUsuarioBean = new UsuarioBean();
                    oUsuarioBean.setId(oResultSet.getLong("id"));
                    oUsuarioBean.setUsername(oResultSet.getString("username"));
                    oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                    oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
                    oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));                             
                } else{
                    throw new ResourceNotFoundException("User with id " + id + " not found.");
                }
            }
        }
        return oUsuarioBean;
    }

    public List<UsuarioBean> getAll() throws SQLException {
        String strSQL = "SELECT * FROM usuario";
        List<UsuarioBean> usuarios = new ArrayList<>();
        try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL);
             ResultSet oResultSet = oPreparedStatement.executeQuery()) {
            while (oResultSet.next()) {
                UsuarioBean oUsuarioBean = new UsuarioBean();
                oUsuarioBean.setId(oResultSet.getLong("id"));
                oUsuarioBean.setUsername(oResultSet.getString("username"));
                oUsuarioBean.setNombre(oResultSet.getString("nombre"));
                oUsuarioBean.setApellido1(oResultSet.getString("apellido1"));
                oUsuarioBean.setApellido2(oResultSet.getString("apellido2"));
                usuarios.add(oUsuarioBean);
            }
        }
        return usuarios;
    }

        public UsuarioBean create(UsuarioBean usuario) throws SQLException {
            String strSQL = "INSERT INTO usuario (username, nombre, apellido1, apellido2) VALUES (?, ?, ?, ?)";
            try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
                oPreparedStatement.setString(1, usuario.getUsername());
                oPreparedStatement.setString(2, usuario.getNombre());
                oPreparedStatement.setString(3, usuario.getApellido1());
                oPreparedStatement.setString(4, usuario.getApellido2());
                int affectedRows = oPreparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new SQLException("Creating user failed, no rows affected.");
                }
                try (ResultSet generatedKeys = oPreparedStatement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        usuario.setId(generatedKeys.getLong(1));
                    }
                }
            }
            return usuario;
        }

        public UsuarioBean update(UsuarioBean usuario) throws SQLException, ResourceNotFoundException {
            String strSQL = "UPDATE usuario SET username = ?, nombre = ?, apellido1 = ?, apellido2 = ? WHERE id = ?";
            try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
                oPreparedStatement.setString(1, usuario.getUsername());
                oPreparedStatement.setString(2, usuario.getNombre());
                oPreparedStatement.setString(3, usuario.getApellido1());
                oPreparedStatement.setString(4, usuario.getApellido2());
                oPreparedStatement.setLong(5, usuario.getId());
                int affectedRows = oPreparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new ResourceNotFoundException("User with id " + usuario.getId() + " not found.");
                }
            }
            return usuario;
        }

        public boolean delete(Long id) throws SQLException, ResourceNotFoundException {
            String strSQL = "DELETE FROM usuario WHERE id = ?";
            try (PreparedStatement oPreparedStatement = this.oConnection.prepareStatement(strSQL)) {
                oPreparedStatement.setLong(1, id);
                int affectedRows = oPreparedStatement.executeUpdate();
                if (affectedRows == 0) {
                    throw new ResourceNotFoundException("User with id " + id + " not found.");
                }
                return true;
            }
        }

}