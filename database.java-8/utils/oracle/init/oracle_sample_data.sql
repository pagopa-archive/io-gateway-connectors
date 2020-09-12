ALTER SESSION SET CURRENT_SCHEMA = system;

CREATE TABLE messages (
    id NUMBER NOT NULL,
    scadenza date NOT NULL,
    destinatario VARCHAR2(16) NOT NULL,
    testo VARCHAR2(1000) NOT NULL,
    titolo VARCHAR2(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO messages (id, scadenza, destinatario, testo, titolo) VALUES (1, TO_DATE ('2020-11-30', 'yyyy-mm-dd') , 'ISPXNB32R82Y766F', '# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.', 'Welcome to IO, Giovanni');
INSERT INTO messages (id, scadenza, destinatario, testo, titolo) VALUES (2, TO_DATE ('2020-12-31', 'yyyy-mm-dd') , 'ISPXNB32R82Y766D', '# Welcome, Luca Rossi. Your fiscal code is ISPXNB32R82Y766D. I hope you will enjoy IO.', 'Welcome to IO, Luca');