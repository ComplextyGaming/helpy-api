package com.helpy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity(name = "solicitudes")
@AllArgsConstructor
@NoArgsConstructor
public class Solicitude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;
    @OneToOne
    @JoinColumn(name = "id_expert", nullable = false, foreignKey = @ForeignKey(name = "FK_request_expert"))
    private Expert expert;
}
