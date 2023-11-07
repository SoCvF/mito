package com.mitocode.model1;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Role {
    @EqualsAndHashCode.Include
    @Id
    private Integer idRole;

    @Column(length = 20, nullable = false)
    private String name;

    @Column(nullable = false)
    private boolean enabled;

}
