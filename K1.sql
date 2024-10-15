-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 15, 2024 at 03:13 AM
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
(1, 'FSAFSAFSA', 1);

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
(1, 'SERGIO', 'IDARRAGA', '3014814875');

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
(1112759822, '2024-10-07 12:26:42'),
(1112759822, '2024-10-14 11:41:29'),
(1112759822, '2024-10-14 12:08:08');

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
(11, 1),
(12, 0);

-- --------------------------------------------------------

--
-- Table structure for table `Objects`
--

CREATE TABLE `Objects` (
  `IDO` int(11) NOT NULL,
  `Type` varchar(100) NOT NULL,
  `Name` varchar(150) NOT NULL
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
(12, 'REMOVED', 'REMOVED');

-- --------------------------------------------------------

--
-- Table structure for table `Users`
--

CREATE TABLE `Users` (
  `IDU` int(11) NOT NULL,
  `CC` bigint(20) DEFAULT NULL,
  `Name` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Phone` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Users`
--

INSERT INTO `Users` (`IDU`, `CC`, `Name`, `Surname`, `Phone`) VALUES
(1, NULL, 'SERGIO', 'IDARRAGA', '301481'),
(2, NULL, 'REMOVED', 'REMOVED', 'REMOVED');

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
(1, 1, 2, 1);

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
  `Phone` bigint(20) NOT NULL,
  `Email` varchar(320) NOT NULL,
  `Password` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `Workers`
--

INSERT INTO `Workers` (`ID`, `Admin`, `Created`, `Name`, `Surname`, `Phone`, `Email`, `Password`) VALUES
(1112759822, 1, '2024-10-07 11:56:41', 'SERGIO', 'IDARRAGA AGUIRRE', 3014814875, 'K1mikaze@proton.me', 'fe4d3915c9f1a62fea224fa37dab82f805310b1d');

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
  ADD UNIQUE KEY `Name` (`Name`);

--
-- Indexes for table `Users`
--
ALTER TABLE `Users`
  ADD PRIMARY KEY (`IDU`),
  ADD UNIQUE KEY `Phone` (`Phone`),
  ADD UNIQUE KEY `CC` (`CC`);

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
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Deliveries`
--
ALTER TABLE `Deliveries`
  MODIFY `IDD` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Drivers`
--
ALTER TABLE `Drivers`
  MODIFY `DriverID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `Objects`
--
ALTER TABLE `Objects`
  MODIFY `IDO` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `Users`
--
ALTER TABLE `Users`
  MODIFY `IDU` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `Users_Objects_Deliveries`
--
ALTER TABLE `Users_Objects_Deliveries`
  MODIFY `IDT` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
