package persistencias;
// Generated 13 feb 2025, 10:17:18 by Hibernate Tools 5.4.33.Final

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Jugador generated by hbm2java
 */
@Entity
@Table(name="jugador" , catalog="liga_fantasy")
public class Jugador implements java.io.Serializable {

	private int idJugador;
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

	/*public Jugador(int idJugador, String equipo_1, String nombre, String posicion, int fuerzaAtaque, int fuerzaTecnica,
			int fuerzaDefensa, int fuerzaPortero) {
		this.idJugador = idJugador;
		this.equipo_1 = equipo_1;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fuerzaAtaque = fuerzaAtaque;
		this.fuerzaTecnica = fuerzaTecnica;
		this.fuerzaDefensa = fuerzaDefensa;
		this.fuerzaPortero = fuerzaPortero;
	}

	public Jugador(int idJugador, Equipo equipo, String equipo_1, String nombre, String posicion, int fuerzaAtaque,
			int fuerzaTecnica, int fuerzaDefensa, int fuerzaPortero) {
		this.idJugador = idJugador;
		this.equipo = equipo;
		this.equipo_1 = equipo_1;
		this.nombre = nombre;
		this.posicion = posicion;
		this.fuerzaAtaque = fuerzaAtaque;
		this.fuerzaTecnica = fuerzaTecnica;
		this.fuerzaDefensa = fuerzaDefensa;
		this.fuerzaPortero = fuerzaPortero;
	}*/
	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name="id_jugador", unique = true, nullable = false)
	public int getIdJugador() {
		return this.idJugador;
	}

	public void setIdJugador(int idJugador) {
		this.idJugador = idJugador;
	}
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_equipo", nullable = true)
	public Equipo getEquipo() {
		return this.equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	@Column(name="equipo",nullable=false)
	public String getEquipo_1() {
		return this.equipo_1;
	}

	public void setEquipo_1(String equipo_1) {
		this.equipo_1 = equipo_1;
	}
	@Column(name="nombre",nullable=false)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Column(name="posicion",nullable=false)
	public String getPosicion() {
		return this.posicion;
	}

	public void setPosicion(String posicion) {
		this.posicion = posicion;
	}
	@Column(name="fuerza_ataque",nullable=false)
	public int getFuerzaAtaque() {
		return this.fuerzaAtaque;
	}

	public void setFuerzaAtaque(int fuerzaAtaque) {
		this.fuerzaAtaque = fuerzaAtaque;
	}
	@Column(name="fuerza_tecnica",nullable=false)
	public int getFuerzaTecnica() {
		return this.fuerzaTecnica;
	}

	public void setFuerzaTecnica(int fuerzaTecnica) {
		this.fuerzaTecnica = fuerzaTecnica;
	}
	@Column(name="fuerza_defensa",nullable=false)
	public int getFuerzaDefensa() {
		return this.fuerzaDefensa;
	}

	public void setFuerzaDefensa(int fuerzaDefensa) {
		this.fuerzaDefensa = fuerzaDefensa;
	}
	@Column(name="fuerza_portero",nullable=false)
	public int getFuerzaPortero() {
		return this.fuerzaPortero;
	}

	public void setFuerzaPortero(int fuerzaPortero) {
		this.fuerzaPortero = fuerzaPortero;
	}

}
