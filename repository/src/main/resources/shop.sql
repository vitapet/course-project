-- --------------------------------------------------------
-- Хост:                         127.0.0.1
-- Версия сервера:               5.5.23 - MySQL Community Server (GPL)
-- Операционная система:         Win64
-- HeidiSQL Версия:              9.4.0.5192
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

-- Дамп структуры для таблица shop.comments
DROP TABLE IF EXISTS `comments`;
CREATE TABLE IF NOT EXISTS `comments` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `text` varchar(250) NOT NULL DEFAULT '0',
  `user` bigint(20) NOT NULL DEFAULT '0',
  `news` bigint(20) NOT NULL DEFAULT '0',
  `date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `comment_user` (`user`),
  KEY `comment_news` (`news`),
  CONSTRAINT `comment_news` FOREIGN KEY (`news`) REFERENCES `news` (`id`),
  CONSTRAINT `comment_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.comments: ~0 rows (приблизительно)
/*!40000 ALTER TABLE `comments` DISABLE KEYS */;
/*!40000 ALTER TABLE `comments` ENABLE KEYS */;

-- Дамп структуры для таблица shop.goods
DROP TABLE IF EXISTS `goods`;
CREATE TABLE IF NOT EXISTS `goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `category` varchar(50) DEFAULT NULL,
  `count` bigint(20) NOT NULL DEFAULT '0',
  `price` decimal(10,2) NOT NULL DEFAULT '0.00',
  `is_deleted` varchar(50) DEFAULT 'NOT_DELETED',
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `goods_category` (`category`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.goods: ~8 rows (приблизительно)
/*!40000 ALTER TABLE `goods` DISABLE KEYS */;
INSERT INTO `goods` (`id`, `name`, `description`, `category`, `count`, `price`, `is_deleted`) VALUES
	(1, 'Lenovo V5101', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(14, 'Lenovo V5102', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(15, 'Lenovo V5103', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(16, 'Lenovo V5104', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(17, 'Lenovo V5105', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(18, 'Lenovo V5106', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(19, 'Lenovo V5107', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED'),
	(20, 'Lenovo V5108', 'top notebook!!!', 'COMPUTERS', 2, 1230.90, 'NOT_DELETED');
/*!40000 ALTER TABLE `goods` ENABLE KEYS */;

-- Дамп структуры для таблица shop.news
DROP TABLE IF EXISTS `news`;
CREATE TABLE IF NOT EXISTS `news` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user` bigint(20) NOT NULL DEFAULT '0',
  `author` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(250) DEFAULT NULL,
  `date` timestamp NULL DEFAULT NULL,
  `image` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `FK_news_users` (`user`),
  CONSTRAINT `FK_news_users` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.news: ~1 rows (приблизительно)
/*!40000 ALTER TABLE `news` DISABLE KEYS */;
INSERT INTO `news` (`id`, `user`, `author`, `name`, `description`, `date`, `image`) VALUES
	(1, 2, '1', '1', '1', '2017-12-13 17:11:15', '1.jpg');
/*!40000 ALTER TABLE `news` ENABLE KEYS */;

-- Дамп структуры для таблица shop.purchases
DROP TABLE IF EXISTS `purchases`;
CREATE TABLE IF NOT EXISTS `purchases` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods` bigint(20) DEFAULT '0',
  `count` bigint(20) DEFAULT '0',
  `user` bigint(20) DEFAULT '0',
  `date` timestamp NULL DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `purchases_prodact` (`goods`),
  KEY `purchases_user` (`user`),
  CONSTRAINT `purchases_goods` FOREIGN KEY (`goods`) REFERENCES `goods` (`id`),
  CONSTRAINT `purchases_user` FOREIGN KEY (`user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.purchases: ~3 rows (приблизительно)
/*!40000 ALTER TABLE `purchases` DISABLE KEYS */;
INSERT INTO `purchases` (`id`, `goods`, `count`, `user`, `date`, `status`) VALUES
	(1, 1, 0, 1, '2017-12-11 10:48:48', 'REVIEWING'),
	(2, 1, 1, 1, '2017-12-04 09:41:00', 'IN_PROGRESS'),
	(3, 1, 1, 1, '2017-12-04 09:41:00', 'NEW');
/*!40000 ALTER TABLE `purchases` ENABLE KEYS */;

-- Дамп структуры для таблица shop.users
DROP TABLE IF EXISTS `users`;
CREATE TABLE IF NOT EXISTS `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `role` varchar(50) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `surname` varchar(50) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `phone` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `street` varchar(50) DEFAULT NULL,
  `house` varchar(50) DEFAULT NULL,
  `block` int(11) DEFAULT '0',
  `apartment` int(11) DEFAULT '0',
  `status` varchar(50) DEFAULT 'ACTIVE',
  PRIMARY KEY (`id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Дамп данных таблицы shop.users: ~6 rows (приблизительно)
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` (`id`, `login`, `password`, `role`, `first_name`, `surname`, `birth_date`, `phone`, `country`, `city`, `street`, `house`, `block`, `apartment`, `status`) VALUES
	(1, 'vitali.apetsenak@gmail.com', '1234', 'USER', 'Виталий', 'Апетенок', '1991-01-17', '+375336594872', 'Беларусь', 'Минск', 'пр-т Независимости', '168', 1, 219, 'ACTIVE'),
	(2, 'apetsenak@gmail.com', '17011991', 'ADMIN', 'Виталий', 'Апетенок', '1991-01-17', '+375336594872', 'Беларусь', 'Минск', 'пр-т Независимости', '168', 1, 219, 'ACTIVE'),
	(3, 'v.apetsenak@gmail.com', '17011991', 'ROOT', 'Виталий', 'Апетенок', '1991-01-17', '+375336594872', 'Беларусь', 'Минск', 'пр-т Независимости', '168', 1, 219, 'ACTIVE'),
	(4, '1234', '123', 'USER', '123', '123', '1991-01-17', '123', '123', '123', '123', '123', 1, 123, 'ACTIVE'),
	(5, '12', '123', 'USER', '123', '123', '1991-01-17', '123', '123', '123', '123', '123', 123, 123, 'ACTIVE'),
	(6, '123', '1', 'USER', '1', '1', '1313-12-13', '1', '1', '2', '1', '1', 1, 1, 'ACTIVE');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
