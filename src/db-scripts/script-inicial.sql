-- Crea la base de datos clinicaMedica
CREATE DATABASE IF NOT EXISTS clinicaMedica
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_unicode_ci;

USE clinicaMedica;

-- Crea la tabla pacientes
CREATE TABLE IF NOT EXISTS pacientes (
  idPaciente INT AUTO_INCREMENT PRIMARY KEY,
  dpi VARCHAR(13),
  nombre VARCHAR(255) NOT NULL,
  telefono VARCHAR(10),
  correo VARCHAR(50)
);

-- Crea la tabla usuarios para login
CREATE TABLE IF NOT EXISTS usuarios (
  idUsuario INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(50),
  password VARCHAR(255) NOT NULL
);

-- Inserta un usuario por defecto solo si no existe
INSERT INTO usuarios (username, password)
SELECT * FROM (SELECT 'admin', 'admin123') AS tmp
WHERE NOT EXISTS (
    SELECT username FROM usuarios WHERE username = 'admin'
) LIMIT 1;

-- Crea la tabla doctores
CREATE TABLE IF NOT EXISTS doctores (
  idDoctor INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(255) NOT NULL,
  especialidad VARCHAR(100) NOT NULL,
  telefono VARCHAR(10),
  correo VARCHAR(50)
);

-- Inserta doctores por defecto
INSERT INTO doctores (nombre, especialidad, telefono, correo) VALUES
  ('Dr. Juan Pérez', 'Cardiología', '5551234567', 'juan.perez@clinica.com'),
  ('Dra. Ana López', 'Pediatría', '5559876543', 'ana.lopez@clinica.com');