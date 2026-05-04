CREATE DATABASE IF NOT EXISTS softlearning;
USE softlearning;

CREATE TABLE books (
    id_product VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    isbn VARCHAR(50),
    title VARCHAR(255),
    author VARCHAR(255),
    publisher VARCHAR(255),
    publish_year INT,
    stock INT,
    price DOUBLE,
    weight DOUBLE,
    height DOUBLE,
    width DOUBLE,
    depth DOUBLE,
    is_available BOOLEAN,
    PRIMARY KEY (id_product)
);

CREATE TABLE clients (
    id_client INT NOT NULL,
    id_person VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(50),
    adress VARCHAR(255),
    name_person VARCHAR(255),
    registration_date VARCHAR(255),
    PRIMARY KEY (id_client)
);

create table courses(
    id_product VARCHAR(50) NOT NULL,
    name VARCHAR(255),
    description TEXT,
    stock INT,
    price DOUBLE,
    id_course VARCHAR(255),
    duration DOUBLE,
    is_available BOOLEAN
);

create table vehicles(
    matricula VARCHAR(50) NOT NULL,
    marca VARCHAR(255),
    modelo VARCHAR(255),
    caracteristicas TEXT,
    carga DOUBLE,
    capacidad DOUBLE,
    adquisicion VARCHAR(255),
    revision VARCHAR(255)
);

CREATE TABLE orders (
    order_id INT NOT NULL,
    operation_ref INT NOT NULL,
    client_id INT NOT NULL,
    description TEXT,
    start_date VARCHAR(255),
    finish_date VARCHAR(255),
    receiver_address VARCHAR(255),
    receiver_person VARCHAR(255),
    payment_date VARCHAR(255),
    delivery_date VARCHAR(255),
    phone_contacts VARCHAR(255),
    package_weight DOUBLE,
    package_height DOUBLE,
    package_width DOUBLE,
    package_depth DOUBLE,
    status VARCHAR(50),
    PRIMARY KEY (order_id)
);

CREATE TABLE order_details (
    detail_id BIGINT NOT NULL AUTO_INCREMENT,
    order_id INT NOT NULL,
    ref VARCHAR(50) NOT NULL,
    price DOUBLE,
    discount DOUBLE,
    amount INT,
    PRIMARY KEY (detail_id),
    CONSTRAINT fk_order_details_orders FOREIGN KEY (order_id) REFERENCES orders(order_id) ON DELETE CASCADE
);

CREATE TABLE permissions (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_permissions_name (name)
);

CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(50) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_roles_role_name (role_name)
);

CREATE TABLE role_permissions (
    role_id BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, permission_id),
    CONSTRAINT fk_role_permissions_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE,
    CONSTRAINT fk_role_permissions_permission FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE
);

CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(255) NOT NULL,
    is_enabled BOOLEAN DEFAULT TRUE,
    account_no_expired BOOLEAN DEFAULT TRUE,
    account_no_locked BOOLEAN DEFAULT TRUE,
    credential_no_expired BOOLEAN DEFAULT TRUE,
    PRIMARY KEY (id),
    UNIQUE KEY uk_users_username (username)
);

CREATE TABLE user_roles (
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    CONSTRAINT fk_user_roles_role FOREIGN KEY (role_id) REFERENCES roles(id) ON DELETE CASCADE
);

CREATE USER 'lorien'@'%' IDENTIFIED BY 'lorien';
GRANT ALL PRIVILEGES ON softlearning.* TO 'lorien'@'%';
FLUSH PRIVILEGES;