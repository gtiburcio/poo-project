package application;

import application.views.medico.NovoMedico;
import application.views.paciente.NovoPaciente;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene novoPacienteScene = new NovoMedico().mountScene(stage);
        stage.setScene(novoPacienteScene);
        stage.setResizable(false);
        stage.show();
    }
}
