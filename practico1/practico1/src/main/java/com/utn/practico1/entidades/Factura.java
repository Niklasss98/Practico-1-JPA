package com.utn.practico1.entidades;

import com.utn.practico1.tiposDatos.formaPago;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Factura extends BaseEntidad {

    private int numero;
    private Date fecha;
    private double descuento;
    private formaPago formaPago;
    private int total;

}
