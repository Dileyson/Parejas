package panelPrincipal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class Parejas extends JFrame implements ActionListener {
    private Tablero t;
    private JButton[][] botones;
    private ImageIcon[] imagenes;
    private static int sw;
    private static int a, b, ii, jj, contador_jugador1 = 0, contador_jugador2 = 0;
    ;
    private JLabel puntos_jugador_1 = new JLabel();
    private JLabel puntos_jugador_2 = new JLabel();
    private JPanel panelEste = new JPanel(new FlowLayout());
    private JPanel panelOeste = new JPanel(new FlowLayout());
    private String pj1;
    private String pj2;
    private int puntosJ1;
    private int puntosJ2;
    private int contador;
    List<Integer> numeroAciertos;

    public Parejas() {
        sw = 0;
        t = new Tablero(6);
        pj1 = "0";
        pj2 = "0";
        contador = 0;
        puntosJ1 = 0;
        puntosJ2 = 0;
        numeroAciertos = new ArrayList<Integer>();
        t.genAleatorio();
        iniciarComponentes();
        puntajesJuagores();
        configVentana();
    }

    public void configVentana() {

        setTitle("Concéntrate ");
        setSize(850, 600);
        //setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
        //this.getContentPane().setLayout();
    }

    public void puntajesJuagores() {


        JPanel panelNorte = new JPanel(new FlowLayout());
        JPanel panelEste = new JPanel(new FlowLayout());
        JPanel panelOeste = new JPanel(new FlowLayout());
        JLabel puntaje = new JLabel();
        JLabel jugador_1 = new JLabel();
        JLabel jugador_2 = new JLabel();

        jugador_1.setText("Jugador 1:");
        jugador_2.setText("Jugador 2:");
        puntos_jugador_1.setText(pj1);
        puntos_jugador_2.setText(pj2);

        jugador_1.setPreferredSize(new Dimension(200, 50));
        jugador_2.setPreferredSize(new Dimension(200, 50));


        panelEste.add(jugador_1);
        panelEste.add(puntos_jugador_1);
        panelOeste.add(jugador_2);
        panelOeste.add(puntos_jugador_2);

        this.add(panelNorte, "North");
        this.add(panelEste, "East");
        this.add(panelOeste, "West");


    }

    public void iniciarComponentes() {
        int d = t.getDimension();
        imagenes = new ImageIcon[d * d / 2 + 1];
        imagenes[0] = null;
        for (int i = 1; i <= d * d / 2; i++)
            imagenes[i] = new ImageIcon(getClass().getResource("/imagen/" + i + ".bmp"));
        JPanel A = new JPanel(new GridLayout(d, d));

        botones = new JButton[d][d];
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {

                botones[i][j] = new JButton();
                botones[i][j].addActionListener(this);
                A.add(botones[i][j]);
            }
        }
        this.add(A, "Center");
    }

    public void accion(int x, int y) {
        switch (sw) {
            case 0:
                if (!t.esClic(x, y)) {
                    t.clic(x, y);
                    botones[x][y].setIcon(imagenes[t.getPos(x, y)]);
                    sw = 1;
                    a = x;
                    b = y;
                }
                break;
            case 1:
                if (!t.esClic(x, y)) {
                    t.clic(x, y);
                    botones[x][y].setIcon(imagenes[t.getPos(x, y)]);
                    ii = x;
                    jj = y;
                    if (t.getPos(a, b) != t.getPos(ii, jj)) {
                        sw = 2;
                        this.contador++;

                    } else {
                        this.contador++;
                        numeroAciertos.add(contador);

                        if (contador % 2 != 0) {

                            if(numeroAciertos.contains(contador-2))
                                puntosJ1=puntosJ1*2;
                            else puntosJ1++;
                            pj1 = Integer.toString(puntosJ1);
                            puntos_jugador_1.setText(pj1);
                            panelEste.add(puntos_jugador_1);
                        } else {

                            if(numeroAciertos.contains(contador-2))
                                puntosJ2=puntosJ2*2;
                            else puntosJ2++;
                            pj2 = Integer.toString(puntosJ2);
                            puntos_jugador_2.setText(pj2);
                            panelEste.add(puntos_jugador_2);
                        }
                        puntajesJuagores();

                        sw = 0;
                    }
                }
                break;
            case 2:
                botones[a][b].setIcon(null);
                botones[ii][jj].setIcon(null);
                t.clic(a, b);
                t.clic(ii, jj);
                sw = 0;
                break;
        }
    }

    public void actionPerformed(ActionEvent ae) {

        int d = t.getDimension();
        for (int i = 0; i < d; i++) {
            for (int j = 0; j < d; j++) {
                if (botones[i][j] == ae.getSource()) {
                    accion(i, j);
                    if (t.esCompleto()) {
                        if (puntosJ1>puntosJ2) {
                            JOptionPane.showMessageDialog(this, "Felicidades, ha ganado el jugador 1.", "Al fin terminaron!", JOptionPane.INFORMATION_MESSAGE, null);
                        }
                        else{
                            JOptionPane.showMessageDialog(this, "Felicidades, ha ganado el jugador 2.", "Al fin terminaron!", JOptionPane.INFORMATION_MESSAGE, null);
                        }

                            System.exit(0);

                    }
                    return;
                }
            }
        }
    }
}
