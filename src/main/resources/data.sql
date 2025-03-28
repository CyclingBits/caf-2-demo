-- Initialize contractors
INSERT INTO contractor (name, nip) VALUES ('Firma ABC', '1234567890');
INSERT INTO contractor (name, nip) VALUES ('Przedsiębiorstwo XYZ', '9876543210');
INSERT INTO contractor (name, nip) VALUES ('Spółka Handlowa', '5678901234');
INSERT INTO contractor (name, nip) VALUES ('Zakład Usługowy', '2345678901');

-- Initialize limits
INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used) 
VALUES (1, 'RETAIL', 100000.00, 'PLN', '2024-01-01', '2025-12-31', 25000.00);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used) 
VALUES (1, 'RETAIL', 50000.00, 'EUR', '2024-01-01', '2024-12-31', 5000.00);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used) 
VALUES (2, 'RETAIL', 75000.00, 'PLN', '2024-01-01', '2024-12-31', 15000.00);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used) 
VALUES (3, 'RETAIL', 200000.00, 'PLN', '2024-01-01', '2024-12-31', 50000.00);