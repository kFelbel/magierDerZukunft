-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Erstellungszeit: 24. Mrz 2023 um 08:38
-- Server-Version: 10.4.27-MariaDB
-- PHP-Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Datenbank: `magierderzukunft`
--
CREATE DATABASE IF NOT EXISTS `magierderzukunft` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `magierderzukunft`;

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `coins`
--

CREATE TABLE `coins` (
  `_id` int(11) NOT NULL,
  `name` text NOT NULL,
  `symbol` text NOT NULL,
  `price` double NOT NULL,
  `founder` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `coins`
--

INSERT INTO `coins` (`_id`, `name`, `symbol`, `price`, `founder`) VALUES
(1, 'Bitcoin', 'BTC', 22000, 'Satoshi'),
(2, 'Ethereum', 'ETH', 1400, 'Nikolai'),
(5, 'Verasity', 'VRA', 0.0067, 'Jan'),
(6, 'VeChain', 'VET', 0.22, 'Ka'),
(7, 'Move', 'MOVE', 0.2, 'Elsar');

-- --------------------------------------------------------

--
-- Tabellenstruktur für Tabelle `transactions`
--

CREATE TABLE `transactions` (
  `_id` int(11) NOT NULL,
  `coinToBuy` text NOT NULL,
  `pricePerCoin` double NOT NULL,
  `amountUSDT` double NOT NULL,
  `amountCoin` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Daten für Tabelle `transactions`
--

INSERT INTO `transactions` (`_id`, `coinToBuy`, `pricePerCoin`, `amountUSDT`, `amountCoin`) VALUES
(7, '(VRA) Verasity', 0.0067, 30, 4477.611940298508),
(8, '(BTC) Bitcoin', 22000, 400, 0.01818181818181818),
(9, '(MOVE) Move', 0.2, 50, 250),
(10, '(MOVE) Move', 0.2, 800, 4000),
(11, '(USDT) Tether', 0.9451, 400, 427.36),
(12, '(USDT) Tether', 0.9451, 300, 320.52),
(13, '(USDT) Tether', 0.9451, 30, 32.052),
(14, '(USDT) Tether', 0.9451, -1, -200),
(15, '(VRA) Verasity', 0.0067, 200, 29850.746268656716),
(16, '(USDT) Tether', 0.9451, 250, 267.1),
(17, '(USDT) Tether', 0.9451, 250, 267.1),
(18, '(USDT) Tether', 0.9451, -1, -500),
(19, '(BTC) Bitcoin', 22000, 500, 0.022727272727272728),
(20, '(MOVE) Move', 0.2, -1, -1000),
(21, '(MOVE) Move', 0.2, -1, -1000),
(22, '(MOVE) Move', 0.2, -1, -300),
(23, '(USDT) Tether', 0.9451, 60, -1),
(24, '(VRA) Verasity', 0.0067, -1, -2000),
(25, '(USDT) Tether', 0.9451, 13.4, -1),
(26, '(BTC) Bitcoin', 22000, -1, -0.01),
(27, '(USDT) Tether', 0.9451, 220, -1),
(28, '(BTC) Bitcoin', 22000, -1, -0.01),
(29, '(USDT) Tether', 0.9451, -1, 220),
(30, '(USDT) Tether', 0.9451, 900, 961.5600000000001),
(31, '(USDT) Tether', 0.9451, -1, -1000),
(32, '(VRA) Verasity', 0.0067, 1000, 149253.73134328358),
(33, '(VRA) Verasity', 0.0067, -1, -40000),
(34, '(USDT) Tether', 0.9451, -1, 268),
(35, '(VRA) Verasity', 0.0067, -1, -20000),
(36, '(USDT) Tether', 0.9451, -1, 134),
(37, '(VRA) Verasity', 0.0067, -1, -7000),
(38, '(USDT) Tether', 0.9451, -1, 46.9),
(39, '(VRA) Verasity', 0.0067, -1, -12000),
(40, '(USDT) Tether', 0.9451, -1, 80.4);

--
-- Indizes der exportierten Tabellen
--

--
-- Indizes für die Tabelle `coins`
--
ALTER TABLE `coins`
  ADD PRIMARY KEY (`_id`);

--
-- Indizes für die Tabelle `transactions`
--
ALTER TABLE `transactions`
  ADD PRIMARY KEY (`_id`);

--
-- AUTO_INCREMENT für exportierte Tabellen
--

--
-- AUTO_INCREMENT für Tabelle `coins`
--
ALTER TABLE `coins`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT für Tabelle `transactions`
--
ALTER TABLE `transactions`
  MODIFY `_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
