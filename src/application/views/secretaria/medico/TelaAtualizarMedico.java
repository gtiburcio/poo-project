package application.views.secretaria.medico;

import application.controller.EspecialidadeController;
import application.controller.MedicoController;
import application.model.Especialidade;
import application.model.Medico;
import application.model.enums.Estados;
import application.model.enums.Genero;
import application.views.Tela;
import application.views.secretaria.principal.TelaPrincipal;
import application.views.secretaria.util.BotaoVoltar;
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

public class TelaAtualizarMedico implements Tela, EventHandler<ActionEvent> {
	private Stage stage;

	private final Scene scene;

	private final Pane pane;

	private Medico medico;

	private final MedicoController medicoController = new MedicoController();

	private final EspecialidadeController especialidadeController = new EspecialidadeController();

	Label lblNome = new Label("Nome");
	TextField tfNome = new TextField();

	Label lblEspecial = new Label("Especialidades");
	ComboBox<Especialidade> cbEspecial = new ComboBox<>();

	Label lblCRM = new Label("CRM");
	TextField tfCRM = new TextField();

	Label lblDadosPrincipais = new Label("Dados Principais");

	Label lblCPF = new Label("CPF");
	TextField tfCPF = new TextField();

	Label lblRG = new Label("RG");
	TextField tfRG = new TextField();

	Label lblDataNasc = new Label("Data Nascimento");
	DatePicker dpDataNasc = new DatePicker();

	Label lblEmail = new Label("E-mail");
	TextField tfEmail = new TextField();

	Label lblTelResid = new Label("Telefone Resid.");
	TextField tfTelResid = new TextField();

	Label lblTelCel = new Label("Telefone Cel.");
	TextField tfTelCel = new TextField();

	Label lblGenero = new Label("Gênero");
	ComboBox<Genero> cbGenero = new ComboBox<>();

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

	Image imgUserAdd = new Image("resources/images/medico.png");
	ImageView ivUserAdd = new ImageView(imgUserAdd);

	public TelaAtualizarMedico(Medico medico) {
		this.medico = medico;
		this.pane = new Pane();
		this.scene = new Scene(pane, 900, 600);
		entityToView(medico);
	}

	public void mountScene(Stage stage) {
		this.stage = stage;

		stage.setTitle("Atualizar Médico");

		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPrincipal());

		pane.getChildren().add(ivUserAdd);

		pane.getChildren().add(lblNome);
		pane.getChildren().add(tfNome);
		pane.getChildren().add(lblEspecial);
		pane.getChildren().add(cbEspecial);
		pane.getChildren().add(lblCRM);
		pane.getChildren().add(tfCRM);
		pane.getChildren().add(lblDadosPrincipais);
		pane.getChildren().add(lblCPF);
		pane.getChildren().add(tfCPF);
		pane.getChildren().add(lblRG);
		pane.getChildren().add(tfRG);
		pane.getChildren().add(lblDataNasc);
		pane.getChildren().add(dpDataNasc);
		pane.getChildren().add(lblEmail);
		pane.getChildren().add(tfEmail);
		pane.getChildren().add(lblTelResid);
		pane.getChildren().add(tfTelResid);
		pane.getChildren().add(lblTelCel);
		pane.getChildren().add(tfTelCel);
		pane.getChildren().add(lblGenero);
		pane.getChildren().add(cbGenero);
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

		lblNome.relocate(250, 90);
		tfNome.relocate(300, 90);
		tfNome.setMinWidth(200);

		lblEspecial.relocate(550, 90);
		cbEspecial.relocate(650, 90);
		getEspecialidadeComboBox();
		cbEspecial.setMinWidth(200);

		lblCRM.relocate(550, 140);
		tfCRM.relocate(650, 140);
		tfCRM.setMinWidth(200);

		lblDadosPrincipais.relocate(30, 190);
		lblDadosPrincipais.setStyle("-fx-underline: true; -fx-font-weight: bold;");

		lblCPF.relocate(70, 240);
		tfCPF.relocate(120, 240);
		tfCPF.setMinWidth(170);

		lblRG.relocate(320, 240);
		tfRG.relocate(350, 240);
		tfRG.setMinWidth(170);

		lblDataNasc.relocate(550, 240);
		dpDataNasc.relocate(650, 240);
		dpDataNasc.setMinWidth(200);

