package application.views.paciente;

import java.util.Objects;

import application.controller.PacienteController;
import application.model.Paciente;
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

public class TelaPacientes implements Tela {

	private Stage stage;

	private final Pane pane;

	private final Scene scene;

	private final PacienteController controller;

	private ObservableList<Paciente> pacientes;

	private TableView<Paciente> table;

	private Button buttonCadastrar;

	private Button buttonAtualizar;

	private Button buttonExcluir;

	public TelaPacientes() {
		this.pane = new Pane();
		this.scene = new Scene(pane, 1200, 700);
		this.controller = new PacienteController();
	}

	@Override
	public void mountScene(Stage stage) {
		this.stage = stage;

		Label titulo = new Label("Pacientes");
		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPrincipal());

		titulo.setFont(new Font("Arial", 40));
		titulo.relocate(500, 50);

		Image pacienteImage = new Image("resources/images/paciente.png");
		ImageView pacienteView = new ImageView(pacienteImage);

		pacienteView.setFitHeight(100);
		pacienteView.setFitWidth(100);
		pacienteView.setPreserveRatio(true);
		pacienteView.relocate(370, 20);

		TableColumn<Paciente, String> nameColumn = new TableColumn<>("Nome");
		nameColumn.setMinWidth(333);
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("nome"));

		TableColumn<Paciente, String> generoColumn = new TableColumn<>("Genero");
		generoColumn.setMinWidth(67);
		generoColumn.setCellValueFactory(new PropertyValueFactory<>("genero"));

		TableColumn<Paciente, String> cpfColumn = new TableColumn<>("CPF");
		cpfColumn.setMinWidth(200);
		cpfColumn.setCellValueFactory(new PropertyValueFactory<>("cpf"));

		TableColumn<Paciente, String> celularColumn = new TableColumn<>("Celular");
		celularColumn.setMinWidth(200);
		celularColumn.setCellValueFactory(new PropertyValueFactory<>("telCelular"));

		TableColumn<Paciente, String> telefoneColumn = new TableColumn<>("Telefone");
		telefoneColumn.setMinWidth(200);
		telefoneColumn.setCellValueFactory(new PropertyValueFactory<>("telResid"));

		table = new TableView<>();
		table.getColumns().addAll(nameColumn, generoColumn, cpfColumn, celularColumn, telefoneColumn);
		table.setMinWidth(1000);
		table.relocate(100, 150);

		loadPacientes();

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

		pane.getChildren().addAll(titulo, pacienteView, table, buttonCadastrar, buttonAtualizar, buttonExcluir,
				botaoVoltar.getButton());

		stage.setScene(scene);
		stage.setTitle("Pacientes");
	}

	private void loadPacientes() {
		try {
			this.pacientes = FXCollections.observableList(controller.findAll());
			table.setItems(pacientes);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			errorMessage("Falha ao carregar os pacientes!");
		}
	}

	private Paciente getSelectedPaciente() {
		TableView.TableViewSelectionModel<Paciente> selectionModel = table.getSelectionModel();
		return selectionModel.getSelectedItem();
	}

	private void cadastrar() {
		new TelaNovoPaciente().mountScene(stage);
	}

	private void atualizar() {
		Paciente paciente = getSelectedPaciente();
		if (Objects.isNull(paciente)) {
			errorMessage("Selecione um paciente para atualizar!");
			return;
		}
		new TelaAtualizarPaciente(paciente).mountScene(stage);
	}

	private void excluir() {
		Paciente paciente = getSelectedPaciente();
		if (Objects.isNull(paciente)) {
			errorMessage("Selecione um paciente para excluir!");
			return;
		}
		try {
			if (choiceOptionMessage("Deseja realmente excluir ".concat(paciente.getNome().concat("?")))) {
				controller.delete(paciente);
				loadPacientes();
			}
		} catch (Exception ex) {
			errorMessage("Ocorreu um erro ao deletar o paciente, tente mais tarde...");
		}
	}
}
