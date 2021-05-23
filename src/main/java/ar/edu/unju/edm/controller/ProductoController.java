package ar.edu.unju.edm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ar.edu.unju.edm.model.Producto;
import ar.edu.unju.edm.service.IProductoService;

@Controller
public class ProductoController {
	
	@Autowired 
	@Qualifier("unImp")
	IProductoService iProductoService;
	
	//mostrar
	@GetMapping("/producto/mostrar")
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
			model.addAttribute("unProducto",iProductoService.crearProducto());
			model.addAttribute("editMode", "false");
		}	 
		model.addAttribute("productos", iProductoService.obtenerTodosProductos());
		return("producto");
	}
	
	
	//Guardar
	@PostMapping("/producto/guardar")
	public String guardarNuevoProducto(@ModelAttribute("unProducto") Producto nuevoProducto, Model model) {
    iProductoService.guardarProducto(nuevoProducto);
  //mostrar el listado de producto luego de la carga de un producto
  		System.out.println(iProductoService.obtenerTodosProductos().get(0).getMarca());
  		
  		return "redirect:/producto/mostrar";
 
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

	@GetMapping("/producto/eliminarProducto/{id}")
	public String eliminarProducto(Model model, @PathVariable(name="id") int id) {
		try {			
			iProductoService.eliminarProducto(id);			
		}
		catch(Exception e){
			model.addAttribute("listErrorMessage",e.getMessage());
		}			
		return "redirect:/producto/mostrar";
	
	}
	
	@GetMapping("/volver")
	public String cargarNuevoProducto(Model model) {
		//model.addAttribute("unProducto", iProductoService.obtenerProductoNuevo());
		return("redirect:/producto");
	}

}
