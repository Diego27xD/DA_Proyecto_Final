package DAO;

import BEAN.Genero;
import UTIL.DbBean;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class GeneroDAO {

    public Vector<Genero> listaGeneros(String cad) {

        Vector<Genero> lista = new Vector<Genero>();
        DbBean db = new DbBean();

        if (!db.conecta()) {
            return lista;
        }

        String sql = "SELECT IdGenero, Descrip "
                   + "FROM dbo.Genero "
                   + "WHERE LOWER(Descrip) LIKE ? "
                   + "ORDER BY IdGenero";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            String buscar = cad == null ? "" : cad.trim();

            ps.setString(1, "%" + buscar.toLowerCase() + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Genero genero = new Genero();

                    genero.setIdGenero(rs.getInt("IdGenero"));
                    genero.setDescrip(rs.getString("Descrip"));

                    lista.add(genero);
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

    public boolean grabarGenero(String descripcion) {

        if (descripcion == null ||
                descripcion.trim().isEmpty()) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "INSERT INTO dbo.Genero (Descrip) "
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

    public boolean modificarGenero(int idGenero,
                                   String descripcion) {

        if (idGenero <= 0 || descripcion == null ||
                descripcion.trim().isEmpty()) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "UPDATE dbo.Genero "
                   + "SET Descrip = ? "
                   + "WHERE IdGenero = ?";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            ps.setString(1, descripcion.trim());
            ps.setInt(2, idGenero);

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

    public boolean eliminarGenero(int idGenero) {

        if (idGenero <= 0) {
            return false;
        }

        DbBean db = new DbBean();

        if (!db.conecta()) {
            return false;
        }

        String sql = "DELETE FROM dbo.Genero "
                   + "WHERE IdGenero = ?";

        try (PreparedStatement ps =
                db.getConexion().prepareStatement(sql)) {

            ps.setInt(1, idGenero);

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
