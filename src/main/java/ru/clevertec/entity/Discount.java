package ru.clevertec.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "discount")
@Entity
@Builder
@Table(name = "pricediscount")
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "price")
    private Double price;
    @Column(name = "discount", nullable = false)
    private Boolean discount;

    @ToString.Exclude
    @OneToOne(mappedBy = "discount", fetch = FetchType.LAZY)
    private Product product;
}
