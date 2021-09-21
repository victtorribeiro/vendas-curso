package github.victtorribeiro;

import github.victtorribeiro.domain.entity.Cliente;
import github.victtorribeiro.domain.entity.Pedido;
import github.victtorribeiro.domain.repository.Clientes;
import github.victtorribeiro.domain.repository.Pedidos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

//@SpringBootApplication -- reconhece que esta classe inicia uma aplicação spring boot
@SpringBootApplication
public class VendasApplication {

    @Bean
    public CommandLineRunner init(
            @Autowired Clientes clientes,
            @Autowired Pedidos pedidos
            ){
        return args -> {
            System.out.println("Salvando Clientes");
            Cliente fulano = new Cliente("Victor");
            clientes.save(fulano);

//            clientes.save(new Cliente("Raquel"));

            Pedido p = new Pedido();
            p.setCliente( fulano );
            p.setDataPedido(LocalDate.now());
            p.setTotal(BigDecimal.valueOf(200));

            pedidos.save(p);


//            Cliente cliente = clientes.findClienteFetchPedidos(fulano.getId());
//            System.out.println(cliente);
//            System.out.println(cliente.getPedidos());

            pedidos.findByCliente(fulano).forEach(System.out::println);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }




}
