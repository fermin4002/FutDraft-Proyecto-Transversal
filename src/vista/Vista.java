package vista;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

import controlador.*;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;

import javax.swing.border.LineBorder;
import javax.swing.JComboBox;

import javax.swing.JList;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;

import javax.swing.JTextField;
import javax.swing.JSpinner;


import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSeparator;
import javax.swing.JToggleButton;


public class Vista extends JFrame {

	public static final long serialVersionUID = 1L;
	public  JPanel contentPane;
	public JPanel panelInicio;
	public JLabel lblLogo;
	public JLabel lblFondo;
	public JLabel lblTexto;
	public JButton btnEmpezar;
	public JPanel PanelPlantilla;
	public JButton btnDelanteroDerecho;
	public JButton btnCentroCampistaDerecho;
	public JButton btnCentroCampistaDerechoCentro;
	public JButton btnCentroCampistaIzquierdoCentro;
	public JButton btnCentroCampistaIzquierdo;
	public JButton btnDefensaDerecha;
	public JButton btnDefensaDerechaCentro;
	public JButton btnDefensaIzquierdaCentro;
	public JButton btnDefensaIzquierda;
	public JButton btnPortero;
	public JButton btnDelanteroIzquierda;
	public JPanel panelElecion;
	public JButton btnEleccionUno;
	public JButton btnEleccionDos;
	public JButton btnEleccionTres;
	public JButton btnEleccionCuatro;
	public JLabel lblFondoPlantilla;
	public JLabel lblFondoDraft;
	public JLabel lblSalir;
	public Object lblSaliraddMouseLis;
	public JButton btnSimularPartida;
	public JButton btnEleccionCinco;
	public JLabel lblFondoMenu;
	public JLabel lblLogoMenu;
	public JButton btnJugar;
	public JButton btnJugadores;



	public JButton btnClasificaciones;
	public JPanel panelMenu;
	public JLabel lblLogJugadores;
	public JTable tablaJugadores;
	public JLabel lblVolverJugadores;
	public JPanel panelJugadores;
	public JLabel lblFondo_Pantalla_Jugadores;
	public JLabel lblVolverPlantilla;
	public JScrollPane scrollPane;

	public JPanel panelClasificacion;
	public JLabel lblFondo_1;
	public JTable tablaClasificacion;
	public JTable tablaJornadas;
	public JComboBox comboBoxJornada;
	public JPanel panelResultados;
	public JLabel lblVolverClasificacion;


	public JPanel panelVistaEquipo;
	public JLabel lblNewLabel_EscudoEquipoLocal;
	public JLabel lblNewLabel_EquipoVisitante;
	public JList listEquipoVisitante;
	public JList listEquipoLocal;
	public JLabel lblVolverPlantilla_1;
	public JLabel lblFtotoVersus;
	public JLabel lblEmpezarSimulacion;
	public JLabel lblFondoPantalla_2;
	public JLabel lblFondeSimulacion;
	public JPanel panelFIltroJugadores;
	public JComboBox comboBoxFiltroPosicion;
	public JComboBox comboBoxFiltroEquipo;
	public JButton btnFIltrar;
	public JPanel panel_1;
	public JLabel lblAtaqueFIltro;
	public JLabel lblAtaqueMinFiltro;
	public JLabel lblAtaqueMaxFiltro;
	public JSpinner spinnerMinAtaque;
	public JSpinner spinnerMaxAtaque;
	public JPanel panel_2;
	public JLabel lblTecnicaFIltro;
	public JLabel lblTecnicaMinFiltro;
	public JLabel lblTecnicaMaxFiltro;
	public JSpinner spinnerMinTecnica;
	public JSpinner spinnerMaxTecnica;
	public JPanel panel_3;
	public JLabel lblDefensaFiltro;
	public JLabel lblDefensaMinFiltro;
	public JLabel lblDefensaMaxFiltro;
	public JSpinner spinnerMinDefensa;
	public JSpinner spinnerMaxDefensa;
	public JPanel panel_4;
	public JLabel lblPorteroFIltro;
	public JLabel lblProteroMinFiltro;
	public JLabel lblProteroMaxFiltro;
	public JSpinner spinnerMinPortero;
	public JSpinner spinnerMaxPortero;
	public JPanel panel_5;
	public JLabel lblPosicionFIltro;
	public JLabel lblEquipoFIltro;
	public JLabel lblFIltros;

