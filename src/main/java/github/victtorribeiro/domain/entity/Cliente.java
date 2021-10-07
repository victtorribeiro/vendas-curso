package github.victtorribeiro.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 100)
    @NotEmpty(message = "Campo nome é obrigatório.")
    private String nome;

    @Column(length = 11)
    @NotEmpty(message = "Campo CPF é obrigatório.")
    @CPF(message = "Informe um CPF válido")
    private String cpf;

    @JsonIgnore
    @OneToMany( mappedBy = "cliente", fetch = FetchType.LAZY )
    private Set<Pedido> pedidos;






    public Cliente(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }


}
