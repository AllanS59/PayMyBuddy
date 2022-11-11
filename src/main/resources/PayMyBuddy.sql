/* PAY MY BUDDY */
/*-------------------------------------------------------- */
/* Setting up DB */
/*-------------------------------------------------------- */
DROP DATABASE paymybuddy ;
CREATE DATABASE paymybuddy;
USE paymybuddy;

/*-------------------------------------------------------- */
/* CREATING TABLES */
/*-------------------------------------------------------- */
CREATE TABLE `user` (
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `first_name` VARCHAR(20) NOT NULL,
  `last_name` VARCHAR(20) NOT NULL,
  `address` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`email`)
) ;

CREATE TABLE `assoc_contacts` (
  `email_user` VARCHAR(100) NOT NULL,
  `email_contact` VARCHAR(100) NOT NULL,
  FOREIGN KEY (`email_user`) REFERENCES  `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  FOREIGN KEY (`email_contact`) REFERENCES  `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE,
  PRIMARY KEY(`email_user`, `email_contact`)
) ;

CREATE TABLE `account` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `email_user` VARCHAR(100) NOT NULL,
  `balance_Checkpoint` FLOAT,
  `date_Checkpoint` DATETIME NOT NULL,
  `type_account` VARCHAR(10) NOT NULL,
   FOREIGN KEY(`email_user`) REFERENCES `user`(`email`) ON DELETE CASCADE ON UPDATE CASCADE
) ;

CREATE TABLE `transactions` (
  `id` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `account_sender` INT NOT NULL,
  `account_receiver` INT NOT NULL,
  `amount` FLOAT NOT NULL,
  `date` DATETIME NOT NULL,
  `description` VARCHAR(150),
  `commission_Rate` FLOAT NOT NULL,
   FOREIGN KEY(`account_sender`) REFERENCES `account`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
   FOREIGN KEY(`account_receiver`) REFERENCES `account`(`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ;


/*-------------------------------------------------------- */
/* INITIALIZING SYSTEM ACCOUNTS (fees, app, main accounts) */
/*-------------------------------------------------------- */
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("admin@mail.com","passadmin","Allan","Admin","1 rue Admin 75000 Paris","01-01-01-01-01");
insert into `account`( `email_user`,`balance_Checkpoint`, `date_Checkpoint`, `type_account`) values("admin@mail.com",1000000,"2022-10-10 10:00:00","SYSTEM");
insert into `account`( `email_user`,`balance_Checkpoint`, `date_Checkpoint`, `type_account`) values("admin@mail.com",10000000,"2022-10-10 10:00:00","SYSTEM");
insert into `account`( `email_user`,`balance_Checkpoint`, `date_Checkpoint`, `type_account`) values("admin@mail.com",0,"2022-10-10 10:00:00","SYSTEM");

/*-------------------------------------------------------- */
/* CREATING SOME TEST DATA */
/*-------------------------------------------------------- */
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("jdupont@mail.com","pass1234","Jean","Dupont","4 rue LaRue 50000 Ville","06-07-08-09-10");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("mdupont@mail.com","pass4567","Marie","Dupont","4 rue LaRue 50000 Ville","06-05-04-03-02");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("kdupont@mail.com","pass1357","Kevin","Dupont","4 rue LaRue 50000 Ville","06-15-14-13-12");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("bmartin@mail.com","password1","Bernard","Martin","12 rue AutreRue 75000 City","06-99-98-17-72");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("mmartin@mail.com","password1","Maeva","Martin","12 rue AutreRue 75000 City","06-00-78-87-74");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("ysalim@mail.com","pass7234","Yacine","Salim","5 avenue Lavenue 55400 Village","04-57-68-02-99");
insert into `user` (`email`,`password`,`first_name`,`last_name`,`address`,`phone`) values ("sshoto@mail.com","password34","Sakura","Shoto","14 avenue Lavenue 55400 Village","07-56-88-12-99");

insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("jdupont@mail.com",500,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("mdupont@mail.com",500,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("kdupont@mail.com",40,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("bmartin@mail.com",200,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("mmartin@mail.com",200,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("ysalim@mail.com",70,"2022-10-10 10:00:00", "USER");
insert into `account` (`email_user`,`balance_Checkpoint`,`date_Checkpoint`, `type_account`) values ("sshoto@mail.com",70,"2022-10-10 10:00:00", "USER");

insert into `assoc_contacts` (`email_user`,`email_contact`) values ("jdupont@mail.com","mdupont@mail.com");
insert into `assoc_contacts` (`email_user`,`email_contact`) values ("jdupont@mail.com","kdupont@mail.com");
insert into `assoc_contacts` (`email_user`,`email_contact`) values ("jdupont@mail.com","bmartin@mail.com");
insert into `assoc_contacts` (`email_user`,`email_contact`) values ("kdupont@mail.com","jdupont@mail.com");
insert into `assoc_contacts` (`email_user`,`email_contact`) values ("kdupont@mail.com","ysalim@mail.com");
insert into `assoc_contacts` (`email_user`,`email_contact`) values ("kdupont@mail.com","sshoto@mail.com");

insert into `transactions` (`account_sender`,`account_receiver`,`amount`,`date`,`description`,`commission_Rate`)values (6, 9, 20,"2022-10-11 14:00:00", "virement 20e",0.5);

