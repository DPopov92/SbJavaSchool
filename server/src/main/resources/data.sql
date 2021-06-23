DROP TABLE IF EXISTS Balance;
DROP TABLE IF EXISTS Cards;
DROP TABLE IF EXISTS Accounts;
DROP TABLE IF EXISTS Clients;

CREATE TABLE Clients (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR,
  surname VARCHAR
);

CREATE TABLE Accounts (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  balance int DEFAULT 0,
  client_id  INT NOT NULL,
    foreign key (client_id) references Clients(id),
  number VARCHAR NOT NULL
);

CREATE TABLE Cards (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  owner_Name VARCHAR,
  owner_Surname VARCHAR,
  pin INT NOT NULL,
  account_id  INT NOT NULL,
      foreign key (account_id) references Accounts(id),
  number VARCHAR NOT NULL
);

CREATE TABLE Balance (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  amount INT NOT NULL,
  currency VARCHAR,
  account_id  INT NOT NULL,
      foreign key (account_id) references Accounts(id)
);


INSERT INTO Clients (name,surname) VALUES
  ('Вася','Ложкин');


INSERT INTO Accounts (balance,client_id,number) VALUES
  (123,1,'123');

INSeRT INTO Cards (owner_Name,owner_Surname,pin,account_id,number) VALUES
   ('Вася', 'Ложкин', 777, 1, '12345');

INSERT INTO Balance(amount,currency,account_id) VALUES
    (828382, 'RUB', 1)