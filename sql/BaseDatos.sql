-- Database: portal-transaccional

-- DROP DATABASE IF EXISTS "portal-transaccional";

CREATE DATABASE "portal-transaccional"
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Colombia.1252'
    LC_CTYPE = 'Spanish_Colombia.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- -----------------------------------
-- Table "PERSONAS"
-- -----------------------------------
CREATE TABLE PERSONAS (
	"id_persona" SERIAL NOT NULL,
	"nombre" VARCHAR(150) NULL,
	"genero" CHAR(1) NULL,
	"edad" NUMERIC NULL,
	"identificacion" VARCHAR(50) UNIQUE,
	"direccion" VARCHAR(80) NULL,
	"telefono" NUMERIC NULL,
	PRIMARY KEY ("id_persona")
);

-- ---------------------------------
-- Table "CLIENTES"
-- ---------------------------------
CREATE TABLE CLIENTES (
	"id_cliente" SERIAL NOT NULL,
	"contraseña" VARCHAR(100) NOT NULL,
	"estado" BOOLEAN NOT NULL,
	PRIMARY KEY ("id_cliente"),
	CONSTRAINT "fk_clientes_personas"
		FOREIGN KEY ("id_cliente")
		REFERENCES PERSONAS ("id_persona")
		ON DELETE NO ACTION
);

-- ---------------------------------
-- Table "CUENTAS"
-- ---------------------------------
CREATE TABLE CUENTAS (
	"id_cuenta" SERIAL NOT NULL,
	"numero_cuenta" VARCHAR(50) NOT NULL,
	"tipo_cuenta" VARCHAR(50) NULL,
	"saldo_inicial" DECIMAL(16,2) NOT NULL,
	"estado" BOOLEAN NULL,
	"id_cliente" INT NOT NULL,
	PRIMARY KEY ("id_cuenta"),
	CONSTRAINT "fk_cuentas_clientes"
		FOREIGN KEY ("id_cliente")
		REFERENCES CLIENTES ("id_cliente")
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);

-- ---------------------------------
-- Table "MOVIMIENTOS"
-- ---------------------------------
CREATE TABLE MOVIMIENTOS (
	"id_movimiento" SERIAL NOT NULL,
	"fecha" TIMESTAMP NOT NULL,
	"tipo_movimiento" VARCHAR(50) NOT NULL,
	"valor" DECIMAL(16,2) NOT NULL,
	"saldo" DECIMAL(16,2) NOT NULL,
	"id_cuenta" INT NOT NULL,
	PRIMARY KEY ("id_movimiento"),
	CONSTRAINT "fk_movimientos_cuentas"
		FOREIGN KEY ("id_cuenta")
		REFERENCES CUENTAS ("id_cuenta")
		ON DELETE NO ACTION
		ON UPDATE NO ACTION
);