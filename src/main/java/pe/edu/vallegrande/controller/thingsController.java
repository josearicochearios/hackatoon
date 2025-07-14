package pe.edu.vallegrande.controller;

import pe.edu.vallegrande.database.thingsDAO;
import pe.edu.vallegrande.model.thingsEntity;

import javax.swing.table.DefaultTableModel;

public class thingsController {

    private thingsDAO dao = new thingsDAO();

    public void save(thingsEntity th) {
        try {
            dao.save(th);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void update(thingsEntity th) {
        try {
            dao.update(th);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        try {
            dao.delete(id);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void getAll(DefaultTableModel model) {
        try {
            dao.getAll(model);
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

}
