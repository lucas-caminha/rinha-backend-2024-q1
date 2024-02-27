CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `limite` int DEFAULT NULL,
  `saldo_inicial` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) 

INSERT INTO `cliente` (`id`, `limite`, `saldo_inicial`) VALUES (1, 100000, 0);
INSERT INTO `cliente` (`id`, `limite`, `saldo_inicial`) VALUES (2, 80000, 0);
INSERT INTO `cliente` (`id`, `limite`, `saldo_inicial`) VALUES (3, 1000000, 0);
INSERT INTO `cliente` (`id`, `limite`, `saldo_inicial`) VALUES (4, 10000000, 0);
INSERT INTO `cliente` (`id`, `limite`, `saldo_inicial`) VALUES (5, 500000, 0);
