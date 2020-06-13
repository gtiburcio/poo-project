CREATE TABLE `plano` (
  `id_plano` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(200) NOT NULL,
  `id_convenio` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_plano`),
  KEY `convenio_ibfk_1_idx` (`id_convenio`),
  CONSTRAINT `convenio_ibfk_1` FOREIGN KEY (`id_convenio`) REFERENCES `convenio` (`id_convenio`)
)