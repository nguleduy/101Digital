-- Table for User
CREATE TABLE IF NOT EXISTS tbl_user
(
    id              SERIAL PRIMARY KEY,
    username        VARCHAR(50) UNIQUE NOT NULL,
    password        VARCHAR(100)       NOT NULL,
    mobile_number   VARCHAR(20),
    name            VARCHAR(100),
    address         TEXT,
    type VARCHAR(20), -- Type can be 'SHOP-OWNER', 'SHOP-OPERATOR', 'CUSTOMER'
    disabled        BOOLEAN DEFAULT FALSE
);

-- Table for Shop
CREATE TABLE IF NOT EXISTS tbl_shop
(
    id             SERIAL PRIMARY KEY,
    latitude       NUMERIC(10, 2) NOT NULL,
    longitude      NUMERIC(10, 2) NOT NULL,
    address        VARCHAR(255),
    phone_number   VARCHAR(20),
    coffee_options JSONB,
    queue_settings JSONB,
    opening_time   TIME,
    closing_time   TIME,
    day_of_week    VARCHAR(10),
    disabled       BOOLEAN DEFAULT FALSE
);

-- Table for Menu
CREATE TABLE IF NOT EXISTS tbl_menu
(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR(100),
    price           NUMERIC(10, 2),
    disabled        BOOLEAN DEFAULT FALSE
);

-- Table for Order
CREATE TABLE IF NOT EXISTS tbl_order
(
    id              SERIAL PRIMARY KEY,
    shop_id         INT REFERENCES tbl_shop (id),
    user_id         INT REFERENCES tbl_user (id),
    menu_id         INT REFERENCES tbl_menu (id),
    order_time      TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status          VARCHAR(20) NOT NULL, -- Status can be 'pending', 'completed', 'cancelled', etc.
    disabled        BOOLEAN DEFAULT FALSE
);

-- Table for Queue
CREATE TABLE IF NOT EXISTS tbl_queue
(
    id                      SERIAL PRIMARY KEY,
    shop_id                 INT UNIQUE REFERENCES tbl_shop (id),
    order_id                INT UNIQUE REFERENCES tbl_order (id),
    queue_position          INT,
    estimated_waiting_time  INTERVAL,
    served                  BOOLEAN DEFAULT FALSE,
    disabled                BOOLEAN DEFAULT FALSE
);

-- Table for Promotion
CREATE TABLE IF NOT EXISTS tbl_promotion
(
    id              SERIAL PRIMARY KEY,
    shop_id         INT REFERENCES tbl_shop (id),
    user_id         INT REFERENCES tbl_user (id),
    description     TEXT,
    points          INT,
    disabled        BOOLEAN DEFAULT FALSE
);