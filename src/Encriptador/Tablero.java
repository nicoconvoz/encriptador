package Encriptador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;


public class Tablero extends JPanel implements ActionListener{
    
    JFrame frame = new JFrame("Tablero");
    
    // Creamos los contadores del Tablero
    private int contador;
    private int contadorCod;
    private int con1 = 0;
    private int con2 = 0;
    private int z=0;
    // Variable que sirve de indice de columnas de la matriz cod
    private int cod2Pos=0;
    /*
      Creamos las matrices cod y codCP
      cod es la matriz que contiene la clave secreta.
      codCP es la matriz que contiene la codificación de la clave publica.
    */
    public static int cod[][] = new int[16][8]; 
    public static int codCP[][] = new int[16][8];
    // Sirve para leer el archivo del cofre linea a linea.
    private String linea[]=new String[129];
    // Contenedor de clave publica.
    private String clavePublica="";
    // Esta variable maneja la habiltiación del boton desencriptar de la clase Encriptador.
    private static boolean botonDesenc = true;
    // Instancias de Paneles y botones de la GUI de la clase Tablero.
    public  JPanel panel1 = new JPanel();
    public  JPanel panel2 = new JPanel();
    private JButton[] boton = new JButton[128];
    private JButton guardar = new JButton("Guardar"); 
    private JButton abrirCS = new JButton("Abrir CS"); 
    private JButton abrirCP = new JButton("Abrir CP");  
    private JButton resetear = new JButton("Resetear"); 
    
    private boolean v=false;
    private boolean codHabilitado=false;
    private Color color = guardar.getBackground();

    public boolean getCodHabilitado() {
        return codHabilitado;
    }
    /*
      Creamos los botones y refrescamos los paneles para que se vean.
    */
    public Tablero()  {  
        createAndShowGUI();
        iniciar();
    } 
    
    // Sirve para pasar el cod a la clase Encriptador.
    public static int[][] getCod(){
        return cod;
    }
    
    // Sirve para avisar a la clase Encriptador si el boton Desencriptar esta habilitado o deshabilitado.
    public static boolean getBotonDesenc(){
        return botonDesenc;
    }
    
    // Escuchamos los diversos botones
    public void actionPerformed(ActionEvent e) {
        // Al presionar en guardar llamamos al metodo privado guardarClave()
        if (e.getActionCommand().equals("Guardar")){
            guardarClave();
        }
        // Al presionar en AbrirCS llamamos al metodo privado abrirCLaveSecreta()
        if (e.getActionCommand().equals("AbrirCS")){
            abrirClaveSecreta();
        }
        // Al presionar en abrirCP llamamos al metodo privado abrirClavePublica()
        if (e.getActionCommand().equals("AbrirCP")){
            abrirClavePublica();
        }
        //Al presionar el botón Resetear el tablero vuelve a 0
        if (e.getActionCommand().equals("Resetear")){
            codHabilitado=false;
            for (int i=0; i<boton.length;i++){
                boton[i].setText(String.valueOf(i+1));
                boton[i].setEnabled(true);
                boton[i].setBackground(color);;
            } 
            guardar.setEnabled(false);
            contadorCod=0;
            contador=0;
            con2=0;
            con1=0;
            botonDesenc=true;
        }
        /*
        * Recorremos el vector de los botones del 1 al 128 y en cada caso vamos cargando la info
          y vamos llamando a los metodos procesoBotonCS y Contadores
          Por ejemplo estamos en el boton 0 donde i=0 entonces en el if reacciona solo cuando el
          boton llamado es "boton0"
          el cod entonces en los indices con1 y con2 que son respectivamente 0 si no se ha corrido
          el metodo contadores()
          y procesoBotonCS es cargado con dos parametros el vector boton y el indice del vector
        */
        for(int i=0;i<128;i++){
            if (e.getActionCommand().equals("boton"+i)){
            cod[con1][con2] = i+1;
            procesoBotonCS(boton,i);
            Contadores();
            }
        }   
    }
    
