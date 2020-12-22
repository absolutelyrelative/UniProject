-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema SimpleBooking
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema SimpleBooking
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `SimpleBooking` DEFAULT CHARACTER SET utf8 ;
USE `SimpleBooking` ;

-- -----------------------------------------------------
-- Table `SimpleBooking`.`Utente`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Utente` (
  `idUtente` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `username` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idUtente`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Amministratore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Amministratore` (
  `idAmministratore` INT NOT NULL AUTO_INCREMENT,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idAmministratore`, `Utente_idUtente`),
  UNIQUE INDEX `idAmministratore_UNIQUE` (`idAmministratore` ASC) VISIBLE,
  INDEX `fk_Amministratore_Utente1_idx` (`Utente_idUtente` ASC) VISIBLE,
  CONSTRAINT `fk_Amministratore_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `SimpleBooking`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Compratore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Compratore` (
  `idCompratore` INT NOT NULL AUTO_INCREMENT,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idCompratore`, `Utente_idUtente`),
  UNIQUE INDEX `idCompratore_UNIQUE` (`idCompratore` ASC) VISIBLE,
  INDEX `fk_Compratore_Utente1_idx` (`Utente_idUtente` ASC) VISIBLE,
  CONSTRAINT `fk_Compratore_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `SimpleBooking`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Venditore`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Venditore` (
  `idVenditore` INT NOT NULL AUTO_INCREMENT,
  `Utente_idUtente` INT NOT NULL,
  PRIMARY KEY (`idVenditore`, `Utente_idUtente`),
  UNIQUE INDEX `idVenditore_UNIQUE` (`idVenditore` ASC) VISIBLE,
  INDEX `fk_Venditore_Utente1_idx` (`Utente_idUtente` ASC) VISIBLE,
  CONSTRAINT `fk_Venditore_Utente1`
    FOREIGN KEY (`Utente_idUtente`)
    REFERENCES `SimpleBooking`.`Utente` (`idUtente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Ordine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Ordine` (
  `idOrdine` INT NOT NULL AUTO_INCREMENT,
  `Compratore_idCompratore` INT NOT NULL,
  `Importo_Tot` FLOAT NOT NULL DEFAULT 0,
  PRIMARY KEY (`idOrdine`),
  UNIQUE INDEX `idOrdine_UNIQUE` (`idOrdine` ASC) VISIBLE,
  INDEX `fk_Ordine_Compratore1_idx` (`Compratore_idCompratore` ASC) VISIBLE,
  CONSTRAINT `fk_Ordine_Compratore1`
    FOREIGN KEY (`Compratore_idCompratore`)
    REFERENCES `SimpleBooking`.`Compratore` (`idCompratore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Tipo_Bene`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Tipo_Bene` (
  `idTipo Bene` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Amministratore_idAmministratore` INT NOT NULL,
  PRIMARY KEY (`idTipo Bene`),
  UNIQUE INDEX `idTipo Bene_UNIQUE` (`idTipo Bene` ASC) VISIBLE,
  INDEX `fk_Tipo Bene_Amministratore1_idx` (`Amministratore_idAmministratore` ASC) VISIBLE,
  CONSTRAINT `fk_Tipo Bene_Amministratore1`
    FOREIGN KEY (`Amministratore_idAmministratore`)
    REFERENCES `SimpleBooking`.`Amministratore` (`idAmministratore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Beni`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Beni` (
  `idBeni` INT NOT NULL AUTO_INCREMENT,
  `Nome` VARCHAR(45) NOT NULL,
  `Descrizione` VARCHAR(255) NULL,
  `Data_Inizio` DATE NOT NULL,
  `Data_Fine` DATE NOT NULL,
  `Costo_pw` FLOAT NULL,
  `Costo_pm` FLOAT NULL,
  `Costo_pd` FLOAT NOT NULL,
  `GPS_Lat` FLOAT NULL,
  `GPS_Lon` FLOAT NULL,
  `Addr` VARCHAR(128) NOT NULL,
  `Venditore_idVenditore` INT NOT NULL,
  `Tipo_Bene_idTipo_Bene` INT NOT NULL,
  `Stato_Bene` TINYINT NOT NULL DEFAULT 0 COMMENT 'Stato del bene:\n0 - Non Approvato da Amministratore\n1 - Approvato da Amministratore\n',
  `Pubblicazione` TINYINT NOT NULL DEFAULT 0 COMMENT '0 - Non pubblicato\n1 - Pubblicato',
  `Amministratore_idAmministratore` INT NULL,
  PRIMARY KEY (`idBeni`),
  UNIQUE INDEX `idBeni_UNIQUE` (`idBeni` ASC) VISIBLE,
  INDEX `fk_Beni_Venditore1_idx` (`Venditore_idVenditore` ASC) VISIBLE,
  INDEX `fk_Beni_Tipo Bene1_idx` (`Tipo_Bene_idTipo_Bene` ASC) VISIBLE,
  INDEX `fk_Beni_Amministratore1_idx` (`Amministratore_idAmministratore` ASC) VISIBLE,
  CONSTRAINT `fk_Beni_Venditore1`
    FOREIGN KEY (`Venditore_idVenditore`)
    REFERENCES `SimpleBooking`.`Venditore` (`idVenditore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Beni_Tipo Bene1`
    FOREIGN KEY (`Tipo_Bene_idTipo_Bene`)
    REFERENCES `SimpleBooking`.`Tipo_Bene` (`idTipo Bene`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Beni_Amministratore1`
    FOREIGN KEY (`Amministratore_idAmministratore`)
    REFERENCES `SimpleBooking`.`Amministratore` (`idAmministratore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Immagine`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Immagine` (
  `idImmagine` INT NOT NULL AUTO_INCREMENT,
  `Data` BLOB NULL,
  `Beni_idBeni` INT NOT NULL,
  PRIMARY KEY (`idImmagine`),
  UNIQUE INDEX `idImmagine_UNIQUE` (`idImmagine` ASC) VISIBLE,
  INDEX `fk_Immagine_Beni1_idx` (`Beni_idBeni` ASC) VISIBLE,
  CONSTRAINT `fk_Immagine_Beni1`
    FOREIGN KEY (`Beni_idBeni`)
    REFERENCES `SimpleBooking`.`Beni` (`idBeni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Rating`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Rating` (
  `idRating` INT NOT NULL AUTO_INCREMENT,
  `Rating` TINYINT(1) NULL DEFAULT 1,
  `Beni_idBeni` INT NOT NULL,
  `Compratore_idCompratore` INT NOT NULL,
  PRIMARY KEY (`idRating`),
  INDEX `fk_Feedback_Beni1_idx` (`Beni_idBeni` ASC) VISIBLE,
  INDEX `fk_Rating_Compratore1_idx` (`Compratore_idCompratore` ASC) VISIBLE,
  CONSTRAINT `fk_Feedback_Beni1`
    FOREIGN KEY (`Beni_idBeni`)
    REFERENCES `SimpleBooking`.`Beni` (`idBeni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Rating_Compratore1`
    FOREIGN KEY (`Compratore_idCompratore`)
    REFERENCES `SimpleBooking`.`Compratore` (`idCompratore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Feedback`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Feedback` (
  `idFeedback` INT NOT NULL AUTO_INCREMENT,
  `Commento` VARCHAR(150) NULL,
  `Feedback_idFeedback` INT NULL,
  `Beni_idBeni` INT NOT NULL,
  `Compratore_idCompratore` INT NULL,
  `Venditore_idVenditore` INT NULL,
  PRIMARY KEY (`idFeedback`),
  UNIQUE INDEX `idFeedback_UNIQUE` (`idFeedback` ASC) VISIBLE,
  INDEX `fk_Feedback_Feedback1_idx` (`Feedback_idFeedback` ASC) VISIBLE,
  INDEX `fk_Feedback_Beni2_idx` (`Beni_idBeni` ASC) VISIBLE,
  INDEX `fk_Feedback_Compratore1_idx` (`Compratore_idCompratore` ASC) VISIBLE,
  INDEX `fk_Feedback_Venditore1_idx` (`Venditore_idVenditore` ASC) VISIBLE,
  CONSTRAINT `fk_Feedback_Feedback1`
    FOREIGN KEY (`Feedback_idFeedback`)
    REFERENCES `SimpleBooking`.`Feedback` (`idFeedback`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Beni2`
    FOREIGN KEY (`Beni_idBeni`)
    REFERENCES `SimpleBooking`.`Beni` (`idBeni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Compratore1`
    FOREIGN KEY (`Compratore_idCompratore`)
    REFERENCES `SimpleBooking`.`Compratore` (`idCompratore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Feedback_Venditore1`
    FOREIGN KEY (`Venditore_idVenditore`)
    REFERENCES `SimpleBooking`.`Venditore` (`idVenditore`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Line_Item`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Line_Item` (
  `idLine Item` INT NOT NULL AUTO_INCREMENT,
  `Ordine_idOrdine` INT NOT NULL,
  `Beni_idBeni` INT NOT NULL,
  `Data_sel_Inizio` DATE NOT NULL,
  `Data_sel_Fine` DATE NOT NULL,
  PRIMARY KEY (`idLine Item`),
  INDEX `fk_Line Item_Ordine1_idx` (`Ordine_idOrdine` ASC) VISIBLE,
  INDEX `fk_Line Item_Beni1_idx` (`Beni_idBeni` ASC) VISIBLE,
  CONSTRAINT `fk_Line Item_Ordine1`
    FOREIGN KEY (`Ordine_idOrdine`)
    REFERENCES `SimpleBooking`.`Ordine` (`idOrdine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Line Item_Beni1`
    FOREIGN KEY (`Beni_idBeni`)
    REFERENCES `SimpleBooking`.`Beni` (`idBeni`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `SimpleBooking`.`Pagamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `SimpleBooking`.`Pagamento` (
  `idPagamento` INT NOT NULL AUTO_INCREMENT,
  `Stato` TINYINT NOT NULL DEFAULT 0,
  `Ordine_idOrdine` INT NOT NULL,
  `Numero_Carta` VARCHAR(45) NOT NULL,
  `CVV` VARCHAR(45) NOT NULL,
  `PIN` VARCHAR(45) NOT NULL,
  `Importo` FLOAT NOT NULL,
  PRIMARY KEY (`idPagamento`),
  INDEX `fk_Pagamento_Ordine1_idx` (`Ordine_idOrdine` ASC) VISIBLE,
  CONSTRAINT `fk_Pagamento_Ordine1`
    FOREIGN KEY (`Ordine_idOrdine`)
    REFERENCES `SimpleBooking`.`Ordine` (`idOrdine`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;