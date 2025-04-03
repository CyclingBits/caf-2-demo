-- Initialize contractors
INSERT INTO contractor (name, nip) VALUES ('Firma ABC', '1234567890');
INSERT INTO contractor (name, nip) VALUES ('Przedsiębiorstwo XYZ', '9876543210');
INSERT INTO contractor (name, nip) VALUES ('Spółka Handlowa', '5678901234');
INSERT INTO contractor (name, nip) VALUES ('Zakład Usługowy', '2345678901');

-- Initialize limits
INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan, aml_relevant) 
VALUES (1, 'RETAIL', 100000.00, 'PLN', '2024-01-01', '2025-12-31', 95000.00, false, 'LEASING', true, false, true);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan, aml_relevant) 
VALUES (1, 'RETAIL', 50000.00, 'EUR', '2024-01-01', '2025-12-31', 5000.00, true, 'ATHLON', false, true, false);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan, aml_relevant)
VALUES (1, 'RETAIL', 20000.00, 'PLN', '2024-01-01', '2025-12-31', 5000.00, false, 'LEASING', false, true, false);


INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan, aml_relevant) 
VALUES (2, 'RETAIL', 75000.00, 'PLN', '2024-01-01', '2025-12-31', 15000.00, false, 'LEASING', true, true, true);

INSERT INTO limits (contractor_id, type, limit_value, currency, date_from, date_to, used, suspended, company, leasing, loan, aml_relevant) 
VALUES (3, 'RETAIL', 200000.00, 'PLN', '2024-01-01', '2025-04-10', 50000.00, false, 'ATHLON', false, false, false);

-- Initialize CAF records
INSERT INTO caf (limit_id, type, status) VALUES (1, 'INCREASE', 'IN_PROGRESS');
INSERT INTO caf (limit_id, type, status) VALUES (4, 'SUSPENSION', 'IN_PROGRESS');

-- Initialize CAF requirements
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (1, 'IZP', true);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (1, 'RATING', true);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (1, 'CAUSE', true);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (1, 'ANALYSIS', true);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (1, 'APPROVAL', true);

INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (2, 'IZP', false);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (2, 'RATING', false);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (2, 'CAUSE', false);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (2, 'ANALYSIS', false);
INSERT INTO caf_requirement (caf_id, requirement, fulfilled) VALUES (2, 'APPROVAL', false);

-- Initialize IZP records
INSERT INTO izp (points, contractor_id, date) 
VALUES (120, 1, '2024-01-15');

INSERT INTO izp (points, contractor_id, date) 
VALUES (285, 1, '2024-05-10');

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

-- Initialize Beneficiary records
INSERT INTO beneficiary (contractor_id, name, nip, date) 
VALUES (1, 'Firma Córka 1', '1111111111', '2024-01-15');

INSERT INTO beneficiary (contractor_id, name, nip, date) 
VALUES (1, 'Firma Córka 2', '2222222222', '2024-02-10');

INSERT INTO beneficiary (contractor_id, name, nip, date) 
VALUES (2, 'Spółka Zależna', '3333333333', '2024-01-25');

INSERT INTO beneficiary (contractor_id, name, nip, date) 
VALUES (3, 'Podmiot Powiązany', '4444444444', '2024-03-05');

-- Initialize Document records (empty files for sample data)
INSERT INTO document (contractor_id, date, type, file) 
VALUES (1, '2024-01-20', 'FINANCIAL', RAWTOHEX('Sample document content'));

INSERT INTO document (contractor_id, date, type, file) 
VALUES (1, '2024-01-25', 'REGISTRY', RAWTOHEX('Registry document content'));

INSERT INTO document (contractor_id, date, type, file) 
VALUES (2, '2024-02-15', 'BOOKKEEPING', RAWTOHEX('Another sample document content'));

INSERT INTO document (contractor_id, date, type, file) 
VALUES (3, '2024-03-10', 'AGREEMENT', RAWTOHEX('Agreement document content'));