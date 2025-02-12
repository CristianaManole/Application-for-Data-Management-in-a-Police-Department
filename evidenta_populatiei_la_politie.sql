-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Gazdă: 127.0.0.1
-- Timp de generare: ian. 16, 2025 la 07:20 PM
-- Versiune server: 10.4.32-MariaDB
-- Versiune PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Bază de date: `evidenta_populatiei_la_politie`
--

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `adrese`
--

CREATE TABLE `adrese` (
  `ID_Adresa` int(11) NOT NULL,
  `Strada` varchar(50) NOT NULL,
  `Numar` int(11) NOT NULL,
  `Oras` varchar(50) NOT NULL,
  `Judet` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `adrese`
--

INSERT INTO `adrese` (`ID_Adresa`, `Strada`, `Numar`, `Oras`, `Judet`) VALUES
(1, 'Bucuresti', 15, 'Bucuresti', 'Bucuresti'),
(3, 'Victor Hugo', 7, 'Cluj Napoca', 'Cluj'),
(4, 'Calea Mosilor', 41, 'București', 'București'),
(5, 'Strada Mare', 10, 'Timisoara', 'Timis'),
(6, 'Strada Avram Iancu', 50, 'Brasov', 'Brasov'),
(7, 'Strada Zorilor', 14, 'Constanta', 'Constanta'),
(8, 'Strada Calarasi', 30, 'Ploiesti', 'Prahova'),
(9, 'Bdul 1 Decembrie', 99, 'Oradea', 'Bihor'),
(10, 'Strada Carpati', 3, 'Sibiu', 'Sibiu'),
(12, 'Aleea Barajul Dunarii', 60, 'Bucuresti', 'Sector 2');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `cetatenii`
--

CREATE TABLE `cetatenii` (
  `ID_Cetatenie` int(11) NOT NULL,
  `Tara` varchar(50) NOT NULL,
  `Nationalitate` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `cetatenii`
--

INSERT INTO `cetatenii` (`ID_Cetatenie`, `Tara`, `Nationalitate`) VALUES
(1, 'România', 'română'),
(2, 'Germania', 'germană'),
(3, 'Franța', 'franceză'),
(4, 'Italia', 'italiană'),
(5, 'Spania', 'spaniolă'),
(6, 'Marea Britanie', 'britanică'),
(7, 'SUA', 'americană'),
(8, 'Canada', 'canadiană'),
(9, 'Austria', 'austriacă');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `dosare`
--

CREATE TABLE `dosare` (
  `ID_Dosare` int(11) NOT NULL,
  `Numar_Dosar` int(11) NOT NULL,
  `Descriere` varchar(100) NOT NULL,
  `Data_inregistrarii` date NOT NULL,
  `ID_Ofiteri` int(11) NOT NULL,
  `ID_Persoana` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `dosare`
--

INSERT INTO `dosare` (`ID_Dosare`, `Numar_Dosar`, `Descriere`, `Data_inregistrarii`, `ID_Ofiteri`, `ID_Persoana`) VALUES
(21, 10001, 'Dosar de investigatie pentru furt', '2025-01-01', 1, 1),
(22, 10002, 'Dosar de investigatie pentru frauda', '2025-01-02', 2, 3),
(23, 10003, 'Dosar de investigatie pentru coruptie', '2025-01-03', 3, 4),
(24, 10004, 'Dosar de investigatie pentru omor', '2025-01-04', 4, 5),
(25, 10005, 'Dosar de investigatie pentru trafic de droguri', '2025-01-05', 5, 6),
(26, 10006, 'Dosar de investigatie pentru spalare de bani', '2025-01-06', 6, 7),
(27, 10007, 'Dosar de investigatie pentru inselaciune', '2025-01-07', 7, 8),
(28, 10008, 'Dosar de investigatie pentru abuz in serviciu', '2025-01-08', 8, 9),
(32, 10009, 'Dosar de investigatie pentru ucidere din culpa', '2024-06-12', 3, 5),
(33, 10010, 'Dosar de investigatie pentru furt', '2024-04-27', 8, 5);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `ofiteri`
--

CREATE TABLE `ofiteri` (
  `ID_Ofiteri` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Prenume` varchar(50) NOT NULL,
  `Grad` varchar(50) NOT NULL,
  `CNP` char(13) NOT NULL,
  `Sex` enum('M','F') NOT NULL DEFAULT 'M'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `ofiteri`
--

INSERT INTO `ofiteri` (`ID_Ofiteri`, `Nume`, `Prenume`, `Grad`, `CNP`, `Sex`) VALUES
(1, 'Popescu', 'Ionel', 'Comisar', '1850215001234', 'M'),
(2, 'Ionescu', 'Maria', 'Agent', '1900322004567', 'F'),
(3, 'Georgescu', 'Andrei', 'Subcomisar', '1780730007890', 'M'),
(4, 'Dumitrescu', 'Elena', 'Inspector', '1951110101234', 'F'),
(5, 'Stan', 'Vlad', 'Comisar', '1820605004567', 'M'),
(6, 'Pop', 'Ana', 'Agent', '1880917007890', 'F'),
(7, 'Marin', 'Cristian', 'Subcomisar', '1930120001234', 'M'),
(8, 'Tudor', 'Bianca', 'Inspector', '1801225004567', 'F'),
(9, 'Vasile', 'Mihai', 'Comisar', '1840411007890', 'M'),
(10, 'Balan', 'Ioana', 'Agent', '1920518001234', 'F');

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `persoane`
--

CREATE TABLE `persoane` (
  `ID_Persoana` int(11) NOT NULL,
  `Nume` varchar(50) NOT NULL,
  `Prenume` varchar(50) NOT NULL,
  `CNP` char(13) NOT NULL,
  `Sex` enum('M','F') NOT NULL DEFAULT 'F',
  `Data_nasterii` date DEFAULT NULL,
  `ID_Adresa` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `persoane`
--

INSERT INTO `persoane` (`ID_Persoana`, `Nume`, `Prenume`, `CNP`, `Sex`, `Data_nasterii`, `ID_Adresa`) VALUES
(1, 'Maria', 'Sorina', '1850215001234', 'F', '1985-02-15', 7),
(3, 'Georgescu', 'Andrei', '1780730007890', 'M', '1978-07-30', 3),
(4, 'Dumitrescu', 'Elena', '2951110101234', 'F', '1995-11-10', 4),
(5, 'Stan', 'Vlad', '1820605004567', 'M', '1982-06-05', 5),
(6, 'Pop', 'Ana', '2880917007890', 'F', '1988-09-17', 6),
(7, 'Marin', 'Cristian', '1930120001234', 'M', '1993-01-20', 7),
(8, 'Tudor', 'Bianca', '2801225004567', 'F', '1980-12-25', 8),
(9, 'Vasile', 'Mihai', '1840411007890', 'M', '1984-04-11', 9),
(10, 'Balan', 'Ioana', '2920518001234', 'F', '1992-05-18', 10),
(11, 'Manole', 'Cristiana', '6030924420054', 'F', '2003-09-24', 1);

-- --------------------------------------------------------

--
-- Structură tabel pentru tabel `persoanecetatenii`
--

CREATE TABLE `persoanecetatenii` (
  `ID_Persoana` int(11) NOT NULL,
  `ID_Cetatenie` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Eliminarea datelor din tabel `persoanecetatenii`
--

INSERT INTO `persoanecetatenii` (`ID_Persoana`, `ID_Cetatenie`) VALUES
(1, 6),
(3, 2),
(3, 3),
(4, 1),
(5, 6),
(5, 8),
(6, 1),
(6, 4),
(6, 8),
(7, 7),
(8, 2),
(9, 4),
(10, 1);

--
-- Indexuri pentru tabele eliminate
--

--
-- Indexuri pentru tabele `adrese`
--
ALTER TABLE `adrese`
  ADD PRIMARY KEY (`ID_Adresa`);

--
-- Indexuri pentru tabele `cetatenii`
--
ALTER TABLE `cetatenii`
  ADD PRIMARY KEY (`ID_Cetatenie`);

--
-- Indexuri pentru tabele `dosare`
--
ALTER TABLE `dosare`
  ADD PRIMARY KEY (`ID_Dosare`),
  ADD KEY `Persoana_DosarFK` (`ID_Persoana`),
  ADD KEY `Ofiter_DosarFK` (`ID_Ofiteri`);

--
-- Indexuri pentru tabele `ofiteri`
--
ALTER TABLE `ofiteri`
  ADD PRIMARY KEY (`ID_Ofiteri`),
  ADD UNIQUE KEY `CNP` (`CNP`);

--
-- Indexuri pentru tabele `persoane`
--
ALTER TABLE `persoane`
  ADD PRIMARY KEY (`ID_Persoana`),
  ADD UNIQUE KEY `CNP` (`CNP`),
  ADD KEY `Persoana_AdresaFK` (`ID_Adresa`);

--
-- Indexuri pentru tabele `persoanecetatenii`
--
ALTER TABLE `persoanecetatenii`
  ADD PRIMARY KEY (`ID_Persoana`,`ID_Cetatenie`),
  ADD KEY `CetatenieFK` (`ID_Cetatenie`);

--
-- AUTO_INCREMENT pentru tabele eliminate
--

--
-- AUTO_INCREMENT pentru tabele `adrese`
--
ALTER TABLE `adrese`
  MODIFY `ID_Adresa` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pentru tabele `cetatenii`
--
ALTER TABLE `cetatenii`
  MODIFY `ID_Cetatenie` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- AUTO_INCREMENT pentru tabele `dosare`
--
ALTER TABLE `dosare`
  MODIFY `ID_Dosare` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT pentru tabele `ofiteri`
--
ALTER TABLE `ofiteri`
  MODIFY `ID_Ofiteri` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT pentru tabele `persoane`
--
ALTER TABLE `persoane`
  MODIFY `ID_Persoana` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- Constrângeri pentru tabele eliminate
--

--
-- Constrângeri pentru tabele `dosare`
--
ALTER TABLE `dosare`
  ADD CONSTRAINT `Ofiter_DosarFK` FOREIGN KEY (`ID_Ofiteri`) REFERENCES `ofiteri` (`ID_Ofiteri`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `Persoana_DosarFK` FOREIGN KEY (`ID_Persoana`) REFERENCES `persoane` (`ID_Persoana`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constrângeri pentru tabele `persoane`
--
ALTER TABLE `persoane`
  ADD CONSTRAINT `Persoana_AdresaFK` FOREIGN KEY (`ID_Adresa`) REFERENCES `adrese` (`ID_Adresa`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constrângeri pentru tabele `persoanecetatenii`
--
ALTER TABLE `persoanecetatenii`
  ADD CONSTRAINT `CetatenieFK` FOREIGN KEY (`ID_Cetatenie`) REFERENCES `cetatenii` (`ID_Cetatenie`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `PersoanaFK` FOREIGN KEY (`ID_Persoana`) REFERENCES `persoane` (`ID_Persoana`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
