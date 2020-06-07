package application.views.login;

import application.views.Tela;
import application.views.principal.TelaPrincipal;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
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

    private Label labelLogin;

    private TextField login;

    private PasswordField password;

    private Button buttonLogin;

    public TelaLogin() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1000, 800);
    }

    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;
        labelLogin = new Label("Clínica Médica");
        labelLogin.relocate(320, 250);
        labelLogin.setFont(new Font("Arial", 50));

        login = new TextField();
        login.relocate(350, 350);
        login.setMinWidth(250);

        password = new PasswordField();
        password.relocate(350, 450);
        password.setMinWidth(250);

        buttonLogin = new Button("Entrar");
        buttonLogin.relocate(380, 550);
        buttonLogin.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonLogin.setMinWidth(200);
        buttonLogin.setMinHeight(30);
        buttonLogin.setOnMouseClicked(event -> fazerLogin());

        pane.getChildren().add(labelLogin);
        pane.getChildren().add(login);
        pane.getChildren().add(password);
        pane.getChildren().add(buttonLogin);

        Image imgUserAdd = new Image("resources/images/background.jpg");
        BackgroundSize backgroundSize = new BackgroundSize(0, 0, true, true, true, true);
        BackgroundImage backgroundImage = new BackgroundImage(imgUserAdd, NO_REPEAT, NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);

        pane.setBackground(new Background(backgroundImage));
        stage.setScene(scene);
        stage.setTitle("Login");
    }

    public void fazerLogin() {
        if (!login.getText().equals("") && !password.getText().equals("")) {
            new TelaPrincipal().mountScene(stage);
        } else {
            Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
            dialogoErro.setHeaderText("Login ou senha inválida!");
            dialogoErro.showAndWait();
        }
    }
}
