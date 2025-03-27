-- Umetanje podataka u tablicu POLAZNIK
INSERT INTO Student(firstName, lastName) VALUES
('Ivo', 'Ivic'),
('Ana', 'Anic'),
('Marko', 'Markic'),
('Petra', 'Petric');
-- Umetanje podataka u tablicu PROGRAM_OBRAZOVANJA
INSERT INTO EducationProgram(name, csvet) VALUES
('Prvi', 3),
('Drugi', 5),
('Treći', 2);

-- Umetanje podataka u tablicu UPIS
INSERT INTO Enrollment(studentId, educationProgramId) VALUES
(1, 1),
(2, 2),
(3, 3),

insert into USERS(id, username, password)
values
    ('user', '$2a$12$h0HcS2QDb/7zPASbLa2GoOTSRP6CWK0oX7pCK.dPjkM6L5N4pNovi'), -- password = user
    ('admin', '$2a$12$INo0nbj40sQrTB7b28KJput/bNltGmFyCfRsUhvy73qcXo5/XdsTG'); -- password = admin (edited)