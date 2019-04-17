-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: mysql
-- Generation Time: Apr 17, 2019 at 12:20 AM
-- Server version: 10.3.4-MariaDB
-- PHP Version: 7.2.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `joesAUTOSv2`
--
CREATE DATABASE IF NOT EXISTS `joesAUTOSv2` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `joesAUTOSv2`;

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(6) NOT NULL,
  `employee_id` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `employee_id`) VALUES
(1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(6) NOT NULL,
  `first_name` varchar(20) NOT NULL,
  `last_name` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  `telephone` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `town` varchar(50) NOT NULL,
  `city` varchar(50) NOT NULL,
  `country` varchar(50) NOT NULL,
  `post_code` varchar(20) NOT NULL,
  `salary` double(10,2) NOT NULL,
  `role_id` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `first_name`, `last_name`, `email`, `telephone`, `address`, `town`, `city`, `country`, `post_code`, `salary`, `role_id`) VALUES
(2, 'Ryan', 'Freeman', 'admin@joesautos.com', '', '', '', '', '', '', 10000.00, 1),
(44, 'Tegan', 'Houghton', 'teganhoughton@joesautos.com', '447945954483', '40 Old Edinburgh Road', 'BEESTON REGIS', '', 'UK', 'NR26 4GF', 60000.00, 2),
(45, 'Charlie', 'Bruce', 'charliebruce@joesautos.com', '447931428790', '94 Sea Road', 'LANE HEAD', '', 'UK', 'HD8 2QN', 40000.00, 3),
(46, 'Freya', 'Marsden', 'fmarsden@joesautos.com', '447023193415', '34 Withers Close', 'ALLGREAVE', '', 'UK', 'SK11 5XU', 35000.00, 4),
(50, 'Ryan', 'Freeman', '', '', '', '', '', '', '', 30000.00, 4),
(51, 'Ryan', 'Freeman', '', '', '', '', '', '', '', 50000.00, 3);

-- --------------------------------------------------------

--
-- Table structure for table `employee_roles`
--

