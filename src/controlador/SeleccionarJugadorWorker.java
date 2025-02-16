package controlador;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

import persistencias.Jugador;

public class SeleccionarJugadorWorker extends SwingWorker<Void, Void> {
    private Jugador jugador;
    private JLabel lblNombre;
    private JButton btnJugador;
    private Controlador controlador;

    public SeleccionarJugadorWorker(Jugador jugador, JLabel lblNombre, JButton btnJugador, Controlador controlador) {
        this.jugador = jugador;
        this.lblNombre = lblNombre;
        this.btnJugador = btnJugador;
        this.controlador = controlador;
    }


    @Override
    protected void done() {
        if (jugador != null) {
          
            if (jugador.getPosicion().equals("POR")) {
                
                lblNombre.setText(jugador.getNombre());
                btnJugador.setVisible(false);
                btnJugador.setEnabled(false);
                btnJugador.setIcon(controlador.fotoEscalarButton(btnJugador, "")); 
            } else if (jugador.getPosicion().equals("DEF")) {
              
                lblNombre.setText(jugador.getNombre());
                btnJugador.setVisible(false);
                btnJugador.setEnabled(false);
                btnJugador.setIcon(controlador.fotoEscalarButton(btnJugador, ""));
            } else if (jugador.getPosicion().equals("MED")) {
              
                lblNombre.setText(jugador.getNombre());
                btnJugador.setVisible(false);
                btnJugador.setEnabled(false);
                btnJugador.setIcon(controlador.fotoEscalarButton(btnJugador, ""));
            } else if (jugador.getPosicion().equals("DEL")) {
         
                lblNombre.setText(jugador.getNombre());
                btnJugador.setVisible(false);
                btnJugador.setEnabled(false);
                btnJugador.setIcon(controlador.fotoEscalarButton(btnJugador, ""));
            }
            controlador.jugadoresEquipoLocalSeleccionados++; 
            controlador.verificarJugadoresSeleccionados(); 
        }
    }


	@Override
	protected Void doInBackground() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}