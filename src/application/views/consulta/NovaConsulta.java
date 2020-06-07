package application.views.consulta;

import application.views.Tela;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class NovaConsulta implements Tela, EventHandler<ActionEvent> {
	
	private final Scene scene;

    private final Pane pane;

	Label lblPaciente = new Label("Paciente");
	ComboBox<String> cbPaciente = new ComboBox<String>();
	
	Label lblPeso = new Label("Peso");
	TextField tfPeso = new TextField();
	
	Label lblAltura = new Label("Altura");
	TextField tfAltura = new TextField();
	
	Label lblSintoma = new Label("Sintomas");	
	TextField tfSintoma = new TextField();
	Button btnAddSintoma = new Button("+");
	ListView<String> lvSintoma = new ListView<String>();
	
	Label lblPrescricao = new Label("Prescrições");
	TextField tfPrescricao = new TextField();
	Button btnAddPrescricao = new Button("+");
	ListView<String> lvPrescricao = new ListView<String>();
	
	Label lblExame = new Label("Exames a ser realizados");
	ComboBox<String> cbExame = new ComboBox<String>();
	Button btnAddExame = new Button("+");
	ListView<String> lvExame = new ListView<String>();
	
	Button btnFinalCons = new Button("Finalizar consulta");
	
	public NovaConsulta() {
		this.pane = new Pane();
        this.scene = new Scene(pane, 550, 768);
	}
	
	@Override
	public void mountScene(Stage stage) {
		stage.setScene(scene);
        stage.setTitle("Nova Consulta");
        
        pane.getChildren().add(lblPaciente);
        pane.getChildren().add(cbPaciente);
        
        pane.getChildren().add(lblPeso);
        pane.getChildren().add(tfPeso);
        
        pane.getChildren().add(lblAltura);
        pane.getChildren().add(tfAltura);
        
        pane.getChildren().add(lblSintoma);
        pane.getChildren().add(tfSintoma);
        pane.getChildren().add(btnAddSintoma);
        pane.getChildren().add(lvSintoma);
        
        pane.getChildren().add(lblExame);
        pane.getChildren().add(cbExame);
        pane.getChildren().add(btnAddExame);
        pane.getChildren().add(lvExame);
        
        pane.getChildren().add(lblPrescricao);
        pane.getChildren().add(tfPrescricao);
        pane.getChildren().add(btnAddPrescricao);
        pane.getChildren().add(lvPrescricao);

        pane.getChildren().add(btnFinalCons);
        
        lblPaciente.relocate(160, 30);
        cbPaciente.relocate(160, 50);
        cbPaciente.setMinWidth(230);
        
        lblPeso.relocate(160, 100);
        tfPeso.relocate(160, 120);
        tfPeso.setMaxWidth(80);
        
        lblAltura.relocate(270, 100);
        tfAltura.relocate(270, 120);
        tfAltura.setMaxWidth(80);
        
        lblSintoma.relocate(160, 170);
        tfSintoma.relocate(160, 190);
        tfSintoma.setMinWidth(190);
        btnAddSintoma.relocate(360, 190);
        lvSintoma.relocate(160, 230);
        lvSintoma.setPrefSize(230, 100);
        
        lblExame.relocate(160, 360);
        cbExame.relocate(160, 380);
        cbExame.setMinWidth(190);
        btnAddExame.relocate(360, 380);
        lvExame.relocate(160, 420);
        lvExame.setPrefSize(230, 100);
        
        lblPrescricao.relocate(160, 550);
        tfPrescricao.relocate(160, 570);
        tfPrescricao.setMinWidth(190);
        btnAddPrescricao.relocate(360, 570);
        lvPrescricao.relocate(160, 610);
        lvPrescricao.setPrefSize(230, 100);
        
        btnFinalCons.relocate(400, 730);
        
        
	}
	
	@Override
	public void handle(ActionEvent event) {
		// TODO Auto-generated method stub
	}

}
