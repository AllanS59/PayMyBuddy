#PAY MY BUDDY
##Application for easy transfer of money
---
###Main features
+ Add money to your account
+ Withdraw money from your account
+ Sent money to another user amount your contact list (0.5% fees per transaction)
+ Add other users to your contact list

---
###Main vocabulary
+ **USER**: Each user of the application. THey are defined by several classic data (mail, password, name, address...)
+ **ACCOUNT** Each lambda user will have one account (AccountType = USER) defined by an balance (amount of money available on the account) and a checkpoint date (date of the last update of the account).
        The update date is used to limit the number of updates (only where it is required by a new transaction).
        There is also 3 accounts defined for Admin (AccountType = ADMIN):
            - Appro account (from where money will be get/set when a user add/remove money from his account
            -Ope account (in case Appro account needs more money)
            - Fees account (where all commission of transactions will be sent)
+ **TRANSACTION**: It is each transfer of money between two accounts. It is defined by the sender account , the receiver account, the amount transferred, the date of transfer, a description, and the fees to apply on the transaction
**ASSOC_CONTACT**: Each user have some 'contacts' to whom he can send money. Each relation is defined by Assoc_Contact.

---
###Class Diagram
![Class Diagram](https://github.com/AllanS59/PayMyBuddy/blob/master/diagrammes/Diagrammes_Chap_6-Diagramme_de_classe.png?raw=true)

---
###Relationship model
![Relationship model](https://github.com/AllanS59/PayMyBuddy/blob/master/diagrammes/Diagrammes_Chap_6-modele_relationnel.png?raw=true)

---
###Package Diagramm
![Relationship model](https://github.com/AllanS59/PayMyBuddy/blob/master/diagrammes/Diagrammes_Chap_6-Diagramme_de_package.png?raw=true)

