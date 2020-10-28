CREATE TABLE messages (
    id SERIAL NOT NULL,
    amount bigint,
    due_date date NOT NULL,
    fiscal_code varchar(16) NOT NULL,
    invalid_after_due_date boolean NOT NULL,
    markdown varchar(10000) NOT NULL,
    notice_number varchar(18) NOT NULL,
    subject varchar(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO messages VALUES (1, 0, '2020-11-30', 'ISPXNB32R82Y766F', false, '# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.', '1', 'Welcome to IO, Giovanni');
INSERT INTO messages VALUES (2, 0, '2020-12-31', 'ISPXNB32R82Y766D', false, '# Welcome, Luca Rossi. Your fiscal code is ISPXNB32R82Y766D. I hope you will enjoy IO.', '1', 'Welcome to IO, Luca');