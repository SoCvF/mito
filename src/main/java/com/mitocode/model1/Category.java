package com.mitocode.model1;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Category {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCategory;
    @Column(name = "category_name",length = 50, nullable = false)
    private String name;
    @Column(length = 25, nullable = false)
    private String description;
    @Column(nullable = false)
    private boolean enabled;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }
}
