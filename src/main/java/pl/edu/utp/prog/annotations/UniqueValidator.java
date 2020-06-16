package pl.edu.utp.prog.annotations;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.utp.prog.repository.StudentRepository;

public class UniqueValidator implements ConstraintValidator<Unique, String> {
	@Autowired
	private StudentRepository studentRepository;
	@Override
	public void initialize(Unique format) {}
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if(studentRepository  == null) {
			return true;
		}
		return !studentRepository.existsByEmail(value);
	}

}
