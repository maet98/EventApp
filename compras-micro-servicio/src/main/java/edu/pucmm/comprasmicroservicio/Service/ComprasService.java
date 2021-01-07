package edu.pucmm.comprasmicroservicio.Service;

import edu.pucmm.comprasmicroservicio.DTO.PurchaseDTO;
import edu.pucmm.comprasmicroservicio.DTO.TodayDTO;
import edu.pucmm.comprasmicroservicio.Repository.ComprasRepository;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

@Service
@RefreshScope
public class ComprasService {

    @Autowired
    private ComprasRepository comprasRepository;

    @Autowired
    private RestTemplate restTemplate;

    public Compras saveCompra(Compras compras) {
        Compras compras1 = this.comprasRepository.save(compras);
        restTemplate.postForObject("http://NOTIFICACION-SERVICIO/createInvoice", compras1, Compras.class);
        return compras1;
    }

    public TodayDTO getToday() {
        TodayDTO ans = new TodayDTO();
        ans.setToday(LocalDate.now());
        ans.setCount(this.comprasRepository.countAllByFechaCompra(LocalDate.now()));
        return ans;
    }

    public List<PurchaseDTO> Solicitudes() {
        return this.comprasRepository.getSolicitudes(LocalDate.now());
    }

    public List<PurchaseDTO> getPurchase() {
        return this.comprasRepository.getGroupByDate();
    }

    public List<Compras> getByUser(String usuario) {
        return this.comprasRepository.findByUsuario(usuario);
    }

    public List<Compras> getAll() {
        return this.comprasRepository.findAll();
    }
}
