CREATE TABLE Marque(
   Id_Marque INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Libelle VARCHAR(30),
   UNIQUE(Libelle)
);
CREATE TABLE Reference(
   Id_Reference VARCHAR(10) PRIMARY key not NULL,
   Libelle VARCHAR(50),
   Prix DECIMAL(5,2),
   Id_Categorie INT NOT NULL,
   check (Char_Length(Id_Reference)>=6),
   FOREIGN KEY(Id_Categorie) REFERENCES Categorie(Id_Categorie)
);
CREATE TABLE Modele(
   Id_Modele INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Libelle VARCHAR(20),
   Id_Marque INT not null,
   UNIQUE(Libelle),
   foreign key (Id_Marque) references Marque(Id_Marque)
);
CREATE TABLE Categorie(
   Id_Categorie INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Libelle VARCHAR(20),
   UNIQUE(Libelle)
);
CREATE TABLE Vehicule(
   Id_Vehicule INT PRIMARY KEY NOT null AUTO_INCREMENT,
   Annee SMALLINT,
   Id_Marque INT NOT NULL,
   Id_Modele INT NOT NULL,
   FOREIGN KEY(Id_Marque) REFERENCES Marque(Id_Marque),
   FOREIGN KEY(Id_Modele) REFERENCES Modele(Id_Modele)
);
CREATE TABLE Piece(
   Id_Piece INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Date_Extraction Date,
   Id_Reference VARCHAR(10) not null,
   Id_Vehicule INT NOT NULL,
   foreign key (Id_Reference) references Reference(Id_Reference),
   FOREIGN KEY(Id_Vehicule) REFERENCES Vehicule(Id_Vehicule)
);
CREATE TABLE ReferenceVehicule(
   Id_Reference VARCHAR(10),
   Id_Vehicule INT,
   PRIMARY KEY(Id_Reference, Id_Vehicule),
   FOREIGN KEY(Id_Reference) REFERENCES Piece(Id_Reference),
   FOREIGN KEY(Id_Vehicule) REFERENCES Vehicule(Id_Vehicule)
);
CREATE TABLE Transactions(
   Id_Vente INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
   Date_Vente DATE,
   Date_Recuperation DATE,
   Id_Piece INT NOT NULL,
   FOREIGN KEY(Id_Piece) REFERENCES Piece(Id_Piece)
);
Insert into Marque(Libelle) values ('Acura'),('Alfa_Romeo'),('Alpine'),('Aston_Martin'),('Auburn'),('Audi'),('Austin'),('Bentley'),('BMW'),('Bugatti'),('Cadillac'),('Chevrolet'),('Chrysler'),('Citroën'),('Dacia'),('Dodge'),('Ferrari'),('FIAT'),('Ford'),('GAZ'),('GMC'),('Honda'),('Hummer'),('Hyundai'),('Jaguar'),('Jeep'),('Kia'),('Koenigsegg'),('Lamborghini'),('Lancia'),('Land_Rover'),('Lexus'),('Lincoln'),('Lotus'),('Maserati'),('Matra'),('Mazda'),('McLaren'),('Mercedes_Benz'),('Mini'),('Mitsubishi'),('Nissan'),('Opel'),('Peugeot'),('Pontiac'),('Porsche'),('Renault'),('Rolls_Royce'),('Rover'),('Saab'),('Seat'),('Shelby'),('Skoda'),('Smart'),('Subaru'),('Suzuki'),('Tesla'),('Toyota'),('Triumph'),('Volkswagen'),('Volvo');
Insert into Modele(Libelle, Id_Marque) values ('DB9',4),('206',44),('306',44),('406',44),('506',44),('606',44),('C1',14),('C2',14),('C3',14),('C4',14),('C5',14),('Berlingo',14),('A1',6),('A2',6),('A3',6),('A4',6),('A5',6),('A6',6),('TT',6),('Giulia',2),('Stelvio',2),('Giulietta',2),('911',46),('Panda',18),('Punto',18),('500',18),('Serie_1',9),('Serie_2',9),('Serie_3',9),('X1',9),('X2',9),('X3',9),('Model_S',57),('Model_3',57),('Polo',60),('Passat',60),('Tiguan',60),('Touran',60),('Corsa',43),('Astra',43),('Omega',43);
Insert into Categorie(Libelle) values ('Moteur'),('Echappement'),('Eclairage'),('Climatisation'),('Chauffage'),('Freinage'),('Direction'),('Carrosserie'),('Electronique');
insert into reference(Id_Reference,Libelle,Prix,Id_Categorie) values ('A75W12','Ampoule Clignotant 75W',150,3),('B85G12','Bougie de préchauffage',75,1),('DF26AZ','Disque de frein diametre 26"',200,6);
Insert into Vehicule(Annee,Id_Marque ,Id_Modele) values (2020,'6','13'),(2020,'6','14'),(2020,'6','15'),(2020,'6','16'),(2020,'6','17'),(2020,'6','19'),(2021,'6','13'),(2021,'6','14'),(2021,'6','15'),(2021,'6','16'),(2021,'6','17'),(2021,'6','19'),(2019,'14','7'),(2019,'14','8'),(2019,'14','9'),(2019,'14','10'),(2018,'14','1'),(2018,'14','12'),(2020, '57','33'),(2020, '57','34');
insert into Piece(Date_Extraction,Id_Reference,Id_Vehicule) values (STR_TO_DATE('7,1,2021','%d,%m,%Y'),'A75W12',1),(STR_TO_DATE('7,1,2021','%d,%m,%Y'),'B85G12',1),(STR_TO_DATE('7,1,2021','%d,%m,%Y'),'DF26AZ',2);
insert into transactions(Date_Recuperation,Date_Vente,Id_Piece) values (STR_TO_DATE('7,2,2021','%d,%m,%Y'),STR_TO_DATE('6,2,2021','%d,%m,%Y'),3),(STR_TO_DATE('7,2,2020','%d,%m,%Y'),STR_TO_DATE('6,2,2020','%d,%m,%Y'),1),(STR_TO_DATE('7,6,2020','%d,%m,%Y'),STR_TO_DATE('6,6,2020','%d,%m,%Y'),2);
insert into ReferenceVehicule(Id_Vehicule,Id_Reference) values (1,'A75W12'),(2,'A75W12'),(3,'A75W12'),(4,'A75W12'),(5,'A75W12'),(6,'A75W12'),(7,'A75W12'),(8,'A75W12'),(9,'A75W12'),(10,'A75W12'),(11,'A75W12'),(12,'A75W12');