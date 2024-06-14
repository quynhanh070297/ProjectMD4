package rikkei.projectmodule4.model.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "categories")
public class Category
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;

    @Column(nullable = false, length = 100)
    private String categoryName;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private boolean status;
}
