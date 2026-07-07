use bdfisiohaven;


-- ==========================================
-- 1. INSERTAR CATEGORÍAS
-- ==========================================
INSERT INTO categorias (nombre) VALUES 
('Ejercicio y Rehabilitación'),
('Termoterapia'),
('Electroterapia'),
('Masoterapia');

-- ==========================================
-- 2. INSERTAR MARCAS
-- ==========================================
-- Usamos IGNORE por si ejecutas el script dos veces, no duplique marcas
INSERT IGNORE INTO marcas (nombre) VALUES 
('Tunturi'), ('Carci'), ('Care fitness'), ('xtreme sport'), ('BOSU Fitness'), 
('Thermoform'), ('BIMEDICA'), ('Hydrocollator Hotpac'), ('Chattanooga'), 
('Proiron Perú'), ('Ultimate fitness Perú'), ('Ripley'), ('Promart'), 
('Oxford Perú'), ('Neck Jamp'), ('Tumi Healer'), ('JP REHAB SRL'), 
('Prohands'), ('HydroSun'), ('Thera'), ('Contect'), ('Medior'), 
('PERUMASSAGE'), ('Magneto'), ('Tens'), ('Globus'), ('Fisio Medic Market'), 
('Meditea'), ('Trideer'), ('Gymnic'), ('Boldfit'), ('Holgu'), ('JrMedical'), 
('Chiquilandia'), ('Oxford store'), ('Ultra G'), ('MD Perú'), ('Neocarbon'), 
('Risutimport'), ('Genérico'), ('Marybray');

