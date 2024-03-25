-- MySQL dump 10.13  Distrib 8.0.31, for macos13.0 (arm64)
--
-- Host: localhost    Database: yw082
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `customer_id` int NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(20) NOT NULL,
  `vip_status` enum('active','expired','none') DEFAULT NULL,
  `vip_expire` date DEFAULT NULL,
  `customer_address` varchar(255) DEFAULT NULL,
  `customer_phone` char(20) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Alice Smith','active','2024-12-31','789 Hill Rd','555-7890'),(2,'Bob Johnson','',NULL,'321 Lake Ave','555-6543'),(3,'John Doe','active','2024-12-31','123 Elm Street','555-1234'),(4,'John Doe','active','2024-12-31','123 Elm Street','555-1234');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customerorder`
--

DROP TABLE IF EXISTS `customerorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customerorder` (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `restaurant_id` int DEFAULT NULL,
  `customer_id` int DEFAULT NULL,
  `order_status` enum('confirmed','accepted','rejected','delivering','delivered','wait_for_driver') NOT NULL,
  `comment` text,
  `order_subtotal` decimal(10,2) DEFAULT NULL,
  `order_rating` decimal(2,1) DEFAULT NULL,
  `order_review` varchar(255) DEFAULT NULL,
  `order_service_fee` decimal(15,2) DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `delivery_person_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `customer_id` (`customer_id`),
  KEY `customerorder_ibfk_1` (`restaurant_id`),
  KEY `fk_customerorder_deliveryperson` (`delivery_person_id`),
  CONSTRAINT `customerorder_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `restaurant` (`restaurant_id`),
  CONSTRAINT `customerorder_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `Customer` (`customer_id`),
  CONSTRAINT `fk_customerorder_deliveryperson` FOREIGN KEY (`delivery_person_id`) REFERENCES `DeliveryPerson` (`delivery_person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customerorder`
--

LOCK TABLES `customerorder` WRITE;
/*!40000 ALTER TABLE `customerorder` DISABLE KEYS */;
INSERT INTO `customerorder` VALUES (1,1,1,'confirmed','Please deliver quickly.',20.00,4.5,'\"Excellent service and delicious food. Highly recommended!\"',2.00,'2024-03-18 14:28:54',NULL),(2,2,2,'delivered','',30.00,NULL,NULL,3.00,'2024-03-18 14:28:54',NULL),(3,1,1,'confirmed',NULL,27.50,NULL,NULL,3.99,'2024-03-18 14:28:54',NULL);
/*!40000 ALTER TABLE `customerorder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `DeliveryPerson`
--

DROP TABLE IF EXISTS `DeliveryPerson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `DeliveryPerson` (
  `delivery_person_id` int NOT NULL AUTO_INCREMENT,
  `delivery_person_name` varchar(255) NOT NULL,
  `delivery_person_phone_number` varchar(20) NOT NULL,
  PRIMARY KEY (`delivery_person_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `DeliveryPerson`
--

LOCK TABLES `DeliveryPerson` WRITE;
/*!40000 ALTER TABLE `DeliveryPerson` DISABLE KEYS */;
INSERT INTO `DeliveryPerson` VALUES (1,'John Doe','555-1122'),(2,'Jane Roe','555-3344');
/*!40000 ALTER TABLE `DeliveryPerson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menu`
--

DROP TABLE IF EXISTS `menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `menu` (
  `item_id` int NOT NULL AUTO_INCREMENT,
  `item_name` varchar(50) DEFAULT NULL,
  `restaurant_id` int DEFAULT NULL,
  `item_price` decimal(10,2) DEFAULT NULL,
  `item_sales_volume` int DEFAULT NULL,
  `item_image` varchar(255) DEFAULT NULL,
  `item_description` varchar(255) DEFAULT NULL,
  `item_category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `Restaurant_id` (`restaurant_id`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`restaurant_id`) REFERENCES `Restaurant` (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menu`
--

LOCK TABLES `menu` WRITE;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` VALUES (1,'Updated Cheese Pizza',1,10.99,100,'url_to_updated_cheese_pizza_image.jpg','An updated description of the delicious cheese pizza.','Main Course'),(2,'Espresso',1,2.50,150,'espresso.jpg','Strong and rich in flavor.',NULL),(13,'Cheese Pizza',1,9.99,NULL,'http://example.com/pizza.jpg','A delicious cheese pizza','Main Course');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `OrderItems`
--

DROP TABLE IF EXISTS `OrderItems`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `OrderItems` (
  `order_id` int NOT NULL,
  `item_id` int NOT NULL,
  `quantity` int DEFAULT '1',
  PRIMARY KEY (`order_id`,`item_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `orderitems_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `customerOrder` (`order_id`),
  CONSTRAINT `orderitems_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `menu` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `OrderItems`
--

LOCK TABLES `OrderItems` WRITE;
/*!40000 ALTER TABLE `OrderItems` DISABLE KEYS */;
INSERT INTO `OrderItems` VALUES (1,1,2),(1,2,1),(2,2,1),(2,13,1);
/*!40000 ALTER TABLE `OrderItems` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurant`
--

DROP TABLE IF EXISTS `restaurant`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `restaurant` (
  `restaurant_id` int NOT NULL AUTO_INCREMENT,
  `restaurant_name` varchar(50) NOT NULL,
  `restaurant_category` varchar(30) DEFAULT NULL,
  `restaurant_rating` decimal(2,1) DEFAULT NULL,
  `restaurant_revenue` decimal(15,2) DEFAULT NULL,
  `restaurant_address` varchar(255) DEFAULT NULL,
  `restaurant_phone` char(20) DEFAULT NULL,
  `restaurant_hero_image` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`restaurant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurant`
--

LOCK TABLES `restaurant` WRITE;
/*!40000 ALTER TABLE `restaurant` DISABLE KEYS */;
INSERT INTO `restaurant` VALUES (1,'Tasty Treats','Cafe',4.5,10000.00,'123 Main St','412562170','image1.jpg'),(2,'Savory Sensations','Fine Dining',4.8,20000.00,'456 Side St','923123522','image2.jpg');
/*!40000 ALTER TABLE `restaurant` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-25 18:14:38
