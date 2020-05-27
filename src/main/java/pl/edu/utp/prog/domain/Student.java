package pl.edu.utp.prog.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.*;

import org.springframework.data.annotation.Transient;

import pl.edu.utp.prog.annotations.Unique;



@Entity
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@Size(min = 2, max = 20, message = "The length should be between 2 and 20 letters!")
	@Pattern(regexp = "^[a-zA-Z]+", message = "Only letters allowed!")
	private String name;
	
	@Email(message = "This is not correct email address!")
	@Unique
	@NotBlank
	private String email;
	
	@Transient
	@AssertTrue(message = "You have to confirm that are the student!")
	private Boolean isStudent;
	public Student() {};
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setStudent(boolean isStudent) {
		this.isStudent = isStudent;
	}

	public String getEmail() {
		return email;
	}

}