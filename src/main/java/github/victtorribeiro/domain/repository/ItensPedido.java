package github.victtorribeiro.domain.repository;

import github.victtorribeiro.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {

}
