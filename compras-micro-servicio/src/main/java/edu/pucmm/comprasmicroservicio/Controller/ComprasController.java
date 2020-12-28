package edu.pucmm.comprasmicroservicio.Controller;

import edu.pucmm.comprasmicroservicio.Service.ComprasService;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    @GetMapping
    public List<Compras> getAll() {
        return this.comprasService.getAll();
    }

    @PostMapping
    public Compras create(@RequestBody Compras compras) {
        return this.comprasService.saveCompra(compras);
    }

    @GetMapping("/{name}")
    public List<Compras> getByUser(@PathVariable("name") String usuario) {
        return this.comprasService.getByUser(usuario);
    }
}
