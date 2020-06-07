package application.views.usuario;

import application.controller.UsuarioController;
import application.model.Usuario;
import application.views.Tela;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.Objects;

public class TelaUsuarios implements Tela {

    private Stage stage;

    private final Pane pane;

    private final Scene scene;

    private final UsuarioController controller;

    private ObservableList<Usuario> usuarios;

    private TableView<Usuario> table;

    private Button buttonCadastrar;

    private Button buttonAtualizar;

    private Button buttonExcluir;

    public TelaUsuarios() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.controller = new UsuarioController();
    }


    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;

        Label titulo = new Label("Usuários");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(500, 50);

        Image usuarioImage = new Image("resources/images/usuario.png");
        ImageView usuarioView = new ImageView(usuarioImage);

        usuarioView.setFitHeight(100);
        usuarioView.setFitWidth(100);
        usuarioView.setPreserveRatio(true);
        usuarioView.relocate(370, 20);

        TableColumn<Usuario, String> nameColumn = new TableColumn<>("Nome");
        nameColumn.setMinWidth(333);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

        TableColumn<Usuario, String> loginColumn = new TableColumn<>("Login");
        loginColumn.setMinWidth(333);
        loginColumn.setCellValueFactory(new PropertyValueFactory<>("login"));

        TableColumn<Usuario, String> perfilColumn = new TableColumn<>("Perfil");
        perfilColumn.setMinWidth(333);
        perfilColumn.setCellValueFactory(usuario -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(usuario.getValue().getPerfil().getDescricao());
            return property;
        });

        table = new TableView<>();
        table.getColumns().addAll(nameColumn, loginColumn, perfilColumn);
        table.setMinWidth(1000);
        table.relocate(100, 150);

        loadUsuarios();

        buttonCadastrar = new Button("Cadastrar");
        buttonCadastrar.relocate(150, 600);
        buttonCadastrar.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonCadastrar.setMinWidth(100);
        buttonCadastrar.setMinHeight(30);
        buttonCadastrar.setOnMouseClicked(event -> cadastrar());

        buttonAtualizar = new Button("Atualizar");
        buttonAtualizar.relocate(550, 600);
        buttonAtualizar.setStyle("-fx-background-color: ".concat("#ff9234"));
        buttonAtualizar.setMinWidth(100);
        buttonAtualizar.setMinHeight(30);
        buttonAtualizar.setOnMouseClicked(event -> atualizar());

        buttonExcluir = new Button("Excluir");
        buttonExcluir.relocate(950, 600);
        buttonExcluir.setStyle("-fx-background-color: ".concat("#d92027"));
        buttonExcluir.setMinWidth(100);
        buttonExcluir.setMinHeight(30);
        buttonExcluir.setOnMouseClicked(event -> excluir());

        pane.getChildren().addAll(titulo, usuarioView, table, buttonCadastrar, buttonAtualizar, buttonExcluir);

        stage.setScene(scene);
        stage.setTitle("Usuarios");
    }

    private void loadUsuarios() {
        try {
            this.usuarios = FXCollections.observableList(controller.findAll());
            table.setItems(usuarios);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            errorMessage("Falha ao carregar os usuários!");
        }
    }

    private Usuario getSelectedUsuario() {
        TableView.TableViewSelectionModel<Usuario> selectionModel = table.getSelectionModel();
        return selectionModel.getSelectedItem();
    }

    private void cadastrar() {
        new TelaNovoUsuario().mountScene(stage);
    }

    private void atualizar() {
        Usuario usuario = getSelectedUsuario();
        if (Objects.isNull(usuario)) {
            errorMessage("Selecione um usuário para atualizar!");
            return;
        }
        new TelaAtualizarUsuario(usuario).mountScene(stage);
    }

    private void excluir() {
        Usuario usuario = getSelectedUsuario();
        if (Objects.isNull(usuario)) {
            errorMessage("Selecione um usuário para excluir!");
            return;
        }
        try {
            if (choiceOptionMessage("Deseja realmente excluir ".concat(usuario.getNome().concat("?")))) {
                controller.delete(usuario);
                loadUsuarios();
            }
        } catch (Exception ex) {
            errorMessage("Ocorreu um erro ao deletar o usuário, tente mais tarde...");
        }
    }
}
