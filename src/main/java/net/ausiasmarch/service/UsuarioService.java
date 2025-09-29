package net.ausiasmarch.service;

import java.sql.Connection;
import java.sql.SQLException;

import net.ausiasmarch.connection.HikariPool;
import net.ausiasmarch.dao.UsuarioDao;
import net.ausiasmarch.exception.ResourceNotFoundException;
import net.ausiasmarch.model.UsuarioBean;

public class UsuarioService {

    public UsuarioBean get(Long id) throws SQLException, ResourceNotFoundException {
        // Es mejor crear la conexion a nivel de servicio y pasarla al DAO
        try (Connection oConnection = HikariPool.getPool().getConnection()) {
            UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
            UsuarioBean oUsuarioBean = oUsuarioDao.get(id);
            return oUsuarioBean;
        }
    }
        public java.util.List<UsuarioBean> getAll() throws SQLException {
            try (Connection oConnection = HikariPool.getPool().getConnection()) {
                UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
                return oUsuarioDao.getAll();
            }
        }

        public UsuarioBean create(UsuarioBean usuario) throws SQLException {
            try (Connection oConnection = HikariPool.getPool().getConnection()) {
                UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
                return oUsuarioDao.create(usuario);
            }
        }

        public UsuarioBean update(UsuarioBean usuario) throws SQLException, ResourceNotFoundException {
            try (Connection oConnection = HikariPool.getPool().getConnection()) {
                UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
                return oUsuarioDao.update(usuario);
            }
        }

        public boolean delete(Long id) throws SQLException, ResourceNotFoundException {
            try (Connection oConnection = HikariPool.getPool().getConnection()) {
                UsuarioDao oUsuarioDao = new UsuarioDao(oConnection);
                return oUsuarioDao.delete(id);
            }
        }

}
