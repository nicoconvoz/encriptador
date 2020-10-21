
package Encriptador;

import javax.swing.JOptionPane;

public class Cofre {
    /*
      Declaramos la matriz cod que contendrá el codigo con el que encriptaremos
      el cofre.
    */
    private int cod[][] = new int[16][8];
    // Variable texto de entrada (textoE)
    private String textoE="";
    // Para tomar el tamaño de textoE
    private int largoTexto;
    // Matriz letra para obtener los caracteres de textoE
    private char letra[]=new char[128];
    
    public Cofre(){        
    }
    
    /*
      El usuario setea el texto de la frase en la variable textoE
    */
    public void setFrase(String textoE){
        this.textoE=textoE;
    }
    
    /*
      Creamos el cofre llamando a 3 métodos y devolviendo cod
    */
    public int[][] Cofretizar(){
        extraerCaracteres();
        cargarCod();
        generarCofre();
        return cod;
    }
    
    /*
      En extraer caracteres hacemos algo similar al mismo método de la clase 
      Encriptar
    */
    private void extraerCaracteres(){
        int tamTexto=0;
        if(textoE.equals("")){
            JOptionPane.showMessageDialog(null, "No puede haber una frase vacia.","Tablero", 0);
        }else{
            largoTexto=textoE.length();
            for(int i=0; i<largoTexto;i++){
                tamTexto = textoE.length();
                letra[i] = textoE.charAt(0);
                textoE = textoE.substring(textoE.length() - (tamTexto - 1));
            }
        }
    }
    
    /*
      Cargamos cod con los numeros del 1 al 128 en orden ascendente dependiendo
      de los indices i y j
    */
    private void cargarCod(){
        int contador=0;
        for(int i=0;i<cod.length;i++){
            for(int j=0; j<cod[0].length;j++){
                contador++;
                cod[i][j]=contador;
            }         
        }
    }
    
