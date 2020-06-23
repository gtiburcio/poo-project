package application.views.login;

import application.controller.UsuarioController;
import application.views.Tela;
import application.views.principal.TelaPrincipal;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static javafx.scene.layout.BackgroundRepeat.NO_REPEAT;

public class TelaLogin implements Tela {

	private Stage stage;

	private final Scene scene;

	private final Pane pane;

	private TextField login;

	private PasswordField password;

	private UsuarioController controller;

	public TelaLogin() {
		this.pane = new Pane();
		this.scene = new Scene(pane, 1000, 800);
		this.controller = new UsuarioController();
	}

	@Override
	public void mountScene(Stage stage) {
		this.stage = stage;
		Label labelLogin = new Label("Clínica Médica");
		labelLogin.relocate(330, 250);
		labelLogin.setFont(new Font("Arial", 50));

		login = new TextField();
		login.relocate(360, 350);
		login.setMinWidth(250);

		password = new PasswordField();
		password.relocate(360, 450);
		password.setMinWidth(250);

		Button buttonLogin = new Button("Entrar");
		buttonLogin.relocate(390, 550);
		buttonLogin.setStyle("-fx-background-color: #4fddae; -fx-border-radius: 50px");
		buttonLogin.setMinWidth(200);
		buttonLogin.setMinHeight(30);
		buttonLogin.setOnMouseClicked(event -> fazerLogin());

		pane.getChildren().add(labelLogin);
		pane.getChildren().add(login);
		pane.getChildren().add(password);
		pane.getChildren().add(buttonLogin);

		Image imgUserAdd = new Image("resources/images/background.jpg");
		BackgroundSize backgroundSize = new BackgroundSize(0, 0, true, true, true, true);
		BackgroundImage backgroundImage = new BackgroundImage(imgUserAdd, NO_REPEAT, NO_REPEAT,
				BackgroundPosition.CENTER, backgroundSize);

		pane.setBackground(new Background(backgroundImage));
		stage.setScene(scene);
		stage.setTitle("Login");
	}

	public void fazerLogin() {
		if (!login.getText().equals("") && !password.getText().equals("")) {
			try {
				long perfil = controller.authenticate(login.getText(), password.getText());
				
				if(perfil == 0) {
					errorMessage("Login ou senha inválidos!");
				}else {
					new TelaPrincipal().mountScene(stage);
				}

			} catch (Exception e) {
				errorMessage("Falha ao tentar logar no sistema!");
			}
		} else {
			errorMessage("Digite o login e a senha!");
		}
	}
}
