package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Product;

//商品在庫更新用DAO
public class ProductDAO {

	//商品テーブルから全てのデータを取得する
	public List<Product> selectAll() throws Exception{
		List<Product> list = new ArrayList<>();
		//SQL文を設定する
		String sql = "SELECT * FROM s_product";

		try (Connection con = DbManager.getConnection("h2");
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			while (rs.next()) {
				list.add(new Product(
						rs.getInt("product_id"),
						rs.getString("product_name"),
						rs.getInt("stock"),
						rs.getDouble("price")
						));
			}
		}
		return list;
	}

	// 商品IDで単一商品を取得する
    public Product selectById(int productId) throws Exception {
        String sql = "SELECT * FROM s_product WHERE product_id = ?";
        try (Connection con = DbManager.getConnection("mysql");
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, productId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Product(
                            rs.getInt("product_id"),
                            rs.getString("product_name"),
                            rs.getInt("stock"),
                            rs.getDouble("price")
                    );
                } else {
                    return null;
                }
            }
        }
    }

	//商品情報を登録する
	public int insert(Product product) throws Exception {
		String sql = "INSERT INTO s_product(product_name, stock, price) VALUES (?, ?, ?)";

		try (Connection con = DbManager.getConnection("mysql");
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getStock());
			ps.setDouble(3, product.getPrice());
			return ps.executeUpdate();
		}
	}

	//商品情報を更新する
	public int update(Product product) throws Exception {
		String sql = "UPDATE s_product SET product_name=?, stock=?, price=? WHERE product_id=?";

		try (Connection con = DbManager.getConnection("mysql");
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setString(1, product.getProductName());
			ps.setInt(2, product.getStock());
			ps.setDouble(3, product.getPrice());
			ps.setInt(4, product.getProductId());
			return ps.executeUpdate();
		}
	}

	//商品情報を削除する
	public int delete(int productId) throws Exception {
		String sql = "DELETE FROM s_product WHERE product_id=?";

		try (Connection con = DbManager.getConnection("mysql");
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, productId);
			return ps.executeUpdate();
		}
	}

	//商品在庫を変動させるメソッド
	public int updateStock(int productId,int delta) throws Exception {
		String sql = "UPDATE s_product SET stock = stock + ? WHERE product_id=?";

		try (Connection con = DbManager.getConnection("mysql");
				PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, delta);
			ps.setInt(2, productId);
			return ps.executeUpdate();
		}
	}

}
