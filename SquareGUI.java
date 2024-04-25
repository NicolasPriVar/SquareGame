import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.border.*;

public class SquareGUI extends JFrame {
    private JPanel panelTablero;
    private JPanel panelImagen;
    private JPanel panelMovimientos;
    private JPanel panelTriple;

    private JMenuItem menuItemAbrir;
    private JMenuItem menuItemSalvar;

    private JButton botonArriba;
    private JButton botonAbajo;
    private JButton botonDerecha;
    private JButton botonIzquierda;
    private JButton botonArribaIzquierda;
    private JButton botonArribaDerecha;


    private SquareGUI() {
        super("Square");
        prepareElements();
        prepareElementsBoard();
        prepareActions();
    }

    //Acciones basicas
    public void prepareActions() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(SquareGUI.this, "¿Seguro que quieres salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
        
        menuItemAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SquareGUI.this, "Funcionalidad de abrir archivo en construcción.");
            }
        });
        menuItemSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(SquareGUI.this, "Funcionalidad de guardar archivo en construcción.");
            }
        });        
    }

    //Salir desde menu
    public void prepareActionsMenu(JMenuItem menuItemSalir) {
        menuItemSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(SquareGUI.this, "¿Seguro que quieres salir?", "Confirmar Salida", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
    }
    

    //Tablero
    public void prepareElementsBoard() {
        panelTablero = new JPanel();
        panelTablero.setBorder(new CompoundBorder(new EmptyBorder(5, 5, 5, 5), new TitledBorder("Tablero")));
        Dimension tam = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho = tam.width / 3;
        panelTablero.setPreferredSize(new Dimension(ancho, 100));
        panelTablero.setLayout(new GridLayout(1, 1));
        TableroPanel tableroPanel = new TableroPanel(); // Crea el panel del tablero
        panelTablero.add(tableroPanel); // Agrega el panel del tablero al panelTablero
    }



    //refrescar tablero
    public void refresh(){

    }

    
    //Elementos basicos
    public void prepareElements() {
        menuItemAbrir = new JMenuItem("Abrir");
        menuItemSalvar = new JMenuItem("Salvar");
        
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        //Tamano ventana
        int ancho = d.width / 2;
        int alto = d.height / 2;
        setSize(ancho, alto);
        int derecha = d.width / 4;
        int arriba = d.height / 4;
        setLocation(derecha, arriba);
        JMenuBar menuBar = new JMenuBar();
        JMenu menuArchivo = new JMenu("Archivo");
        JMenuItem menuItemNuevo = new JMenuItem("Nuevo");
        JMenuItem menuItemSalir = new JMenuItem("Salir");

        prepareElementsBoard();
        add(panelTablero, BorderLayout.CENTER);

        prepareActionsMenu(menuItemSalir);
        menuArchivo.add(menuItemNuevo);
        menuArchivo.add(menuItemAbrir);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalvar);
        menuArchivo.addSeparator();
        menuArchivo.add(menuItemSalir);
        
        menuBar.add(menuArchivo);
        setJMenuBar(menuBar);

        //Panel para la imagen
        panelImagen = new JPanel();
        panelImagen.setBorder(new CompoundBorder(new EmptyBorder( 5, 5, 5, 5 ), new LineBorder(Color.BLACK)));
        Dimension tam2 = Toolkit.getDefaultToolkit().getScreenSize();
        int altoImg = tam2.height / 6;
        int anchoImg = tam2.width;
        panelImagen.setPreferredSize(new Dimension(anchoImg, altoImg));
        panelImagen.setLayout(new GridLayout( 1, 1 ));
        String imagePath = "E:/Semestre_7/POOB/Laboratorios/Barbosa-Prieto-Lab05/imagenLogo.png";
        ImageIcon imageIcon = new ImageIcon(imagePath);
        Image image = imageIcon.getImage().getScaledInstance(anchoImg/2, altoImg/2, Image.SCALE_DEFAULT);
        ImageIcon scaledImageIcon = new ImageIcon(image);
        JLabel imageLabel = new JLabel(scaledImageIcon);
        panelImagen.add(imageLabel);
        add(panelImagen, BorderLayout.NORTH);



        //Panel para los movimientos(arriba, abajo, derecha, izquierda)
        panelMovimientos = new JPanel();
        panelMovimientos.setBorder(new CompoundBorder(new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Movimientos" )));
        panelMovimientos.setLayout( new GridLayout(2, 3));
        botonArriba = new JButton("NORTE");
        botonAbajo = new JButton("SUR");
        botonDerecha = new JButton("ESTE");
        botonIzquierda = new JButton("OESTE");
        botonArribaIzquierda = new JButton(" ");
        botonArribaDerecha = new JButton(" ");
        panelMovimientos.add(botonArribaIzquierda);
        panelMovimientos.add(botonArriba);
        panelMovimientos.add(botonArribaDerecha);
        panelMovimientos.add(botonIzquierda);
        panelMovimientos.add(botonAbajo);
        panelMovimientos.add(botonDerecha);    
        add(panelMovimientos, BorderLayout.SOUTH);


        //Se crea el panel de la derecha dividido en 3
        panelTriple = new JPanel();
        Dimension tam4 = Toolkit.getDefaultToolkit().getScreenSize();
        int ancho3 = tam4.width/4;
        panelTriple.setPreferredSize(new Dimension(ancho3, 100));
        panelTriple.setLayout(new GridLayout(3, 1)); // Dividir en 3 paneles verticalmente
        panelTriple.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));        
        //Se crea el panel encargado de los contadores de movimientos y fichas bien ubicadas
        JPanel panelTriple1 = new JPanel();
        panelTriple.add(panelTriple1);
        panelMovimientos.setBorder(new CompoundBorder(new EmptyBorder( 5, 5, 5, 5 ), new LineBorder(Color.BLACK)));
        panelTriple1.setLayout(new GridLayout(2, 2));
        panelTriple1.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));        
        JLabel labelMovimientos = new JLabel("Movimientos");
        panelTriple1.add(labelMovimientos);
        JLabel contadorMovimientos = new JLabel("0");
        panelTriple1.add(contadorMovimientos);
        JLabel labelFichas = new JLabel("Fichas Bien Ubicadas (%)");
        panelTriple1.add(labelFichas);
        JLabel contadorFichas = new JLabel("0");
        panelTriple1.add(contadorFichas);

        //Se crea el panel encargado de las modificaciones que hay en el juego (tablero y colores)
        JPanel panelTriple2 = new JPanel();
        panelTriple2.setLayout(new GridLayout(2,1));
        panelTriple.add(panelTriple2);
        panelTriple2.setBorder(new CompoundBorder(new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Modificaciones" )));
        panelTriple2.add(new Button("Modificar color"));
        panelTriple2.add(new Button("Modificar tablero"));
        
        //Se crea el panel encargado de dar la opción de terminar el juego.
        JPanel panelTriple3 = new JPanel();
        panelTriple3.setLayout(new GridLayout(1,1));
        panelTriple.add(panelTriple3);
        panelTriple3.setBorder(new CompoundBorder(new EmptyBorder( 5, 5, 5, 5 ), new TitledBorder( "Finalización de juego" )));
        panelTriple3.add(new Button("Terminar juego"));
        add(panelTriple, BorderLayout.EAST);


        
    }

    //Principal
    public static void main(String[] args) {
        
        SquareGUI gui = new SquareGUI();
        gui.setVisible(true);
    }
}

class TableroPanel extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        setBackground(Color.white);
        super.paintComponent(g);
        int ancho = getWidth();
        int alto = getHeight();
        int row = 4;
        int col = 4;
        for (int f = 0; f <= row; f++) {
            g.drawLine(f * ancho / row, 0, f * ancho / row, alto);
        }
        for (int c = 0; c <= col; c++) {
            g.drawLine(0, c * alto / col, ancho, c * alto / col);
        }
    }
}