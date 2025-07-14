package pe.edu.vallegrande.database;

import pe.edu.vallegrande.model.userEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class userDAO extends databaseConection{

    private userEntity user = new userEntity();

    public userEntity verifyUser(String userName,String password) throws Exception {

        String query = "SELECT * FROM user_app WHERE username = ?";

        try {

            PreparedStatement ps = getConnection().prepareStatement(query);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String dbPassword = rs.getString("password");
                if (dbPassword.equals(password)) {
                    user.setId(rs.getInt("id"));
                    return user;
                }
            }

            return null;
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            this.closeConnection();
        }
        return null;
    }

}
