
package Encriptador;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JFrame;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Encriptador extends JPanel implements ActionListener {
    Tablero tablero1 = new Tablero();
    static JFrame frame = new JFrame("encriptador");
    //Creamos dos JTextArea y dos botones
    protected JTextArea Visor1= new JTextArea(10, 20);
    protected JTextArea Visor2= new JTextArea(10, 20);
    private JButton Encriptar = new JButton("encriptar");
    private JButton Desencriptar = new JButton("desencriptar");
    JMenuBar barra;
    JMenu menu_archivo;
    JMenu menu_opciones;
    JMenu menu_acercade;
    JMenuItem menuitem_abrir;
    JMenuItem menuitem_guardar;
    JMenuItem menuitem_cerrar;
    JMenuItem menuitem_intercambiar;
    JMenuItem menuitem_tablero;
    JMenuItem menuitem_vaciar;
    JMenuItem menuitem_acercade;
    //Escuchamos los dos botones 
    public void actionPerformed(ActionEvent e) {  
        /*Al presionar en encriptar
          cargamos la matriz cod del tablero1 en enc1
          y en Visor2 cargamos el texto del resultado 
          de encriptar el texto de Visor1 
        */
        if (e.getActionCommand().equals("encriptar")){     
            //Instanciamos un objeto de la clase Encriptar()
            Encriptar enc1 = new Encriptar();
            if(tablero1.getCodHabilitado()==true){
                enc1.setCod(Tablero.getCod());
                Visor2.setText(enc1.Encriptando(Visor1.getText())); 
            }else{
                JOptionPane.showMessageDialog(this, "Antes de encriptar necesita configurar el Tablero ( Menu: Opciones -> Tablero )","Encriptador", 1);
            }
            enc1=null;
            System.gc();
        }
        /*Al presionar en desencriptar primero hacemos un 
          if donde preguntamos si esta habilitado el boton
          desencriptar y en caso de que este habilitado
          cargamos la matriz cod del tablero1 en enc1
          y en Visor2 cargamos el texto del resultado 
          de desencriptar el texto de Visor1.
          En caso de que este deshabilitado mostramos un mensaje
          de alerta.
        */
        if (e.getActionCommand().equals("desencriptar")){
            //Instanciamos un objeto de la clase Encriptar()
            Encriptar enc1 = new Encriptar();
            if(Tablero.getBotonDesenc()){
                if(tablero1.getCodHabilitado()==true){
                    enc1.setCod(Tablero.getCod());
                    Visor2.setText(enc1.Desencriptando(Visor1.getText()));
                }else{
                    JOptionPane.showMessageDialog(this, "Antes de desencriptar necesita configurar el Tablero ( Menu: Opciones -> Tablero )","Encriptador", 1);
                }
            }else{
                JOptionPane.showMessageDialog(this, "Al ingresar una Clave Publica usted no puede desencriptar y solamente puede encriptar.","Encriptador", 0);
            }
            enc1=null;
            System.gc();
        }
        if (e.getActionCommand().equals("abrir")){
            abrirArchivo();
        }
        if (e.getActionCommand().equals("guardar")){
            guardarArchivo();
        }
        if (e.getActionCommand().equals("cerrar")){
            frame.dispose();
            tablero1.cerrar();
        }
        if (e.getActionCommand().equals("tablero")){
            tablero1.Visible();
        }
        if (e.getActionCommand().equals("intercambiar")){
            String a,b;
            a=Visor1.getText();
            b=Visor2.getText();
            Visor1.setText("");
            Visor2.setText("");
            Visor1.append(b);
            Visor2.append(a);
        }
        if (e.getActionCommand().equals("vaciar")){
            Visor1.setText("");
            Visor2.setText("");
        }
        if (e.getActionCommand().equals("acercade")){
            JOptionPane.showMessageDialog(this,"Encriptador Cofre \n\nCreado por:\n\n- Nicolas Astorga\n ");
        }
    }
    
    /*
      Vamos creando los botones.
      A cada uno le asignamos un tamaño y un nombre para ser escuchado.
    */
    void crearBoton(JButton i, String j) {
            i.setPreferredSize(new Dimension(200, 30));
            add(i);
            i.setActionCommand(j);
            i.addActionListener(this);
    }
    
    /*
    * Guardamos el archivo encriptado o desencriptado
    */
    void guardarArchivo(){
        try {
            JFileChooser file=new JFileChooser();
            file.setAcceptAllFileFilterUsed(false);
            FileFilter filter = new FileNameExtensionFilter("Archivos TXT","txt");
            file.addChoosableFileFilter(filter);
            file.showSaveDialog(null);
            File guarda = file.getSelectedFile();  
            BufferedWriter bw = null;
            FileWriter fw = null;
            String texto = Visor2.getText();
            String[] textoTemp;
            textoTemp=texto.split("\n");
            
                if(guarda.getName().endsWith(".txt")==true){
                    for (int i = 0; i < textoTemp.length; i++) {
                        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(guarda.getAbsoluteFile(),true),"ISO-8859-1")); 
                        bw.write(textoTemp[i]);
                        if(i<textoTemp.length-1){
                           bw.newLine();
                            }
                        bw.close();
                    }
                }else{
                    for (int i = 0; i < textoTemp.length; i++) {
                        bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(guarda.getAbsoluteFile()+".txt",true),"ISO-8859-1")); 
                        bw.write(textoTemp[i]);
                        if(i<textoTemp.length-1){
                            bw.newLine();
                        }
                        bw.close();
                    }
            }    
        } 
        catch (IOException e) {
                e.printStackTrace();
        }   
    }
    /*
    * Abrir Archivos para encriptar
    */
    void abrirArchivo(){
        
        boolean primeraPasada=false;
        Visor1.setText("");
        
        JFileChooser file=new JFileChooser();
        file.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Archivos TXT","txt");
        file.addChoosableFileFilter(filter);
        file.showOpenDialog(null);
        File abre=file.getSelectedFile();
        File archivo = null;
        FileReader fr1 = null;
        FileReader fr2 = null;
        BufferedReader br1 = null;
        BufferedReader br2 = null;

        try {
            // FileReader y FileWriter son clases del paquete java.io que 
            // nos permiten leer y escribir "streams" de datos y de archivos.
            // Apertura del fichero y creacion de BufferedReader para poder
            // hacer una lectura comoda (disponer del metodo readLine()).
            fr1 = new FileReader (abre);
            br1 = new BufferedReader(fr1);
            fr2 = new FileReader (abre);
            br2 = new BufferedReader(fr2);
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(abre),"ISO-8859-1")); 
            String sCadena;
            String datos;
            int m=0;
            char temp;
            String texto="";


            // Obtenemos el numero de lineas de nuestro fichero
            long numeroLineas = 0;
            while ((sCadena = br1.readLine())!=null) {
                numeroLineas++;
            }
            // Hacemos la lectura del fichero dependiendo del numero de lineas
            for(int i=0;i<numeroLineas;i++){
                datos=in.readLine();
                if(primeraPasada==true){
                texto+="\n";
                }
                if(datos!=""){
                    while(m<datos.length()){
                       if( m!=datos.length()){
                           temp = datos.charAt(m); 
                           texto += temp;   
                       }
                       m++;
                    }
                    primeraPasada=true;
                    m=0;
                }
            }
            Visor1.append(texto);
        }
        catch(Exception e){
           e.printStackTrace();
        }finally{
           // En el finally cerramos el fichero, para asegurarnos
           // que se cierra tanto si todo va bien como si salta 
           // una excepcion.
           try{
              if( null != fr1 ){
                 fr1.close();
              }
              if( null != fr2 ){
                 fr2.close();
              }
           }catch (Exception e2){
              e2.printStackTrace();
           }
        }   
    }
    /*
    * Creación de Menús
    */
    void crearMenu(){
        barra=new JMenuBar();
        menu_archivo=new JMenu("Archivo");
        menu_opciones=new JMenu("Opciones");
        menu_acercade=new JMenu("Acerca de");
        menuitem_abrir = new  JMenuItem("Abrir");
        menuitem_guardar = new JMenuItem("Guardar");
        menuitem_cerrar = new JMenuItem("Cerrar");
        menuitem_tablero = new JMenuItem("Tablero");
        menuitem_intercambiar = new JMenuItem("Intercambiar");
        menuitem_vaciar = new JMenuItem("Vaciar");
        menuitem_acercade = new JMenuItem("Acerca de");
        menu_archivo.add(menuitem_abrir);
        menu_archivo.add(menuitem_guardar);
        menu_archivo.add(menuitem_cerrar);
        menu_opciones.add(menuitem_tablero);
        menu_opciones.add(menuitem_intercambiar);
        menu_opciones.add(menuitem_vaciar);
        menu_acercade.add(menuitem_acercade);
        barra.add(menu_archivo);
        barra.add(menu_opciones);
        barra.add(menu_acercade);
        menuitem_abrir.setActionCommand("abrir");
        menuitem_abrir.addActionListener(this);
        menuitem_guardar.setActionCommand("guardar");
        menuitem_guardar.addActionListener(this);
        menuitem_cerrar.setActionCommand("cerrar");
        menuitem_cerrar.addActionListener(this);
        menuitem_tablero.setActionCommand("tablero");
        menuitem_tablero.addActionListener(this);
        menuitem_intercambiar.setActionCommand("intercambiar");
        menuitem_intercambiar.addActionListener(this);
        menuitem_vaciar.setActionCommand("vaciar");
        menuitem_vaciar.addActionListener(this);
        menuitem_acercade.setActionCommand("acercade");
        menuitem_acercade.addActionListener(this);
    }
    
    /*
      Creamos los Layput y vamos cargando los componentes en ellos.
    */
    public Encriptador() {
        super(new GridBagLayout());
        crearMenu();
        JScrollPane scrollPane0 = new JScrollPane(barra);
        Visor1.setEditable(true);
        JScrollPane scrollPane1 = new JScrollPane(Visor1);
        Visor2.setEditable(true);
        JScrollPane scrollPane2 = new JScrollPane(Visor2);
        GridBagConstraints a = new GridBagConstraints();
        a.gridwidth = GridBagConstraints.REMAINDER;
        a.fill = GridBagConstraints.BOTH;
        a.weightx = 1.0;
        a.weighty = 1.0;
        add(scrollPane0, a);
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        add(scrollPane1, c);
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = GridBagConstraints.REMAINDER;
        d.fill = GridBagConstraints.BOTH;
        d.weightx = 1.0;
        d.weighty = 1.0;
        add(scrollPane2, d); 
        frame.setResizable(false);
        crearBoton(Encriptar,"encriptar");
        crearBoton(Desencriptar,"desencriptar");
    }
    
    /*
      Creamos el JFrame donde van a ir los layout y los componentes.
    */
    private static void createAndShowGUI() {
        //En caso de ser cerrada la ventana se termina la ejecucion
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new Encriptador());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    
    /*
      Iniciamos el tablero y nuestro GUI encriptador.
    */
    public static void main(String[] args) {
        createAndShowGUI(); 
    }
    
}