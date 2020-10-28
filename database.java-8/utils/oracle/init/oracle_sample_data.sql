ALTER SESSION SET CURRENT_SCHEMA = system;

CREATE TABLE messages (
    id number NOT NULL,
    amount number,
    due_date date NOT NULL,
    fiscal_code VARCHAR2(16) NOT NULL,
    invalid_after_due_date number(1) NOT NULL,
    markdown clob NOT NULL,
    notice_number VARCHAR2(18) NOT NULL,
    subject VARCHAR2(100) NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO messages VALUES (1, 0, TO_DATE ('2020-11-30', 'yyyy-mm-dd') , 'ISPXNB32R82Y766F', 0, '# Welcome, Giovanni Rossi. Your fiscal code is ISPXNB32R82Y766F. I hope you will enjoy IO.', '1', 'Welcome to IO, Giovanni');
INSERT INTO messages VALUES (2, 0, TO_DATE ('2020-12-31', 'yyyy-mm-dd') , 'ISPXNB32R82Y766D', 0, '# Welcome, Luca Rossi. Your fiscal code is ISPXNB32R82Y766D. I hope you will enjoy IO.', '1', 'Welcome to IO, Luca');