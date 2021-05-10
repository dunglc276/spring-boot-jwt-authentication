CREATE TABLE users (
  id serial PRIMARY KEY,
  username varchar(255) NOT NULL,
  email varchar (255) NOT NULL,
  password varchar(255) NOT NULL
);

CREATE TABLE roles (
  id serial PRIMARY KEY ,
  role varchar(255) NOT NULL
);

CREATE TABLE user_roles (
  user_id bigint NULL ,
  role_id bigint NULL
);