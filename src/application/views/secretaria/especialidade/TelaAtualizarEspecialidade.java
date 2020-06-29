package application.views.secretaria.especialidade;

import application.controller.EspecialidadeController;
import application.model.Especialidade;
import application.views.Tela;
import application.views.secretaria.util.BotaoVoltar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaAtualizarEspecialidade implements Tela, EventHandler<ActionEvent> {

	private Stage stage;

	private final Scene scene;

	private final Pane pane;

	private Especialidade especialidade;

	EspecialidadeController especialidadeController = new EspecialidadeController();
	
	Label lblNome = new Label("Nome");
	TextField tfNome = new TextField();

	Label lblDescricao = new Label("Descrição");
	TextField tfDescricao = new TextField();

	Button btnSalvar = new Button("Salvar");

	Image imgUserAdd = new Image("resources/images/especialidade.png");
	ImageView ivUserAdd = new ImageView(imgUserAdd);

	public TelaAtualizarEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
		entityToView(especialidade);
		this.pane = new Pane();
		this.scene = new Scene(pane, 900, 600);
	}

	public void mountScene(Stage stage) {
		this.stage = stage;
		BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaEspecialidades());

		stage.setTitle("Atualizar Especialidade");

        pane.getChildren().add(ivUserAdd);
        pane.getChildren().add(lblNome);
        pane.getChildren().add(tfNome);
        pane.getChildren().add(lblDescricao);
        pane.getChildren().add(tfDescricao);
        pane.getChildren().add(btnSalvar);
        pane.getChildren().add(botaoVoltar.getButton());

		ivUserAdd.relocate(80, 50);
        ivUserAdd.setFitHeight(100);
        ivUserAdd.setFitWidth(100);
        ivUserAdd.setPreserveRatio(true);

        lblNome.relocate(250, 90);
        tfNome.relocate(320, 90);
        tfNome.setMinWidth(200);
        
        lblDescricao.relocate(250, 140);
        tfDescricao.relocate(320, 140);
        tfDescricao.setMinWidth(350);
        tfDescricao.setMinHeight(100);
 
        btnSalvar.relocate(770, 560);
        btnSalvar.setMinWidth(80);
        btnSalvar.setOnAction(this);

        stage.setScene(scene);

		btnSalvar.relocate(770, 560);
		btnSalvar.setMinWidth(80);
		btnSalvar.setStyle("-fx-background-color: ".concat("#4fddae"));
		btnSalvar.setOnAction(this);

		stage.setScene(scene);
		stage.setTitle("Nova Especialidade");
	}

	public void entityToView(Especialidade especialidade) {
		tfNome.setText(especialidade.getNome());
		tfDescricao.setText(especialidade.getDescricao());
		
	}

	public void viewToEntity() {
		especialidade.setNome(tfNome.getText());
		especialidade.setDescricao(tfDescricao.getText());
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
				especialidadeController.update(especialidade);
				successMessage("Especialidade ".concat(especialidade.getNome()).concat(" foi atualizado com sucesso!"));
				new TelaEspecialidades().mountScene(stage);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				errorMessage("Ocorreu um erro ao salvar a especialidade, tente novamente mais tarde...");
			}
		}
			
	}

	private boolean validFields() {
		if (tfNome.getText().equals("") || tfNome.getText() == null) {
			errorMessage("Preencha o nome, por favor!");
			return false;
		}
		
		return true;
	}

}
