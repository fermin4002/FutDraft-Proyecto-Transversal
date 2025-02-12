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
	public JButton btnClasificaion;
	public JPanel panelMenu;
	public JLabel lblLogJugadores;
	public JTable tablaJugadores;
	public JLabel lblVolverJugadores;
	public JLabel lblNewLabel;
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
		   
		   panelJugadores = new JPanel();
		   panelJugadores.setBounds(0, 0, 1329, 761);
		   panelJugadores.setVisible(false);
		   
		    PanelPlantilla = new JPanel();
		    PanelPlantilla.setBounds(0, 0, 1329, 742);
		    PanelPlantilla.setVisible(false);
		      
		      panelClasificacion = new JPanel();
		      panelClasificacion.setLayout(null);
		      panelClasificacion.setVisible(false);
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
		      
		      panelMenu = new JPanel();
		      panelMenu.setBounds(0, 0, 1329, 742);
		      contentPane.add(panelMenu);
		      panelMenu.setVisible(false);
		      panelMenu.setLayout(null);
		      
		      btnClasificaion = new JButton("");
		      btnClasificaion.setBounds(459, 597, 500, 113);
		      panelMenu.add(btnClasificaion);
		      
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
		      btnEleccionCinco.setBounds(359, 29, 118, 285);
		      panelElecion.add(btnEleccionCinco);
		      
		      lblSalir = new JLabel("");
		      lblSalir.setBounds(799, 0, 45, 31);
		      panelElecion.add(lblSalir);
		      
		       btnEleccionUno = new JButton("");
		       btnEleccionUno.addActionListener(new ActionListener() {
		       	public void actionPerformed(ActionEvent e) {
		       	}
		       });
		       btnEleccionUno.setBounds(41, 29, 116, 285);
		       panelElecion.add(btnEleccionUno);
		       
		        btnEleccionDos = new JButton("");
		        btnEleccionDos.addActionListener(new ActionListener() {
		        	public void actionPerformed(ActionEvent e) {
		        	}
		        });
		        btnEleccionDos.setBounds(196, 29, 117, 285);
		        panelElecion.add(btnEleccionDos);
		        
		         btnEleccionTres = new JButton("");
		         btnEleccionTres.setBounds(525, 29, 118, 285);
		         panelElecion.add(btnEleccionTres);
		         
		          btnEleccionCuatro = new JButton("");
		          btnEleccionCuatro.setBounds(684, 29, 116, 285);
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
		   contentPane.add(panelJugadores);
		   panelJugadores.setLayout(null);
		   
		   lblLogJugadores = new JLabel("");
		   lblLogJugadores.setBounds(250, 1, 251, 146);
		   panelJugadores.add(lblLogJugadores);
		   
		    lblNewLabel = new JLabel("Jugadores");
		    lblNewLabel.setForeground(new Color(255, 255, 255));
		   lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 45));
		   lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		   lblNewLabel.setBounds(603, 0, 487, 147);
		   panelJugadores.add(lblNewLabel);
		   
		   scrollPane = new JScrollPane();
		   scrollPane.setBounds(250, 157, 840, 549);
		   panelJugadores.add(scrollPane);
		   
		   tablaJugadores = new JTable();
		   scrollPane.setViewportView(tablaJugadores);
		   
		   lblVolverJugadores = new JLabel("");
		   lblVolverJugadores.setBounds(0, 1, 82, 79);
		   panelJugadores.add(lblVolverJugadores);
		   
		   lblFondo_Pantalla_Jugadores = new JLabel("");
		   lblFondo_Pantalla_Jugadores.setBounds(0, 0, 1329, 751);
		   panelJugadores.add(lblFondo_Pantalla_Jugadores);
	}
	
	public static ImageIcon ajustarTamañoImg(String ruta, int ancho, int alto) {
        ImageIcon imagen = new ImageIcon((ruta));
        Image imagenOriginal = imagen.getImage();
        Image imagenAjustada = imagenOriginal.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH);
        return new ImageIcon(imagenAjustada);
    }//FIN AJUSTAR TAMAÑO IMG
}
