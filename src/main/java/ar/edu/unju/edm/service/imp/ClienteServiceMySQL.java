package ar.edu.unju.edm.service.imp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import ar.edu.unju.edm.model.Cliente;
import ar.edu.unju.edm.repository.IClienteDAO;
import ar.edu.unju.edm.service.IClienteService;

@Service
@Qualifier("impmysql")
public class ClienteServiceMySQL implements IClienteService{

	@Autowired
	Cliente unCliente;
	@Autowired
	IClienteDAO iClienteDAO;

    @Override
	public void guardarCliente(Cliente clienteGuardado) {
		// TODO Auto-generated method stub
		iClienteDAO.save(clienteGuardado);	
		}

	@Override
	public Cliente crearCliente() {
		// TODO Auto-generated method stub
		return unCliente;
	}

	@Override
	public List<Cliente> obtenerTodosClientes() {
		// TODO Auto-generated method stub
		return (List<Cliente>) iClienteDAO.findAll();
	}
	
	@Override
	public Cliente encontrarUnCliente(int dni) throws Exception {
		// TODO Auto-generated method stub
		return iClienteDAO.findByNroDocumento(dni).orElseThrow(()->new Exception("El cliente NO existe"));
	}

	@Override
	public void modificarCliente(Cliente clienteModificado) throws Exception{
		// TODO Auto-generated method stub
				Cliente clienteAModificar = iClienteDAO.findByNroDocumento(clienteModificado.getNroDocumento()).orElseThrow(()->new Exception("El Cliente no fue encontrado"));
				cambiarCliente(clienteModificado, clienteAModificar);
				iClienteDAO.save(clienteAModificar);
	}

	private void cambiarCliente(Cliente clienteModificado, Cliente clienteAModificar) {
		// TODO Auto-generated method stub
		clienteAModificar.setNombreApellido(clienteModificado.getNombreApellido());
		clienteAModificar.setNroDocumento(clienteModificado.getNroDocumento());
		clienteAModificar.setTipoDocumento(clienteModificado.getTipoDocumento());
		clienteAModificar.setFechaNacimiento(clienteModificado.getFechaNacimiento());
		clienteAModificar.setCodigoAreaTelefono(clienteModificado.getCodigoAreaTelefono());
		clienteAModificar.setNumTelefono(clienteModificado.getNumTelefono());
		clienteAModificar.setEmail(clienteModificado.getEmail());
	}

	@Override
	public void eliminarCliente(int id)throws Exception {
		// TODO Auto-generated method stub
		Cliente ClienteEliminar= iClienteDAO.findByNroDocumento(id).orElseThrow(()->new Exception("El cliente NO encontrado"));
		iClienteDAO.delete(ClienteEliminar);
	}

}
