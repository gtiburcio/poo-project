package application.views.agendamento;

import application.controller.EspecialidadeController;
import application.controller.PacienteController;
import application.model.Especialidade;
import application.model.Paciente;
import application.views.Tela;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.List;

import static application.views.util.MaskUtils.cpfMask;
import static application.views.util.MaskUtils.removeAllMasks;
import static java.util.Objects.nonNull;

public class TelaNovoAgendamento implements Tela {

    private Stage stage;

    private final Scene scene;

    private final Pane pane;

    private final EspecialidadeController especialidadeController;

    private final PacienteController pacienteController;

    private List<Especialidade> especialidades;

    private TextField cpfPaciente;

    private Label nomeLabel;

    private ComboBox<Especialidade> comboEspecialidade;

    public TelaNovoAgendamento() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.especialidadeController = new EspecialidadeController();
        this.pacienteController = new PacienteController();
    }

    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;

        Label titulo = new Label("Novo Agendamento");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(480, 50);

        Image agendaImage = new Image("resources/images/agenda.png");
        ImageView agendaView = new ImageView(agendaImage);

        agendaView.setFitHeight(100);
        agendaView.setFitWidth(100);
        agendaView.setPreserveRatio(true);
        agendaView.relocate(330, 20);

        Label labelCpf = new Label("Cpf Paciente:");
        labelCpf.setFont(new Font("Arial", 16));
        labelCpf.relocate(130, 220);

        cpfPaciente = new TextField();
        cpfPaciente.relocate(250, 220);
        cpfPaciente.setMinWidth(100);
        cpfPaciente.setOnKeyReleased(p -> {
            cpfMask(cpfPaciente);
            if (cpfPaciente.getText().length() == 14) {
                loadPacienteByCpf();
            } else {
                nomeLabel.setText("Paciente: ");
                disableEspecialidadeCombo(true);
            }
        });

        nomeLabel = new Label("Paciente: ");
        nomeLabel.setFont(new Font("Arial", 16));
        nomeLabel.relocate(130, 270);

        Label labelEspecialidade = new Label("Especialidade:");
        labelEspecialidade.setFont(new Font("Arial", 16));
        labelEspecialidade.relocate(700, 220);

        comboEspecialidade = new ComboBox<>();
        comboEspecialidade.setItems(getEspecialidades());
        comboEspecialidade.relocate(840, 220);
        comboEspecialidade.setMinWidth(250);
        comboEspecialidade.setOnAction(action -> findMedicos());

        pane.getChildren().addAll(titulo, agendaView, labelCpf, cpfPaciente,
                nomeLabel,
                labelEspecialidade, comboEspecialidade);

        stage.setScene(scene);
        stage.setTitle("Novo Agendamento");
    }

    private ObservableList<Especialidade> getEspecialidades() {
        try {
            especialidades = especialidadeController.findAll();
            return FXCollections.observableList(especialidades);
        } catch (Exception ex) {
            errorMessage("Erro ao buscar as especialidades, tente novamente mais tarde...");
        }
        return null;
    }

    private void findMedicos() {
        System.out.println(comboEspecialidade.getSelectionModel().getSelectedItem());
    }

    private void loadPacienteByCpf() {
        String cpf = removeAllMasks(cpfPaciente.getText());
        try {
            nomeLabel.setText("Paciente: ...");
            Paciente paciente = pacienteController.findByCpf(cpf);
            if (nonNull(paciente)) {
                nomeLabel.setText("Paciente: " + paciente.getNome());
                disableEspecialidadeCombo(false);
            } else {
                nomeLabel.setText("Nenhum paciente encontrado com esse cpf...");
                disableEspecialidadeCombo(true);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            errorMessage("Erro ao buscar o paciente, tente novamente mais tarde...");
        }
    }

    private void disableEspecialidadeCombo(boolean flag) {
        comboEspecialidade.setDisable(flag);
    }
}
