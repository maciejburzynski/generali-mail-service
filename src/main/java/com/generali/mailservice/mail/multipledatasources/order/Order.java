package com.generali.mailservice.mail.multipledatasources.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "orders")
public class Order {

  @Id
  @GeneratedValue
  private Long id;
  private String name;


  public Order(String name) {
    this.name = name;
  }
}
