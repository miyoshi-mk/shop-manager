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
        String sql = "SELECT customer_id, customer_name, email, phone, address, registered_at FROM customer ORDER BY customer_id ASC";

        try (Connection con = DbManager.getConnection("h2");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Customer c = new Customer();
                c.setId(rs.getInt("customer_id"));
                c.setName(rs.getString("customer_name"));
                c.setEmail(rs.getString("email"));
                c.setPhone(rs.getString("phone"));
                c.setAddress(rs.getString("address"));

                Timestamp ts = rs.getTimestamp("registered_at");
                c.setRegisteredAt(ts);

                list.add(c);
            }
        }
        return list;
    }

	public void insert(Customer c) throws Exception {
        String sql = "INSERT INTO customer (customer_name, email, phone, address) VALUES (?, ?, ?, ?)";

        try (Connection con = DbManager.getConnection("h2");
        		PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getName());
            ps.setString(2, c.getEmail());
            ps.setString(3, c.getPhone());
            ps.setString(4, c.getAddress());
            ps.executeUpdate();
        }
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM customer WHERE customer_id = ?";
        try ( Connection con = DbManager.getConnection("h2");
        		PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }
}
