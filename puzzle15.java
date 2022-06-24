import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;

public class puzzle15 implements ActionListener {
    private JFrame ventana, mDialog;
    private JButton[] btnBoton = new JButton[16];
    private JButton btnIniciar, btnIniciar2, btnTemporal;
    private Random btnInit;
    private JLabel esInfo;
    private Random puzzleInit;
    private JTextField timePlay;
    private int dirX = 5, dirY = 5, control = 0, control2 = 0;

    void printBtn() {
        puzzleInit = new Random();
        ventana = new JFrame("Juegos de Pesca - DS3 UTP");
        ventana.setBounds(100, 100, 500, 500);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 16; i++) {
            btnBoton[i] = new JButton(String.format("%02d", i + 1));

            btnBoton[i].setBounds(115 + 55 * (i % 4), 115 + 55 * (i / 4), 50, 50);
            ventana.add(btnBoton[i]);
            btnBoton[i].addActionListener(this);
        }

        btnIniciar = new JButton("Iniciar");
        btnIniciar.setBounds(10, 115, 90, 30);
        ventana.add(btnIniciar);
        btnIniciar.addActionListener(this);

        btnIniciar2 = new JButton("Iniciar 2");
        btnIniciar2.setBounds(10, 165, 90, 30);
        ventana.add(btnIniciar2);
        btnIniciar2.addActionListener(this);

        esInfo = new JLabel("Tiempo");
        esInfo.setBounds(350, 105, 90, 30);
        ventana.add(esInfo);

        timePlay = new JTextField("0");
        timePlay.setBounds(350, 135, 50, 30);
        ventana.add(timePlay);

        ventana.setVisible(true);

    }

    Timer timerInit = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int c = Integer.parseInt(timePlay.getText());
            timePlay.setText(String.valueOf(c + 1));
        }
    });

    Timer timerMoveX = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnTemporal.getLocation().x, y = btnTemporal.getLocation().y;
            int x2 = btnBoton[15].getLocation().x;
            btnTemporal.setLocation(x + dirX, y);
            btnBoton[15].setLocation(x2 - dirX, y);
            if (x + dirX >= control && x2 - dirX == control2) {
                timerMoveX.stop();
            }
        }
    });

    public void actionPerformed(ActionEvent e) {
        btnTemporal = (JButton) e.getSource();
        if (e.getSource() == btnTemporal) {
            System.out.println(btnTemporal.getLocation().x + " " +
                    btnTemporal.getLocation().y);

            if (btnTemporal.getLocation().x + 55 == btnBoton[15].getLocation().x
                    || btnTemporal.getLocation().y - 55 == btnBoton[15].getLocation().y) {
                control = btnBoton[15].getLocation().x;
                control2 = btnTemporal.getLocation().x;
                timerMoveX.start();
            }

        }
        if (e.getSource() == btnIniciar) {
            timePlay.setText("0");
            timerInit.start();
            int x, y, j;
            for (int i = 0; i < 15; i++) {
                j = puzzleInit.nextInt(15);
                x = btnBoton[i].getLocation().x;
                y = btnBoton[i].getLocation().y;

                btnBoton[i].setLocation(btnBoton[j].getLocation().x, btnBoton[j].getLocation().y);
                btnBoton[j].setLocation(x, y);
            }

        }

        // if (e.getSource() == btnIniciar2) {
        // System.out.println("Entrando al Action");
        // timerInit.stop();
        // }

    }

}