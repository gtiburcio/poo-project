package application.views.agendamento;

import application.controller.AgendamentoController;
import application.controller.EspecialidadeController;
import application.controller.MedicoController;
import application.controller.PacienteController;
import application.model.Agendamento;
import application.model.Especialidade;
import application.model.Medico;
import application.model.Paciente;
import application.views.Tela;
import application.views.principal.TelaPrincipal;
import application.views.util.BotaoVoltar;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static application.model.Agendamento.builder;
import static application.views.util.MaskUtils.cpfMask;
import static application.views.util.MaskUtils.removeAllMasks;
import static java.util.Objects.nonNull;
import static javafx.collections.FXCollections.observableList;

public class TelaNovoAgendamento implements Tela {

    private Stage stage;

    private final Scene scene;

    private final Pane pane;

    private final AgendamentoController agendamentoController;

    private final EspecialidadeController especialidadeController;

    private final MedicoController medicoController;

    private final PacienteController pacienteController;

    private List<Especialidade> especialidades;

    private TextField cpfPaciente;

    private Label nomeLabel;

    private ComboBox<Especialidade> comboEspecialidade;

    private ComboBox<Medico> comboMedico;

    private DatePicker dpAgendamento = new DatePicker();

    private ComboBox<LocalTime> comboHorarios;

    private Paciente paciente;

