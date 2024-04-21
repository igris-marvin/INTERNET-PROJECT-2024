-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server version:               11.3.2-MariaDB - mariadb.org binary distribution
-- Server OS:                    Win64
-- HeidiSQL Version:             12.6.0.6765
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Dumping database structure for sanienterprisedb
DROP DATABASE IF EXISTS `sanienterprisedb`;
CREATE DATABASE IF NOT EXISTS `sanienterprisedb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;
USE `sanienterprisedb`;

-- Dumping structure for table sanienterprisedb.account
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
  `account_id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(20) NOT NULL,
  `status` enum('ACTIVE','INACTIVE','SUSPENDED','PENDING_ACTIVATION','LOCKED') NOT NULL,
  `username` varchar(20) NOT NULL,
  `fk_cart_id` int(11) DEFAULT NULL,
  `fk_history_id` int(11) DEFAULT NULL,
  `fk_wishlist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UK_q3oug9y393ao8s67fb377abjl` (`fk_cart_id`),
  UNIQUE KEY `UK_25mxp5la2ykyywbbw3ggsh00d` (`fk_history_id`),
  UNIQUE KEY `UK_m3hopl0l78ahjjx0rp62nrw0` (`fk_wishlist_id`),
  CONSTRAINT `FKggpb83d4h60mxgo1m3l8cxve2` FOREIGN KEY (`fk_history_id`) REFERENCES `history` (`history_id`),
  CONSTRAINT `FKht9t0x06gpy791nk45n2ys8sh` FOREIGN KEY (`fk_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKkqbknqh3r0i59tlfncdkltpo1` FOREIGN KEY (`fk_wishlist_id`) REFERENCES `wishlist` (`wishlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.address
DROP TABLE IF EXISTS `address`;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(50) NOT NULL,
  `province` varchar(50) NOT NULL,
  `street` varchar(50) NOT NULL,
  `surburb` varchar(50) NOT NULL,
  `zip_code` varchar(4) NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.cart
DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart` (
  `cart_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`cart_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.cart_products
DROP TABLE IF EXISTS `cart_products`;
CREATE TABLE IF NOT EXISTS `cart_products` (
  `cart_cart_id` int(11) NOT NULL,
  `products_product_id` int(11) NOT NULL,
  UNIQUE KEY `UK_drjwlaada8umelrgy12e7fwe1` (`products_product_id`),
  KEY `FKewu4olnfvfyd6wfdix1s4gdtr` (`cart_cart_id`),
  CONSTRAINT `FKewu4olnfvfyd6wfdix1s4gdtr` FOREIGN KEY (`cart_cart_id`) REFERENCES `cart` (`cart_id`),
  CONSTRAINT `FKs05ud3dow9bi7li6q05gmh546` FOREIGN KEY (`products_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.history
DROP TABLE IF EXISTS `history`;
CREATE TABLE IF NOT EXISTS `history` (
  `history_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.history_product_id
DROP TABLE IF EXISTS `history_product_id`;
CREATE TABLE IF NOT EXISTS `history_product_id` (
  `history_history_id` int(11) NOT NULL,
  `product_id_product_id` int(11) NOT NULL,
  UNIQUE KEY `UK_1uumqnk3qvnyp4p9pa3dm1tul` (`product_id_product_id`),
  KEY `FK2fxw8c7fipoc8k71mubss8v08` (`history_history_id`),
  CONSTRAINT `FK2fxw8c7fipoc8k71mubss8v08` FOREIGN KEY (`history_history_id`) REFERENCES `history` (`history_id`),
  CONSTRAINT `FKbyh4chrm20brjqlsskjoa01q4` FOREIGN KEY (`product_id_product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.image
DROP TABLE IF EXISTS `image`;
CREATE TABLE IF NOT EXISTS `image` (
  `image_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_name` varchar(100) NOT NULL,
  `image_source` longblob DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`image_id`),
  KEY `FKgpextbyee3uk9u6o2381m7ft1` (`product_id`),
  CONSTRAINT `FKgpextbyee3uk9u6o2381m7ft1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.product
DROP TABLE IF EXISTS `product`;
CREATE TABLE IF NOT EXISTS `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `category` enum('CHAIR','TABLE','BED','SOFA','CABINET','SHELVE','DESK') NOT NULL,
  `date_added` datetime(6) NOT NULL,
  `height` float DEFAULT NULL,
  `length` float DEFAULT NULL,
  `price` float NOT NULL,
  `product_description` varchar(1000) NOT NULL,
  `product_name` varchar(100) NOT NULL,
  `product_status` enum('AVAILABLE','UNAVAILABLE','OUT_OF_STOCK','COMING_SOON') NOT NULL,
  `quantity` int(11) NOT NULL,
  `style` varchar(100) DEFAULT NULL,
  `width` float DEFAULT NULL,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.user
DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `user_type` varchar(31) NOT NULL,
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `contact_number` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `id_number` varchar(13) NOT NULL,
  `name` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `admin_password` varchar(20) DEFAULT NULL,
  `admin_username` varchar(20) DEFAULT NULL,
  `permissions` enum('READ_ONLY','READ_WRITE') DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `birth_date` datetime(6) DEFAULT NULL,
  `date_added` datetime(6) DEFAULT NULL,
  `gender` char(20) DEFAULT NULL,
  `fk_account_id` int(11) DEFAULT NULL,
  `fk_address_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `UK_893geq5bxk3o6gh642gh6cyic` (`id_number`),
  UNIQUE KEY `UK_292ycht4p41ja5i6i3uvqal1s` (`fk_account_id`),
  UNIQUE KEY `UK_hgaopwcwsm6fcns6aidyjf5ot` (`fk_address_id`),
  CONSTRAINT `FK3b4kmikokqjutodt2302s7cmy` FOREIGN KEY (`fk_address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `FKa2x5i8n8d5e9bdmq9x887bvge` FOREIGN KEY (`fk_account_id`) REFERENCES `account` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.wishlist
DROP TABLE IF EXISTS `wishlist`;
CREATE TABLE IF NOT EXISTS `wishlist` (
  `wishlist_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`wishlist_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

-- Dumping structure for table sanienterprisedb.wishlist_product_id
DROP TABLE IF EXISTS `wishlist_product_id`;
CREATE TABLE IF NOT EXISTS `wishlist_product_id` (
  `wishlist_wishlist_id` int(11) NOT NULL,
  `product_id_product_id` int(11) NOT NULL,
  UNIQUE KEY `UK_keatg8ucoxi3v7ajpsesdgk2k` (`product_id_product_id`),
  KEY `FKs96rt8heer0cci8raqrk3qbqo` (`wishlist_wishlist_id`),
  CONSTRAINT `FKm5dlccpndu50d73fe7n701gtq` FOREIGN KEY (`product_id_product_id`) REFERENCES `product` (`product_id`),
  CONSTRAINT `FKs96rt8heer0cci8raqrk3qbqo` FOREIGN KEY (`wishlist_wishlist_id`) REFERENCES `wishlist` (`wishlist_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Data exporting was unselected.

/*!40103 SET TIME_ZONE=IFNULL(@OLD_TIME_ZONE, 'system') */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;
