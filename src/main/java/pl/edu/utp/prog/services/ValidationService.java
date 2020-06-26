package pl.edu.utp.prog.services;
import java.util.Set;

import javax.validation.ConstraintViolation;
import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javafx.scene.control.Control;
@Service

public class ValidationService {
	@Autowired
	private javax.validation.Validator beanValidator;
	public <T,U> Validator<T> getValidator(Class <U> beanClass, String beanFieldName){
		return new Validator<T>() {
			@Override
			public ValidationResult apply(Control control, T newValue) {
				String message="";
				Set<ConstraintViolation<U>> errors= beanValidator.validateValue(beanClass, beanFieldName, newValue);
				for (ConstraintViolation<U> e:errors) {
					message+=e.getMessage()+ "\n";
				}
				return ValidationResult.fromMessageIf(control,message,Severity.ERROR,!errors.isEmpty());
			}
				
		};
	}

}