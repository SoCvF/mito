package com.mitocode.model;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Client {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    @Column(length = 150, nullable = false)
    private String firstName;
    @Column(length = 150, nullable = false)
    private String lasName;
    @Column(length = 10, nullable = false, unique = true)
    private String carId;
    @Column(length = 10, nullable = false)
    private String phoneNumber;
    @Column(length = 50, nullable = false)
    private String email;
    @Column(length = 100, nullable = false)
    private String address;

}
