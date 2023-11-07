# INSERT INTO `mobilele`.`brands` (`id`, `brand`) VALUES ('1', 'Toyota');
# INSERT INTO `mobilele`.`brands` (`id`, `brand`) VALUES ('2', 'Ford');
#
#
# INSERT INTO `mobilele`.`models` (`id`,`brand_id`, `name`)
# values (1,1,'Camry'),
#        (2,1, 'Corolla'),
#        (3,2, 'Focus'),
#        (4,2,'Fiesta');
#
# INSERT INTO `mobilele`.`users`(id, active, email, first_name, last_name, password)
# VALUES
#     (1, 1, 'admin@test.com', 'testA', 'testB', '3d4d2cab09f9022c152614b97b493d2508c04903eb48dd860ec958fa75c1345b878d071e6466ecfa1399a733ed16478f');
# INSERT INTO `mobilele`.`user_roles`(id, role)
# VALUES
#     (1, 'ADMIN'),
#     (2, 'USER');
# INSERT INTO `mobilele`.`users_roles`(id, role)
# VALUES
#     (2, 'ADMIN'),
#     (3, 'USER');
# INSERT INTO `mobilele`.`brands` (`id`, `brand`)
# VALUES ('3', 'Trabant');

# insert into `mobilele`.`models`(`id`,`model_category`, `name`, `brand_id`)
# values (1, 'CAR', '601', 3);

insert into `mobilele`.`offers`(`id`, `description`, `engine`,
                                `image_url`, `mileage`, `price`, `transmission`, `uuid`, `year`, `model_id`)

values (3, 'trabi super', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Trabant_1.1_30.06.19_JM_1.jpg/1200px-Trabant_1.1_30.06.19_JM_1.jpg',
        30005, 100, 'MANUAL', '9d3e0343-6a6c-4788-a5c8-3f6d42f43eak', 1987, 1),
       (4, 'trabi', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Trabant_1.1_30.06.19_JM_1.jpg/1200px-Trabant_1.1_30.06.19_JM_1.jpg',
        2000000, 1000, 'MANUAL', '9d3e0343-6a6c-4788-a5c8-3f6d42f43eal', 1986, 1),
       (5, 'extra', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Trabant_1.1_30.06.19_JM_1.jpg/1200px-Trabant_1.1_30.06.19_JM_1.jpg',
        300, 100000, 'MANUAL', '9d3e0343-6a6c-4788-a5c8-3f6d42f43eaz', 1980, 1),
       (6, 'trabi bla', 'PETROL', 'https://upload.wikimedia.org/wikipedia/commons/thumb/b/b1/Trabant_1.1_30.06.19_JM_1.jpg/1200px-Trabant_1.1_30.06.19_JM_1.jpg',
        3005, 1000000, 'MANUAL', '9d3e0343-6a6c-4788-a5c8-3f6d42f43eap', 1989, 1);



