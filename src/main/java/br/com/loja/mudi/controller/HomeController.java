package br.com.loja.mudi.controller;

import br.com.loja.mudi.model.Pedido;
import br.com.loja.mudi.model.StatusPedido;
import br.com.loja.mudi.repository.PedidoRepository;
import br.com.loja.mudi.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("home")
public class HomeController {
    PedidoRepository pedidoRepository;
    UserRepository userRepository;

    public HomeController(PedidoRepository pedidoRepository, UserRepository userRepository) {
        this.pedidoRepository = pedidoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String home(Model model) {
        Sort sort = Sort.by("dataDaEntrega").descending();
        PageRequest paginacao = PageRequest.of(0, 10, sort);


        List<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.ENTREGUE,paginacao);
        model.addAttribute("pedidos", pedidos);
        model.addAttribute("nomePag", "home");
        return "home";
    }

}
