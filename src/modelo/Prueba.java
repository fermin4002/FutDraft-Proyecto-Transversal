package modelo;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import persistencias.Jugador;



public class Prueba {
	
	public static void main(String[]args) throws Exception{
		SessionFactory sessionFactory=null;
		
		List<JugadorCsv> jugadores = null;

		try {
			Prueba prueba=new Prueba();
			jugadores=prueba.abrirCSV("ficheros/jugadores_ligaFantasy.csv");
			System.out.println(jugadores.toString());
			System.out.println("abierto");
			
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
			
			prueba.insertarDatos(sessionFactory, jugadores);
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public List<JugadorCsv> abrirCSV(String rutaCSV){
		List<JugadorCsv> jugadores = null;
		try  {
			FileReader reader = new FileReader(rutaCSV);
			
			CsvToBeanBuilder<JugadorCsv> csvBuilder = new CsvToBeanBuilder<JugadorCsv>(reader);
			CsvToBean<JugadorCsv> csv = csvBuilder.withType(JugadorCsv.class).withIgnoreLeadingWhiteSpace(true).build();

			jugadores = csv.parse();

        } catch (IOException e) {
            e.printStackTrace();
        }
		
		return jugadores;
	}
	
	public void insertarDatos(SessionFactory sesion,List<JugadorCsv> jugadores) {
		Session session = null;
		
		try {
			session = sesion.getCurrentSession();
			session.beginTransaction();
			int num=0;
			Jugador temp=null;
			for(JugadorCsv clave:jugadores) {
				System.out.println("Empieza "+num);
				num++;
				temp=new Jugador();
				//System.out.println(temp);
				temp.setIdJugador(num);
				temp.setEquipo_1(clave.getEquipo());
				temp.setNombre(clave.getNombre());
				temp.setPosicion(clave.getPosicion());
				temp.setFuerzaAtaque(clave.getFuerzaAtaque());
				temp.setFuerzaDefensa(clave.getFuerzaDefensa());
				temp.setFuerzaPortero(clave.getFuerzaPortero());
				temp.setFuerzaTecnica(clave.getFuerzaTecnica());
				
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
	
}
