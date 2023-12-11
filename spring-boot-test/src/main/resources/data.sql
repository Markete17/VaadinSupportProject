CREATE TABLE enlaces (
    id INT PRIMARY KEY,
    nombre VARCHAR(255),
    descripcion VARCHAR(255),
    id_enlace_padre INT,
    FOREIGN KEY (id_enlace_padre) REFERENCES enlaces(id)
);

-- Insertar Valoración (Categoría principal)
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('1', 'Valoración', 'El enlace de Valoración te brinda la oportunidad de proporcionar comentarios sobre tu experiencia. Nos encantaría saber tus pensamientos para ofrecerte un servicio aún mejor', null);

-- Insertar Pruebas Complementarias (Categoría principal)
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('2', 'Pruebas Complementarias', 'Categoría principal para pruebas complementarias médicas', null);

-- Insertar Subcategorías de Pruebas Complementarias
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('3', 'Laboratorio', 'Subcategoría de pruebas complementarias - Laboratorio', '2');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('4', 'Imagen', 'Subcategoría de pruebas complementarias - Imagen', '2');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('5', 'ECG', 'Subcategoría de pruebas complementarias - ECG', '2');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('6', 'Endoscopias', 'Subcategoría de pruebas complementarias - Endoscopias', '2');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('7', 'Anatomía Patológica', 'Subcategoría de pruebas complementarias - Anatomía Patológica', '2');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('8', 'Otras', 'Otras subcategorías de pruebas complementarias', '2');

-- Insertar Intervenciones (Categoría principal)
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('9', 'Intervenciones', 'Categoría principal para intervenciones médicas', null);

-- Insertar Documentación (Categoría principal)
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('10', 'Documentación', 'Categoría principal para documentación médica', null);

-- Insertar Otros (Categoría principal)
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('11', 'Otros', 'Categoría principal para otros enlaces', null);

-- Insertar subenlaces a Laboratorio
INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('12', 'Subenlace 1 de Laboratorio', 'Descripción del subenlace 1 de Laboratorio', '3');

INSERT INTO enlaces (id, nombre, descripcion, id_enlace_padre) 
VALUES ('13', 'Subenlace 2 de Laboratorio', 'Descripción del subenlace 2 de Laboratorio', '3');
