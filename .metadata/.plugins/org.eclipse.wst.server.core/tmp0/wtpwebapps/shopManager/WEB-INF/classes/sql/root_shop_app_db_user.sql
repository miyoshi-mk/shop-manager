/* DB作成 */
CREATE DATABASE shop_app_db CHARACTER SET utf8 COLLATE utf8_general_ci;

/* DBユーザを作成 */
CREATE USER IF NOT EXISTS shop_app IDENTIFIED BY 'pass';

/* 権限付与 */
GRANT ALL PRIVILEGES ON shop_app_db.* TO shop_app;