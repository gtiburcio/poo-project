package application.views.paciente;

import application.controller.ConvenioController;
import application.controller.PacienteController;
import application.controller.PacientePlanoController;
import application.controller.PlanoController;
import application.model.Convenio;
import application.model.Paciente;
import application.model.PacientePlano;
import application.model.Plano;
import application.model.enums.Estados;
import application.model.enums.Genero;
import application.views.Tela;
import application.views.util.BotaoVoltar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import static java.util.Objects.isNull;

public class TelaNovoPaciente implements Tela, EventHandler<ActionEvent> {

	private Stage stage;

	private final Scene scene;

	private final Pane pane;

	ConvenioController convenioController = new ConvenioController();
	PlanoController planoController = new PlanoController();
	PacienteController pacienteController = new PacienteController();
	PacientePlanoController pacientePlanoController = new PacientePlanoController();

	Label lblNome = new Label("Nome");
	TextField tfNome = new TextField();

	Label lblDataNasc = new Label("Data Nascimento");
	DatePicker dpDataNasc = new DatePicker();

	Label lblGenero = new Label("Gênero");
	ComboBox<Genero> cbGenero = new ComboBox<>();

	Label lblCPF = new Label("CPF");
	TextField tfCPF = new TextField();

	Label lblRG = new Label("RG");
	TextField tfRG = new TextField();

	Label lblNCart = new Label("Nº Carteirinha");
	TextField tfNCart = new TextField();

	Label lblConvenio = new Label("Convênio");
	ComboBox<Convenio> cbConvenio = new ComboBox<>();

	Label lblPlano = new Label("Plano");
	ComboBox<Plano> cbPlano = new ComboBox<>();

	Label lblInforCont = new Label("Informações de Contato");

	Label lblEmail = new Label("E-mail");
	TextField tfEmail = new TextField();

	Label lblTelResid = new Label("Telefone Resid.");
	TextField tfTelResid = new TextField();

	Label lblTelCel = new Label("Telefone Cel.");
	TextField tfTelCel = new TextField();

	Label lblEndereco = new Label("Endereço");

	Label lblLogradouro = new Label("Logradouro");
	TextField tfLogradouro = new TextField();

	Label lblCEP = new Label("CEP");
	TextField tfCEP = new TextField();

	Label lblComplemento = new Label("Complemento");
	TextField tfComplemento = new TextField();

	Label lblNumero = new Label("Número");
	TextField tfNumero = new TextField();

	Label lblBairro = new Label("Bairro");
	TextField tfBairro = new TextField();

	Label lblCidade = new Label("Cidade");
	TextField tfCidade = new TextField();

	Label lblUF = new Label("UF");
	ComboBox<Estados> cbUF = new ComboBox<>();

	Button btnSalvar = new Button("Salvar");

	Image imgUserAdd = new Image("resources/images/paciente.png");
	ImageView ivUserAdd = new ImageView(imgUserAdd);

	public TelaNovoPaciente() {
		this.pane = new Pane();
		this.scene = new Scene(pane, 900, 600);
	}

	public void mountScene(Stage stage) {
		this.stage = stage;
		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPacientes());

		stage.setTitle("Novo Paciente");

		pane.getChildren().add(ivUserAdd);

