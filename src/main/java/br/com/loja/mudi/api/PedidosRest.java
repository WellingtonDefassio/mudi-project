package br.com.loja.mudi.api;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;
import br.com.loja.mudi.repository.PedidoRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/pedidos")
public class PedidosRest {

    PedidoRepository pedidoRepository;

    public PedidosRest(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("aguardando")
    public ResponseEntity getPedidosAguardando() {
        Sort sort = Sort.by("id").descending();
        PageRequest page = PageRequest.of(0, 10, sort);
        List<Pedido> byStatusPedido = pedidoRepository.findByStatusPedido(StatusPedido.AGUARDANDO, page);

        return new ResponseEntity(byStatusPedido, HttpStatus.OK);

    }

}
