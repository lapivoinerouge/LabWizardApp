package com.lab.wizard.domain.user;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.wizard.domain.result.UndoneResult;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity(name = "patients")
public class Patient {

    public Patient(@NotNull String firstname, @NotNull String lastname, String pesel, @NotNull String email, @NotNull String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.pesel = pesel;
        this.email = email;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @Column(name = "pesel")
    private String pesel;

    @NotNull
    @Column(name = "email")
    private String email;

    @NotNull
    @Column(name = "password")
    private String password;

    @OneToMany(
            targetEntity = UndoneResult.class,
            mappedBy = "patient",
            cascade = CascadeType.REMOVE,
            fetch = FetchType.LAZY
    )
    @JsonIgnore
    private List<UndoneResult> undoneResults;

//    @OneToMany(
//            targetEntity = Result.class,
//            mappedBy = "patient",
//            cascade = CascadeType.REMOVE,
//            fetch = FetchType.LAZY
//    )
    //@JsonBackReference
//    private List<Result> doneResults;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Patient)) return false;

        Patient patient = (Patient) o;

        if (!getPesel().equals(patient.getPesel())) return false;
        return getEmail().equals(patient.getEmail());
    }

    @Override
    public int hashCode() {
        return 31 * getPesel().hashCode();
    }
}
