package com.devIT.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Table(name="user_order")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Date orderDate;
	private String orderStatus;
	private BigDecimal orderTotal;

	@ManyToOne
	private User user;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
		Order order = (Order) o;
		return Objects.equals(id, order.id);
	}

	@Override
	public int hashCode() {
		return 0;
	}
}
