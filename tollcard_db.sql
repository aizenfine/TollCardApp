-- phpMyAdmin SQL Dump
-- version 3.5.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 09, 2014 at 05:42 PM
-- Server version: 5.5.25a
-- PHP Version: 5.4.4

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tollcard_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE IF NOT EXISTS `customer` (
  `noKtp` int(20) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `alamat` varchar(50) DEFAULT NULL,
  `noHp` varchar(13) DEFAULT NULL,
  PRIMARY KEY (`noKtp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`noKtp`, `nama`, `alamat`, `noHp`) VALUES
(112233, 'Ayu', 'PGA', '088992117'),
(1452810, 'Ayu', 'PGA', '088992117'),
(14528101, 'Ayu', 'PGA', '0889921171');

-- --------------------------------------------------------

--
-- Table structure for table `diakses`
--

CREATE TABLE IF NOT EXISTS `diakses` (
  `idAkses` int(4) NOT NULL,
  `waktu` varchar(5) NOT NULL,
  `idCard` int(4) NOT NULL,
  `idPortal` int(4) NOT NULL,
  PRIMARY KEY (`idAkses`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `kartu`
--

CREATE TABLE IF NOT EXISTS `kartu` (
  `idCard` int(6) NOT NULL,
  `saldo` int(9) NOT NULL,
  `noKtp` int(20) NOT NULL,
  PRIMARY KEY (`idCard`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kartu`
--

INSERT INTO `kartu` (`idCard`, `saldo`, `noKtp`) VALUES
(11, 0, 112233),
(12, 0, 112233),
(111, 0, 14528101);

-- --------------------------------------------------------

--
-- Table structure for table `mengelola`
--

CREATE TABLE IF NOT EXISTS `mengelola` (
  `idPegawai` int(4) NOT NULL,
  `idCard` int(4) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `pegawai`
--

CREATE TABLE IF NOT EXISTS `pegawai` (
  `idPegawai` int(4) NOT NULL,
  `nama` varchar(20) NOT NULL,
  `password` varchar(8) NOT NULL,
  PRIMARY KEY (`idPegawai`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pegawai`
--

INSERT INTO `pegawai` (`idPegawai`, `nama`, `password`) VALUES
(2, 'Mahendra', '2'),
(1000, 'Izzan', '1234');

-- --------------------------------------------------------

--
-- Table structure for table `portal`
--

CREATE TABLE IF NOT EXISTS `portal` (
  `idPortal` int(4) NOT NULL,
  `nama` varchar(10) NOT NULL,
  `tarif` int(6) NOT NULL,
  PRIMARY KEY (`idPortal`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `portal`
--

INSERT INTO `portal` (`idPortal`, `nama`, `tarif`) VALUES
(1234, 'Madiun', 2000),
(2468, 'Bandung', 3000);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
