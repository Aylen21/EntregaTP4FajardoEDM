package ar.edu.unju.edm.util;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;
@Service
public class ProductoServiceImpl implements IProductoService{
	private static final Log LOGGER = LogFactory.getLog(ProductoServiceImpl.class);
	//como se hace la solucion del problema
		//guardar en Array
		//guarde en una BD MySQL
		//guarde en una BD Oracle
	
	@Autowired
	Producto unProducto;
	
	//ArrayList<Producto> listaDeProductos = new ArrayList<Producto>();
	private List<Producto> listaDeProductos = ListadoProductos.productos;
	
	@Override
	public void guardarProducto(Producto unProducto) {
		// TODO Auto-generated method stub
		
		//esta línea la puse solo para que escriba en la consola el nombre del producto que llega
				//una línea que me sirve a mí de control
				//un log artesanal
				System.out.println(unProducto.getNombre());
				listaDeProductos.add(unProducto);
				
				//otra línea de control
				//quiero saber cuántos elementos hay en el arreglo
				System.out.println(listaDeProductos.size());
				
				LOGGER.info("METHOD: ingresando a Guardar Producto");
				LOGGER.info("RESULT: guardado " + listaDeProductos.get(listaDeProductos.size()-1).getNombre());
	}

	@Override
	public void modificarProducto(Producto productoAModificar) {
		// TODO Auto-generated method stub

		for (int i = 0; i <  listaDeProductos.size(); i++){
		    if ( listaDeProductos.get(i).getCodProducto() == productoAModificar.getCodProducto()) {
		    	listaDeProductos.set(i , productoAModificar);
		    }
		}
		
	}

	@Override
	public void eliminarProducto(Producto productoAEliminar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Producto obtenerUnProducto(String nombreProducto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Producto> obtenerTodosProductos() {
		// TODO Auto-generated method stub
		return listaDeProductos;
	}

	@Override
	public Producto obtenerNuevoProducto() {
		// TODO Auto-generated method stub
		return unProducto;
	}

	@Override
	public Producto obtenerUltimoProducto() {
		// TODO Auto-generated method stub
		int i = listaDeProductos.size() - 1;
		return listaDeProductos.get(i);
	}

	@Override
	public Producto encontradoUnProducto(int codigo) {
		// TODO Auto-generated method stub
		for (int i = 0; i < listaDeProductos.size(); i++){
		    if (listaDeProductos.get(i).getCodProducto() == codigo) {
		    	unProducto = listaDeProductos.get(i);
		    }
		}
	
		return unProducto;
	}
	
}