    /*
      Con cada uso incrementamos contadorCod que nos va a servir en procesoBotonCS
      para darle nombre al texto del boton 
      en segundo lugar se saca el resto después de dividir el primer operando por el segundo
      y si es == 0 incrementamos contador (contador nos sirve por un lado para elegir de que
      color se van a pintar los botones y por otro lado también se usa en otra parte para
      calcular el primer indice de cod)
      en tercer lugar con2 se incrementa
      en cuarto lugar preguntamos si con1 es 15 y con 2 es 8 y habilitamos el boton "guardar"
      en quinto lugar cada vez que con2 == 8 volvemos con2 a 0 e incrementamos con1  
    */
    private void Contadores() {
        contadorCod++;
        if (contadorCod % 8 == 0) {
            contador++;
        }
        con2++;      
        if (con1==15 && con2==8){
            guardar.setEnabled(true);
        }  
        if (con2 == 8) {
            con2 = 0;
            con1++;
            if(con1==16){
                codHabilitado=true;
            }
        }                  
    }
    
    /*
      Le decimos a cada boton pasado por parametro en este metodo
      que sea de color gris, lleve por texto un "?" y que este deshabilitado
    */
    private void procesoBotonCP(JButton[] i, int k) {
        i[k].setBackground(Color.DARK_GRAY);
        i[k].setText("?");
        i[k].setEnabled(false);
    }
    
    /* 
      Segun el valor de contador elegimos el color de cada boton
      por último cambiamos el texto del boton y lo deshabilitamos
    */
    private void procesoBotonCS(JButton[] i, int k) {
        switch (contador) {
            case 0:
                i[k].setBackground(Color.green);
                break;
            case 1:
                i[k].setBackground(Color.red);
                break;
            case 2:
                i[k].setBackground(Color.yellow);
                break;
            case 3:
                i[k].setBackground(Color.blue);
                break;
            case 4:
                i[k].setBackground(Color.GRAY);
                break;
            case 5:
                i[k].setBackground(Color.getHSBColor(40, 250, 140));
                break;
            case 6:
                i[k].setBackground(Color.getHSBColor(140, 140, 40));
                break;
            case 7:
                i[k].setBackground(Color.getHSBColor(52, 247, 191));
                break;
            case 8:
                i[k].setBackground(Color.BLACK);
                break;
            case 9:
                i[k].setBackground(Color.PINK);
                break;
            case 10:
                i[k].setBackground(Color.ORANGE);
                break;
            case 11:
                i[k].setBackground(Color.WHITE);
                break;
            case 12:
                i[k].setBackground(Color.CYAN);
                break;
            case 13:
                i[k].setBackground(Color.MAGENTA);
                break;
            case 14:
                i[k].setBackground(Color.lightGray);
                break;
            case 15:
                i[k].setBackground(Color.DARK_GRAY);
                break;
        }
        i[k].setText(String.valueOf(contadorCod + 1));
        i[k].setEnabled(false);
    }
    
    /*
      Damos los atributos a los botones del vector y los agregamos al GUI
      también los dejamos a la escucha.
    */
    private void crearBoton1(JButton[] i, int k, String j) {
        i[k].setName(j);
        i[k].setPreferredSize(new Dimension(1, 20));
        panel1.add(i[k]);                
        i[k].setActionCommand(j);
        i[k].addActionListener(this); 
    }
    
    /*
     Creamos el resto de los votones y los ponemos a la escucha.
    */
    private void crearBoton2(JButton i, String j) {    
        i.setPreferredSize(new Dimension(150, 30));
        panel2.add(i);
        i.setActionCommand(j);
        i.addActionListener(this);
    }
    
