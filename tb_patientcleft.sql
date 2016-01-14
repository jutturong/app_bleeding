-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 14, 2016 at 04:46 AM
-- Server version: 10.1.9-MariaDB
-- PHP Version: 5.5.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cleft`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_patientcleft`
--

CREATE TABLE `tb_patientcleft` (
  `id_patient` int(11) NOT NULL,
  `name` varchar(30) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `lastname` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `id_card` int(13) NOT NULL,
  `telephone` varchar(12) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `id_sex` int(2) NOT NULL,
  `birthdate` date NOT NULL,
  `address` int(11) NOT NULL,
  `province_id` int(5) NOT NULL,
  `diagnosis` text CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `detail_diagnosis` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `informative_name` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `informative_lastname` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `informative_tel` varchar(12) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `tb_patientcleft`
--

INSERT INTO `tb_patientcleft` (`id_patient`, `name`, `lastname`, `id_card`, `telephone`, `id_sex`, `birthdate`, `address`, `province_id`, `diagnosis`, `detail_diagnosis`, `informative_name`, `informative_lastname`, `informative_tel`) VALUES
(172, 'กานดา', 'วรประวัติ', 2147483647, '0855241258', 1, '2015-05-29', 857, 34, 'à¸­à¸·à¹ˆà¸™à¹†', 'à¹€à¸›à¹‡à¸™à¸™à¸­à¸à¹€à¸«à¸™', 'à¸§à¸´à¸Šà¸´à¸•', 'à¸¨à¸£à¸µà¹€à¸Šà¸µà¸¢à¸‡', '0858539042'),
(173, 'สุวัติ', 'ละออพันธ์', 2147483647, '0855241258', 1, '2015-05-29', 857, 25, 'à¸­à¸·à¹ˆà¸™à¹†', 'à¹€à¸›à¹‡à¸™à¸™à¸­à¸à¹€à¸«à¸™', 'à¸§à¸´à¸Šà¸´à¸•', 'à¸¨à¸£à¸µà¹€à¸Šà¸µà¸¢à¸‡', '0858539042'),
(174, 'เมชิตา', 'ดอสุบรร', 2147483647, '0855241258', 2, '2015-05-29', 857, 34, 'à¸›à¸²à¸à¹à¸«à¸§à¹ˆà¸‡', 'à¹€à¸›à¹‡à¸™à¸™à¸­à¸à¹€à¸«à¸™', 'à¸§à¸´à¸Šà¸´à¸•', 'à¸¨à¸£à¸µà¹€à¸Šà¸µà¸¢à¸‡', '0858539042'),
(177, 'วนิดา', 'ศรีบุญลือ', 2147483647, '0855241258', 1, '2015-06-08', 857, 34, 'à¸›à¸²à¸à¹à¸«à¸§à¹ˆà¸‡à¹à¸¥à¸°à¹€à¸žà¸”à¸²à¸™à¹‚à¸«à¸§à¹ˆ', '', 'à¸§à¸´à¸Šà¸´à¸•', 'à¸¨à¸£à¸µà¹€à¸Šà¸µà¸¢à¸‡', '0858539042');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_patientcleft`
--
ALTER TABLE `tb_patientcleft`
  ADD PRIMARY KEY (`id_patient`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_patientcleft`
--
ALTER TABLE `tb_patientcleft`
  MODIFY `id_patient` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=178;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
