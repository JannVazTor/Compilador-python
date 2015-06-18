import java.util.ArrayList;
/**
 *
 * @author JANN
 */

public class Lexico {
    private String linea;
    private int numRenglon;
    int estado = 0, columna, valorMT;

//    Lista cabeza = null;
//    Lista p;
    
    char caracter;
    String lexema = "";
    String imprimirError = "";
    final char blanco = (char)32;
    final char tab = (char)9;
    final char quote = (char)39;
    final char nuevalinea = (char)10;
    final char ret = (char)13;
    int aux = 0;
    String imprimir="";
    boolean bandera = true;
    private boolean comentario;
    Form_Home home;
    ArrayList<Integer> token = new ArrayList<>();
    ArrayList<Integer> renglon = new ArrayList<>();
    
    Lexico (){
   
    }
    
    Lexico(String linea, int numRenglon){
        this.linea = linea;
        this.numRenglon = numRenglon;
    }
    
    public void lexico(){
            for (int i = 0; i < this.linea.length(); i++) {
                caracter = linea.charAt(i);
//                if (bandera == true) {
//                    break;
//                }
                if (caracter == quote && caracter == linea.charAt(i+1) && caracter == linea.charAt(i+2)) {
                    i = i+2;
                    bandera=false;
                    aux+=1;
                    break;
                }else{

                    if (Character.isLetter(caracter)) {
                        columna = 1;
                    }else if (Character.isDigit(caracter)) {
                        columna = 2;
                    }else{
                        switch(caracter){
                            case '_': columna = 0;
                                break;
                            case '.': columna = 3;
                                break;
                            case quote: columna = 4;
                                break;
                            case '#': columna = 5;
                                break;
                            case '+': columna = 7;
                                break;
                            case '-': columna = 8;
                                break;
                            case '*': columna = 9;
                                break;
                            case '/': columna = 10;
                                break;
                            case '=': columna = 11;
                                break;
                            case '!': columna = 12;
                                break;
                            case '<': columna = 13;
                                break;
                            case '>': columna = 14;
                                break;
                            case '&': columna = 15;
                                break;
                            case '^': columna = 16;
                                break;
                            case '|': columna = 17;
                                break;
                            case nuevalinea: columna = 18;
                                break;
                            case tab: columna = 19;
                                break;
                            case blanco : columna = 20;
                                break;
                            case ret : columna = 22;
                                break;
                            case '(' : columna = 23;
                                break;
                            case ')' : columna = 24;
                                break;
                            case ':' : columna = 25;
                                break;
                            case ',' : columna = 26;
                                break;
                            default:
                                columna = 21;
                                break;
                        }
                    }
                }
                valorMT = MatrizTransicion[estado][columna];
                if (valorMT < 100) {
                    estado = valorMT;
                    if (estado == 0) {
                        lexema = "";
                    }else{
                        lexema = lexema + caracter;
                    }
                }else if (valorMT >= 100 && valorMT < 500) {
//                    if (valorMT == 100) {
//                        MatrizPalabrasReservadas();
//                    }
                    if (valorMT == 100 || valorMT == 111 || valorMT == 114 || valorMT == 116 || valorMT == 102 || valorMT == 101) {
                        MatrizPalabrasReservadas();
                        i--;
                    }else{
                        lexema = lexema + caracter;
                    }
                        token.add(valorMT);
                        renglon.add(numRenglon);
//                        insertarNodo();
                        imprimir += "Imprimir Nodo: " + lexema +" "+ valorMT +" "+ numRenglon + "\n";
                        estado = 0;
                        lexema = "";
                }else{
                    ImprimirError();
                    
                }
        }  
    }
    
