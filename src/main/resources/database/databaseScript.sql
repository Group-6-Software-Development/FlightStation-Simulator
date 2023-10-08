CREATE USER IF NOT EXISTS 'flightAppuser'@'localhost' IDENTIFIED BY 'appuser321';

CREATE DATABASE IF NOT EXISTS flight_station_database;
USE flight_station_database;

GRANT DROP, CREATE, ALTER, SELECT, INSERT, UPDATE ON flight_station_database.variables TO 'flightAppuser'@'localhost';

CREATE TABLE IF NOT EXISTS variables (
                           id INT AUTO_INCREMENT PRIMARY KEY,
                           name VARCHAR(50),
                           customer_count INT,
                           total_time DOUBLE,
                           busy_time DOUBLE,
                           utilization DOUBLE,
                           through_put DOUBLE,
                           avg_service_time DOUBLE,
                           waiting_time DOUBLE,
                           lead_time DOUBLE,
                           queue_length DOUBLE
);
