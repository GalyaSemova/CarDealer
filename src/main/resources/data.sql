INSERT INTO `mobilele`.`brands` (`id`, `brand`) VALUES ('1', 'Toyota');
INSERT INTO `mobilele`.`brands` (`id`, `brand`) VALUES ('2', 'Ford');


INSERT INTO `mobilele`.`models` (`id`,`brand_id`, `name`)
values (1,1,'Camry'),
       (2,1, 'Corolla'),
       (3,2, 'Focus'),
       (4,2,'Fiesta');
