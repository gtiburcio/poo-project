package application.views;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public interface Tela {

    void mountScene(Stage stage);

    default void successMessage(String message) {
        Alert dialogoSuccess = new Alert(Alert.AlertType.CONFIRMATION);
        dialogoSuccess.setHeaderText(message);
        dialogoSuccess.showAndWait();
    }

    default void errorMessage(String message) {
        Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
        dialogoErro.setHeaderText(message);
        dialogoErro.showAndWait();
    }

    default boolean choiceOptionMessage(String message) {
        ButtonType ok = new ButtonType("Sim", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancel = new ButtonType("Não", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert dialog = new Alert(Alert.AlertType.WARNING, message, ok, cancel);
        dialog.setContentText(message);
        Optional<ButtonType> result = dialog.showAndWait();
        return result.orElse(cancel).equals(ok);
    }
}
