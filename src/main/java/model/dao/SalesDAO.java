package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.Sales;

public class SalesDAO {

	//売上登録
	public int insertSale(Sales s) throws Exception {
        String sql = "INSERT INTO sales (product_id, customer_id, quantity, remarks) VALUES (?, ?, ?, ?)";

        try (Connection con = DbManager.getConnection("mysql")) {
            con.setAutoCommit(false); // トランザクション開始
            int result = 0;

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setInt(1, s.getProductId());
                ps.setInt(2, s.getCustomerId());
                ps.setInt(3, s.getQuantity());
                ps.setString(4, s.getRemarks());
                result = ps.executeUpdate();
            }

            // 在庫減少処理
            String updateSql = "UPDATE s_product SET stock = stock - ? WHERE product_id = ?";
            try (PreparedStatement ps2 = con.prepareStatement(updateSql)) {
                ps2.setInt(1, s.getQuantity());
                ps2.setInt(2, s.getProductId());
                ps2.executeUpdate();
            }

            con.commit();
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public List<Sales> selectAll() throws Exception {
        List<Sales> list = new ArrayList<>();
        String sql = "SELECT * FROM sales ORDER BY sale_id DESC";

        try (Connection con = DbManager.getConnection("mysql");
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Sales s = new Sales();
                s.setSaleId(rs.getInt("sale_id"));
                s.setProductId(rs.getInt("product_id"));
                s.setCustomerId(rs.getInt("customer_id"));
                s.setQuantity(rs.getInt("quantity"));
                Timestamp ts = rs.getTimestamp("sale_date");
                if (ts != null) s.setSaleDate(ts.toLocalDateTime());
                s.setRemarks(rs.getString("remarks"));
                list.add(s);
            }
        }
        return list;
    }
	
}
