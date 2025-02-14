package modelo;

import com.opencsv.bean.CsvBindByName;



public class JugadorCsv {
	@CsvBindByName(column = "Equipo")
	private String equipo;
	@CsvBindByName(column = "Nombre")
	private String nombre;
	@CsvBindByName(column = "Posicion")
	private String posicion;
	@CsvBindByName(column = "FuerzaAtaque")
	private int fuerzaAtaque;
	@CsvBindByName(column = "FuerzaTecnica")
	private int fuerzaTecnica;
	@CsvBindByName(column = "FuerzaDefensa")
	private int fuerzaDefensa;
	@CsvBindByName(column = "FuerzaPortero")
	private int fuerzaPortero;
	
	public JugadorCsv() {
		
	}
	
	public JugadorCsv(String equipo, String nombre, String posicion, int fuerzaAtaque, int fuerzaTecnica,
			int fuerzaDefensa, int fuerzaPortero) {
		
		this.equipo = equipo;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fuerzaAtaque = fuerzaAtaque;
		this.fuerzaTecnica = fuerzaTecnica;
		this.fuerzaDefensa = fuerzaDefensa;
		this.fuerzaPortero = fuerzaPortero;
	}
	public String getEquipo() {
		return equipo;
	}
	public String getNombre() {
		return nombre;
	}
	public String getPosicion() {
		return posicion;
	}
	public int getFuerzaAtaque() {
		return fuerzaAtaque;
	}
	public int getFuerzaTecnica() {
		return fuerzaTecnica;
	}
	public int getFuerzaDefensa() {
		return fuerzaDefensa;
	}
	public int getFuerzaPortero() {
		return fuerzaPortero;
	}
	
	
	public void setEquipo(String equipo) {
		this.equipo = equipo;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	public void setFuerzaAtaque(int fuerzaAtaque) {
		this.fuerzaAtaque = fuerzaAtaque;
	}
	public void setFuerzaTecnica(int fuerzaTecnica) {
		this.fuerzaTecnica = fuerzaTecnica;
	}
	public void setFuerzaDefensa(int fuerzaDefensa) {
		this.fuerzaDefensa = fuerzaDefensa;
	}
	public void setFuerzaPortero(int fuerzaPortero) {
		this.fuerzaPortero = fuerzaPortero;
	}
	@Override
	public String toString() {
		return "JugadorCsv [equipo=" + equipo + ", nombre=" + nombre + ", posicion=" + posicion + ", fuerzaAtaque="
				+ fuerzaAtaque + ", fuerzaTecnica=" + fuerzaTecnica + ", fuerzaDefensa=" + fuerzaDefensa
				+ ", fuerzaPortero=" + fuerzaPortero + "]";
	}
	
	
	
}
