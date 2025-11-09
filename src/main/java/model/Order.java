package model;

import java.io.Serializable;
import java.sql.Timestamp;

//発注情報クラス
public class Order implements Serializable {
		
	//フィールド
	private int orderId;
	private int productId;
	private int quantity;
	private String status;
	private Timestamp orderDate;

	// コンストラクタ
	public Order() {}
	
	public Order(int orderId, int productId, int quantity, String status, Timestamp orderDate) {
		this.orderId = orderId;
	    this.productId = productId;
	    this.quantity = quantity;
	    this.status = status;
	    this.orderDate = orderDate;
	}

	// getter/setter
	public int getOrderId() { return orderId; }
	public void setOrderId(int orderId) { this.orderId = orderId; }

	public int getProductId() { return productId; }
	public void setProductId(int productId) { this.productId = productId; }

	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getOrderDate() { return orderDate; }
    public void setOrderDate(Timestamp orderDate) { this.orderDate = orderDate; }
}	


