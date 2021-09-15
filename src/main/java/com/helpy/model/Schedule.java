package com.helpy.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity(name = "schedulesssa")
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "DATE")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @Column(name = "state")
    private String state;

    @Column(name = "starthour")
    private Long startHour;

    @Column(name = "startmin")
    private Long startMin;

    @Column(name = "finishhour")
    private Long finishHour;

    @Column(name = "finishmin")
    private Long finishMin;

    @OneToOne
    @JoinColumn(name = "playerId", nullable = true, foreignKey = @ForeignKey(name="FK_player_schedule"))
    Player player;

    @OneToOne
    @JoinColumn(name = "expertId", nullable = true, foreignKey = @ForeignKey(name="FK_expert_schedule"))
    Expert expert;
}
