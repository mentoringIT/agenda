-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 25-04-2015 a las 21:30:19
-- Versión del servidor: 5.5.16
-- Versión de PHP: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `agenda`
--
CREATE DATABASE `agenda` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `agenda`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contacto`
--

CREATE TABLE IF NOT EXISTS `contacto` (
  `contacto_id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  PRIMARY KEY (`contacto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `detalle_contacto`
--

CREATE TABLE IF NOT EXISTS `detalle_contacto` (
  `detalle_contacto_id` int(11) NOT NULL AUTO_INCREMENT,
  `medio_contacto_id` int(11) NOT NULL,
  `valor` varchar(50) NOT NULL,
  `contacto_id` int(11) NOT NULL,
  PRIMARY KEY (`detalle_contacto_id`),
  KEY `fk_detalle_contacto_medio_contacto_idx` (`medio_contacto_id`),
  KEY `fk_detalle_contacto_contacto1_idx` (`contacto_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medio_contacto`
--

CREATE TABLE IF NOT EXISTS `medio_contacto` (
  `medio_contacto_id` int(11) NOT NULL,
  `clave` char(1) NOT NULL,
  `descripcion` varchar(10) NOT NULL,
  PRIMARY KEY (`medio_contacto_id`),
  UNIQUE KEY `clave_UNIQUE` (`clave`),
  UNIQUE KEY `descripcion_UNIQUE` (`descripcion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Volcado de datos para la tabla `medio_contacto`
--

INSERT INTO `medio_contacto` (`medio_contacto_id`, `clave`, `descripcion`) VALUES
(1, 'T', 'Telefono'),
(2, 'D', 'Direccion'),
(3, 'M', 'Mail');

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `detalle_contacto`
--
ALTER TABLE `detalle_contacto`
  ADD CONSTRAINT `fk_detalle_contacto_contacto1` FOREIGN KEY (`contacto_id`) REFERENCES `contacto` (`contacto_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_detalle_contacto_medio_contacto` FOREIGN KEY (`medio_contacto_id`) REFERENCES `medio_contacto` (`medio_contacto_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
