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
  
INSERT INTO `vehicle` (`registration_plate`, `manufacturer`, `description`)
VALUES 
  ('PBN5706', 'TOYOTA', 'COROLLA GLI UPPER'),
  ('PBN5707', 'TOYOTA', 'COROLLA GLI UPPER'),
  ('OVT1748', 'VW', 'SPACEFOX TREND GII');
  
INSERT INTO `parking_ticket` (`id`, `start_at`, `end_at`, `driver_cpf`, `guard_id`, `vehicle_plate`)
VALUES 
  (1, '2022-02-15 12:00:00', null, '99999999990', 1, 'PBN5706'),
  (2, '2022-02-15 12:30:00', '2022-02-15 13:00:00', '99999999991', 2, 'PBN5707'),
  (3, '2022-02-15 14:00:00', '2022-02-15 19:00:00', '99999999991', 3, 'OVT1748');
   