-- Sample data for tbl_user
INSERT INTO tbl_user (username, password, mobile_number, name, address, type)
VALUES ('shop_owner', 'password1', '1234567890', 'Shop Owner', '123 Main St', 'SHOP-OWNER'),
       ('shop_operator', 'password2', '0987654321', 'Shop Operator', '456 Elm St', 'SHOP-OPERATOR'),
       ('customer1', 'password3', '9876543210', 'Customer 1', '789 Oak St', 'CUSTOMER'),
       ('customer2', 'password4', '0123456789', 'Customer 2', '101 Pine St', 'CUSTOMER');

-- Sample data for tbl_shop
INSERT INTO tbl_shop (latitude, longitude, address, phone_number, coffee_options, queue_settings, opening_time,
                      closing_time, day_of_week)
VALUES (40.7128, -74.0060, '1 Coffee St', '123-456-7890', '{"espresso": 3.50, "latte": 4.00}',
        '{"max_queue_size": 10, "min_order_time": "00:05:00"}', '08:00:00', '18:00:00', 'Monday'),
       (34.0522, -118.2437, '2 Coffee Ave', '987-654-3210', '{"cappuccino": 3.75, "mocha": 4.25}',
        '{"max_queue_size": 15, "min_order_time": "00:10:00"}', '09:00:00', '20:00:00', 'Tuesday');

-- Sample data for tbl_menu
INSERT INTO tbl_menu (name, price)
VALUES ('Espresso', 3.50),
       ('Latte', 4.00),
       ('Cappuccino', 3.75),
       ('Mocha', 4.25);