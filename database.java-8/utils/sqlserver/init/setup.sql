CREATE DATABASE test;
GO
USE test;
GO
CREATE TABLE messages (
    id int NOT NULL IDENTITY,
    amount bigint,
    due_date date NOT NULL,
    fiscal_code varchar(16) NOT NULL,
    invalid_after_due_date bit NOT NULL,
    markdown text NOT NULL,
    notice_number varchar(18) NOT NULL,
    subject varchar(100) NOT NULL,
    PRIMARY KEY (id)
)
GO
INSERT INTO messages VALUES (0, '2020-11-30', 'ISPXNB32R82Y766F', 0, '# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.', '1', 'Welcome to IO, Giovanni');
INSERT INTO messages VALUES (1, '2020-12-31', 'ISPXNB32R82Y766D', 0, '# Welcome, Luca Rossi. Your fiscal code is ISPXNB32R82Y766D. I hope you will enjoy IO.', '1', 'Welcome to IO, Luca');