	public JLabel lblVolverPlantilla_Simulacion;
	public JPanel panelSimulacion;
	public JList listSimulacion;
	public JLabel lblNewLabel_1;
	public JLabel lblNewLabel_EscudoEquipoLocal_Simulacion;
	public JLabel lblNewLabel_EscudoEquipoVisitante_Simulacion;
	public JLabel lblFtotoVersus_1;
	public JButton btnIniciarSimulacion;
	public JLabel lblFondo_Marcador;
	public JLabel lblMarcador;
	public JLabel lblSiguiente_Ronda;
	public JLabel lblSalirPrincipal;
	public JLabel lblInformacion;
	public JPanel panelMostrarInformacion;
	public JLabel lblTextoSobreNosotros;
	public JLabel lblDescripcionSobreNosotros;
	public JLabel lblFondoInformacion;
	public JLabel lblSalirMenu;
	public JPanel panelInformacion;
	public JLabel lblSalirMenu_Informacion;
	public JPanel panelMostrarInformacionDedicamos;
	public JLabel lblTextoTituloDedicamos;
	public JLabel lblDescripcionDedicamos;
	public JSeparator separator_1;
	public JPanel panelMostrarInformacionDedicamos_1;
	public JLabel lblTextoComoJugar;
	public JLabel lblDescripcionComoJugar;
	public JSeparator separator_2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Vista vista = new Vista();
					Controlador controlador=new Controlador(vista);

