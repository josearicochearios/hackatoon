package pe.edu.vallegrande.controller;

import pe.edu.vallegrande.database.userDAO;
import pe.edu.vallegrande.model.userEntity;

public class userController {

    private userDAO userdb = new userDAO();

    public userEntity verifyUser(String user, String passsword) throws Exception {
        try {
            return userdb.verifyUser(user, passsword);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
