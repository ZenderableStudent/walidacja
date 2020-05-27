package pl.edu.utp.prog.controllers;

import java.util.List;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationResult;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import pl.edu.utp.prog.domain.Student;
import pl.edu.utp.prog.repository.StudentRepository;

@Controller
public class MainController {

	@FXML
	private Label Label_Name;

	@FXML
	private Label Label_Email;

	@FXML
	private Button Button_Confirm;

	@FXML
	private CheckBox CheckBox_Student;

	@FXML
	private TextField TextField_Name;

	@FXML
	private TextField TextField_Email;

	@Autowired
	private StudentRepository repo;

	@FXML
	void initialize() {
		ValidationSupport vsForTextField = new ValidationSupport();
		vsForTextField.registerValidator(TextField_Name,
				Validator.combine(Validator.createEmptyValidator("This field can't be empty"),
						Validator.createRegexValidator("No numbers here!", "^[a-zA-Z]+", Severity.ERROR),
						Validator.createPredicateValidator(name -> {
							return ((String) name).length() < 15 ? true : false;
						}, "Less letters!", Severity.ERROR)));

		ValidationSupport vsForCheckBox = new ValidationSupport();
		vsForCheckBox.registerValidator(CheckBox_Student,
				(Control c, Boolean newValue) -> ValidationResult.fromErrorIf(c, "Check this checkbox!", !newValue));

		Button_Confirm.disableProperty().bind(vsForTextField.invalidProperty().or(vsForCheckBox.invalidProperty()));
	}

	@FXML
	void buttonAction(ActionEvent event) {
		Student student = new Student();
		student.setName(this.TextField_Name.getText());
		student.setEmail(this.TextField_Email.getText());
		student.setStudent(true);
		repo.save(student);
		System.out.println("Added to the database!");

		//for(ValidationSupport v : val) {v.errorDecorationEnabledProperty().set(false);}
		 this.TextField_Name.clear();
		 this.TextField_Email.clear();
		 this.CheckBox_Student.setSelected(false);

		 printStudentsFromDB();

		
	}
	void printStudentsFromDB() {
		
		List<Student> students = repo.findAll();
		 for (Student s : students) {
		 System.out.println("id:"+s.getId() + " name:" + s.getName()+ " email:" + s.getEmail());
		 }
	}


}
