package github.victtorribeiro.domain.repository;

import github.victtorribeiro.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto, Integer> {
}
