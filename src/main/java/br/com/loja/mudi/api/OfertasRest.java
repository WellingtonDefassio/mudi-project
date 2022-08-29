package br.com.loja.mudi.api;

import br.com.loja.mudi.dto.RequestOfertaDTO;
import br.com.loja.mudi.model.Oferta;
import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.repository.PedidoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/ofertas")
public class OfertasRest {

    PedidoRepository pedidoRepository;

    public OfertasRest(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Oferta> criaOferta(@Valid @RequestBody RequestOfertaDTO ofertaDTO) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(ofertaDTO.getPedidoId());

        if (!pedidoOptional.isPresent()) {
            return null;
        }
        Pedido pedido = pedidoOptional.get();
        Oferta oferta = ofertaDTO.toOferta();
        oferta.setPedido(pedido);
        pedido.getOferta().add(oferta);

        pedidoRepository.save(pedido);

        return new ResponseEntity<>(oferta, HttpStatus.CREATED);
    }

}

