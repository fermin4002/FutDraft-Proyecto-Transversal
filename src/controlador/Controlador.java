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

public class Controlador implements ActionListener,MouseListener {

    private Vista vista;
    private ControladorHibernate hibernate;
    private DefaultListModel<String> listModelVisitante = new DefaultListModel<>();

    private DefaultTableModel modeloTJugadores,modeloTCLasidicacion,modeloTJornadas;

    public Controlador(Vista vista) {
        this.vista = vista;
        //Principio
        this.vista.btnEmpezar.addActionListener(this);
        this.vista.btnEmpezar.setActionCommand("empezar");
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

        this.vista.lblSalir.addMouseListener(this);
        this.vista.btnSimularPartida.addActionListener(this);
        this.vista.btnJugadores.addActionListener(this);
        this.vista.lblVolverJugadores.addMouseListener(this);
        this.vista.lblVolverPlantilla.addMouseListener(this);


        //Clasificacion
        this.vista.lblVolverClasificacion.addMouseListener(this);
        this.vista.btnFIltrar.addActionListener(this);

        this.vista.lblSalirPrincipal.addMouseListener(this);

       //Ver equipos
        this.vista.lblVolverPlantilla_1.addMouseListener(this);

        //Ver simulacion
        this.vista.lblEmpezarSimulacion.addMouseListener(this);
        this.vista.lblVolverPlantilla_Simulacion.addMouseListener(this);
        this.vista.panelVistaEquipo.setVisible(false); // Asegúrate de que el panel que contiene el JList esté visible
        this.vista.listEquipoVisitante.setModel(listModelVisitante); 
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
        modeloTCLasidicacion.addColumn("");
        modeloTCLasidicacion.addColumn("Nombre");
        modeloTCLasidicacion.addColumn("PJ");
        modeloTCLasidicacion.addColumn("PTO");
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
			hibernate.cargarJugadores();
		} catch (Exception e) {
			
			e.printStackTrace();
		}
        try {
			this.hibernate=new ControladorHibernate();
			hibernate.cargarJugadores();
		} catch (Exception e) {
			e.printStackTrace();
		}
        
