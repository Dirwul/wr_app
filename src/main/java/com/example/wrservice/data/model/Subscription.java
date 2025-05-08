package com.example.wrservice.data.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "subscriptions")
public class Subscription {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id", nullable = false)
	private long id;

	@NotBlank
	@Column(name = "service_name")
	private String serviceName;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;
}
