package com.r2s.java_backend_03.model;

import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tbl_identification")
@Builder
public class Identification {

	@Id
	@Column(name = "col_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "col_expired_date", nullable = false)
	private Date expiredDate;

	@OneToOne(mappedBy = "identification")
//	@JsonIgnore
//	@JsonBackReference
	private User user;
}
