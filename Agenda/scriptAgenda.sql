--
-- Table structure for table `personas`
--

DROP TABLE IF EXISTS `personas`;

CREATE TABLE `personas` (
  `idPersona` int(11) NOT NULL AUTO_INCREMENT,
  `Nombre` varchar(45) NOT NULL,
  `Telefono` varchar(20) NOT NULL,
  `Email` varchar(45) NOT NULL,
  `Cumpleanos` date NOT NULL,
  `Calle` varchar(45) NOT NULL,
  `Altura` varchar(20) NOT NULL,
  `Piso` varchar(20) NOT NULL,
  `Depto` varchar(20) NOT NULL,
  `Localidad` varchar(45) NOT NULL,
  `TipoContacto` varchar(45) NOT NULL,
  PRIMARY KEY (`idPersona`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `Localidades`
--

DROP TABLE IF EXISTS `Localidades`;

CREATE TABLE `Localidades` (
  `idLocalidad` int(11) NOT NULL AUTO_INCREMENT,
  `Localidad` varchar(45) NOT NULL,
  PRIMARY KEY (`idLocalidad`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `TipoContacto`
--

DROP TABLE IF EXISTS `TipoContacto`;

CREATE TABLE `TipoContacto` (
  `idTipoContacto` int(11) NOT NULL AUTO_INCREMENT,
  `TipoContacto` varchar(45) NOT NULL,
  PRIMARY KEY (`idTipoContacto`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

INSERT INTO `personas` (`Nombre`, `Telefono`, `Email`, `Cumpleanos`, `Calle`, `Altura`, `Piso`, `Depto`, `Localidad`, `TipoContacto`)
VALUES ('Martin Nuñez','15224532526','madarnu@hotmail.com','1988-10-14','Guayaquil','928','1','0','San Miguel','Trabajo');

INSERT INTO `personas` (`Nombre`, `Telefono`, `Email`, `Cumpleanos`, `Calle`, `Altura`, `Piso`, `Depto`, `Localidad`, `TipoContacto`)
VALUES ('Romero Silvana','1544326324','rsilv@gmail.com','1997-05-21','Robles','1214','1','0','Los Polvorines','Amistad');

INSERT INTO `personas` (`Nombre`, `Telefono`, `Email`, `Cumpleanos`, `Calle`, `Altura`, `Piso`, `Depto`, `Localidad`, `TipoContacto`)
VALUES ('Alejandra Leiva','153562643','ale@gmail.com','1970-05-25','Casares','1453','0','1','Los Polvorines','Oficina');

INSERT INTO `localidades` (`Localidad`) VALUES ('Los Polvorines');

INSERT INTO `localidades` (`Localidad`) VALUES ('San Miguel');

INSERT INTO `tipocontacto` (`TipoContacto`) VALUES ('Amistad');

INSERT INTO `tipocontacto` (`TipoContacto`) VALUES ('Trabajo');

INSERT INTO `tipocontacto` (`TipoContacto`) VALUES ('Oficina');