    /*
      Sirve para guardar tanto la clave secreta como la clave publica.
      pero en este metodo se guarda primero la clave privada y se llama al metodo crearCofre()
    */
    private void guardarClave(){
        /*
          La estructura Try Catch sirve para preveer errores y responder a los mismos.
        */
        try {
            /*
              Instanciamos un objeto de la clase JFileChooser
              JFileChooser es una clase java que nos permite
              mostrar fácilmente una ventana para la selección
              de un fichero
            */
            JFileChooser file=new JFileChooser();
            // Le decimos que no acepte toda clase de archivos
            file.setAcceptAllFileFilterUsed(false);
            // Le decimos que solo habrá archivos .priv
            FileFilter filter = new FileNameExtensionFilter("Archivos SECRET","secret");
            // Cargamos nuestro filtro
            file.addChoosableFileFilter(filter);
            // Abrimos el cuadro de dialogo para guardar archivos.
            file.showSaveDialog(this);
            // Creamos un archivo y le asignamos la dirección del archivo seleccionado en el cuadro
            File guarda = file.getSelectedFile();
            /*
              Instanciamos un objeto de la clase Properties
              Un archivo de este tipo almacena la información como texto.
            */
            Properties claveSecreta = new Properties();
            z=1;
            /*
              Recorremos cod con los for
              y vamos estableciendo los valores de las propiedades del texto
              de la forma etiqueta primero que seria el valor String de z 
              y luego el valor asignado a la etiqueta que seia el String del contenido de cod
            */
            for(int i=0; i<cod.length;i++){
                for(int j=0; j<cod[0].length;j++){
                    claveSecreta.setProperty(String.valueOf(z), String.valueOf(cod[i][j]));
                    z++;
                }
            }
            z=0;
            /*
              Observamos si el final del archivo termina en ".secret" y si es asi lo guardamos
              sino es asi le colocamos ".secret" al final y lo guardamos.
            */
            if(guarda.getName().endsWith(".secret")){
                FileOutputStream out = new FileOutputStream(guarda);
                claveSecreta.store(out, null);
                out.close();
            }else{
                FileOutputStream out = new FileOutputStream(guarda + ".secret");
                claveSecreta.store(out, null);
                out.close();
            }
        } catch (IOException e) {
                System.out.println(e);
        } catch (Exception e) {
                System.out.println(e);
        }
        crearCofre();
    } 
    
    // Abrimos la Clave Secreta
    private void abrirClaveSecreta(){   
        try {
            JFileChooser file=new JFileChooser();
            file.setAcceptAllFileFilterUsed(false);
            FileFilter filter = new FileNameExtensionFilter("Archivos SECRET","secret");
            file.addChoosableFileFilter(filter);
            file.showOpenDialog(this);
            File abre=file.getSelectedFile();
            Properties claveSecreta = new Properties();
            FileInputStream in = new FileInputStream(abre);
            claveSecreta.load(in);
            in.close();
            /*
              Recorremos los valores de la etiquetas key de la clave secreta y
              vamos obteniendo su valor y el del valor del value que le hemos 
              asigando
            */
            for (String key : claveSecreta.stringPropertyNames()) {
                    String value = claveSecreta.getProperty(key);
                    // Llamamos al metodo cargarClave y le pasamos por parametros la key y el value
                    cargarClave(Integer.parseInt(key), Integer.parseInt(value), 's');
            } 
            // Habilitamos el boton guardar de esta clase y el boton Desencriptar de la clase Encriptador
            guardar.setEnabled(true);
            botonDesenc=true;
        } catch (IOException e) {
                System.out.println(e);
        } catch (Exception e) {
                System.out.println(e);
        }
    }
    
