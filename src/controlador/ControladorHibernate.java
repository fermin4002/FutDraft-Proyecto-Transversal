package controlador;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import modelo.JugadorCsv;
import persistencias.Equipo;
import persistencias.Jugador;
import persistencias.Partido;

public class ControladorHibernate {
	
	private   SessionFactory  sessionFactory;
	
	
	public ControladorHibernate() throws Exception{
		try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
			
		}
	}
	
	
	public void close() {
		if(null!=sessionFactory) {
			sessionFactory.close();
		}
	}
	
	private List<JugadorCsv> abrirCSV(String rutaCSV)throws Exception{
		List<JugadorCsv> jugadores = null;
		try  {
			FileReader reader = new FileReader(rutaCSV);
			
			CsvToBeanBuilder<JugadorCsv> csvBuilder = new CsvToBeanBuilder<JugadorCsv>(reader);
			CsvToBean<JugadorCsv> csv = csvBuilder.withType(JugadorCsv.class).withIgnoreLeadingWhiteSpace(true).build();

			jugadores = csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        }
		
		return jugadores;
	}
	
	private void insertarJugadores(SessionFactory sesion,List<JugadorCsv> jugadores) throws Exception{
		Session session = null;
		
		try {
			session = sesion.getCurrentSession();
			session.beginTransaction();
			int num=0;
			String query="insert into jugador (id_jugador,equipo,nombre,posicion,fuerza_ataque,fuerza_tecnica,fuerza_defensa,fuerza_portero)values"
					+ "(:num,:equipo,:nombre,:posicion,:fA,:fT,:fD,:fP)";
			Query insert=session.createSQLQuery(query);
			Jugador temp=null;
			for(JugadorCsv clave:jugadores) {
				System.out.println("Empieza "+num);
				temp=new Jugador();
				temp.setNombre(clave.getNombre());
				temp.setEquipo_1(clave.getEquipo());
				temp.setFuerzaAtaque(clave.getFuerzaAtaque());
				temp.setFuerzaDefensa(clave.getFuerzaDefensa());
				temp.setFuerzaPortero(clave.getFuerzaPortero());
				temp.setFuerzaTecnica(clave.getFuerzaTecnica());
				temp.setPosicion(clave.getPosicion());
				num++;
				
				
				System.out.println(temp);
				session.saveOrUpdate(temp);
				System.out.println("Fin "+num);
				
			}

			
			session.getTransaction().commit();
		}
		catch(Exception e) {
			e.printStackTrace();
			if (null != session) {
				session.getTransaction().rollback();
			}
			throw e;
		}
		finally {
			if (null != session) {
				session.close();
			}
		}
		
		
	}
	
	public void cargarJugadores() throws Exception{
		try {		
			insertarJugadores(sessionFactory, abrirCSV("ficheros/jugadores_ligaFantasy.csv"));
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}
	}

	
	public List<Jugador> extraerJugadores(){
		List<Jugador> salida=new ArrayList<Jugador>();
		Session sesion=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			Query query=sesion.createQuery("from Jugador");
			
			salida=query.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
			
		}
		return salida;
	}
	
	public void resetearJugadores() {
		Session sesion=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			Query query=sesion.createQuery("update from jugador "
										+ "set equipo=null ");
			
			query.executeUpdate();
			System.out.println("fgd");
			
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
			
		}
	}
	public List<Jugador> extraerJugadoresPorPosicion(String posicion) {
		List<Jugador> jugadores=null;
		Session sesion=null;
	    try {
	    	sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
		
			Query q=sesion.createQuery("FROM Jugador WHERE posicion = :posi AND equipo=null");
			q.setParameter("posi", posicion);
			
			jugadores=q.getResultList();
			
			sesion.close();
			
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return jugadores;
	}
	
	public List<String> extraerNombreEquipos(){
		List<String> salida=new ArrayList<String>();
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			
			String rr="select distinct equipo from jugador";
			
			Query query= sesion.createSQLQuery(rr);
			
			salida=query.list();

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		
		return salida;
	}
	
	public List<Jugador> extraerJugadoresFiltro(int minA,int maxA,int minT,int maxT,int minD,int maxD,int minP,int maxP){
		List<Jugador> salida=null;
		
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Jugador j where (j.fuerzaAtaque>= :minA and j.fuerzaAtaque<=:maxA)"
					+ "and (j.fuerzaTecnica>= :minT and j.fuerzaTecnica<=:maxT) "
					+ "and (j.fuerzaDefensa>= :minD and j.fuerzaDefensa<=:maxD) "
					+ "and (j.fuerzaPortero>= :minP and j.fuerzaPortero<=:maxP) ";
			
			Query query= sesion.createQuery(consulta);
			query.setParameter("minA", minA);
			query.setParameter("minT", minT);
			query.setParameter("minD", minD);
			query.setParameter("minP", minP);
			
			query.setParameter("maxA", maxA);
			query.setParameter("maxT", maxT);
			query.setParameter("maxD", maxD);
			query.setParameter("maxP", maxP);

			salida=query.list();

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	public Equipo crearEquipo(String nombre) {
		Session sesion=null;
		Equipo salida=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			salida=new Equipo();
			salida.setNombre(nombre);
			
			sesion.save(salida);
			
			sesion.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public void crearEquipo(Equipo equipo) {
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			sesion.save(equipo);
			
			sesion.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		
	}
	
	public void asignarEquipoUpdate(List<Jugador> plantilla) {
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			for(Jugador clave:plantilla) {
				sesion.update(clave);
			}
			
			
			sesion.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
	}
	
	public boolean isJugadoresCargados() {
		boolean salida=false;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Jugador j where j.idJugador=1";
			
			Query query=sesion.createQuery(consulta);
			Jugador temp=null;
			temp=(Jugador) query.uniqueResult();
			if(temp!=null) {
				salida=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public boolean isEquiposCreados(int cantidad) {
		boolean salida=false;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Equipo";
			
			Query query=sesion.createQuery(consulta);
			List<Equipo> temp=null;
			temp=query.list();
			if(temp.size()>=cantidad) {
				salida=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public boolean isEquiposCreadosMenor(int cantidad) {
		boolean salida=false;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Equipo";
			
			Query query=sesion.createQuery(consulta);
			List<Equipo> temp=null;
			temp=query.list();
			if(temp.size()<cantidad) {
				salida=true;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public List<Equipo> extraerEquipos() {
		List<Equipo> salida=null;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Equipo";
			
			Query query=sesion.createQuery(consulta);
			salida=query.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public List<Partido> extraerJornada(int jornada) {
		List<Partido> salida=null;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Partido e where e.jornada=:jornada";
			
			Query query=sesion.createQuery(consulta);
			query.setParameter("jornada",jornada);
			salida=query.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	
	public void guardarCalendario(List<Partido> partidos) {
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			for(Partido clave:partidos) {
				sesion.save(clave);
			}
			
			
			sesion.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
	}
	
	public List<Equipo> extraerEquiposOrdenados() {
		List<Equipo> salida=null;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Equipo e order by e.puntos desc";
			
			Query query=sesion.createQuery(consulta);
			salida=query.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	//btnSimularPartida.setEnabled(false);
	public List<Partido> extraerJornadasNoJugadas(){
		List<Partido> salida=null;
		Session sesion=null;
		
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			String consulta="from Partido e where e.golesLocal=null and (e.equipoByIdEquipoLocal.equipoJugador=true or e.equipoByIdEquipoVisitante.equipoJugador=true)"
					+ " order by e.jornada";
			
			Query query=sesion.createQuery(consulta);
			
			salida=query.list();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		return salida;
	}
	public Equipo extraEquipoJugador() {
		Equipo equipo=null;
		Session sesion=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			Query q=sesion.createQuery("FROM Equipo WHERE equipoJugador=true");
			equipo=(Equipo) q.uniqueResult();
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(sesion!=null) {
				sesion.close();
			}
		}
		return equipo;
		
	}
	public void añadirequipo(Jugador jugador,Equipo equipo) {

		Session sesion=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			
			jugador.setEquipo(equipo);
			sesion.update(jugador);
			
			sesion.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
			sesion.getTransaction().getRollbackOnly();
		}finally {
			if(sesion==null) {
				sesion.close();
			}
		}
	}	
}
