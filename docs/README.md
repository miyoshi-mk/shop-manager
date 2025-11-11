# ShopManager

概要
本アプリは小規模店舗の在庫・発注・売上・顧客情報を一元管理できるWebアプリケーションです。
日々の販売データを視覚的に分析し、グラフ表示によって業務効率を高めることを目的としています。

なお、本アプリは職業訓練の卒業制作として開発されました。

## 主な機能構成

●ログイン機能
　セッション管理による簡易的なログイン機能
●商品管理
　商品の登録・編集・削除・一覧表示・在庫アラート機能
●発注管理
　発注情報の登録・在庫の自動更新
●顧客管理
　顧客情報の登録・郵便番号検索による住所自動入力
●ダッシュボード（簡易分析）

## 使用技術
【フロントエンド】
HTML/CSS/JavaScript/Chart.js
【バックエンド】
Java Servlet/JSP
【データベース】
MySQL/H2 database
【開発環境】
Eclipse/Apache Tomcat
【バージョン管理】
GitHub

##データベース構成
テーブル名　　　　主なカラム
s_user          user_id,user_name,role,password
s_product       product_id,product_name,stock,price
s_order         order_id,product_id,user_id,quantitiy,status,order_date
customer        customer_id,customer_name,email,phone,address,registerd_at
sales           sale_id,product_it,customer_id,quantity,sale_date,remarks

##実行手順


##今後の改良予定
・ログインユーザーごとの権限管理
・CSVエクスポート機能
・データ分析ページの拡充