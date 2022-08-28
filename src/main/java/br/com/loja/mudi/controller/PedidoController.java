package br.com.loja.mudi.controller;

import br.com.loja.mudi.dto.RequestPedidoDTO;
import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.User;
import br.com.loja.mudi.repository.PedidoRepository;
import br.com.loja.mudi.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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
    UserRepository userRepository;

    public PedidoController(PedidoRepository pedidoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("formulario")
    public String formulario(RequestPedidoDTO requestPedidoDTO) {
        return "pedido/formulario";
    }

    @PostMapping("novo")
    public String novo(@Valid RequestPedidoDTO requestPedidoDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "pedido/formulario";
        }
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User authenticatedUser = userRepository.findById(username).get();
        Pedido pedido = requestPedidoDTO.toPedido();
        pedido.setUser(authenticatedUser);
        pedidoRepository.save(pedido);
        return "redirect:/usuario/todos";
    }

}
