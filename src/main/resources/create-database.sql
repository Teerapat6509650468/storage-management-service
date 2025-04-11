-- Create the database
CREATE DATABASE storage_management;
GO

-- Use the database
USE storage_management;
GO

-- Create the warehouse table
CREATE TABLE warehouse (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    total_area FLOAT NOT NULL,
    available_area FLOAT NOT NULL
);

-- Create the product table
CREATE TABLE product (
    id BIGINT IDENTITY(1,1) PRIMARY KEY,
    name NVARCHAR(255) NOT NULL,
    size FLOAT NOT NULL,
    warehouse_id BIGINT,
    FOREIGN KEY (warehouse_id) REFERENCES warehouse(id)
);