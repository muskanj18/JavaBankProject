CREATE TABLE transactions (
    TransID INT PRIMARY KEY,
    AcctNo INT,
    OldBal DECIMAL(10, 2),
    TransType VARCHAR(1) CHECK (TransType IN('W', 'D')),
    TransAmt DECIMAL(10, 2),
    NewBal DECIMAL(10, 2),
    TransStat VARCHAR(20)
);



INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (101, 2001, 500.00, 'D', 200.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (102, 2002, 1000.00, 'W', 300.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (103, 2003, 1500.00, 'D', 1000.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (104, 2004, 120.00, 'W', 50.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (105, 2005, 800.00, 'D', 200.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (106, 2006, 3000.00, 'W', 1000.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (107, 2007, 50.00, 'W', 100.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (108, 2008, 200.00, 'D', 500.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (109, 2009, 100.00, 'W', 200.00, NULL, NULL);
INSERT INTO transactions (TransID, AcctNo, OldBal, TransType, TransAmt, NewBal, TransStat) VALUES (110, 2010, 10.00, 'D', 20.00, NULL, NULL);


CREATE TABLE ValidTrans (
    TransID INT PRIMARY KEY NOT NULL,
    TransType VARCHAR(1),
    TransAmt DECIMAL(10, 2),
    Validity VARCHAR(10)
);


CREATE TABLE InValidTrans (
    TransID INT PRIMARY KEY NOT NULL,
    TransType VARCHAR(1),
    TransAmt DECIMAL(10, 2),
    Validity VARCHAR(10)
);