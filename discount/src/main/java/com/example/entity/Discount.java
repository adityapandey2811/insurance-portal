
package com.example.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.v3.oas.annotations.media.Schema; // Import OpenAPI Schema

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Schema(description = "Discount entity") // OpenAPI Schema
public class Discount {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Schema(description = "The ID of the discount")
	private Long discountId;
	@Schema(description = "The ID of the associated policy")
	private Long policyId;
	@Schema(description = "The value of the discount")
	private Double value;
}
