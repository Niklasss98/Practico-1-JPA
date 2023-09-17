package com.utn.practico1;

import com.utn.practico1.entidades.*;
import com.utn.practico1.repositorios.*;
import com.utn.practico1.tiposDatos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Practico1Application {

	@Autowired
	RubroRepository rubroRepository;

	@Autowired
	ClienteRepository clienteRepository;

	public static void main(String[] args) {

		SpringApplication.run(Practico1Application.class, args);
		System.out.println("---------------Programa Corriendo------------------");
	}

	@Bean
	CommandLineRunner init(RubroRepository rubroRepository1, ClienteRepository clienteRepository1) {

		return args -> {
			System.out.println("-----------------Programa en Funcionamiento----------------------");
			//Creacion De Rubros
			Rubro rubro1 = Rubro.builder()
					.denominacion("Pasteleria")
					.build();
			Rubro rubro2 = Rubro.builder()
					.denominacion("Reposteria").
					build();
			Rubro rubro3 = Rubro.builder()
					.denominacion("Cafeteria")
					.build();

			// Creacion de Productos
			Producto producto1 = Producto.builder()
					.denominacion("Chocotorta")
					.tiempoEstimadoCocina(40)
					.precioVenta(3000)
					.precioCompra(2500)
					.tipo(tipo.manufacturado)
					.stockActual(5)
					.stockMinimo(2)
					.Receta("Receta Chocotorta")
					.build();

			Producto producto2 = Producto.builder()
					.denominacion("Budin de Limon")
					.tiempoEstimadoCocina(45)
					.precioVenta(750)
					.precioCompra(350)
					.tipo(tipo.manufacturado)
					.stockActual(8)
					.stockMinimo(2)
					.Receta("Receta Budin de Limon")
					.build();

			Producto producto3 = Producto.builder()
					.denominacion("Alfajor de Maicena")
					.tiempoEstimadoCocina(70)
					.precioVenta(250)
					.precioCompra(150)
					.tipo(tipo.manufacturado)
					.stockActual(50)
					.stockMinimo(10)
					.Receta("Receta Alfajores de Maicena")
					.build();

			//Asociar Productos a los Rubros
			rubro1.agregarProducto(producto1);
			rubro2.agregarProducto(producto2);
			rubro2.agregarProducto(producto3);

			//Guardar Rubros
			rubroRepository.save(rubro1);
			rubroRepository.save(rubro2);
			rubroRepository.save(rubro3);

			//Creacion de Clientes
			Cliente cliente1 = Cliente.builder()
					.nombre("Marisa")
					.apellido("Gonzalez")
					.email("@mail")
					.telefono("123456789")
					.build();
			Cliente cliente2 = Cliente.builder()
					.nombre("Camila")
					.apellido("Lucca")
					.email("@mail")
					.telefono("123456789")
					.build();

			//Creacion de Domicilios
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Ohiggins")
					.numero("700")
					.localidad("Villa Hipodromo")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Lerma")
					.numero("800")
					.localidad("Villa Crespo")
					.build();

			//Asociar Domicilios a Clientes
			cliente1.agregarDomicilio(domicilio1);
			cliente2.agregarDomicilio(domicilio2);

			//Fechas a String para guardarlas en los pedidos
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fecha1String = "2023-09-16";
			String fecha2String = "2023-08-24";

			Date fecha1 = formatoFecha.parse(fecha1String);
			Date fecha2 = formatoFecha.parse(fecha2String);

			//Creacion de Pedidos
			Pedido pedido1 = Pedido.builder()
					.fecha(fecha1)
					.total(4500)
					.tipoEnvio(tipoEnvio.delivery)
					.estado(estado.entregado)
					.build();
			Pedido pedido2 = Pedido.builder()
					.fecha(fecha2)
					.total(1500)
					.estado(estado.entregado)
					.tipoEnvio(tipoEnvio.retiro)
					.build();

			//Creacion de Facturas
			Factura factura1 = Factura.builder()
					.fecha(fecha1)
					.total(4100)
					.numero(1)
					.descuento(400)
					.formaPago(formaPago.efectivo)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha2)
					.total(1200)
					.numero(2)
					.descuento(300)
					.formaPago(formaPago.efectivo)
					.build();

			//Creacion de Detalles
			DetallePedido detalle1 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(3000)
					.build();
			DetallePedido detalle2 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(1500)
					.build();
			DetallePedido detalle3 = DetallePedido.builder()
					.cantidad(6)
					.subtotal(1500)
					.build();

			//Asociar Producto a Detalle de Pedido
			detalle1.setProducto(producto1);
			detalle2.setProducto(producto2);
			detalle3.setProducto(producto3);

			//Asociar Detalles al Pedido
			pedido1.agregarDetalle(detalle1);
			pedido1.agregarDetalle(detalle2);
			pedido2.agregarDetalle(detalle3);

			//Asociar Pedido a Factura
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);

			//Asociar Pedido a Cliente
			cliente1.agregarPedido(pedido1);
			cliente2.agregarPedido(pedido2);

			//Guardar CLientes
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);

			//Recuperar Rubros desde la Base de Datos
			Rubro rubro1Recuperado = rubroRepository.findById(rubro1.getId()).orElse(null);
			if (rubro1Recuperado != null) {
				System.out.println("Denominacion: " + rubro1Recuperado.getDenominacion());
				rubro1Recuperado.mostrarProductos();
			}

			Rubro rubro2Recuperado = rubroRepository.findById(rubro2.getId()).orElse(null);
			if (rubro2Recuperado != null) {
				System.out.println("Denominacion: " + rubro2Recuperado.getDenominacion());
				rubro2Recuperado.mostrarProductos();
			}

			//Recuperar Clientes desde la Base de Datos
			Cliente cliente1Recuperado = clienteRepository.findById(cliente1.getId()).orElse(null);
			if (cliente1Recuperado != null) {
				System.out.println("Nombre: " + cliente1Recuperado.getNombre());
				System.out.println("Apellido: " + cliente1Recuperado.getApellido());
				System.out.println("Mail: " + cliente1Recuperado.getEmail());
				System.out.println("Telefono: " + cliente1Recuperado.getTelefono());
				System.out.println("-----------------------------------------------------");
				cliente1Recuperado.mostrarPedidos();
				cliente1Recuperado.mostrarDomicilios();
			}

			Cliente cliente2Recuperado = clienteRepository.findById(cliente2.getId()).orElse(null);
			if (cliente2Recuperado != null) {
				System.out.println("Nombre: " + cliente2Recuperado.getNombre());
				System.out.println("Apellido: " + cliente2Recuperado.getApellido());
				System.out.println("Mail: " + cliente2Recuperado.getEmail());
				System.out.println("Telefono: " + cliente2Recuperado.getTelefono());
				System.out.println("-----------------------------------------------------");
				cliente2Recuperado.mostrarPedidos();
				cliente2Recuperado.mostrarDomicilios();

			}
		};
	}
}