		pane.getChildren().add(lblNome);
		pane.getChildren().add(tfNome);
		pane.getChildren().add(lblDataNasc);
		pane.getChildren().add(dpDataNasc);
		pane.getChildren().add(lblGenero);
		pane.getChildren().add(cbGenero);
		pane.getChildren().add(lblCPF);
		pane.getChildren().add(tfCPF);
		pane.getChildren().add(lblRG);
		pane.getChildren().add(tfRG);
		pane.getChildren().add(lblNCart);
		pane.getChildren().add(tfNCart);
		pane.getChildren().add(lblConvenio);
		pane.getChildren().add(cbConvenio);
		pane.getChildren().add(lblPlano);
		pane.getChildren().add(cbPlano);
		pane.getChildren().add(lblInforCont);
		pane.getChildren().add(lblEmail);
		pane.getChildren().add(tfEmail);
		pane.getChildren().add(lblTelResid);
		pane.getChildren().add(tfTelResid);
		pane.getChildren().add(lblTelCel);
		pane.getChildren().add(tfTelCel);
		pane.getChildren().add(lblEndereco);
		pane.getChildren().add(lblLogradouro);
		pane.getChildren().add(tfLogradouro);
		pane.getChildren().add(lblCEP);
		pane.getChildren().add(tfCEP);
		pane.getChildren().add(lblComplemento);
		pane.getChildren().add(tfComplemento);
		pane.getChildren().add(lblNumero);
		pane.getChildren().add(tfNumero);
		pane.getChildren().add(lblBairro);
		pane.getChildren().add(tfBairro);
		pane.getChildren().add(lblCidade);
		pane.getChildren().add(tfCidade);
		pane.getChildren().add(lblUF);
		pane.getChildren().add(cbUF);
		pane.getChildren().add(btnSalvar);
		pane.getChildren().add(botaoVoltar.getButton());

		ivUserAdd.relocate(80, 50);
		ivUserAdd.setFitHeight(100);
		ivUserAdd.setFitWidth(100);
		ivUserAdd.setPreserveRatio(true);

		lblNome.relocate(250, 40);
		tfNome.relocate(300, 40);
		tfNome.setMinWidth(200);

		lblDataNasc.relocate(550, 40);
		dpDataNasc.relocate(650, 40);
		dpDataNasc.setMinWidth(200);

		lblGenero.relocate(250, 90);
		cbGenero.relocate(300, 90);
		cbGenero.setMinWidth(100);
		cbGenero.getItems().addAll(Genero.values());

		lblCPF.relocate(440, 90);
		tfCPF.relocate(470, 90);
		tfCPF.setMinWidth(100);

		lblRG.relocate(670, 90);
		tfRG.relocate(700, 90);
		tfRG.setMinWidth(100);

		lblNCart.relocate(250, 140);
		tfNCart.relocate(330, 140);
		tfNCart.setMinWidth(100);

		lblConvenio.relocate(500, 140);
		cbConvenio.relocate(560, 140);
		cbConvenio.setMinWidth(100);
		getConvenioComboBox();
		cbConvenio.setOnAction(this);

		lblPlano.relocate(700, 140);
		cbPlano.relocate(750, 140);
		cbPlano.setPrefWidth(100);

		lblInforCont.relocate(30, 190);
		lblInforCont.setStyle("-fx-underline: true; -fx-font-weight: bold;");

		lblEmail.relocate(70, 240);
		tfEmail.relocate(120, 240);
		tfEmail.setMinWidth(300);

		lblTelResid.relocate(450, 240);
		tfTelResid.relocate(550, 240);
		tfTelResid.setMinWidth(300);

		lblTelCel.relocate(460, 290);
		tfTelCel.relocate(550, 290);
		tfTelCel.setMinWidth(300);

		lblEndereco.relocate(30, 340);
		lblEndereco.setStyle("-fx-underline: true; -fx-font-weight: bold;");

		lblLogradouro.relocate(70, 390);
		tfLogradouro.relocate(150, 390);
		tfLogradouro.setMinWidth(270);

		lblCEP.relocate(500, 390);
		tfCEP.relocate(550, 390);
		tfCEP.setMinWidth(70);

		lblComplemento.relocate(70, 440);
		tfComplemento.relocate(160, 440);
		tfComplemento.setMinWidth(200);

		lblNumero.relocate(500, 440);
		tfNumero.relocate(550, 440);
		tfNumero.setMinWidth(70);

		lblBairro.relocate(70, 490);
		tfBairro.relocate(160, 490);
		tfBairro.setMaxWidth(150);

		lblCidade.relocate(350, 490);
		tfCidade.relocate(410, 490);
		tfCidade.setMinWidth(200);

