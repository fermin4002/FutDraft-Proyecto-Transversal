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
	
	/*public static void main(String[]args) {
		try {
			ControladorHibernate pru=new ControladorHibernate();
			try {
				/*List<Jugador> salida=pru.extraerJugadoresIdEquipo(sessionFactory, 1);
				for(Jugador clave: salida) {
					System.out.println(clave);
				}
				pru.cargarJugadores();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	}*/
	
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
				/*String nombre=clave.getNombre();
				String equipo=clave.getEquipo();
				String posicion=clave.getPosicion();
				
				int fA=clave.getFuerzaAtaque();
				int fT=clave.getFuerzaTecnica();
				int fD=clave.getFuerzaDefensa();
				int fP=clave.getFuerzaPortero();
				
				insert.setParameter("num", num);
				insert.setParameter("nombre", nombre);
				insert.setParameter("equipo", equipo);
				insert.setParameter("posicion", posicion);
				
				insert.setParameter("fA", fA);
				insert.setParameter("fT", fT);
				insert.setParameter("fD", fD);
				insert.setParameter("fP", fP);*/
				
				System.out.println(temp);
				session.saveOrUpdate(temp);
				//insert.executeUpdate();
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
	
	public List<Jugador> extraerJugadoresPosicion(SessionFactory session,String pos)throws Exception{
		List<Jugador> jugadoresSalida=new ArrayList<Jugador>();
		Session sesion=null;
		
		try {
			sesion=session.getCurrentSession();
			sesion.beginTransaction();
			
			
			String rr="from Jugador where posicion =:pos and equipo=null";
			
			Query query= sesion.createQuery(rr);
			query.setParameter("pos", pos);
			
			jugadoresSalida=query.list();

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		
		return jugadoresSalida;
	}
	
	public List<Jugador> extraerJugadoresIdEquipo(SessionFactory session,int idEquipo){
		List<Jugador> jugadoresSalida=new ArrayList<Jugador>();
		Session sesion=null;
		
		try {
			sesion=session.getCurrentSession();
			sesion.beginTransaction();
			
			
			String rr="select * from Jugador where  id_equipo=:idEquipo";
			
			Query query= sesion.createSQLQuery(rr);
			query.setParameter("idEquipo", idEquipo);
			
			jugadoresSalida=query.list();

		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}finally {
			if(null!=sesion) {
				sesion.close();
			}
		}
		
		return jugadoresSalida;
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
		
			Query q=sesion.createQuery("FROM Jugador WHERE posicion = :posi");
			q.setParameter("posi", posicion);
			
			jugadores=q.getResultList();
			
			sesion.close();
			
	    }catch(Exception e) {
	    	e.printStackTrace();
	    }
	    return jugadores;
	}
	
	public List<String> extraerEquipos(){
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
	public Equipo crearEquipo() {
		Session sesion=null;
		Equipo salida=null;
		try {
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			salida=new Equipo();
			salida.setNombre("patatas");
			
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
}
