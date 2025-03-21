-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: kommunityon
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
-- Table structure for table `codigo_recuperacao`
--

DROP TABLE IF EXISTS `codigo_recuperacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `codigo_recuperacao` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CODIGO` varchar(6) NOT NULL,
  `ID_USUARIO` bigint NOT NULL,
  `EMISSAO` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CODIGO` (`CODIGO`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `codigo_recuperacao_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `codigo_recuperacao`
--

LOCK TABLES `codigo_recuperacao` WRITE;
/*!40000 ALTER TABLE `codigo_recuperacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `codigo_recuperacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comentario`
--

DROP TABLE IF EXISTS `comentario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comentario` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` bigint DEFAULT NULL,
  `ID_SOLICITACAO` bigint DEFAULT NULL,
  `TEXTO` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  KEY `ID_SOLICITACAO` (`ID_SOLICITACAO`),
  CONSTRAINT `comentario_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `comentario_ibfk_2` FOREIGN KEY (`ID_SOLICITACAO`) REFERENCES `solicitacao` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comentario`
--

LOCK TABLES `comentario` WRITE;
/*!40000 ALTER TABLE `comentario` DISABLE KEYS */;
/*!40000 ALTER TABLE `comentario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `endereco`
--

DROP TABLE IF EXISTS `endereco`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `endereco` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` bigint DEFAULT NULL,
  `UF` varchar(2) NOT NULL,
  `BAIRRO` varchar(60) NOT NULL,
  `CIDADE` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `endereco_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `endereco`
--

LOCK TABLES `endereco` WRITE;
/*!40000 ALTER TABLE `endereco` DISABLE KEYS */;
/*!40000 ALTER TABLE `endereco` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitacao`
--

DROP TABLE IF EXISTS `solicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitacao` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` bigint DEFAULT NULL,
  `TITULO` varchar(60) NOT NULL,
  `BAIRRO` varchar(60) NOT NULL,
  `DESCRICAO` varchar(450) NOT NULL,
  `NUM_LIKES` int DEFAULT '0',
  `NUM_COMENTARIOS` int DEFAULT '0',
  `DATA_ABERTURA` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `DATA_CONCLUSAO` datetime DEFAULT NULL,
  `ANONIMO` enum('0','1') DEFAULT '0',
  PRIMARY KEY (`ID`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  CONSTRAINT `solicitacao_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacao`
--

LOCK TABLES `solicitacao` WRITE;
/*!40000 ALTER TABLE `solicitacao` DISABLE KEYS */;
INSERT INTO `solicitacao` VALUES (1,1,'Semáforo quebrado na Avenida','Jardim X','O semáforo está quebrado há 2 semanas, causando trânsito que se extende até o outro bairro. Precisamos que consertem logo.',5,0,'2025-03-21 13:37:31',NULL,'0');
/*!40000 ALTER TABLE `solicitacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitacao_tag`
--

DROP TABLE IF EXISTS `solicitacao_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitacao_tag` (
  `ID_SOLICITACAO` bigint NOT NULL,
  `ID_TAG` bigint NOT NULL,
  PRIMARY KEY (`ID_SOLICITACAO`,`ID_TAG`),
  KEY `ID_TAG` (`ID_TAG`),
  CONSTRAINT `solicitacao_tag_ibfk_1` FOREIGN KEY (`ID_SOLICITACAO`) REFERENCES `solicitacao` (`ID`) ON DELETE CASCADE,
  CONSTRAINT `solicitacao_tag_ibfk_2` FOREIGN KEY (`ID_TAG`) REFERENCES `tag` (`ID`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitacao_tag`
--

LOCK TABLES `solicitacao_tag` WRITE;
/*!40000 ALTER TABLE `solicitacao_tag` DISABLE KEYS */;
/*!40000 ALTER TABLE `solicitacao_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tag`
--

DROP TABLE IF EXISTS `tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tag` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NOME` varchar(30) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tag`
--

LOCK TABLES `tag` WRITE;
/*!40000 ALTER TABLE `tag` DISABLE KEYS */;
INSERT INTO `tag` VALUES (1,'Trânsito'),(2,'Saúde'),(3,'Avenida'),(4,'Energia'),(5,'Inclusão'),(6,'Violência'),(7,'Entretenimento'),(8,'Serviços públicos');
/*!40000 ALTER TABLE `tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NOME` varchar(80) NOT NULL,
  `CPF` varchar(15) NOT NULL,
  `EMAIL` varchar(70) NOT NULL,
  `SENHA` varchar(30) NOT NULL,
  `TELEFONE` varchar(22) NOT NULL,
  `DATA_INTERACAO_1` timestamp NULL DEFAULT NULL,
  `DATA_INTERACAO_2` timestamp NULL DEFAULT NULL,
  `TIPO` varchar(3) DEFAULT 'USU',
  `FOTO_PERFIL` mediumblob,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `CPF` (`CPF`),
  UNIQUE KEY `EMAIL` (`EMAIL`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'Administrador','00000000000','administrador.kon@gmail.com','@@Kon!2025','1',NULL,NULL,'ADM',NULL),(2,'Usuário da ouvidoria','12312312312','usuario.kon@gmail.com','123','12312312312',NULL,NULL,'USU',NULL);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario_like_solicitacao`
--

DROP TABLE IF EXISTS `usuario_like_solicitacao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario_like_solicitacao` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ID_USUARIO` bigint DEFAULT NULL,
  `ID_SOLICITACAO` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `ID_USUARIO` (`ID_USUARIO`),
  KEY `ID_SOLICITACAO` (`ID_SOLICITACAO`),
  CONSTRAINT `usuario_like_solicitacao_ibfk_1` FOREIGN KEY (`ID_USUARIO`) REFERENCES `usuario` (`ID`),
  CONSTRAINT `usuario_like_solicitacao_ibfk_2` FOREIGN KEY (`ID_SOLICITACAO`) REFERENCES `solicitacao` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario_like_solicitacao`
--

LOCK TABLES `usuario_like_solicitacao` WRITE;
/*!40000 ALTER TABLE `usuario_like_solicitacao` DISABLE KEYS */;
/*!40000 ALTER TABLE `usuario_like_solicitacao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'kommunityon'
--
/*!50106 SET @save_time_zone= @@TIME_ZONE */ ;
/*!50106 DROP EVENT IF EXISTS `APAGA_CODIGOS_ANTIGOS` */;
DELIMITER ;;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;;
/*!50003 SET character_set_client  = utf8mb4 */ ;;
/*!50003 SET character_set_results = utf8mb4 */ ;;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;;
/*!50003 SET @saved_time_zone      = @@time_zone */ ;;
/*!50003 SET time_zone             = 'SYSTEM' */ ;;
/*!50106 CREATE*/ /*!50117 DEFINER=`root`@`localhost`*/ /*!50106 EVENT `APAGA_CODIGOS_ANTIGOS` ON SCHEDULE EVERY 1 DAY STARTS '2025-03-21 10:37:31' ON COMPLETION NOT PRESERVE ENABLE DO BEGIN
  DELETE FROM CODIGO_RECUPERACAO WHERE EMISSAO < NOW() - INTERVAL 1 DAY;
END */ ;;
/*!50003 SET time_zone             = @saved_time_zone */ ;;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;;
/*!50003 SET character_set_client  = @saved_cs_client */ ;;
/*!50003 SET character_set_results = @saved_cs_results */ ;;
/*!50003 SET collation_connection  = @saved_col_connection */ ;;
DELIMITER ;
/*!50106 SET TIME_ZONE= @save_time_zone */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-03-21 10:38:05