    /*
      Aquí lo que hacemos es recorrer la variable i desde 0 hasta largoTexto
      y en el caso de que sea cada letra llamamos al método orden con diversos
      parametros en primer lugar el numero 1 (n1) en segundo lugar el numero 2 
      (n2) en tercer lugar porcion en cuarto lugar forma y en quinto lugar 
      dirección (dir)
      n1 y n2 pueden ir desde el 0 hasta el 15
      porcion puede ser total (t) o la mitad izquierda (+) o la mitad derecha (-)
      forma puede ser normal (n) o cruzada (c)
      dir puede ser positiva (+) o inversa (-)
    */
    private void generarCofre(){
        for (int i=0; i<largoTexto; i++){
            switch(letra[i]){
            case 'a': case 'A':
                orden(0,1,'t','n','+');
                orden(3,4,'t','c','-');
                orden(2,3,'+','c','+');
                orden(5,6,'-','n','-');
                orden(8,9,'t','n','+');
                orden(11,12,'t','c','-');
                orden(10,11,'+','c','+');
                orden(13,14,'-','n','-');
                break;
            case 'b': case 'B':
                orden(2,3,'t','n','+');
                orden(5,6,'t','c','-');
                orden(0,1,'+','c','+');
                orden(3,4,'-','n','-');
                orden(10,11,'t','n','+');
                orden(13,14,'t','c','-');
                orden(8,9,'+','c','+');
                orden(11,12,'-','n','-');
                break;
            case 'c': case 'C':
                orden(4,3,'t','n','+');
                orden(1,0,'t','c','-');
                orden(6,5,'+','c','+');
                orden(3,2,'-','n','-');
                orden(12,11,'t','n','+');
                orden(9,8,'t','c','-');
                orden(14,13,'+','c','+');
                orden(11,10,'-','n','-');
                break;
            case 'd': case 'D':
                orden(6,5,'t','n','+');
                orden(3,2,'t','c','-');
                orden(0,1,'+','c','+');
                orden(4,3,'-','n','-');
                orden(14,13,'t','n','+');
                orden(11,10,'t','c','-');
                orden(9,8,'+','c','+');
                orden(12,11,'-','n','-');
                break;
            case 'e': case 'E':
                orden(0,7,'t','n','+');
                orden(1,2,'t','c','-');
                orden(4,5,'+','c','+');
                orden(3,6,'-','n','-');
                orden(8,15,'t','n','+');
                orden(9,10,'t','c','-');
                orden(12,13,'+','c','+');
                orden(11,14,'-','n','-');
                break;
            case 'f': case 'F':
                orden(3,6,'t','n','+');
                orden(4,5,'t','c','-');
                orden(1,2,'+','c','+');
                orden(0,7,'-','n','-');
                orden(11,14,'t','n','+');
                orden(12,13,'t','c','-');
                orden(9,10,'+','c','+');
                orden(8,15,'-','n','-');
                break;
            case 'g': case 'G':
                orden(2,1,'t','n','+');
                orden(7,0,'t','c','-');
                orden(6,3,'+','c','+');
                orden(5,4,'-','n','-');
                orden(15,8,'t','n','+');
                orden(10,9,'t','c','-');
                orden(13,12,'+','c','+');
                orden(14,11,'-','n','-');
                break;
            case 'h': case 'H':
                orden(5,4,'t','n','+');
                orden(6,3,'t','c','-');
                orden(7,0,'+','c','+');
                orden(2,1,'-','n','-');
                orden(13,12,'t','n','+');
                orden(14,11,'t','c','-');
                orden(15,8,'+','c','+');
                orden(10,9,'-','n','-');
                break;
            case 'i': case 'I':
                orden(9,1,'t','n','+');
                orden(12,4,'t','c','-');
                orden(11,3,'+','c','+');
                orden(14,6,'-','n','-');
                orden(8,0,'t','n','+');
                orden(11,3,'t','c','-');
                orden(10,2,'+','c','+');
                orden(13,5,'-','n','-');
                break;
            case 'j': case 'J':
                orden(11,3,'t','n','+');
                orden(14,6,'t','c','-');
                orden(9,1,'+','c','+');
                orden(12,4,'-','n','-');
                orden(10,2,'t','n','+');
                orden(13,5,'t','c','-');
                orden(8,0,'+','c','+');
                orden(11,3,'-','n','-');
                break;
            case 'k': case 'K':
                orden(11,3,'t','n','+');
                orden(8,0,'t','c','-');
                orden(13,5,'+','c','+');
                orden(10,2,'-','n','-');
                orden(12,4,'t','n','+');
                orden(9,0,'t','c','-');
                orden(14,5,'+','c','+');
                orden(11,2,'-','n','-');
                break;
            case 'l': case 'L':
                orden(13,5,'t','n','+');
                orden(10,2,'t','c','-');
                orden(8,1,'+','c','+');
                orden(11,3,'-','n','-');
                orden(14,6,'t','n','+');
                orden(11,3,'t','c','-');
                orden(9,0,'+','c','+');
                orden(12,4,'-','n','-');
                break;
            case 'm': case 'M':
                orden(15,7,'t','n','+');
                orden(10,2,'t','c','-');
                orden(13,5,'+','c','+');
                orden(14,6,'-','n','-');
                orden(8,0,'t','n','+');
                orden(9,1,'t','c','-');
                orden(12,4,'+','c','+');
                orden(11,3,'-','n','-');
                break;
            case 'n': case 'N':
                orden(14,6,'t','n','+');
                orden(13,5,'t','c','-');
                orden(10,2,'+','c','+');
                orden(15,7,'-','n','-');
                orden(11,3,'t','n','+');
                orden(12,4,'t','c','-');
                orden(9,1,'+','c','+');
                orden(8,0,'-','n','-');
                break;
            case 'ñ': case 'Ñ':
                orden(8,1,'t','n','+');
                orden(9,0,'t','c','-');
                orden(12,3,'+','c','+');
                orden(11,4,'-','n','-');
                orden(15,2,'t','n','+');
                orden(10,7,'t','c','-');
                orden(13,6,'+','c','+');
                orden(14,5,'-','n','-');
                break;
            case 'o': case 'O':
                orden(12,4,'t','n','+');
                orden(11,3,'t','c','-');
                orden(8,0,'+','c','+');
                orden(9,1,'-','n','-');
                orden(13,5,'t','n','+');
                orden(14,6,'t','c','-');
                orden(15,7,'+','c','+');
                orden(10,2,'-','n','-');
                break;
            case 'p': case 'P':
                orden(0,8,'t','n','+');
                orden(3,11,'t','c','-');
                orden(2,10,'+','c','+');
                orden(5,13,'-','n','-');
                orden(1,9,'t','n','+');
                orden(4,12,'t','c','-');
                orden(3,11,'+','c','+');
                orden(6,14,'-','n','-');
                break;
            case 'q': case 'Q':
                orden(2,10,'t','n','+');
                orden(5,13,'t','c','-');
                orden(0,8,'+','c','+');
                orden(3,11,'-','n','-');
                orden(3,11,'t','n','+');
                orden(6,14,'t','c','-');
                orden(1,9,'+','c','+');
                orden(4,12,'-','n','-');
                break;
            case 'r': case 'R':
                orden(4,12,'t','n','+');
                orden(1,9,'t','c','-');
                orden(6,14,'+','c','+');
                orden(3,11,'-','n','-');
                orden(3,11,'t','n','+');
                orden(0,8,'t','c','-');
                orden(5,13,'+','c','+');
                orden(2,10,'-','n','-');
                break;
            case 's': case 'S':
                orden(6,14,'t','n','+');
                orden(3,11,'t','c','-');
                orden(0,9,'+','c','+');
                orden(4,12,'-','n','-');
                orden(5,13,'t','n','+');
                orden(2,10,'t','c','-');
                orden(1,8,'+','c','+');
                orden(3,11,'-','n','-');
                break;
            case 't': case 'T':
                orden(0,8,'t','n','+');
                orden(1,9,'t','c','-');
                orden(4,12,'+','c','+');
                orden(3,11,'-','n','-');
                orden(7,15,'t','n','+');
                orden(2,10,'t','c','-');
                orden(5,13,'+','c','+');
                orden(6,14,'-','n','-');
                break;
            case 'u': case 'U':
                orden(3,11,'t','n','+');
                orden(4,12,'t','c','-');
                orden(1,9,'+','c','+');
                orden(0,8,'-','n','-');
                orden(6,14,'t','n','+');
                orden(5,13,'t','c','-');
                orden(2,10,'+','c','+');
                orden(7,15,'-','n','-');
                break;
            case 'v': case 'V':
                orden(2,8,'t','n','+');
                orden(7,9,'t','c','-');
                orden(6,12,'+','c','+');
                orden(5,11,'-','n','-');
                orden(1,8,'t','n','+');
                orden(0,9,'t','c','-');
                orden(3,12,'+','c','+');
                orden(4,11,'-','n','-');
                break;
            case 'w': case 'W':
                orden(5,13,'t','n','+');
                orden(6,14,'t','c','-');
                orden(7,15,'+','c','+');
                orden(2,10,'-','n','-');
                orden(4,12,'t','n','+');
                orden(3,11,'t','c','-');
                orden(0,8,'+','c','+');
                orden(1,9,'-','n','-');
                break;
            default:
                orden(0,15,'t','n','+');
                orden(1,14,'t','c','-');
                orden(2,13,'+','c','+');
                orden(3,12,'-','n','-');
                orden(4,11,'t','n','+');
                orden(5,10,'t','c','-');
                orden(6,9,'+','c','+');
                orden(7,8,'-','n','-');
                break;
            }
        }
    }
    
