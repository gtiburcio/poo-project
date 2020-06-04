package application.views.medico;

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

public class NovoMedico{
	
	private Scene scene;

    private Pane pane;

    public NovoMedico() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 900, 600);
    }
    
    public Scene mountScene(Stage stage) {
        Label lblNome = new Label("Nome");
        TextField tfNome = new TextField();

        Label lblEspecial = new Label("Especialidades");
        ComboBox<Object> cbEspecial = new ComboBox<>();

        Label lblDadosPrincipais = new Label("Dados Principais");

        Label lblCPF = new Label("CPF");
        TextField tfCPF = new TextField();

        Label lblRG = new Label("RG");
        TextField tfRG = new TextField();

        Label lblDataNasc = new Label("Data Nascimento");
        DatePicker dpDataNasc = new DatePicker();

        Label lblNCons = new Label("Nº do conselho");
        TextField tfNCons= new TextField();

        Label lblSiglaCon = new Label("Sigla do conselho");
        ComboBox<Object> cbSiglaCons = new ComboBox<>();

        Label lblUFCons = new Label("UF do conselho");
        ComboBox<Object> cbUFCons = new ComboBox<>();

        Label lblGenero = new Label("Gênero");
        ComboBox<Object> cbGenero = new ComboBox<>();

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
        ComboBox<Object> cbCidade = new ComboBox<>();

        Label lblUF = new Label("UF");
        ComboBox<Object> cbUF = new ComboBox<>();

        Button btnSalvar = new Button("Salvar");

        Image imgUserAdd = new Image("resources/images/contact.png");
        ImageView ivUserAdd = new ImageView(imgUserAdd);

        stage.setTitle("Novo Paciente");

        pane.getChildren().add(ivUserAdd);

        pane.getChildren().add(lblNome);
        pane.getChildren().add(tfNome);
        pane.getChildren().add(lblEspecial);
        pane.getChildren().add(cbEspecial);
        pane.getChildren().add(lblDadosPrincipais);
        pane.getChildren().add(lblCPF);
        pane.getChildren().add(tfCPF);
        pane.getChildren().add(lblRG);
        pane.getChildren().add(tfRG);
        pane.getChildren().add(lblDataNasc);
        pane.getChildren().add(dpDataNasc);
        pane.getChildren().add(lblNCons);
        pane.getChildren().add(tfNCons);
        pane.getChildren().add(lblSiglaCon);
        pane.getChildren().add(cbSiglaCons);
        pane.getChildren().add(lblUFCons);
        pane.getChildren().add(cbUFCons);
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
        pane.getChildren().add(cbCidade);
        pane.getChildren().add(lblUF);
        pane.getChildren().add(cbUF);
        pane.getChildren().add(btnSalvar);

        ivUserAdd.relocate(50, 50);
        ivUserAdd.setFitHeight(100);
        ivUserAdd.setFitWidth(100);
        ivUserAdd.setPreserveRatio(true);

        lblNome.relocate(250, 90);
        tfNome.relocate(300, 90);
        tfNome.setMinWidth(200);

        lblEspecial.relocate(550, 90);
        cbEspecial.relocate(650, 90);
        cbEspecial.setMinWidth(200);
        
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
        
        lblNCons.relocate(70, 290);
        tfNCons.relocate(170, 290);
        tfNCons.setMaxWidth(120);
        
        lblSiglaCon.relocate(300, 290);
        cbSiglaCons.relocate(400, 290);
        cbSiglaCons.setMinWidth(100);
        
        lblUFCons.relocate(520, 290);
        cbUFCons.relocate(610, 290);
        cbUFCons.setMaxWidth(90);
        
        lblGenero.relocate(700, 290);
        cbGenero.relocate(750, 290);
        cbGenero.setMinWidth(100);
        
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

        btnSalvar.relocate(770, 560);
        btnSalvar.setMinWidth(80);

        return scene;
    }

}
