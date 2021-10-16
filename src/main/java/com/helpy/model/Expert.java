package com.helpy.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@DiscriminatorValue("experts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expert extends User {
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name = "game_id", nullable = true,
    foreignKey = @ForeignKey(name = "FK_expert_game"))
    private Game game;
}