    /*
      Aca sucede toda la magia del cofre
      Se ponen en switch todas las opciones
      Un ejemplo en porcion 't' forma 'n' dir '+'
      recorremos con un for j desde 0 hasta el tamaño de cod[0]
      grabamos en la variable temp el contenido de cod[n1][j]
      y en ese mismo punto de la matriz guardamos cod[n2][j]
      y al último punto de la matriz le cargamos el contenido de temp
      de otras diversas formas configuramos los datos de cod para que 
      con la frase se encripte bien.
    */
    private void orden(int n1,int n2, char porcion, char forma, char dir){
        int temp;
        int n=0;
        switch(porcion){
        case 't':
            switch(forma){
            case 'n':
                switch(dir){
                case '+':
                    for (int j=0;j<cod[0].length;j++){
                        temp=cod[n1][j];
                        cod[n1][j]=cod[n2][j];
                        cod[n2][j]=temp;
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=cod[0].length-1;j>=0;j--){
                        temp=cod[n1][n];
                        cod[n1][n]=cod[n2][j];
                        cod[n2][j]=temp;
                        n++;
                    }
                    break;
                }
                break;
            case 'c':
                switch(dir){
                case '+':
                    for (int j=0;j<cod[0].length;j++){
                        temp=cod[n1][j];
                        if(j==cod[0].length - 1){
                            cod[n1][j]=cod[n2][0];
                            cod[n2][0]=temp;
                        }else{
                            cod[n1][j]=cod[n2][j+1];
                            cod[n2][j+1]=temp;  
                        }
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=cod[0].length-1;j>=0;j--){
                        temp=cod[n1][n];
                        if(j==0){
                            cod[n1][n]=cod[n2][cod[0].length-1];
                            cod[n2][cod[0].length-1]=temp;
                        }else{
                            cod[n1][n]=cod[n2][j-1];
                            cod[n2][j-1]=temp;  
                        }
                        n++;
                    }
                    break;
                }
                break;
            }
            break;
        case '+':
            switch(forma){
            case 'n':
                switch(dir){
                case '+':
                    for (int j=0;j<cod[0].length/2;j++){
                        temp=cod[n1][j];
                        cod[n1][j]=cod[n2][j];
                        cod[n2][j]=temp;
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=(cod[0].length/2)-1;j>=0;j--){
                        temp=cod[n1][n];
                        cod[n1][n]=cod[n2][j];
                        cod[n2][j]=temp;
                        n++;
                    }
                    break;
                }
                break;
            case 'c':
                switch(dir){
                case '+':
                    for (int j=0;j<cod[0].length/2;j++){
                        temp=cod[n1][j];
                        if(j==(cod[0].length/2) - 1){
                            cod[n1][j]=cod[n2][0];
                            cod[n2][0]=temp;
                        }else{
                            cod[n1][j]=cod[n2][j+1];
                            cod[n2][j+1]=temp;  
                        }
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=(cod[0].length/2)-1;j>=0;j--){
                        temp=cod[n1][n];
                        if(j==0){
                            cod[n1][n]=cod[n2][(cod[0].length/2)-1];
                            cod[n2][(cod[0].length/2)-1]=temp;
                        }else{
                            cod[n1][n]=cod[n2][j-1];
                            cod[n2][j-1]=temp;  
                        }
                        n++;
                    }
                    break;
                }
                break;
            }
            break;
        case '-':
            switch(forma){
            case 'n':
                switch(dir){
                case '+':
                    for (int j=(cod[0].length/2)-1;j<cod[0].length;j++){
                        temp=cod[n1][j];
                        cod[n1][j]=cod[n2][j];
                        cod[n2][j]=temp;
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=cod[0].length-1;j>=(cod[0].length/2)-1;j--){
                        temp=cod[n1][n];
                        cod[n1][n]=cod[n2][j];
                        cod[n2][j]=temp;
                        n++;
                    }
                    break;
                }
                break;
            case 'c':
                switch(dir){
                case '+':
                    for (int j=(cod[0].length/2)-1;j<cod[0].length;j++){
                        temp=cod[n1][j];
                        if(j==cod[0].length - 1){
                            cod[n1][j]=cod[n2][(cod[0].length/2)-1];
                            cod[n2][(cod[0].length/2)-1]=temp;
                        }else{
                            cod[n1][j]=cod[n2][j+1];
                            cod[n2][j+1]=temp;  
                        }
                    }
                    break;
                case '-':
                    n=0;
                    for (int j=cod[0].length-1;j>=(cod[0].length/2)-1;j--){
                        temp=cod[n1][n];
                        if(j==(cod[0].length/2)-1){
                            cod[n1][n]=cod[n2][cod[0].length-1];
                            cod[n2][cod[0].length-1]=temp;
                        }else{
                            cod[n1][n]=cod[n2][j-1];
                            cod[n2][j-1]=temp;  
                        }
                        n++;
                    }
                    break;
                }
                break;
            }
        } 
    }
    
}