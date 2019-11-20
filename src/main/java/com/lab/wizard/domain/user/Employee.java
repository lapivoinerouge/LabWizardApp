package com.lab.wizard.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.wizard.domain.result.Result;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "employees")
public class Employee {

    public Employee(@NotNull String firstname, @NotNull String lastname, @NotNull String licence, @NotNull String login, @NotNull String password, @NotNull String role) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.licence = licence;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    public Employee(Long id, @NotNull String firstname, @NotNull String lastname, @NotNull String licence, @NotNull String login, @NotNull String password, @NotNull String role) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.licence = licence;
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "licence")
    private String licence;

    @NotNull
    @Column(name = "login")
    private String login;

    @NotNull
    @Column(name = "password")
    private String password;

    @NotNull
    @Column(name = "role")
    private String role;

    @OneToMany(
            targetEntity = Result.class,
            mappedBy = "employee",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<Result> results;
}
