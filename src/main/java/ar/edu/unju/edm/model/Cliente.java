package ar.edu.unju.edm.model;

import java.time.LocalDate;
//import java.util.Date;
import java.time.Period;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

@Component
public class Cliente {
	private int nroDocumento;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaNacimiento;
	
	
	private String tipoDocumento;
	private int codigoAreaTelefono;
	private int numTelefono;
	private String email;
	private String password;
	private String nombreApellido;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate fechaUltimaCompra;
	
	public LocalDate getFechaUltimaCompra() {
		return fechaUltimaCompra;
	}

	public void setFechaUltimaCompra(LocalDate fechaUltimaCompra) {
		this.fechaUltimaCompra = fechaUltimaCompra;
	}

	public Cliente() {
		// TODO Auto-generated constructor stub
	}

	public int getNroDocumento() {
		return nroDocumento;
	}

	public void setNroDocumento(int nroDocumento) {
		this.nroDocumento = nroDocumento;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	public int getCodigoAreaTelefono() {
		return codigoAreaTelefono;
	}

	public void setCodigoAreaTelefono(int codigoAreaTelefono) {
		this.codigoAreaTelefono = codigoAreaTelefono;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getEdad() {		
		int edad = 0;
		LocalDate hoy = LocalDate.now();
		Period periodo = Period.between(fechaNacimiento, hoy);
		edad = periodo.getYears();		
		return edad;
	}
	
	public String getTiempoDesdeUltimaCompra() {
		LocalDate fechaActual = LocalDate.now();
		Period periodo = Period.between(fechaUltimaCompra, fechaActual);
		return " T Desde Ultima Compra  Año: " + periodo.getYears() + " Mes: " + periodo.getMonths() + " Dia: " + periodo.getDays() ;		
		 	
	}
	public String getTiempoHastaCumple() {
		
		LocalDate fechaActual= LocalDate.now();
        LocalDate fechaC= getFechaNacimiento();
        LocalDate hastaC = fechaC.withYear(fechaActual.getYear());

        if (hastaC.isBefore(fechaActual) || hastaC.isEqual(fechaActual)) {
        	hastaC = hastaC.plusYears(1);
        }

        Period periodo = Period.between(fechaActual, hastaC);
        return "T Hasta Cumpleaños: " + " Mes: " + periodo.getMonths() + " Dia: " + periodo.getDays();
	    }
	
	public String getPassword() {
		return password;
	}

	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombreApellido() {
		return nombreApellido;
	}

	public void setNombreApellido(String nombreApellido) {
		this.nombreApellido = nombreApellido;
	}

}