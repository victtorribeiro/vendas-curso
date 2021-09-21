package github.victtorribeiro.domain.repository;

import github.victtorribeiro.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface Clientes extends JpaRepository< Cliente, Integer > {

    @Query(value = " select * from Cliente c where c.nome like '%:nome%' ", nativeQuery = true)
    List<Cliente> encontraPorNome( @Param("nome") String nome);

    //List<Cliente> findByNomeOrId(String nome, Integer id);

    void deleteByNome(String nome);

    //Cliente findOneByNome()
    boolean existsByNome(String nome);

    @Query(" select c from Cliente c left join fetch c.pedidos p where c.id = :id ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );



}
