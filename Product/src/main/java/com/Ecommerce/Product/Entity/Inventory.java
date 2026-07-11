package com.Ecommerce.Product.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Inventory {

      @Id
      @GeneratedValue(strategy = GenerationType.IDENTITY)
      private Long inventoryId;

      private Integer availableQuantity;

      private Integer ReservedQuantity;

      @OneToOne
      @JoinColumn(name = "productId")
      private Product product;
}
