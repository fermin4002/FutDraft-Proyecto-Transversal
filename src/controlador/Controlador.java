package controlador;

import vista.*;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import net.bytebuddy.asm.Advice.This;
import persistencias.Jugador;

public class Controlador implements ActionListener,MouseListener {

    private Vista vista;
    private ControladorHibernate hibernate;
    private DefaultTableModel modeloTJugadores;
    public Controlador(Vista vista) {
        this.vista = vista;
        
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
        this.vista.btnJugar.addActionListener(this);
        this.vista.btnSalir.addActionListener(this);
        this.vista.lblSalir.addMouseListener(this);
        this.vista.btnSimularPartida.addActionListener(this);
        this.vista.btnJugadores.addActionListener(this);
        this.vista.lblVolverJugadores.addMouseListener(this);
        this.vista.lblVolverPlantilla.addMouseListener(this);
       //Ver equipos
        this.vista.lblVolverPlantilla_1.addMouseListener(this);
        //Ver simulacion
        this.vista.lblEmpezarSimulacion.addMouseListener(this);
        //MOdelos
        modeloTJugadores= new DefaultTableModel();
        modeloTJugadores.addColumn("Nombre");
        modeloTJugadores.addColumn("Equipo");
        modeloTJugadores.addColumn("Posicion");
        modeloTJugadores.addColumn("F. Ataque");
        modeloTJugadores.addColumn("F. Defensa");
        modeloTJugadores.addColumn("F. Tecnica");
        modeloTJugadores.addColumn("F. Portero");
        
        vista.tablaJugadores.setModel(modeloTJugadores);
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
        cargarTabla();
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
        } else if (isPlayerButton(e.getSource())) {
            vista.panelElecion.setVisible(true);
            disableButtons(botonesDeshabilitar);
        } else if (isEleccionButton(e.getSource())) {
            vista.panelElecion.setVisible(false);
            enableButtons(botonesDeshabilitar);
        }
        
        if(e.getSource()==this.vista.lblFondoDraft) {
        	vista.panelElecion.setVisible(false);
        }
        if(e.getSource()==this.vista.btnJugar) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.PanelPlantilla.setVisible(true);
        }
        if(e.getSource()==this.vista.btnSalir) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelInicio.setVisible(true);
        }
        if(e.getSource()==this.vista.btnJugadores) {
        	this.vista.panelMenu.setVisible(false);
        	this.vista.panelJugadores.setVisible(true);
        }
        if(e.getSource()==this.vista.btnSimularPartida) {
        	this.vista.PanelPlantilla.setVisible(false);
        	this.vista.panelVistaEquipo.setVisible(true);
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
               source == this.vista.btnEleccionCuatro;
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
        this.vista.lblFondo.setIcon(fotoEscalarLabel(this.vista.lblFondo, "imagenes/fondo-principal.jpg"));
        this.vista.lblFondoMenu.setIcon(fotoEscalarLabel(this.vista.lblFondoMenu, "imagenes/fondo-principal.jpg"));
        this.vista.lblLogo.setIcon(fotoEscalarLabel(this.vista.lblLogo, "imagenes/logo.png"));
        this.vista.lblLogoMenu.setIcon(fotoEscalarLabel(this.vista.lblLogoMenu, "imagenes/logo.png"));
        this.vista.lblFondoPlantilla.setIcon(fotoEscalarLabel(this.vista.lblFondoPlantilla, "imagenes/cesped.png"));
        this.vista.btnEmpezar.setIcon(fotoEscalarButton(this.vista.btnEmpezar, "imagenes/boton-inicio.png"));
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
        this.vista.btnSimularPartida.setIcon(fotoEscalarButton(this.vista.btnSimularPartida,"imagenes/enfentramiento.png"));
        this.vista.btnJugar.setIcon(fotoEscalarButton(this.vista.btnJugar, "imagenes/boton-jugar.png"));
        this.vista.btnJugadores.setIcon(fotoEscalarButton(this.vista.btnJugadores, "imagenes/sobrenosotros.jpg"));
        this.vista.btnSalir.setIcon(fotoEscalarButton(this.vista.btnSalir, "imagenes/boton-salir.png"));
        this.vista.lblFondoDraft.setIcon(fotoEscalarLabel(this.vista.lblFondoDraft, "imagenes/fondo_futDraft.jpg"));
        this.vista.lblVolverJugadores.setIcon(fotoEscalarLabel(this.vista.lblVolverJugadores, "imagenes/volver.png"));
        this.vista.lblVolverPlantilla.setIcon(fotoEscalarLabel(this.vista.lblVolverPlantilla, "imagenes/volver.png"));
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
        this.vista.lblVolverPlantilla_2.setIcon(fotoEscalarLabel(this.vista.lblVolverPlantilla_2,"imagenes/volver.png"));
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
        if(e.getSource()==this.vista.lblVolverPlantilla) {
        	this.vista.PanelPlantilla.setVisible(false);
        	this.vista.panelMenu.setVisible(true);
        }if(e.getSource()==this.vista.lblVolverPlantilla_1) {
        	this.vista.panelVistaEquipo.setVisible(false);
        	this.vista.PanelPlantilla.setVisible(true);
        }if(e.getSource()==this.vista.lblEmpezarSimulacion) {
        	this.vista.PanelPlantilla.setVisible(false);
        	this.vista.panelSimulacion.setVisible(true);
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
	public void cargarTabla() {
		List<Jugador> entrada=hibernate.extraerJugadores();
		
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
			
			modeloTJugadores.addRow(row);
		}
	}
}