package application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Genero {
	M("Masculino"),
	F("Feminino"),
	N("N�o bin�rio"),
	O("Outro");

    private String nome;
    
    @Override
    public String toString() {
        return nome;
    }
}
