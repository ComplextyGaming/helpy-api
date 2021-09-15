package com.helpy.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

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
