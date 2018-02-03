-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.5.0.5196
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Дамп структуры базы данных shop
DROP DATABASE IF EXISTS `shop`;
CREATE DATABASE IF NOT EXISTS `shop` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `shop`;

-- Дамп структуры для таблица shop.t_comments
DROP TABLE IF EXISTS `t_comments`;
CREATE TABLE IF NOT EXISTS `t_comments` (
  `F_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_TEXT` varchar(250) NOT NULL DEFAULT '0',
  `F_USER_ID` bigint(20) NOT NULL DEFAULT '0',
  `F_NEWS_ID` bigint(20) NOT NULL DEFAULT '0',
  `F_TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`F_ID`),
  KEY `FK_COMMENT_USER` (`F_USER_ID`),
  KEY `FK_COMMENT_NEWS` (`F_NEWS_ID`),
  CONSTRAINT `FK_COMMENT_NEWS` FOREIGN KEY (`F_NEWS_ID`) REFERENCES `t_news` (`F_ID`),
  CONSTRAINT `FK_COMMENT_USER` FOREIGN KEY (`F_USER_ID`) REFERENCES `t_users` (`F_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_comments: ~4 rows (приблизительно)
/*!40000 ALTER TABLE `t_comments` DISABLE KEYS */;
INSERT INTO `t_comments` (`F_ID`, `F_TEXT`, `F_USER_ID`, `F_NEWS_ID`, `F_TIMESTAMP`) VALUES
	(22, 'hfhthtfhtfh', 1, 10, '2018-01-30 13:27:00'),
	(23, 'Test comment', 1, 10, '2018-01-30 16:14:17'),
	(24, 'drggrgdrgrgrgd', 1, 10, '2018-02-02 15:01:50'),
	(25, 'rgdrgdgrgdrg', 1, 10, '2018-02-02 15:01:55');
/*!40000 ALTER TABLE `t_comments` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_news
DROP TABLE IF EXISTS `t_news`;
CREATE TABLE IF NOT EXISTS `t_news` (
  `F_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_USER_ID` bigint(20) NOT NULL DEFAULT '0',
  `F_AUTHOR` varchar(50) DEFAULT NULL,
  `F_NAME` varchar(50) DEFAULT NULL,
  `F_DESCRIPTION` longtext,
  `F_TIMESTAMP` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`F_ID`),
  UNIQUE KEY `name` (`F_NAME`),
  KEY `FK_NEWS_USER` (`F_USER_ID`),
  CONSTRAINT `FK_NEWS_USER` FOREIGN KEY (`F_USER_ID`) REFERENCES `t_users` (`F_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_news: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `t_news` DISABLE KEYS */;
INSERT INTO `t_news` (`F_ID`, `F_USER_ID`, `F_AUTHOR`, `F_NAME`, `F_DESCRIPTION`, `F_TIMESTAMP`) VALUES
	(10, 2, 'authorUpd', 'newsUpd', 'newsUpd', '2018-01-24 13:26:12'),
	(16, 2, 'author', 'header', 'text', '2018-01-29 14:12:08'),
	(18, 2, 'w3rw3r3wrwr', 'rw3r3r', 'ftyhththfthh', '2018-01-30 16:49:03'),
	(19, 3, 'drgdrgdgrggrg', 'grgrdgdgrgdgdr', 'drgdrgdggdrgdrg', '2018-02-02 22:33:17'),
	(20, 3, 'e4te4tet4te4t', 'ye4te4ttet4te', 'e4tet4tt44te4t', '2018-02-02 22:33:37'),
	(21, 3, 'r5yr5yr5yr5y5yr5y', 'ry5yyr5yry55y', 'r5yr5y5yr5yr5y', '2018-02-02 22:33:52'),
	(22, 3, 'e4t4ttet4t4t', 'e4te4t4ett4tet44ete4t', 'et4ett4te4tett4t', '2018-02-02 22:34:09'),
	(23, 3, 'ngyjugjkugujguj', '4et4ttet4t', 'et4ttete4t4t', '2018-02-02 22:34:26');
/*!40000 ALTER TABLE `t_news` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_news_file
DROP TABLE IF EXISTS `t_news_file`;
CREATE TABLE IF NOT EXISTS `t_news_file` (
  `F_NEWS_ID` bigint(20) NOT NULL,
  `F_NAME` varchar(50) DEFAULT NULL,
  `F_LOCATION` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`F_NEWS_ID`),
  CONSTRAINT `FK__t_news` FOREIGN KEY (`F_NEWS_ID`) REFERENCES `t_news` (`F_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_news_file: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `t_news_file` DISABLE KEYS */;
INSERT INTO `t_news_file` (`F_NEWS_ID`, `F_NAME`, `F_LOCATION`) VALUES
	(10, '10.jpg', 'd:\\\\opt\\\\upload\\\\'),
	(16, '16.jpg', 'd:\\opt\\upload\\'),
	(18, '18.jpg', 'd:\\opt\\upload\\'),
	(19, '19.jpg', 'd:\\opt\\upload\\'),
	(20, '20.jpg', 'd:\\opt\\upload\\'),
	(21, '21.jpg', 'd:\\opt\\upload\\'),
	(22, '22.jpg', 'd:\\opt\\upload\\'),
	(23, '23.jpg', 'd:\\opt\\upload\\');
/*!40000 ALTER TABLE `t_news_file` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_orders
DROP TABLE IF EXISTS `t_orders`;
CREATE TABLE IF NOT EXISTS `t_orders` (
  `F_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_USER_ID` bigint(20) DEFAULT '0',
  `F_TIMESTAMP` timestamp NULL DEFAULT NULL,
  `F_STATUS` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`F_ID`),
  KEY `FK_order_user` (`F_USER_ID`),
  CONSTRAINT `FK_order_user` FOREIGN KEY (`F_USER_ID`) REFERENCES `t_users` (`F_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_orders: ~2 rows (приблизительно)
/*!40000 ALTER TABLE `t_orders` DISABLE KEYS */;
INSERT INTO `t_orders` (`F_ID`, `F_USER_ID`, `F_TIMESTAMP`, `F_STATUS`) VALUES
	(1, 1, '2018-01-28 17:52:21', 'IN_PROGRESS'),
	(8, 1, '2018-01-29 23:52:56', 'NEW');
/*!40000 ALTER TABLE `t_orders` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_orders_products
DROP TABLE IF EXISTS `t_orders_products`;
CREATE TABLE IF NOT EXISTS `t_orders_products` (
  `F_ORDER_ID` bigint(20) NOT NULL DEFAULT '0',
  `F_PRODUCT_ID` bigint(20) NOT NULL DEFAULT '0',
  `F_COUNT` int(11) DEFAULT NULL,
  `F_AMOUNT` decimal(10,2) DEFAULT '0.00',
  PRIMARY KEY (`F_ORDER_ID`,`F_PRODUCT_ID`),
  KEY `FK_orders` (`F_ORDER_ID`),
  KEY `FK_products` (`F_PRODUCT_ID`),
  CONSTRAINT `FK_orders` FOREIGN KEY (`F_ORDER_ID`) REFERENCES `t_orders` (`F_ID`),
  CONSTRAINT `FK_products` FOREIGN KEY (`F_PRODUCT_ID`) REFERENCES `t_products` (`F_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_orders_products: ~5 rows (приблизительно)
/*!40000 ALTER TABLE `t_orders_products` DISABLE KEYS */;
INSERT INTO `t_orders_products` (`F_ORDER_ID`, `F_PRODUCT_ID`, `F_COUNT`, `F_AMOUNT`) VALUES
	(1, 1, 2, 2461.80),
	(1, 14, 2, 2461.80),
	(1, 15, 3, 3692.70),
	(8, 14, 2, 2461.80),
	(8, 16, 1, 1230.90);
/*!40000 ALTER TABLE `t_orders_products` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_products
DROP TABLE IF EXISTS `t_products`;
CREATE TABLE IF NOT EXISTS `t_products` (
  `F_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_NAME` varchar(50) DEFAULT NULL,
  `F_DESCRIPTION` varchar(250) DEFAULT NULL,
  `F_CATEGORY` varchar(50) DEFAULT NULL,
  `F_COUNT` int(11) NOT NULL DEFAULT '0',
  `F_PRICE` decimal(10,2) NOT NULL DEFAULT '0.00',
  `F_STATUS` varchar(50) DEFAULT 'NOT_DELETED',
  PRIMARY KEY (`F_ID`),
  UNIQUE KEY `F_NAME_F_CATEGORY` (`F_NAME`,`F_CATEGORY`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_products: ~14 rows (приблизительно)
/*!40000 ALTER TABLE `t_products` DISABLE KEYS */;
INSERT INTO `t_products` (`F_ID`, `F_NAME`, `F_DESCRIPTION`, `F_CATEGORY`, `F_COUNT`, `F_PRICE`, `F_STATUS`) VALUES
	(1, 'Lenovo V5101', 'top notebook!!!', 'APPLIANCES', 3, 1230.90, 'DELETED'),
	(14, 'Lenovo V5102', 'top notebook!!!', 'BUILDING', 2, 1230.90, 'NOT_DELETED'),
	(15, 'Lenovo V5103', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'DELETED'),
	(16, 'Lenovo V5104', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(17, 'Lenovo V5105', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(18, 'Lenovo V5106', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(19, 'Lenovo V5107', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(22, 'thih', 'fhthth', 'FOOD', 3, 2.00, 'NOT_DELETED'),
	(26, 'test add', 'test add descr', 'ELECTRONICS', 2, 1.60, 'NOT_DELETED'),
	(28, 'Lenovo V5106', 'top notebook!!!', 'ELECTRONICS', 2, 1230.90, 'NOT_DELETED'),
	(29, 'ibiubi', 'hiubiubiub', 'BUILDING', 10, 12233.00, 'NOT_DELETED'),
	(31, 'Lenovo V5101', 'top notebook!!!', 'BUILDING', 2, 1230.90, 'DELETED'),
	(32, 'aedfesfesf', 'sefsefsefsfef', 'FOOD', 2, 2.00, 'NOT_DELETED'),
	(33, 'product', 'product', 'FOOD', 34, 128.40, 'NOT_DELETED');
/*!40000 ALTER TABLE `t_products` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_users
DROP TABLE IF EXISTS `t_users`;
CREATE TABLE IF NOT EXISTS `t_users` (
  `F_ID` bigint(20) NOT NULL AUTO_INCREMENT,
  `F_LOGIN` varchar(50) DEFAULT NULL,
  `F_PASSWORD` varchar(250) DEFAULT NULL,
  `F_ROLE` varchar(50) DEFAULT 'USER',
  `F_STATUS` varchar(50) DEFAULT 'ACTIVE',
  PRIMARY KEY (`F_ID`),
  UNIQUE KEY `login` (`F_LOGIN`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_users: ~10 rows (приблизительно)
/*!40000 ALTER TABLE `t_users` DISABLE KEYS */;
INSERT INTO `t_users` (`F_ID`, `F_LOGIN`, `F_PASSWORD`, `F_ROLE`, `F_STATUS`) VALUES
	(1, 'vitali.apetsenak@gmail.com', '$2a$10$X4ffQop3DDOimI7LmtbP4uI8KsO5tLr27vvWgDqUvD49yp.wBQ2mm', 'USER', 'ACTIVE'),
	(2, 'apetsenak@gmail.com', '17011991', 'ADMIN', 'ACTIVE'),
	(3, 'v.apetsenak@gmail.com', '17011991', 'ROOT', 'ACTIVE'),
	(4, '1234', '123', 'ADMIN', 'DELETED'),
	(6, '123', '123', 'USER', 'DELETED'),
	(7, '1231323233', '123', 'USER', 'BLOCKED'),
	(10, 'tut1@gmail.com', '1234', 'USER', 'BLOCKED'),
	(11, 'test', '$2a$10$h5ykuxK.oOrASD9IU/XHaedu/BUH5BHLMAT.W/pcpHYwA0V9b1rFe', 'USER', 'ACTIVE'),
	(12, 'test2', '$2a$10$q6XuRMZ4bEyVRWdqlEJPBejvOQUQM43fg8GalXu9PgrhUKU/LEzqq', 'USER', 'ACTIVE'),
	(17, 'tut@gmail.com', '$2a$10$lj3BYltkun9x.ZGkFrodaOuys2rvYlXhDYuvApMeuc4xHofrJ6N8C', 'USER', 'BLOCKED');
/*!40000 ALTER TABLE `t_users` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_user_address
DROP TABLE IF EXISTS `t_user_address`;
CREATE TABLE IF NOT EXISTS `t_user_address` (
  `F_USER_ID` bigint(20) NOT NULL,
  `F_COUNTRY` varchar(50) DEFAULT NULL,
  `F_CITY` varchar(50) DEFAULT NULL,
  `F_STREET` varchar(50) DEFAULT NULL,
  `F_HOUSE` varchar(50) DEFAULT NULL,
  `F_BLOCK` int(11) DEFAULT '0',
  `F_APARTMENT` int(11) DEFAULT '0',
  PRIMARY KEY (`F_USER_ID`),
  CONSTRAINT `FK_t_user_address_t_user_info` FOREIGN KEY (`F_USER_ID`) REFERENCES `t_user_info` (`F_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_user_address: ~7 rows (приблизительно)
/*!40000 ALTER TABLE `t_user_address` DISABLE KEYS */;
INSERT INTO `t_user_address` (`F_USER_ID`, `F_COUNTRY`, `F_CITY`, `F_STREET`, `F_HOUSE`, `F_BLOCK`, `F_APARTMENT`) VALUES
	(1, 'blr', 'minsk', 'indep', '168', 1, 111),
	(2, 'blr', 'minsk', 'indep', '168', 1, 111),
	(3, 'blr', 'minsk', 'indep', '168', 1, 111),
	(4, 'blr', 'minsk', 'indep', '168', 1, 111),
	(6, 'blr', 'minsk', 'indep', '168', 1, 111),
	(7, 'blr', 'minsk', 'indep', '168', 1, 111),
	(17, 'country', 'city', '', '', NULL, NULL);
/*!40000 ALTER TABLE `t_user_address` ENABLE KEYS */;

-- Дамп структуры для таблица shop.t_user_info
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE IF NOT EXISTS `t_user_info` (
  `F_USER_ID` bigint(20) NOT NULL,
  `F_FIRST_NAME` varchar(50) DEFAULT NULL,
  `F_SURNAME` varchar(50) DEFAULT NULL,
  `F_BIRTH_DATE` date DEFAULT NULL,
  `F_PHONE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`F_USER_ID`),
  CONSTRAINT `FK__t_users` FOREIGN KEY (`F_USER_ID`) REFERENCES `t_users` (`F_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.t_user_info: ~9 rows (приблизительно)
/*!40000 ALTER TABLE `t_user_info` DISABLE KEYS */;
INSERT INTO `t_user_info` (`F_USER_ID`, `F_FIRST_NAME`, `F_SURNAME`, `F_BIRTH_DATE`, `F_PHONE`) VALUES
	(1, 'asassaasa', 'sdfgsdf', '2018-01-23', '+375336594872'),
	(2, 'Виталя', 'Апетенок', '2018-01-23', '1111111'),
	(3, 'Виталя', 'Апетенок', '2018-01-23', '1111111'),
	(4, 'Виталя', 'Апетенок', '2018-01-23', '1111111'),
	(6, 'Виталя', 'Апетенок', '2018-01-23', '1111111'),
	(7, 'Виталя', 'Апетенок', '2018-01-23', '1111111'),
	(11, 'test', 'test', '2018-01-01', '123'),
	(12, '12341213', '12313', '2018-01-01', '12'),
	(17, 'TestReg', 'TestReg', '1991-01-17', '+375336594872');
/*!40000 ALTER TABLE `t_user_info` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
