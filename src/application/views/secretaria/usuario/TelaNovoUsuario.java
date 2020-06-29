package application.views.secretaria.usuario;

import application.controller.PerfilController;
import application.controller.UsuarioController;
import application.model.Perfil;
import application.model.Usuario;
import application.views.Tela;
import application.views.secretaria.util.BotaoVoltar;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaNovoUsuario implements Tela {

    private Stage stage;

    private final Pane pane;

    private final Scene scene;

    private final UsuarioController controller;

    private final PerfilController perfilController;

    private TextField nomeText;

    private TextField loginText;

    private PasswordField passwordField;

    public TelaNovoUsuario() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.controller = new UsuarioController();
        this.perfilController = new PerfilController();
    }

    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;

        Label titulo = new Label("Novo Usuário");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(480, 50);

        Image usuarioImage = new Image("resources/images/usuario.png");
        ImageView usuarioView = new ImageView(usuarioImage);

        usuarioView.setFitHeight(100);
        usuarioView.setFitWidth(100);
        usuarioView.setPreserveRatio(true);
        usuarioView.relocate(330, 20);

        Label labelNome = new Label("Nome: ");
        labelNome.setFont(new Font("Arial", 16));
        labelNome.relocate(130, 250);

        nomeText = new TextField();
        nomeText.relocate(200, 250);
        nomeText.setMinWidth(250);

        Label labelLogin = new Label("Login: ");
        labelLogin.setFont(new Font("Arial", 16));
        labelLogin.relocate(630, 250);

        loginText = new TextField();
        loginText.relocate(700, 250);
        loginText.setMinWidth(250);

        Label labelSenha = new Label("Senha: ");
        labelSenha.setFont(new Font("Arial", 16));
        labelSenha.relocate(130, 350);

        passwordField = new PasswordField();
        passwordField.relocate(200, 350);
        passwordField.setMinWidth(250);

        Button buttonSalvar = new Button("Salvar");
        buttonSalvar.relocate(700, 350);
        buttonSalvar.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonSalvar.setMinWidth(250);
        buttonSalvar.setMinHeight(30);
        buttonSalvar.setOnMouseClicked(event -> salvar());

        BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaUsuarios());

        pane.getChildren().addAll(titulo, usuarioView);
        pane.getChildren().addAll(labelNome, nomeText);
        pane.getChildren().addAll(labelLogin, loginText);
        pane.getChildren().addAll(labelSenha, passwordField);
        pane.getChildren().addAll(buttonSalvar, botaoVoltar.getButton());

        stage.setScene(scene);
        stage.setTitle("Novo Usuário");
    }

    private void salvar() {
        if (validFields()) {
            try {
                Perfil perfil = perfilController.findByDescription("Secretária");
                Usuario usuario = Usuario.builder()
                        .nome(nomeText.getText())
                        .login(loginText.getText())
                        .perfil(perfil)
                        .senha(passwordField.getText())
                        .build();
                controller.save(usuario);
                successMessage("Usuário ".concat(usuario.getNome()).concat(" foi salvo com sucesso!"));
                new TelaUsuarios().mountScene(stage);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                errorMessage("Ocorreu um erro ao salvar o usuário, tente novamente mais tarde...");
            }
        }
    }

    private boolean validFields() {
        if (nomeText.getText().equals("") || loginText.getText().equals("") || passwordField.getText().equals("")) {
            errorMessage("Preencha todos os campos, por favor!");
            return false;
        }
        boolean isDuplicated = false;
        try {
            isDuplicated = loginIsDuplicated();
        } catch (Exception exception) {
            errorMessage("Erro ao buscar usuários, tente novamente mais tarde...");
            return false;
        }
        if (isDuplicated) {
            errorMessage("Login já cadastrado!");
            return false;
        }
        return true;
    }

    private boolean loginIsDuplicated() throws Exception {
        return new UsuarioController().isDuplicateLogin(loginText.getText());
    }
}
