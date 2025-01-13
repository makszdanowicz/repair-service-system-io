USE repair_service;

CREATE TABLE users ( 
	user_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(8) NOT NULL,
    role ENUM('CLIENT', 'TECHNICIAN', 'ADMIN') NOT NULL
);

CREATE TABLE clients(
	user_id INT PRIMARY KEY,
    personal_data VARCHAR(50) NOT NULL,
    address VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL,
    FOREIGN KEY(user_id) REFERENCES users(user_id)
);

CREATE TABLE requests(
	request_id INT AUTO_INCREMENT PRIMARY KEY,
    issue_description VARCHAR(255) NOT NULL,
    device_model VARCHAR(50) NOT NULL,
    client_id INT NOT NULL,
    status ENUM('NEW', 'PENDING', 'ASSIGNED', 'IN_PROGRESS', 'COMPLETED', 'CLOSED') 
    NOT NULL DEFAULT 'NEW',
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(client_id) REFERENCES clients(user_id)
);

CREATE TABLE technicians(
	user_id INT PRIMARY KEY,
    personal_data VARCHAR(50) NOT NULL,
    availability BOOLEAN NOT NULL DEFAULT TRUE,
    request_id INT UNIQUE,
    FOREIGN KEY(user_id) REFERENCES users(user_id),
    FOREIGN KEY (request_id) REFERENCES requests(request_id)
);

CREATE TABLE reviews(
	review_id INT AUTO_INCREMENT PRIMARY KEY,
    rate INT NOT NULL,
    opinion VARCHAR(255),
    request_id INT,
    date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(request_id) REFERENCES requests(request_id)
);