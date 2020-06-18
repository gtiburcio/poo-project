package application.views.util;

import javafx.application.Platform;
import javafx.scene.control.TextField;

public class MaskUtils {

    public static String removeAllMasks(String value) {
        return value.replace(".", "").replace("-", "").replace(",", "");
    }

    public static void cpfMask(TextField textField) {
        maxField(textField, 14);
        String value = textField.getText();
        value = value.replaceAll("[^0-9]", "");
        value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
        value = value.replaceFirst("(\\d{3})(\\d)", "$1.$2");
        value = value.replaceFirst("(\\d{3})(\\d)", "$1-$2");
        positionCaret(textField);
        textField.setText(value);
    }

    public static void cepMask(TextField textField) {
        maxField(textField, 9);
        String value = textField.getText();
        value = value.replaceAll("[^0-9]", "");
        value = value.replaceFirst("(\\d{5})(\\d)", "$1-$2");
        positionCaret(textField);
        textField.setText(value);
    }

    private static void positionCaret(TextField textField) {
        Platform.runLater(() -> {
                    if (textField.getText().length() != 0) {
                        textField.positionCaret(textField.getText().length());
                    }
                }
        );
    }

    public static void maxField(TextField textField, Integer length) {
        textField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                    if (newValue == null || newValue.length() > length) {
                        textField.setText(oldValue);
                    }
                }
        );
    }
}
