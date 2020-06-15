package application.views.especialidade;

import application.views.Tela;
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

public class TelaNovaEspecialidade implements Tela, EventHandler<ActionEvent> {
	
	private final Scene scene;

    private final Pane pane;
    
    Label lblNome = new Label("Nome");
    TextField tfNome = new TextField();
    
    Label lblDescricao = new Label("Descricao");
    TextField tfDescricao = new TextField();

    Button btnSalvar = new Button("Salvar");

    Image imgUserAdd = new Image("resources/images/contact.png");
    ImageView ivUserAdd = new ImageView(imgUserAdd);

    public TelaNovaEspecialidade() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 900, 600);
    }

    public void mountScene(Stage stage) {
        stage.setTitle("Novo Especialidade");

        pane.getChildren().add(ivUserAdd);

        pane.getChildren().add(lblNome);
        pane.getChildren().add(tfNome);
        
        pane.getChildren().add(lblDescricao);
        pane.getChildren().add(tfDescricao);
       
        pane.getChildren().add(btnSalvar);

        ivUserAdd.relocate(50, 50);
        ivUserAdd.setFitHeight(100);
        ivUserAdd.setFitWidth(100);
        ivUserAdd.setPreserveRatio(true);

        lblNome.relocate(250, 90);
        tfNome.relocate(300, 90);
        tfNome.setMinWidth(200);
        
        lblDescricao.relocate(250, 120);
        tfDescricao.relocate(300, 90);
        tfDescricao.setMinWidth(200);
 
        btnSalvar.relocate(770, 560);
        btnSalvar.setMinWidth(80);
        btnSalvar.setOnAction(this);

        stage.setScene(scene);
    }

	@Override
	public void handle(ActionEvent event) {
		if(event.getTarget().equals(btnSalvar)) {
			
		}
	}
}
