package com.algebra.parcijalni_ispit.student;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is required")
    @Size(min = 3, max = 10)
    @Column(name = "firstName")
    private String firstName;
    @NotBlank(message = "Lastname is required")
    @Size(min = 3, max = 20)
    @Column(name = "lastName")
    private String lastName;


    public Student(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
