-- Task 1
-- Write SQL statements in this file to create the brewery database and 
-- populate the database with the given SQL files

DROP DATABASE IF EXISTS brewery;
CREATE DATABASE brewery;

USE brewery;

SOURCE database/beers.sql;
SOURCE database/breweries.sql;
SOURCE database/categories.sql;
SOURCE database/geocodes.sql;
SOURCE database/styles.sql;



