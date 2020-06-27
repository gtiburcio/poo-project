package application.views.medico.consulta;

import application.controller.ConsultaController;
import application.controller.PrescricaoController;
import application.mail.Mail;
import application.model.Agendamento;
import application.model.Consulta;
import application.model.Prescricao;
import application.views.Tela;
import application.views.medico.principal.TelaPrincipalMedico;
import application.views.secretaria.util.BotaoVoltar;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static javafx.collections.FXCollections.observableList;

public class TelaConsulta implements Tela {

    private Stage stage;

    private final Pane pane;

    private final Scene scene;

    private final Agendamento agendamento;

    private final ConsultaController consultaController;

    private final PrescricaoController prescricaoController;

    private TextField descricaoMedicamento;

    private List<String> prescricoes;

    private ListView<String> listaPrescricoes;

    public TelaConsulta(Agendamento agendamento) {
        this.pane = new Pane();
        this.scene = new Scene(pane, 1200, 700);
        this.agendamento = agendamento;
        this.consultaController = new ConsultaController();
        this.prescricaoController = new PrescricaoController();
        this.prescricoes = new ArrayList<>();
    }

    @Override
    public void mountScene(Stage stage) {
        this.stage = stage;

        Label titulo = new Label("Nova Consulta");
        titulo.setFont(new Font("Arial", 40));
        titulo.relocate(460, 50);

        Label labelNomePaciente = new Label("Paciente: " + agendamento.getPaciente().getNome());
        labelNomePaciente.setFont(new Font("Arial", 16));
        labelNomePaciente.relocate(250, 150);

        Label labelMedico = new Label("Médico: " + agendamento.getMedico().getNome());
        labelMedico.setFont(new Font("Arial", 16));
        labelMedico.relocate(800, 150);

        Label labelData = new Label("Data: " + agendamento.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        labelData.setFont(new Font("Arial", 16));
        labelData.relocate(250, 200);

        Label labelNota = new Label("Hora: " + agendamento.getHora());
        labelNota.setFont(new Font("Arial", 16));
        labelNota.relocate(800, 200);

        Label labelPrescricao = new Label("Prescrição:");
        labelPrescricao.setFont(new Font("Arial", 16));
        labelPrescricao.relocate(300, 280);

        descricaoMedicamento = new TextField();
        descricaoMedicamento.relocate(400, 280);
        descricaoMedicamento.setMinWidth(400);

        Button buttonAddPrescricao = new Button("+");
        buttonAddPrescricao.relocate(820, 278);
        buttonAddPrescricao.setStyle("-fx-background-color: ".concat("#4fddae"));
        buttonAddPrescricao.setMinWidth(30);
        buttonAddPrescricao.setMinHeight(30);
        buttonAddPrescricao.setOnMouseClicked(event -> adicionarPrescricao());

        listaPrescricoes = new ListView<>();
        listaPrescricoes.relocate(300, 330);
        listaPrescricoes.setPrefWidth(600);
        listaPrescricoes.setPrefHeight(300);
        listaPrescricoes.setItems(observableList(prescricoes));

        Button buttonRemoverPrescricao = new Button("-");
        buttonRemoverPrescricao.relocate(930, 330);
        buttonRemoverPrescricao.setStyle("-fx-background-color: ".concat("#d92027"));
        buttonRemoverPrescricao.setMinWidth(30);
        buttonRemoverPrescricao.setMinHeight(30);
        buttonRemoverPrescricao.setOnMouseClicked(event -> removerPrescricao());

        Button buttonFinalizar = new Button("Realizar Consulta!");
        buttonFinalizar.relocate(550, 640);
        buttonFinalizar.setStyle("-fx-background-color: ".concat("#5F57E5"));
        buttonFinalizar.setMinWidth(100);
        buttonFinalizar.setMinHeight(30);
        buttonFinalizar.setOnMouseClicked(event -> finalizar());

        BotaoVoltar botaoVoltar = new BotaoVoltar(stage, new TelaPrincipalMedico(agendamento.getMedico()));

        pane.getChildren().add(titulo);
        pane.getChildren().add(labelNomePaciente);
        pane.getChildren().add(labelMedico);
        pane.getChildren().add(labelData);
        pane.getChildren().add(labelNota);
        pane.getChildren().add(labelPrescricao);
        pane.getChildren().add(descricaoMedicamento);
        pane.getChildren().add(buttonAddPrescricao);
        pane.getChildren().add(listaPrescricoes);
        pane.getChildren().add(buttonRemoverPrescricao);
        pane.getChildren().add(buttonFinalizar);
        pane.getChildren().add(botaoVoltar.getButton());

        stage.setScene(scene);
        stage.setTitle("Nova Consulta");
    }

    private void finalizar() {
        Consulta consulta = Consulta.builder()
                .agendamento(agendamento)
                .data(LocalDate.now())
                .hora(LocalTime.now())
                .build();
        try {
            long id = consultaController.salvar(consulta);
            prescricaoController.salvar(montarPrescricoes(id));
            successMessage("Consulta realizada com sucesso!");
            new TelaPrincipalMedico(agendamento.getMedico()).mountScene(stage);
            Mail.sendMail(agendamento.getPaciente(), prescricoes);
        } catch (Exception ex) {
            ex.printStackTrace();
            errorMessage("Ocorreu um erro ao salvar a consulta, tente mais tarde...");
        }
    }

    private List<Prescricao> montarPrescricoes(long idConsulta) {
        return prescricoes.stream().map(p -> Prescricao.builder()
                .consulta(Consulta.builder().id(idConsulta).build())
                .descricao(p)
                .build()).collect(Collectors.toList());
    }

    private void adicionarPrescricao() {
        if (Objects.nonNull(descricaoMedicamento.getText()) && !descricaoMedicamento.getText().equals("")) {
            prescricoes.add(descricaoMedicamento.getText());
            listaPrescricoes.setItems(observableList(prescricoes));
            descricaoMedicamento.setText("");
        } else {
            errorMessage("Preencha o campo da prescrição para adicionar a lista!");
        }
    }

    private void removerPrescricao() {
        String item = listaPrescricoes.getSelectionModel().getSelectedItems().get(0);
        if (Objects.nonNull(item) && !item.equals("")) {
            listaPrescricoes.getItems().removeAll(item);
            prescricoes.remove(item);
        } else {
            errorMessage("Selecione uma prescricao para remover!!");
        }
    }
}
