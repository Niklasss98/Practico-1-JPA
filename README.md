# Practico-2-JPA

Entrega del Trabajo Práctico N1 de Desarrollo de Software

Alumno: Lucca, Nicolás Ignacio / Legajo: 44064 / Curso: 3K09

Ejecutar este proyecto con IntelliJ IDEA y JDK 17.

Si hay problemas con los puertos, cambiar el puerto en application.properties, dentro de la carpeta resources

Si hay problemas del tipo 'org.hibernate.PersistentObjectException: detached entity passed to persist' interpretar que entidad esta desconectada o hibernada. Esto se puede solucionar cambiando el CascadeType de ALL a MERGE
(Como fue resuelto en la clase Pedido ya que una entidad de Producto estaba hibernada)
