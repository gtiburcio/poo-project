package application.views.principal;

import application.views.Tela;
import application.views.agendamento.TelaNovoAgendamento;
import application.views.especialidade.TelaNovaEspecialidade;
import application.views.medico.TelaMedicos;
import application.views.paciente.TelaPacientes;
import application.views.usuario.TelaUsuarios;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class TelaPrincipal implements Tela {

    private final Pane pane;

    private final Scene scene;

    private final String menuBorder = "-fx-border-color: grey; -fx-border-radius: 7; -fx-cursor: hand;";

    public TelaPrincipal() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
    }

    @Override
    public void mountScene(Stage stage) {

        Label titulo = new Label("Página Inicial");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(455, 50);

        Image pacienteImage = new Image("resources/images/paciente.png");
        ImageView pacienteView = new ImageView(pacienteImage);
        pacienteView.setFitHeight(100);
        pacienteView.setFitWidth(100);
        pacienteView.setPreserveRatio(true);

        Label labelPaciente = new Label("Paciente", pacienteView);
        labelPaciente.relocate(200, 200);
        labelPaciente.setFont(new Font("Arial", 20));

        labelPaciente.setOnMouseEntered(event -> labelPaciente.setStyle(menuBorder));
        labelPaciente.setOnMouseExited(event -> labelPaciente.setStyle(null));
        labelPaciente.setOnMouseClicked(event -> new TelaPacientes().mountScene(stage));

        Image medicoImage = new Image("resources/images/medico.png");
        ImageView medicoView = new ImageView(medicoImage);
        medicoView.setFitHeight(100);
        medicoView.setFitWidth(100);
        medicoView.setPreserveRatio(true);

        Label labelMedico = new Label("Médico", medicoView);
        labelMedico.setFont(new Font("Arial", 20));
        labelMedico.relocate(500, 200);

        labelMedico.setOnMouseEntered(event -> labelMedico.setStyle(menuBorder));
        labelMedico.setOnMouseExited(event -> labelMedico.setStyle(null));
        labelMedico.setOnMouseClicked(event -> new TelaMedicos().mountScene(stage));


        Image especialidadeImage = new Image("resources/images/especialidade.png");
        ImageView especialidadeView = new ImageView(especialidadeImage);
        especialidadeView.setFitHeight(100);
        especialidadeView.setFitWidth(100);
        especialidadeView.setPreserveRatio(true);

        Label labelEspecialidade = new Label("Especialidade", especialidadeView);
        labelEspecialidade.setFont(new Font("Arial", 20));
        labelEspecialidade.relocate(800, 220);

        labelEspecialidade.setOnMouseEntered(event -> labelEspecialidade.setStyle(menuBorder));
        labelEspecialidade.setOnMouseExited(event -> labelEspecialidade.setStyle(null));
        labelEspecialidade.setOnMouseClicked(event -> new TelaNovaEspecialidade().mountScene(stage));


        Image agendaImage = new Image("resources/images/agenda.png");
        ImageView agendaView = new ImageView(agendaImage);
        agendaView.setFitHeight(100);
        agendaView.setFitWidth(100);
        agendaView.setPreserveRatio(true);

        Label labelAgenda = new Label("Agenda", agendaView);
        labelAgenda.setFont(new Font("Arial", 20));
        labelAgenda.relocate(200, 400);

        labelAgenda.setOnMouseEntered(event -> labelAgenda.setStyle(menuBorder));
        labelAgenda.setOnMouseExited(event -> labelAgenda.setStyle(null));
        labelAgenda.setOnMouseClicked(event -> new TelaNovoAgendamento().mountScene(stage));

        Image pagamentoImage = new Image("resources/images/pagamento.png");
        ImageView pagamentoView = new ImageView(pagamentoImage);
        pagamentoView.setFitHeight(100);
        pagamentoView.setFitWidth(100);
        pagamentoView.setPreserveRatio(true);

        Label labelPagamento = new Label("Pagamento", pagamentoView);
        labelPagamento.setFont(new Font("Arial", 20));
        labelPagamento.relocate(500, 400);

        labelPagamento.setOnMouseEntered(event -> labelPagamento.setStyle(menuBorder));
        labelPagamento.setOnMouseExited(event -> labelPagamento.setStyle(null));

        Image usuarioImage = new Image("resources/images/usuario.png");
        ImageView usuarioView = new ImageView(usuarioImage);
        usuarioView.setFitHeight(100);
        usuarioView.setFitWidth(100);
        usuarioView.setPreserveRatio(true);

        Label labelUsuario = new Label("Usuários", usuarioView);
        labelUsuario.setFont(new Font("Arial", 20));
        labelUsuario.relocate(800, 400);

        labelUsuario.setOnMouseEntered(event -> labelUsuario.setStyle(menuBorder));
        labelUsuario.setOnMouseExited(event -> labelUsuario.setStyle(null));
        labelUsuario.setOnMouseClicked(event -> new TelaUsuarios().mountScene(stage));

        Label footer = new Label("");
        double footerHeight = 70;
        footer.relocate(0, scene.getHeight() - footerHeight);
        footer.setMinWidth(scene.getWidth());
        footer.setMinHeight(footerHeight);
        footer.setStyle("-fx-background-color: #4fddae;");

        pane.getChildren().add(titulo);
        pane.getChildren().add(labelPaciente);
        pane.getChildren().add(labelMedico);
        pane.getChildren().add(labelEspecialidade);
        pane.getChildren().add(labelAgenda);
        pane.getChildren().add(labelPagamento);
        pane.getChildren().add(labelUsuario);
        pane.getChildren().add(footer);

        stage.setScene(scene);
        stage.setTitle("Principal");
    }
}
