package model;
import java.io.Serializable;

//商品情報クラス
public class Product implements Serializable{

	//フィールド
	private int productId;                  //商品ID
	private String productName;        //商品名
	private int stock;                        //商品在庫
	private double price;                   //価格

	//コンストラクタ
	public Product() {}

	public Product(int productId, String productName, int stock, double price) {
		this.productId = productId;
		this.productName = productName;
		this.stock = stock;
		this.price = price;
	}

	//getter/setter
	public int getProductId() {return productId;}
	public void setProductId(int productId) {this.productId = productId;}

	public String getProductName() {	return productName;}
	public void setProductName(String productName) {this.productName = productName;}

	public int getStock() {	return stock;}
	public void setStock(int stock) {this.stock = stock;}

	public double getPrice() {return price;}
	public void setPrice(double price) {this.price = price;}

}
