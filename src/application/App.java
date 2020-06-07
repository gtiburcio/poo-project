package application;

import application.views.consulta.NovaConsulta;
import application.views.login.TelaLogin;
import application.views.medico.NovoMedico;
import application.views.paciente.NovoPaciente;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        new NovaConsulta().mountScene(stage);
        stage.setResizable(false);
        stage.show();
    }
}
