-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: DESKTOP-MDHAGU4    Database: stockdb
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `monetarytransaction`
--

DROP TABLE IF EXISTS `monetarytransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `monetarytransaction` (
  `transactionID` int NOT NULL,
  `userID` int NOT NULL,
  `externalBankRoute#` varchar(20) NOT NULL,
  `externalBankName` varchar(50) NOT NULL,
  `externalBankAct#` varchar(20) NOT NULL,
  `activityType` varchar(10) NOT NULL,
  `amount` decimal(10,2) NOT NULL,
  PRIMARY KEY (`transactionID`),
  KEY `userID` (`userID`),
  CONSTRAINT `monetarytransaction_ibfk_1` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`),
  CONSTRAINT `monetarytransaction_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `monetarytransaction`
--

LOCK TABLES `monetarytransaction` WRITE;
/*!40000 ALTER TABLE `monetarytransaction` DISABLE KEYS */;
INSERT INTO `monetarytransaction` VALUES (10,1,'9876562918','Chase','1235925209','DEPOSIT',100.00);
/*!40000 ALTER TABLE `monetarytransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order` (
  `orderID` int NOT NULL AUTO_INCREMENT,
  `orderType` int NOT NULL,
  `quantity` decimal(6,2) NOT NULL,
  `userID` int NOT NULL,
  `stockSymbol` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`orderID`),
  KEY `stockSymbol` (`stockSymbol`),
  KEY `userID` (`userID`),
  KEY `orderType` (`orderType`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`stockSymbol`) REFERENCES `stock` (`stockSymbol`),
  CONSTRAINT `order_ibfk_2` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`),
  CONSTRAINT `order_ibfk_3` FOREIGN KEY (`orderType`) REFERENCES `ordertypetable` (`orderType`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,0,50.00,1,'QQQ'),(2,1,-20.00,1,'QQQ'),(3,0,50.00,1,'QQQ'),(4,0,69.00,1,'ABC');
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ordertypetable`
--

DROP TABLE IF EXISTS `ordertypetable`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ordertypetable` (
  `orderType` int NOT NULL,
  `orderStatus` varchar(20) NOT NULL,
  PRIMARY KEY (`orderType`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ordertypetable`
--

LOCK TABLES `ordertypetable` WRITE;
/*!40000 ALTER TABLE `ordertypetable` DISABLE KEYS */;
INSERT INTO `ordertypetable` VALUES (0,'buy'),(1,'sell');
/*!40000 ALTER TABLE `ordertypetable` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stock`
--

DROP TABLE IF EXISTS `stock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stock` (
  `stockSymbol` varchar(5) NOT NULL,
  `ask` decimal(6,2) NOT NULL,
  `bid` decimal(6,2) NOT NULL,
  `52_Week` decimal(6,2) DEFAULT NULL,
  `quarterlyDividendPerc` decimal(3,2) DEFAULT NULL,
  `PEratio` decimal(4,2) DEFAULT NULL,
  `totalShares` int NOT NULL,
  PRIMARY KEY (`stockSymbol`),
  UNIQUE KEY `stockSymbol_UNIQUE` (`stockSymbol`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stock`
--

LOCK TABLES `stock` WRITE;
/*!40000 ALTER TABLE `stock` DISABLE KEYS */;
INSERT INTO `stock` VALUES ('ABC',4.56,1.23,7.89,NULL,10.00,999),('MEM',5.32,5.06,5.32,NULL,NULL,5000),('QQQ',12.04,10.24,15.64,4.00,NULL,1000);
/*!40000 ALTER TABLE `stock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stocktransaction`
--

DROP TABLE IF EXISTS `stocktransaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stocktransaction` (
  `transactionID` int NOT NULL,
  `orderID` int NOT NULL,
  `userID` int NOT NULL,
  `priceExecuted` decimal(10,2) NOT NULL,
  PRIMARY KEY (`transactionID`),
  KEY `orderID` (`orderID`),
  KEY `userID` (`userID`),
  CONSTRAINT `stocktransaction_ibfk_1` FOREIGN KEY (`transactionID`) REFERENCES `transaction` (`transactionID`),
  CONSTRAINT `stocktransaction_ibfk_2` FOREIGN KEY (`orderID`) REFERENCES `order` (`orderID`),
  CONSTRAINT `stocktransaction_ibfk_3` FOREIGN KEY (`userID`) REFERENCES `order` (`userID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stocktransaction`
--

LOCK TABLES `stocktransaction` WRITE;
/*!40000 ALTER TABLE `stocktransaction` DISABLE KEYS */;
INSERT INTO `stocktransaction` VALUES (1,1,1,12.42),(2,2,1,52.12),(3,3,1,2.11),(4,4,1,12.34);
/*!40000 ALTER TABLE `stocktransaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transactionID` int NOT NULL AUTO_INCREMENT,
  `transactionDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`transactionID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
INSERT INTO `transaction` VALUES (1,'2022-04-18 13:05:53'),(2,'2022-04-18 13:57:15'),(3,'2022-04-18 13:57:15'),(4,'2022-04-18 14:58:30'),(10,'2022-04-20 14:17:43');
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `userID` int NOT NULL AUTO_INCREMENT,
  `username` varchar(25) NOT NULL,
  `password` varchar(25) NOT NULL,
  `balance` decimal(10,2) unsigned NOT NULL DEFAULT '0.00',
  `ssn` varchar(10) NOT NULL,
  `address` varchar(100) NOT NULL,
  `fName` varchar(20) NOT NULL,
  `lName` varchar(20) NOT NULL,
  `email` varchar(100) DEFAULT NULL,
  `phone#` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`userID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'abc','123',100.00,'999662323','myhouse','Bob','Bill','bobbill@yahoo.com','9099099009'),(2,'user','pass',0.00,'123546565','247 Spruce Dr, CA, 22716','Charlie','Smith',NULL,NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-20 15:11:09
