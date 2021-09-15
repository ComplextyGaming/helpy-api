package com.helpy.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "reviews")
@AllArgsConstructor
@NoArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "comment")
    private String comment;

    @OneToOne
    @JoinColumn(name = "id_expert", nullable = false, foreignKey = @ForeignKey(name = "FK_request_expert"))
    private Expert expert;

    @OneToOne
    @JoinColumn(name = "id_player", nullable = false, foreignKey = @ForeignKey(name = "FK_request_player"))
    private Player player;
}
