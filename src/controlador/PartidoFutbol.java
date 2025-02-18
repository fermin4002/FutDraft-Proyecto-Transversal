package controlador;

import javax.swing.*;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import persistencias.*;
import vista.Vista;

import java.util.ArrayList;
import java.util.List;


class PartidoFutbol extends Thread {
    private String equipoLocal;
    private String equipoVisitante;
    
    private boolean mostrar;
  
    private Vista vista;
    
    private Partido partido;

    private DefaultListModel<String> listModel; 
    private String eventoTexto = "";
    private ArrayList<Integer> jugadas=new ArrayList<Integer>();
    
    
    public PartidoFutbol(Partido partido,Vista vista) {
        this.partido=partido;
    	this.jugadas=new ArrayList<Integer>();
        this.equipoLocal = partido.getEquipoByIdEquipoLocal().getNombre();
        this.equipoVisitante = partido.getEquipoByIdEquipoVisitante().getNombre();
        partido.setGolesLocal(0);
    	partido.setGolesVisitante(0);
        this.listModel=new DefaultListModel<String>();
    	
        this.mostrar=true;
       
        this.vista=vista;
        
        
        
        int contador=0;      
        int num;
        while(contador<12) {
        	num=(int)(1+Math.random()*90);
        	if(!jugadas.contains(num)) {
        		jugadas.add(num);
        		contador++;
        	}
        }
  
    }
    
    public PartidoFutbol(Partido partido) {
    	this.partido=partido;
    	this.mostrar=false;
    	
    	partido.setGolesLocal(0);
    	partido.setGolesVisitante(0);
    }

