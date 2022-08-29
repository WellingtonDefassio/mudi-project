package br.com.loja.mudi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oferta")
public class OfertaController {
    @GetMapping
    public String getFormularioParaOfertas(Model model) {
        model.addAttribute("nomePag", "facaOferta");

        return "oferta/home";
    }
}
