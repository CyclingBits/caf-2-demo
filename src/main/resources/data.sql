-- Initialize contractors
INSERT INTO contractor (name, nip) VALUES ('Firma ABC', '1234567890');
INSERT INTO contractor (name, nip) VALUES ('Przedsiębiorstwo XYZ', '9876543210');
INSERT INTO contractor (name, nip) VALUES ('Spółka Handlowa', '5678901234');
INSERT INTO contractor (name, nip) VALUES ('Zakład Usługowy', '2345678901');

-- Initialize limits
INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan) 
VALUES (1, 'RETAIL', 100000.00, 'PLN', '2024-01-01', '2025-12-31', 25000.00, false, 'LEASING', true, false);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan) 
VALUES (1, 'RETAIL', 50000.00, 'EUR', '2024-01-01', '2024-12-31', 5000.00, true, 'ATHLON', false, true);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan) 
VALUES (2, 'RETAIL', 75000.00, 'PLN', '2024-01-01', '2024-12-31', 15000.00, false, 'LEASING', true, true);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan) 
VALUES (3, 'RETAIL', 200000.00, 'PLN', '2024-01-01', '2024-12-31', 50000.00, false, 'ATHLON', false, false);

-- Initialize CAF records
INSERT INTO caf (limit_id, type, status) VALUES (1, 'INCREASE', 'COMPLETED');
INSERT INTO caf (limit_id, type, status) VALUES (1, 'EXTENSION', 'IN_PROGRESS');
INSERT INTO caf (limit_id, type, status) VALUES (2, 'SUSPENSION', 'COMPLETED');
INSERT INTO caf (limit_id, type, status) VALUES (3, 'INCREASE', 'IN_PROGRESS');

-- Initialize IZP records
INSERT INTO izp (points, contractor_id, date) 
VALUES (120, 1, '2024-01-15');

INSERT INTO izp (points, contractor_id, date) 
VALUES (285, 1, '2024-02-10');

INSERT INTO izp (points, contractor_id, date) 
VALUES (350, 2, '2024-01-20');

INSERT INTO izp (points, contractor_id, date) 
VALUES (200, 3, '2024-03-05');

-- Initialize Rating records
INSERT INTO rating (contractor_id, points, date) 
VALUES (1, 5, '2024-01-20');

INSERT INTO rating (contractor_id, points, date) 
VALUES (1, 8, '2024-02-15');

INSERT INTO rating (contractor_id, points, date) 
VALUES (2, 7, '2024-01-25');

INSERT INTO rating (contractor_id, points, date) 
VALUES (3, 9, '2024-03-10');