        //vista.comboBoxFiltroEquipo.setModel();
        cargarEquipos();
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
            cargarPorteros(); 
        }else if (e.getSource() == this.vista.btnDefensaDerecha || e.getSource() == this.vista.btnDefensaIzquierda|| e.getSource() == this.vista.btnDefensaIzquierdaCentro||e.getSource() == this.vista.btnDefensaDerechaCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDefensores();
        } else if (e.getSource() == this.vista.btnCentroCampistaDerecho || e.getSource() == this.vista.btnCentroCampistaIzquierdo||e.getSource() == this.vista.btnCentroCampistaDerechoCentro||e.getSource() == this.vista.btnCentroCampistaIzquierdoCentro) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarMediocampistas();
        } else if (e.getSource() == this.vista.btnDelanteroDerecho || e.getSource() == this.vista.btnDelanteroIzquierda) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
            cargarDelanteros();
        }else if (isPlayerButton(e.getSource())) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
        } else if (isEleccionButton(e.getSource())) {
            vista.panelElecion.setVisible(false);
            enableButtons(botonesDeshabilitar);
        }
        if(e.getSource()==this.vista.lblFondoDraft) {
        	vista.panelElecion.setVisible(false);
        }
        else if(e.getSource()==this.vista.btnJugar) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.PanelPlantilla.setVisible(true);
        }

        else if(e.getSource()==this.vista.btnClasificaciones) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelClasificacion.setVisible(true);

        }
        else if(e.getSource()==this.vista.btnJugadores) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelJugadores.setVisible(true);
        }
        else if (e.getSource() == this.vista.btnSimularPartida) {
        	
        	for(int i=0;i<19;i++) {
        		creacionTotalEquipo();
        	}
        	JList<String> jListVisitante = new JList<>(listModelVisitante);
            this.vista.PanelPlantilla.setVisible(false);
            this.vista.panelVistaEquipo.setVisible(true);
        }
        else if(e.getSource()==this.vista.btnFIltrar) {
        	cargarTabla(modeloTCLasidicacion);
        }
        
    }
    public void cargarPorteros() {
        List<Jugador> porteros = hibernate.extraerJugadoresPorPosicion("POR"); 
        //Me mezcla la lista que he sacado de las consultas
        Collections.shuffle(porteros); 

        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");
     // Asigno los 5 porteros aleatorios a los botones con el Math.min lo que hace esque me recorra 5 veces la lista y esos 5 porteros me los añade a los botones
        for (int i = 0; i < Math.min(5, porteros.size()); i++) {
            Jugador portero = porteros.get(i);
            String mensaje= portero.getNombre() +"<br>"+
                	"F. Ataque: "+ portero.getFuerzaAtaque()+"<br>"+
                	"F. Tecnica: "+ portero.getFuerzaTecnica()+"<br>"+
                	"F. Defensa: "+ portero.getFuerzaDefensa()+"<br>"+
                	"F. Portero: "+ portero.getFuerzaPortero()+"<br>";
            switch (i) {
            case 0:
            	setLabelButton(vista.btnEleccionUno,mensaje);
                break;
            case 1:
            	setLabelButton(vista.btnEleccionDos,mensaje);
                break;
            case 2:
            	setLabelButton(vista.btnEleccionTres,mensaje);
                break;
            case 3:
            	setLabelButton(vista.btnEleccionCuatro,mensaje);
                break;
            case 4:
            	setLabelButton(vista.btnEleccionCinco,mensaje);
                break;
            }
        }
    }
    public void cargarDefensores() {
        List<Jugador> defensores = hibernate.extraerJugadoresPorPosicion("DEF");
        Collections.shuffle(defensores);

        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        for (int i = 0; i < Math.min(5, defensores.size()); i++) {
            Jugador defensor = defensores.get(i);
        	String mensaje= defensor.getNombre() +"<br>"+
        	"F. Ataque: "+ defensor.getFuerzaAtaque()+"<br>"+
        	"F. Tecnica: "+ defensor.getFuerzaTecnica()+"<br>"+
        	"F. Defensa: "+ defensor.getFuerzaDefensa()+"<br>"+
        	"F. Portero: "+ defensor.getFuerzaPortero()+"<br>";
        
        	
            switch (i) {
                case 0:
                	setLabelButton(vista.btnEleccionUno,mensaje);
                    break;
                case 1:
                	setLabelButton(vista.btnEleccionDos,mensaje);
                    break;
                case 2:
                	setLabelButton(vista.btnEleccionTres,mensaje);
                    break;
                case 3:
                	setLabelButton(vista.btnEleccionCuatro,mensaje);
                    break;
                case 4:
                	setLabelButton(vista.btnEleccionCinco,mensaje);
                    break;
            }
        }
    }
    public void cargarMediocampistas() {
        List<Jugador> mediocampistas = hibernate.extraerJugadoresPorPosicion("MED");
        Collections.shuffle(mediocampistas);

        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        for (int i = 0; i < Math.min(5, mediocampistas.size()); i++) {
            Jugador mediocampista = mediocampistas.get(i);
            String mensaje= mediocampista.getNombre() +"<br>"+
                	"F. Ataque: "+ mediocampista.getFuerzaAtaque()+"<br>"+
                	"F. Tecnica: "+ mediocampista.getFuerzaTecnica()+"<br>"+
                	"F. Defensa: "+ mediocampista.getFuerzaDefensa()+"<br>"+
                	"F. Portero: "+ mediocampista.getFuerzaPortero()+"<br>";
            switch (i) {
            case 0:
            	setLabelButton(vista.btnEleccionUno,mensaje);
                break;
            case 1:
            	setLabelButton(vista.btnEleccionDos,mensaje);
                break;
            case 2:
            	setLabelButton(vista.btnEleccionTres,mensaje);
                break;
            case 3:
            	setLabelButton(vista.btnEleccionCuatro,mensaje);
                break;
            case 4:
            	setLabelButton(vista.btnEleccionCinco,mensaje);
                break;
            }
        }
    }
    public void cargarDelanteros() {
        List<Jugador> delanteros = hibernate.extraerJugadoresPorPosicion("DEL");
        Collections.shuffle(delanteros);

        vista.btnEleccionUno.setText("");
        vista.btnEleccionDos.setText("");
        vista.btnEleccionTres.setText("");
        vista.btnEleccionCuatro.setText("");
        vista.btnEleccionCinco.setText("");

        for (int i = 0; i < Math.min(5, delanteros.size()); i++) {
            Jugador delantero = delanteros.get(i);
            String mensaje= delantero.getNombre() +"<br>"+
                	"F. Ataque: "+ delantero.getFuerzaAtaque()+"<br>"+
                	"F. Tecnica: "+ delantero.getFuerzaTecnica()+"<br>"+
                	"F. Defensa: "+ delantero.getFuerzaDefensa()+"<br>"+
                	"F. Portero: "+ delantero.getFuerzaPortero()+"<br>";
            switch (i) {
            case 0:
            	setLabelButton(vista.btnEleccionUno,mensaje);
                break;
            case 1:
            	setLabelButton(vista.btnEleccionDos,mensaje);
                break;
            case 2:
            	setLabelButton(vista.btnEleccionTres,mensaje);
                break;
            case 3:
            	setLabelButton(vista.btnEleccionCuatro,mensaje);
                break;
            case 4:
            	setLabelButton(vista.btnEleccionCinco,mensaje);
                break;
            }
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

       
        //inicio

        this.vista.lblFondoPlantilla.setIcon(fotoEscalarLabel(this.vista.lblFondoPlantilla, "imagenes/cesped.png"));
        this.vista.lblSalirPrincipal.setIcon(fotoEscalarLabel(this.vista.lblSalirPrincipal, "imagenes/salir-principal.png"));
        this.vista.lblInformacion.setIcon(fotoEscalarLabel(this.vista.lblInformacion, "imagenes/informacion.png"));

        this.vista.btnEmpezar.setIcon(fotoEscalarButton(this.vista.btnEmpezar, "imagenes/boton-inicio.png"));
        //menu
        this.vista.btnJugar.setIcon(fotoEscalarButton(this.vista.btnJugar, "imagenes/boton-jugar.png"));
        this.vista.btnJugadores.setIcon(fotoEscalarButton(this.vista.btnJugadores, "imagenes/sobrenosotros.jpg"));
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
        
        
        this.vista.lblLogJugadores.setIcon(fotoEscalarLabel(this.vista.lblLogJugadores, "imagenes/logo.png"));
        this.vista.lblFondo_Pantalla_Jugadores.setIcon(fotoEscalarLabel(this.vista.lblFondo_Pantalla_Jugadores, "imagenes/fondo-principal.jpg"));
        this.vista.lblSalir.setIcon(fotoEscalarLabel(this.vista.lblSalir,"imagenes/salir.png"));
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
        this.vista.lblSiguiente_Ronda.setIcon(fotoEscalarLabel(this.vista.lblSiguiente_Ronda,"imagenes/siguiete_jornada.png"));
        //panelInformacion
        this.vista.lblFondoInformacion.setIcon(fotoEscalarLabel(this.vista.lblFondoInformacion,"imagenes/fondo-principal.jpg"));
        this.vista.lblSalirMenu_Informacion.setIcon(fotoEscalarLabel(this.vista.lblSalirMenu_Informacion,"imagenes/volver.png"));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == this.vista.lblSalir) {
            this.vista.panelElecion.setVisible(false);
            
          
            JButton[] botonesHabilitar = {
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

            enableButtons(botonesHabilitar);
        }
        if(e.getSource()==this.vista.lblVolverJugadores) {
        	this.vista.panelJugadores.setVisible(false);
        	this.vista.panelMenu.setVisible(true);

        }
        else if(e.getSource()==this.vista.lblVolverPlantilla) {

        	this.vista.PanelPlantilla.setVisible(false);
        	this.vista.panelMenu.setVisible(true);
        }else if(e.getSource()==this.vista.lblVolverPlantilla_1) {
        	this.vista.panelVistaEquipo.setVisible(false);
        	this.vista.PanelPlantilla.setVisible(true);
        }else if(e.getSource()==this.vista.lblEmpezarSimulacion) {
        	this.vista.panelVistaEquipo.setVisible(false);
        	this.vista.panelSimulacion.setVisible(true);
        }else if(e.getSource()==this.vista.lblVolverPlantilla_Simulacion) {
        	this.vista.PanelPlantilla.setVisible(true);
        	this.vista.panelSimulacion.setVisible(false);
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
	
	//Tabla Jugadores
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
					String.valueOf(clave.getFuerzaTecnica())
			};
			
			modelo.addRow(row);
		}
	}

	
	
	
	public void cargarEquipos() {
		
		List<String> entrada=hibernate.extraerEquipos();
		
		for(String clave:entrada) {
			vista.comboBoxFiltroEquipo.addItem(clave);
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
	 public List<Jugador> crerPlantillaMaquina() {
	        listModelVisitante.clear(); 
	        List<Jugador> equipo=new ArrayList<Jugador>();
	        List<Jugador> temp;
	        
	        temp = hibernate.extraerJugadoresPorPosicion("POR");
	        Collections.shuffle(temp);
	        if (!temp.isEmpty()) {
	            listModelVisitante.addElement(temp.get(0).getNombre()); 
	        }  
	        
	        List<Jugador> defensas = hibernate.extraerJugadoresPorPosicion("DEF");
	        Collections.shuffle(defensas);
	        for (int i = 0; i < Math.min(4, defensas.size()); i++) {
	            listModelVisitante.addElement(defensas.get(i).getNombre());
	        }

	        List<Jugador> mediocampistas = hibernate.extraerJugadoresPorPosicion("MED");
	        Collections.shuffle(mediocampistas);
	        for (int i = 0; i < Math.min(4, mediocampistas.size()); i++) {
	            listModelVisitante.addElement(mediocampistas.get(i).getNombre()); 
	        }

	       
	        List<Jugador> delanteros = hibernate.extraerJugadoresPorPosicion("DEL");
	        Collections.shuffle(delanteros);
	        for (int i = 0; i < Math.min(2, delanteros.size()); i++) {
	            listModelVisitante.addElement(delanteros.get(i).getNombre()); 
	        }

	       return equipo;
	}
	public void asignarJugadoresEquipo(Equipo equipo,List<Jugador> jugadores) {
		
		for(Jugador clave:jugadores) {
			clave.setEquipo(equipo);
		}
		
	}
	public void creacionTotalEquipo() {
		List<Jugador> temp=crerPlantillaMaquina();
		Equipo equipo=hibernate.crearEquipo();
		asignarJugadoresEquipo(equipo, temp);
		hibernate.asignarEquipoUpdate(temp);
	}
	 
}