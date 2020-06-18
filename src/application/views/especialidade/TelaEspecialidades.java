package application.views.especialidade;

import java.util.Objects;

import application.controller.EspecialidadeController;
import application.model.Especialidade;
import application.views.Tela;
import application.views.principal.TelaPrincipal;
import application.views.util.BotaoVoltar;
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

public class TelaEspecialidades implements Tela {

	private Stage stage;

	private final Pane pane;

	private final Scene scene;

	private final EspecialidadeController controller;

	private ObservableList<Especialidade> especialidades;

	private TableView<Especialidade> table;

	private Button buttonCadastrar;

	private Button buttonAtualizar;

	private Button buttonExcluir;

	public TelaEspecialidades() {
		this.pane = new Pane();
		this.scene = new Scene(pane, 1200, 700);
		this.controller = new EspecialidadeController();
	}

	@Override
	public void mountScene(Stage stage) {
		this.stage = stage;

		Label titulo = new Label("Especialidades");
		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPrincipal());

		titulo.setFont(new Font("Arial", 40));
		titulo.relocate(500, 50);

		Image especialidadeImage = new Image("resources/images/especialidade.png");
		ImageView especialidadeView = new ImageView(especialidadeImage);

		especialidadeView.setFitHeight(100);
		especialidadeView.setFitWidth(100);
		especialidadeView.setPreserveRatio(true);
		especialidadeView.relocate(370, 20);

		TableColumn<Especialidade, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setMinWidth(300);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Especialidade, String> descricaoColumn = new TableColumn<>("Descrição");
		descricaoColumn.setMinWidth(700);
		descricaoColumn.setCellValueFactory(new PropertyValueFactory<>("descricao"));

		
		table = new TableView<>();
		table.getColumns().addAll(nameColumn, descricaoColumn);
		table.setMinWidth(1000);
		table.relocate(100, 150);

		loadEspecialidade();

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

		pane.getChildren().addAll(titulo, especialidadeView, table, buttonCadastrar, buttonAtualizar, buttonExcluir,
				botaoVoltar.getButton());

		stage.setScene(scene);
		stage.setTitle("Especialidades");
	}

	private void loadEspecialidade() {
		try {
			this.especialidades = FXCollections.observableList(controller.findAll());
			table.setItems(especialidades);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			errorMessage("Falha ao carregar as especialidades!");
		}
	}

	private Especialidade getSelectedEspecialidade() {
		TableView.TableViewSelectionModel<Especialidade> selectionModel = table.getSelectionModel();
		return selectionModel.getSelectedItem();
	}

	private void cadastrar() {
		new TelaNovaEspecialidade().mountScene(stage);
	}

	private void atualizar() {
		Especialidade especialidade = getSelectedEspecialidade();
		if (Objects.isNull(especialidade)) {
			errorMessage("Selecione uma especialidade para atualizar!");
			return;
		}
		new TelaAtualizarEspecialidade(especialidade).mountScene(stage);
	}

	private void excluir() {
		Especialidade especialidade = getSelectedEspecialidade();
		if (Objects.isNull(especialidade)) {
			errorMessage("Selecione uma especialidade para excluir!");
			return;
		}
		try {
			if (choiceOptionMessage("Deseja realmente excluir ".concat(especialidade.getNome().concat("?")))) {
				controller.delete(especialidade);
				loadEspecialidade();
			}
		} catch (Exception ex) {
			errorMessage("Ocorreu um erro ao deletar o paciente, tente mais tarde...");
		}
	}
}
