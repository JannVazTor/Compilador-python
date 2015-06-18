import java.util.ArrayList;
import javax.swing.JOptionPane;
/**
 *
 * @author JANN
 */

public class Sintaxis {
    ArrayList<Integer> token = new ArrayList<>();
    ArrayList<Integer> renglon = new ArrayList<>();
    int p = 0;
    int ifexist=0,elseExist=0;
    String errores[];
    boolean banIdent = true;
    private boolean bandera,bandNum,bandBin = false;
    
    Sintaxis(){
    }
    
    public void variables(ArrayList<Integer> token, ArrayList<Integer> renglon){
        this.token = token;
        this.renglon = renglon;
        bloque();
    }
    
    private void bloque(){
        while(p<token.size()){
    //-------------------------------------------------------------------------------------------------------------            
                if(p < token.size() && token.get(p) == 204){//if
                        p++;
                        ifexist++;
                        if (p < token.size() && esExpresion()) {
                            if (p < token.size() && esDosPuntos(token.get(p))) {
                                p++;
                                if (p < token.size() && esElse(token.get(p))) {
                                    elseExist++;
                                    p++;
                                        if (p < token.size() && esDosPuntos(token.get(p))) {
                                            p++;
                                            if (p<token.size()) {
                                                bloque();
                                            }else{
                                                JOptionPane.showMessageDialog(null,finSintaxis());
                                                finSecuencia();
                                                break;
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null,erroresSintaxis(504,renglon.get(p-1)));
                                            finSecuencia();
                                            break;
                                        }
                                }else{
                                    if (p < token.size()) {
                                       bloque(); 
                                    }else{
                                       JOptionPane.showMessageDialog(null, finSintaxis()); 
                                       finSecuencia();
                                       break;
                                    }                       
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,erroresSintaxis(504,renglon.get(p-1)));
                                finSecuencia();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,erroresSintaxis(505,renglon.get(p-1)));
                            finSecuencia();
                            break;
                        }   
                }else{
    //-------------------------------------------------------------------------------------------------------------  
                    if (p < token.size() &&  token.get(p) == 210) {//while
                        p++;
                        if (p < token.size() &&  esExpresion()) {
                            if (p < token.size() && esDosPuntos(token.get(p))) {
                                p++;
                                if (p < token.size() && esElse(token.get(p))) {
                                    elseExist++;
                                    p++;
                                    if (p < token.size() && esDosPuntos(token.get(p))) {
                                        p++;
                                        if (p<token.size()) {
                                            bloque();
                                        }else{
                                            JOptionPane.showMessageDialog(null,finSintaxis()); 
                                            finSecuencia();
                                            break;
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null,erroresSintaxis(504,renglon.get(p-1)));
                                        finSecuencia();
                                        break;
                                    }
                                }else{
                                    if (p < token.size()) {
                                       bloque(); 
                                    }else{
                                       JOptionPane.showMessageDialog(null,finSintaxis()); 
                                       finSecuencia();
                                       break;
                                    }                       
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,erroresSintaxis(504,renglon.get(p-1)));
                                finSecuencia();
                                break;
                            }
                        }else{
                            JOptionPane.showMessageDialog(null,erroresSintaxis(506,renglon.get(p-1)));
                            finSecuencia();
                            break;
                        }   
                    }else{
    //-------------------------------------------------------------------------------------------------------------  
                        if (p < token.size() && token.get(p) == 100) {//identificador
                            p++;
                            if (Identificadores()) { 
                                if (p < token.size() && esIgual(token.get(p))) {
                                    p++;
                                    if (p < token.size() && esInput(token.get(p))) {
                                        p++;
                                        if (p < token.size() && esParentecisIzq(token.get(p))) {
                                        p++;
                                        if (esExpInput()) {
                                                if ( p < token.size() && esParentecisDer(token.get(p))) {
                                                    p++;
                                                    if (p < token.size()) { 
                                                        bloque();
                                                    }else{             
                                                        JOptionPane.showMessageDialog(null,finSintaxis());
                                                        finSecuencia();
                                                        break;
                                                    }
                                                }else{
                                                    JOptionPane.showMessageDialog(null,erroresSintaxis(509,renglon.get(p-1)));
                                                    finSecuencia();
                                                    break;
                                                }
                                        }else{
                                            JOptionPane.showMessageDialog(null,erroresSintaxis(513,renglon.get(p-1)));
                                            finSecuencia();
                                            break;
                                        }
                                    }else{
                                        JOptionPane.showMessageDialog(null, erroresSintaxis(510,renglon.get(p-1)));
                                        finSecuencia();
                                        break;
                                    }
                                    }else{
                                        if (p < token.size() && esExpIdent()) {
                                            if (p < token.size()) {
                                                 bloque();
                                            }else{
                                                JOptionPane.showMessageDialog(null,finSintaxis());
                                                finSecuencia();
                                                break;
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null,erroresSintaxis(507,renglon.get(p-1)));
                                            finSecuencia();
                                            break;
                                        }
                                    }
                                }else{
                                    if (p < token.size()) {
                                        bloque();
                                    }else{
                                        JOptionPane.showMessageDialog(null, finSintaxis());
                                        finSecuencia();
                                        break;
                                    }
                                }
                            }else{
                                JOptionPane.showMessageDialog(null,erroresSintaxis(508,renglon.get(p-1)));
                                finSecuencia();
                                break;
                            }

                        }else{
    //-------------------------------------------------------------------------------------------------------------  
                        if (p < token.size() && token.get(p) == 209) {//print
                            p++;
                            if (p < token.size() && esParentecisIzq(token.get(p))) {
                                p++;
                                if (esExpPrint()) {
                                        if ( p < token.size() && esParentecisDer(token.get(p))) {
                                            p++;
                                            if (p < token.size()) { 
                                                bloque();
                                            }else{             
                                                JOptionPane.showMessageDialog(null,finSintaxis());
                                                finSecuencia();
                                                break;
                                            }
                                        }else{
                                            JOptionPane.showMessageDialog(null,erroresSintaxis(509,renglon.get(p-1)));
                                            finSecuencia();
                                            break;
                                        }
                                }else{
                                    JOptionPane.showMessageDialog(null,erroresSintaxis(507,renglon.get(p-1)));
                                    finSecuencia();
                                    break;
                                }
 
                            }else{
                                JOptionPane.showMessageDialog(null, erroresSintaxis(510,renglon.get(p-1)));
                                finSecuencia();
                                break;
                            }
                        }else{
//------------------------------------------------------------------------------------------------------------------
                            if (p > 0 && p < token.size() && esElse(token.get(p))) {
                                p++;
                                elseExist++;
                                if (p < token.size() && esDosPuntos(token.get(p))) {
                                    if (elseExist > ifexist) {
                                        JOptionPane.showMessageDialog(null,erroresSintaxis(511,renglon.get(p-1)));
                                        finSecuencia();
                                        break;
                                    }else{
                                        p++;
                                        if (p<token.size()) {
                                            bloque();
                                        }else{
                                            JOptionPane.showMessageDialog(null,finSintaxis()); 
                                            finSecuencia();
                                            break;
                                        }
                                    }
                                }else{
                                    JOptionPane.showMessageDialog(null,erroresSintaxis(504,renglon.get(p-1)));
                                    finSecuencia();
                                    break;
                                }
                            }else{
                                p++;
                                JOptionPane.showMessageDialog(null, erroresSintaxis(512,renglon.get(p-1)));
                                p--;
                                finSecuencia();
                                break;
                            }             
                        }
                    }
                }
            }
    }
}     
//-------------------------------------------------------------------------------------------------------------------
    private void finSecuencia(){
        p = token.size();
    }
//-------------------------------------------------------------------------------------------------------------------
    private boolean esExpresion(){
        if (p<token.size() && tiposVariables((token.get(p)))) {
            p++;
            bandera = true;
            if (p<token.size() && operadorBin((token.get(p)))) {
                p++;
                if (p<token.size() && esParentecisIzq(token.get(p))) {
                    p++;
                    if (expNumerica()) {
                        if (p<token.size() && esParentecisDer(token.get(p))) {
                            p++;
                            if (expBinaria()) {
                                bandera = false;
                                bandBin = false;
                                bandNum = false;
                                esExpresion();
                            }else{
                                bandera = true;
                                return bandera;
                            }
                        }else{
                            bandera = false;
                            return bandera;
                        }
                    }else{
                        bandera = false;
                        return bandera;
                    }
                }else{
                    bandera = false;
                    esExpresion();
                }
            }
            return bandera;
        }
        return bandera;
    }
    
