package github.victtorribeiro.rest.controller;

import github.victtorribeiro.domain.entity.Cliente;
import github.victtorribeiro.domain.repository.Clientes;
import github.victtorribeiro.exception.RegraNegocioException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import static org.springframework.http.HttpStatus.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    private Clientes clientes;

    public ClienteController(Clientes clientes) {
        this.clientes = clientes;
    }

    @GetMapping("/{id}")
    public Cliente getClienteById( @PathVariable Integer id ){


        return clientes
                .findById(id)
                .orElseThrow( () -> new RegraNegocioException("Cliente não encontrado " + id));
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Cliente save( @RequestBody Cliente cliente){

        return clientes.save(cliente);

    }
    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete ( @PathVariable Integer id ){
        clientes.findById(id)
                .map( cliente -> {
                    clientes.delete(cliente);
                    return cliente;
                })
                .orElseThrow( () -> new RegraNegocioException("Cliente não encontrado " + id));


    }
    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Cliente cliente ){

        clientes
                .findById(id)
                .map( clienteExistente -> {
                    cliente.setId(clienteExistente.getId());
                    clientes.save(cliente);
                    return clienteExistente;
                } ).orElseThrow( () -> new RegraNegocioException("Cliente não encontrado " + id));

    }

    @GetMapping
    public List<Cliente> find ( Cliente filtro ) {
        ExampleMatcher matcher = ExampleMatcher
                                    .matching()
                                    .withIgnoreCase()
                                    .withStringMatcher( ExampleMatcher.StringMatcher.CONTAINING );
        Example example = Example.of(filtro, matcher);

        //List<Cliente> lista = clientes.findAll(example);

        return clientes.findAll();
    }



}
