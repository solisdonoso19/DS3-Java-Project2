import javax.swing.*;
import javax.swing.Timer;
import java.awt.event.*;
import java.util.*;
import java.io.*;

public class puzzle15 extends playersPuzzle15 implements ActionListener {
    private JFrame ventana, mDialog;
    private JButton[] btnBoton = new JButton[16];
    private JButton btnIniciar, btnIniciar2, btnTemporal;
    private JLabel esInfo;
    private Random puzzleInit;
    private boolean gameInit = false;
    private JTextField timePlay, namePlayer;
    private Integer dirX = 5, dirY = 5, control = 0, control2 = 0, idPlayer = 0;;
    private Integer[] btnControl = new Integer[16];
    private playersPuzzle15 player[] = new playersPuzzle15[5];
    private FileWriter fw;

    void printBtn() {
        puzzleInit = new Random();
        ventana = new JFrame("Juegos de Pesca - DS3 UTP");
        ventana.setBounds(100, 100, 800, 500);
        ventana.setLayout(null);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        for (int i = 0; i < 16; i++) {
            btnBoton[i] = new JButton(String.format("%02d", i + 1));

            btnBoton[i].setBounds(115 + 55 * (i % 4), 115 + 55 * (i / 4), 50, 50);
            btnControl[i] = (115 + 55 * (i % 4)) + (115 + 55 * (i / 4));
            ventana.add(btnBoton[i]);
            btnBoton[i].addActionListener(this);
        }
        btnBoton[15].setVisible(false);

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

        namePlayer = new JTextField("");
        namePlayer.setBounds(10, 80, 150, 30);
        ventana.add(namePlayer);

        esInfo = new JLabel("Nombre del jugador: ");
        esInfo.setSize(250, 50);
        esInfo.setLocation(10, 40);
        ventana.add(esInfo);

        // esInfo = new JLabel("Universidad Tecnologica de Panama");
        // esInfo.setSize(250, 50);
        // esInfo.setLocation(450, 50);
        // ventana.add(esInfo);

        // esInfo = new JLabel("Facultad de Sistemas Computacionales");
        // esInfo.setSize(250, 50);
        // esInfo.setLocation(450, 100);
        // ventana.add(esInfo);

        // esInfo = new JLabel("Licenciatura en Desarrollo de Software");
        // esInfo.setSize(250, 50);
        // esInfo.setLocation(450, 150);
        // ventana.add(esInfo);

        // esInfo = new JLabel("Desarrollo de Software III");
        // esInfo.setSize(250, 50);
        // esInfo.setLocation(450, 200);
        // ventana.add(esInfo);

        // esInfo = new JLabel("Carlos Solis");
        // esInfo.setSize(100, 50);
        // esInfo.setLocation(450, 250);
        // ventana.add(esInfo);

        // esInfo = new JLabel("6-723-1380");
        // esInfo.setSize(100, 50);
        // esInfo.setLocation(450, 300);
        // ventana.add(esInfo);

        // esInfo = new JLabel("Proyecto #2");
        // esInfo.setSize(100, 50);
        // esInfo.setLocation(450, 350);
        // ventana.add(esInfo);
        if (player[0].getTimePlay() != 0) {

        }
        for (int i = 0; i <= 4; i++) {
            player[i] = new playersPuzzle15();
            player[i].setPlayer("null");
            player[i].setTimePlay(0);
        }
        creacionFile();

        ventana.setVisible(true);

    }

