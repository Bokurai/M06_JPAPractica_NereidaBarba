CREATE TABLE IF NOT EXISTS libro_autor (
    id_libro SERIAL,
    id_autor SERIAL,
    FOREIGN KEY (id_libro) REFERENCES libros(id_libro),
    FOREIGN KEY (id_autor) REFERENCES autores(id_autor)
);

