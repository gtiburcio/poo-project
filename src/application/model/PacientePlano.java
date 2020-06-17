package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PacientePlano implements IModel {
	
	private long id;
	
	private Paciente paciente;
	
	private Plano plano;
}
