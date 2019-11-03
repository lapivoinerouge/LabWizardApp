package com.lab.wizard.domain.result;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lab.wizard.domain.user.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "results")
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "undone_id")
    private UndoneResult undoneResult;

//    @NotNull
//    @ManyToOne
//    @JoinColumn(name = "patient_id")
//    private Patient patient;
//
//    @NotNull
//    @Column(name = "material")
//    private String material;
//
//    @NotNull
//    @Column(name = "receive_date")
//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
//    private LocalDate receiveDate;

    @NotNull
    @Column(name = "result")
    private String result;

    @NotNull
    @Column(name = "comment")
    private String comment;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @NotNull
    @Column(name = "finish_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate finishDate;
}

