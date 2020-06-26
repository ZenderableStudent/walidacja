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
import pl.edu.utp.prog.services.ValidationService;

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

	@Autowired
	private ValidationService valServ;

	@FXML
	void initialize() {
		ValidationSupport vsForName = new ValidationSupport();
		vsForName.registerValidator(TextField_Name, valServ.getValidator(Student.class,"name"));
		
		ValidationSupport vsForEmail = new ValidationSupport();
		vsForEmail.registerValidator(TextField_Email, valServ.getValidator(Student.class, "email"));
		
		ValidationSupport vsForCheck = new ValidationSupport();
		vsForCheck.registerValidator(CheckBox_Student, valServ.getValidator(Student.class, "isStudent"));
		
		Button_Confirm.disableProperty().bind(vsForName.invalidProperty().or(vsForCheck.invalidProperty().or(vsForEmail.invalidProperty())));
	}

	@FXML
	void buttonAction(ActionEvent event) {
		if(repo.existsByEmail(this.TextField_Email.getText())) {
			System.out.println("Email exist already.");
		}
		else {
		Student student = new Student();
		student.setName(this.TextField_Name.getText());
		student.setEmail(this.TextField_Email.getText());
		student.setStudent(true);
		repo.save(student);
		System.out.println("Added to the database!");

		 this.TextField_Name.clear();
		 this.TextField_Email.clear();
		 this.CheckBox_Student.setSelected(false);

		 printStudentsFromDB();
		}
		
	}
	void printStudentsFromDB() {
		
		List<Student> students = repo.findAll();
		 for (Student s : students) {
		 System.out.println("id:"+s.getId() + " name:" + s.getName()+ " email:" + s.getEmail());
		 }
	}


}
