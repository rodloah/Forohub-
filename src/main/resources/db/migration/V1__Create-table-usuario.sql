create table usuario (
    id BIGSERIAL not null,
    nombre varchar(100) not null,
    email varchar(100) not null unique,
    password varchar(100) not null,
    primary key (id)
);