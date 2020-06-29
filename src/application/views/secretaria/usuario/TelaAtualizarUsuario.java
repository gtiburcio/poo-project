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

public class TelaAtualizarUsuario implements Tela {

    private Stage stage;

    private final Pane pane;

    private final Scene scene;

    private final Usuario usuario;

    private final UsuarioController controller;

    private final PerfilController perfilController;

    private TextField nomeText;

    private TextField loginText;

    private PasswordField passwordField;

    public TelaAtualizarUsuario(Usuario usuario) {
        this.usuario = usuario;
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.controller = new UsuarioController();
        this.perfilController = new PerfilController();
    }

    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;

        Label titulo = new Label("Atualizar Usuário");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(450, 50);

        Image usuarioImage = new Image("resources/images/usuario.png");
        ImageView usuarioView = new ImageView(usuarioImage);

        usuarioView.setFitHeight(100);
        usuarioView.setFitWidth(100);
        usuarioView.setPreserveRatio(true);
        usuarioView.relocate(330, 20);

        Label labelNome = new Label("Nome: ");
        labelNome.setFont(new Font("Arial", 16));
        labelNome.relocate(130, 250);

        nomeText = new TextField(usuario.getNome());
        nomeText.relocate(200, 250);
        nomeText.setMinWidth(250);

        Label labelLogin = new Label("Login: ");
        labelLogin.setFont(new Font("Arial", 16));
        labelLogin.relocate(630, 250);

        loginText = new TextField(usuario.getLogin());
        loginText.relocate(700, 250);
        loginText.setMinWidth(250);

        Label labelSenha = new Label("Senha: ");
        labelSenha.setFont(new Font("Arial", 16));
        labelSenha.relocate(130, 350);

        passwordField = new PasswordField();
        passwordField.relocate(200, 350);
        passwordField.setMinWidth(250);
        passwordField.setText(usuario.getSenha());

        Button buttonAtualizar = new Button("Atualizar");
        buttonAtualizar.relocate(700, 350);
        buttonAtualizar.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonAtualizar.setMinWidth(250);
        buttonAtualizar.setMinHeight(30);
        buttonAtualizar.setOnMouseClicked(event -> salvar());

        BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaUsuarios());

        pane.getChildren().addAll(titulo, usuarioView);
        pane.getChildren().addAll(labelNome, nomeText);
        pane.getChildren().addAll(labelLogin, loginText);
        pane.getChildren().addAll(labelSenha, passwordField);
        pane.getChildren().addAll(buttonAtualizar, botaoVoltar.getButton());

        stage.setScene(scene);
        stage.setTitle("Atualizar Usuário");
    }

    private void salvar() {
        if (validFields()) {
            try {
                Perfil perfil = perfilController.findByDescription("Secretária");
                Usuario usuarioAtualizado = Usuario.builder()
                        .id(usuario.getId())
                        .nome(nomeText.getText())
                        .login(loginText.getText())
                        .senha(usuario.getSenha())
                        .senha(passwordField.getText())
                        .perfil(perfil)
                        .build();
                controller.update(usuarioAtualizado);
                successMessage("Usuário ".concat(usuarioAtualizado.getNome()).concat(" foi atualizado com sucesso!"));
                new TelaUsuarios().mountScene(stage);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                errorMessage("Ocorreu um erro ao atualizar o usuário, tente novamente mais tarde...");
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
            if (!loginText.getText().equals(usuario.getLogin())) {
                isDuplicated = loginIsDuplicated();
            }
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
