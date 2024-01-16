package lk.nibm.ead2.web.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART_ITEM")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BasketItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "Quantity")
    private Long quantity;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    private Double price;

    @ManyToOne
    private Basket basket;

}
