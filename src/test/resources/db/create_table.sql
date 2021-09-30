DROP DATABASE IF EXISTS tests;
CREATE DATABASE tests;

\connect "tests";

CREATE TABLE public."todos" (
    "text" varchar(100) NOT NULL,
    CONSTRAINT "pk_table" PRIMARY KEY ("text")
);