		lblUF.relocate(650, 490);
		cbUF.relocate(680, 490);
		cbUF.setMinWidth(70);
		cbUF.getItems().addAll(Estados.values());

		btnSalvar.relocate(770, 560);
		btnSalvar.setMinWidth(80);
		btnSalvar.setStyle("-fx-background-color: ".concat("#4fddae"));
		btnSalvar.setOnAction(this);

		stage.setScene(scene);
		stage.setTitle("Novo Paciente");
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget().equals(btnSalvar)) {
			salvar();
		}

		if (event.getTarget().equals(cbConvenio)) {
			getPlanoComboBox();
		}
	}

	public void getPlanoComboBox() {
		try {
			if (cbConvenio.getValue() != null) {
				cbPlano.getItems().clear();
				cbPlano.getItems().addAll(planoController.findByConvenio(cbConvenio.getValue()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void getConvenioComboBox() {
		try {
			cbConvenio.getItems().add(new Convenio(0, ""));
			cbConvenio.getItems().addAll(convenioController.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Paciente viewToEntityPaciente() {
		Paciente paciente = new Paciente();
		paciente.setNome(tfNome.getText());
		paciente.setDataNasc(dpDataNasc.getValue());
		paciente.setGenero(cbGenero.getValue());
		paciente.setCpf(tfCPF.getText());
		paciente.setRg(tfRG.getText());
		paciente.setNCarteirinha(tfNCart.getText());
		paciente.setEmail(tfEmail.getText());
		paciente.setTelResid(tfTelResid.getText());
		paciente.setTelCelular(tfTelCel.getText());
		paciente.setLogradouro(tfLogradouro.getText());
		paciente.setCep(tfCEP.getText());
		paciente.setComplemento(tfComplemento.getText());
		paciente.setNumero(tfNumero.getText());
		paciente.setBairro(tfBairro.getText());
		paciente.setCidade(tfCidade.getText());
		paciente.setUf(cbUF.getValue());
		return paciente;
	}

	private void salvar() {
		if (validFields()) {
			Paciente paciente = viewToEntityPaciente();
			long idPaciente = 0;
			try {
				idPaciente = pacienteController.save(paciente, true);
				successMessage("Paciente ".concat(paciente.getNome()).concat(" foi salvo com sucesso!"));
				new TelaPacientes().mountScene(stage);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
				errorMessage("Ocorreu um erro ao salvar o paciente, tente novamente mais tarde...");
			}

			if (cbPlano.getValue() != null && cbConvenio.getSelectionModel().getSelectedItem().getId() != 0) {
				PacientePlano pacientePlano = new PacientePlano();
				pacientePlano.setPaciente(paciente);
				pacientePlano.getPaciente().setId(idPaciente);
				pacientePlano.setPlano(cbPlano.getValue());
				try {
					pacientePlanoController.save(pacientePlano);
					new TelaPacientes().mountScene(stage);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
		}
	}

	private boolean validFields() {
		if (tfNome.getText().equals("") || dpDataNasc.getValue() == null || cbGenero.getValue() == null
				|| tfCPF.getText().equals("") || tfRG.getText().equals("") || tfNCart.getText().equals("")
				|| tfEmail.getText().equals("") || tfTelCel.getText().equals("") || tfTelResid.getText().equals("")
				|| tfLogradouro.getText().equals("") || tfCEP.getText().equals("") || tfNumero.getText().equals("")
				|| tfBairro.getText().equals("") || tfCidade.getText().equals("") || cbUF.getValue() == null) {
			errorMessage("Preencha todos os campos, por favor!");
			return false;
		}
		if ((!isNull(cbConvenio.getSelectionModel().getSelectedItem())
				&& cbConvenio.getSelectionModel().getSelectedItem().getId() != 0)
				&& isNull(cbPlano.getSelectionModel().getSelectedItem())) {
			errorMessage("Selecione um plano do convênio, por favor!");
			return false;
		}
		return true;
	}
}
