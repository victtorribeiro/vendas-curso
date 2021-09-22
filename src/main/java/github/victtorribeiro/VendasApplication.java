package github.victtorribeiro;

import github.victtorribeiro.domain.entity.Cliente;
import github.victtorribeiro.domain.repository.Clientes;
import org.h2.command.Command;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//@SpringBootApplication -- reconhece que esta classe inicia uma aplicação spring boot
@SpringBootApplication
public class VendasApplication {


    public static void main(String[] args) {
        SpringApplication.run(VendasApplication.class, args);
    }




}
