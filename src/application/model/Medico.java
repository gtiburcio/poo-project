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
public class Medico implements IModel {

    private long id;

    private String nome;

    private String cpf;

    private String rg;

    private LocalDate dataNasc;

    private String crm;

    private String email;

    private String logradouro;

    private String cep;

    private String complemento;

    private String numero;

    private String bairro;

    private String cidade;

    private Estados uf;

    private String telResid;

    private String telCelular;

    private Genero genero;

    private Especialidade especialidade;

    private Usuario usuario;

    @Override
    public String toString() {
        return nome;
    }
}
