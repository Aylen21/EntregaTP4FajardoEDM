package ar.edu.unju.edm.controller;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
