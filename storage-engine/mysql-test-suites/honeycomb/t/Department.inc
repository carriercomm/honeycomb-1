--disable_warnings
DROP TABLE IF EXISTS `Department`;
--enable_warnings
CREATE TABLE `Department` (
  `Id` int(11) DEFAULT NULL,
  `Name` varchar(50) DEFAULT NULL,
  KEY `Id` (`Id`),
  KEY `Name` (`Name`)
);

INSERT INTO `Department` VALUES (2,'Quality Control'),(7,'Manufacturing'),(6,'Shipping'),(8,'Maintenance'),(4,'Operations'),(0,'Research & Development'),(3,'Human Resources'),(1,'Legal'),(5,'Business Development'),(9,'Accounting');
