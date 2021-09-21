package github.victtorribeiro.domain.repository;

import github.victtorribeiro.domain.entity.Cliente;
import github.victtorribeiro.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);

}
