-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: mygame
-- ------------------------------------------------------
-- Server version	8.0.40

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
-- Table structure for table `backgrounds`
--

DROP TABLE IF EXISTS `backgrounds`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `backgrounds` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `image_path` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `backgrounds`
--

LOCK TABLES `backgrounds` WRITE;
/*!40000 ALTER TABLE `backgrounds` DISABLE KEYS */;
INSERT INTO `backgrounds` VALUES (1,'MainMenu','/assets/backgrounds/mainmenu.png'),(2,'namefield','src/assets/backgrounds/name.png');
/*!40000 ALTER TABLE `backgrounds` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `choices`
--

DROP TABLE IF EXISTS `choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `choices` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dialogue_id` int DEFAULT NULL,
  `choice_text` varchar(255) DEFAULT NULL,
  `next_dialogue_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `dialogue_id` (`dialogue_id`),
  CONSTRAINT `choices_ibfk_1` FOREIGN KEY (`dialogue_id`) REFERENCES `dialogues` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `choices`
--

LOCK TABLES `choices` WRITE;
/*!40000 ALTER TABLE `choices` DISABLE KEYS */;
INSERT INTO `choices` VALUES (1,11,'I\'m excited to start over.',12),(2,11,'I miss our old place.',13),(3,11,'I\'m nervous but I\'ll try.',14);
/*!40000 ALTER TABLE `choices` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dialogues`
--

DROP TABLE IF EXISTS `dialogues`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dialogues` (
  `id` int NOT NULL AUTO_INCREMENT,
  `speaker` varchar(255) DEFAULT NULL,
  `text` text,
  `character_image` varchar(255) DEFAULT NULL,
  `background_image` varchar(255) DEFAULT NULL,
  `sfx` varchar(255) DEFAULT NULL,
  `type` varchar(20) DEFAULT 'normal',
  `next_dialogue_id` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dialogues`
--

LOCK TABLES `dialogues` WRITE;
/*!40000 ALTER TABLE `dialogues` DISABLE KEYS */;
INSERT INTO `dialogues` VALUES (1,'ALARM','Beep beep beep!','alarm.png','bedroom1.png','alarmsfx.wav','normal',NULL),(2,'{player}','(YAWNN~)','protagonist.png','bedroom1.png','yawn.wav','normal',NULL),(3,'{player}','Ugh... first morning in the new house. New room. New school. New everything.','protagonist.png','bedroom1.png','','normal',NULL),(4,'{player}','I still can’t believe we moved halfway across the country.','protagonist.png','bedroom1.png','','normal',NULL),(5,'{player}','Maybe this is my chance to start fresh... or maybe it’ll just be weird.','protagonist.png','bedroom1.png','','normal',NULL),(6,'Mom','{player}, Breakfast’s ready! Don’t be late on your first day!','mom.png','bedroom1.png','','normal',NULL),(7,'{player}','Oh crap I gotta get ready, it’ll be embarrassing to be late on the first day.','protagonist.png','bedroom1.png','','normal',NULL),(8,'Mom','Morning, sweetheart. Sleep okay?','mom.png','diningtable.png','','normal',NULL),(9,'{player}','Yeah... I guess. Just getting used to the new room.','protagonist.png','diningtable.png','','normal',NULL),(10,'Mom','I know it’s a big change. This place feels so quiet without all the familiar noises, right?','mom.png','diningtable.png','','normal',NULL),(11,'Mom','How are you feeling about everything?','mom.png','diningtable.png','','choice',NULL),(12,'Mom','That’s the spirit. A fresh start can be a good thing. You never know what great things are waiting around the corner.','mom.png','diningtable.png','','normal',15),(13,'Mom','I know, honey. I miss it too. It’s okay to feel that way. But give this place a chance, alright? You might end up liking it here more than you think.','mom.png','diningtable.png','','normal',15),(14,'Mom','Oh honey, just be yourself. People will see how amazing you are.','mom.png','diningtable.png','','normal',15),(15,'Mom','Anyways, you better start preparing soon.','mom.png','diningtable.png','','normal',NULL),(16,'Mom','Don’t forget your bag and your schedule. First days are always a little chaotic.','mom.png','diningtable.png','','normal',NULL),(17,'{player}','Thanks Mom! I Got it!','mom.png','diningtable.png','','normal',NULL);
/*!40000 ALTER TABLE `dialogues` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `player_id` int NOT NULL AUTO_INCREMENT,
  `player_Name` varchar(100) NOT NULL,
  `time_added` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `player_id` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (1,'asd','2025-04-13 10:00:39'),(2,'Midnightkohi','2025-04-13 10:00:39'),(3,'Saitama','2025-04-13 10:00:39'),(4,'Sample','2025-04-13 10:00:39'),(5,'asd','2025-04-13 10:00:39'),(6,'asd','2025-04-13 10:00:39'),(7,'asd','2025-04-13 10:00:39'),(8,'Jkllen','2025-04-13 10:00:39'),(9,'s','2025-04-13 10:00:39'),(10,'Sample1','2025-04-13 10:00:39'),(11,'asd','2025-04-13 10:00:39'),(12,'Stats','2025-04-13 10:00:39'),(13,'asd','2025-04-13 10:00:39'),(14,'asd','2025-04-13 10:00:39'),(15,'asd','2025-04-13 10:00:39'),(16,'asd','2025-04-13 10:00:39'),(17,'asd','2025-04-13 10:00:39'),(18,'Vaile','2025-04-13 10:00:39'),(19,'sad','2025-04-13 10:00:39'),(20,'sad','2025-04-13 10:00:39'),(21,'asd','2025-04-13 10:00:39'),(22,'asd','2025-04-13 10:00:39'),(23,'sad','2025-04-13 10:00:39'),(24,'sd','2025-04-13 10:00:39'),(25,'asd','2025-04-13 10:00:39'),(26,'sad','2025-04-13 10:00:39'),(27,'s','2025-04-13 10:00:39'),(28,'asd','2025-04-13 10:00:39'),(29,'asd','2025-04-13 10:00:39'),(30,'sd','2025-04-13 10:00:39'),(31,'asd','2025-04-13 10:00:39'),(32,'sadsad','2025-04-13 10:00:39'),(33,'assad','2025-04-13 10:00:39'),(34,'sad','2025-04-13 10:00:39'),(35,'asd','2025-04-13 10:00:39'),(36,'sad','2025-04-13 10:00:39'),(37,'asd','2025-04-13 10:00:39'),(38,'asd','2025-04-13 10:00:39'),(39,'asd','2025-04-13 10:00:39'),(40,'Jkllen','2025-04-13 10:00:39'),(41,'sad','2025-04-13 10:00:39'),(42,'asd','2025-04-13 10:00:39'),(43,'asd','2025-04-13 10:00:39'),(44,'Finally','2025-04-13 10:01:28'),(45,'Augustine','2025-04-13 10:30:59'),(46,'adsasd','2025-04-13 10:31:50'),(47,'Test','2025-04-13 10:34:38'),(48,'asd','2025-04-13 10:35:14'),(49,'sad','2025-04-13 10:39:17'),(50,'sd','2025-04-13 10:40:56'),(51,'asd','2025-04-13 10:43:14'),(52,'sad','2025-04-13 10:50:48'),(53,'Vaile','2025-04-13 11:00:17'),(54,'Ass','2025-04-13 11:13:09'),(55,'sad','2025-04-13 11:13:57'),(56,'asd','2025-04-13 11:14:26'),(57,'asd','2025-04-13 11:20:42'),(58,'asd','2025-04-13 11:22:53'),(59,'asd','2025-04-13 11:24:16'),(60,'asd','2025-04-13 11:24:26'),(61,'asd','2025-04-13 11:26:10'),(62,'LOL','2025-04-13 11:26:48'),(63,'asd','2025-04-13 11:44:15'),(64,'sad','2025-04-13 11:46:00'),(65,'asd','2025-04-13 11:46:37'),(66,'Augustine','2025-04-13 12:01:33'),(67,'asdsad','2025-04-13 12:07:44'),(68,'Augustine','2025-04-13 12:08:33'),(69,'Sarah','2025-04-13 13:17:35'),(70,'Augustine','2025-04-13 13:48:08'),(71,'JAJJAJA','2025-04-13 13:56:41'),(72,'Augustine','2025-04-13 14:04:22'),(73,'sad','2025-04-13 14:06:12'),(74,'asd','2025-04-13 14:06:57'),(75,'asd','2025-04-13 14:07:43'),(76,'asd','2025-04-13 14:08:15'),(77,'asd','2025-04-13 14:09:28'),(78,'sad','2025-04-13 14:10:00'),(79,'aasd','2025-04-13 14:11:58'),(80,'sad','2025-04-13 14:13:06'),(81,'sad','2025-04-13 14:17:27'),(82,'da','2025-04-13 14:18:50'),(83,'fas','2025-04-13 14:20:26'),(84,'asd','2025-04-13 14:22:54'),(85,'SAd','2025-04-13 14:26:48'),(86,'asd','2025-04-13 14:28:27'),(87,'asd','2025-04-13 14:28:46'),(88,'sad','2025-04-13 14:32:53'),(89,'sd','2025-04-13 14:36:23'),(90,'asd','2025-04-13 14:36:41'),(91,'asd','2025-04-13 14:40:39'),(92,'sad','2025-04-13 14:42:39'),(93,'JEjemon','2025-04-13 14:50:27'),(94,'asd','2025-04-13 14:54:16'),(95,'asd','2025-04-13 15:02:19'),(96,'asd','2025-04-13 15:02:59'),(97,'s','2025-04-13 15:03:38'),(98,'d','2025-04-13 15:04:05'),(99,'sad','2025-04-13 15:04:45'),(100,'sad','2025-04-13 15:06:01'),(101,'d','2025-04-13 15:06:36'),(102,'asd','2025-04-13 15:09:05'),(103,'asd','2025-04-13 15:11:27'),(104,'s','2025-04-13 15:12:48'),(105,'asd','2025-04-13 15:23:18'),(106,'sd','2025-04-13 15:26:41'),(107,'asd','2025-04-13 15:32:41'),(108,'dsf','2025-04-13 15:34:14'),(109,'asd','2025-04-13 15:36:29'),(110,'sd','2025-04-13 15:36:45');
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `saves`
--

DROP TABLE IF EXISTS `saves`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `saves` (
  `slot_id` int NOT NULL,
  `player_name` varchar(255) DEFAULT NULL,
  `dialogue_index` int DEFAULT NULL,
  PRIMARY KEY (`slot_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `saves`
--

LOCK TABLES `saves` WRITE;
/*!40000 ALTER TABLE `saves` DISABLE KEYS */;
/*!40000 ALTER TABLE `saves` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-04-13 23:54:05
