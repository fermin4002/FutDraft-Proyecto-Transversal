package controlador;

import vista.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;



import net.bytebuddy.asm.Advice.This;
import persistencias.Equipo;
import persistencias.Jugador;
import persistencias.Partido;

public class Controlador implements ActionListener,MouseListener {
	int jugadoresEquipoLocalSeleccionados = 0;
    private Vista vista;
    private ControladorHibernate hibernate;
    private String[] nombres;
    List<Partido> jornada;
    List<PartidoFutbol> jugandose;
    private List<Jugador> jugador;
    private DefaultTableModel modeloTJugadores,modeloTCLasidicacion,modeloTJornadas;
    private Jugador porteroSeleccionado;
    private Jugador defensorSeleccionado;
    private Jugador mediocampistaSeleccionado;
    private Jugador delanteroSeleccionado;
    private PartidoFutbol partidoFutbol;
    public Controlador(Vista vista) {
        this.vista = vista;
        jugador=new ArrayList<Jugador>();
        nombres =new String[] {
        	    "Tormenta FC", "Dragones Negros", "Relámpagos Dorados", "Águilas de Acero",
        	    "Titanes del Gol", "Neón United", "Construcciones Villalta", "Ules FC",
        	    "Guerreros del Césped", "Rayo Espectral", "Patatillas FC", "Escorpiones de Fuego",
        	    "Trueno Celeste", "Sombras del Balón", "Leones del Horizonte", "Halcones Plateados",
        	    "Inferno FC", "Vikingos del Área", "Tempestad Azul"
        	};
        //Principio
        this.vista.btnEmpezar.addActionListener(this);
        this.vista.btnEmpezar.setActionCommand("empezar");
        this.vista.btnCrearEquipoPropio.addActionListener(this);
        
        //Porteros
        this.vista.btnPortero.addActionListener(this);
        //Delanteros
        this.vista.btnDelanteroDerecho.addActionListener(this);
        this.vista.btnDelanteroIzquierda.addActionListener(this);
        //CentroCampistas
        this.vista.btnCentroCampistaDerecho.addActionListener(this);
        this.vista.btnCentroCampistaDerechoCentro.addActionListener(this);
        this.vista.btnCentroCampistaIzquierdoCentro.addActionListener(this);
        this.vista.btnCentroCampistaIzquierdo.addActionListener(this);
        //Defensas
        this.vista.btnDefensaDerecha.addActionListener(this);
        this.vista.btnDefensaDerechaCentro.addActionListener(this);
        this.vista.btnDefensaIzquierda.addActionListener(this);
        this.vista.btnDefensaIzquierdaCentro.addActionListener(this);
        //Eleccion
        this.vista.btnEleccionUno.addActionListener(this);
        this.vista.btnEleccionDos.addActionListener(this);
        this.vista.btnEleccionTres.addActionListener(this);
        this.vista.btnEleccionCuatro.addActionListener(this);
        this.vista.btnEleccionCinco.addActionListener(this);
        this.vista.btnJugar.addActionListener(this);

        this.vista.btnClasificaciones.addActionListener(this);


        this.vista.btnSimularPartida.addActionListener(this);
        this.vista.btnJugadores.addActionListener(this);
        this.vista.lblVolverJugadores.addMouseListener(this);
        this.vista.lblVolverPlantilla.addMouseListener(this);

       
        //Clasificacion
        this.vista.lblVolverClasificacion.addMouseListener(this);
        this.vista.btnFIltrar.addActionListener(this);
        this.vista.comboBoxJornada.addActionListener(this);
        this.vista.lblSalirPrincipal.addMouseListener(this);
        this.vista.comboBoxEquipoClasificacion.addActionListener(this);
       //Ver equipos
        this.vista.lblVolverPlantilla_1.addMouseListener(this);

        //Ver simulacion
        this.vista.lblEmpezarSimulacion.addMouseListener(this);
        this.vista.lblVolverPlantilla_Simulacion.addMouseListener(this);
        this.vista.panelVistaEquipo.setVisible(false); 
        this.vista.btnIniciarSimulacion.addActionListener(this);
        //Ver infromacion
        this.vista.lblInformacion.addMouseListener(this);
        //Informacion
        this.vista.lblSalirMenu_Informacion.addMouseListener(this);

        //MOdelos
        modeloTJugadores= new DefaultTableModel();
        modeloTJugadores.addColumn("Nombre");
        modeloTJugadores.addColumn("Equipo");
        modeloTJugadores.addColumn("Posicion");
        modeloTJugadores.addColumn("F. Ataque");
        modeloTJugadores.addColumn("F. Defensa");
        modeloTJugadores.addColumn("F. Tecnica");
        modeloTJugadores.addColumn("F. Portero");
        
        
        modeloTJornadas=new DefaultTableModel();
        modeloTJornadas.addColumn("Local");
        modeloTJornadas.addColumn("");
        modeloTJornadas.addColumn("Visitante");
        
        modeloTCLasidicacion=new DefaultTableModel();
        modeloTCLasidicacion.addColumn("POS");
        modeloTCLasidicacion.addColumn("Nombre");
        modeloTCLasidicacion.addColumn("PTS");
        modeloTCLasidicacion.addColumn("DG");
        modeloTCLasidicacion.addColumn("PJ");
        modeloTCLasidicacion.addColumn("PG");
        modeloTCLasidicacion.addColumn("PE");
        modeloTCLasidicacion.addColumn("PP");
        
       
        
        
        vista.spinnerMinPortero.setModel(new SpinnerNumberModel(1,1,99,1));
        vista.spinnerMinAtaque.setModel(new SpinnerNumberModel(1,1,99,1));
        vista.spinnerMinDefensa.setModel(new SpinnerNumberModel(1,1,99,1));
        vista.spinnerMinTecnica.setModel(new SpinnerNumberModel(1,1,99,1));
        
        vista.spinnerMaxTecnica.setModel(new SpinnerNumberModel(99,1,99,1));
        vista.spinnerMaxDefensa.setModel(new SpinnerNumberModel(99,1,99,1));
        vista.spinnerMaxAtaque.setModel(new SpinnerNumberModel(99,1,99,1));
        vista.spinnerMaxPortero.setModel(new SpinnerNumberModel(99,1,99,1));
        
        vista.comboBoxFiltroPosicion.addItem("Todas");
        vista.comboBoxFiltroPosicion.addItem("POR");
        vista.comboBoxFiltroPosicion.addItem("DEF");
        vista.comboBoxFiltroPosicion.addItem("MED");
        vista.comboBoxFiltroPosicion.addItem("DEL");
        
        vista.comboBoxFiltroEquipo.addItem("Todos");
        
        
        
        vista.tablaJugadores.setModel(modeloTJugadores);
        vista.tablaClasificacion.setModel(modeloTCLasidicacion);
        vista.tablaJornadas.setModel(modeloTJornadas);
        
        try {
			this.hibernate=new ControladorHibernate();
			if(!hibernate.isJugadoresCargados()) {
				hibernate.cargarJugadores();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        
        
        
        cargarEquipos();
        cargarComboJornada();
        cargarTabla(modeloTJugadores);
        imagenes();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton[] botonesDeshabilitar = {
            vista.btnDelanteroDerecho,
            vista.btnDelanteroIzquierda,
            vista.btnCentroCampistaDerecho,
            vista.btnCentroCampistaDerechoCentro,
            vista.btnCentroCampistaIzquierdoCentro,
            vista.btnCentroCampistaIzquierdo,
            vista.btnDefensaDerecha,
            vista.btnDefensaDerechaCentro,
            vista.btnDefensaIzquierda,
            vista.btnDefensaIzquierdaCentro,
            vista.btnPortero
        };
        switch(e.getActionCommand()) {
        
        }
        if (e.getSource() == this.vista.btnEmpezar) {
            this.vista.panelInicio.setVisible(false);
            this.vista.panelMenu.setVisible(true);
            
        }else if (e.getSource() == this.vista.btnPortero) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarPorteros(this.vista.lblNombrePortero, this.vista.btnPortero);
            this.vista.btnPortero.setVisible(false);
        } else if (e.getSource() == this.vista.btnDefensaDerecha) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDefensores(this.vista.lblNombreDefensaIDerecha, this.vista.btnDefensaDerecha);
        } else if (e.getSource() == this.vista.btnDefensaIzquierda) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDefensores(this.vista.lblNombreDefensaIzquierda, this.vista.btnDefensaIzquierda);
        } else if (e.getSource() == this.vista.btnDefensaIzquierdaCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDefensores(this.vista.lblNombreDefensaIzquierdaCentro, this.vista.btnDefensaIzquierdaCentro);
        } else if (e.getSource() == this.vista.btnDefensaDerechaCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDefensores(this.vista.lblNombreDefensaIDerechaCentro, this.vista.btnDefensaDerechaCentro);
        } else if (e.getSource() == this.vista.btnCentroCampistaDerecho) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarMediocampistas(this.vista.lblNombreCentroCampistaDerecha, this.vista.btnCentroCampistaDerecho);
        } else if (e.getSource() == this.vista.btnCentroCampistaIzquierdo) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarMediocampistas(this.vista.lblNombreCentroCampistaIzquierdo, this.vista.btnCentroCampistaIzquierdo);
        } else if (e.getSource() == this.vista.btnCentroCampistaDerechoCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarMediocampistas(this.vista.lblNombreCentroCampistaDerechaCentro, this.vista.btnCentroCampistaDerechoCentro);
        } else if (e.getSource() == this.vista.btnCentroCampistaIzquierdoCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarMediocampistas(this.vista.lblNombreCentroCampistaIzquierdoCentro, this.vista.btnCentroCampistaIzquierdoCentro);
        } else if (e.getSource() == this.vista.btnDelanteroDerecho) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDelanteros(this.vista.lblNombreDelanteroDerecho, this.vista.btnDelanteroDerecho);
        } else if (e.getSource() == this.vista.btnDelanteroIzquierda) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDelanteros(this.vista.lblNombreDelanteroIzquierdo, this.vista.btnDelanteroIzquierda);
        }
        //Copiado
        else if (e.getSource()==this.vista.btnEleccionUno) {
       	 vista.panelElecion.setVisible(false);
            enableButtons(botonesDeshabilitar);
            Equipo equipo=hibernate.extraEquipoJugador();
            Jugador jugador=this.jugador.get(0);
            hibernate.añadirequipo(jugador, equipo);
            cargarJugadoresEquipo();
            verificarJugadoresSeleccionados();
	    }else if (e.getSource()==this.vista.btnEleccionDos) {
	       	 vista.panelElecion.setVisible(false);
	         enableButtons(botonesDeshabilitar);
	         Equipo equipo=hibernate.extraEquipoJugador();
            Jugador jugador=this.jugador.get(1);
            hibernate.añadirequipo(jugador, equipo);
            cargarJugadoresEquipo();
            verificarJugadoresSeleccionados();
	    }else if (e.getSource()==this.vista.btnEleccionTres) {
	    	vista.panelElecion.setVisible(false);
	    	enableButtons(botonesDeshabilitar); 
	    	Equipo equipo=hibernate.extraEquipoJugador();
           Jugador jugador=this.jugador.get(2);
           hibernate.añadirequipo(jugador, equipo);
           cargarJugadoresEquipo();
           verificarJugadoresSeleccionados();
	    }else if (e.getSource()==this.vista.btnEleccionCuatro) {
	    	vista.panelElecion.setVisible(false);
	    	enableButtons(botonesDeshabilitar);  
	    	Equipo equipo=hibernate.extraEquipoJugador();
           Jugador jugador=this.jugador.get(3);
           hibernate.añadirequipo(jugador, equipo);
           cargarJugadoresEquipo();
           verificarJugadoresSeleccionados();
	    }else if (e.getSource()==this.vista.btnEleccionCinco) {
	    	vista.panelElecion.setVisible(false);
	    	enableButtons(botonesDeshabilitar); 
	    	Equipo equipo=hibernate.extraEquipoJugador();
           Jugador jugador=this.jugador.get(4);
           hibernate.añadirequipo(jugador,equipo);
           cargarJugadoresEquipo();
           verificarJugadoresSeleccionados();
        }
        
        else if (isPlayerButton(e.getSource())) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
        } else if (isEleccionButton(e.getSource())) {
            vista.panelElecion.setVisible(false);
            enableButtons(botonesDeshabilitar);
        }

        else if(e.getSource()==this.vista.btnJugar) {
        	List<Partido> partidos=hibernate.extraerJornadasNoJugadas();
        	if(partidos.size()==0&&hibernate.isEquiposCreados(20)) {
        		hibernate.reinicio();
        		this.vista.btnPortero.setVisible(true);
        	    this.vista.btnDelanteroDerecho.setVisible(true);
        	    this.vista.btnDelanteroIzquierda.setVisible(true); 
        	    this.vista.btnCentroCampistaDerecho.setVisible(true); 
        	    this.vista.btnCentroCampistaDerechoCentro.setVisible(true); 
        	    this.vista.btnCentroCampistaIzquierdo.setVisible(true); 
        	    this.vista.btnCentroCampistaIzquierdoCentro.setVisible(true); 
        	    this.vista.btnDefensaDerecha.setVisible(true); 
        	    this.vista.btnDefensaDerechaCentro.setVisible(true); 
        	    this.vista.btnDefensaIzquierda.setVisible(true); 
        	    this.vista.btnDefensaIzquierdaCentro.setVisible(true);
        	    
        	    this.vista.btnPortero.setEnabled(true);
        	    this.vista.btnDelanteroDerecho.setEnabled(true);
        	    this.vista.btnDelanteroIzquierda.setEnabled(true); 
        	    this.vista.btnCentroCampistaDerecho.setEnabled(true); 
        	    this.vista.btnCentroCampistaDerechoCentro.setEnabled(true); 
        	    this.vista.btnCentroCampistaIzquierdo.setEnabled(true); 
        	    this.vista.btnCentroCampistaIzquierdoCentro.setEnabled(true); 
        	    this.vista.btnDefensaDerecha.setEnabled(true); 
        	    this.vista.btnDefensaDerechaCentro.setEnabled(true); 
        	    this.vista.btnDefensaIzquierda.setEnabled(true); 
        	    this.vista.btnDefensaIzquierdaCentro.setEnabled(true);
        	    
        	    this.vista.lblNombrePortero.setVisible(false);
        	    this.vista.lblNombreDelanteroDerecho.setVisible(false);
        	    this.vista.lblNombreDelanteroIzquierdo.setVisible(false); 
        	    this.vista.lblNombreCentroCampistaDerecha.setVisible(false); 
        	    this.vista.lblNombreCentroCampistaDerechaCentro.setVisible(false); 
        	    this.vista.lblNombreCentroCampistaIzquierdo.setVisible(false); 
        	    this.vista.lblNombreCentroCampistaIzquierdoCentro.setVisible(false); 
        	    this.vista.lblNombreDefensaIDerecha.setVisible(false); 
        	    this.vista.lblNombreDefensaIDerechaCentro.setVisible(false); 
        	    this.vista.lblNombreDefensaIzquierda.setVisible(false); 
        	    this.vista.lblNombreDefensaIzquierdaCentro.setVisible(false);
        	}
        	if(hibernate.isEquiposCreados(1)||hibernate.isEquiposCreados(20)) {
	        	this.vista.panelMenu.setVisible(false);
	        	
	        	this.vista.panelPlantilla.setVisible(true);
	        	this.vista.panelPlantilla.setVisible(true);
	        	cargarJugadoresEquipo();
	        	verificarJugadoresSeleccionados();

        	}else{
        		verificarJugadoresSeleccionados();
        		this.vista.panelMenu.setEnabled(false);
        		this.vista.btnJugar.setEnabled(false);
        		this.vista.btnJugadores.setEnabled(false);
        		this.vista.btnClasificaciones.setEnabled(false);
        		this.vista.btnJugar.setVisible(false);
        		this.vista.btnJugadores.setVisible(false);
        		this.vista.btnClasificaciones.setVisible(false);
        		
        		this.vista.panelNombreEquipo.setVisible(true);
        		this.vista.panelNombreEquipo.setEnabled(true);
        	}
        }

        else if(e.getSource()==this.vista.btnClasificaciones) {
        	cargarTablaJornada();
        	cargarTablaClasificacion();
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelClasificacion.setVisible(true);

        }
        else if(e.getSource()==this.vista.btnJugadores) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelJugadores.setVisible(true);
        }
        else if (e.getSource() == this.vista.btnSimularPartida) {
        	if(hibernate.isEquiposCreadosMenor(20)) {
        		for(int i=0;i<19;i++) {
            		creacionTotalEquipo(nombres[i]);
            	}
        		generarCalendario();
            }
        	Partido partido=hibernate.extraerJornadasNoJugadas().get(0);
        	vista.lblNombreLocalVista.setText(partido.getEquipoByIdEquipoLocal().getNombre());
        	vista.lblNombreVisitanteVista.setText(partido.getEquipoByIdEquipoVisitante().getNombre());
        	
        	cargarLista(vista.listEquipoLocal,partido.getEquipoByIdEquipoLocal().getJugadors());
        	cargarLista(vista.listEquipoVisitante,partido.getEquipoByIdEquipoVisitante().getJugadors());
        	
        	
            this.vista.panelPlantilla.setVisible(false);
            this.vista.panelVistaEquipo.setVisible(true);
        }
        else if(e.getSource()==this.vista.btnFIltrar) {
        	cargarTabla(modeloTJugadores);
        }
        else if(e.getSource()==this.vista.comboBoxJornada) {
        	cargarTablaJornada();
        }
        else if(e.getSource()==this.vista.btnCrearEquipoPropio) {
        	if(!String.valueOf(vista.txtNombreEquipoJugador.getText()).equals("")) {
        		String nombre=String.valueOf(vista.txtNombreEquipoJugador.getText());
        		Equipo equipo=new Equipo();
        		equipo.setNombre(nombre);
        		equipo.setEquipoJugador(true);
        		hibernate.crearEquipo(equipo);
        		this.vista.panelMenu.setVisible(false);
        		this.vista.panelNombreEquipo.setVisible(false);
        		this.vista.btnJugar.setEnabled(true);
        		this.vista.btnJugadores.setEnabled(true);
        		this.vista.btnClasificaciones.setEnabled(true);
	        	this.vista.panelPlantilla.setVisible(true);
        	}else {
        		vista.lblErrorCreacion.setText("ERROR, introduzca un nombre para el equipo");
        	}

        }else if(e.getSource()==this.vista.comboBoxEquipoClasificacion) {
        	cargarPlantilla();

        }else if(e.getSource()==this.vista.btnIniciarSimulacion) {
        	this.vista.btnIniciarSimulacion.setEnabled(false);
        	this.vista.lblVolverPlantilla_Simulacion.setVisible(false);
        	List<Partido> jornada=simularJornada();
        	
        	
        	
        	
        }
        
        
        
    }
    public void cargarPorteros(JLabel lblNombrePortero, JButton btnPortero) {
        List<Jugador> porteros = hibernate.extraerJugadoresPorPosicion("POR");
        Collections.shuffle(porteros);
        this.jugador.clear();
        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        // Asigno los 5 porteros aleatorios a los botones
        for (int i = 0; i < Math.min(5, porteros.size()); i++) {
            Jugador portero = porteros.get(i);
            String mensaje = portero.getNombre() + "<br>" +
                    "F. Ataque: " + portero.getFuerzaAtaque() + "<br>" +
                    "F. Técnica: " + portero.getFuerzaTecnica() + "<br>" +
                    "F. Defensa: " + portero.getFuerzaDefensa() + "<br>" +
                    "F. Portero: " + portero.getFuerzaPortero() + "<br>";

            switch (i) {
                case 0:
                    setLabelButton(vista.btnEleccionUno, mensaje);
                    break;
                case 1:
                    setLabelButton(vista.btnEleccionDos, mensaje);
                    break;
                case 2:
                    setLabelButton(vista.btnEleccionTres, mensaje);
                    break;
                case 3:
                    setLabelButton(vista.btnEleccionCuatro, mensaje);
                    break;
                case 4:
                    setLabelButton(vista.btnEleccionCinco, mensaje);
                    break;
            }
            this.jugador.add(portero);
        }
    }

 

    public void cargarDefensores(JLabel lblNombreDefensor, JButton btnDefensor) {
        List<Jugador> defensores = hibernate.extraerJugadoresPorPosicion("DEF");
        Collections.shuffle(defensores);
        this.jugador.clear();
        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

      
        for (int i = 0; i < Math.min(5, defensores.size()); i++) {
            Jugador defensor = defensores.get(i);
            String mensaje = defensor.getNombre() + "<br>" +
                    "F. Ataque: " + defensor.getFuerzaAtaque() + "<br>" +
                    "F. Técnica: " + defensor.getFuerzaTecnica() + "<br>" +
                    "F. Defensa: " + defensor.getFuerzaDefensa() + "<br>" +
                    "F. Portero: " + defensor.getFuerzaPortero() + "<br>";

            switch (i) {
                case 0:
                    setLabelButton(vista.btnEleccionUno, mensaje);
                    break;
                case 1:
                    setLabelButton(vista.btnEleccionDos, mensaje);
                    break;
                case 2:
                    setLabelButton(vista.btnEleccionTres, mensaje);
                    break;
                case 3:
                    setLabelButton(vista.btnEleccionCuatro, mensaje);
                    break;
                case 4:
                    setLabelButton(vista.btnEleccionCinco, mensaje);
                    break;
            }
            this.jugador.add(defensor);
        }
    }

  
    public void cargarMediocampistas(JLabel lblNombreMediocampista, JButton btnMediocampista) {
        List<Jugador> mediocampistas = hibernate.extraerJugadoresPorPosicion("MED");
        Collections.shuffle(mediocampistas);
        this.jugador.clear();
        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        // Asigno los 5 mediocampistas aleatorios a los botones
        for (int i = 0; i < Math.min(5, mediocampistas.size()); i++) {
            Jugador mediocampista = mediocampistas.get(i);
            String mensaje = mediocampista.getNombre() + "<br>" +
                    "F. Ataque: " + mediocampista.getFuerzaAtaque() + "<br>" +
                    "F. Técnica: " + mediocampista.getFuerzaTecnica() + "<br>" +
                    "F. Defensa: " + mediocampista.getFuerzaDefensa() + "<br>" +
                    "F. Portero: " + mediocampista.getFuerzaPortero() + "<br>";

            switch (i) {
                case 0:
                    setLabelButton(vista.btnEleccionUno, mensaje);
                     break;
                case 1:
                    setLabelButton(vista.btnEleccionDos, mensaje);
                    break;
                case 2:
                    setLabelButton(vista.btnEleccionTres, mensaje);
                     break;
                case 3:
                    setLabelButton(vista.btnEleccionCuatro, mensaje);
                     break;
                case 4:
                    setLabelButton(vista.btnEleccionCinco, mensaje);
                    break;
            }
            this.jugador.add(mediocampista);
            
        }
    }

  

    public void cargarDelanteros(JLabel lblNombreDelantero, JButton btnDelantero) {
        List<Jugador> delanteros = hibernate.extraerJugadoresPorPosicion("DEL");
        Collections.shuffle(delanteros);
        this.jugador.clear();
        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        // Asigno los 5 delanteros aleatorios a los botones
        for (int i = 0; i < Math.min(5, delanteros.size()); i++) {
            Jugador delantero = delanteros.get(i);
            String mensaje = delantero.getNombre() + "<br>" +
                    "F. Ataque: " + delantero.getFuerzaAtaque() + "<br>" +
                    "F. Técnica: " + delantero.getFuerzaTecnica() + "<br>" +
                    "F. Defensa: " + delantero.getFuerzaDefensa() + "<br>" +
                    "F. Portero: " + delantero.getFuerzaPortero() + "<br>";

            switch (i) {
                case 0:
                    setLabelButton(vista.btnEleccionUno, mensaje);
                    break;
                case 1:
                    setLabelButton(vista.btnEleccionDos, mensaje);
                    break;
                case 2:
                    setLabelButton(vista.btnEleccionTres, mensaje);
                    break;
                case 3:
                    setLabelButton(vista.btnEleccionCuatro, mensaje);
                    break;
                case 4:
                    setLabelButton(vista.btnEleccionCinco, mensaje);
                    break;
            }
            this.jugador.add(delantero);
        }
    }

   
    public boolean isPlayerButton(Object source) {
        return source == this.vista.btnPortero ||
               source == this.vista.btnDelanteroDerecho ||
               source == this.vista.btnDelanteroIzquierda ||
               source == this.vista.btnCentroCampistaDerecho ||
               source == this.vista.btnCentroCampistaDerechoCentro ||
               source == this.vista.btnCentroCampistaIzquierdo ||
               source == this.vista.btnCentroCampistaIzquierdoCentro ||
               source == this.vista.btnDefensaDerecha ||
               source == this.vista.btnDefensaDerechaCentro ||
               source == this.vista.btnDefensaIzquierda ||
               source == this.vista.btnDefensaIzquierdaCentro;
    }

    public boolean isEleccionButton(Object source) {
        return source == this.vista.btnEleccionUno ||
               source == this.vista.btnEleccionDos ||
               source == this.vista.btnEleccionTres ||
               source == this.vista.btnEleccionCuatro||
               source == this.vista.btnEleccionCinco;
    }

    public void disableButtons(JButton[] botones) {
        for (JButton boton : botones) {
            boton.setEnabled(false);
            boton.setVisible(false);
        }
    }

    public void enableButtons(JButton[] botones) {
        for (JButton boton : botones) {
            boton.setEnabled(true);
            boton.setVisible(true);
        }
    }
    

    public ImageIcon fotoEscalarLabel(JLabel label, String url) {
        ImageIcon imagenDefecto = new ImageIcon(url);
        ImageIcon icono = new ImageIcon(imagenDefecto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        return icono;
    }

    public ImageIcon fotoEscalarButton(JButton label, String url) {
        ImageIcon imagenDefecto = new ImageIcon(url);
        ImageIcon icono = new ImageIcon(imagenDefecto.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_SMOOTH));
        return icono;
    }

    public void imagenes() {
       //panel Menu 
    	this.vista.lblFondo.setIcon(fotoEscalarLabel(this.vista.lblFondo, "imagenes/fondo-principal.jpg"));
        this.vista.lblFondoMenu.setIcon(fotoEscalarLabel(this.vista.lblFondoMenu, "imagenes/fondo-principal.jpg"));
        this.vista.lblLogo.setIcon(fotoEscalarLabel(this.vista.lblLogo, "imagenes/logo.png"));
        this.vista.lblLogoMenu.setIcon(fotoEscalarLabel(this.vista.lblLogoMenu, "imagenes/logo.png"));
        
        this.vista.lblFondoEleccionUno.setIcon(fotoEscalarLabel(this.vista.lblFondoEleccionUno, "imagenes/foto-carta.png"));
        this.vista.lblFondoEleccionDos.setIcon(fotoEscalarLabel(this.vista.lblFondoEleccionDos, "imagenes/foto-carta.png"));
        this.vista.lblFondoEleccionTres.setIcon(fotoEscalarLabel(this.vista.lblFondoEleccionTres, "imagenes/foto-carta.png"));
        this.vista.lblFondoEleccionCuatro.setIcon(fotoEscalarLabel(this.vista.lblFondoEleccionCuatro, "imagenes/foto-carta.png"));
        this.vista.lblFondoEleccionCinco.setIcon(fotoEscalarLabel(this.vista.lblFondoEleccionCinco, "imagenes/foto-carta.png"));
       
        //inicio
        this.vista.lblFondoPlantilla.setIcon(fotoEscalarLabel(this.vista.lblFondoPlantilla, "imagenes/cesped.png"));
        this.vista.lblSalirPrincipal.setIcon(fotoEscalarLabel(this.vista.lblSalirPrincipal, "imagenes/salir-principal.png"));
        this.vista.lblInformacion.setIcon(fotoEscalarLabel(this.vista.lblInformacion, "imagenes/informacion.png"));
        

        this.vista.btnEmpezar.setIcon(fotoEscalarButton(this.vista.btnEmpezar, "imagenes/boton-inicio.png"));
        //menu
        this.vista.btnJugar.setIcon(fotoEscalarButton(this.vista.btnJugar, "imagenes/boton-jugar.png"));
        this.vista.btnJugadores.setIcon(fotoEscalarButton(this.vista.btnJugadores, "imagenes/sobrenosotros.jpg"));
        this.vista.lblFondoNombreEquipo.setIcon(fotoEscalarLabel(this.vista.lblFondoNombreEquipo, "imagenes/fondo-principal.jpg"));
        //jugador
        this.vista.lblVolverJugadores.setIcon(fotoEscalarLabel(this.vista.lblVolverJugadores, "imagenes/volver.png"));
        //Selecion plantilla
        this.vista.btnPortero.setIcon(fotoEscalarButton(this.vista.btnPortero, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDefensaIzquierda.setIcon(fotoEscalarButton(this.vista.btnDefensaIzquierda, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnCentroCampistaIzquierdoCentro.setIcon(fotoEscalarButton(this.vista.btnCentroCampistaIzquierdoCentro, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDefensaIzquierdaCentro.setIcon(fotoEscalarButton(this.vista.btnDefensaIzquierdaCentro, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDefensaDerechaCentro.setIcon(fotoEscalarButton(this.vista.btnDefensaDerechaCentro, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDefensaDerecha.setIcon(fotoEscalarButton(this.vista.btnDefensaDerecha, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnCentroCampistaIzquierdo.setIcon(fotoEscalarButton(this.vista.btnCentroCampistaIzquierdo, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnCentroCampistaDerechoCentro.setIcon(fotoEscalarButton(this.vista.btnCentroCampistaDerechoCentro, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnCentroCampistaDerecho.setIcon(fotoEscalarButton(this.vista.btnCentroCampistaDerecho, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDelanteroDerecho.setIcon(fotoEscalarButton(this.vista.btnDelanteroDerecho, "imagenes/camiseta-de-futbol.png"));
        this.vista.btnDelanteroIzquierda.setIcon(fotoEscalarButton(this.vista.btnDelanteroIzquierda, "imagenes/camiseta-de-futbol.png"));
        this.vista.lblFondoPlantilla.setIcon(fotoEscalarLabel(this.vista.lblFondoPlantilla, "imagenes/cesped.png"));
        this.vista.btnSimularPartida.setIcon(fotoEscalarButton(this.vista.btnSimularPartida,"imagenes/enfentramiento.png"));

        this.vista.btnJugar.setIcon(fotoEscalarButton(this.vista.btnJugar, "imagenes/boton-jugar.png"));
        this.vista.btnJugadores.setIcon(fotoEscalarButton(this.vista.btnJugadores, "imagenes/sobrenosotros.jpg"));
        this.vista.btnClasificaciones.setIcon(fotoEscalarButton(this.vista.btnClasificaciones, "imagenes/boton-clasificaciones.jpg"));

        this.vista.lblFondoDraft.setIcon(fotoEscalarLabel(this.vista.lblFondoDraft, "imagenes/fondo_futDraft.jpg"));
        this.vista.lblVolverPlantilla.setIcon(fotoEscalarLabel(this.vista.lblVolverPlantilla, "imagenes/volver.png"));
        //Clasificacion Cambiar imagen
        this.vista.lblVolverClasificacion.setIcon(fotoEscalarLabel(this.vista.lblVolverClasificacion,"imagenes/volver.png"));
        this.vista.lblFondoCLasificacion.setIcon(fotoEscalarLabel(this.vista.lblFondoCLasificacion,"imagenes/fondo-principal.jpg"));
        this.vista.lblFondoClasificacionEquipo.setIcon(fotoEscalarLabel(this.vista.lblFondoClasificacionEquipo,"imagenes/cesped.png"));
        
        
        this.vista.lblLogJugadores.setIcon(fotoEscalarLabel(this.vista.lblLogJugadores, "imagenes/logo.png"));
        this.vista.lblFondo_Pantalla_Jugadores.setIcon(fotoEscalarLabel(this.vista.lblFondo_Pantalla_Jugadores, "imagenes/fondo-principal.jpg"));
        //panelVistaEquipo
        this.vista.lblVolverPlantilla_1.setIcon(fotoEscalarLabel(this.vista.lblVolverPlantilla_1,"imagenes/volver.png"));
        this.vista.lblNewLabel_EscudoEquipoLocal.setIcon(fotoEscalarLabel(this.vista.lblNewLabel_EscudoEquipoLocal,"imagenes/fotoLogoLocal.png"));
        this.vista.lblNewLabel_EquipoVisitante.setIcon(fotoEscalarLabel(this.vista.lblNewLabel_EquipoVisitante,"imagenes/fotoLogoVisitante.png"));
        this.vista.lblFtotoVersus.setIcon(fotoEscalarLabel(this.vista.lblFtotoVersus,"imagenes/versus.png"));
        this.vista.lblFondoPantalla_2.setIcon(fotoEscalarLabel(this.vista.lblFondoPantalla_2,"imagenes/fondo-principal.jpg"));
        this.vista.lblEmpezarSimulacion.setIcon(fotoEscalarLabel(this.vista.lblEmpezarSimulacion,"imagenes/fotoSilvato.png"));
        //panelSimulacion
        this.vista.lblFondeSimulacion.setIcon(fotoEscalarLabel(this.vista.lblFondeSimulacion,"imagenes/fondo-principal.jpg"));
        this.vista.lblVolverPlantilla_Simulacion.setIcon(fotoEscalarLabel(this.vista.lblVolverPlantilla_Simulacion,"imagenes/volver.png"));
        this.vista.lblNewLabel_EscudoEquipoLocal_Simulacion.setIcon(fotoEscalarLabel(this.vista.lblNewLabel_EscudoEquipoLocal_Simulacion,"imagenes/fotoLogoLocal.png"));
        this.vista.lblNewLabel_EscudoEquipoVisitante_Simulacion.setIcon(fotoEscalarLabel(this.vista.lblNewLabel_EscudoEquipoVisitante_Simulacion,"imagenes/fotoLogoVisitante.png"));
        this.vista.lblFtotoVersus_1.setIcon(fotoEscalarLabel(this.vista.lblFtotoVersus_1,"imagenes/versus.png"));
        this.vista.btnIniciarSimulacion.setIcon(fotoEscalarButton(this.vista.btnIniciarSimulacion, "imagenes/boton_simular.png"));
        this.vista.lblFondo_Marcador.setIcon(fotoEscalarLabel(this.vista.lblFondo_Marcador,"imagenes/marcador.png"));
        //panelInformacion
        this.vista.lblFondoInformacion.setIcon(fotoEscalarLabel(this.vista.lblFondoInformacion,"imagenes/fondo-principal.jpg"));
        this.vista.lblSalirMenu_Informacion.setIcon(fotoEscalarLabel(this.vista.lblSalirMenu_Informacion,"imagenes/volver.png"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
        if(e.getSource()==this.vista.lblVolverJugadores) {
        	this.vista.panelJugadores.setVisible(false);
        	this.vista.panelMenu.setVisible(true);

        }
        else if(e.getSource()==this.vista.lblVolverPlantilla) {

        	this.vista.panelPlantilla.setVisible(false);
        	this.vista.panelMenu.setVisible(true);
        	this.vista.btnJugar.setEnabled(true);
        	this.vista.btnJugar.setVisible(true);
        	this.vista.btnClasificaciones.setVisible(true);
        	this.vista.btnClasificaciones.setEnabled(true);
        	this.vista.btnJugadores.setEnabled(true);
        	this.vista.btnJugadores.setVisible(true);
        }else if(e.getSource()==this.vista.lblVolverPlantilla_1) {
        	this.vista.panelVistaEquipo.setVisible(false);
        	this.vista.panelPlantilla.setVisible(true);
        }else if(e.getSource()==this.vista.lblEmpezarSimulacion) {
        	this.vista.panelVistaEquipo.setVisible(false);
        	this.vista.btnIniciarSimulacion.setEnabled(true);
        	this.vista.panelSimulacion.setVisible(true);
        }else if(e.getSource()==this.vista.lblVolverPlantilla_Simulacion) {
        	List<Partido> partidos=hibernate.extraerJornadasNoJugadas();
        	
        	if(partidos.size()==0) {
        		this.vista.panelPlantilla.setVisible(true);
            	this.vista.panelSimulacion.setVisible(false);
            	this.vista.btnSimularPartida.setEnabled(false);
        	}else {
        		Partido partido=hibernate.extraerJornadasNoJugadas().get(0);
            	vista.lblNombreLocalVista.setText(partido.getEquipoByIdEquipoLocal().getNombre());
            	vista.lblNombreVisitanteVista.setText(partido.getEquipoByIdEquipoVisitante().getNombre());
            	
            	cargarLista(vista.listEquipoLocal,partido.getEquipoByIdEquipoLocal().getJugadors());
            	cargarLista(vista.listEquipoVisitante,partido.getEquipoByIdEquipoVisitante().getJugadors());
        		this.vista.panelVistaEquipo.setVisible(true);
            	this.vista.panelSimulacion.setVisible(false);
        	}
        	
        //Panel informacion
        }else if(e.getSource()==this.vista.lblInformacion) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelInformacion.setVisible(true);
        	this.vista.panelMostrarInformacion.setVisible(true);
        	this.vista.panelMostrarInformacionDedicamos_1.setVisible(true);
        	this.vista.panelMostrarInformacionDedicamos.setVisible(true);
        	String mensaje= "En FutDraft, somos apasionados del fútbol y la tecnología.<br>" +
                    "Nuestra misión es revolucionar la forma en que los fanáticos<br>" +
                    "del fútbol interactúan con su deporte favorito. Con FutDraft,<br>" +
                    "ofrecemos una experiencia única donde los usuarios pueden<br>" +
                    "crear, gestionar y competir con sus propios equipos de ensueño,<br>" +
                    "utilizando estadísticas en tiempo real y datos actualizados<br>" +
                    "de jugadores de todo el mundo. Nuestro equipo está formado por<br>" +
                    "desarrolladores, diseñadores y amantes del fútbol que comparten<br>" +
                    "una visión común: llevar la emoción del fútbol a un nivel superior.<br>";
        	setLabelText(vista.lblDescripcionSobreNosotros,mensaje);
        	String mensaje2= "Un FutDraft es una plataforma o aplicación que se dedica a la creación y gestión de equipos de fútbol virtuales,<br>" +
                    "donde los usuarios pueden actuar como 'mánagers' y armar sus propios equipos de ensueño.<br>" +
                    "<br>" +
                    "*Creación de equipos virtuales<br>" +
                    "*Competiciones y ligas<br>" +
                    "*Gestión de plantillas<br>" +
                    "*Estadísticas en tiempo real<br>" +
                    "*Experiencia interactiva<br>" +
                    "*Educación y estrategia<br>";
        	setLabelText(vista.lblDescripcionDedicamos,mensaje2);
        	String mensaje3= "FutDraft es una emocionante plataforma que te permite convertirte en el mánager de tu propio equipo de fútbol virtual.<br>" +
                    "<br>" +
                    "1. Regístrate y crea tu equipo:<br>" +
                    "   - Regístrate y elige un nombre para tu equipo.<br>" +
                    "   - Selecciona jugadores de diferentes ligas y posiciones.<br>" +
                    "<br>" +
                    "2. Armar tu alineación:<br>" +
                    "   - Organiza a tus jugadores en la formación que prefieras.<br>" +
                    "   - Equilibra tu equipo con defensores, mediocampistas y delanteros.<br>";
        	setLabelText(vista.lblDescripcionComoJugar,mensaje3);
        }else if(e.getSource()==this.vista.lblSalirMenu_Informacion) {
        	this.vista.panelMenu.setVisible(true);
        	this.vista.panelInformacion.setVisible(false);
        	this.vista.panelMostrarInformacion.setVisible(false);
        	this.vista.panelMostrarInformacionDedicamos_1.setVisible(false);
        	this.vista.panelMostrarInformacionDedicamos.setVisible(false);
        }else if(e.getSource()== this.vista.lblSalirPrincipal) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelInicio.setVisible(true);
        }

        else if(e.getSource()==this.vista.lblVolverClasificacion) {
        	this.vista.panelClasificacion.setVisible(false);
        	this.vista.panelMenu.setVisible(true);
        }

    }

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		 if (e.getSource() == this.vista.btnSimularPartida) {
		        vista.btnSimularPartida.setIcon(fotoEscalarButton(vista.btnSimularPartida, "imagenes/enfentramiento_hover.png"));
		        vista.btnSimularPartida.setBounds(vista.btnSimularPartida.getX(), vista.btnSimularPartida.getY(), 250, 60);
		      
		    }
	}

	@Override
	public void mouseExited(MouseEvent e) {
		if (e.getSource() == this.vista.btnSimularPartida) {
	        vista.btnSimularPartida.setIcon(fotoEscalarButton(vista.btnSimularPartida, "imagenes/enfentramiento_hover.png"));
	        vista.btnSimularPartida.setBounds(vista.btnSimularPartida.getX(), vista.btnSimularPartida.getY(), 200, 50);
	    }
		
	}
	
	public  void setLabelText(JLabel label, String text) {
        String htmlText = "<html>" + text.replace("\n", "<br>") + "</html>";
        label.setText(htmlText);
    }
	public  void setLabelButton(JButton btnEleccionUno, String text) {
        String htmlText = "<html>" + text.replace("\n", "<br>") + "</html>";
        btnEleccionUno.setText(htmlText);
    }
	
	
	//CargarTabla y Spinners
	public void cargarTabla(DefaultTableModel modelo) {
		
		int minA=(int) vista.spinnerMinAtaque.getValue();
		int maxA=(int) vista.spinnerMaxAtaque.getValue();
		int minT=(int) vista.spinnerMinTecnica.getValue();
		int maxT=(int) vista.spinnerMaxTecnica.getValue();
		int minD=(int) vista.spinnerMinDefensa.getValue();
		int maxD=(int) vista.spinnerMaxDefensa.getValue();
		int minP=(int) vista.spinnerMinPortero.getValue();
		int maxP=(int) vista.spinnerMaxPortero.getValue();
		String equipo=String.valueOf(vista.comboBoxFiltroEquipo.getSelectedItem());
		String posicion=String.valueOf(vista.comboBoxFiltroPosicion.getSelectedItem());
		
		
		List<Jugador> entrada=hibernate.extraerJugadoresFiltro(minA,maxA,minT,maxT,minD,maxD,minP,maxP);
		
		int tamano=entrada.size();
		
		if(!equipo.equals("Todos")) {
			for(int i =tamano-1;i>-1;i--) {
				if(!entrada.get(i).getEquipo_1().equals(equipo)) {
					entrada.remove(i);
				}
			}
		}
		
		tamano=entrada.size();
		
		if(!posicion.equals("Todas")) {
			for(int i =tamano-1;i>-1;i--) {
				if(!entrada.get(i).getPosicion().equals(posicion)) {
					entrada.remove(i);
				}
			}
		}
		
		
		
		
		modelo.setRowCount(0);
		vista.tablaJugadores.setModel(modelo);
		for(Jugador clave:entrada) {
			String[] row= {
					clave.getNombre(),
					clave.getEquipo_1(),
					clave.getPosicion(),
					String.valueOf(clave.getFuerzaAtaque()),
					String.valueOf(clave.getFuerzaDefensa()),
					String.valueOf(clave.getFuerzaTecnica()),
					String.valueOf(clave.getFuerzaPortero())
			};
			
			modelo.addRow(row);
		}
	}


	public void cargarEquipos() {
		
		List<String> entrada=hibernate.extraerNombreEquipos();
		
		for(String clave:entrada) {
			vista.comboBoxFiltroEquipo.addItem(clave);
		}
		
	}


	//creacion de plantillas
	public List<Jugador> crerPlantillaMaquina() {
	        
	        List<Jugador> equipo=new ArrayList<Jugador>();
	        List<Jugador> temp;
	        
	        temp = hibernate.extraerJugadoresPorPosicion("POR");
	        Collections.shuffle(temp);
	        if (!temp.isEmpty()) {
	            equipo.add(temp.get(0)); 
	        }  
	        
	        temp = hibernate.extraerJugadoresPorPosicion("DEF");
	        Collections.shuffle(temp);
	        for (int i = 0; i < Math.min(4, temp.size()); i++) {
	        	equipo.add(temp.get(i));
	        }

	        temp = hibernate.extraerJugadoresPorPosicion("MED");
	        Collections.shuffle(temp);
	        for (int i = 0; i < Math.min(4, temp.size()); i++) {
	        	equipo.add(temp.get(i));
	        }

	       
	        temp = hibernate.extraerJugadoresPorPosicion("DEL");
	        Collections.shuffle(temp);
	        for (int i = 0; i < Math.min(2, temp.size()); i++) {
	        	equipo.add(temp.get(i));
	        }

	       return equipo;
	}
	public void asignarJugadoresEquipo(Equipo equipo,List<Jugador> jugadores) {
		
		for(Jugador clave:jugadores) {
			clave.setEquipo(equipo);
		}
		
	}
	
	public void creacionTotalEquipo(String nombre) {
		List<Jugador> temp=crerPlantillaMaquina();
		Equipo equipo=hibernate.crearEquipo(nombre);
		asignarJugadoresEquipo(equipo, temp);
		hibernate.asignarEquipoUpdate(temp);
	}
	
	//Generar Calendario
	public void generarCalendario() {
		List<Equipo> entrada=hibernate.extraerEquipos();
		List<Partido>partidos=new ArrayList<Partido>();
		int totalEquipos = entrada.size();
        int mitad = totalEquipos / 2;

		for(int i=0;i<19;i++) {
			
			for (int t = 0; t < mitad; t++) {
				Equipo local = entrada.get(t);
				Equipo visitante = entrada.get(totalEquipos - 1 - t);
                partidos.add(new Partido(local,visitante,i+1));
            }
			
            List<Equipo> nuevaLista = new ArrayList<>();
            nuevaLista.add(entrada.get(0)); 
            nuevaLista.add(entrada.get(totalEquipos - 1)); 

            for (int t = 1; t < totalEquipos - 1; t++) {
                nuevaLista.add(entrada.get(t));
            }

            entrada = nuevaLista;

			
			
			
		}
		
		hibernate.guardarCalendario(partidos);
		
	}
	
	public void cargarComboJornada() {

		for(int i=1;i<20;i++) {
			vista.comboBoxJornada.addItem("Jornada "+i);
		}
		
	}
	
	public void cargarTablaJornada() {
		
		String [] valor=String.valueOf(vista.comboBoxJornada.getSelectedItem()).split(" ");
		int jornada=Integer.parseInt(valor[1]);
		List<Partido> partidos=hibernate.extraerJornada(jornada);
		modeloTJornadas.setRowCount(0);
		for(Partido clave:partidos) {
			
			String local=clave.getEquipoByIdEquipoLocal().getNombre();
			String visitante=clave.getEquipoByIdEquipoVisitante().getNombre();
			String resultadoL,resultadoV;
			if(clave.getGolesLocal()==null) {
				resultadoL="X";
			}else {
				resultadoL=String.valueOf(clave.getGolesLocal());
			}
			
			if(clave.getGolesVisitante()==null) {
				resultadoV="X";
			}else {
				resultadoV=String.valueOf(clave.getGolesVisitante());
			}
			modeloTJornadas.addRow(new String[] {local,resultadoL+"-"+resultadoV,visitante});
		}
		vista.tablaJornadas.setModel(modeloTJornadas);
		
	}
	
	public void cargarTablaClasificacion() {
		List<Equipo> entrada=hibernate.extraerEquiposOrdenados();
		DefaultComboBoxModel<String> modeloCombo=new DefaultComboBoxModel<String>();
		int pos=1;
		modeloTCLasidicacion.setRowCount(0);
		for(Equipo clave:entrada) {
			String posS=String.valueOf(pos);
			String nombre=clave.getNombre();
			String pts=String.valueOf(clave.getPuntos());
			String dg=String.valueOf(clave.getGolesFavor()-clave.getGolesContra());
			String pj=String.valueOf(clave.getEmpates()+clave.getVictorias()+clave.getDerrotas());
			String v=String.valueOf(clave.getVictorias());
			String e=String.valueOf(clave.getEmpates());
			String d=String.valueOf(clave.getDerrotas());
			
			modeloCombo.addElement(nombre);
			modeloTCLasidicacion.addRow(new String[] {posS,nombre,pts,dg,pj,v,e,d});
			
			pos++;
		}
		vista.comboBoxEquipoClasificacion.setModel(modeloCombo);
		vista.tablaClasificacion.setModel(modeloTCLasidicacion);
	}
	
	public void cargarLista(JList lista, Set<Jugador> jugadores) {
		DefaultListModel<String> modelo=new DefaultListModel<String>();
		for(Jugador clave:jugadores) {
			String elemento=clave.getPosicion()+"-"+clave.getNombre();
			modelo.addElement(elemento);
		}
		
		lista.setModel(modelo);
	}
	
	public void cargarJugadoresEquipo() {
	    List<Jugador> jugadores = hibernate.obtenerJugadoresPorEquipo();

	    if (jugadores.size()==0) {
	        System.out.println("El equipo no tiene jugadores.");
	       
	    }else {
	    	
	  
		    int contadorPorteros = 0;
		    int contadorDefensores = 0;
		    int contadorMediocampistas = 0;
		    int contadorDelanteros = 0;
	
		    for (Jugador jugador : jugadores) {
		        switch (jugador.getPosicion()) {
		            case "POR":
		                if (contadorPorteros == 0) {
		                    vista.lblNombrePortero.setText(jugador.getNombre());
		                    vista.lblNombrePortero.setVisible(true);
		                    this.vista.btnPortero.setVisible(false);
		                    this.vista.btnPortero.setEnabled(false);
		                }
		                contadorPorteros++;
		                break;
		            case "DEF":
		            	if (contadorDefensores == 4) {
		                    this.vista.btnDefensaDerecha.setVisible(false);
		                    this.vista.btnDefensaDerecha.setVisible(false);
		                    this.vista.btnDefensaIzquierda.setVisible(false);
		                    this.vista.btnDefensaIzquierdaCentro.setVisible(false);
		                }
		            	else if (contadorDefensores < 4) { 
		                    if (contadorDefensores == 0) {
		                        vista.lblNombreDefensaIDerecha.setText(jugador.getNombre());
		                        vista.lblNombreDefensaIDerecha.setVisible(true);
		                        this.vista.btnDefensaDerecha.setVisible(false);
		                        this.vista.btnDefensaDerecha.setEnabled(false);
		                    } else if (contadorDefensores == 1) {
		                        vista.lblNombreDefensaIDerechaCentro.setText(jugador.getNombre());
		                        vista.lblNombreDefensaIDerechaCentro.setVisible(true);
		                        this.vista.btnDefensaDerechaCentro.setVisible(false);
		                        this.vista.btnDefensaDerechaCentro.setEnabled(false);
		                    } else if (contadorDefensores == 2) {
		                        vista.lblNombreDefensaIzquierda.setText(jugador.getNombre());
		                        vista.lblNombreDefensaIzquierda.setVisible(true);
		                        this.vista.btnDefensaIzquierda.setVisible(false);
		                        this.vista.btnDefensaIzquierda.setEnabled(false);
		                    } else if (contadorDefensores == 3) {
		                        vista.lblNombreDefensaIzquierdaCentro.setText(jugador.getNombre());
		                        vista.lblNombreDefensaIzquierdaCentro.setVisible(true);
		                        this.vista.btnDefensaIzquierdaCentro.setVisible(false);
		                        this.vista.btnDefensaIzquierdaCentro.setEnabled(false);
		                    }
		                    contadorDefensores++;
		                }
		                
		                break;
		            case "MED":
		            	if (contadorMediocampistas == 4) {
		                    this.vista.btnCentroCampistaDerecho.setVisible(false);
		                    this.vista.btnCentroCampistaDerechoCentro.setVisible(false);
		                    this.vista.btnCentroCampistaIzquierdo.setVisible(false);
		                    this.vista.btnCentroCampistaIzquierdoCentro.setVisible(false);
		                }
		            	else if (contadorMediocampistas < 4) {
		                    if (contadorMediocampistas == 0) {
		                        vista.lblNombreCentroCampistaDerecha.setText(jugador.getNombre());
		                        vista.lblNombreCentroCampistaDerecha.setVisible(true);
		                        this.vista.btnCentroCampistaDerecho.setVisible(false);
		                        this.vista.btnCentroCampistaDerecho.setEnabled(false);
		                    } else if (contadorMediocampistas == 1) {
		                        vista.lblNombreCentroCampistaDerechaCentro.setText(jugador.getNombre());
		                        vista.lblNombreCentroCampistaDerechaCentro.setVisible(true);
		                        this.vista.btnCentroCampistaDerechoCentro.setVisible(false);
		                        this.vista.btnCentroCampistaDerechoCentro.setEnabled(false);
		                    } else if (contadorMediocampistas == 2) {
		                        vista.lblNombreCentroCampistaIzquierdo.setText(jugador.getNombre());
		                        vista.lblNombreCentroCampistaIzquierdo.setVisible(true);
		                        this.vista.btnCentroCampistaIzquierdo.setEnabled(false);
		                        this.vista.btnCentroCampistaIzquierdo.setVisible(false);
		                    } else if (contadorMediocampistas == 3) {
		                        vista.lblNombreCentroCampistaIzquierdoCentro.setText(jugador.getNombre());
		                        vista.lblNombreCentroCampistaIzquierdoCentro.setVisible(true);
		                        this.vista.btnCentroCampistaIzquierdoCentro.setEnabled(false);
		                        this.vista.btnCentroCampistaIzquierdoCentro.setVisible(false);
		                    }
		                    contadorMediocampistas++;
		                }		               
		                
		                break;
		            case "DEL":
		            	if (contadorDelanteros == 2) {
		                    this.vista.btnDelanteroIzquierda.setVisible(false);
		                    this.vista.btnDelanteroDerecho.setVisible(false);
		                }
		            	else if (contadorDelanteros < 2) { 
		                    if (contadorDelanteros == 0) {
		                        vista.lblNombreDelanteroIzquierdo.setText(jugador.getNombre());
		                        vista.lblNombreDelanteroIzquierdo.setVisible(true);
		                        this.vista.btnDelanteroIzquierda.setVisible(false);
		                        this.vista.btnDelanteroIzquierda.setEnabled(false);
		                    } else if (contadorDelanteros == 1) {
		                        vista.lblNombreDelanteroDerecho.setText(jugador.getNombre());
		                        vista.lblNombreDelanteroDerecho.setVisible(true);
		                        this.vista.btnDelanteroDerecho.setVisible(false);
		                        this.vista.btnDelanteroDerecho.setEnabled(false);
		                    }
		                    contadorDelanteros++;
		                }
		                
		                break;
		            default:
		                break;
		        }
		    
		    }
	    }
	}

	public void cargarPlantilla() {
		int selec=vista.comboBoxEquipoClasificacion.getSelectedIndex();
		Equipo equipo=hibernate.extraerEquiposOrdenados().get(selec);
		List<Jugador> jugadores=hibernate.extraerPlantilla(equipo);
		
		if(jugadores.size()==11) {
			for(int i =0;i<11;i++) {
				
				switch(i) {
				case 0:
					vista.lblClasiDefensa1.setText(jugadores.get(i).getNombre());
					break;
				case 1:
					vista.lblClasiDefensa2.setText(jugadores.get(i).getNombre());
					break;
				case 2:
					vista.lblClasiDefensa3.setText(jugadores.get(i).getNombre());
					break;
				case 3:
					vista.lblClasiDefensa4.setText(jugadores.get(i).getNombre());
					break;
				case 4:
					vista.lblClasiDelantero1.setText(jugadores.get(i).getNombre());
					break;
				case 5:
					vista.lblClasiDelantero2.setText(jugadores.get(i).getNombre());
					break;
				case 6:
					vista.lblClasiMedio1.setText(jugadores.get(i).getNombre());
					break;
				case 7:
					vista.lblClasiMedio2.setText(jugadores.get(i).getNombre());
					break;
				case 8:
					vista.lblClasiMedio3.setText(jugadores.get(i).getNombre());
					break;
				case 9:
					vista.lblClasiMedio4.setText(jugadores.get(i).getNombre());
					break;
				case 10:
					vista.lblClasiPortero.setText(jugadores.get(i).getNombre());
					break;
				}
				
			}
		}
	} 

    public void verificarJugadoresSeleccionados() {
    	List<Jugador> total = hibernate.obtenerJugadoresPorEquipo();
    	int jugadoresEquipoLocalSeleccionados=total.size();
    	System.out.println(jugadoresEquipoLocalSeleccionados);
        if (jugadoresEquipoLocalSeleccionados ==11 ) {
            vista.btnSimularPartida.setEnabled(true);
        } else {
            vista.btnSimularPartida.setEnabled(false);
           
        }
        
    }
   
    public List<Partido> simularJornada() {
    	jornada=hibernate.extraerJornada();
    	jugandose=new ArrayList<PartidoFutbol>();
    	
    	boolean ultimaJornada=true;
    	if(jornada.size()==10) {
    		ultimaJornada=true;
    	}else {
	    	for(int i=jornada.size()-1;i>9;i--) {
	    		jornada.remove(i);
	    	}
    	}
    	PartidoFutbol hilo1=null;
    	try {
    		
    	for(Partido clave:jornada) {
    		if(clave.getEquipoByIdEquipoLocal().isEquipoJugador()) {
    			DefaultListModel<String> modelo=new DefaultListModel<String>();
    			this.vista.listSimulacion.setModel(modelo);
    			hilo1=new  PartidoFutbol(clave,vista);
    			jugandose.add(hilo1);
    			hilo1.start();
    		}else {
    			PartidoFutbol hilo=new PartidoFutbol(clave);
    			jugandose.add(hilo);
    			hilo.start();
 	
    		}
    		
    	}
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    
    	
    	System.out.println("Fin simulacion");
    	
    	return jornada;
    }
 
    
}