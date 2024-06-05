package br.edu.imepac.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("convenio")
public class ConvenioController {

    @Autowired
    private ConvenioService convenioservice;
}
