package pe.edu.vallegrande.database;

import pe.edu.vallegrande.model.thingsEntity;

import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;

public class thingsDAO extends databaseConection{

    public void getAll(DefaultTableModel model) throws Exception {
        String query = "SELECT id, name, material, is_required, category, proveedor, stock, description, creation_date FROM things WHERE active = 1 ORDER BY last_update DESC";
        try {
            String datos[] = new String[9];
            Statement st = getConnection().createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                for (int i = 0; i < 9; i++) {
                    datos[i] = rs.getString(i+1);
                }
                model.addRow(datos);
            }
            rs.close();
            st.close();
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            closeConnection();
        }
    }

    public void save(thingsEntity things) throws Exception {
        String query = "INSERT INTO things (name, material, is_required, category, proveedor, stock, creation_date, description) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        Timestamp lastUpdate = things.getLast_update() != null
                ? new Timestamp(things.getLast_update().getTime())
                : new Timestamp(System.currentTimeMillis());
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, things.getName());
            ps.setString(2, things.getMaterial());
            ps.setString(3, things.getIs_required());
            ps.setString(4, things.getCategory());
            ps.setString(5, things.getProveedor());
            ps.setInt(6, things.getStock());
            ps.setTimestamp(7, new java.sql.Timestamp(things.getCreation_date().getTime()));
            ps.setString(8, things.getDescription());
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al guardar el objeto things", e);
        } finally {
            closeConnection();
        }
    }

    public void update(thingsEntity things) throws Exception {
        String query = "UPDATE things SET name = ?, material = ?, is_required = ?, category = ?, proveedor = ?, stock = ?, creation_date = ?, active = ?, description = ? WHERE id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, things.getName());
            ps.setString(2, things.getMaterial());
            ps.setString(3, things.getIs_required());
            ps.setString(4, things.getCategory());
            ps.setString(5, things.getProveedor());
            ps.setInt(6, things.getStock());
            ps.setTimestamp(7, new java.sql.Timestamp(things.getCreation_date().getTime()));
            ps.setBoolean(8, true);
            ps.setString(9, things.getDescription());
            ps.setInt(10, things.getId()); // ID al final para el WHERE
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al actualizar el objeto things", e);
        } finally {
            closeConnection();
        }
    }

    public void delete(int id) throws Exception {
        String query = "UPDATE things SET active = 0 WHERE id = ?";
        try {
            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("Error al eliminar (desactivar) el registro con ID: " + id, e);
        } finally {
            closeConnection();
        }
    }

}
