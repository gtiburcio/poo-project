package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Plano implements IModel {

    private long id;

    private String nome;
    
    private Convenio convenio;
    
    @Override
	public String toString() {
		return nome;
	}
}