-- ==========================================
-- 3. INSERTAR PRODUCTOS
-- ==========================================
-- Utilizamos subconsultas para que MySQL busque los IDs automáticamente
-- y agregamos una imagen por defecto para que no devuelva NULL en tu DTO
select * from productos;
-- BANDAS ELÁSTICAS (Ejercicio y Rehabilitación)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('500', 'Bandas elásticas', 20.00, 3, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Tunturi'), 'assets/img/default.png'),
('501', 'Bandas elásticas', 39.90, 2, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Carci'), 'assets/img/default.png'),
('502', 'Bandas elásticas', 78.90, 1, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Care fitness'), 'assets/img/default.png'),
('503', 'Bandas elásticas', 59.00, 5, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'xtreme sport'), 'assets/img/default.png'),
('504', 'Bandas elásticas', 65.90, 4, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'BOSU Fitness'), 'assets/img/default.png');

-- COMPRESAS CALIENTES Y FRÍAS (Termoterapia)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('400', 'Compresas calientes y frías', 100.50, 3, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Thermoform'), 'assets/img/default.png'),
('401', 'Compresas calientes y frías', 90.30, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'BIMEDICA'), 'assets/img/default.png'),
('402', 'Compresas calientes y frías', 136.50, 7, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Hydrocollator Hotpac'), 'assets/img/default.png'),
('403', 'Compresas calientes y frías', 187.20, 2, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Chattanooga'), 'assets/img/default.png'),
('404', 'Compresas calientes y frías', 214.50, 5, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Hydrocollator Hotpac'), 'assets/img/default.png');

-- FOAM ROLLER (Ejercicio y Rehabilitación)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('740', 'Foam roller', 59.00, 6, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Proiron Perú'), 'assets/img/default.png'),
('741', 'Foam roller', 34.00, 4, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Ultimate fitness Perú'), 'assets/img/default.png'),
('742', 'Foam roller', 42.00, 5, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Ripley'), 'assets/img/default.png'),
('743', 'Foam roller', 99.00, 10, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Promart'), 'assets/img/default.png'),
('744', 'Foam roller', 77.00, 8, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Oxford Perú'), 'assets/img/default.png');

-- HANDS PRO (Masoterapia)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('235', 'Hands pro', 59.90, 6, (SELECT id FROM categorias WHERE nombre = 'Masoterapia'), (SELECT id FROM marcas WHERE nombre = 'Neck Jamp'), 'assets/img/default.png'),
('236', 'Hands pro', 40.80, 3, (SELECT id FROM categorias WHERE nombre = 'Masoterapia'), (SELECT id FROM marcas WHERE nombre = 'Tumi Healer'), 'assets/img/default.png'),
('237', 'Hands pro', 250.00, 5, (SELECT id FROM categorias WHERE nombre = 'Masoterapia'), (SELECT id FROM marcas WHERE nombre = 'JP REHAB SRL'), 'assets/img/default.png'),
('238', 'Hands pro', 109.00, 3, (SELECT id FROM categorias WHERE nombre = 'Masoterapia'), (SELECT id FROM marcas WHERE nombre = 'Prohands'), 'assets/img/default.png'),
('239', 'Hands pro', 163.00, 3, (SELECT id FROM categorias WHERE nombre = 'Masoterapia'), (SELECT id FROM marcas WHERE nombre = 'Prohands'), 'assets/img/default.png');

-- INFRARROJO (Termoterapia)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('652', 'Infrarrojo', 120.00, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'HydroSun'), 'assets/img/default.png'),
('653', 'Infrarrojo', 154.00, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Thera'), 'assets/img/default.png'),
('654', 'Infrarrojo', 237.90, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Contect'), 'assets/img/default.png'),
('655', 'Infrarrojo', 43.00, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'Medior'), 'assets/img/default.png'),
('656', 'Infrarrojo', 310.00, 4, (SELECT id FROM categorias WHERE nombre = 'Termoterapia'), (SELECT id FROM marcas WHERE nombre = 'PERUMASSAGE'), 'assets/img/default.png');

-- MAGNETO TERAPÉUTICO (Electroterapia)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('440', 'Magneto terapéutico', 4500.00, 4, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Magneto'), 'assets/img/default.png'),
('441', 'Magneto terapéutico', 6000.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Tens'), 'assets/img/default.png'),
('442', 'Magneto terapéutico', 3650.00, 2, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Globus'), 'assets/img/default.png'),
('443', 'Magneto terapéutico', 5900.00, 1, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Fisio Medic Market'), 'assets/img/default.png'),
('444', 'Magneto terapéutico', 5190.00, 5, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Meditea'), 'assets/img/default.png');

-- PELOTAS DE PERCUSIÓN (Ejercicio y Rehabilitación)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('860', 'Pelotas de percusión', 136.40, 2, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Trideer'), 'assets/img/default.png'),
('861', 'Pelotas de percusión', 292.96, 6, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Gymnic'), 'assets/img/default.png'),
('862', 'Pelotas de percusión', 50.00, 7, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Boldfit'), 'assets/img/default.png'),
('863', 'Pelotas de percusión', 59.00, 4, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Gymnic'), 'assets/img/default.png'),
('864', 'Pelotas de percusión', 47.00, 8, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Holgu'), 'assets/img/default.png');

-- PELOTAS PROPIOCEPCIÓN (Ejercicio y Rehabilitación)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('760', 'Pelotas propiocepción', 30.00, 2, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'JrMedical'), 'assets/img/default.png'),
('761', 'Pelotas propiocepción', 50.00, 6, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Chiquilandia'), 'assets/img/default.png'),
('762', 'Pelotas propiocepción', 245.00, 2, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Oxford store'), 'assets/img/default.png'),
('763', 'Pelotas propiocepción', 175.00, 6, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Ultra G'), 'assets/img/default.png'),
('764', 'Pelotas propiocepción', 289.00, 6, (SELECT id FROM categorias WHERE nombre = 'Ejercicio y Rehabilitación'), (SELECT id FROM marcas WHERE nombre = 'Promart'), 'assets/img/default.png');

-- TENS (Electroterapia)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('540', 'Tens', 240.00, 7, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'MD Perú'), 'assets/img/default.png'),
('541', 'Tens', 310.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Tens'), 'assets/img/default.png'),
('542', 'Tens', 252.00, 5, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Neocarbon'), 'assets/img/default.png'),
('543', 'Tens', 70.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'PERUMASSAGE'), 'assets/img/default.png'),
('544', 'Tens', 64.00, 1, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Risutimport'), 'assets/img/default.png');

-- ULTRASONIDO TERAPÉUTICO (Electroterapia)
-- Nota: Se ajustaron los SKU de los últimos 3 para evitar error de 'Duplicate Entry' (937A, 937B, 937C)
INSERT INTO productos (codigo_sku, nombre_generico, precio_unitario, stock_actual, id_categoria, id_marca, imagen_url) VALUES 
('935', 'Ultrasonido terapéutico', 399.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Genérico'), 'assets/img/default.png'),
('936', 'Ultrasonido terapéutico', 420.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Marybray'), 'assets/img/default.png'),
('937A', 'Ultrasonido terapéutico', 3900.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Carci'), 'assets/img/default.png'),
('937B', 'Ultrasonido terapéutico', 5700.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Carci'), 'assets/img/default.png'),
('937C', 'Ultrasonido terapéutico', 3500.00, 3, (SELECT id FROM categorias WHERE nombre = 'Electroterapia'), (SELECT id FROM marcas WHERE nombre = 'Carci'), 'assets/img/default.png');