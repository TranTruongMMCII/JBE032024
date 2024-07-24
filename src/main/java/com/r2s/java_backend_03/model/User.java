package com.r2s.java_backend_03.model;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity(name = "tbl_user")
public class User {

	@Id
	@Column(name = "col_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "col_name", nullable = false)
//	@NotBlank
//	@Size(min = 10)
	private String name;

	@Column(name = "col_email", length = 50, nullable = true)
//	@Email
	private String email;

	@Column(name = "col_is_deleted", columnDefinition = "bit default 0")
	private boolean deleted;

	public boolean getDeleted() {
		return this.deleted;
	}
	
	@Column(unique = true)
	private String userName;
	
	private String password;

	@OneToOne(cascade = { CascadeType.ALL })
	@JoinColumn(name = "col_identification_id", referencedColumnName = "col_id")
//	@JsonIgnore
//	@JsonManagedReference
	private Identification identification;

//	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	@OneToMany(mappedBy = "user")
	private List<Address> addresses = new ArrayList<>();

//	@ManyToMany
//	@JoinTable(
//			name = "user_course", 
//			joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "col_id"), 
//			inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id")
//	)
//	private List<Course> courses = new ArrayList<>();
	
	@OneToMany(mappedBy = "user")
	private List<UserCourse> userCourses = new ArrayList<>();
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "role_user",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "col_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private List<Role> roles = new ArrayList<>();
}
