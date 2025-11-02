package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Customer;

public class CustomerDAO {
	
	public List<Customer> selectAll() throws Exception {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT id, name, email, phone, address, registered_at FROM customer ORDER BY id ASC";
        
        try (Connection con = DbManager.getConnection("mysql");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            
            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("id"));
                c.setName(rs.getString("name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));
                
                Timestamp ts = rs.getTimestamp("registered_at");
                if (ts != null) c.setRegisteredAt(ts.toLocalDateTime());
                
                list.add(c);
            }
        }
        return list;
    }
}