    /*
      Creamos nuestro Cofre que contendrá la clave secreta pero que servirá de clave pública.
    */
    private void crearCofre(){
        // Creamos una serie de variables.
        String claveCC="";
        int contp=1;
        String llaveCofre;
        String textoEncCofre="";
        // Creamos una instancia de Encriptar que se llama enc1
        Encriptar enc1 =new Encriptar();
        // Ingresamos la frase que vamos a asignarle a la clave publica en la variable llaveCofre
        llaveCofre=JOptionPane.showInputDialog("Ingrese frase para cofre de Clave Publica: ");
        if ((llaveCofre != null) && (llaveCofre.length() > 0)) {
            // Instanciamos un objeto de la clase Cofre.
            Cofre cofre = new Cofre();
            // Cargamos la frase en nuestro objeto cofre
            cofre.setFrase(llaveCofre);
            // Obtenemos la codCP creada por la frase que ingresamos
            codCP=cofre.Cofretizar();
            /*
              Recorremos cod
              Guardamos todos los valores de cod en una String que se llama claveCC
            */
            for(int i=0; i<cod.length; i++){
                for (int j=0; j<cod[0].length; j++){
                    if (contp==1){
                        claveCC = String.valueOf(cod[i][j]);
                    }else{
                        claveCC += " " + cod[i][j];                
                    }
                    contp++;
                }
            }
            contp=0;
            // Cargamos en enc1 el codCP
            enc1.setCod(codCP);
            // Encriptamos el contenido de claveCC y lo guardamos en textoEncCofre
            textoEncCofre=enc1.Encriptando(claveCC);
            // Guardamos textoEncCofre en un archivo ".cofre"
            try {
                JFileChooser file=new JFileChooser();
                file.setAcceptAllFileFilterUsed(false);
                FileFilter filter = new FileNameExtensionFilter("Archivos COFRE","cofre");
                file.addChoosableFileFilter(filter);
                file.showSaveDialog(this);
                File guarda = file.getSelectedFile();
                if(guarda.getName().endsWith(".cofre")){
                    FileWriter escribir=new FileWriter(guarda,false);
                    escribir.write(textoEncCofre);
                    escribir.close();
                }else{
                    FileWriter escribir=new FileWriter(guarda + ".cofre",false);
                    escribir.write(textoEncCofre);
                    escribir.close();
                }
            } catch (IOException e) {
                    System.out.println(e);
            }
            cofre=null;
        }else{
            JOptionPane.showMessageDialog(this, "No puede haber una frase vacia.","Tablero", 0);
        }
        enc1=null;
        System.gc();
    }
    
    // Abrimos la Clave Publica 
    private void abrirClavePublica(){   
        JFileChooser file=new JFileChooser();
        file.setAcceptAllFileFilterUsed(false);
        FileFilter filter = new FileNameExtensionFilter("Archivos COFRE","cofre");
        file.addChoosableFileFilter(filter);
        file.showOpenDialog(this);
        File abre=file.getSelectedFile();
        File archivo = null;
        FileReader fr1 = null;
        FileReader fr2 = null;
        BufferedReader br1 = null;
        BufferedReader br2 = null;
        try {
            /* 
              FileReader y FileWriter son clases del paquete java.io que 
              nos permiten leer y escribir "streams" de datos y de archivos.
              Apertura del fichero y creacion de BufferedReader para poder
              hacer una lectura comoda (disponer del metodo readLine()).
            */
            fr1 = new FileReader (abre);
            br1 = new BufferedReader(fr1);
            fr2 = new FileReader (abre);
            br2 = new BufferedReader(fr2);
            String sCadena;
            // Obtenemos el numero de lineas de nuestro fichero
            long lNumeroLineas = 0;
            while ((sCadena = br1.readLine())!=null) {
                lNumeroLineas++;
            }
            // Hacemos la lectura del fichero dependiendo del numero de lineas
            for(int i=0;i<lNumeroLineas;i++){
                linea[i]=br2.readLine();
            }
            // Cargamos las lineas en la variable clavePublica
            for(int i=0;i<lNumeroLineas;i++){
                if(clavePublica.equals("")){
                    clavePublica=linea[i];
                }else{
                    clavePublica=clavePublica+linea[i];
                }
            }
         }
        catch(Exception e){
           System.out.println(e);
        }finally{
            /* 
              En el finally cerramos el fichero, para asegurarnos
              que se cierra tanto si todo va bien como si salta 
              una excepcion.
            */
           try{
              if( null != fr1 ){
                 fr1.close();
              }
              if( null != fr2 ){
                 fr2.close();
              }
           }catch (Exception e2){
              System.out.println(e2);
           }
        }
        // Llamamos al metodo abrirCofre() y deshabilitamos el boton guardar de esta clase
        abrirCofre();
        guardar.setEnabled(false);
    }
    
