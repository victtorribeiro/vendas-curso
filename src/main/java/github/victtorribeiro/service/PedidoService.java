package github.victtorribeiro.service;

import github.victtorribeiro.domain.entity.Pedido;
import github.victtorribeiro.domain.enums.StatusPedido;
import github.victtorribeiro.rest.dto.PedidoDTO;

import java.util.Optional;

public interface PedidoService {

    Pedido salvar(PedidoDTO dto);

    Optional<Pedido> obterPedidoCompleto(Integer id);

    void atualizaStatus(Integer id, StatusPedido statusPedido);

}
