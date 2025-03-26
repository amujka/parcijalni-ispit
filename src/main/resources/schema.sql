CREATE TABLE USERS (
    id INT IDENTITY(1,1) PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE Student (
    id INT IDENTITY(1,1) PRIMARY KEY,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

-- Kreiranje tablice PROGRAM_OBRAZOVANJA
CREATE TABLE Education_program (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    csvet INT NOT NULL
);
-- Kreiranje tablice UPIS
CREATE TABLE Enrollment (
   id INT IDENTITY(1,1) PRIMARY KEY,
    Education_program_ID INT REFERENCES Education_program(id),
    Student_ID INT REFERENCES Student(id)
);