    public TelaNovoAgendamento() {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.agendamentoController = new AgendamentoController();
        this.especialidadeController = new EspecialidadeController();
        this.medicoController = new MedicoController();
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
                disableComboEspecialidades(true);
            }
        });

        nomeLabel = new Label("Paciente:");
        nomeLabel.setFont(new Font("Arial", 16));
        nomeLabel.relocate(130, 300);

        Label labelEspecialidade = new Label("Especialidade:");
        labelEspecialidade.setFont(new Font("Arial", 16));
        labelEspecialidade.relocate(700, 220);

        comboEspecialidade = new ComboBox<>();
        comboEspecialidade.setItems(getEspecialidades());
        comboEspecialidade.relocate(840, 220);
        comboEspecialidade.setMinWidth(250);
        comboEspecialidade.setOnAction(action -> findMedicos());

        Label labelMedico = new Label("Medico:");
        labelMedico.setFont(new Font("Arial", 16));
        labelMedico.relocate(700, 380);

        comboMedico = new ComboBox<>();
        comboMedico.relocate(840, 380);
        comboMedico.setMinWidth(250);
        comboMedico.setOnAction(action -> liberarHorario());

        Label labelDataAgendamento = new Label("Data:");
        labelDataAgendamento.setFont(new Font("Arial", 16));
        labelDataAgendamento.relocate(130, 380);

        dpAgendamento.relocate(200, 380);
        dpAgendamento.setMinWidth(200);
        dpAgendamento.setOnAction(action -> carregarHorario());

        Label labelHoraio = new Label("Horário:");
        labelHoraio.setFont(new Font("Arial", 16));
        labelHoraio.relocate(130, 510);

        comboHorarios = new ComboBox<>();
        comboHorarios.relocate(210, 510);
        comboHorarios.setMinWidth(60);

        Button buttonSalvar = new Button("Salvar");
        buttonSalvar.relocate(840, 510);
        buttonSalvar.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonSalvar.setMinWidth(250);
        buttonSalvar.setMinHeight(30);
        buttonSalvar.setOnMouseClicked(event -> salvar());

        BotaoVoltar voltar = new BotaoVoltar(stage, new TelaPrincipal());

        pane.getChildren().addAll(titulo, agendaView, labelCpf, cpfPaciente, nomeLabel,
                labelEspecialidade, comboEspecialidade,
                labelMedico, comboMedico,
                voltar.getButton(),
                labelDataAgendamento, dpAgendamento,
                labelHoraio, comboHorarios, buttonSalvar);

        disableComboEspecialidades(true);
        disableComboMedicos(true);
        disableDataAgendamento(true);
        disableHorarios(true);

        stage.setScene(scene);
        stage.setTitle("Novo Agendamento");
    }

    private void salvar() {
        Medico medico = comboMedico.getSelectionModel().getSelectedItem();
        LocalDate data = dpAgendamento.getValue();
        LocalTime horario = comboHorarios.getSelectionModel().getSelectedItem();
        if (validarAgendamento(medico, data, horario)) {
            Agendamento agendamento = builder()
                    .data(data)
                    .hora(horario)
                    .medico(medico)
                    .paciente(paciente)
                    .build();
            try {
                agendamentoController.salvar(agendamento);
                successMessage("Agendamento realizado com sucesso!");
                new TelaPrincipal().mountScene(this.stage);
            } catch (SQLException throwables) {
                errorMessage("Ocorreu um erro ao tentar realizar o agendamento, tente novamente mais tarde...");
            }
        } else {
            errorMessage("Por favor, preencher todos os campos!");
        }
    }

    private void carregarHorario() {
        LocalDate dataAgendamento = dpAgendamento.getValue();
        Medico medico = comboMedico.getSelectionModel().getSelectedItem();
        disableHorarios(true);
        if (nonNull(dataAgendamento) && nonNull(medico)) {
            try {
                List<LocalTime> horarios = agendamentoController.montarHorariosAgendamento(dataAgendamento, medico.getId());
                comboHorarios.setItems(observableList(horarios));
                disableHorarios(false);
            } catch (SQLException throwables) {
                errorMessage("Erro ao buscar os horarios, tente novamente mais tarde...");
            }
        }
    }

    private void liberarHorario() {
        Medico medico = comboMedico.getSelectionModel().getSelectedItem();
        if (nonNull(medico)) {
            disableDataAgendamento(false);
        }
    }

    private ObservableList<Especialidade> getEspecialidades() {
        try {
            especialidades = especialidadeController.findAll();
            return observableList(especialidades);
        } catch (Exception ex) {
            errorMessage("Erro ao buscar as especialidades, tente novamente mais tarde...");
        }
        return null;
    }

    private void findMedicos() {
        Especialidade especialidade = comboEspecialidade.getSelectionModel().getSelectedItem();
        disableComboMedicos(true);
        if (nonNull(especialidade)) {
            try {
                preencherComboMedicos(medicoController.findByEspecialidade(especialidade.getId()));
                disableComboMedicos(false);
            } catch (Exception exception) {
                disableComboMedicos(true);
                errorMessage("Erro ao buscar os medicos, tente novamente mais tarde...");
            }
        }
    }

    private void loadPacienteByCpf() {
        String cpf = removeAllMasks(cpfPaciente.getText());
        paciente = null;
        try {
            nomeLabel.setText("Paciente: ...");
            Paciente paciente = pacienteController.findByCpf(cpf);
            if (nonNull(paciente)) {
                this.paciente = paciente;
                nomeLabel.setText("Paciente: " + paciente.getNome());
                disableComboEspecialidades(false);
            } else {
                nomeLabel.setText("Nenhum paciente encontrado com esse cpf...");
                disableComboEspecialidades(true);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            errorMessage("Erro ao buscar o paciente, tente novamente mais tarde...");
        }
    }

    private void disableComboEspecialidades(boolean flag) {
        if (flag) {
            disableComboMedicos(flag);
        }
        comboEspecialidade.getSelectionModel().clearSelection();
        comboEspecialidade.setDisable(flag);
    }

    private void disableComboMedicos(boolean flag) {
        if (flag) {
            disableDataAgendamento(flag);
        }
        comboMedico.getSelectionModel().clearSelection();
        comboMedico.setDisable(flag);
    }

    private void disableDataAgendamento(boolean flag) {
        if (flag) {
            disableHorarios(flag);
        }
        dpAgendamento.setValue(null);
        dpAgendamento.setDisable(flag);
    }

    private void disableHorarios(boolean flag) {
        comboHorarios.getSelectionModel().clearSelection();
        comboHorarios.setDisable(flag);
    }

    private void preencherComboMedicos(List<Medico> medicos) {
        ObservableList<Medico> observableList = observableList(medicos);
        comboMedico.setItems(observableList);
    }

    private boolean validarAgendamento(Medico medico, LocalDate data, LocalTime hora) {
        return nonNull(paciente) && nonNull(medico) && nonNull(data) && nonNull(hora);
    }
}
