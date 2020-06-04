package application.views.paciente;

import java.io.InputStream;

import javafx.application.Application;
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

public class NovoPaciente extends Application {
	@Override
	public void start(Stage stage) throws Exception {
		
		Label lblNome = new Label("Nome");
		TextField tfNome = new TextField();
		
		Label lblDataNasc = new Label("Data Nascimento");
		DatePicker dpDataNasc = new DatePicker();
		
		Label lblSexo = new Label("Sexo");
		ComboBox<Object> cbSexo = new ComboBox<>();
		
		Label lblCPF = new Label("CPF");
		TextField tfCPF = new TextField();
		
		Label lblRG = new Label("RG");
		TextField tfRG = new TextField();
		
		Label lblNCart = new Label("Nº Carteirinha");
		TextField tfNCart = new TextField();
		
		Label lblConvenio = new Label("Convênio");
		ComboBox<Object> cbConvenio = new ComboBox<>();
		
		Label lblPlano = new Label("Plano");
		ComboBox<Object> cbPlano= new ComboBox<>();
		
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
		ComboBox<Object> cbCidade= new ComboBox<>();
		
		Label lblUF = new Label("UF");
		ComboBox<Object> cbUF= new ComboBox<>();
		
		Button btnCancelar = new Button("Cancelar");
		
		Button btnSalvar = new Button("Salvar");
		
		Pane pan = new Pane();
		Scene scn = new Scene(pan, 900, 600);
		
		InputStream isUserAdd = getClass().getResourceAsStream("/images/useradd.png");
		Image imgUserAdd = new Image(isUserAdd);
		ImageView ivUserAdd = new ImageView(imgUserAdd);
		
		stage.setTitle("Novo Paciente");
		
		pan.getChildren().add(ivUserAdd);
		
		pan.getChildren().add(lblNome);
		pan.getChildren().add(tfNome);
		pan.getChildren().add(lblDataNasc);
		pan.getChildren().add(dpDataNasc);
		pan.getChildren().add(lblSexo);
		pan.getChildren().add(cbSexo);
		pan.getChildren().add(lblCPF);
		pan.getChildren().add(tfCPF);
		pan.getChildren().add(lblRG);
		pan.getChildren().add(tfRG);
		pan.getChildren().add(lblNCart);
		pan.getChildren().add(tfNCart);
		pan.getChildren().add(lblConvenio);
		pan.getChildren().add(cbConvenio);
		pan.getChildren().add(lblPlano);
		pan.getChildren().add(cbPlano);
		pan.getChildren().add(lblInforCont);
		pan.getChildren().add(lblEmail);
		pan.getChildren().add(tfEmail);
		pan.getChildren().add(lblTelResid);
		pan.getChildren().add(tfTelResid);
		pan.getChildren().add(lblTelCel);
		pan.getChildren().add(tfTelCel);
		pan.getChildren().add(lblEndereco);
		pan.getChildren().add(lblLogradouro);
		pan.getChildren().add(tfLogradouro);
		pan.getChildren().add(lblCEP);
		pan.getChildren().add(tfCEP);
		pan.getChildren().add(lblComplemento);
		pan.getChildren().add(tfComplemento);
		pan.getChildren().add(lblNumero);
		pan.getChildren().add(tfNumero);
		pan.getChildren().add(lblBairro);
		pan.getChildren().add(tfBairro);
		pan.getChildren().add(lblCidade);
		pan.getChildren().add(cbCidade);
		pan.getChildren().add(lblUF);
		pan.getChildren().add(cbUF);
		pan.getChildren().add(btnCancelar);
		pan.getChildren().add(btnSalvar);
		
		ivUserAdd.relocate(50, 50);
		ivUserAdd.setFitHeight(100);
		ivUserAdd.setFitWidth(100);
		ivUserAdd.setPreserveRatio(true);
		
		lblNome.relocate(250, 40);
		tfNome.relocate(300, 40);
		tfNome.setMinWidth(200);
		
		lblDataNasc.relocate(550, 40);
		dpDataNasc.relocate(650, 40);
		dpDataNasc.setMinWidth(200);
		
		lblSexo.relocate(250, 90);
		cbSexo.relocate(300, 90);
		cbSexo.setMinWidth(100);
		
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
		
		lblPlano.relocate(700, 140);
		cbPlano.relocate(750, 140);
		cbPlano.setMinWidth(100);
		
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
		tfBairro.setMinWidth(200);
		
		lblCidade.relocate(400, 490);
		cbCidade.relocate(460, 490);
		cbCidade.setMinWidth(200);
		
		lblUF.relocate(720, 490);
		cbUF.relocate(750, 490);
		cbUF.setMinWidth(70);
		
		btnCancelar.relocate(670, 560);
		btnCancelar.setMinWidth(80);
		
		btnSalvar.relocate(770, 560);
		btnSalvar.setMinWidth(80);
		
		
		stage.setScene(scn);
		stage.setResizable(false);
		stage.show();
				
	}
	
	public static void main(String[] args) {
		Application.launch(NovoPaciente.class, args);
	}
}
