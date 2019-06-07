-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 07-06-2019 a las 00:18:14
-- Versión del servidor: 10.3.14-MariaDB
-- Versión de PHP: 7.3.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id9729488_nutricaredb`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Alimento`
--

CREATE TABLE `Alimento` (
  `idAlimento` int(10) UNSIGNED NOT NULL,
  `nombre` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` int(11) NOT NULL,
  `info` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Alimento`
--

INSERT INTO `Alimento` (`idAlimento`, `nombre`, `tipo`, `info`) VALUES
(1, 'Manzana', 1, 'Fruta muy rica'),
(2, 'Lechuga', 2, 'Vegetal verde'),
(3, 'Pera', 1, 'Fruta'),
(4, 'Mandarina', 1, 'Fruta cítrica'),
(5, 'Pepino', 2, 'Se usa en ensaladas'),
(6, 'Tomate', 1, 'Fruta roja'),
(7, 'Zanahoria', 2, 'Verdura naranja'),
(8, 'Mango', 1, 'Fruta rica '),
(9, 'Platano', 1, 'amarillo'),
(10, 'apio', 2, 'vegetal'),
(11, 'Naranja', 1, ''),
(12, 'ciruela', 1, 'roja');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuario`
--

CREATE TABLE `Usuario` (
  `idUsuario` int(11) NOT NULL,
  `nombre` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `apellido` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `username` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `pass` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Usuario`
--

INSERT INTO `Usuario` (`idUsuario`, `nombre`, `apellido`, `email`, `username`, `pass`) VALUES
(1, 'Diego', 'Salcedo', 'diegitsen@gmail.com', 'diegitsen', '12345'),
(2, 'Jorge', 'Landaverry', 'jorgelandaverry@hotmail.com', 'JALG', '99911157');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Alimento`
--
ALTER TABLE `Alimento`
  ADD PRIMARY KEY (`idAlimento`);

--
-- Indices de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Alimento`
--
ALTER TABLE `Alimento`
  MODIFY `idAlimento` int(10) UNSIGNED NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT de la tabla `Usuario`
--
ALTER TABLE `Usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
