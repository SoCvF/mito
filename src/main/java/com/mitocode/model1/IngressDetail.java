package com.mitocode.model1;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(IngressDetailPK.class)
public class IngressDetail {

    @Id
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Ingress ingress;

    @Id
    @SuppressWarnings("JpaAttributeTypeInspection")
    private Product product;

    @Column(nullable = false)
    private short quantity;

    @Column(columnDefinition = "decimal(6,2)", nullable = false)
    private short cost ;
}
