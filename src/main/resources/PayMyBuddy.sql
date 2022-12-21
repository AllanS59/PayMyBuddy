/* PAY MY BUDDY */
/*-------------------------------------------------------- */
/* Setting up DB */
/*-------------------------------------------------------- */
DROP DATABASE paymybuddy ;
CREATE DATABASE paymybuddy;
USE paymybuddy;

SET @@global.time_zone = '+02:00';

/*-------------------------------------------------------- */
/* CREATING TABLES */
/*-------------------------------------------------------- */
CREATE TABLE `user` (
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(256) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`email`)
) ;

CREATE TABLE `assoc_contacts` (
  `email_user` VARCHAR(100) NOT NULL,
  `email_contact` VARCHAR(100) NOT NULL,
  FOREIGN KEY (`email_user`) REFERENCES  `user`(`email`),
  FOREIGN KEY (`email_contact`) REFERENCES  `user`(`email`),
  PRIMARY KEY(`email_user`, `email_contact`)
) ;

CREATE TABLE `account` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `email_user` VARCHAR(100) NOT NULL,
  `balance_Checkpoint` FLOAT,
  `date_Checkpoint` DATETIME NOT NULL,
  `type_account` VARCHAR(10) NOT NULL,
   FOREIGN KEY(`email_user`) REFERENCES `user`(`email`)
) ;

CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `account_sender` INT NOT NULL,
  `account_receiver` INT NOT NULL,
  `amount` FLOAT NOT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(150),
  `commission_Rate` FLOAT NOT NULL,
   FOREIGN KEY(`account_sender`) REFERENCES `account`(`id`),
   FOREIGN KEY(`account_receiver`) REFERENCES `account`(`id`)
) ;


