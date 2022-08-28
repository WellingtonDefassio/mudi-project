package br.com.loja.mudi.controller;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;
import br.com.loja.mudi.repository.PedidoRepository;
import br.com.loja.mudi.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    PedidoRepository pedidoRepository;
    UserRepository userRepository;

    public UsuarioController(PedidoRepository pedidoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("todos")
    public String home(Model model, Principal principal) {
        List<Pedido> pedidos = pedidoRepository.findAllByUser(principal.getName());
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("nomePag", "todosPedidos");
        return "usuario/home";
    }

    @GetMapping("{status}")
    public String porStatus(@PathVariable("status") String status, Model model, Principal principal) {

        List<Pedido> pedidos = pedidoRepository.findByUsernameAndStatusPedido(principal.getName(), StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        model.addAttribute("nomePag", "todosPedidos");
        return "usuario/home";
    }

    @ExceptionHandler(Exception.class)
    public String onError() {
        return "redirect:/usuario/home";
    }

}
