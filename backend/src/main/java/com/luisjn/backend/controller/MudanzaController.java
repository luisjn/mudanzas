package com.luisjn.backend.controller;

import com.luisjn.backend.model.Mudanza;
import com.luisjn.backend.service.MudanzaService;
import com.luisjn.backend.util.factory.MudanzaFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("mudanza")
public class MudanzaController {
    private final MudanzaService mudanzaService;
    private final MudanzaFactory mudanzaFactory;

    public MudanzaController(MudanzaService mudanzaService, MudanzaFactory mudanzaFactory) {
        this.mudanzaService = mudanzaService;
        this.mudanzaFactory = mudanzaFactory;
    }

    @PostMapping("{dni}")
    public List<String> manejarArchivo(@PathVariable("dni") String dni, @RequestParam("file") MultipartFile file) throws IOException {
        Mudanza mudanza = mudanzaFactory.crearMudanza(dni, file);
        List<String> viajes = mudanzaService.realizarMudanza(mudanza);
        return viajes;
    }
}
