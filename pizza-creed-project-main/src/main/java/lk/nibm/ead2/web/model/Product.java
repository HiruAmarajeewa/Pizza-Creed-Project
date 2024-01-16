package lk.nibm.ead2.web.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "PRODUCT")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<BasketItem> basketItems;

    @Column(name = "Name")
    private @NotBlank String Name;

    @Column(name = "Size")
    private @NotBlank String Size;

    @Column(name = "Price")
    private @NotBlank Double Price;

}
