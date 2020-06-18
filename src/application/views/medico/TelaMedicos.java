package application.views.medico;

import application.controller.MedicoController;
import application.model.Medico;
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

import java.util.Objects;

public class TelaMedicos implements Tela {

	private Stage stage;

	private final Pane pane;

	private final Scene scene;

	private final MedicoController controller;

	private ObservableList<Medico> medicos;

	private TableView<Medico> table;

	private Button buttonCadastrar;

	private Button buttonAtualizar;

	private Button buttonExcluir;

	public TelaMedicos() {
		this.pane = new Pane();
		this.scene = new Scene(pane, 1200, 700);
		this.controller = new MedicoController();
	}

	@Override
	public void mountScene(Stage stage) {
		this.stage = stage;

		Label titulo = new Label("Medicos");
		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPrincipal());

		titulo.setFont(new Font("Arial", 40));
		titulo.relocate(500, 50);

		Image medicoImage = new Image("resources/images/medico.png");
		ImageView medicoView = new ImageView(medicoImage);

		medicoView.setFitHeight(100);
		medicoView.setFitWidth(100);
		medicoView.setPreserveRatio(true);
		medicoView.relocate(370, 20);

		TableColumn<Medico, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setMinWidth(333);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Medico, String> generoColumn = new TableColumn<>("Genero");
		generoColumn.setMinWidth(67);
		generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));

		TableColumn<Medico, String> cpfColumn = new TableColumn<>("CPF");
		cpfColumn.setMinWidth(200);
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		TableColumn<Medico, String> celularColumn = new TableColumn<>("Celular");
		celularColumn.setMinWidth(200);
		celularColumn.setCellValueFactory(new PropertyValueFactory<>("telCelular"));

		TableColumn<Medico, String> telefoneColumn = new TableColumn<>("Telefone");
		telefoneColumn.setMinWidth(200);
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telResid"));

		table = new TableView<>();
		table.getColumns().addAll(nameColumn, generoColumn, cpfColumn, celularColumn, telefoneColumn);
		table.setMinWidth(1000);
		table.relocate(100, 150);

		loadMedicos();

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

		pane.getChildren().addAll(titulo, medicoView, table, buttonCadastrar, buttonAtualizar, buttonExcluir,
				botaoVoltar.getButton());

		stage.setScene(scene);
		stage.setTitle("Pacientes");
	}

	private void loadMedicos() {
		try {
			this.medicos = FXCollections.observableList(controller.findAll());
			table.setItems(medicos);
		} catch (Exception ex) {
			ex.printStackTrace();
			errorMessage("Falha ao carregar os medicos!");
		}
	}

	private Medico getSelectedMedico() {
		TableView.TableViewSelectionModel<Medico> selectionModel = table.getSelectionModel();
		return selectionModel.getSelectedItem();
	}

	private void cadastrar() {
		new TelaNovoMedico().mountScene(stage);
	}

	private void atualizar() {
		Medico medico = getSelectedMedico();
		if (Objects.isNull(medico)) {
			errorMessage("Selecione um medico para atualizar!");
			return;
		}
		new TelaAtualizarMedico(medico).mountScene(stage);
	}

	private void excluir() {
		Medico medico = getSelectedMedico();
		if (Objects.isNull(medico)) {
			errorMessage("Selecione um paciente para excluir!");
			return;
		}
		try {
			if (choiceOptionMessage("Deseja realmente excluir ".concat(medico.getNome().concat("?")))) {
				controller.delete(medico);
				loadMedicos();
			}
		} catch (Exception ex) {
			errorMessage("Ocorreu um erro ao deletar o médico, tente mais tarde...");
		}
	}
}