    Timer timerInit = new Timer(1000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int c = Integer.parseInt(timePlay.getText());
            timePlay.setText(String.valueOf(c + 1));
            validar();
        }
    });

    Timer timerMove15 = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnBoton[14].getLocation().x, y = btnBoton[14].getLocation().y;
            int x2 = btnBoton[15].getLocation().x;
            btnBoton[14].setLocation(x + dirX, y);
            btnBoton[15].setLocation(x2 - dirX, y);
            if (x + dirX >= control && x2 - dirX == control2) {
                timerMove15.stop();
            }
        }
    });

    Timer timerMoveXR = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnTemporal.getLocation().x, y = btnTemporal.getLocation().y;
            int x2 = btnBoton[15].getLocation().x;
            btnTemporal.setLocation(x + dirX, y);
            btnBoton[15].setLocation(x2 - dirX, y);
            if (x + dirX >= control && x2 - dirX == control2) {
                timerMoveXR.stop();
            }
        }
    });

    Timer timerMoveXL = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnTemporal.getLocation().x, y = btnTemporal.getLocation().y;
            int x2 = btnBoton[15].getLocation().x;
            btnTemporal.setLocation(x - dirX, y);
            btnBoton[15].setLocation(x2 + dirX, y);
            if (x - dirX >= control && x2 + dirX == control2) {
                timerMoveXL.stop();
            }
        }
    });

    Timer timerMoveYT = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnTemporal.getLocation().x, y = btnTemporal.getLocation().y;
            int y2 = btnBoton[15].getLocation().y;
            btnTemporal.setLocation(x, y + dirY);
            btnBoton[15].setLocation(x, y2 - dirY);
            if (y + dirY >= control && y2 - dirY == control2) {
                timerMoveYT.stop();
            }
        }
    });

    Timer timerMoveYB = new Timer(9, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int x = btnTemporal.getLocation().x, y = btnTemporal.getLocation().y;
            int y2 = btnBoton[15].getLocation().y;
            btnTemporal.setLocation(x, y - dirY);
            btnBoton[15].setLocation(x, y2 + dirY);
            if (y - dirY >= control && y2 + dirY == control2) {
                timerMoveYB.stop();
            }

        }
    });

    public void creacionFile() {

        try {
            int menor = 0;
            fw = new FileWriter("Jugadores.txt", true);
            fw.write("Jugadores con mejor tiempo\r\n__________________________________\r\n");
            PrintWriter writer = new PrintWriter("Jugadores.txt");
            writer.close();
            fw.write("1. " + player[0].getPlayer() + " con: " + player[0].getTimePlay() + " segundos\r\n");
            fw.write("2. " + player[1].getPlayer() + " con: " + player[1].getTimePlay() + " segundos\r\n");
            fw.write("3. " + player[2].getPlayer() + " con: " + player[2].getTimePlay() + " segundos\r\n");
            fw.write("4. " + player[3].getPlayer() + " con: " + player[3].getTimePlay() + " segundos\r\n");
            fw.write("5. " + player[4].getPlayer() + " con: " + player[4].getTimePlay() + " segundos\r\n");
            fw.close();

        } catch (Exception e) {
            System.out.println("Error al grabar " + e.toString());
        }
    }

    public void validar() {
        boolean Win = true;
        for (int i = 0; i < 15; i++) {
            if (btnControl[i] == (btnBoton[i].getLocation().x + btnBoton[i].getLocation().y)) {
                continue;
            } else {
                Win = false;
                break;
            }
        }
        if (Win == true) {
            timerInit.stop();
            JOptionPane.showMessageDialog(mDialog, "Ganaste, tu tiempo fue de: " + timePlay.getText() + " segundos: ");

            timePlay.setText(String.valueOf("0"));
            btnIniciar.setText("Reiniciar");
        }
    }

    public void actionPerformed(ActionEvent e) {
        btnTemporal = (JButton) e.getSource();
        if (e.getSource() == btnTemporal && e.getSource() != btnIniciar && e.getSource() != btnIniciar2 && gameInit) {

            if (btnTemporal.getLocation().x + 55 == btnBoton[15].getLocation().x
                    && btnTemporal.getLocation().y == btnBoton[15].getLocation().y) {

                control = btnBoton[15].getLocation().x;
                control2 = btnTemporal.getLocation().x;
                timerMoveXR.start();

            }

            if (btnTemporal.getLocation().x - 55 == btnBoton[15].getLocation().x
                    && btnTemporal.getLocation().y == btnBoton[15].getLocation().y) {
                control = btnBoton[15].getLocation().x;
                control2 = btnTemporal.getLocation().x;
                timerMoveXL.start();
            }

            if (btnTemporal.getLocation().y + 55 == btnBoton[15].getLocation().y
                    && btnTemporal.getLocation().x == btnBoton[15].getLocation().x) {
                control = btnBoton[15].getLocation().y;
                control2 = btnTemporal.getLocation().y;
                timerMoveYT.start();
            }

            if (btnTemporal.getLocation().y - 55 == btnBoton[15].getLocation().y
                    && btnTemporal.getLocation().x == btnBoton[15].getLocation().x) {
                control = btnBoton[15].getLocation().y;
                control2 = btnTemporal.getLocation().y;
                timerMoveYB.start();
            }

        }

        if (e.getSource() == btnIniciar) {
            gameInit = true;
            timePlay.setText("0");
            timerInit.start();
            int x, y, j;
            for (int i = 0; i < 15; i++) {
                j = puzzleInit.nextInt(15);
                x = btnBoton[i].getLocation().x;
                y = btnBoton[i].getLocation().y;

                btnBoton[i].setLocation(btnBoton[j].getLocation().x,
                        btnBoton[j].getLocation().y);
                btnBoton[j].setLocation(x, y);
            }
        }

        if (e.getSource() == btnIniciar2) {
            gameInit = true;
            control = btnBoton[15].getLocation().x;
            control2 = btnBoton[14].getLocation().x;
            timerMove15.start();
            timerInit.start();

        }

    }

}
