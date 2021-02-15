-- cda_voiture.categorie definition

CREATE TABLE `categorie` (
  `Id_Categorie` int NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`Id_Categorie`),
  UNIQUE KEY `Libelle` (`Libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.marque definition

CREATE TABLE `marque` (
  `Id_Marque` int NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`Id_Marque`),
  UNIQUE KEY `Libelle` (`Libelle`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.modele definition

CREATE TABLE `modele` (
  `Id_Modele` int NOT NULL AUTO_INCREMENT,
  `Libelle` varchar(20) DEFAULT NULL,
  `Id_Marque` int NOT NULL,
  PRIMARY KEY (`Id_Modele`),
  UNIQUE KEY `Libelle` (`Libelle`),
  KEY `Id_Marque` (`Id_Marque`),
  CONSTRAINT `modele_ibfk_1` FOREIGN KEY (`Id_Marque`) REFERENCES `marque` (`Id_Marque`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.reference definition

CREATE TABLE `reference` (
  `Id_Reference` varchar(10) NOT NULL,
  `Libelle` varchar(50) DEFAULT NULL,
  `Prix` decimal(5,2) DEFAULT NULL,
  `Id_Categorie` int NOT NULL,
  PRIMARY KEY (`Id_Reference`),
  KEY `Id_Categorie` (`Id_Categorie`),
  CONSTRAINT `reference_ibfk_1` FOREIGN KEY (`Id_Categorie`) REFERENCES `categorie` (`Id_Categorie`),
  CONSTRAINT `reference_chk_1` CHECK ((char_length(`Id_Reference`) >= 6))
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.vehicule definition

CREATE TABLE `vehicule` (
  `Id_Vehicule` int NOT NULL AUTO_INCREMENT,
  `Annee` smallint DEFAULT NULL,
  `Id_Marque` int NOT NULL,
  `Id_Modele` int NOT NULL,
  PRIMARY KEY (`Id_Vehicule`),
  KEY `Id_Marque` (`Id_Marque`),
  KEY `Id_Modele` (`Id_Modele`),
  CONSTRAINT `vehicule_ibfk_1` FOREIGN KEY (`Id_Marque`) REFERENCES `marque` (`Id_Marque`),
  CONSTRAINT `vehicule_ibfk_2` FOREIGN KEY (`Id_Modele`) REFERENCES `modele` (`Id_Modele`)
  constraint MarqueModelAnnee unique (Annee,Id_Marque,Id_Modele);
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.piece definition

CREATE TABLE `piece` (
  `Id_Piece` int NOT NULL AUTO_INCREMENT,
  `Date_Extraction` date DEFAULT NULL,
  `Id_Reference` varchar(10) NOT NULL,
  `Id_Vehicule` int NOT NULL,
  PRIMARY KEY (`Id_Piece`),
  KEY `Id_Reference` (`Id_Reference`),
  KEY `Id_Vehicule` (`Id_Vehicule`),
  CONSTRAINT `piece_ibfk_1` FOREIGN KEY (`Id_Reference`) REFERENCES `reference` (`Id_Reference`),
  CONSTRAINT `piece_ibfk_2` FOREIGN KEY (`Id_Vehicule`) REFERENCES `vehicule` (`Id_Vehicule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.referencevehicule definition

CREATE TABLE `referencevehicule` (
  `Id_Reference` varchar(10) NOT NULL,
  `Id_Vehicule` int NOT NULL,
  PRIMARY KEY (`Id_Reference`,`Id_Vehicule`),
  KEY `Id_Vehicule` (`Id_Vehicule`),
  CONSTRAINT `referencevehicule_ibfk_1` FOREIGN KEY (`Id_Reference`) REFERENCES `piece` (`Id_Reference`),
  CONSTRAINT `referencevehicule_ibfk_2` FOREIGN KEY (`Id_Vehicule`) REFERENCES `vehicule` (`Id_Vehicule`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


-- cda_voiture.transactions definition

CREATE TABLE `transactions` (
  `Id_Vente` int NOT NULL AUTO_INCREMENT,
  `Date_Vente` date DEFAULT NULL,
  `Date_Recuperation` date DEFAULT NULL,
  `Id_Piece` int NOT NULL,
  PRIMARY KEY (`Id_Vente`),
  KEY `Id_Piece` (`Id_Piece`),
  CONSTRAINT `transactions_ibfk_1` FOREIGN KEY (`Id_Piece`) REFERENCES `piece` (`Id_Piece`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
