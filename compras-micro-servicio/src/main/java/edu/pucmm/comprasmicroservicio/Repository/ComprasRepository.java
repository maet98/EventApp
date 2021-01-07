package edu.pucmm.comprasmicroservicio.Repository;

import edu.pucmm.comprasmicroservicio.DTO.PurchaseDTO;
import edu.pucmm.comprasmicroservicio.entity.Compras;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@RefreshScope
public interface ComprasRepository extends JpaRepository<Compras, Long> {
    public List<Compras> findByUsuario(String usuario);
    int countAllByFechaCompra(LocalDate fecha);
    @Query("select new edu.pucmm.comprasmicroservicio.DTO.PurchaseDTO( c.fechaCompra, sum(c.total)) from Compras c  group by c.fechaCompra order by c.fechaCompra ASC")
    public List<PurchaseDTO> getGroupByDate();
    @Query("select new edu.pucmm.comprasmicroservicio.DTO.PurchaseDTO( c.fechaEvento, count(c.fechaCompra)) from Compras c where c.fechaEvento >= :fecha group by c.fechaEvento order by c.fechaEvento ASC")
    public List<PurchaseDTO> getSolicitudes(@Param("fecha")LocalDate fecha);
}
