CREATE TABLE `order_line_items` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `price` decimal(38,2) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `sku_code` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `order_number` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ;

CREATE TABLE `orders_order_line_items_list` (
  `orders_id` bigint NOT NULL,
  `order_line_items_list_id` bigint NOT NULL,
  UNIQUE KEY `UKao6a1tas0iyb7iju31c5b7ef8` (`order_line_items_list_id`),
  KEY `FKj6ix8gvar7itra1kd1vxxor20` (`orders_id`),
  CONSTRAINT `FK9itkpvs1xr2gte662cyk3u736` FOREIGN KEY (`order_line_items_list_id`) REFERENCES `order_line_items` (`id`),
  CONSTRAINT `FKj6ix8gvar7itra1kd1vxxor20` FOREIGN KEY (`orders_id`) REFERENCES `orders` (`id`)
);