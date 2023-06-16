# Car Rental Project

### Front-end Application: https://github.com/jak-hry/Car_Rental_frontend

### Description
This project consists of multiple controllers that handle different aspects of a car rental application.
Here is a brief overview of each controller:

## CarCategoryController

- Endpoint: `/v1/category`
- Description: Handles operations related to car categories.
- Endpoints:
  - `GET /v1/category?category={category}`: Retrieves a list of cars by the specified category.
  - `GET /v1/category`: Retrieves a list of all available car categories.

## CarController

- Endpoint: `/v1/cars`
- Description: Manages operations on cars.
- Endpoints:
  - `GET /v1/cars`: Retrieves a list of cars from the database.
  - `GET /v1/cars/api`: Retrieves a list of cars from an external API.
  - `GET /v1/cars/available`: Retrieves a list of available cars.
  - `GET /v1/cars/{carId}`: Retrieves a specific car based on the ID.
  - `GET /v1/cars?model={model}`: Retrieves a car by model.
  - `POST /v1/cars`: Saves a new car.
  - `PUT /v1/cars/{carId}`: Updates an existing car.
  - `DELETE /v1/cars/{carId}`: Deletes a car.

## CustomerController

- Endpoint: `/v1/customers`
- Description: Manages operations on customers.
- Endpoints:
  - `GET /v1/customers`: Retrieves a list of customers.
  - `GET /v1/customers/{customerId}`: Retrieves a specific customer based on the ID.
  - `GET /v1/customers?lastName={lastName}`: Retrieves a list of customers by last name.
  - `GET /v1/customers?firstName={firstName}`: Retrieves a list of customers by first name.
  - `GET /v1/customers?phoneNumber={phoneNumber}`: Retrieves a list of customers by phone number.
  - `POST /v1/customers`: Creates a new customer.
  - `PUT /v1/customers/{customerId}`: Updates an existing customer.
  - `DELETE /v1/customers/{customerId}`: Deletes a customer.

## DamagePenaltyController

- Endpoint: `/v1/damage-penalties`
- Description: Handles operations related to damage penalties.
- Endpoints:
  - `POST /v1/damage-penalties`: Creates a new damage penalty for a rental.
  - `GET /v1/damage-penalties`: Retrieves a list of all damage penalties.
  - `GET /v1/damage-penalties/{id}`: Retrieves a specific damage penalty based on the ID.
  - `DELETE /v1/damage-penalties/{id}`: Deletes a damage penalty based on the ID.

## ExchangeController

- Endpoint: `/v1/exchange`
- Description: Handles operations related to currency exchange rates.
- Endpoints:
  - `GET /v1/exchange`: Retrieves the current exchange rates.

## RentalController

- Endpoint: `/v1/rentals`
- Description: Manages operations on rentals.
- Endpoints:
  - `GET /v1/rentals`: Retrieves a list of all rentals.
  - `GET /v1/rentals/{rentalId}`: Retrieves a specific rental based on the ID.
  - `GET /v1/rentals?customerId={customerId}`: Retrieves a list of rentals for a specific customer based on the customer ID.
  - `POST /v1/rentals`: Creates a new rental.
  - `POST /v1/rentals/{rentalId}/add-damage`: Adds a damage penalty to a rental.
  - `POST /v1/rentals/{rentalId}/remove-damage`: Removes a damage penalty from a rental.
  - `PUT /v1/rentals/{id}`: Updates an existing rental.
  - `DELETE /v1/rentals/{rentalId}`: Deletes a rental.

## TransmissionTypeController

- Endpoint: `/v1/transmission`
- Description: Manages operations on transmission types.
- Endpoints:
  - `GET /v1/transmission`: Retrieves a list of all transmission types.
  - `GET /v1/transmission/cars?transmissionTypeId={transmissionTypeId}`: Retrieves a list of cars based on a specific transmission type.
