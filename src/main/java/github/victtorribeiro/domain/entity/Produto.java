package github.victtorribeiro.domain.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @NotEmpty(message = "Campo Descrição é obriagatório.")
    private String descricao;

    @Column(name = "preco_unitario")
    @NotNull(message = "Campo Preço é obrigatório")
    private BigDecimal preco;


}
