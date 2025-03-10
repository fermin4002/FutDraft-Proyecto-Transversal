package persistencias;

import javax.persistence.*;
import  static javax.persistence.GenerationType.IDENTITY;


// Generated 13 feb 2025, 10:17:18 by Hibernate Tools 5.4.33.Final

/**
 * Partido generated by hbm2java
 */
@Entity
@Table(name="partido", catalog="liga_fantasy")
public class Partido implements java.io.Serializable {

	private int idPartido;
	private Equipo equipoByIdEquipoVisitante;
	private Equipo equipoByIdEquipoLocal;
	private int jornada;
	private Integer golesLocal;
	private Integer golesVisitante;

	public Partido() {
	}

	public Partido(int idPartido, int jornada) {
		this.idPartido = idPartido;
		this.jornada = jornada;
	}

	public Partido(int idPartido, Equipo equipoByIdEquipoVisitante, Equipo equipoByIdEquipoLocal, int jornada,
			Integer golesLocal, Integer golesVisitante) {
		this.idPartido = idPartido;
		this.equipoByIdEquipoVisitante = equipoByIdEquipoVisitante;
		this.equipoByIdEquipoLocal = equipoByIdEquipoLocal;
		this.jornada = jornada;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}
	
	public Partido( Equipo equipoByIdEquipoLocal,Equipo equipoByIdEquipoVisitante, int jornada) {
		this.equipoByIdEquipoVisitante = equipoByIdEquipoVisitante;
		this.equipoByIdEquipoLocal = equipoByIdEquipoLocal;
		this.jornada = jornada;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	
	@Column(name="id_partido", unique = true, nullable = false)
	public int getIdPartido() {
		return this.idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_equipo_visitante", nullable = true)
	public Equipo getEquipoByIdEquipoVisitante() {
		return this.equipoByIdEquipoVisitante;
	}

	public void setEquipoByIdEquipoVisitante(Equipo equipoByIdEquipoVisitante) {
		this.equipoByIdEquipoVisitante = equipoByIdEquipoVisitante;
	}
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_equipo_local", nullable = true)
	public Equipo getEquipoByIdEquipoLocal() {
		return this.equipoByIdEquipoLocal;
	}

	public void setEquipoByIdEquipoLocal(Equipo equipoByIdEquipoLocal) {
		this.equipoByIdEquipoLocal = equipoByIdEquipoLocal;
	}

	@Column(name="jornada",nullable=false)
	public int getJornada() {
		return this.jornada;
	}

	public void setJornada(int jornada) {
		this.jornada = jornada;
	}
	@Column(name="goles_local",nullable=true)
	public Integer getGolesLocal() {
		return this.golesLocal;
	}

	public void setGolesLocal(Integer golesLocal) {
		this.golesLocal = golesLocal;
	}
	@Column(name="goles_visitante",nullable=true)
	public Integer getGolesVisitante() {
		return this.golesVisitante;
	}

	public void setGolesVisitante(Integer golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

}
