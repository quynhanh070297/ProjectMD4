package rikkei.projectmodule4.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "order_details")
public class OrderDetail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
    @Column(nullable = false, length = 100)
    private String name;
    @Column(nullable = false)
    private Double unitPrice;
    @Column(nullable = false)
    private int orderQuantity;

}
