package br.com.loja.mudi.controller;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    PedidoRepository pedidoRepository;

    public HomeController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping("/home")
    public String home(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }

}
