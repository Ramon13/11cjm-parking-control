USE `parking_controller`;
INSERT INTO `driver` (`cpf`, `name`)
VALUES 
  ('99999999990', 'Luce Hadufuns'),
  ('99999999991', 'Gjurd Diocles'),
  ('99999999992', 'Nestor Yosef');
  
INSERT INTO `guard` (`id`, `cpf`, `name`, `username`, `password`, `reset_credentials`)
VALUES 
  (1, '99999999980', 'Wiegand Kristīna',  'kistina',  '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1),
  (2, '99999999981', 'Trish Clara',       'clara',    '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1),
  (3, '99999999982', 'Aurelia Katinka',   'katinka',  '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1);
  
INSERT INTO `supervisor` (`id`, `username`, `password`, `reset_credentials`)
VALUES 
  (1,  'ramon',    '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1),
  (2,  'clara',    '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1),
  (3,  'katinka',  '{bcrypt}$2a$10$j0Eeieuz7Pz.gUKtesw7v.m2gKP.YgjV0y6dtBH6QBbFhTGJyTkgi', 1);
  
INSERT INTO `vehicle` (`registration_plate`, `manufacturer`, `description`)
VALUES 
  ('PBN5706', 'TOYOTA', 'COROLLA GLI UPPER'),
  ('PBN5707', 'TOYOTA', 'COROLLA GLI UPPER'),
  ('OVT1748', 'VW', 'SPACEFOX TREND GII');
  
INSERT INTO `parking_ticket` (`id`, `start_at`, `end_at`, `driver_cpf`, `opened_guard_id`, `closed_guard_id`, `vehicle_plate`, `vehicle_mileage`)
VALUES 
  (1, '2022-02-15 12:00:00', '2022-02-15 13:23:00', '99999999990', 1, 1, 'PBN5706', 12000),
  (2, '2022-02-15 12:30:00', '2022-02-15 13:00:00', '99999999991', 2, 1, 'PBN5706', 12014),
  (3, '2022-02-15 14:00:00', '2022-02-15 19:00:00', '99999999991', 3, 3, 'PBN5707', 20000),
  (4, '2022-02-15 14:30:00', '2022-02-15 18:00:00', '99999999991', 3, 3, 'PBN5707', 20100),
  (5, '2022-02-16 14:30:00', '2022-02-16 15:30:00', '99999999991', 1, 1, 'OVT1748', null),
  (6, '2022-02-17 12:00:00', null, '99999999991', 1, null, 'OVT1748', 28000);
    