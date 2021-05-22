package ar.edu.unju.edm.controller;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	private static final Log AYLEN = LogFactory.getLog(ProductoController.class);;
	
	@Autowired 
	IProductoService iProductoService;
	@GetMapping("/producto")
	public String cargarProducto(Model model) {
		model.addAttribute("unProducto", iProductoService.obtenerNuevoProducto());
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}
	@GetMapping("/producto/editar/{codProducto}")
	public String editarProducto(Model model, @PathVariable(name="codProducto") int codigo) throws Exception {
		try {
			Producto productoEncontrado =  iProductoService.encontradoUnProducto(codigo);
			model.addAttribute("unProducto", productoEncontrado);	
			model.addAttribute("editMode", "true");
		}
		catch (Exception e) {
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto",iProductoService.obtenerNuevoProducto());
			model.addAttribute("editMode", "false");
		}	 
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}
	
	@PostMapping("/producto")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
    iProductoService.guardarProducto(nuevoProducto);
    
  //mostrar el listado de producto luego de la carga de un producto
  		System.out.println(iProductoService.obtenerTodosProductos().get(0).getMarca());
  		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
  		AYLEN.error("solo de prueba");
  		return "producto";
 
}

	@PostMapping("/producto/modificar")
	public String modificarProducto(@ModelAttribute("unProducto")Producto productoAModificar, Model model) {
		try {
			iProductoService.modificarProducto(productoAModificar);
			model.addAttribute("unProducto", new Producto());				
			model.addAttribute("editMode", "false");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// pasar las excepciones al html
			model.addAttribute("formUsuarioErrorMessage",e.getMessage());
			model.addAttribute("unProducto", productoAModificar);			
			model.addAttribute("productos",iProductoService.obtenerTodosProductos());
			model.addAttribute("editMode","true");
		}
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return ("producto");
	
	}
	@GetMapping("/ultimo")
	public String cargarUltimoProducto(Model model) {
		model.addAttribute("ultimoProducto", iProductoService.obtenerUltimoProducto());
		return("mostrar-ultimo");
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		//model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("redirect:/producto");
	}

}
