CREATE TABLE `Clientes` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Apellido` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Sexo` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Edad` int DEFAULT NULL,
  `Estado` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  `Ciudad` varchar(255) COLLATE utf8mb4_unicode_ci DEFAULT NULL,
  UNIQUE KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `Clientes` (`id`, `Nombre`, `Apellido`, `Sexo`, `Edad`, `Estado`, `Ciudad`) VALUES
(1, 'Oscar', 'Huerta Sanchez', 'M', 25, 'Guanajuato', 'León'),
(2, 'Octavio', 'Sanchez Ortega', 'M', 30, 'Guanajuato', 'Irapuato'),
(3, 'Julio', 'Morales Camarena', 'M', 23, 'Jalisco', 'Guadalajara'),
(4, 'María', 'Diaz Arellano', 'F', 25, 'Michoacan', 'La piedad'),
(5, 'Lorena', 'López Ortiz ', 'F', 30, 'Nuevo León', 'Monterrey'),
(6, 'Fernando', 'Huerta Mireles', 'M', 28, 'Jalisco', 'Lagos de Moreno'),
(7, 'Casandra', 'Contreras Pérez', 'F', 22, 'Guanajuato', 'León');

-- Sentencia que arroje aquellos registros cuyo sexo sea masculino y la edad sea superior a 25
SELECT * FROM Clientes WHERE Sexo = "M" AND Edad > 25;

-- Escribir una sentencia que actualice el registro con Id 6 indicando que la Ciudad es “San Juan de los Lagos” y Apellidos “Herrera Huerta”
UPDATE Clientes SET Ciudad = "San Juan de los Lagos", Apellido = "Herrera Huerta" WHERE id = 6;

-- Escribir una sentencia que arroje aquellas personas que están entre 20 y 29 años y sean sexo femenino.
SELECT * FROM Clientes WHERE Sexo = "F" AND Edad BETWEEN 20 AND 29;

-- Escribir una sentencia que arroje los registros cuyos Apellidos contienen el apellido "Huerta"
SELECT * FROM Clientes WHERE Apellido LIKE "%Huerta%";

-- Sentencia que arroje el conteo de los registros que están en el estado de Guanajuato.
SELECT COUNT(*) FROM Clientes WHERE Estado = "Guanajuato";

-- Escribir una sentencia que inserte un nuevo registro a dicha tabla considerando que todos los campos son obligatorios.
INSERT INTO `Clientes` (`id`, `Nombre`, `Apellido`, `Sexo`, `Edad`, `Estado`, `Ciudad`) VALUES
(8, 'Abraham', 'Espinosa Mendoza', 'M', 20, 'Yucatán', 'Mérida');

-- Escribir una sentencia que seleccione la cantidad de personas que hay por estado.
SELECT Estado, COUNT(*) AS Total FROM Clientes GROUP BY Estado;

-- Escribir una sentencia que consulte las personas que son del sexo Masculino y las ordene de manera descendente por su apellido y posteriormente por su Nombre.
SELECT * FROM Clientes WHERE Sexo = "M" ORDER BY Apellido DESC, Nombre DESC;