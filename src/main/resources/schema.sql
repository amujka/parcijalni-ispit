CREATE TABLE USERS (
   id IDENTITY PRIMARY KEY ,
  username VARCHAR(50) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL
);

CREATE TABLE Student (
     id IDENTITY PRIMARY KEY ,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL
);

-- Kreiranje tablice PROGRAM_OBRAZOVANJA
CREATE TABLE EducationProgram (
     id IDENTITY PRIMARY KEY ,
    name VARCHAR(100) NOT NULL,
    csvet INT NOT NULL
);
-- Kreiranje tablice UPIS
CREATE TABLE Enrollment (
    id IDENTITY PRIMARY KEY ,
    studentId INT REFERENCES EducationProgram(id),
    educationProgramId INT REFERENCES Student(id)
);