    /**
     *
     */
    @Override
    public void run() {
    	boolean gol=false;
    	if(mostrar) {
	    	listModel.addElement("Comienza el partido y");
	    	listModel.addElement("arranca a rodar el esferico");
	    	listModel.addElement("en este partido "+equipoLocal+"-"+equipoVisitante);
	    	listModel.addElement("----------------");
	    	vista.listSimulacion.setModel(listModel);
        	
    	}
    	boolean jugada=false;
        for (int minuto = 1; minuto <= 90; minuto++) {
        	if(mostrar) {
        		actualizarInterfaz(minuto);
        	}
            
            try {
            	if(jugadas.contains(minuto)) {
            		
            		if(!jugada) {
            			jugada=true;
            			gol=calcularAtaqueDefensa(partido.getEquipoByIdEquipoLocal(),partido.getEquipoByIdEquipoVisitante(),minuto);
            			if(gol) {
            				partido.setGolesLocal(partido.getGolesLocal()+1);
            			}
            		}else{
            			jugada=false;
            			gol=calcularAtaqueDefensa(partido.getEquipoByIdEquipoVisitante(),partido.getEquipoByIdEquipoLocal(),minuto);
            			if(gol) {
            				partido.setGolesVisitante(partido.getGolesVisitante()+1);
            			}
            		}
            	}
            	
                if(minuto == 45 && mostrar) {
                	listModel.addElement("DESCANSO");
                	listModel.addElement("----------------");
                	vista.listSimulacion.setModel(listModel);
                	Thread.sleep(5000);              
                }else if(minuto==90 && mostrar) {
                	 listModel.addElement("FINAL DEL PARTIDO");
                	 vista.listSimulacion.setModel(listModel);
                }else if(mostrar){
                    Thread.sleep(1000);
                }
                
            }catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        calcularPuntos();
        calculo();
        guardarPartido();
        if(mostrar) {
        	vista.lblVolverPlantilla_Simulacion.setVisible(true);
            
        }
        
    }

    
    public void simularEventosNoGol(int minuto,String nombre) {
    	int numeroprobrabilidad = (int) (1+Math.random() * 4);
        
        	switch (numeroprobrabilidad) {
            case 1:
                eventoTexto ="Min:"+minuto+" Paradon del portero";
                break;
            case 2:
            	
                eventoTexto = "Min:"+minuto+" La ocasión ha sido despejada por la defensa";
                break;
            case 3:
                eventoTexto ="Min:"+minuto+ nombre+" se encontraba en fuera de juego";
                break;
            case 4:
                eventoTexto ="Min:"+minuto+ " El VAR ha anulado el gol.";
                break;
           
        	}
      
        listModel.addElement(eventoTexto); 
        listModel.addElement("----------------");
        vista.listSimulacion.setModel(listModel); 
    }
    
    public void simularEventosGol(int minuto,String nombre) {
    	int numeroprobrabilidad = (int) (1+Math.random() * 4);
        
        	switch (numeroprobrabilidad) {
            case 1:
                eventoTexto = "Min:"+minuto+ "Golazo por la escuadra derecha de "+nombre;
                break;
            case 2:
            	
                eventoTexto = "Min:"+minuto+ " Abate al portero con un gran toque de calidad.";
                break;
            case 3:
                eventoTexto ="Min:"+minuto+  " Que barbaridad "+nombre +", que manera de hacerlo ver facil";
                break;
            case 4:
                eventoTexto ="Min:"+minuto+  "Penalti convertido por "+nombre;
                break;
           
        	}
      
        listModel.addElement(eventoTexto);
        listModel.addElement("----------------");
        vista.listSimulacion.setModel(listModel); 
    }
    
    public boolean calcularAtaqueDefensa(Equipo ataca,Equipo defiende,int minuto){
    	int ataque=0;
    	int defensa=0;
    	boolean gol=false;
    	List<Jugador>atacantes=new ArrayList<Jugador>(ataca.getJugadors());
    	Jugador atacantePrincipal=null;
    	
    	while(null==atacantePrincipal) {
    		if(atacantePrincipal==null) {
    			atacantePrincipal=atacantes.get((int)(Math.random()*11));
    		}		    		
			if(atacantePrincipal.getPosicion().equals("POR")) {
				atacantePrincipal=null;
			}
    	}
    	
    	//ataque
    	ataque=atacantePrincipal.getFuerzaAtaque()*2;
    	for(Jugador clave: atacantes) {
    		if(clave.getPosicion().equals("MED")) {
    			ataque=ataque+clave.getFuerzaTecnica();
    		}
    	}
    	for(Jugador clave:defiende.getJugadors()) {
    		if(clave.getPosicion().equals("POR")) {
    			defensa=defensa+clave.getFuerzaPortero()*3;
    		}else if(clave.getPosicion().equals("DEF")) {
    			defensa=defensa+clave.getFuerzaDefensa();
    		}
    	}
    	ataque=(int)(Math.random()*ataque+1);
    	defensa=(int)(Math.random()*defensa+1);
    	if(ataque>defensa) {
    		gol=true;
    		if(mostrar) {
    			simularEventosGol(minuto, atacantePrincipal.getNombre());
    		}
    	}else {
    		if(mostrar) {
    			simularEventosNoGol(minuto,atacantePrincipal.getNombre());
    		}
    	}
    	return gol;
    }

    public void actualizarInterfaz(int minuto) {
        vista.lblMarcadorMinutos.setText("Minuto: " + minuto);
        vista.lblMarcador.setText(equipoLocal + " " + partido.getGolesLocal() + " - " + partido.getGolesVisitante() + " " + equipoVisitante);
    }
    
    public void calcularPuntos() {
    	if(partido.getGolesVisitante()==partido.getGolesLocal()) {
    		
    		partido.getEquipoByIdEquipoLocal().setGolesFavor(partido.getEquipoByIdEquipoLocal().getGolesFavor()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoLocal().setGolesContra(partido.getEquipoByIdEquipoLocal().getGolesContra()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoLocal().setEmpates(partido.getEquipoByIdEquipoLocal().getEmpates()+1);
    		
    		partido.getEquipoByIdEquipoVisitante().setGolesFavor(partido.getEquipoByIdEquipoVisitante().getGolesFavor()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoVisitante().setGolesContra(partido.getEquipoByIdEquipoVisitante().getGolesContra()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoVisitante().setEmpates(partido.getEquipoByIdEquipoVisitante().getEmpates()+1);
    		
    		
    		
    	}else if(partido.getGolesVisitante()>partido.getGolesLocal()) {
    		partido.getEquipoByIdEquipoLocal().setGolesFavor(partido.getEquipoByIdEquipoLocal().getGolesFavor()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoLocal().setGolesContra(partido.getEquipoByIdEquipoLocal().getGolesContra()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoLocal().setDerrotas(partido.getEquipoByIdEquipoLocal().getDerrotas()+1);
    		
    		partido.getEquipoByIdEquipoVisitante().setGolesFavor(partido.getEquipoByIdEquipoVisitante().getGolesFavor()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoVisitante().setGolesContra(partido.getEquipoByIdEquipoVisitante().getGolesContra()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoVisitante().setVictorias(partido.getEquipoByIdEquipoVisitante().getVictorias()+1);
    		
    	}else {
    		partido.getEquipoByIdEquipoLocal().setGolesFavor(partido.getEquipoByIdEquipoLocal().getGolesFavor()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoLocal().setGolesContra(partido.getEquipoByIdEquipoLocal().getGolesContra()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoLocal().setVictorias(partido.getEquipoByIdEquipoLocal().getVictorias()+1);
    		
    		partido.getEquipoByIdEquipoVisitante().setGolesFavor(partido.getEquipoByIdEquipoVisitante().getGolesFavor()+partido.getGolesVisitante());
    		partido.getEquipoByIdEquipoVisitante().setGolesContra(partido.getEquipoByIdEquipoVisitante().getGolesContra()+partido.getGolesLocal());
    		partido.getEquipoByIdEquipoVisitante().setDerrotas(partido.getEquipoByIdEquipoVisitante().getDerrotas()+1);
    		
    	}
    }
    
    public void calculo() {
    	partido.getEquipoByIdEquipoLocal().calcularPuntos();
    	partido.getEquipoByIdEquipoVisitante().calcularPuntos();
    }
    
    public void guardarPartido() {
    	SessionFactory sessionFactory=null;
    	Session sesion=null;
    	try {
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			sessionFactory = configuration.buildSessionFactory();
			sesion=sessionFactory.getCurrentSession();
			sesion.beginTransaction();
			sesion.update(partido);
			sesion.update(partido.getEquipoByIdEquipoLocal());
			sesion.update(partido.getEquipoByIdEquipoVisitante());
			sesion.getTransaction().commit();
			
		}catch(Exception e) {
			e.printStackTrace();
			if(null!=sesion) {
				sesion.getTransaction().rollback();
			}
		}finally {
			if(sesion!=null) {
				sesion.close();
			}
			if(sessionFactory!=null) {
				sessionFactory.close();
			}
		}

    }
}