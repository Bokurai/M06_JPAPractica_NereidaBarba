CREATE TABLE autor (
    id_autor SERIAL PRIMARY KEY,
    nombre VARCHAR(255),
    apellidos VARCHAR(255)
);

CREATE TABLE libro (
    id_libro SERIAL PRIMARY KEY,
    id_tema VARCHAR(255),
    titulo VARCHAR(255),
    titulo_resumen VARCHAR(255),
    resumen TEXT,
    FOREIGN KEY (id_tema) REFERENCES tema(id_tema)
);

CREATE TABLE tema (
    id_tema VARCHAR(255) PRIMARY KEY
);

CREATE TABLE libro_autor (
    id_libro INT,
    id_autor INT,
    PRIMARY KEY (id_libro, id_autor),
    FOREIGN KEY (id_libro) REFERENCES libro(id_libro),
    FOREIGN KEY (id_autor) REFERENCES autor(id_autor)
);
