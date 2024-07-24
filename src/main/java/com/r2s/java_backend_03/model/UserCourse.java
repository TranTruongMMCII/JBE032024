package com.r2s.java_backend_03.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserCourse {

//	@EmbeddedId
//	private UserCourseId id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
//	@MapsId(value = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "course_id")
//	@MapsId(value = "courseId")
	private Course course;

	private Date startDate;

	private Date endDate;

//	@Embeddable
//	static class UserCourseId {
//		@Column(name = "user_id")
//		private int userId;
//
//		@Column(name = "course_id")
//		private int courseId;
//
//		private Date date;
//	}
}
