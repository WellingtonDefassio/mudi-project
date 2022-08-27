package br.com.loja.mudi.controller;

import br.com.loja.mudi.dto.RequestPedidoDTO;
import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller()
@RequestMapping("pedido")
public class PedidoController {

    PedidoRepository pedidoRepository;

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("formulario")
    public String formulario(RequestPedidoDTO requestPedidoDTO) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequestPedidoDTO requestPedidoDTO, BindingResult result) {
        if(result.hasErrors()) {
            return "pedido/formulario";
        }
        Pedido pedido = requestPedidoDTO.toPedido();
        pedidoRepository.save(pedido);
        return "redirect:/home";
    }

}
