package com.utn.practico1.entidades;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Rubro extends BaseEntidad {

    private String denominacion;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinColumn(name = "rubro_id")
    @Builder.Default
    private List<Producto> productos = new ArrayList<>();

    public void agregarProducto (Producto producto) { productos.add(producto); }

    public void mostrarProductos() {

        System.out.println("Los productos de este Rubro son: ");
        for (Producto producto: productos) {
            System.out.println("Denominación: " + producto.getDenominacion() + ", Receta: " + producto.getReceta()
                    + ", Unidad Medida: " + producto.getUnidadMedida() + ", Precio Compra: " + producto.getPrecioCompra()
                    + ", Precio Venta: " + producto.getPrecioVenta() + ", Stock Actual: " + producto.getStockActual()
                    + ", Stock Minimo: " + producto.getStockMinimo() + ", Tiempo estimado de cocina: " +
                    producto.getTiempoEstimadoCocina() + ", Tipo: " + producto.getTipo());
        }
    }

}
