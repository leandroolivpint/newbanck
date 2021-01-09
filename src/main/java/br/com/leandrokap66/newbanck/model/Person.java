package br.com.leandrokap66.newbanck.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "People")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @NotBlank(message = "Name is mandatory")
    @Column(name = "name")
    private String name;
    @NotBlank(message = "CPF is mandatory")
    @Column(name = "cpf", unique = true)
    private String cpf;
    @NotBlank(message = "Email is mandatory")
    @Column(name = "email", unique = true)
    private String email;
    @NotBlank(message = "Date of birthday is mandatory")
    @Column(name = "date_of_birth")
    private String dateOfBirth;


    public Person() {
        super();
    }

    public Person(long id, String name, String cpf, String email, String dateOfBirth) {
        super();
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
