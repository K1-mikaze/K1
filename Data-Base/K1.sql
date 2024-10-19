-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 19, 2024 at 06:19 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `K1`
--

-- --------------------------------------------------------

--
-- Table structure for table `Deliveries`
--

CREATE TABLE `Deliveries` (
  `IDD` int(11) NOT NULL,
  `Address` varchar(250) NOT NULL,
  `Driver` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Deliveries`
--

INSERT INTO `Deliveries` (`IDD`, `Address`, `Driver`) VALUES
(1, 'CRA 66C #62-20', 2),
(2, 'CRA 63 #103-B10', 3),
(3, 'ESTACION METRO SANTO DOMINGO', 3),
(4, 'CALLEL 48D #65A-57, LOCAL 222', 3),
(5, 'CALLE 5B#36B-36, UNIDAD RINCÓN DE CASTILLO 1, APARTAMENTO 501', 2),
(6, 'TRASVERSAL 56A#54-88, AVENIDA EL CARRETERO (BELLO), TALLER DE MOTOS EL MONO', 2),
(7, 'CRA 25B NO. 18 ASUR 62, AVEYANEDA SAN LUCAS', 2),
(8, 'APARTADO, ANTIOQUIA', 2),
(9, 'APARTADO, ANTIOQUIA', 1),
(10, 'CRA 59B #77S85, LA ESTRELLA', 3),
(11, 'LAURELES, CALLE 35', 3),
(12, 'CRA 49 #58-19, CLÍNICA VICTORIANA', 2),
(13, 'CALLE 47 #46-46, CUIDADO DE BIENESTAR DEL ANCIANO, TERCER PISO, CUARTO 305, BARRIO MANCHESTER, BELLO', 3),
(14, 'CALLE 20FF #77-85, PARIS - BELLO', 4),
(15, 'EDIFICIO SEIBA, AL LADO DEL COLTEJER, PISO 11, OFICINA 1124', 4),
(16, 'CRA 31 #70SUR72, BARRIO SAN ISIDRO, SABANETA', 4),
(17, 'CRA 31 #70SUR72, BARRIO SAN ISIDRO, SABANETA', 4),
(18, 'BARRIO LA INMACULADA, CARRERA 12C NO. 59-46, SOLEDAD, ATLÁNTICO', 1),
(19, 'CARRERA 19 #26-43, CENTRO, MANIZALES', 1),
(20, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(21, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(22, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(23, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(24, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(25, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(26, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(27, 'CARRERA 80, CALLE 41 SUR 21, CORREGIMIENTO DE SAN ANTONIO DE PRADO, ANTIOQUIA (PORCICARNES - 2ND FLOOR)', 3),
(28, 'ITAGUI, AL FRENTE DE LA BOMBA DEL METRO', 3),
(29, 'MARMATO EL LLANO, CALDAS', 1),
(30, 'BUENAAVENTURA, BARRIO 12 DE ABRIL, CALLE 12 #57A46', 2),
(31, 'PARQUE SAN ANTONIO', 3),
(32, 'CL 57 #69-27, RINCÓN DEL BOSQUE, BELLO, ANTIOQUIA', 3),
(33, 'CARRERA TERCERA 809, LA CUCHILLA, AGUADAS, CALDAS', 2),
(34, 'VEGACHÍ', 1),
(35, 'CALLE 49, CARRERA 99 91, INTERIOR 207, BARRIO JUAN 23 LA QUIEBRA', 2),
(36, 'CALLE 107 #37B15, INTERIOR 202, CANCHA DEL TIERRERO', 2),
(37, 'ISNÓS, HUILA', 1),
(38, 'SANTA MARTA, MAGDALENA, CALLE 18 #21-139, EDIFICIO KATAMARAN (LOCAL 1)', 1),
(39, 'SANTA MARTA, MAGDALENA, CALLE 18 #21-139, EDIFICIO KATAMARAN (LOCAL 1)', 1),
(40, 'CALLE 79B #55C21, BARRIO EL BOSQUE, ARANJUEZ/MORAVIA', 2),
(41, 'IPIALES, NARIÑO', 1),
(42, 'K.9 #58-25 LA BOQUILLA', 2),
(43, 'SANTA MARTA, ATLANTICO', 1),
(44, 'CRA 44 #96-32,MANRIQUE GUADALUPE', 3);

-- --------------------------------------------------------

--
-- Table structure for table `Drivers`
--

CREATE TABLE `Drivers` (
  `DriverID` int(11) NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Drivers`
--

INSERT INTO `Drivers` (`DriverID`, `Name`, `Surname`, `Phone`) VALUES
(1, 'SERGIO', 'IDARRAGA', '3014814875'),
(2, 'Mateo', 'Gutierrez', '3017581922'),
(3, 'Alejandro', 'Martinez', '3044467427'),
(4, 'Mauricio', 'Restrepo', '3178534072');

-- --------------------------------------------------------

--
-- Table structure for table `Logs`
--

CREATE TABLE `Logs` (
  `IDL` bigint(20) NOT NULL,
  `Log` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Logs`
--

INSERT INTO `Logs` (`IDL`, `Log`) VALUES
(1112759822, '2024-10-16 23:39:59'),
(1112759822, '2024-10-16 23:43:19'),
(1112759822, '2024-10-17 10:37:01'),
(1112759822, '2024-10-17 22:34:55'),
(1112759822, '2024-10-17 23:18:51'),
(1112759822, '2024-10-17 23:22:44'),
(1112759822, '2024-10-18 20:53:40'),
(1112759822, '2024-10-18 20:58:04'),
(1112759822, '2024-10-18 21:10:22'),
(1112759822, '2024-10-18 21:12:51');

-- --------------------------------------------------------

--
-- Table structure for table `Number_Units`
--

CREATE TABLE `Number_Units` (
  `IDO_2` int(11) NOT NULL,
  `Remainings` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Number_Units`
--

INSERT INTO `Number_Units` (`IDO_2`, `Remainings`) VALUES
(1, 2),
(2, 0),
(3, 1),
(4, 2),
(5, 2),
(6, 0),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1);

-- --------------------------------------------------------

--
-- Table structure for table `Objects`
--

CREATE TABLE `Objects` (
  `IDO` int(11) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Name` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Objects`
--

INSERT INTO `Objects` (`IDO`, `Type`, `Name`) VALUES
(1, 'Watch', 'SYB'),
(2, 'Watch', 'SYG'),
(3, 'Watch', 'HG'),
(4, 'Watch', 'HS'),
(5, 'Watch', 'HB'),
(6, 'Watch', 'RIMB'),
(7, 'Watch', 'RIMW'),
(8, 'Watch', 'YOR'),
(9, 'Watch', 'YOG'),
(10, 'Watch', 'YOGR'),
(11, 'Watch', 'DIB'),
(12, 'Watch', 'RMB');

-- --------------------------------------------------------

--
-- Table structure for table `REMOVED_OBJECTS`
--

CREATE TABLE `REMOVED_OBJECTS` (
  `IDO` int(11) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Name` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `REMOVED_USERS`
--

CREATE TABLE `REMOVED_USERS` (
  `IDU` int(11) NOT NULL,
  `CC` bigint(20) DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `REMOVED_WORKERS`
--

CREATE TABLE `REMOVED_WORKERS` (
  `ID` bigint(20) NOT NULL,
  `Admin` tinyint(4) NOT NULL,
  `Created` datetime NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(320) DEFAULT NULL,
  `Password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `IDU` int(11) NOT NULL,
  `CC` bigint(20) DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`IDU`, `CC`, `Name`, `Surname`, `Phone`) VALUES
(1, NULL, 'JUAN MANUEL', 'OSSA LOPEZ', '3146632143'),
(2, NULL, 'ABIMAEL', 'TORRES', '3017957795'),
(3, NULL, 'MARIO', 'MARULANDA', '3027753303'),
(4, NULL, 'YEISY', 'RENDON', '3046592373'),
(5, NULL, 'ORFA', 'MOSQUERA', '3175776020'),
(6, NULL, 'CARLOS MARIO', 'RICO', '3146886434'),
(7, NULL, 'YOVANNY', 'URREGO', '3046671792'),
(8, NULL, 'REMOVED', 'REMOVED', NULL),
(9, 1037470549, 'SANTIAGO', 'MARTÍNEZ CEREN', '3146015907'),
(10, NULL, 'LUIS FERNANDO', 'GARCES ZAPATA', '3022712265'),
(11, NULL, 'ELVIS', 'HURTADO', '3227822457'),
(12, NULL, 'DUBAN', 'GUTIERREZ', '3216800472'),
(13, NULL, 'CARLOS', 'HOYOS', '3024038137'),
(14, NULL, 'KATHERINE', 'PÉREZ', '3218096149'),
(15, NULL, 'JULIAN ', 'ZAPATA', '3246224984'),
(16, NULL, 'MIGUEL ANGEL', 'VELASQUEZ', '3195154217'),
(17, 22173272, 'MIGUEL', 'GUERRERO', '3042430940'),
(18, 75102854, 'EDISSON', 'VALLEJO', '3008818942'),
(19, NULL, ' JOSE', 'NAVARRO VILLALBA', '3006808122'),
(20, NULL, 'GUILLERMO', 'RESTREPO', '3022430009'),
(21, 13000440, 'ENDER', 'GONZÁLEZ', '3226758164'),
(22, NULL, 'FRANCISCO JAVIER', 'ARCE', '3135708518'),
(23, NULL, 'WILFRIDO', 'PANESSO', '3126487444'),
(24, NULL, 'SANTIAGO', 'CARDENAS', '3173678675'),
(25, NULL, 'JEANETH', 'CARMONA GALLEGO', '3205076640'),
(26, NULL, 'LUIS JOSÉ', 'GÓMEZ', '3011305146'),
(27, NULL, 'LUZ MARINA', 'MARÍN GARCÍA', '3015791802'),
(28, NULL, 'BRAYAN', 'ARCILA', '3137954887'),
(29, 12171076, 'WILLIAM', 'ORDOÑEZ GARCEZ', '3115626009'),
(30, NULL, 'LUIS', 'MUJICA', '3193962440'),
(31, NULL, 'LUIS', 'ZAPATA', '3026222647'),
(32, 87100587, 'ARMANDO', 'YEPES', '3116946042'),
(33, 730994192, 'ALBERTO', 'LUNA IRIARTE', '3012707768'),
(34, NULL, 'FRANCISCO', 'ARMANDO', '3204542017'),
(35, NULL, 'DAVID', 'GOMEZ', '3243894091');

-- --------------------------------------------------------

--
-- Table structure for table `Users_Objects_Deliveries`
--

CREATE TABLE `Users_Objects_Deliveries` (
  `IDT` int(11) NOT NULL,
  `IDU_1` int(11) NOT NULL,
  `IDO_1` int(11) NOT NULL,
  `IDD_1` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users_Objects_Deliveries`
--

INSERT INTO `Users_Objects_Deliveries` (`IDT`, `IDU_1`, `IDO_1`, `IDD_1`) VALUES
(1, 1, 6, 1),
(2, 2, 3, 2),
(3, 3, 1, 3),
(4, 4, 11, 4),
(5, 5, 1, 5),
(6, 6, 2, 6),
(7, 7, 1, 7),
(8, 8, 1, 8),
(9, 9, 5, 9),
(10, 10, 6, 10),
(11, 11, 6, 11),
(12, 12, 1, 12),
(13, 13, 3, 13),
(14, 14, 10, 14),
(15, 15, 7, 15),
(16, 16, 7, 16),
(17, 16, 6, 16),
(18, 17, 9, 18),
(19, 18, 2, 19),
(20, 19, 5, 20),
(21, 19, 3, 20),
(22, 19, 4, 20),
(23, 19, 6, 20),
(24, 19, 7, 20),
(25, 19, 5, 20),
(26, 19, 5, 20),
(27, 19, 3, 20),
(28, 20, 5, 28),
(29, 21, 1, 29),
(30, 22, 7, 30),
(31, 23, 1, 31),
(32, 24, 6, 32),
(33, 25, 9, 33),
(34, 26, 8, 34),
(35, 27, 11, 35),
(36, 28, 5, 36),
(37, 29, 2, 37),
(38, 30, 1, 38),
(39, 30, 2, 38),
(40, 31, 7, 40),
(41, 32, 4, 41),
(42, 33, 1, 42),
(43, 34, 2, 43),
(44, 35, 3, 44);

-- --------------------------------------------------------

--
-- Table structure for table `Workers`
--

CREATE TABLE `Workers` (
  `ID` bigint(20) NOT NULL,
  `Admin` tinyint(1) NOT NULL,
  `Created` datetime NOT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) DEFAULT NULL,
  `Email` varchar(320) DEFAULT NULL,
  `Password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Workers`
--

INSERT INTO `Workers` (`ID`, `Admin`, `Created`, `Name`, `Surname`, `Phone`, `Email`, `Password`) VALUES
(1112759822, 1, '2024-10-16 23:39:04', 'SERGIO', 'IDARRAGA', '3014814875', 'K1mikaze@proton.me', 'fe4d3915c9f1a62fea224fa37dab82f805310b1d');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Deliveries`
--
ALTER TABLE `Deliveries`
  ADD UNIQUE KEY `IDU_2` (`IDD`),
  ADD KEY `Driver` (`Driver`);

--
-- Indexes for table `Drivers`
--
ALTER TABLE `Drivers`
  ADD PRIMARY KEY (`DriverID`);

--
-- Indexes for table `Logs`
--
ALTER TABLE `Logs`
  ADD KEY `IDL` (`IDL`);

--
-- Indexes for table `Number_Units`
--
ALTER TABLE `Number_Units`
  ADD UNIQUE KEY `IDO_2` (`IDO_2`);

--
-- Indexes for table `Objects`
--
ALTER TABLE `Objects`
  ADD PRIMARY KEY (`IDO`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD UNIQUE KEY `Name_2` (`Name`);

--
-- Indexes for table `REMOVED_OBJECTS`
--
ALTER TABLE `REMOVED_OBJECTS`
  ADD PRIMARY KEY (`IDO`),
  ADD UNIQUE KEY `Name` (`Name`),
  ADD UNIQUE KEY `Name_2` (`Name`);

--
-- Indexes for table `REMOVED_USERS`
--
ALTER TABLE `REMOVED_USERS`
  ADD PRIMARY KEY (`IDU`),
  ADD UNIQUE KEY `CC` (`CC`),
  ADD UNIQUE KEY `Phone` (`Phone`);

--
-- Indexes for table `REMOVED_WORKERS`
--
ALTER TABLE `REMOVED_WORKERS`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Phone` (`Phone`),
  ADD UNIQUE KEY `Phone_2` (`Phone`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`IDU`),
  ADD UNIQUE KEY `Phone` (`Phone`),
  ADD UNIQUE KEY `CC` (`CC`),
  ADD UNIQUE KEY `Phone_2` (`Phone`);

--
-- Indexes for table `Users_Objects_Deliveries`
--
ALTER TABLE `Users_Objects_Deliveries`
  ADD PRIMARY KEY (`IDT`),
  ADD KEY `IDU_1` (`IDU_1`),
  ADD KEY `IDO_1` (`IDO_1`),
  ADD KEY `IDD_1` (`IDD_1`);

--
-- Indexes for table `Workers`
--
ALTER TABLE `Workers`
  ADD PRIMARY KEY (`ID`),
  ADD UNIQUE KEY `Phone` (`Phone`),
  ADD UNIQUE KEY `Phone_2` (`Phone`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Deliveries`
--
ALTER TABLE `Deliveries`
  MODIFY `IDD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `Drivers`
--
ALTER TABLE `Drivers`
  MODIFY `DriverID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `Objects`
--
ALTER TABLE `Objects`
  MODIFY `IDO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `IDU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=36;

--
-- AUTO_INCREMENT for table `Users_Objects_Deliveries`
--
ALTER TABLE `Users_Objects_Deliveries`
  MODIFY `IDT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `Deliveries`
--
ALTER TABLE `Deliveries`
  ADD CONSTRAINT `Deliveries_ibfk_2` FOREIGN KEY (`Driver`) REFERENCES `Drivers` (`DriverID`);

--
-- Constraints for table `Logs`
--
ALTER TABLE `Logs`
  ADD CONSTRAINT `Logs_ibfk_1` FOREIGN KEY (`IDL`) REFERENCES `Workers` (`ID`),
  ADD CONSTRAINT `Logs_ibfk_2` FOREIGN KEY (`IDL`) REFERENCES `Workers` (`ID`);

--
-- Constraints for table `Number_Units`
--
ALTER TABLE `Number_Units`
  ADD CONSTRAINT `Number_Units_ibfk_1` FOREIGN KEY (`IDO_2`) REFERENCES `Objects` (`IDO`);

--
-- Constraints for table `REMOVED_OBJECTS`
--
ALTER TABLE `REMOVED_OBJECTS`
  ADD CONSTRAINT `REMOVED_OBJECTS_ibfk_1` FOREIGN KEY (`IDO`) REFERENCES `Objects` (`IDO`);

--
-- Constraints for table `REMOVED_USERS`
--
ALTER TABLE `REMOVED_USERS`
  ADD CONSTRAINT `REMOVED_USERS_ibfk_1` FOREIGN KEY (`IDU`) REFERENCES `Users` (`IDU`);

--
-- Constraints for table `REMOVED_WORKERS`
--
ALTER TABLE `REMOVED_WORKERS`
  ADD CONSTRAINT `REMOVED_WORKERS_ibfk_1` FOREIGN KEY (`ID`) REFERENCES `Workers` (`ID`);

--
-- Constraints for table `Users_Objects_Deliveries`
--
ALTER TABLE `Users_Objects_Deliveries`
  ADD CONSTRAINT `Users_Objects_Deliveries_ibfk_1` FOREIGN KEY (`IDU_1`) REFERENCES `Users` (`IDU`),
  ADD CONSTRAINT `Users_Objects_Deliveries_ibfk_2` FOREIGN KEY (`IDO_1`) REFERENCES `Objects` (`IDO`),
  ADD CONSTRAINT `Users_Objects_Deliveries_ibfk_3` FOREIGN KEY (`IDD_1`) REFERENCES `Deliveries` (`IDD`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
