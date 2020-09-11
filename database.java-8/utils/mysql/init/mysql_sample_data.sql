create database test;

use test;

CREATE TABLE messages (
    id int NOT NULL AUTO_INCREMENT,
    scadenza date NOT NULL,
    destinatario varchar(16) NOT NULL,
    testo varchar(1000) NOT NULL,
    titolo varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO messages (id, scadenza, destinatario, testo, titolo) VALUES (1, '2020-11-30', 'ISPXNB32R82Y766F', '# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.', 'Welcome to IO, Giovanni');
INSERT INTO messages (id, scadenza, destinatario, testo, titolo) VALUES (2, '2020-12-31', 'ISPXNB32R82Y766D', '# Welcome, Luca Rossi. Your fiscal code is ISPXNB32R82Y766D. I hope you will enjoy IO.', 'Welcome to IO, Luca');