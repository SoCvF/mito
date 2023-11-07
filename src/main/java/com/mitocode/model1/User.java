package com.mitocode.model1;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(name = "user_data")
public class User {

    @Id
    private Integer idUser;

    @ManyToOne
    @JoinColumn(name = "id_Role", nullable = false)
    private Role role;
    @Column(length = 50,nullable = false)
    private String username;
    @Column(length = 60,nullable = false)
    private String password;
    @Column(nullable = false)
    private boolean enabled;
}
