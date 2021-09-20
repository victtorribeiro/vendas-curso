package github.victtorribeiro.domain.repositorio;

import github.victtorribeiro.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface Clientes extends JpaRepository< Cliente, Integer > {

    List<Cliente> findByNome(String nome);

    //List<Cliente> findByNomeOrId(String nome, Integer id);

    //Cliente findOneByNome()
    boolean existsByNome(String nome);

}
