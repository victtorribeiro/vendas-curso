package github.victtorribeiro.rest.controller;

import github.victtorribeiro.domain.entity.ItemPedido;
import github.victtorribeiro.domain.entity.Pedido;
import github.victtorribeiro.domain.enums.StatusPedido;
import github.victtorribeiro.exception.RegraNegocioException;
import github.victtorribeiro.rest.dto.AtualizacaoStatusPedidoDTO;
import github.victtorribeiro.rest.dto.InformacaoItemPedidoDTO;
import github.victtorribeiro.rest.dto.InformacoesPedidoDTO;
import github.victtorribeiro.rest.dto.PedidoDTO;
import github.victtorribeiro.service.PedidoService;
import static org.springframework.http.HttpStatus.*;

import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody @Valid PedidoDTO dto ){

        Pedido pedido = service.salvar(dto);
        return pedido.getId();

    }

    @GetMapping("{id}")
    public InformacoesPedidoDTO getById( @PathVariable Integer id) {

        return service
                .obterPedidoCompleto(id)
                .map(this::converterPedido)
                .orElseThrow( () -> new RegraNegocioException("Pedido não encontrado. " + id));
    }

    private InformacoesPedidoDTO converterPedido (Pedido pedido){
        return InformacoesPedidoDTO
                .builder()
                .codigo(pedido.getId())
                .dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
                .cpf(pedido.getCliente().getCpf())
                .nomeCliente(pedido.getCliente().getNome())
                .total(pedido.getTotal())
                .status(pedido.getStatus().name())
                .itens(converterItens(pedido.getItens()))
                .build();
    }


    private List<InformacaoItemPedidoDTO> converterItens(List<ItemPedido> itens){
        if (CollectionUtils.isEmpty(itens)){
            return Collections.emptyList();
        }

        return itens
                .stream()
                .map( item -> InformacaoItemPedidoDTO
                        .builder()
                        .descricaoProduto(item.getProduto().getDescricao())
                        .precoUnitario(item.getProduto().getPreco())
                        .quantidade(item.getQuantidade())
                        .build()
                ).collect(Collectors.toList());
    }

    @PatchMapping("{id}")
    public void updateStatus( @PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto){
        //String novoStatus = dto.getNovoStatus();
        service.atualizaStatus(id, StatusPedido.valueOf(dto.getNovoStatus()));
    }

}
