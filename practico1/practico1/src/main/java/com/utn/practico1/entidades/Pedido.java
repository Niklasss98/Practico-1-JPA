package com.utn.practico1.entidades;

import com.utn.practico1.tiposDatos.estado;
import com.utn.practico1.tiposDatos.tipoEnvio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Pedido extends BaseEntidad {

    private estado estado;
    private Date fecha;
    private tipoEnvio tipoEnvio;
    private double total;

    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true, fetch = FetchType.EAGER) //CascadeType fue cambiado a MERGE para unir la entidad a la tabla correspondiente, ya que esta entidad se encuentra en un estado de Hibernate
    @JoinColumn(name = "pedido_id")
    @Builder.Default
    private List<DetallePedido> detallePedidos = new ArrayList<>();

    public void agregarDetalle (DetallePedido detallePedido) {
        detallePedidos.add(detallePedido);
    }

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private Factura factura;


}
