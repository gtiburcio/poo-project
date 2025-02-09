package application.model;

import application.model.enums.Estados;
import application.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Paciente implements IModel {

	private long id;

	private String nome;

	private LocalDate dataNasc;

	private Genero genero;

	private String cpf;

	private String rg;

	private String nCarteirinha;

	private String email;

	private String telResid;

	private String telCelular;

	private String logradouro;

	private String cep;

	private String complemento;

	private String numero;

	private String bairro;

	private String cidade;

	private Estados uf;
	
	@Override
	public String toString() {
		return nome;
	}
}
