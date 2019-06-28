-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 28-06-2019 a las 13:48:20
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
  `idAlimento` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `tipo` int(11) NOT NULL,
  `info` varchar(25) COLLATE utf8_unicode_ci DEFAULT NULL,
  `calorias` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `carbohidratos` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `grasas` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `proteinas` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Alimento`
--

INSERT INTO `Alimento` (`idAlimento`, `nombre`, `tipo`, `info`, `calorias`, `carbohidratos`, `grasas`, `proteinas`) VALUES
(1, 'Manzana', 1, 'Fruta rica', '10', '2', '5', '9'),
(2, 'Tomate', 2, 'Verdura rica', '15', '12', '3', '8'),
(3, 'Pera', 1, 'Fruta', '10', '10', '10', '10'),
(4, 'uva', 1, 'morado', '4', '14', '14', '12'),
(5, 'limon', 1, 'acido', '12', '14', '36', '9'),
(6, 'fresa', 1, 'gran jugo', '5', '5', '6', '9'),
(7, 'naranja', 1, 'rico', '2', '2', '5', '8'),
(8, 'alcachofa', 2, 'rico', '5', '5', '10', '10');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Dieta`
--

CREATE TABLE `Dieta` (
  `idDieta` int(11) NOT NULL,
  `fecha` date DEFAULT NULL,
  `idPaciente` int(11) NOT NULL,
  `idAlimento` int(11) NOT NULL,
  `comio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Dieta`
--

INSERT INTO `Dieta` (`idDieta`, `fecha`, `idPaciente`, `idAlimento`, `comio`) VALUES
(1, '2010-10-10', 1, 1, 1),
(3, '2019-06-27', 2, 6, 1),
(4, '2019-06-27', 2, 2, 1),
(5, '2019-06-27', 2, 2, 1),
(7, '2019-06-27', 1, 1, 1),
(15, '2019-06-27', 2, 5, 1),
(16, '2019-06-25', 1, 2, 1),
(17, '2019-06-20', 1, 3, 1),
(18, '2019-06-21', 1, 3, 1),
(19, '2019-06-22', 1, 3, 1),
(20, '2019-06-22', 1, 3, 1),
(21, '2019-06-23', 1, 3, 1),
(22, '2019-06-24', 1, 3, 1),
(23, '2019-06-22', 2, 3, 1),
(24, '2019-06-23', 2, 3, 1),
(25, '2019-06-24', 2, 3, 1),
(26, '2019-06-27', 2, 3, 1),
(27, '2019-06-27', 2, 4, 1),
(28, '2019-06-27', 1, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Doctor`
--

CREATE TABLE `Doctor` (
  `idDoctor` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `correo` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `usuario` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Doctor`
--

INSERT INTO `Doctor` (`idDoctor`, `nombre`, `apellido`, `correo`, `usuario`, `password`) VALUES
(1, 'Alberto', 'Castro', 'alberto@gmail.com', 'D_001', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Paciente`
--

CREATE TABLE `Paciente` (
  `idPaciente` int(11) NOT NULL,
  `nombre` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `peso` int(11) NOT NULL,
  `edad` int(11) NOT NULL,
  `correo` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `usuario` varchar(25) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(25) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Paciente`
--

INSERT INTO `Paciente` (`idPaciente`, `nombre`, `apellido`, `peso`, `edad`, `correo`, `usuario`, `password`) VALUES
(1, 'Pepe', 'Gonzales', 80, 30, 'pepe@gmail.com', '001', '123'),
(2, 'Juan', 'Castillo', 100, 40, 'juan@gmail.com', '002', '123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Paciente_Doctor`
--

CREATE TABLE `Paciente_Doctor` (
  `idPacienteDoctor` int(11) NOT NULL,
  `idPaciente` int(11) NOT NULL,
  `idDoctor` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `Paciente_Doctor`
--

INSERT INTO `Paciente_Doctor` (`idPacienteDoctor`, `idPaciente`, `idDoctor`) VALUES
(1, 1, 1),
(2, 2, 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Alimento`
--
ALTER TABLE `Alimento`
  ADD PRIMARY KEY (`idAlimento`);

--
-- Indices de la tabla `Dieta`
--
ALTER TABLE `Dieta`
  ADD PRIMARY KEY (`idDieta`),
  ADD KEY `Dieta_Alimento` (`idAlimento`),
  ADD KEY `Dieta_Paciente` (`idPaciente`);

--
-- Indices de la tabla `Doctor`
--
ALTER TABLE `Doctor`
  ADD PRIMARY KEY (`idDoctor`);

--
-- Indices de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  ADD PRIMARY KEY (`idPaciente`);

--
-- Indices de la tabla `Paciente_Doctor`
--
ALTER TABLE `Paciente_Doctor`
  ADD PRIMARY KEY (`idPacienteDoctor`),
  ADD KEY `Paciente_Doctor_Doctor` (`idDoctor`),
  ADD KEY `Paciente_Doctor_Paciente` (`idPaciente`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `Alimento`
--
ALTER TABLE `Alimento`
  MODIFY `idAlimento` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `Dieta`
--
ALTER TABLE `Dieta`
  MODIFY `idDieta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=29;

--
-- AUTO_INCREMENT de la tabla `Doctor`
--
ALTER TABLE `Doctor`
  MODIFY `idDoctor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `Paciente`
--
ALTER TABLE `Paciente`
  MODIFY `idPaciente` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `Paciente_Doctor`
--
ALTER TABLE `Paciente_Doctor`
  MODIFY `idPacienteDoctor` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Dieta`
--
ALTER TABLE `Dieta`
  ADD CONSTRAINT `Dieta_Alimento` FOREIGN KEY (`idAlimento`) REFERENCES `Alimento` (`idAlimento`),
  ADD CONSTRAINT `Dieta_Paciente` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`);

--
-- Filtros para la tabla `Paciente_Doctor`
--
ALTER TABLE `Paciente_Doctor`
  ADD CONSTRAINT `Paciente_Doctor_Doctor` FOREIGN KEY (`idDoctor`) REFERENCES `Doctor` (`idDoctor`),
  ADD CONSTRAINT `Paciente_Doctor_Paciente` FOREIGN KEY (`idPaciente`) REFERENCES `Paciente` (`idPaciente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
