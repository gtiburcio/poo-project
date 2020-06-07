package application.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Usuario implements IModel {

    private long id;

    private String nome;

    private String login;

    private String senha;

    private Perfil perfil;
}
