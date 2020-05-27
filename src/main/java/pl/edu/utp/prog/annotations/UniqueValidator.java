package pl.edu.utp.prog.annotations;

public class UniqueValidator {
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(studentRepository == null) {
			return true;
		}
		return value != null && !studentRepository.existsByEmail(value);
	}

}
