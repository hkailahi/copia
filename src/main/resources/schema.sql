DROP TABLE IF EXISTS pickups;
DROP TABLE IF EXISTS recipients;
DROP TABLE IF EXISTS matches;

CREATE TABLE IF NOT EXISTS pickups (
  FirstName  VARCHAR(32),
  LastName   VARCHAR(32),
  Street     VARCHAR(100),
  City       VARCHAR(100),
  State      VARCHAR(32),
  Postal     INT,
  Country    VARCHAR(32),
  Email      VARCHAR(100),
  Phone      VARCHAR(12),
  Latitude   DOUBLE PRECISION,
  Longitude  DOUBLE PRECISION,
  Categories INT,
  PickupAt   TIMESTAMP,
  TimeZoneId VARCHAR(50)
)
AS SELECT *
FROM CSVREAD('classpath:Pickups.csv');

ALTER TABLE pickups ADD IF NOT EXISTS PickupId int auto_increment;

CREATE TABLE IF NOT EXISTS recipients (
  FirstName VARCHAR(32),
  LastName VARCHAR(32),
  Street VARCHAR(100),
  City VARCHAR(100),
  State VARCHAR(32),
  Postal INT,
  Country VARCHAR(32),
  Email VARCHAR(100),
  Phone VARCHAR(12),
  Latitude DOUBLE PRECISION,
  Longitude DOUBLE PRECISION,
  Restrictions INT,
  Sunday INT,
  Monday INT,
  Tuesday INT,
  Wednesday INT,
  Thursday INT,
  Friday INT,
  Saturday INT
)
AS SELECT *
FROM CSVREAD('classpath:Recipients.csv');

ALTER TABLE recipients ADD IF NOT EXISTS RecipientId int auto_increment;

CREATE TABLE IF NOT EXISTS matches (
  MatchId  BIGINT auto_increment,
  Deliveries INT,
  Distance DOUBLE PRECISION,
  PickupId INT,
  RecipientId1 INT,
  RecipientId2 INT,
  RecipientId3 INT,
  RecipientId4 INT,
  RecipientId5 INT,
  RecipientId6 INT
);