package com.helpy.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("expert")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Expert extends User {
    @Column
    private String description;
}
