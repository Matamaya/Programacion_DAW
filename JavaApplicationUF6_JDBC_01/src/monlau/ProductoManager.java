
package monlau;

import monlau.dao.ProductoDAO;
import monlau.dao.ProductoDAOImpl;
import monlau.model.Producto;

public class ProductoManager {
    public static void main(String[] args) {
        ProductoDAO producto =new ProductoDAOImpl();
        
        //agregar nuevo producto

        //producto.insert(new Producto(1,"Pollo",10.00));    //Producto 1
        //producto.insert(new Producto(2,"Carne",30.00));    //Producto 2


        //obtener el producto con el ID = 2
        Producto p = producto.read(2);
        //System.out.println(p);


        //eliminar el Producto 2
        producto.delete(p);  // pasa el objeto Producto como parámetro

        // actualizar el producto 1 (ejemplo: cambiar nombre y precio)
        //Producto p2 = new Producto(1, "Pollo Asado", 12.50);
        //producto.update(p2);
    }
}