    private void abrirCofre(){
        int contp=1;
        String llaveCofre;
        String textoDesencCofre="";
        // Creamos una instancia de la clase Cofre()
        Cofre cofre =new Cofre();
        // Creamos una instancia de Encriptar() y le cargamos codCP
        Encriptar enc1 =new Encriptar();
        // Cargamos la frase de la clave publica en la variable llaveCofre 
        // y la cargamos en el  objeto cofre
        llaveCofre=JOptionPane.showInputDialog("Ingrese frase para cofre de Clave Publica: ");
        if ((llaveCofre != null) && (llaveCofre.length() > 0)) {
        cofre.setFrase(llaveCofre);
        // Obtenemos el codCP dependiendo de la frase cargada
        codCP=cofre.Cofretizar();
        enc1.setCod(codCP);
        // En textoDesencCofre desencriptamos los datos que estan en la variable clavePublica
        textoDesencCofre=enc1.Desencriptando(clavePublica);
        // Llamamos al metodo preCargaCP y le pasamos como parametro textoDesencCofre 
        preCargaCP(textoDesencCofre);
        // Deshabilitamos el boton Desencriptar de la clase Encriptador.
        botonDesenc=false;
        }else{
            JOptionPane.showMessageDialog(this, "No puede haber una frase vacia.","Tablero", 0);
        }
        cofre=null;
        enc1=null;
        System.gc();
    }
    
    private void preCargaCP(String textoDesenc){
        int tamTexto;
        int m=0;
        int cPreCarga=1;
        // Le damor a la variable numeroChar el valor vacio 
        char numeroChar=Character.MIN_VALUE;
        String textoNumero="";
        int largoTexto=textoDesenc.length();
        // Recorremos cod
        for(int i=0; i<cod.length; i++){
            for(int j=0; j<cod[0].length; j++){
                // Mientras el valor de la variable m sea menor a largoTexto se repite el bucle
                // m comienza por ser 0
                while(m<largoTexto){
                    // Pregunta si el caracter de textoDesenc con indice m es diferente
                    // a ' ' un espacio vacio y si es =' ' entonces sale del bucle
                    if(textoDesenc.charAt(m)!=' '){
                        // tamTexto toma el tamaño de textoDesenc
                        tamTexto = textoDesenc.length();
                        // numeroChar toma el caracter de textoDesenc con indice m
                        numeroChar = textoDesenc.charAt(m);
                        // la variable textoNumero va agregando a su contenido el caracter de numeroChar 
                        textoNumero += numeroChar;
                   
                        m++;
                    }else{
                        break;
                    }
                }
                m++;
                // Cod se va cargando con el valor entero del parseDouble del contenido
                // del String textoNumero (Tiene que ser Double porque con Integer dice fuera de rango 
                cod[i][j]=(int)Double.parseDouble(textoNumero); 
                textoNumero="";
            }
        }
        m=0;
        /*
          Recorremos cod 
          Y llamamos al metodo cargarClave con los parametros cPreCarga que se va incrementando,
          la posición de cod y la el caracter 'p' que nos dice que la clave es publica
        */
        for(int i=0; i<cod.length; i++){
            for(int j=0; j<cod[0].length; j++){   
                cargarClave(cPreCarga, cod[i][j], 'p');
                cPreCarga++;
            }
        }
    }
    
