package application;

import application.views.agendamento.TelaNovoAgendamento;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        new TelaNovoAgendamento().mountScene(stage);
        stage.setResizable(false);
        stage.show();
    }
}
