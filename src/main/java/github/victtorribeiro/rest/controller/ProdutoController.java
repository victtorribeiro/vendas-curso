package github.victtorribeiro.rest.controller;

import github.victtorribeiro.domain.entity.Produto;
import github.victtorribeiro.domain.repository.Produtos;
import github.victtorribeiro.exception.RegraNegocioException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos produtos) {
        this.repository = produtos;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save(@RequestBody @Valid Produto produto ){

        return repository.save(produto);
    }

    @GetMapping("{id}")
    public Produto getProdutoById( @PathVariable Integer id ){

        return repository
                .findById(id)
                .orElseThrow( () -> new RegraNegocioException("Produto não encontrado " + id));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete ( @PathVariable Integer id){
        repository.findById(id)
                .map( p -> {
                    repository.delete(p);
                    return p;
                })
                .orElseThrow( () -> new RegraNegocioException("Produto não encontrado " + id));
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update ( @PathVariable Integer id, @RequestBody Produto produto){

        repository.findById(id)
                .map( p -> {
                    produto.setId(p.getId());
                    repository.save(produto);
                    return p;
                })
                .orElseThrow( () -> new RegraNegocioException("Produto não encontrado " + id));

    }
    @GetMapping
    public List<Produto> find ( Produto filtro){

        ExampleMatcher matcher = ExampleMatcher
                .matching()
                .withIgnoreCase()
                .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);

        return repository.findAll();
    }


}
