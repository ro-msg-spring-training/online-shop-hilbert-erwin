INSERT INTO LOCATION(name) VALUES
('location1'),('location2'),('location3');

INSERT INTO PRODUCT(name) VALUES
('product1'),('product2'),('product3');

INSERT INTO STOCK(product,location,quantity) VALUES
(1,1,5),
(1,2,3),
(1,3,4),
(2,2,3),
(2,3,4),
(3,1,5),
(3,3,4);


-- veszek 5 product3-at es product1-et (remelem hogy location 1 rol) => ez 1 order
-- veszek 4 product2-ot (remelem location 3rol)
