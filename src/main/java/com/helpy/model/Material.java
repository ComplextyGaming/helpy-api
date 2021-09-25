package com.helpy.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
@Table(name = "material")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double cost;

    private Double discount;

    @OneToOne
    @JoinColumn(name = "gameId", nullable = true, foreignKey = @ForeignKey(name="FK_game_material"))
    Game game;

    @OneToOne
    @JoinColumn(name = "expertId", nullable = true, foreignKey = @ForeignKey(name="FK_expert_material"))
    Expert expert;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "material_tags",
            joinColumns = {@JoinColumn(name = "material_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")})
    private List<Tag> tags;

}
