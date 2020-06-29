package application.views.secretaria.util;

import application.views.Tela;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class BotaoVoltar {

    private final Stage stage;

    private final Tela telaAnterior;

    public BotaoVoltar(Stage stage, Tela telaAnterior) {
        this.stage = stage;
        this.telaAnterior = telaAnterior;
    }

    public ImageView getButton() {
        Image voltarImage = new Image("resources/images/voltar.png");
        ImageView voltarView = new ImageView(voltarImage);

        voltarView.setFitHeight(40);
        voltarView.setFitWidth(40);
        voltarView.setPreserveRatio(true);
        voltarView.relocate(20, 20);
        voltarView.setOnMouseEntered(event -> voltarView.setStyle("-fx-cursor: hand"));
        voltarView.setOnMouseExited(event -> voltarView.setStyle(null));
        voltarView.setOnMouseClicked(event -> telaAnterior.mountScene(stage));

        return voltarView;
    }
}
