ALTER TABLE `product`
    ADD FOREIGN KEY (`category`) REFERENCES `product_category`(`id`);

ALTER TABLE `stock`
    ADD FOREIGN KEY (`product`) REFERENCES `product`(`id`);

ALTER TABLE `stock`
    ADD FOREIGN KEY (`location`) REFERENCES `location`(`id`);

ALTER TABLE `order_detail`
    ADD FOREIGN KEY (`orderz`) REFERENCES `orderz`(`id`);

ALTER TABLE `order_detail`
    ADD FOREIGN KEY (`product`) REFERENCES `product`(`id`);

ALTER TABLE `revenue`
    ADD FOREIGN KEY (`location`) REFERENCES `location`(`id`);

ALTER TABLE `orderz`
    ADD FOREIGN KEY (`shipped_from`) REFERENCES `location`(`id`);

ALTER TABLE `orderz`
    ADD FOREIGN KEY (`customer`) REFERENCES `customer`(`id`);