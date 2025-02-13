package persistencias;
// Generated 5 feb 2025, 13:07:02 by Hibernate Tools 5.4.33.Final



/**
 * Jugador generated by hbm2java
 */
public class Jugador implements java.io.Serializable {

	private Integer idJugador;
	private Equipo equipo;
	
	private String equipo_1;
	private String nombre;
	private String posicion;
	private int fuerzaAtaque;
	private int fuerzaTecnica;
	private int fuerzaDefensa;
	private int fuerzaPortero;

	public Jugador() {
	}

	public Jugador(String equipo_1, String nombre, String posicion, int fuerzaAtaque, int fuerzaTecnica,
			int fuerzaDefensa, int fuerzaPortero) {
		this.equipo_1 = equipo_1;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fuerzaAtaque = fuerzaAtaque;
		this.fuerzaTecnica = fuerzaTecnica;
		this.fuerzaDefensa = fuerzaDefensa;
		this.fuerzaPortero = fuerzaPortero;
	}

	public Jugador(Equipo equipo, String equipo_1, String nombre, String posicion, int fuerzaAtaque, int fuerzaTecnica,
			int fuerzaDefensa, int fuerzaPortero) {
		this.equipo = equipo;
		this.equipo_1 = equipo_1;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fuerzaAtaque = fuerzaAtaque;
		this.fuerzaTecnica = fuerzaTecnica;
		this.fuerzaDefensa = fuerzaDefensa;
		this.fuerzaPortero = fuerzaPortero;
	}

	public Integer getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(Integer idJugador) {
		this.idJugador = idJugador;
	}

	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public String getEquipo_1() {
		return this.equipo_1;
	}

	public void setEquipo_1(String equipo_1) {
		this.equipo_1 = equipo_1;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}

	public int getFuerzaAtaque() {
		return this.fuerzaAtaque;
	}

	public void setFuerzaAtaque(int fuerzaAtaque) {
		this.fuerzaAtaque = fuerzaAtaque;
	}

	public int getFuerzaTecnica() {
		return this.fuerzaTecnica;
	}

	public void setFuerzaTecnica(int fuerzaTecnica) {
		this.fuerzaTecnica = fuerzaTecnica;
	}

	public int getFuerzaDefensa() {
		return this.fuerzaDefensa;
	}

	public void setFuerzaDefensa(int fuerzaDefensa) {
		this.fuerzaDefensa = fuerzaDefensa;
	}

	public int getFuerzaPortero() {
		return this.fuerzaPortero;
	}

	public void setFuerzaPortero(int fuerzaPortero) {
		this.fuerzaPortero = fuerzaPortero;
	}
	
	
}
