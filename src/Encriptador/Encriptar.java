package Encriptador;

public class Encriptar {
    // Creamos las variables que contendran nuestro texto encriptado y desencriptado.
    private String encresult="";
    private String desencresult="";
    // Creamos nuestra matriz que contendrá el cod de la clase Tablero.
    private static int cod[][] = new int[16][8];
    // Creamos todas las matrices necesarias para trabajar con los binarios.
    private int bin1[][] = new int[16][8];
    private int bin2[][] = new int[16][8];
    private int binEnc[][] = new int[16][8];
    private int binDesenc[][] = new int[16][8];
    /*
      Creamos nuestro String que contendra una caracter o una secuencia
      de key para caracteres especiales
    */
    private String letra[]=new String[16];
    /*
      Sirven como primero y segundo indice de la matriz bin1 y bindecod
    */
    private int index1=0;
    private int index2=0;
    /*
      La variable "texto" toma una copia de textoT
      La variable "textoT" es todo el texto que se le ingresa
      al metodo encriptador o al metodo desencriptador
      La variable "tamtexto" sirve para tomar el valor
      de texto.length()
      La variable "dato" toma el valro de un registro de la matriz cod
      y calcula el indice 2 que explicamos mas arriba de la matriz
      bin1 y bindecod
      La variable "con1" sirve para recorrer la matriz bin1 y bin 2 y 
      ponerla a 0 y luego sirve de segundo indice para ambas matrices
      La constante newline es solo un salto de linea.
    */
    private String texto="";
    private String textoT="";
    private int tamTexto;
    private int dato=0;
    private int con1 = 7;
    private final static String newline = "\n";
    
    public Encriptar(){
    }
    
    /*
      Con este método iniciamos la encriptación.
      Ponemos el contenido de la variable formal textoE en la global textoT
      Preguntamos si la variable encresult esta vacia y si lo esta copiamos
      la variable textoT en texto
      Ahora decimos mientras texto no este vacio y ejecutamos
      Creamos la variable linea y le ponemos 16 lineas
      ponemos la variable n en 0
      y preguntamos si el tamaño de texto es menor a 16
      recorremos m de 15 a 0 y preguntamos si el tamaño de texto es igual a m
      en caso de ser cierto en texto guardamos el valor de texto mas una
      subcadena de la cadena linea que va de 0 a n+1
      luego incrementamos n y saliendo del bucle ponemos n a 0
      Luego le decimos que ejecute el metodo extraerCaracteres con el parametro
      tipo que es char con una 'e' que significa encriptador
      Después ejecutamos el método convertiraBinario y le pasamos por parametro
      un 0 que es el tipo y también indica que es para el encriptador y 1 para el
      desencriptador.
      Por último llamamos al método encriptación y le pasamos por parametro un 0
      que también indica que el método ha sido llamado por el método Encriptando
      Luego ponemos el contenido de la variable encresult en la variable texto
      ponemos encresult en vacio y retornamos texto.
    */
    public String Encriptando(String textoE) {
        this.textoT=textoE;
        if (encresult.equals("")) {
            texto = textoT;
        }
        while ((!texto.equals(""))) {
            String linea="|||||||||||||||";
            int n=0;
            if(texto.length()<16){
                for(int m=15;m>0;m--){                
                    if ((texto.length() == m)) {
                        texto = (texto + linea.substring(0, n+1));
                        break;
                    }
                    n++;
                }
            }
            n=0;
            this.extraerCaracteres('e');
            this.convertiraBinario(0);
            this.encriptacion(0);
        }
        texto = encresult;
        encresult="";
        return texto;
    }
    
    /*
      En este método recibimos por parametro una String y colocamos su contenido
      en la variable textoT
      Luego decimos Mientras el contenido de texto sea diferente a vacio
      llamamos al método extraerCaracteres y le indicamos que es llamado por el 
      desencriptador lo mismo hacemos con convertirBinario y encriptacion pasando
      por parametro el numero 1
      Luego ponemos el contenido de desencresult en texto
      vaciamos desencresult y retornamos texto
    */
    public String Desencriptando(String textoE) {
        this.textoT=textoE;
        texto = textoT;
        while ((!texto.equals(""))) {
            extraerCaracteres('d');
            convertiraBinario(1);
            encriptacion(1);
        }
        texto = desencresult;
        desencresult = "";
        return texto;
    }
    
