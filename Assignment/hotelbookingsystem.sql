-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 05, 2023 at 06:58 AM
-- Server version: 8.0.31
-- PHP Version: 8.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hotelbookingsystem`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

DROP TABLE IF EXISTS `booking`;
CREATE TABLE IF NOT EXISTS `booking` (
  `Booking_ID` int NOT NULL AUTO_INCREMENT,
  `Customer_ID` int NOT NULL,
  `Room_NO` int DEFAULT NULL,
  `Book_date` date NOT NULL,
  `Check_IN` date NOT NULL,
  `Check_OUT` date DEFAULT NULL,
  `Booking_Status_NO` int NOT NULL,
  `Hotel_ID` int DEFAULT NULL,
  `Room_Type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`Booking_ID`),
  KEY `Customer_ID` (`Customer_ID`),
  KEY `Room_NO` (`Room_NO`),
  KEY `Booking_Status_NO` (`Booking_Status_NO`),
  KEY `Hotel_ID` (`Hotel_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`Booking_ID`, `Customer_ID`, `Room_NO`, `Book_date`, `Check_IN`, `Check_OUT`, `Booking_Status_NO`, `Hotel_ID`, `Room_Type`) VALUES
(1, 1, 202, '2023-04-05', '2023-04-06', '2023-04-12', 1, 1, ''),
(2, 2, 302, '2023-04-08', '2023-04-10', '2023-04-17', 4, 1, ''),
(3, 3, 0, '2023-04-15', '2023-04-16', '2023-04-22', 2, 1, 'Twin'),
(4, 4, 201, '2023-04-09', '2023-04-11', '2023-04-20', 1, 1, 'Single'),
(5, 1, 302, '2023-04-17', '2023-04-20', '2023-04-28', 1, 1, 'Single'),
(11, 10, 301, '2023-05-04', '2023-05-08', '2023-05-14', 1, 1, 'Single'),
(10, 1, 0, '2023-05-04', '2023-05-08', '2023-05-14', 2, 1, 'Single'),
(9, 8, 201, '2023-04-29', '2023-05-01', '2023-05-06', 5, 1, 'Single'),
(13, 12, 202, '2023-05-05', '2023-05-08', '2023-05-14', 3, 1, 'Single');

-- --------------------------------------------------------

--
-- Table structure for table `bookingstatus`
--

DROP TABLE IF EXISTS `bookingstatus`;
CREATE TABLE IF NOT EXISTS `bookingstatus` (
  `Status_NO` int NOT NULL AUTO_INCREMENT,
  `Status_Type` varchar(50) NOT NULL,
  PRIMARY KEY (`Status_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `bookingstatus`
--

INSERT INTO `bookingstatus` (`Status_NO`, `Status_Type`) VALUES
(1, 'ACTIVE'),
(2, 'PENDING'),
(3, 'COMPLETE'),
(4, 'CANCEL'),
(5, 'BOOKED');

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
CREATE TABLE IF NOT EXISTS `customer` (
  `Customer_ID` int NOT NULL AUTO_INCREMENT,
  `First_Name` varchar(50) NOT NULL,
  `Last_Name` varchar(50) NOT NULL,
  `Address` varchar(50) DEFAULT NULL,
  `Mobile` varchar(50) NOT NULL,
  `Email` varchar(50) DEFAULT NULL,
  `Gender` varchar(20) DEFAULT NULL,
  `DOB` date DEFAULT NULL,
  `Password` varchar(50) DEFAULT NULL,
  `UserType_ID` int NOT NULL,
  PRIMARY KEY (`Customer_ID`),
  UNIQUE KEY `Mobile` (`Mobile`),
  KEY `UserType_ID` (`UserType_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`Customer_ID`, `First_Name`, `Last_Name`, `Address`, `Mobile`, `Email`, `Gender`, `DOB`, `Password`, `UserType_ID`) VALUES
(1, 'Shimson', 'Sanba', 'Sunakothi', '9861329665', 'shimsonsanba2004@gmail.com', 'Male', '2004-03-04', 'Shimson@123', 1),
(2, 'Arpan', 'Shrestha', 'Lalitpur', '9817624799', 'arpan777@gmail.com', 'Male', '2003-07-07', 'Arpan@123', 2),
(3, 'Shyam', 'Limbu', 'Sunakoshi', '9812131564', 'shyam2004@gmail.com', 'Male', '2002-05-04', 'Shyam@123', 1),
(4, 'Gita', 'Shrestha', 'Lalitpur', '9817456789', 'gita777@gmail.com', 'Female', '2004-07-07', 'gita@123', 1),
(6, 'sdf', 'kjhkj', 'jk', '5545454', 'jjhk', 'jkhjk', '2023-04-21', 'hjkhkjhkj', 1),
(8, 'Ramesh', 'shah', 'Kathmandu', '9876543210', 'rameshshah@gmail.com', 'Male', '2000-04-12', 'ramesh@123', 1),
(9, 'Ashok', 'Last Name', 'charikot', '9879879879', 'ashokkc@gmail.com', 'Male', '2023-04-05', 'ashok@123', 1),
(10, 'Shashwot', 'Rimal', 'Baluwatar', '9876543211', 'shashwot@gmail.com', 'Male', '2004-05-26', 'Rimal@123', 1),
(12, 'Sumee', 'Maharjan', 'Kathmandu', '9874563219', 'sumeemah@gmail.com', 'Female', '2004-10-26', 'Sumeemaha123', 1);

-- --------------------------------------------------------

--
-- Table structure for table `hotel`
--

DROP TABLE IF EXISTS `hotel`;
CREATE TABLE IF NOT EXISTS `hotel` (
  `Hotel_ID` int NOT NULL,
  `Hotel_Name` varchar(50) NOT NULL,
  `Hotel_Address` varchar(50) NOT NULL,
  `Hotel_Phone` varchar(50) NOT NULL,
  `Country` varchar(50) DEFAULT NULL,
  `Star_Rating` float(2,1) NOT NULL,
  PRIMARY KEY (`Hotel_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `hotel`
--

INSERT INTO `hotel` (`Hotel_ID`, `Hotel_Name`, `Hotel_Address`, `Hotel_Phone`, `Country`, `Star_Rating`) VALUES
(1, 'Luton Hotel', 'Luton', '9816865689', 'Japan', 4.8);

-- --------------------------------------------------------

--
-- Table structure for table `itemtype`
--

DROP TABLE IF EXISTS `itemtype`;
CREATE TABLE IF NOT EXISTS `itemtype` (
  `Item_ID` int NOT NULL AUTO_INCREMENT,
  `Item` varchar(50) NOT NULL,
  `Item_Type` varchar(50) NOT NULL,
  `Price` float DEFAULT NULL,
  PRIMARY KEY (`Item_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `itemtype`
--

INSERT INTO `itemtype` (`Item_ID`, `Item`, `Item_Type`, `Price`) VALUES
(1, 'Dry Cleaning', 'amenities', 500),
(2, 'SPA', 'amenities', 600),
(3, 'Apple Juice', 'Drinks', 100),
(4, 'Mango Juice', 'Drinks', 100),
(5, 'Indian Cuisine', 'Meals', 2000),
(6, 'Italian Cuisine', 'Meals', 3000),
(7, 'SandWitch', 'Breakfast', 200),
(8, 'Fried Rice', 'Breakfast', 300);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

DROP TABLE IF EXISTS `payment`;
CREATE TABLE IF NOT EXISTS `payment` (
  `Payment_ID` int NOT NULL AUTO_INCREMENT,
  `Booking_ID` int NOT NULL,
  `Date` date DEFAULT NULL,
  `Payment_Mode` varchar(50) DEFAULT NULL,
  `Total_Payment` float DEFAULT NULL,
  `Payment_Status` varchar(255) NOT NULL,
  PRIMARY KEY (`Payment_ID`),
  KEY `Booking_ID` (`Booking_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`Payment_ID`, `Booking_ID`, `Date`, `Payment_Mode`, `Total_Payment`, `Payment_Status`) VALUES
(1, 1, '2023-04-12', 'Cash', 5100, ''),
(2, 2, '2023-04-17', 'Card', 6200, ''),
(3, 5, '2023-05-05', 'Card', 26668, 'Paid'),
(5, 11, '2023-05-05', 'Card', 17289, 'Paid'),
(6, 12, '2023-05-05', NULL, 17402, 'Unpaid'),
(7, 13, '2023-05-05', NULL, 18080, 'Unpaid');

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
CREATE TABLE IF NOT EXISTS `role` (
  `Role_NO` int NOT NULL AUTO_INCREMENT,
  `Role_Type` varchar(50) NOT NULL,
  PRIMARY KEY (`Role_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`Role_NO`, `Role_Type`) VALUES
(1, 'Manager'),
(2, 'Receptionist'),
(3, 'Bar');

-- --------------------------------------------------------

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
CREATE TABLE IF NOT EXISTS `room` (
  `Room_NO` int NOT NULL,
  `Room_Type` varchar(50) NOT NULL,
  `Status` int NOT NULL,
  PRIMARY KEY (`Room_NO`),
  KEY `Room_Type` (`Room_Type`),
  KEY `Status` (`Status`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `room`
--

INSERT INTO `room` (`Room_NO`, `Room_Type`, `Status`) VALUES
(201, 'Single', 1),
(202, 'Single', 1),
(203, 'Twin', 3),
(204, 'Double', 1),
(301, 'Single', 3),
(302, 'Single', 3),
(303, 'Twin', 1),
(304, 'Double', 1);

-- --------------------------------------------------------

--
-- Table structure for table `roomtype`
--

DROP TABLE IF EXISTS `roomtype`;
CREATE TABLE IF NOT EXISTS `roomtype` (
  `Room_Type` varchar(7) NOT NULL,
  `Total_Room` int NOT NULL,
  `Price` float DEFAULT NULL,
  PRIMARY KEY (`Room_Type`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `roomtype`
--

INSERT INTO `roomtype` (`Room_Type`, `Total_Room`, `Price`) VALUES
('Single', 6, 2500),
('Twin', 4, 4500),
('Double', 4, 6000);

-- --------------------------------------------------------

--
-- Table structure for table `room_status`
--

DROP TABLE IF EXISTS `room_status`;
CREATE TABLE IF NOT EXISTS `room_status` (
  `Status_NO` int NOT NULL AUTO_INCREMENT,
  `Room_Status` varchar(50) NOT NULL,
  PRIMARY KEY (`Status_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `room_status`
--

INSERT INTO `room_status` (`Status_NO`, `Room_Status`) VALUES
(1, 'OPEN'),
(2, 'BOOKED'),
(3, 'CLOSE');

-- --------------------------------------------------------

--
-- Table structure for table `services`
--

DROP TABLE IF EXISTS `services`;
CREATE TABLE IF NOT EXISTS `services` (
  `Service_ID` int NOT NULL AUTO_INCREMENT,
  `Booking_ID` int NOT NULL,
  `Date` date NOT NULL,
  `Item_ID` int NOT NULL,
  `Quantity` int NOT NULL,
  `Status` varchar(11) NOT NULL,
  PRIMARY KEY (`Service_ID`),
  KEY `Booking_ID` (`Booking_ID`),
  KEY `Item_ID` (`Item_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `services`
--

INSERT INTO `services` (`Service_ID`, `Booking_ID`, `Date`, `Item_ID`, `Quantity`, `Status`) VALUES
(1, 5, '2023-04-07', 1, 2, 'PENDING'),
(2, 5, '2023-04-07', 3, 1, 'COMPLETE'),
(3, 5, '2023-04-07', 5, 1, 'PENDING'),
(9, 2, '2023-04-12', 6, 1, 'CANCEL'),
(8, 2, '2023-04-12', 4, 1, 'PENDING'),
(7, 2, '2023-04-12', 2, 1, 'COMPLETE'),
(10, 1, '2023-04-20', 2, 1, 'COMPLETE'),
(11, 5, '2023-04-22', 8, 1, 'CANCEL'),
(13, 5, '2023-04-22', 7, 1, 'PENDING'),
(14, 0, '2023-05-04', 8, 1, 'Pending'),
(15, 0, '2023-05-04', 2, 1, 'Pending'),
(16, 0, '2023-05-04', 3, 2, 'Pending'),
(17, 0, '2023-05-04', 6, 1, 'Pending'),
(18, 0, '2023-05-04', 4, 1, 'Pending'),
(19, 11, '2023-05-05', 8, 1, 'Complete'),
(20, 12, '2023-05-05', 8, 1, 'Pending'),
(21, 12, '2023-05-05', 3, 1, 'Pending'),
(22, 13, '2023-05-05', 8, 1, 'Pending'),
(23, 13, '2023-05-05', 2, 1, 'Complete'),
(24, 13, '2023-05-05', 4, 1, 'Complete');

-- --------------------------------------------------------

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
CREATE TABLE IF NOT EXISTS `staff` (
  `Staff_ID` int NOT NULL AUTO_INCREMENT,
  `Staff_First_Name` varchar(50) NOT NULL,
  `Staff_Last_Name` varchar(50) NOT NULL,
  `Staff_Address` varchar(50) DEFAULT NULL,
  `Staff_Mobile` varchar(50) NOT NULL,
  `Staff_Email` varchar(50) DEFAULT NULL,
  `Staff_Gender` varchar(20) DEFAULT NULL,
  `Staff_DOB` date DEFAULT NULL,
  `Staff_Password` varchar(50) DEFAULT NULL,
  `Hotel_ID` int NOT NULL,
  `Role_NO` int NOT NULL,
  PRIMARY KEY (`Staff_ID`),
  UNIQUE KEY `Staff_Mobile` (`Staff_Mobile`),
  KEY `Hotel_ID` (`Hotel_ID`),
  KEY `Role_NO` (`Role_NO`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` (`Staff_ID`, `Staff_First_Name`, `Staff_Last_Name`, `Staff_Address`, `Staff_Mobile`, `Staff_Email`, `Staff_Gender`, `Staff_DOB`, `Staff_Password`, `Hotel_ID`, `Role_NO`) VALUES
(1, 'Yaman', 'Maharjan', 'KTM', '9749423161', 'yamanmaharjan69@gmail.com', 'Male', '2004-05-26', 'Yaman@123', 1, 1),
(2, 'Sanjeev', 'Shrestha', 'CTK', '9869663324', 'sanjeev77@gmail.com', 'Male', '1986-08-26', 'Sanjeev@123', 1, 2),
(3, 'Ram', 'Tamang', 'PKR', '9874123650', 'ram@gmail.com', 'Male', '1990-02-09', 'Ram@123', 1, 3),
(4, 'Hari', 'Tamang', 'Jhapa', '9856842368', 'hari@gmail.com', 'Male', '1996-05-29', 'Hari@123', 1, 3);

-- --------------------------------------------------------

--
-- Table structure for table `usertype`
--

DROP TABLE IF EXISTS `usertype`;
CREATE TABLE IF NOT EXISTS `usertype` (
  `UserType_ID` int NOT NULL AUTO_INCREMENT,
  `User_Type` varchar(50) NOT NULL,
  PRIMARY KEY (`UserType_ID`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `usertype`
--

INSERT INTO `usertype` (`UserType_ID`, `User_Type`) VALUES
(1, 'Non-Corporate'),
(2, 'Corporate');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
