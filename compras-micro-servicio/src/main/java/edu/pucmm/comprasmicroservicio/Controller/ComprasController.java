package edu.pucmm.comprasmicroservicio.Controller;

import edu.pucmm.comprasmicroservicio.DTO.PurchaseDTO;
import edu.pucmm.comprasmicroservicio.DTO.TodayDTO;
import edu.pucmm.comprasmicroservicio.Service.ComprasService;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/compras")
public class ComprasController {

    @Autowired
    private ComprasService comprasService;

    @GetMapping
    public List<Compras> getAll() {
        return this.comprasService.getAll();
    }

    @GetMapping("/today")
    public TodayDTO getToday() {
        return this.comprasService.getToday();
    }

    @GetMapping("/solicitudes")
    public List<PurchaseDTO> getSolicitudes() {
        return this.comprasService.Solicitudes();
    }

    @GetMapping("/purchases")
    public List<PurchaseDTO>getPurchases() {
        return this.comprasService.getPurchase();
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
