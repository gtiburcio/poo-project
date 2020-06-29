package application.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {
	M("Masculino"),
	F("Feminino"),
	N("Não binário"),
	O("Outro");

    private String nome;
    
    @Override
    public String toString() {
        return nome;
    }
}
