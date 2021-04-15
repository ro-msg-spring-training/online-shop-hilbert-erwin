CREATE TABLE IF NOT EXISTS `address` (
    `id` bigint NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `address_country` varchar(128),
    `address_city` varchar(128),
    `address_county` varchar(128),
    `address_street_address` varchar(128)
);

ALTER TABLE `location`
    DROP COLUMN `address_country`, `address_city`, `address_county`, `address_street_address`;

ALTER TABLE `order`
    DROP COLUMN `address_country`, `address_city`, `address_county`, `address_street_address`;

ALTER TABLE `location`
    ADD COLUMN `address` int;

ALTER TABLE `order`
    ADD COLUMN `address` int;

ALTER TABLE `location`
    ADD FOREIGN KEY (`address`) REFERENCES `address`(`id`);

ALTER TABLE `order`
    ADD FOREIGN KEY (`address`) REFERENCES `address`(`id`);