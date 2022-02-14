CREATE TABLE  `parking_ticket`(
  `id`                    INT NOT NULL AUTO_INCREMENT,
  `start_at`              DATETIME(3) NOT NULL,
  `end_at`                DATETIME(3) NULL,
  `guard_name`            VARCHAR(64) NOT NULL,
  `driver_name`           VARCHAR(64) NOT NULL,
  `vehicle_name`          VARCHAR(64) NOT NULL,
  `registration_plate`    VARCHAR(7) NOT NULL,
  
  PRIMARY KEY(`id`)
) engine=InnoDB default charset=utf8mb4;