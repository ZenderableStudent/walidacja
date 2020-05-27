package pl.edu.utp.prog.annotations;

import javax.validation.ConstraintValidatorContext;

import pl.edu.utp.prog.repository.StudentRepository;

public class UniqueValidator {
	private StudentRepository studentRepository;

	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(studentRepository  == null) {
			return true;
		}
		return value != null && !studentRepository.existsByEmail(value);
	}

}
