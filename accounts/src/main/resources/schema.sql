CREATE TABLE IF NOT EXISTS `customer` (
    `customer_id` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(100) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `mobile_number` VARCHAR(20) NOT NULL,
    `created_date` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `last_modified_date` DATE DEFAULT NULL,
    `modified_by` VARCHAR(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS `account` (
    `customer_id` INT NOT NULL,
    `account_number` INT AUTO_INCREMENT PRIMARY KEY,
    `account_type` VARCHAR(100) NOT NULL,
    `branch_address` VARCHAR(100) NOT NULL,
    `created_date` DATE NOT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `last_modified_date` DATE DEFAULT NULL,
    `modified_by` VARCHAR(20) DEFAULT NULL
);