-- MySQL dump 10.13  Distrib 8.0.45, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: online_banking
-- ------------------------------------------------------
-- Server version	8.0.45

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,2250,2),(2,1000,3),(3,57550,4),(4,793100,5),(5,4999601,6),(6,0,7),(7,500000,8),(8,1500,9),(9,6910000,11),(10,500,13),(11,0,14),(12,0,15),(13,7500,16),(14,0,17);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `accounts`
--

DROP TABLE IF EXISTS `accounts`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `accounts` (
  `account_id` int NOT NULL AUTO_INCREMENT,
  `balance` double NOT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UKe4w4av1wrhanry7t6mxt42nou` (`user_id`),
  CONSTRAINT `FKnjuop33mo69pd79ctplkck40n` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `accounts`
--

LOCK TABLES `accounts` WRITE;
/*!40000 ALTER TABLE `accounts` DISABLE KEYS */;
/*!40000 ALTER TABLE `accounts` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `is_read` bit(1) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `expiry_time` datetime(6) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
INSERT INTO `otp` VALUES (1,'2026-03-09 16:28:47.943767','513341','9831495378'),(2,'2026-03-09 16:49:23.981322','201215','9831495378'),(3,'2026-03-09 16:49:48.391317','652005','9831495378'),(4,'2026-03-09 16:55:22.892278','826841','9831495378'),(5,'2026-03-09 16:59:40.211324','308958','919831495378'),(6,'2026-03-09 17:04:18.299756','321930','919831495378'),(7,'2026-03-09 17:30:14.353721','487416','9831495378'),(8,'2026-03-09 18:34:38.349777','245047','9831495378'),(9,'2026-03-09 18:39:47.177978','506501','9831495378'),(10,'2026-03-09 18:52:19.204426','685164','9831495378'),(11,'2026-03-09 19:01:06.589521','477933','9831495378'),(12,'2026-03-09 19:31:53.018183','929066','8777781128'),(13,'2026-03-09 21:37:57.588507','315480','8777781128'),(14,'2026-03-09 21:39:01.942543','261437','8777781128'),(15,'2026-03-09 21:48:41.853819','434419','9831495378'),(16,'2026-03-09 21:56:12.299504','283507','9831495378'),(17,'2026-03-09 22:04:54.416779','368405','9831495378'),(18,'2026-03-09 22:09:18.754832','696235','9831495378'),(19,'2026-03-09 22:43:58.123477','891109','9831495378'),(20,'2026-03-09 23:29:02.546784','534252','9831495378'),(21,'2026-03-10 10:00:43.104672','376807','9143654328'),(22,'2026-03-10 10:04:15.533634','859388','9143654328'),(23,'2026-03-10 10:57:16.652067','391693','9143654328'),(24,'2026-03-10 12:44:21.633005','557832','9143654328'),(25,'2026-03-10 13:05:59.508254','537828','9831495378'),(26,'2026-03-10 13:08:27.230257','523769','9123038024'),(27,'2026-03-10 13:17:05.156454','681690','9123038024');
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transaction`
--

DROP TABLE IF EXISTS `transaction`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transaction` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transaction`
--

LOCK TABLES `transaction` WRITE;
/*!40000 ALTER TABLE `transaction` DISABLE KEYS */;
/*!40000 ALTER TABLE `transaction` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transactions`
--

DROP TABLE IF EXISTS `transactions`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transactions` (
  `transaction_id` int NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` int NOT NULL,
  `date_time` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transactions`
--

LOCK TABLES `transactions` WRITE;
/*!40000 ALTER TABLE `transactions` DISABLE KEYS */;
INSERT INTO `transactions` VALUES (1,500,'DEPOSIT',2,NULL,NULL),(2,200,'WITHDRAW',2,NULL,NULL),(3,100,'DEPOSIT',3,NULL,NULL),(4,500,'DEPOSIT',3,NULL,NULL),(5,100,'WITHDRAW',3,NULL,NULL),(6,500,'DEPOSIT',3,NULL,NULL),(7,500,'DEPOSIT',4,NULL,NULL),(8,100,'WITHDRAW',4,NULL,NULL),(9,300,'DEPOSIT',4,NULL,NULL),(10,200,'DEPOSIT',4,NULL,NULL),(11,100,'DEPOSIT',4,NULL,NULL),(12,500,'WITHDRAW',4,NULL,NULL),(13,100,'DEPOSIT',4,NULL,NULL),(14,100,'DEPOSIT',4,'2026-03-08 09:46:49.866976',NULL),(15,100,'DEPOSIT',4,'2026-03-08 10:11:27.262808',NULL),(16,100,'WITHDRAW',4,'2026-03-08 10:24:58.036992',NULL),(17,500,'DEPOSIT',4,'2026-03-08 10:38:05.281704',NULL),(18,1000,'WITHDRAW',4,'2026-03-08 10:39:18.890349',NULL),(19,50,'TRANSFER SENT',4,'2026-03-08 10:41:18.015275',NULL),(20,50,'TRANSFER RECEIVED',2,'2026-03-08 10:41:18.023839',NULL),(21,50000,'DEPOSIT',4,'2026-03-08 11:59:26.665719',NULL),(22,500,'TRANSFER SENT',4,'2026-03-08 12:03:26.932011',NULL),(23,500,'TRANSFER RECEIVED',2,'2026-03-08 12:03:26.965359',NULL),(24,800000,'DEPOSIT',5,'2026-03-08 12:08:08.017183',NULL),(25,1000,'TRANSFER SENT',5,'2026-03-08 12:08:53.194445',NULL),(26,1000,'TRANSFER RECEIVED',4,'2026-03-08 12:08:53.202437',NULL),(27,1000,'TRANSFER SENT',5,'2026-03-08 12:11:19.541589',NULL),(28,1000,'TRANSFER RECEIVED',4,'2026-03-08 12:11:19.550500',NULL),(29,1000,'TRANSFER SENT',5,'2026-03-08 12:11:36.588382',NULL),(30,1000,'TRANSFER RECEIVED',4,'2026-03-08 12:11:36.596243',NULL),(31,100,'DEPOSIT',5,'2026-03-08 14:41:54.948427',NULL),(32,5000,'TRANSFER SENT',5,'2026-03-08 14:42:38.707250',NULL),(33,5000,'TRANSFER RECEIVED',4,'2026-03-08 14:42:38.714099',NULL),(34,500,'DEPOSIT',5,'2026-03-09 11:29:09.161813',NULL),(35,100,'DEPOSIT',6,'2026-03-09 11:48:03.294164',NULL),(36,50,'TRANSFER SENT',6,'2026-03-09 11:49:06.727734',NULL),(37,50,'TRANSFER RECEIVED',2,'2026-03-09 11:49:06.735632',NULL),(38,50,'TRANSFER SENT',6,'2026-03-09 11:50:37.655359',NULL),(39,50,'TRANSFER RECEIVED',2,'2026-03-09 11:50:37.663360',NULL),(40,500,'DEPOSIT',2,'2026-03-09 12:44:57.088107',NULL),(41,100,'TRANSFER SENT',2,'2026-03-09 12:48:02.394176',NULL),(42,100,'TRANSFER RECEIVED',2,'2026-03-09 12:48:02.409967',NULL),(43,100,'DEPOSIT',6,'2026-03-09 13:54:39.674834',NULL),(44,200,'DEPOSIT',6,'2026-03-09 13:55:33.436515',NULL),(45,1,'DEPOSIT',6,'2026-03-09 13:56:29.703218',NULL),(46,100,'DEPOSIT',6,'2026-03-09 14:36:19.174119',NULL),(47,200,'TRANSFER SENT',6,'2026-03-09 14:36:35.386794',NULL),(48,200,'TRANSFER RECEIVED',2,'2026-03-09 14:36:35.398819',NULL),(49,100,'WITHDRAW',6,'2026-03-09 14:38:05.013437',NULL),(50,5000000,'DEPOSIT',6,'2026-03-09 14:38:36.485501',NULL),(51,500,'TRANSFER SENT',6,'2026-03-09 14:39:00.850582',NULL),(52,500,'TRANSFER RECEIVED',2,'2026-03-09 14:39:00.862693',NULL),(55,500,'DEPOSIT',9,'2026-03-10 10:00:59.730903',NULL),(56,1000,'DEPOSIT',9,'2026-03-10 10:01:49.578777',NULL),(66,50000,'DEPOSIT',11,'2026-03-10 14:33:13.439818',NULL),(67,8000,'DEPOSIT',16,'2026-03-10 15:40:17.905797',NULL),(68,500,'TRANSFER SENT',16,'2026-03-10 15:42:37.078816',NULL),(69,500,'TRANSFER RECEIVED',5,'2026-03-10 15:42:37.089751',NULL);
/*!40000 ALTER TABLE `transactions` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transfer`
--

DROP TABLE IF EXISTS `transfer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `transfer` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `amount` double NOT NULL,
  `receiver_username` varchar(255) DEFAULT NULL,
  `sender_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transfer`
--

LOCK TABLES `transfer` WRITE;
/*!40000 ALTER TABLE `transfer` DISABLE KEYS */;
INSERT INTO `transfer` VALUES (1,100,'mina',2),(2,100,'mina',4),(3,50,'mina',4),(4,500,'mina',4),(5,1000,'Rahul Saha',5),(6,1000,'Rahul Saha',5),(7,1000,'Rahul Saha',5),(8,5000,'Rahul Saha',5),(9,50,'mina',6),(10,50,'mina',6),(11,100,'mina',2),(12,200,'mina',6),(13,500,'mina',6),(14,500,'Sumit Das',16);
/*!40000 ALTER TABLE `transfer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `otp` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `verified` bit(1) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKdu5v5sr43g5bfnji4vb8hg5s3` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'rahul@gmail.com','1234','rahul',NULL,NULL,_binary '\0'),(2,'mina@gmail.com','new123','mina',NULL,NULL,_binary '\0'),(3,NULL,'bapi#8931','Bapi Saha',NULL,NULL,_binary '\0'),(4,NULL,'Rahul#8777','Rahul Saha',NULL,NULL,_binary '\0'),(5,NULL,'sumit#5469','Sumit Das',NULL,NULL,_binary '\0'),(6,'RAHULSAHA6917@GMAIL.COM','Rahul#9831','Rahul Saha',NULL,NULL,_binary '\0'),(7,'rahul@gmail.com','1234','rahul',NULL,NULL,_binary '\0'),(8,'RAHULSAHA6917@GMAIL.COM','Rahulsaha#8777','Rahul Saha',NULL,'9831495378',_binary '\0'),(9,'SAHARAHUL0589@GMAIL.COM','Minasaha#9143','Mina Sarma',NULL,'9143654328',_binary '\0'),(11,'bishal2003saha@gmail.com','Bishal#9831','Bishal Saha',NULL,'9123038024',_binary '\0'),(13,'Sumon9632@gmail.com','Sumon#75313','Sumon Saha',NULL,'9132083042',_binary '\0'),(14,'romesh1527@gmail.com','Romesh#9831','Romesh Sarker',NULL,'9178963245',_binary '\0'),(15,'Somor Saha1527@gmail.com','Somorsaha#9856','Somor Saha',NULL,'9132536878',_binary '\0'),(16,'sanker9831@gmail.com','sanker#9889542','Sanker Saha',NULL,'9125089274',_binary '\0'),(17,'Roman5649@gmail.com','Roman#2597','Roman Das',NULL,'9125089275',_binary '\0');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-03-11 15:02:02
