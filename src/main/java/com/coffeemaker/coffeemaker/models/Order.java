package com.coffeemaker.coffeemaker.models;
import com.coffeemaker.coffeemaker.enums.DrinkTypes;
import lombok.Data;
import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "CoffeeOrder")
@Data
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Enumerated(EnumType.STRING)
	private DrinkTypes type;

	private Integer quantity;

	float total;

	Date orderDate;

}