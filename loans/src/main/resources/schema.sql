CREATE TABLE IF NOT EXISTS `loan` (
    `loan_id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `mobile_number` VARCHAR(20) NOT NULL,
    `loan_number` VARCHAR(100) NOT NULL,
    `loan_type` VARCHAR(100) NOT NULL,
    `total_loan` INT NOT NULL,
    `amount_paid` INT NOT NULL,
    `outstanding_amount` INT NOT NULL,
    `created_date` DATE DEFAULT NULL,
    `created_by` VARCHAR(20) NOT NULL,
    `last_modified_date` DATE DEFAULT NULL,
    `modified_by` VARCHAR(20) DEFAULT NULL
);