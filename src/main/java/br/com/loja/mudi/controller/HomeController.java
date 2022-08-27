package br.com.loja.mudi.controller;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;
import br.com.loja.mudi.repository.PedidoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    PedidoRepository pedidoRepository;

    public HomeController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @GetMapping()
    public String home(Model model) {
        List<Pedido> pedidos = pedidoRepository.findAll();
        model.addAttribute("pedidos", pedidos);
        return "home";
    }
    @GetMapping("/{status}")
    public String porStatus(@PathVariable("status") String status, Model model) {
        List<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.valueOf(status.toUpperCase()));
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("status", status);
        return "home";
    }

    @ExceptionHandler(Exception.class)
    public String onError() {
        return "redirect:/home";
    }

}
