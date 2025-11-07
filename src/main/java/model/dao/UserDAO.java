package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.User;

public class UserDAO {

	public User login(String userName, String password) throws Exception {
        String sql =  "SELECT user_id, user_name, password FROM s_user WHERE user_name = ? AND password = ?";

        try (Connection con = DbManager.getConnection("h2");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, userName);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new User(
                    		rs.getInt("user_id"),
                            rs.getString("user_name"),
                            rs.getString("password")
                    );
                }
            }
        }
        return null;
    }
}
