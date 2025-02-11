package controlador;

import javax.swing.*;
import java.util.Random;

class PartidoFutbol extends Thread {
    private String equipoLocal;
    private String equipoVisitante;
    private int golesLocal;
    private int golesVisitante;
    private Random random;
    private JLabel lblMinutos;
    private JLabel lblResultado;
    private DefaultListModel<String> listModel; 
    private String eventoTexto = "";

    public PartidoFutbol(String equipoLocal, String equipoVisitante, JLabel lblMinutos, JLabel lblResultado, DefaultListModel<String> listModel) {
        this.equipoLocal = equipoLocal;
        this.equipoVisitante = equipoVisitante;
        this.golesLocal = 0;
        this.golesVisitante = 0;
        this.random = new Random();
        this.lblMinutos = lblMinutos;
        this.lblResultado = lblResultado;
        this.listModel = listModel; 
    }

    @Override
    public void run() {
        for (int minuto = 1; minuto <= 90; minuto++) {
        	
        	if(isInterrupted()) {
        		return;
        	}
        	simularEventos(minuto);
            actualizarInterfaz(minuto);
             
            try {
                if(minuto == 45) {
                    Thread.sleep(5000);
                    listModel.addElement("DESCANSO");
                }else if(minuto==90) {
                	 listModel.addElement("FINAL DEL PARTIDO");
                }else{
                    Thread.sleep(1000);
                }
            }catch(InterruptedException e) {
                return;
            }
        }
    }

    
    public void simularEventos(int minuto) {
    	int numeroprobrabilidad = (int) (Math.random() * 11);
        if (numeroprobrabilidad < 1) {
            if (random.nextBoolean()) {
                golesLocal++;
                String evento = "¡Gol de " + equipoLocal + "! " + golesLocal + " - " + golesVisitante;
                listModel.addElement(evento);
            } else {
                golesVisitante++;
                String evento = "¡Gol de " + equipoVisitante + "! " + golesLocal + " - " + golesVisitante;
                listModel.addElement(evento);
            }
        }else{
        	switch (numeroprobrabilidad) {
            case 1:
                eventoTexto = "Falta a favor de " + equipoLocal + ".";
                break;
            case 2:
                eventoTexto = "Falta a favor de " + equipoVisitante + ".";
                break;
            case 3:
                eventoTexto = "Tiro a puerta de " + equipoLocal + ", pero el portero lo detiene.";
                break;
            case 4:
                eventoTexto = "Tiro a puerta de " + equipoVisitante + ", se va desviado.";
                break;
            case 5:
                eventoTexto = "Córner a favor de " + equipoLocal + ".";
                break;
            case 6:
                eventoTexto = "Córner a favor de " + equipoVisitante + ".";
                break;
            case 7:
                eventoTexto = "Tarjeta amarilla para un jugador de " + equipoLocal + ".";
                break;
            case 8:
                eventoTexto = "Tarjeta amarilla para un jugador de " + equipoVisitante + ".";
                break;
            case 9:
                eventoTexto = "El árbitro revisa una posible falta en el área.";
                break;
            case 10:
                eventoTexto = "El juego se detiene por una lesión.";
                break;
        	}
      
        listModel.addElement(eventoTexto); 
       }   
    }

    public void actualizarInterfaz(int minuto) {
        lblMinutos.setText("Minuto: " + minuto);
        lblResultado.setText(equipoLocal + " " + golesLocal + " - " + golesVisitante + " " + equipoVisitante);
    }
    
    public void detenerPartido() {
    	this.interrupt();
    }
}