CREATE TABLE `employee_roles` (
  `id` int(6) NOT NULL,
  `title` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `employee_roles`
--

INSERT INTO `employee_roles` (`id`, `title`) VALUES
(1, 'Admin'),
(2, 'Regional Sales Manager'),
(3, 'Sales Manager'),
(4, 'Salesman');

-- --------------------------------------------------------

--
-- Table structure for table `motorbikes`
--

CREATE TABLE `motorbikes` (
  `id` int(6) NOT NULL,
  `make` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `engine` varchar(20) NOT NULL,
  `colour` varchar(20) NOT NULL,
  `year` int(4) NOT NULL,
  `price` double(10,2) NOT NULL DEFAULT 0.00,
  `stock` int(2) NOT NULL,
  `frame_id` int(6) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `motorbikes`
--

INSERT INTO `motorbikes` (`id`, `make`, `model`, `engine`, `colour`, `year`, `price`, `stock`, `frame_id`) VALUES
(1, 'Kawasaki', 'Z750 R', '750cc petrol', 'Green', 2006, 3450.00, 97, 5);

-- --------------------------------------------------------

--
-- Table structure for table `motorbike_frames`
--

CREATE TABLE `motorbike_frames` (
  `id` int(6) NOT NULL,
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `motorbike_frames`
--

INSERT INTO `motorbike_frames` (`id`, `name`) VALUES
(1, 'Chopper'),
(2, 'Cruiser'),
(3, 'Motocross'),
(4, 'Scooter'),
(5, 'Sport');

-- --------------------------------------------------------

--
-- Table structure for table `regional_sales_managers`
--

CREATE TABLE `regional_sales_managers` (
  `id` int(6) NOT NULL,
  `employee_id` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `regional_sales_managers`
--

INSERT INTO `regional_sales_managers` (`id`, `employee_id`) VALUES
(11, 44);

-- --------------------------------------------------------

--
-- Table structure for table `sales`
--

CREATE TABLE `sales` (
  `id` int(6) NOT NULL,
  `status` varchar(20) NOT NULL,
  `total` double(10,2) NOT NULL,
  `commission` double(10,2) NOT NULL,
  `salesman_id` int(6) DEFAULT NULL,
  `motorbike_id` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `sales`
--

INSERT INTO `sales` (`id`, `status`, `total`, `commission`, `salesman_id`, `motorbike_id`) VALUES
(1, 'complete', 4243.50, 345.00, 16, 1),
(2, 'complete', 4243.50, 345.00, 16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `salesmen`
--

CREATE TABLE `salesmen` (
  `id` int(6) NOT NULL,
  `commission_rate` double(10,2) NOT NULL,
  `employee_id` int(6) NOT NULL,
  `sales_manager_id` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `salesmen`
--

INSERT INTO `salesmen` (`id`, `commission_rate`, `employee_id`, `sales_manager_id`) VALUES
(12, 10.00, 46, 9),
(16, 10.00, 50, 9);

-- --------------------------------------------------------

--
-- Table structure for table `sales_managers`
--

CREATE TABLE `sales_managers` (
  `id` int(6) NOT NULL,
  `quarterly_bonus` double(10,2) NOT NULL,
  `employee_id` int(6) DEFAULT NULL,
  `regional_sales_manager_id` int(6) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

--
-- Dumping data for table `sales_managers`
--

INSERT INTO `sales_managers` (`id`, `quarterly_bonus`, `employee_id`, `regional_sales_manager_id`) VALUES
(9, 2000.00, 45, 11),
(10, 2000.00, 51, 11);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`),
  ADD KEY `admin.employee_id` (`employee_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_roles.id` (`role_id`);

--
-- Indexes for table `employee_roles`
--
ALTER TABLE `employee_roles`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `motorbikes`
--
ALTER TABLE `motorbikes`
  ADD PRIMARY KEY (`id`),
  ADD KEY `motorbike_frames.id` (`frame_id`);

--
-- Indexes for table `motorbike_frames`
--
ALTER TABLE `motorbike_frames`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `regional_sales_managers`
--
ALTER TABLE `regional_sales_managers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `regional_sales_managers.employee_id` (`employee_id`);

--
-- Indexes for table `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `motorbike_id` (`motorbike_id`),
  ADD KEY `employee_id` (`salesman_id`);

--
-- Indexes for table `salesmen`
--
ALTER TABLE `salesmen`
  ADD PRIMARY KEY (`id`),
  ADD KEY `sales_manager_id` (`sales_manager_id`),
  ADD KEY `sales_staff.employee_id` (`employee_id`);

--
-- Indexes for table `sales_managers`
--
ALTER TABLE `sales_managers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_roles.id` (`regional_sales_manager_id`),
  ADD KEY `sales_managers.employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=52;

--
-- AUTO_INCREMENT for table `employee_roles`
--
ALTER TABLE `employee_roles`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `motorbikes`
--
ALTER TABLE `motorbikes`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `motorbike_frames`
--
ALTER TABLE `motorbike_frames`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `regional_sales_managers`
--
ALTER TABLE `regional_sales_managers`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT for table `sales`
--
ALTER TABLE `sales`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `salesmen`
--
ALTER TABLE `salesmen`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `sales_managers`
--
ALTER TABLE `sales_managers`
  MODIFY `id` int(6) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admin`
--
ALTER TABLE `admin`
  ADD CONSTRAINT `admin.employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `employee_roles.id` FOREIGN KEY (`role_id`) REFERENCES `employee_roles` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `motorbikes`
--
ALTER TABLE `motorbikes`
  ADD CONSTRAINT `motorbike_frames.id` FOREIGN KEY (`frame_id`) REFERENCES `motorbike_frames` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `regional_sales_managers`
--
ALTER TABLE `regional_sales_managers`
  ADD CONSTRAINT `regional_sales_managers.employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `motorbikes.id` FOREIGN KEY (`motorbike_id`) REFERENCES `motorbikes` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `salesmen.id` FOREIGN KEY (`salesman_id`) REFERENCES `salesmen` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `salesmen`
--
ALTER TABLE `salesmen`
  ADD CONSTRAINT `sales_manager_id` FOREIGN KEY (`sales_manager_id`) REFERENCES `sales_managers` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_staff.employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE;

--
-- Constraints for table `sales_managers`
--
ALTER TABLE `sales_managers`
  ADD CONSTRAINT `regional_sales_manager_id` FOREIGN KEY (`regional_sales_manager_id`) REFERENCES `regional_sales_managers` (`id`) ON UPDATE CASCADE,
  ADD CONSTRAINT `sales_managers.employee_id` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`) ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
