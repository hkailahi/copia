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
-- FROM CSVREAD('classpath:Backend Challenge/Pickups.csv');
-- https://stackoverflow.com/questions/5474665/load-csv-file-located-in-the-classpath-for-h2-database
-- https://stackoverflow.com/questions/4784859/how-to-access-csv-file-within-war-using-h2s-csvread-function-query/4790910#4790910
FROM CSVREAD('/Users/hkailahi/dev/scrap/src/main/resources/Backend Challenge/Pickups.csv');

-- https://stackoverflow.com/questions/30596150/how-to-use-a-path-relative-to-project-root-to-h2-db-file-configuration-with-play

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
-- FROM CSVREAD('classpath:Recipients.csv');
FROM CSVREAD('/Users/hkailahi/dev/scrap/src/main/resources/Backend Challenge/Recipients.csv');

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