    private void cargarClave(int key, int value, char clave){
        contador=0;
        /*
          Va preguntando el valor de Key entre que rangos esta
          dependiendo de eso se incrementa contador y cod2Pos toma
          un valor que le servira para indicar el segundo indice de cod
        */
        if (key<9){
            contador=0;
            cod2Pos=key;
        }else{ if(key>8 && key<17){
            contador=1;
            cod2Pos=key-8;
        }else{ if(key>16 && key<25){
            contador=2;
            cod2Pos=key-16;
        }else{ if(key>24 && key<33){
            contador=3;
            cod2Pos=key-24;
        }else{ if(key>32 && key<41){
            contador=4;
            cod2Pos=key-32;
        }else{ if(key>40 && key<49){
            contador=5;
            cod2Pos=key-40;
        }else{ if(key>48 && key<57){
            contador=6;
            cod2Pos=key-48;
        }else{ if(key>56 && key<65){
            contador=7;
            cod2Pos=key-56;
        }else{ if(key>64 && key<73){
            contador=8;
            cod2Pos=key-64;
        }else{ if(key>72 && key<81){
            contador=9;
            cod2Pos=key-72;
        }else{ if(key>80 && key<89){
            contador=10;
            cod2Pos=key-80;
        }else{ if(key>88 && key<97){
            contador=11;
            cod2Pos=key-88;
        }else{ if(key>96 && key<105){
            contador=12;
            cod2Pos=key-96;
        }else{ if(key>104 && key<113){
            contador=13;
            cod2Pos=key-104;
        }else{ if(key>112 && key<121){
            contador=14;
            cod2Pos=key-112;
        }else{ if(key>120 && key<129){
            contador=15;
            cod2Pos=key-120;
        }}}}}}}}}}}}}}}}
        /*
          Si clave es 's' llama al metodo procesoBotonCS y modifica
          el texto del boton por el valor de key
          Si clave es 'p' llama al metodo procesoBotonCP y modifica
          el texto del boton por un "?"
        */ 
        if(clave=='s'){
            procesoBotonCS(boton,value-1);
            boton[value-1].setText(String.valueOf(key));
        }else{
            procesoBotonCP(boton,value-1);
            boton[value-1].setText("?");
        }
        // Carga el valor en cod.
        cod[contador][cod2Pos-1]=value;
        if(contador==15 && cod2Pos-1==7){
            codHabilitado=true;
        }
    }

    // Creamos la GUI del tablero
    private void createAndShowGUI() {
        frame.pack();
        frame.setSize(600, 500); 	
        frame.setLocationRelativeTo(null);
        frame.setVisible(false);
        frame.getContentPane().setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0; 
        GridBagConstraints d = new GridBagConstraints();
        d.gridwidth = GridBagConstraints.REMAINDER;
        d.fill = GridBagConstraints.BOTH;
        d.weightx = 0.1;
        d.weighty = 0.1;
        frame.add(panel1,c);
        frame.add(panel2,d);
        panel1.setLayout(new GridLayout(16,8));
        panel2.setLayout(new GridBagLayout()); 
        frame.setResizable(false);
    }
   
    public void Visible(){
        if (v==false){
            frame.repaint();
            frame.setVisible(true);
            v=true;
        }else{
            frame.repaint();
            frame.setVisible(false);
            v=false;
        }
    }
    void iniciar(){
        /*
          Recorremos el vector de botones y vamos cargando los botones del 1 al 128
        */
        for (int i=0; i<boton.length;i++){
            boton[i]= new JButton(String.valueOf(i+1));
            crearBoton1(boton,i,"boton"+(i));
        }      
            crearBoton2(abrirCP, "AbrirCP");
            crearBoton2(abrirCS, "AbrirCS");
            crearBoton2(guardar, "Guardar");
            crearBoton2(resetear, "Resetear");
            guardar.setEnabled(false);   
    }
    
    void cerrar(){
        frame.dispose();
    } 
}