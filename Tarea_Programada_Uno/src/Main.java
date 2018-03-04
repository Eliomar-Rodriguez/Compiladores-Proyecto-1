import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import generated.*;
//import generated.MonkeyParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ParseTree;


import java.io.FileReader;
import java.util.List;

/**
 * Created by oviquez on 28/2/2018.
 */

public class Main {
    public static void main(String[] args){
        Scanner inst = null;
        // Parser2  parser= null;
        ANTLRInputStream input=null; //clase que se va a entender con el archivo
        CommonTokenStream tokens = null; //lista de tokens
        try {
            input = new ANTLRInputStream(new FileReader("test.txt")); //esta es la entrada para el scanner
            inst = new Scanner(input); // esta es la instancia de scanner, y lee el archivo
             //hay qe hacer la instancia del parser con la variable que tiene los tokens
           // tokens= new CommonTokenStream(inst); //DEBE INCIALIZARSE DE ESTA MANERA
           // parser= new Parser2(tokens);
        }
        catch(Exception e){System.out.println("No hay archivo");}

        /** EL SCANNER hace los reportes de errores con hilos, así si ocurre un error en algún hilo
         *  el scanner siempre sigue trabajando
         */
        List<Token> lista = (List<Token>) inst.getAllTokens(); //la funcion getAllTokens casi no se usa, esto pone a correr al scanner

        for (Token t : lista)
            //el tipo es el id que se asocia a la expresion regular que defina al texto que contiene el token
            // tambien puede contener la fila y columna en la que se encuentra el texto en el archivo
            System.out.println(t.getType() + ":" + t.getText() + "\n");
        inst.reset(); //vuelve a poner al scanner al inicio del archivo de scanner.tokens
                        // para leer nuevamente cuando el parse necesite
                        //de los tokens
        /*
        try{
            ParseTree tree= parser.program();
            System.out.println("Compilación Exitosa!! \n ");
        }
        catch (RecognitionException e){
            System.out.println("Compilación fallida!! ");
        }
        */
    }

}
