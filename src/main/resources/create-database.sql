-- Create the database
CREATE DATABASE storage_management;
GO

-- Use the database
USE storage_management;
GO

-- Create the warehouse table
CREATE TABLE warehouse (
    id NVARCHAR(50) PRIMARY KEY,
    total_area FLOAT NOT NULL,
    available_area FLOAT NOT NULL
);

-- Create the product table
CREATE TABLE product (
    id NVARCHAR(50) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    size FLOAT NOT NULL,
    warehouse_id NVARCHAR(50),
    FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
);