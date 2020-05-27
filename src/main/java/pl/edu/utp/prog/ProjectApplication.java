package pl.edu.utp.prog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

@SpringBootApplication
public class ProjectApplication extends Application {
	private ConfigurableApplicationContext springContext;
	private Parent root;
	private FXMLLoader fxmlLoader;

	public static void main(String[] args) {
		launch(ProjectApplication.class, args);
	}

	@Override
	public void init() {
		springContext = SpringApplication.run(ProjectApplication.class);
		fxmlLoader = new FXMLLoader();
		fxmlLoader.setControllerFactory(springContext::getBean);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		fxmlLoader.setLocation(getClass().getResource("/fxviews/Main.fxml"));
		root = fxmlLoader.load();
		Scene scene = new Scene(root, 300, 200);
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public void stop() {
		springContext.stop();
	}

}
