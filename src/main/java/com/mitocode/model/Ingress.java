package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Ingress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer idIngress;

    @ManyToOne
    @JoinColumn(name = "id_provider", nullable = false)
    private Provider provider;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double salePrice;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double discount ;

    @Column(length = 10, nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private boolean enabled;
}
