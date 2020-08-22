package com.lab.wizard.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.lab.wizard.domain.result.UndoneResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "patients")
public class Patient {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @NonNull
    @Column(name = "firstname")
    private String firstname;

    @NotNull
    @NonNull
    @Column(name = "lastname")
    private String lastname;

    @NotNull
    @NonNull
    @Column(name = "pesel")
    private String pesel;

    @NotNull
    @NonNull
    @Column(name = "email")
    private String email;

    @NotNull
    @NonNull
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
