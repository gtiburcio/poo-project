package application.views.medico;

import application.controller.AgendamentoController;
import application.model.Agendamento;
import application.model.Medico;
import application.views.Tela;
import application.views.login.TelaLogin;
import application.views.secretaria.util.BotaoVoltar;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TelaPrincipalMedico implements Tela {

    private final Pane pane;

    private final Scene scene;

    private final Medico medico;

    private DatePicker dpAgendamento;

    private TableView<Agendamento> table;

    private final AgendamentoController agendamentoController;

    private List<Agendamento> agendamentosDia;

    public TelaPrincipalMedico(Medico medico) {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.medico = medico;
        this.agendamentoController = new AgendamentoController();
    }

    @Override
    public void mountScene(Stage stage) {
        Label titulo = new Label("Bem vindo ".concat(medico.getNome()));
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(400, 50);

        Label labelDataAgendamento = new Label("Data:");
        labelDataAgendamento.setFont(new Font("Arial", 16));
        labelDataAgendamento.relocate(700, 120);

        dpAgendamento = new DatePicker();
        dpAgendamento.relocate(760, 120);
        dpAgendamento.setMinWidth(200);

        Button buttonLogin = new Button("Filtrar");
        buttonLogin.relocate(1000, 120);
        buttonLogin.setStyle("-fx-background-color: #4fddae; -fx-border-radius: 50px");
        buttonLogin.setMinWidth(100);
        buttonLogin.setMinHeight(20);
        buttonLogin.setOnMouseClicked(event -> loadAgendamentos(dpAgendamento.getValue()));

        TableColumn<Agendamento, String> dataColumn = new TableColumn<>("Data");
        dataColumn.setMinWidth(333);
        dataColumn.setCellValueFactory(agendamento -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(agendamento.getValue().getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            return property;
        });

        TableColumn<Agendamento, LocalTime> horaColumn = new TableColumn<>("Hora");
        horaColumn.setMinWidth(333);
        horaColumn.setCellValueFactory(new PropertyValueFactory<>("hora"));

        TableColumn<Agendamento, String> pacienteColumn = new TableColumn<>("Paciente");
        pacienteColumn.setMinWidth(333);
        pacienteColumn.setCellValueFactory(agendamento -> {
            SimpleObjectProperty property = new SimpleObjectProperty();
            property.setValue(agendamento.getValue().getPaciente().getNome());
            return property;
        });

        table = new TableView<>();
        table.getColumns().add(dataColumn);
        table.getColumns().add(horaColumn);
        table.getColumns().add(pacienteColumn);
        table.setMinWidth(1000);
        table.relocate(100, 200);

        loadAgendamentos(LocalDate.now());

        BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaLogin());

        pane.getChildren().add(titulo);
        pane.getChildren().add(table);
        pane.getChildren().add(labelDataAgendamento);
        pane.getChildren().add(dpAgendamento);
        pane.getChildren().add(buttonLogin);
        pane.getChildren().add(botaoVoltar.getButton());

        stage.setScene(scene);
        stage.setTitle("Agendamento do Dia");
    }

    private void loadAgendamentos(LocalDate date) {
        try {
            agendamentosDia = agendamentoController.findAgendamentosDiaMedico(date, medico.getId());
            table.setItems(FXCollections.observableList(agendamentosDia));
        } catch (Exception throwables) {
            errorMessage("Ocorreu um erro ao carregar os agendamentos, tente mais tarde...");
        }
    }
}
