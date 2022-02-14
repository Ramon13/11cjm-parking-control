CREATE TABLE IF NOT EXISTS `driver`(
  `cpf`                   VARCHAR(11) NOT NULL UNIQUE,
  `name`                  VARCHAR(64) NOT NULL,
  
  PRIMARY KEY(`cpf`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `guard`(
 `cpf`                     VARCHAR(11) NOT NULL UNIQUE,
 `name`                    VARCHAR(64) NOT NULL,
 `username`                VARCHAR(20) NOT NULL UNIQUE,
 `password`                VARCHAR(255) NOT NULL,
 
  PRIMARY KEY(`cpf`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE IF NOT EXISTS `vehicle`(
 `registration_plate`       VARCHAR(7) NOT NULL UNIQUE,
 `manufacturer`             VARCHAR(64) NOT NULL,
 `description`              VARCHAR(64) NOT NULL,

  PRIMARY KEY(`registration_plate`) 
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;


CREATE TABLE  `parking_ticket`(
  `id`                    INT NOT NULL AUTO_INCREMENT,
  `start_at`              DATETIME(3) NOT NULL,
  `end_at`                DATETIME(3) NULL,
  `driver_cpf`            VARCHAR(11) NOT NULL,
  `guard_cpf`             VARCHAR(11) NOT NULL,
  `vehicle_plate`         VARCHAR(7) NOT NULL,
  
  PRIMARY KEY(`id`),
  CONSTRAINT `FK_PARKINGTICKET_DRIVER` FOREIGN KEY (`driver_cpf`) REFERENCES `driver` (`cpf`),
  CONSTRAINT `FK_PARKINGTICKET_GUARD` FOREIGN KEY (`guard_cpf`) REFERENCES `guard` (`cpf`),
  CONSTRAINT `FK_PARKINGTICKET_VEHICLE` FOREIGN KEY (`vehicle_plate`) REFERENCES `vehicle` (`registration_plate`)
) ENGINE=InnoDB DEFAULT CHARACTER SET = utf8mb4;