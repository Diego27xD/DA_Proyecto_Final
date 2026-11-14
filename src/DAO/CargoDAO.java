package DAO;

import BEAN.Cargo;
import UTIL.DbBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class CargoDAO {

    public Vector<Cargo> listaCargos(String cad) {

        Vector<Cargo> lista = new Vector<Cargo>();
        DbBean db = new DbBean();

        if (!db.conecta()) {
            return lista;
        }

        String sql = "SELECT IdCargo, DescArea "
                   + "FROM dbo.Cargo "
                   + "WHERE LOWER(DescArea) LIKE ? "
                   + "ORDER BY IdCargo";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            String buscar = cad == null ? "" : cad.trim();
            ps.setString(1, "%" + buscar.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Cargo cargo = new Cargo();

                    cargo.setIdCargo(rs.getInt("IdCargo"));
                    cargo.setDescArea(rs.getString("DescArea"));

                    lista.add(cargo);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lista;
    }

    public boolean grabarCargo(String descripcion) {

        if (descripcion == null ||
                descripcion.trim().isEmpty()) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "INSERT INTO dbo.Cargo (DescArea) "
                   + "VALUES (?)";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            ps.setString(1, descripcion.trim());

            int resultado = ps.executeUpdate();

            return resultado > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean modificarCargo(int idCargo,
                                  String descripcion) {

        if (idCargo <= 0 || descripcion == null ||
                descripcion.trim().isEmpty()) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "UPDATE dbo.Cargo "
                   + "SET DescArea = ? "
                   + "WHERE IdCargo = ?";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            ps.setString(1, descripcion.trim());
            ps.setInt(2, idCargo);

            int resultado = ps.executeUpdate();

            return resultado > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean eliminarCargo(int idCargo) {

        if (idCargo <= 0) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "DELETE FROM dbo.Cargo "
                   + "WHERE IdCargo = ?";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            ps.setInt(1, idCargo);

            int resultado = ps.executeUpdate();

            return resultado > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            try {
                db.desconecta();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