		lblEmail.relocate(70, 290);
		tfEmail.relocate(150, 290);
		tfEmail.setMaxWidth(100);

		lblTelResid.relocate(280, 290);
		tfTelResid.relocate(370, 290);
		tfTelResid.setMaxWidth(100);

		lblTelCel.relocate(475, 290);
		tfTelCel.relocate(550, 290);
		tfTelCel.setMaxWidth(120);

		lblGenero.relocate(700, 290);
		cbGenero.relocate(750, 290);
		cbGenero.setMinWidth(100);
		cbGenero.getItems().addAll(Genero.values());

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
		tfBairro.setMinWidth(200);

		lblCidade.relocate(400, 490);
		tfCidade.relocate(460, 490);
		tfCidade.setMinWidth(200);

		lblUF.relocate(700, 490);
		cbUF.relocate(730, 490);
		cbUF.setMinWidth(70);
		cbUF.getItems().addAll(Estados.values());

		btnSalvar.relocate(770, 560);
		btnSalvar.setMinWidth(80);
		btnSalvar.setOnAction(this);

		stage.setScene(scene);
	}

	public void entityToView(Medico medico) {
		tfNome.setText(medico.getNome());
		cbEspecial.setValue(medico.getEspecialidade());
		tfCRM.setText(medico.getCrm());
		tfCPF.setText(medico.getCpf());
		tfRG.setText(medico.getRg());
		dpDataNasc.setValue(medico.getDataNasc());
		tfEmail.setText(medico.getEmail());
		tfTelResid.setText(medico.getTelResid());
		tfTelCel.setText(medico.getTelCelular());
		cbGenero.setValue(medico.getGenero());
		tfLogradouro.setText(medico.getLogradouro());
		tfCEP.setText(medico.getCep());
		tfComplemento.setText(medico.getComplemento());
		tfNumero.setText(medico.getNumero());
		tfBairro.setText(medico.getBairro());
		tfCidade.setText(medico.getCidade());
		cbUF.setValue(medico.getUf());
	}

	@Override
	public void handle(ActionEvent event) {
		if (event.getTarget().equals(btnSalvar)) {
			salvar();
		}
	}

	private void salvar() {
		if (validFields()) {
			viewToEntity();
			try {
				medicoController.update(medico);
				successMessage("Medico ".concat(medico.getNome()).concat(" foi salvo com sucesso!"));
				new TelaMedicos().mountScene(stage);
			} catch (Exception e) {
				e.printStackTrace();
				errorMessage("Ocorreu um erro ao salvar o médico, tente novamente mais tarde...");
			}
		}
	}

	private void viewToEntity() {
		medico.setNome(tfNome.getText());
		medico.setEspecialidade(cbEspecial.getValue());
		medico.setCrm(tfCRM.getText());
		medico.setCpf(tfCPF.getText());
		medico.setRg(tfRG.getText());
		medico.setDataNasc(dpDataNasc.getValue());
		medico.setEmail(tfEmail.getText());
		medico.setTelResid(tfTelResid.getText());
		medico.setTelCelular(tfTelCel.getText());
		medico.setGenero(cbGenero.getValue());
		medico.setLogradouro(tfLogradouro.getText());
		medico.setCep(tfCEP.getText());
		medico.setComplemento(tfComplemento.getText());
		medico.setNumero(tfNumero.getText());
		medico.setBairro(tfBairro.getText());
		medico.setCidade(tfCidade.getText());
		medico.setUf(cbUF.getValue());
	}

	private boolean validFields() {
		if (tfNome.getText().equals("") || cbEspecial.getValue() == null || tfCRM.getText().equals("")
				|| tfCPF.getText().equals("") || tfRG.getText().equals("")|| dpDataNasc.getValue() == null || tfEmail.getText().equals("") ||  tfTelCel.getText().equals("")
				|| tfTelResid.getText().equals("") || cbGenero.getValue() == null|| tfLogradouro.getText().equals("") || tfCEP.getText().equals("") || tfNumero.getText().equals("")
				|| tfBairro.getText().equals("") || tfCidade.getText().equals("") || cbUF.getValue() == null) {
			errorMessage("Preencha todos os campos, por favor!");
			return false;
		}
		return true;
	}

	private void getEspecialidadeComboBox() {
		try {
			cbEspecial.getItems().addAll(especialidadeController.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
