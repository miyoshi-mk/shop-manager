package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Order;

public class OrderDAO {
	
	//全てのオーダー情報を取得する
	 public List<Order> selectAll() throws Exception {
	        List<Order> list = new ArrayList<>();
	        String sql = "SELECT * FROM s_order";
	        
	        try (Connection con = DbManager.getConnection("mysql");
	             PreparedStatement ps = con.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {
	            
	        	while (rs.next()) {
	                list.add(new Order(
	                    rs.getInt("order_id"),
	                    rs.getInt("product_id"),
	                    rs.getInt("quantity"),
	                    rs.getString("status"),
	                    rs.getTimestamp("order_date")
	                ));
	            }
	        }
	        return list;
	    }

	 public Order selectById(int id) throws Exception {
		    Connection con = DbManager.getConnection("mysql");
		    
		    String sql = "SELECT * FROM s_order WHERE product_id=?";
		    PreparedStatement ps = con.prepareStatement(sql);
		    ps.setInt(1, id);
		    ResultSet rs = ps.executeQuery();
		    Order o = null;
		    if(rs.next()) {
		        o = new Order();
		        o.setOrderId(rs.getInt("id"));
		        o.setProductId(rs.getInt("product_id"));
		        o.setQuantity(rs.getInt("quantity"));
		        o.setStatus(rs.getString("status"));
		    }
		    rs.close();
		    ps.close();
		    con.close();
		    return o;
		}
	 
	 //オーダー情報を追加する
	    public int insert(Order order) throws Exception {
	        String sql = "INSERT INTO s_order(product_id, quantity, status, order_date) VALUES (?, ?, ?, ?)";
	        try (Connection con = DbManager.getConnection("mysql");
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, order.getProductId());
	            ps.setInt(2, order.getQuantity());
	            ps.setString(3, order.getStatus());
	            ps.setTimestamp(4, order.getOrderDate());
	            return ps.executeUpdate();
	        }
	    }

	    //オーダー情報を更新する
	    public int updateStatus(int orderId, String status) throws Exception {
	        String sql = "UPDATE s_order SET status=? WHERE order_id=?";
	        try (Connection con = DbManager.getConnection("mysql");
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setString(1, status);
	            ps.setInt(2, orderId);
	            return ps.executeUpdate();
	        }
	    }

	    //オーダー情報を削除する
	    public int delete(int orderId) throws Exception {
	        String sql = "DELETE FROM s_order WHERE order_id=?";
	        try (Connection con = DbManager.getConnection("mysql");
	             PreparedStatement ps = con.prepareStatement(sql)) {
	            ps.setInt(1, orderId);
	            return ps.executeUpdate();
	        }
	    }

}