    /*
      En este método extraemos los caracteres que hay en el String texto
      Primero declaramos tres variables (no hace falta explicar)
      y hacemos un switch en el que tomamos como referencia el valor de tipo
      en caso de ser 'e' abrimos un bucle de 0 a 16 con la variable "i",
      luego hacemos al principio lo mismo que en el método
      encriptando con la variable "m" y la cadena linea
      después le decimos cargamos el tamaño de texto en tamTexto
      y en la matriz letra con el indice i tomamos el primer caracter de la
      cadena texto y lo transformamos en String.
      por último cargamos en texto la subcadena de texto que toma 
      tamaño del texto - el tamaño del texto - 1
      en caso de ser 'd' sería al principio igual que 'e' pero
      después de cargar el tamaño de texto en tamTexto
      preguntamos si el tamaño de texto es mayor a 15
      en caso de serlo preguntamos si la subcadena de texto del 0 al 16 es igual
      a "||||||||KEY9KEY9" y ponemos dentro de la matriz letra con el indice i
      el caracter equivalente al numero 0 en ascii pasado como cadena y hacemos 
      algo similar con todos los demás
      sino es ninguno de los anteriores hacemos algo parecido a lo que haciamos
      en 'e'
    */
    private void extraerCaracteres(char tipo) {
        int largoTexto=texto.length();
        boolean paso =true;
        String linea="|||||||||||||||";
        switch(tipo){
            case 'e':
                for(int i=0; i<16;i++){
                    int n=0;
                    if(paso){
                    if(texto.length()<16){
                        for(int m=15;m>0;m--){                
                            if ((texto.length() == m)) {
                                texto = (texto + linea.substring(0, n+1));
                                break;
                            }
                            n++;
                        }
                    }
                    paso=false;
                    }
                    tamTexto = texto.length();
                    letra[i] = String.valueOf(texto.charAt(0));
                    texto = texto.substring(texto.length() - (tamTexto - 1));
                }
                break;
            case 'd':
                for(int i=0; i<16;i++){
                    int n=0;
                    if(paso){
                        if(texto.length()<16){
                            for(int m=15;m>0;m--){                
                                if ((texto.length() == m)) {
                                    texto = (texto + linea.substring(0, n+1));
                                    break;
                                }
                                n++;
                            }
                        }
                    paso=false;
                    }
                    tamTexto = texto.length();
                    if(texto.length() > 15){
                    if (texto.substring(0,16).equals("||||||||KEY9KEY9")){
                        letra[i]=String.valueOf((char)0);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY6KEY6")){
                        letra[i]=String.valueOf((char)1);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY2KEY2")){
                        letra[i]=String.valueOf((char)2);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY22KEY22")){
                        letra[i]=String.valueOf((char)3);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY5KEY5")){
                        letra[i]=String.valueOf((char)4);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY17KEY17")){
                        letra[i]=String.valueOf((char)5);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY24KEY24")){
                        letra[i]=String.valueOf((char)6);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY7KEY7")){
                        letra[i]=String.valueOf((char)7);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY20KEY20")){
                        letra[i]=String.valueOf((char)8);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY13KEY13")){
                        letra[i]=String.valueOf((char)9);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY15KEY15")){
                        letra[i]=String.valueOf((char)10);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY4KEY4")){
                        letra[i]=String.valueOf((char)11);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY28KEY28")){
                        letra[i]=String.valueOf((char)12);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY32KEY32")){
                        letra[i]=String.valueOf((char)13);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY11KEY11")){
                        letra[i]=String.valueOf((char)14);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY19KEY19")){
                        letra[i]=String.valueOf((char)15);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY1KEY1")){
                        letra[i]=String.valueOf((char)16);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY3KEY3")){
                        letra[i]=String.valueOf((char)17);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY29KEY29")){
                        letra[i]=String.valueOf((char)18);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY10KEY10")){
                        letra[i]=String.valueOf((char)19);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY27KEY27")){
                        letra[i]=String.valueOf((char)20);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY18KEY18")){
                        letra[i]=String.valueOf((char)21);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY14KEY14")){
                        letra[i]=String.valueOf((char)22);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY21KEY21")){
                        letra[i]=String.valueOf((char)23);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY33KEY33")){
                        letra[i]=String.valueOf((char)24);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY26KEY26")){
                        letra[i]=String.valueOf((char)25);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY16KEY16")){
                        letra[i]=String.valueOf((char)26);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY30KEY30")){
                        letra[i]=String.valueOf((char)27);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY12KEY12")){
                        letra[i]=String.valueOf((char)28);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||||KEY8KEY8")){
                        letra[i]=String.valueOf((char)29);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY31KEY31")){
                        letra[i]=String.valueOf((char)30);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY25KEY25")){
                        letra[i]=String.valueOf((char)31);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{ if (texto.substring(0,16).equals("||||||KEY23KEY23")){
                        letra[i]=String.valueOf((char)127);
                        texto = texto.substring(texto.length() - (tamTexto - 16));
                    }else{
                        tamTexto = texto.length();
                        letra[i] = String.valueOf(texto.charAt(0));
                        texto = texto.substring(texto.length() - (tamTexto - 1));
                    }}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}}
                }else{
                    tamTexto = texto.length();
                    letra[i] = String.valueOf(texto.charAt(0));
                    texto = texto.substring(texto.length() - (tamTexto - 1));   
                }
                }
            break;
        }
    }
    
    /*
      En esta parte convertimos el primer caracter de la matriz letra indice i
      en un numero ascii
     Luego elegimos con switch el tipo 0 o 1 si es encriptar o desencriptar
      en caso de ser 0 recorremos i desde 0 hasta el tamaño de la matriz numero
     y llamamos al método DecABn le pasamos como parametro la matriz numero con
      indice i, también i y un 0 o un 1 indicando si encripta o desencripta
    */
    private void convertiraBinario(int tipo) {
        int numero[]=new int[16];
        for(int i=0; i<letra.length;i++){
            numero[i] = (int)letra[i].charAt(0);
        }
        switch (tipo) {
            case 0:
                for(int i=0; i<numero.length;i++){
                    DecABn(numero[i], i, 0);
                }
                break;
            case 1:
                for(int i=0; i<numero.length;i++){
                    DecABn(numero[i], i, 1);
                }                
                break;
        }
    }
    
    /*
      En este método convertimos el numero ascii en binario
      Ponemos en 0 a bin1 y bin2
      Después preguntamos si la variable tipo tiene un 0 o un 1
      cargamos en res (resto) el resto del numero dividido 2
      en bin 1 primer indice binNro segundo indice con1 ponemos
      el valor del resto y en la variable dec ponemos el resultado entero
      de la división de dec por 2 y restamos uno al valor de con1 todo esto
      en un bucle mientras dec sea diferente a 0
      pasa algo similar cuando la variable tipo contiene 1 solo que en vez 
      de usar bin1 usamos bin2
    */
    private void DecABn(int dec, int binNro, int tipo) {
        int res;
        con1 = 0;
        while (con1 < 8) {
            bin1[binNro][con1] = 0;
            bin2[binNro][con1] = 0;
            con1++;
        }
        con1 = 7;
        if (tipo == 0) {
           do{
                res = dec % 2;
                bin1[binNro][con1] = res;
                dec = (int)dec / 2;
                con1--;
            }while(dec != 0);
        }
        else {
            do{
                res = (dec % 2);
                bin2[binNro][con1] = res;
                dec = dec/2;
                con1--;
            }while(dec != 0);
        }   
        con1 = 7;   
    }
    
    /*
      En este método realmente encriptamos o desencriptamos los bits de 
      cada binario tomado de los numeros del código ascii
      Recorremos i de 0 a cod.length y a j desde 0 hasta cod[0].length
      obtenemos el valor de dato tomado de cod y obtenemos el index1 y el index2
      Según la variable tipo sea 0 hacemos que binEnc primer indice i y segundo 
      indice j contenga el valor de bin1 primer indice index1 segundo indice 
      index2
      Según la variable tipo sea 1 hacemos que binDesenc primer indice index1 
      y segundo indice index2 contenga el valor de bin2 primer indice i segundo 
      indice j
      Después según sea tipo recorremos a i desde 0 hasta binario.length y 
      utilizamos la matriz binario para crear el binario con una forma decimal
      según sea el valor que hallemos en binEnc o binDesenc
      Creamos otro bucle y volvemos a recorrer i y cargamos en numero indice i
      el valor devuelto del método BnADec pasando por parametro a binario indice i
      Volvemos a recorrer i y cargamos en letra indice i el valor de String del
      caracter obtenido de la variable numero indice i
      Segun sea tipo recorremos i de 0 hasta letra.length y luego hacemos un switch
      del número ascii correspondiente del primer caracter de la matriz letra 
      indice i en cada caso sustituir letra indice i por el texto correspondiente
      luego encresult es igual a encresult mas la letra indice i
      si el tipo es 1 se activa el sino en donde
      recorremos i desde 0 hasta letra.length y preguntamos
      si el primer caracter de letra indice i es igual al caracter '|'
      incrementamos letraCon
      recorremos i de forma inversa desde letra.length menos 1 hasta 
      letra.length  menos letraCon menos 1 y vaciamos letra indice i
      recorremos i desde 0 hasta letra.length y cargamos en desencresult
      el valor de desencresult mas los que haya en letra indice i
    */
    private void encriptacion(int tipo) {
        int binario[]=new int[16];
        int numero[]=new int[16];
        for (int i = 0; i < cod.length; i++) {
            for (int j = 0; j < cod[0].length; j++) {
                dato = cod[i][j];
                if (dato<9){
                    index1 = 0;
                    index2 = (dato - 1);
                }else{ if(dato>8 && dato<17){
                    dato -= 8;
                    index1 = 1;
                    index2 = (dato - 1);
                }else{ if(dato>16 && dato<25){
                    dato -= 16;
                    index1 = 2;
                    index2 = (dato - 1);
                }else{ if(dato>24 && dato<33){
                    dato -= 24;
                    index1 = 3;
                    index2 = (dato - 1);
                }else{ if(dato>32 && dato<41){
                    dato -= 32;
                    index1 = 4;
                    index2 = (dato - 1);
                }else{ if(dato>40 && dato<49){
                    dato -= 40;
                    index1 = 5;
                    index2 = (dato - 1);
                }else{ if(dato>48 && dato<57){
                    dato -= 48;
                    index1 = 6;
                    index2 = (dato - 1);
                }else{ if(dato>56 && dato<65){
                    dato -= 56;
                    index1 = 7;
                    index2 = (dato - 1);
                }else{ if(dato>64 && dato<73){
                    dato -= 64;
                    index1 = 8;
                    index2 = (dato - 1);
                }else{ if(dato>72 && dato<81){
                    dato -= 72;
                    index1 = 9;
                    index2 = (dato - 1);
                }else{ if(dato>80 && dato<89){
                    dato -= 80;
                    index1 = 10;
                    index2 = (dato - 1);
                }else{ if(dato>88 && dato<97){
                    dato -= 88;
                    index1 = 11;
                    index2 = (dato - 1);
                }else{ if(dato>96 && dato<105){
                    dato -= 96;
                    index1 = 12;
                    index2 = (dato - 1);
                }else{ if(dato>104 && dato<113){
                    dato -= 104;
                    index1 = 13;
                    index2 = (dato - 1);
                }else{ if(dato>112 && dato<121){
                    dato -= 112;
                    index1 = 14;
                    index2 = (dato - 1);
                }else{ if(dato>120 && dato<129){
                    dato -= 120;
                    index1 = 15;
                    index2 = (dato - 1);
                }}}}}}}}}}}}}}}}
                switch (tipo) {
                    case 0:
                        binEnc[i][j] = bin1[index1][index2];
                        break;
                    case 1:
                        binDesenc[index1][index2] = bin2[i][j];
                        break;
                }
            }
        }
        switch (tipo) {
                    case 0:
                        for(int i=0; i<binario.length;i++){
                            binario[i] = ((binEnc[i][0] * 10000000) 
                                    + ((binEnc[i][1] * 1000000) 
                                    + ((binEnc[i][2] * 100000) 
                                    + ((binEnc[i][3] * 10000) 
                                    + ((binEnc[i][4] * 1000) 
                                    + ((binEnc[i][5] * 100) 
                                    + ((binEnc[i][6] * 10) 
                                    + (binEnc[i][7] * 1))))))));
                        }
                        break;
                    case 1:
                        for(int i=0; i<binario.length;i++){
                            binario[i] = ((binDesenc[i][0] * 10000000) 
                                    + ((binDesenc[i][1] * 1000000) 
                                    + ((binDesenc[i][2] * 100000) 
                                    + ((binDesenc[i][3] * 10000) 
                                    + ((binDesenc[i][4] * 1000) 
                                    + ((binDesenc[i][5] * 100) 
                                    + ((binDesenc[i][6] * 10) 
                                    + (binDesenc[i][7] * 1))))))));
                        }
                        break;
        }
        for(int i=0; i<binario.length;i++){
            numero[i]=BnADec(binario[i]);
        }
        for(int i=0; i<letra.length;i++){
            letra[i] = String.valueOf((char)numero[i]);
        }
        if ((tipo == 0)) {
            for(int i=0; i<letra.length;i++){
                switch((int)letra[i].charAt(0)){
                    case 0:
                        letra[i]="||||||||KEY9KEY9";
                        break;
                    case 1:
                        letra[i]="||||||||KEY6KEY6";
                        break;
                    case 2:
                        letra[i]="||||||||KEY2KEY2";
                        break;
                    case 3:
                        letra[i]="||||||KEY22KEY22";
                        break;
                    case 4:
                        letra[i]="||||||||KEY5KEY5";
                        break;
                    case 5:
                        letra[i]="||||||KEY17KEY17";
                        break;
                    case 6:
                        letra[i]="||||||KEY24KEY24";
                        break;
                    case 7:
                        letra[i]="||||||||KEY7KEY7";
                        break;
                    case 8:
                        letra[i]="||||||KEY20KEY20";
                        break;
                    case 9:
                        letra[i]="||||||KEY13KEY13";
                        break;
                    case 10:
                        letra[i]="||||||KEY15KEY15";
                        break;
                    case 11:
                        letra[i]="||||||||KEY4KEY4";
                        break;
                    case 12:
                        letra[i]="||||||KEY28KEY28";
                        break;
                    case 13:
                        letra[i]="||||||KEY32KEY32";
                        break;
                    case 14:
                        letra[i]="||||||KEY11KEY11";
                        break;
                    case 15:
                        letra[i]="||||||KEY19KEY19";
                        break;
                    case 16:
                        letra[i]="||||||||KEY1KEY1";
                        break;
                    case 17:
                        letra[i]="||||||||KEY3KEY3";
                        break;
                    case 18:
                        letra[i]="||||||KEY29KEY29";
                        break;
                    case 19:
                        letra[i]="||||||KEY10KEY10";
                        break;
                    case 20:
                        letra[i]="||||||KEY27KEY27";
                        break;
                    case 21:
                        letra[i]="||||||KEY18KEY18";
                        break;
                    case 22:
                        letra[i]="||||||KEY14KEY14";
                        break;
                    case 23:
                        letra[i]="||||||KEY21KEY21";
                        break;
                    case 24:
                        letra[i]="||||||KEY33KEY33";
                        break;
                    case 25:
                        letra[i]="||||||KEY26KEY26";
                        break;
                    case 26:
                        letra[i]="||||||KEY16KEY16";
                        break;
                    case 27:
                        letra[i]="||||||KEY30KEY30";
                        break;
                    case 28:
                        letra[i]="||||||KEY12KEY12";
                        break;
                    case 29:
                        letra[i]="||||||||KEY8KEY8";
                        break;
                    case 30:
                        letra[i]="||||||KEY31KEY31";
                        break;
                    case 31:
                        letra[i]="||||||KEY25KEY25";
                        break;
                    case 127:
                        letra[i]="||||||KEY23KEY23";
                        break;
                }
                encresult = encresult + letra[i];
            }
        }else{
            int letraCon=0;
            for(int i=0;i<letra.length;i++){
                if(letra[i].charAt(0)=='|'){
                    letraCon++;      
                }
            }
            for(int i=letra.length-1;i>letra.length-letraCon-1;i--){
                letra[i]="";
            }
            for(int i=0; i<letra.length;i++){
                desencresult = desencresult + letra[i];
            }
        }
    }
    
    /*
      Con este método invertimos una variable String
    */
    private static String reverseIt(String source) {
        int i, len = source.length();
        StringBuilder dest = new StringBuilder(len);
        for (i = (len - 1); i >= 0; i--){
            dest.append(source.charAt(i));
        }
        return dest.toString();
    }
    
    /*
      Con este método transformamos un binario en un decimal
      Primero creamos las variables necesarias
      Tomamos el valor de v y lo pasamos a la variable bin como una cadena
      Luego invertimos la cadena y obtenemos el tamaño de bin en largo
      Iniciamos un bucle For con i desde 1 hasta largo dentro cargamos en X
      el entero de sacar 2 exponente I - 1
      en valor ponemos el valor string del caracter de bin en la posición I - 1
      en numero ponemos el entero de valor
      en v cargamos el valor de v + el numero multiplicado X
      y por último retornamos v
    */
    private int BnADec(int v) {
        String bin;
        int largo;
        int numero;
        String valor;
        int X;
        int I;
        bin = String.valueOf(v);
        bin = reverseIt(bin);
        largo = bin.length();
        v = 0;
        for (I = 1; I <= largo; I++) {
            X = (int)(Math.pow(2, (I - 1)));
            valor=String.valueOf(bin.charAt(I - 1));
            numero=Integer.parseInt(valor);
            v += (numero * X);
        }
        return v;
    }
    
    /*
      Con este método obtenemos el cod necesario para operar.
    */
    public void setCod(int[][] cod){
        this.cod=cod;
    }
  
    
}