package clases;

import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TextEditorModel {

    private MonkeyParser parser;
    private MonkeyScanner scanner;
    private CommonTokenStream tokens = null; //lista de tokens
    private myAntlrErrorHandle handleError;

    public TextEditorModel(){
        this.handleError =new myAntlrErrorHandle();
    }

    public void setListeners(){
        this.scanner.removeErrorListeners();
        this.scanner.addErrorListener(this.handleError);
        this.parser.removeErrorListeners();
        this.parser.addErrorListener(this.handleError);
    }

    public void AnalizeAndExecute(String filePath) throws IOException {
        ANTLRInputStream input= new ANTLRInputStream(new FileReader(filePath));
        this.scanner=new MonkeyScanner(input);
        this.tokens= new CommonTokenStream(this.scanner);
        parser= new MonkeyParser(tokens);
        this.setListeners();
        List<Token> lista = (List<Token>) this.scanner.getAllTokens(); //la funcion getAllTokens casi no se usa, esto pone a correr al scanner

        for (Token t : lista)
            //el tipo es el id que se asocia a la expresion regular que defina al texto que contiene el token
            // tambien puede contener la fila y columna en la que se encuentra el texto en el archivo
            System.out.println(t.getType() + ":" + t.getText() + "\n");
        this.scanner.reset(); //vuelve a poner al scanner al inicio del archivo de scanner.tokens
        // para leer nuevamente cuando el parse necesite
        //de los tokens
        ParseTree tree= parser.program();

    }
}
