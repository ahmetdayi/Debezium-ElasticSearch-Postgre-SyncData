package elastickafkadebezium.product.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String unitStock;

    @ManyToMany(cascade = CascadeType.MERGE,fetch = FetchType.LAZY)
    @JoinTable(inverseJoinColumns = @JoinColumn,
    joinColumns = @JoinColumn)
    private List<Category> categoryList;

    public Product(String name, String unitStock, List<Category> categoryList) {
        this.name = name;
        this.unitStock = unitStock;
        this.categoryList = categoryList;
    }
}