    private boolean expNumerica(){
        if (p<token.size() && tiposVariables(token.get(p))) {
            p++;
            bandNum=true;
            if (p<token.size() && operadorArit(token.get(p))) {
               p++;
               bandNum = false;
               expNumerica();
            }
        }
        return bandNum;
    }
    
    private boolean expBinaria(){
        if (p<token.size() && operadorBin(token.get(p))) {
            p++;
            bandBin=true;
        }
        return bandBin;
    }  
//------------------------------------------------------------------------------------------------------------------       
    private boolean esExpIdent(){
        if (p<token.size() && tiposVariables((token.get(p)))) {
            p++;
            bandera = true;
            if (p<token.size() && operadorArit((token.get(p)))) {
                p++;
                bandera = false;
                esExpIdent();
            }
            return bandera;
        }
        return bandera;
    }
 //------------------------------------------------------------------------------------------------------------------          
    private boolean esExpInput(){
        if (p<token.size() && esString(token.get(p))) {
            p++;
            bandera = true;
            return bandera;
        }
        return bandera;
    }
//------------------------------------------------------------------------------------------------------------------       
    private boolean esExpPrint(){
        if (p<token.size() && tiposVariables((token.get(p)))) {
            p++;
            bandera = true;
            if (p<token.size() && esComa((token.get(p)))) {
                p++;
                bandera = false;
                esExpPrint();
            }else{
                if (p<token.size() && operadorArit(token.get(p))) {
                    p++;
                    bandera = false;
                    esExpPrint();
                }else{
                    return bandera;
                }
            }
            return bandera;
        }
        return bandera;
    }
//------------------------------------------------------------------------------------------------------------------     
    public Boolean Identificadores(){
        if (p<token.size() && esComa(token.get(p))) {
            p++;
            banIdent = false;
            if (p<token.size() && esIden(token.get(p))) {
                p++;
                banIdent = true;
                Identificadores();
            }
            return banIdent; 
        }else{
            if (p<token.size() && esIden(token.get(p))) {
                p++;
                banIdent = true;
                Identificadores();
            }
            return banIdent;
        }
    }
//------------------------------------------------------------------------------------------------------------------ 
    private boolean tiposVariables(int token){
        switch(token){
            case 100: //iden
                return true;
            case 101: //float
                return true;
            case 102://integer
                return true;
            case 103://String
                return true;
            case 211://true
                return true;
            case 212://false
                return true;
            default:
                return false;
        }
    }
//------------------------------------------------------------------------------------------------------------------     
    private boolean operadorBin(int token){
        switch(token){
            case 110://==
                return true;
            case 112://!=
                return true;
            case 113://<=
                return true;
            case 114://<
                return true;
            case 115://>=
                return true;
            case 116://>
                return true;
            case 117://& or
                return true;
            case 118://^ 118
                return true;
            case 119://| not
                return true;
            default:
                return false;      
        }
    }
//------------------------------------------------------------------------------------------------------------------
    private boolean operadorArit(int token){
        switch(token){
            case 106://+
                return true;
            case 107://-
                return true;
            case 108://*
                return true;
            case 109:// /
                return true;
            default:
                return false;
        }
    }  
//------------------------------------------------------------------------------------------------------------------       
    private String erroresSintaxis(int error,int renglon){
        switch(error){
            case 504: 
                return "Error: "+error+" se esperaban dos puntos en el renglon: "+renglon;
            case 505: 
                return "Error: "+error+" se esperaba una expresion valida seguida del if en el renglon: "+renglon;
            case 506: 
                return "Error: "+error+" se esperaba una expresion seguida del while en el renglon: "+renglon;
            case 507: 
                return "Error: "+error+" se esperaba un Identificador, Integer, Flotante o Booleano en el renglon: "+renglon;
            case 508: 
                return "Error: "+error+" se esperaba un Identificador en el renglon: "+renglon;
            case 509: 
                return "Error: "+error+" se esperaba un Parentesis Derecho en el renglon: "+renglon;
            case 510: 
                return "Error: "+error+" se esperaba un Parentesis Izquierdo en el renglon: "+renglon;
            case 511: 
                return "Error: "+error+" sentencia if no encontrada para else en el renglon: "+renglon;
            case 512: 
                return "Error: "+error+" Sintaxis Incorrecta, Entrada no Valida";
            case 513:
                return "Error: "+error+" Se esperaba un String en el renglon: "+renglon; 
            default: 
                return "Error de Sintaxis";
        }
    }
    
    private String finSintaxis(){
        return "Sintaxis Correcta: Ejecucion Finalizada ";
    }
//------------------------------------------------------------------------------------------------------------------     
    private Boolean esElse(int token){
        if (token == 203){
            return true;
        }
        return false;
    }
    
    private Boolean esComa(int token){
        if (token == 121){
            return true;
        }
        return false;
    }
    
    private Boolean esDosPuntos(int token){
        if (token == 124){
            return true;
        }
        return false;
    }
    private Boolean esParentecisIzq(int token){
        if (token == 122){
            return true;
        }
        return false;
    }
    private Boolean esParentecisDer(int token){
        if (token == 123){
            return true;
        }
        return false;
    }
        
    private Boolean esIden(int token){
        if (token == 100) {
            return true;
        }
        return false;
    }
    private Boolean esIgual(int token){
        if (token == 111) {
            return true;
        }
        return false;
    }
    private Boolean esInput(int token){
        if (token == 213) {
            return true;
        }
        return false;
    }
    private Boolean esString(int token){
        if (token == 103) {
            return true;
        }
        return false;
    }
}


