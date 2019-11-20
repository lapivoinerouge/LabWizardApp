package com.lab.wizard.domain.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.lab.wizard.domain.user.Patient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "undone")
public class UndoneResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @NotNull
    @Column(name = "material")
    private String material;

    @NotNull
    @Column(name = "receive_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate receiveDate;

    @Column(name = "done")
    private boolean done;
}
