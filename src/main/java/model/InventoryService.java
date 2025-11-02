package model;

import java.util.List;

import model.dao.ProductDAO;

//在庫アラート機能
public class InventoryService {
	
	private ProductDAO productDAO = new ProductDAO();

    // 在庫がthreshold以下の商品を取得
    public List<Product> getLowStockProducts(int threshold) throws Exception {
        List<Product> all = productDAO.selectAll();
        return all.stream().filter(p -> p.getStock() <= threshold).toList();
    }
}

