# FlightStation Simulator ✈️

**Customer Flow Simulation Software**

The FlightStation Simulator is a Java-based application designed to simulate customer flows at key service points within an airport. The purpose of the software is to help airport staff plan and optimize staffing requirements by simulating customer arrivals and service times at critical service areas.

## Project Overview

The goal of this project was to create a tool for simulating customer flows within an airport environment. By visualizing and measuring these flows, the application enables better decision-making for staffing and capacity planning, ultimately improving operational efficiency.

## Architecture

The application follows the **Model-View-Controller (MVC)** design pattern, which separates the application's logic, user interface, and data management. This architecture provides a clear structure for the application, making it easier to maintain, scale, and extend.

## Technologies and Tools Used

- **Java**: The primary programming language used to develop the application.
- **SQL**: Used for database management and query creation.
- **JUnit**: Employed for unit testing and ensuring the reliability of the application.
- **Jakarta**: Used in conjunction with Java EE for enterprise-level application architecture.
- **JavaFX**: A library used for developing the graphical user interface.
- **Logback**: A logging framework used for managing and storing logs.
- **Hibernate**: ORM (Object-Relational Mapping) tool used to map Java objects to database entities.

## Features

- **Customer Flow Simulation**: Simulates the arrival of customers at various service points in an airport.
- **Service Point Management**: Allows users to configure service points and estimate customer service times, adjusting for different scenarios.
- **Scenario Testing**: Enables users to simulate various scenarios and see how changes in service point efficiency impact customer wait times.
- **Data Analysis**: After running a simulation, the user can analyze the results through detailed output, helping to identify bottlenecks and optimize staffing.

## How to run

- Navigate to `src/main/java`
- Launch `main.java`