					vista.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Vista() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1337, 773);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		  ImageIcon imagenApp = (ajustarTamañoImg("imagenes/logo.png", 64, 64));
		  setTitle("DreamDraft");
	       setIconImage(imagenApp.getImage()); 
	       setLocationRelativeTo(null);
	       setResizable(false); 
	        
		setContentPane(contentPane);
		 contentPane.setLayout(null);

		      
		      panelClasificacion = new JPanel();
		      panelClasificacion.setLayout(null);
		      panelClasificacion.setVisible(false);
		        
		        panelJugadores = new JPanel();
		        panelJugadores.setBounds(0, 0, 1329, 761);
		        panelJugadores.setVisible(false);
		        
		         panelInicio = new JPanel();
		         panelInicio.setBounds(0, 0, 1329, 742);
		         
		          contentPane.add(panelInicio);
		          panelInicio.setLayout(null);
		          
		          lblTexto = new JLabel("Comienza");
		          lblTexto.setFont(new Font("Consolas", Font.BOLD, 45));
		          lblTexto.setBounds(470, 547, 281, 78);
		          panelInicio.add(lblTexto);
		          
		          btnEmpezar = new JButton("");
		          btnEmpezar.setBounds(741, 530, 193, 106);
		          btnEmpezar.setContentAreaFilled(false); 
		          btnEmpezar.setBorderPainted(false); 
		          btnEmpezar.setFocusPainted(false);
		          btnEmpezar.setOpaque(false); 
		          panelInicio.add(btnEmpezar);
		          
		           lblLogo = new JLabel("");
		           lblLogo.setBounds(450, 98, 500, 338);
		           panelInicio.add(lblLogo);
		           
		            lblFondo = new JLabel("");
		            lblFondo.setBounds(0, 0, 1329, 825);
		            panelInicio.add(lblFondo);
		        contentPane.add(panelJugadores);
		        panelJugadores.setLayout(null);
		        
		        panelFIltroJugadores = new JPanel();
		        panelFIltroJugadores.setBackground(new Color(255, 255, 255));
		        panelFIltroJugadores.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		        panelFIltroJugadores.setBounds(890, 158, 403, 549);
		        panelJugadores.add(panelFIltroJugadores);
		        panelFIltroJugadores.setLayout(null);
		        
		        btnFIltrar = new JButton("Filtrar");
		        btnFIltrar.setBounds(10, 477, 383, 44);
		        panelFIltroJugadores.add(btnFIltrar);
		        
		        panel_1 = new JPanel();
		        panel_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		        panel_1.setBackground(new Color(255, 255, 255));
		        panel_1.setBounds(10, 193, 383, 60);
		        panelFIltroJugadores.add(panel_1);
		        panel_1.setLayout(null);
		        
		        lblAtaqueFIltro = new JLabel("Fuerza Ataque");
		        lblAtaqueFIltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblAtaqueFIltro.setBounds(10, 11, 106, 35);
		        panel_1.add(lblAtaqueFIltro);
		        
		        lblAtaqueMinFiltro = new JLabel("Min");
		        lblAtaqueMinFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblAtaqueMinFiltro.setBounds(151, 11, 30, 35);
		        panel_1.add(lblAtaqueMinFiltro);
		        
		        lblAtaqueMaxFiltro = new JLabel("Max");
		        lblAtaqueMaxFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblAtaqueMaxFiltro.setBounds(260, 11, 30, 35);
		        panel_1.add(lblAtaqueMaxFiltro);
		        
		        spinnerMinAtaque = new JSpinner();
		        spinnerMinAtaque.setBounds(185, 10, 55, 38);
		        panel_1.add(spinnerMinAtaque);
		        
		        spinnerMaxAtaque = new JSpinner();
		        spinnerMaxAtaque.setBounds(290, 11, 55, 38);
		        panel_1.add(spinnerMaxAtaque);
		        	
		        panel_2 = new JPanel();
		        panel_2.setLayout(null);
		        panel_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		        panel_2.setBackground(Color.WHITE);
		        panel_2.setBounds(10, 264, 383, 60);
		        panelFIltroJugadores.add(panel_2);
		        
		        lblTecnicaFIltro = new JLabel("Fuerza Tecnica");
		        lblTecnicaFIltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblTecnicaFIltro.setBounds(10, 11, 106, 35);
		        panel_2.add(lblTecnicaFIltro);
		        
		        lblTecnicaMinFiltro = new JLabel("Min");
		        lblTecnicaMinFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblTecnicaMinFiltro.setBounds(151, 11, 30, 35);
		        panel_2.add(lblTecnicaMinFiltro);
		        
		        lblTecnicaMaxFiltro = new JLabel("Max");
		        lblTecnicaMaxFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblTecnicaMaxFiltro.setBounds(260, 11, 30, 35);
		        panel_2.add(lblTecnicaMaxFiltro);
		        
		        spinnerMinTecnica = new JSpinner();
		        spinnerMinTecnica.setBounds(185, 10, 55, 38);
		        panel_2.add(spinnerMinTecnica);
		        
		        spinnerMaxTecnica = new JSpinner();
		        spinnerMaxTecnica.setBounds(290, 11, 55, 38);
		        panel_2.add(spinnerMaxTecnica);
		        
		        panel_3 = new JPanel();
		        panel_3.setLayout(null);
		        panel_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		        panel_3.setBackground(Color.WHITE);
		        panel_3.setBounds(10, 335, 383, 60);
		        panelFIltroJugadores.add(panel_3);
		        
		        lblDefensaFiltro = new JLabel("Fuerza Defensa");
		        lblDefensaFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblDefensaFiltro.setBounds(10, 11, 106, 35);
		        panel_3.add(lblDefensaFiltro);
		        
		        lblDefensaMinFiltro = new JLabel("Min");
		        lblDefensaMinFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblDefensaMinFiltro.setBounds(151, 11, 30, 35);
		        panel_3.add(lblDefensaMinFiltro);
		        
		        lblDefensaMaxFiltro = new JLabel("Max");
		        lblDefensaMaxFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblDefensaMaxFiltro.setBounds(260, 11, 30, 35);
		        panel_3.add(lblDefensaMaxFiltro);
		        
		        spinnerMinDefensa = new JSpinner();
		        spinnerMinDefensa.setBounds(185, 10, 55, 38);
		        panel_3.add(spinnerMinDefensa);
		        
		        spinnerMaxDefensa = new JSpinner();
		        spinnerMaxDefensa.setBounds(290, 11, 55, 38);
		        panel_3.add(spinnerMaxDefensa);
		        
		        panel_4 = new JPanel();
		        panel_4.setLayout(null);
		        panel_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		        panel_4.setBackground(Color.WHITE);
		        panel_4.setBounds(10, 406, 383, 60);
		        panelFIltroJugadores.add(panel_4);
		        
		        lblPorteroFIltro = new JLabel("Fuerza Portero");
		        lblPorteroFIltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblPorteroFIltro.setBounds(10, 11, 106, 35);
		        panel_4.add(lblPorteroFIltro);
		        
		        lblProteroMinFiltro = new JLabel("Min");
		        lblProteroMinFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblProteroMinFiltro.setBounds(151, 11, 30, 35);
		        panel_4.add(lblProteroMinFiltro);
		        
		        lblProteroMaxFiltro = new JLabel("Max");
		        lblProteroMaxFiltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblProteroMaxFiltro.setBounds(260, 11, 30, 35);
		        panel_4.add(lblProteroMaxFiltro);
		        
		        spinnerMinPortero = new JSpinner();
		        spinnerMinPortero.setBounds(185, 10, 55, 38);
		        panel_4.add(spinnerMinPortero);
		        
		        spinnerMaxPortero = new JSpinner();
		        spinnerMaxPortero.setBounds(290, 11, 55, 38);
		        panel_4.add(spinnerMaxPortero);
		        
		        panel_5 = new JPanel();
		        panel_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		        panel_5.setBackground(new Color(255, 255, 255));
		        panel_5.setBounds(10, 66, 383, 116);
		        panelFIltroJugadores.add(panel_5);
		        panel_5.setLayout(null);
		        
		        comboBoxFiltroEquipo = new JComboBox();
		        comboBoxFiltroEquipo.setBounds(215, 61, 158, 44);
		        panel_5.add(comboBoxFiltroEquipo);
		        
		        comboBoxFiltroPosicion = new JComboBox();
		        comboBoxFiltroPosicion.setBounds(10, 61, 158, 44);
		        panel_5.add(comboBoxFiltroPosicion);
		        
		        lblPosicionFIltro = new JLabel("Posicion");
		        lblPosicionFIltro.setHorizontalAlignment(SwingConstants.CENTER);
		        lblPosicionFIltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblPosicionFIltro.setBounds(10, 15, 158, 35);
		        panel_5.add(lblPosicionFIltro);
		        
		        lblEquipoFIltro = new JLabel("Equipo");
		        lblEquipoFIltro.setHorizontalAlignment(SwingConstants.CENTER);
		        lblEquipoFIltro.setFont(new Font("Tahoma", Font.BOLD, 13));
		        lblEquipoFIltro.setBounds(215, 15, 158, 35);
		        panel_5.add(lblEquipoFIltro);
		        
		        lblFIltros = new JLabel("Filtros");
		        lblFIltros.setHorizontalAlignment(SwingConstants.CENTER);
		        lblFIltros.setFont(new Font("Tahoma", Font.BOLD, 33));
		        lblFIltros.setBounds(10, 11, 383, 44);
		        panelFIltroJugadores.add(lblFIltros);
		        
		        lblLogJugadores = new JLabel("");
		        lblLogJugadores.setBounds(250, 1, 251, 146);
		        panelJugadores.add(lblLogJugadores);		        
		         
		         scrollPane = new JScrollPane();
		         scrollPane.setBounds(30, 158, 840, 549);
		         panelJugadores.add(scrollPane);
		         
		         tablaJugadores = new JTable();
		         scrollPane.setViewportView(tablaJugadores);
		         
		         lblVolverJugadores = new JLabel("");
		         lblVolverJugadores.setBounds(0, 1, 82, 79);
		         panelJugadores.add(lblVolverJugadores);
		         
		         lblFondo_Pantalla_Jugadores = new JLabel("");
		         lblFondo_Pantalla_Jugadores.setBounds(0, 0, 1329, 751);
		         panelJugadores.add(lblFondo_Pantalla_Jugadores);
		      panelClasificacion.setBounds(0, 0, 1329, 742);
		      contentPane.add(panelClasificacion);
		      
		      lblVolverClasificacion = new JLabel("");
		      lblVolverClasificacion.setBounds(0, 0, 82, 79);
		      panelClasificacion.add(lblVolverClasificacion);
		      
		      panelResultados = new JPanel();
		      panelResultados.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		      panelResultados.setBounds(463, 84, 443, 346);
		      panelClasificacion.add(panelResultados);
		      panelResultados.setLayout(null);
		      
		      tablaJornadas = new JTable();
		      tablaJornadas.setBounds(10, 61, 423, 275);
		      panelResultados.add(tablaJornadas);
		      
		      comboBoxJornada = new JComboBox();
		      comboBoxJornada.setBounds(226, 11, 207, 47);
		      panelResultados.add(comboBoxJornada);
		      
		      JPanel panel = new JPanel();
		      panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		      panel.setBounds(10, 84, 443, 587);
		      panelClasificacion.add(panel);
		      panel.setLayout(null);
		      
		      tablaClasificacion = new JTable();
		      tablaClasificacion.setBounds(10, 63, 423, 513);
		      panel.add(tablaClasificacion);
		      
		      lblFondo_1 = new JLabel("");
		      lblFondo_1.setBounds(0, 0, 1329, 742);
		      panelClasificacion.add(lblFondo_1);

		        
		        panelVistaEquipo = new JPanel();
		        panelVistaEquipo.setBounds(0, 0, 1329, 736);
		        panelVistaEquipo.setVisible(false);
		        contentPane.add(panelVistaEquipo);
		        panelVistaEquipo.setLayout(null);
		        
		        lblEmpezarSimulacion = new JLabel("");
		        lblEmpezarSimulacion.setBounds(598, 499, 106, 90);
		        panelVistaEquipo.add(lblEmpezarSimulacion);
		        
		        lblNewLabel_EscudoEquipoLocal = new JLabel("");
		        lblNewLabel_EscudoEquipoLocal.setBounds(273, 93, 283, 226);
		        panelVistaEquipo.add(lblNewLabel_EscudoEquipoLocal);
		        
		        lblNewLabel_EquipoVisitante = new JLabel("");
		        lblNewLabel_EquipoVisitante.setBounds(749, 93, 283, 226);
		        panelVistaEquipo.add(lblNewLabel_EquipoVisitante);
		        
		        listEquipoVisitante = new JList();
		        listEquipoVisitante.setBorder(new CompoundBorder());
		        listEquipoVisitante.setBounds(749, 354, 283, 374);
		        panelVistaEquipo.add(listEquipoVisitante);
		        
		        listEquipoLocal = new JList();
		        listEquipoLocal.setBorder(new CompoundBorder());
		        listEquipoLocal.setBounds(273, 354, 283, 374);
		        panelVistaEquipo.add(listEquipoLocal);
		        
		        lblVolverPlantilla_1 = new JLabel("");
		        lblVolverPlantilla_1.setBounds(0, 0, 82, 79);
		        panelVistaEquipo.add(lblVolverPlantilla_1);
		        
		        lblFtotoVersus = new JLabel("");
		        lblFtotoVersus.setBounds(598, 190, 106, 90);
		        panelVistaEquipo.add(lblFtotoVersus);
		        
		        lblFondoPantalla_2 = new JLabel("");
		        lblFondoPantalla_2.setBounds(0, 0, 1352, 736);
		        panelVistaEquipo.add(lblFondoPantalla_2);
		        
		         panelElecion = new JPanel();
		         panelElecion.setBounds(249, 148, 844, 324);
		         panelElecion.setVisible(false);
		         
		          btnSimularPartida = new JButton("");

		          
		          panelSimulacion = new JPanel();
		          panelSimulacion.setBounds(0, 0, 1347, 736);
		          panelSimulacion.setVisible(false);
		          
		           PanelPlantilla = new JPanel();
		           PanelPlantilla.setBounds(0, 0, 1329, 742);
		           PanelPlantilla.setVisible(false);
		           contentPane.add(PanelPlantilla);
		           PanelPlantilla.setLayout(null);
		           
		            panelElecion = new JPanel();
		            panelElecion.setBounds(249, 148, 844, 324);
		            panelElecion.setVisible(false);
		            
		            lblVolverPlantilla = new JLabel("");
		            lblVolverPlantilla.setBounds(0, 0, 82, 79);
		            PanelPlantilla.add(lblVolverPlantilla);
		            
		             btnSimularPartida = new JButton("");
		             
		             btnSimularPartida.setBounds(1029, 622, 213, 91);
		             btnSimularPartida.setContentAreaFilled(false); 
		             btnSimularPartida.setBorderPainted(false); 
		             btnSimularPartida.setFocusPainted(false);
		             PanelPlantilla.add(btnSimularPartida);
		             PanelPlantilla.add(panelElecion);
		             panelElecion.setLayout(null);
		             
		             btnEleccionCinco = new JButton("");
		             btnEleccionCinco.setForeground(new Color(255, 255, 255));
		             btnEleccionCinco.setFont(new Font("Consolas", Font.BOLD, 10));
		             btnEleccionCinco.setBounds(359, 29, 118, 285);
		             btnEleccionCinco.setContentAreaFilled(false); 
		             btnEleccionCinco.setBorderPainted(false); 
		             panelElecion.add(btnEleccionCinco);
		             
		             lblSalir = new JLabel("");
		             lblSalir.setBounds(799, 0, 45, 31);
		             panelElecion.add(lblSalir);
		             
		              btnEleccionUno = new JButton("");
		              btnEleccionUno.setForeground(new Color(255, 255, 255));
		              btnEleccionUno.setFont(new Font("Consolas", Font.BOLD, 10));
		              btnEleccionUno.setContentAreaFilled(false); 
		              btnEleccionUno.setBorderPainted(false);
		              btnEleccionUno.setBounds(41, 29, 116, 285);
		              panelElecion.add(btnEleccionUno);
		              
		               btnEleccionDos = new JButton("");
		               btnEleccionDos.setForeground(new Color(255, 255, 255));
		               btnEleccionDos.setFont(new Font("Consolas", Font.BOLD, 10));
		               btnEleccionDos.setContentAreaFilled(false); 
		               btnEleccionDos.setBorderPainted(false);
		               btnEleccionDos.setBounds(196, 29, 117, 285);
		               panelElecion.add(btnEleccionDos);
		               

		                lblFondoPlantilla = new JLabel("");
		                lblFondoPlantilla.setBounds(0, 0, 1329, 764);
		                PanelPlantilla.add(lblFondoPlantilla);

		                btnEleccionTres = new JButton("");
		                btnEleccionTres.setForeground(new Color(255, 255, 255));
		                btnEleccionTres.setFont(new Font("Consolas", Font.BOLD, 10));
		                btnEleccionTres.setBounds(525, 29, 118, 285);
		                btnEleccionTres.setContentAreaFilled(false); 
		                btnEleccionTres.setBorderPainted(false);
		                panelElecion.add(btnEleccionTres);
		                
		                 btnEleccionCuatro = new JButton("");
		                 btnEleccionCuatro.setForeground(new Color(255, 255, 255));
		                 btnEleccionCuatro.setFont(new Font("Consolas", Font.BOLD, 10));
		                 btnEleccionCuatro.setBounds(684, 29, 116, 285);
		                 btnEleccionCuatro.setContentAreaFilled(false); 
		                 btnEleccionCuatro.setBorderPainted(false); 
		                 panelElecion.add(btnEleccionCuatro);
		                 
		                 lblFondoDraft = new JLabel("");
		                 lblFondoDraft.setBounds(0, 0, 844, 324);
		                 panelElecion.add(lblFondoDraft);
		                 
		                 btnPortero = new JButton("");
		                 btnPortero.setBounds(626, 622, 85, 91);
		                 btnPortero.setContentAreaFilled(false); 
		                 btnPortero.setBorderPainted(false); 
		                 btnPortero.setFocusPainted(false);
		                 PanelPlantilla.add(btnPortero);
		                 
		                 btnDefensaIzquierda = new JButton("");
		                 btnDefensaIzquierda.setBounds(870, 482, 85, 91);
		                 btnDefensaIzquierda.setContentAreaFilled(false); 
		                 btnDefensaIzquierda.setBorderPainted(false); 
		                 btnDefensaIzquierda.setFocusPainted(false);
		                 PanelPlantilla.add(btnDefensaIzquierda);
		                 
		                 btnCentroCampistaIzquierdoCentro = new JButton("");
		                 btnCentroCampistaIzquierdoCentro.setBounds(747, 343, 85, 91);
		                 btnCentroCampistaIzquierdoCentro.setContentAreaFilled(false); 
		                 btnCentroCampistaIzquierdoCentro.setBorderPainted(false); 
		                 btnCentroCampistaIzquierdoCentro.setFocusPainted(false);
		                 PanelPlantilla.add(btnCentroCampistaIzquierdoCentro);
		                 
		                 btnDefensaIzquierdaCentro = new JButton("");
		                 btnDefensaIzquierdaCentro.setBounds(747, 482, 85, 91);
		                 btnDefensaIzquierdaCentro.setContentAreaFilled(false); 
		                 btnDefensaIzquierdaCentro.setBorderPainted(false); 
		                 btnDefensaIzquierdaCentro.setFocusPainted(false);
		                 PanelPlantilla.add(btnDefensaIzquierdaCentro);
		                 
		                 btnDefensaDerechaCentro = new JButton("");
		                 btnDefensaDerechaCentro.setBounds(525, 482, 85, 91);
		                 btnDefensaDerechaCentro.setContentAreaFilled(false); 
		                 btnDefensaDerechaCentro.setBorderPainted(false); 
		                 btnDefensaDerechaCentro.setFocusPainted(false);
		                 PanelPlantilla.add(btnDefensaDerechaCentro);
		                 
		                 btnDefensaDerecha = new JButton("");
		                 btnDefensaDerecha.setBounds(403, 482, 85, 91);
		                 btnDefensaDerecha.setContentAreaFilled(false); 
		                 btnDefensaDerecha.setBorderPainted(false); 
		                 btnDefensaDerecha.setFocusPainted(false);
		                 PanelPlantilla.add(btnDefensaDerecha);
		                 
		                 btnCentroCampistaIzquierdo = new JButton("");
		                 btnCentroCampistaIzquierdo.setBounds(870, 343, 85, 91);
		                 btnCentroCampistaIzquierdo.setContentAreaFilled(false); 
		                 btnCentroCampistaIzquierdo.setBorderPainted(false); 
		                 btnCentroCampistaIzquierdo.setFocusPainted(false);
		                 PanelPlantilla.add(btnCentroCampistaIzquierdo);
		                 
		                 btnCentroCampistaDerechoCentro = new JButton("");
		                 btnCentroCampistaDerechoCentro.setBounds(525, 343, 85, 91);
		                 btnCentroCampistaDerechoCentro.setContentAreaFilled(false); 
		                 btnCentroCampistaDerechoCentro.setBorderPainted(false); 
		                 btnCentroCampistaDerechoCentro.setFocusPainted(false);
		                 PanelPlantilla.add(btnCentroCampistaDerechoCentro);
		                 
		                 btnCentroCampistaDerecho = new JButton("");
		                 btnCentroCampistaDerecho.setBounds(403, 343, 85, 91);
		                 btnCentroCampistaDerecho.setContentAreaFilled(false); 
		                 btnCentroCampistaDerecho.setBorderPainted(false); 
		                 btnCentroCampistaDerecho.setFocusPainted(false);
		                 PanelPlantilla.add(btnCentroCampistaDerecho);
		                 
		                 btnDelanteroDerecho = new JButton("");
		                 btnDelanteroDerecho.setBounds(735, 148, 85, 91);
		                 btnDelanteroDerecho.setContentAreaFilled(false); 
		                 btnDelanteroDerecho.setBorderPainted(false); 
		                 btnDelanteroDerecho.setFocusPainted(false);
		                 PanelPlantilla.add(btnDelanteroDerecho);
		                 
		                  btnDelanteroIzquierda = new JButton("");
		                  btnDelanteroIzquierda.setBounds(525, 148, 85, 91);
		                  btnDelanteroIzquierda.setContentAreaFilled(false); 
		                  btnDelanteroIzquierda.setBorderPainted(false); 
		                  btnDelanteroIzquierda.setFocusPainted(false);
		                  PanelPlantilla.add(btnDelanteroIzquierda);
		                  
		                   lblFondoPlantilla = new JLabel("");
		                   lblFondoPlantilla.setBounds(0, 0, 1329, 764);
		                   PanelPlantilla.add(lblFondoPlantilla);
		          
		          panelMenu = new JPanel();
		          panelMenu.setBounds(0, 0, 1329, 742);
		          contentPane.add(panelMenu);
		          panelMenu.setVisible(false);
		          panelMenu.setLayout(null);
		          
		          lblInformacion = new JLabel("");
		          lblInformacion.setBounds(1243, 0, 76, 71);
		          panelMenu.add(lblInformacion);
		          
		          lblSalirPrincipal = new JLabel("");
		          lblSalirPrincipal.setBounds(0, 0, 76, 71);
		          panelMenu.add(lblSalirPrincipal);
		          
		          btnClasificaciones = new JButton("");
		          btnClasificaciones.setBounds(459, 597, 500, 113);
		          panelMenu.add(btnClasificaciones);
		          
		          btnJugadores = new JButton("");
		          btnJugadores.setBounds(459, 474, 500, 113);
		          panelMenu.add(btnJugadores);
		          
		          btnJugar = new JButton("");
		          btnJugar.setBounds(459, 351, 500, 113);
		          panelMenu.add(btnJugar);
		          
		          lblLogoMenu = new JLabel("");
		          lblLogoMenu.setBounds(459, 0, 500, 338);
		          panelMenu.add(lblLogoMenu);
		          
		          lblFondoMenu = new JLabel("");
		          lblFondoMenu.setBounds(0, 0, 1339, 742);
		          panelMenu.add(lblFondoMenu);
		          
		          panelInformacion = new JPanel();
		          panelInformacion.setBounds(0, 0, 1321, 734);
		          contentPane.add(panelInformacion);
		          panelInformacion.setVisible(false);
		          panelInformacion.setLayout(null);
		          
		          panelMostrarInformacionDedicamos_1 = new JPanel();
		          panelMostrarInformacionDedicamos_1.setLayout(null);
		          panelMostrarInformacionDedicamos_1.setBounds(890, 131, 386, 480);
		          panelMostrarInformacionDedicamos_1.setVisible(false);
		          panelInformacion.add(panelMostrarInformacionDedicamos_1);
		          
		          lblTextoComoJugar = new JLabel("¿Como jugar?");
		          lblTextoComoJugar.setHorizontalAlignment(SwingConstants.CENTER);
		          lblTextoComoJugar.setForeground(Color.BLACK);
		          lblTextoComoJugar.setFont(new Font("Consolas", Font.BOLD, 30));
		          lblTextoComoJugar.setBounds(10, 11, 366, 61);
		          panelMostrarInformacionDedicamos_1.add(lblTextoComoJugar);
		          
		          lblDescripcionComoJugar = new JLabel("");
		          lblDescripcionComoJugar.setForeground(Color.BLACK);
		          lblDescripcionComoJugar.setFont(new Font("Consolas", Font.BOLD, 15));
		          lblDescripcionComoJugar.setBounds(10, 83, 366, 375);
		          panelMostrarInformacionDedicamos_1.add(lblDescripcionComoJugar);
		          
		          separator_2 = new JSeparator();
		          separator_2.setForeground(Color.BLACK);
		          separator_2.setBackground(Color.BLACK);
		          separator_2.setBounds(0, 83, 386, 18);
		          panelMostrarInformacionDedicamos_1.add(separator_2);
		          
		          panelMostrarInformacionDedicamos = new JPanel();
		          panelMostrarInformacionDedicamos.setLayout(null);
		          panelMostrarInformacionDedicamos.setBounds(473, 131, 386, 480);
		          panelMostrarInformacionDedicamos.setVisible(false);
		          panelInformacion.add(panelMostrarInformacionDedicamos);
		          
		          lblTextoTituloDedicamos = new JLabel("¿A que nos dedicamos?");
		          lblTextoTituloDedicamos.setHorizontalAlignment(SwingConstants.CENTER);
		          lblTextoTituloDedicamos.setForeground(Color.BLACK);
		          lblTextoTituloDedicamos.setFont(new Font("Consolas", Font.BOLD, 30));
		          lblTextoTituloDedicamos.setBounds(10, 11, 366, 61);
		          panelMostrarInformacionDedicamos.add(lblTextoTituloDedicamos);
		          
		          lblDescripcionDedicamos = new JLabel("");
		          lblDescripcionDedicamos.setForeground(Color.BLACK);
		          lblDescripcionDedicamos.setFont(new Font("Consolas", Font.BOLD, 15));
		          lblDescripcionDedicamos.setBounds(10, 83, 366, 375);
		          panelMostrarInformacionDedicamos.add(lblDescripcionDedicamos);
		          
		          separator_1 = new JSeparator();
		          separator_1.setForeground(Color.BLACK);
		          separator_1.setBackground(Color.BLACK);
		          separator_1.setBounds(0, 83, 386, 18);
		          panelMostrarInformacionDedicamos.add(separator_1);
		          
		          panelMostrarInformacion = new JPanel();
		          panelMostrarInformacion.setBounds(56, 131, 386, 480);
		          panelInformacion.add(panelMostrarInformacion);
		          panelMostrarInformacion.setVisible(false);
		          panelMostrarInformacion.setLayout(null);
		          
		          lblTextoSobreNosotros = new JLabel("Sobre Nosotros");
		          lblTextoSobreNosotros.setForeground(new Color(0, 0, 0));
		          lblTextoSobreNosotros.setHorizontalAlignment(SwingConstants.CENTER);
		          lblTextoSobreNosotros.setFont(new Font("Consolas", Font.BOLD, 30));
		          lblTextoSobreNosotros.setBounds(10, 11, 366, 61);
		          panelMostrarInformacion.add(lblTextoSobreNosotros);
		          
		           lblDescripcionSobreNosotros = new JLabel("");
		           lblDescripcionSobreNosotros.setForeground(new Color(0, 0, 0));
		           lblDescripcionSobreNosotros.setFont(new Font("Consolas", Font.BOLD, 15));
		           lblDescripcionSobreNosotros.setBounds(10, 94, 366, 375);
		           panelMostrarInformacion.add(lblDescripcionSobreNosotros);
		           
		           JSeparator separator = new JSeparator();
		           separator.setBackground(new Color(0, 0, 0));
		           separator.setForeground(new Color(0, 0, 0));
		           separator.setBounds(0, 83, 386, 18);
		           panelMostrarInformacion.add(separator);
		           
		           lblSalirMenu_Informacion = new JLabel("");
		           lblSalirMenu_Informacion.setBounds(0, 0, 76, 71);
		           panelInformacion.add(lblSalirMenu_Informacion);
		           
		            lblFondoInformacion = new JLabel("");
		            lblFondoInformacion.setBounds(0, 0, 1331, 745);
		            panelInformacion.add(lblFondoInformacion);
		          contentPane.add(panelSimulacion);
		          panelSimulacion.setLayout(null);
		          
		          lblSiguiente_Ronda = new JLabel("");
		          lblSiguiente_Ronda.setBounds(1244, 0, 82, 79);
		          panelSimulacion.add(lblSiguiente_Ronda);
		          
		          lblMarcador = new JLabel("");
		          lblMarcador.setBackground(new Color(255, 255, 255));
		          lblMarcador.setBounds(601, 485, 671, 241);
		          panelSimulacion.add(lblMarcador);
		          
		          lblFondo_Marcador = new JLabel("");
		          lblFondo_Marcador.setBounds(601, 485, 671, 241);
		          panelSimulacion.add(lblFondo_Marcador);
		          
		          btnIniciarSimulacion = new JButton("");
		          btnIniciarSimulacion.setContentAreaFilled(false); // Quita el fondo
		          btnIniciarSimulacion.setBorderPainted(false); // Quita el borde
		          btnIniciarSimulacion.setFocusPainted(false); //
		          btnIniciarSimulacion.setBounds(791, 307, 298, 168);
		          panelSimulacion.add(btnIniciarSimulacion);
		          
		          lblFtotoVersus_1 = new JLabel("");
		          lblFtotoVersus_1.setBounds(870, 169, 106, 90);
		          panelSimulacion.add(lblFtotoVersus_1);
		          
		          lblNewLabel_EscudoEquipoVisitante_Simulacion = new JLabel("");
		          lblNewLabel_EscudoEquipoVisitante_Simulacion.setBounds(1024, 106, 283, 226);
		          panelSimulacion.add(lblNewLabel_EscudoEquipoVisitante_Simulacion);
		          
		          lblNewLabel_EscudoEquipoLocal_Simulacion = new JLabel("");
		          lblNewLabel_EscudoEquipoLocal_Simulacion.setBounds(556, 106, 283, 226);
		          panelSimulacion.add(lblNewLabel_EscudoEquipoLocal_Simulacion);
		          
		          lblNewLabel_1 = new JLabel("Situacion del partitido");
		          lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		          lblNewLabel_1.setFont(new Font("Consolas", Font.BOLD, 17));
		          lblNewLabel_1.setForeground(new Color(255, 255, 255));
		          lblNewLabel_1.setBounds(135, 87, 291, 74);
		          panelSimulacion.add(lblNewLabel_1);
		          
		          listSimulacion = new JList();
		          listSimulacion.setBounds(135, 169, 290, 557);
		          panelSimulacion.add(listSimulacion);
		          
		          lblVolverPlantilla_Simulacion = new JLabel("");
		          lblVolverPlantilla_Simulacion.setBounds(10, 0, 82, 79);
		          panelSimulacion.add(lblVolverPlantilla_Simulacion);
		          
		          lblFondeSimulacion = new JLabel("");
		          lblFondeSimulacion.setBounds(0, 0, 1326, 736);
		          panelSimulacion.add(lblFondeSimulacion);

	}
	
	public static ImageIcon ajustarTamañoImg(String ruta, int ancho, int alto) {
        ImageIcon imagen = new ImageIcon((ruta));
        Image imagenOriginal = imagen.getImage();
        Image imagenAjustada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenAjustada);
    }//FIN AJUSTAR TAMAÑO IMG
}
