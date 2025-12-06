-- 1. Insert Company Account (Singleton)
INSERT INTO company_account (id, balance) VALUES (1, 500000.00)
    ON DUPLICATE KEY UPDATE id=id;

-- 2. Insert Admin User
-- Table name changed from 'user' to 'users' to match your Entity configuration
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$Oj8osRkDCV.mX2LbamTY9eCr23q/priZB/E2TUANUZtKaXWvAYtRG', 'ADMIN')
    ON DUPLICATE KEY UPDATE username=username;

-- 3. Insert Employees & Bank Accounts

-- Grade 1: 1 Employee
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name)
VALUES ('Savings', 'Uchchwas Das', 'ACC-101', 5000.0, 'City Bank', 'Gulshan');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id)
VALUES ('1001', 'Uchchwas Das', 1, 'Dhaka', '01700000001', (SELECT id FROM bank_account WHERE account_number='ACC-101'));

-- Grade 2: 1 Employee
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name)
VALUES ('Savings', 'Apel Sarker', 'ACC-102', 4000.0, 'City Bank', 'Gulshan');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id)
VALUES ('1002', 'Apel Sarker', 2, 'Dhaka', '01700000002', (SELECT id FROM bank_account WHERE account_number='ACC-102'));

-- Grade 3: 2 Employees
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name) VALUES
                                                                                                                   ('Savings', 'Abdul Sumon', 'ACC-103', 3000.0, 'Brac Bank', 'Banani'),
                                                                                                                   ('Savings', 'Tirtha Das', 'ACC-104', 3200.0, 'Brac Bank', 'Banani');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id) VALUES
                                                                                      ('1003', 'Abdul Sumon', 3, 'Sylhet', '01700000003', (SELECT id FROM bank_account WHERE account_number='ACC-103')),
                                                                                      ('1004', 'Tirtha Das', 3, 'Sylhet', '01700000004', (SELECT id FROM bank_account WHERE account_number='ACC-104'));

-- Grade 4: 2 Employees
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name) VALUES
                                                                                                                   ('Savings', 'Abdul Rahaman', 'ACC-105', 2000.0, 'Dutch Bangla', 'Mirpur'),
                                                                                                                   ('Savings', 'Jafar Sadik', 'ACC-106', 2100.0, 'Dutch Bangla', 'Mirpur');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id) VALUES
                                                                                      ('1005', 'Abdul Rahaman', 4, 'Chittagong', '01700000005', (SELECT id FROM bank_account WHERE account_number='ACC-105')),
                                                                                      ('1006', 'Jafar Sadik', 4, 'Chittagong', '01700000006', (SELECT id FROM bank_account WHERE account_number='ACC-106'));

-- Grade 5: 2 Employees
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name) VALUES
                                                                                                                   ('Savings', 'Maruf Rahaman', 'ACC-107', 1000.0, 'Sonali Bank', 'Motijheel'),
                                                                                                                   ('Savings', 'Sajid Rahaman', 'ACC-108', 1100.0, 'Sonali Bank', 'Motijheel');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id) VALUES
                                                                                      ('1007', 'Maruf Rahaman', 5, 'Rajshahi', '01700000007', (SELECT id FROM bank_account WHERE account_number='ACC-107')),
                                                                                      ('1008', 'Sajid Rahaman', 5, 'Rajshahi', '01700000008', (SELECT id FROM bank_account WHERE account_number='ACC-108'));

-- Grade 6: 2 Employees
INSERT INTO bank_account (account_type, account_name, account_number, current_balance, bank_name, branch_name) VALUES
                                                                                                                   ('Savings', 'Turjoy Rahaman', 'ACC-109', 500.0, 'Islami Bank', 'Farmgate'),
                                                                                                                   ('Savings', 'Joy Roy', 'ACC-110', 550.0, 'Islami Bank', 'Farmgate');

INSERT INTO employee (employee_id, name, grade, address, mobile, bank_account_id) VALUES
                                                                                      ('1009', 'Turjoy Rahaman', 6, 'Khulna', '01700000009', (SELECT id FROM bank_account WHERE account_number='ACC-109')),
                                                                                      ('1010', 'Joy Roy', 6, 'Khulna', '01700000010', (SELECT id FROM bank_account WHERE account_number='ACC-110'));