CREATE TABLE IF NOT EXISTS `revenue` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `location` int,
    `date` date,
    `sum` bigint
);

CREATE TABLE IF NOT EXISTS `customer` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `first_name` varchar(128),
    `last_name` varchar(128),
    `username` varchar(128),
    `password` varchar(128),
    `email_address` varchar(128)
);

CREATE TABLE IF NOT EXISTS `supplier` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(128)
);

CREATE TABLE IF NOT EXISTS `product_category` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(128),
    `description` varchar(128)
);

CREATE TABLE IF NOT EXISTS `orderz` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `shipped_from` int,
    `customer` int,
    `created_at` datetime,
    `address_country` varchar(128),
    `address_city` varchar(128),
    `address_county` varchar(128),
    `address_street_address` varchar(128)
);

CREATE TABLE IF NOT EXISTS `location` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(128),
    `address_country` varchar(128),
    `address_city` varchar(128),
    `address_county` varchar(128),
    `address_street_address` varchar(128)
);

CREATE TABLE IF NOT EXISTS `stock` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product` int,
    `location` int,
    `quantity` int
);

CREATE TABLE IF NOT EXISTS `order_detail` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `orderz` int,
    `product` int,
    `quantity` int
);

CREATE TABLE IF NOT EXISTS `product` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `name` varchar(128),
    `description` varchar(128),
    `price` bigint,
    `weight` double,
    `category` int,
    `supplier` int,
    `image_url` varchar(128)
);