    int MatrizTransicion [][] = {
        //0     1       2       3       4       5       6       7       8       9       10      11      12      13      14      15      16      17      18      19      20      21      22        23      24        25      26
        //_     letter	digit	.	'	#	‘’’	+	-	*	/	=	!	<	>	&	^	|	intro	Tab      blank   OC     rc       (        )         :       ,
/*0*/    {1	,1	,2	,1	,5	,6	,7	,106	,107	,108	,109	,8	,9	,10	,11	,117	,118	,119	,120	,0      ,0      ,504,   0,      122,      123,      124,    121},
/*1*/    {1	,1	,1	,1	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100	,100    ,100    ,100,   100,    100,      100,      100,    100},  
/*2*/    {102	,102	,2	,3	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102	,102    ,102    ,504,   102,    102,      102,      102,    102},
/*3*/    {500	,500	,4	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500	,500    ,500    ,500,   500,    500,      500,      500,    500},
/*4*/    {101	,101	,4	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101	,101    ,101    ,101,   101,    101,      101,      101,    101},
/*5*/    {5	,5	,5	,5	,103	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,5	,501	,5      ,503    ,5,     501,    5,        5,        5,      5},
/*6*/    {6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,6	,104	,6      ,6      ,6,     104,    6,        6,        6,      6},
/*7*/    {7	,7	,7	,7	,7	,7	,105	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7	,7      ,7      ,7,     7,      7,        7,        7,      7},
/*8*/    {111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,111	,110	,111	,111	,111	,111	,111	,111	,111	,111    ,111    ,111,   111,    111,      111,      111,    111},
/*9*/    {502	,502	,502	,502	,502	,502	,502	,502	,502	,502	,502	,112	,502	,502	,502	,502	,502	,502	,502	,502    ,502    ,502,   502,    502,      502,      502,    502},
/*10*/   {114	,114	,114	,114	,114	,114	,114	,114	,114	,114	,114	,113	,114	,114	,114	,114	,114	,114	,114	,114    ,114    ,114,   114,    114,      114,      114,    114},
/*11*/   {116	,116	,116	,116	,116	,116	,116	,116	,116	,116	,116	,115	,116	,116	,116	,116	,116	,116	,116	,116    ,116    ,116,   116,    116,      116,      116,    116},
/*12*/    };  
    
    String MatrizPalabrasReservadas [][] = {
        {"break",   "201"},
        {"continue","202"},
        {"else",    "203"},
        {"if",      "204"},
        {"in",      "205"},
        {"is",      "206"},
        {"print",   "209"},
        {"while",   "210"},
        {"true",    "211"},
        {"false",   "212"},
        {"input",   "213"},
    };
    String MatrizErrores [][] = {
        {"Se esperaba un Digito despues del punto","500"},
        {"Se esperaban mas caracteres","501"},
        {"Se estaba un '=' seguido del '!' ","502"},
        {"Se esperaba un apostrofe  \'  ","503"},
        {"Caracter no Esperado  ","504"}
    };
//    
//    private void insertarNodo() {
//        Lista nodo = new Lista(lexema,valorMT,numRenglon);
//        nodo.sig = null;
//        if (cabeza == null) {
//            cabeza = nodo;
//            p = cabeza;
//        }else{
//            p.sig = nodo;
//            p = p.sig;
//        }
//    }
//    public int[] tokens(){
//        ArrayList<Integer> token = new ArrayList<>();
//        
//        p = cabeza;
//            for (int i = 0; p != null; i++) {
//                token.add(p.token);
//                System.out.print( p.token+"\n");
//                p = p.sig;
//            }
//        int tokenss[] = new int[token.size()];
//            for (int i = 0; i < token.size(); i++) {
//                tokenss[i] = token.get(i);
//        }
//        return tokenss;
//    }
    public ArrayList<Integer> token(){ 
        return this.token;
    }
    
    private void ImprimirError() {
        for (int i = 0; i < MatrizErrores.length; i++) {
            if (valorMT == Integer.valueOf(MatrizErrores[i][1])) {
                imprimirError = " Error: "+MatrizErrores[i][1]+"\n"+" Detalles: "+MatrizErrores[i][0]+" en el renglon: "+numRenglon+"\n";
            }
        }
    }

    private void MatrizPalabrasReservadas() {
        for (int i = 0; i < MatrizPalabrasReservadas.length; i++) {
            if (lexema.equals(MatrizPalabrasReservadas[i][0])) {
                valorMT = Integer.valueOf(MatrizPalabrasReservadas[i][1]);
            }
        }
    }
}