package com.mitocode.model1;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProduct;

    @ManyToOne
    @JoinColumn(name="idCategory", nullable = false)
    private Category category;

    @Column(length = 50, nullable = false)
    private String name;
    @Column(length = 150, nullable = false)
    private String description;

    //es un número de 6 cifras de las cuales 2 son decimales
    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean enabled;

}
