package application.model;

import java.time.LocalDate;

import application.model.enums.Estados;
import application.model.enums.Genero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

	private Plano plano;

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
}
