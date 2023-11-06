package com.mitocode.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Sale {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idSale;

    @ManyToOne
    @JoinColumn(name = "id_Client", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "id_User", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double total;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double tax;


//SE ESTA FORZANDO QUE AL MOMENTO QUE SE INSERTE DATOS ESTE LO HAGA EN AMBAS PARTES(SALE DETAILS)
    @OneToMany(mappedBy = "sale", cascade = CascadeType.ALL)
    private List<SaleDetail> details;


}
