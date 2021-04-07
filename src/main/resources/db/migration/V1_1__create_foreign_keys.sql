ALTER TABLE `product`
    ADD FOREIGN KEY (`category`) REFERENCES `product_category`(`id`);

ALTER TABLE `stock`
    ADD FOREIGN KEY (`product`) REFERENCES `product`(`id`);

ALTER TABLE `stock`
    ADD FOREIGN KEY (`location`) REFERENCES `location`(`id`);

ALTER TABLE `order_detail`
    ADD FOREIGN KEY (`order`) REFERENCES `order`(`id`);

ALTER TABLE `order_detail`
    ADD FOREIGN KEY (`product`) REFERENCES `product`(`id`);

ALTER TABLE `revenue`
    ADD FOREIGN KEY (`location`) REFERENCES `location`(`id`);

ALTER TABLE `order`
    ADD FOREIGN KEY (`shipped_from`) REFERENCES `location`(`id`);

ALTER TABLE `order`
    ADD FOREIGN KEY (`customer`) REFERENCES `customer`(`id`);