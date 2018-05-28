package clases;

import clases.Interpreter.Interpreter;
import clases.checker.Checker;
import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;

import javax.swing.*;
import java.io.*;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class TextEditorModel {

    private Checker checker;
    private MonkeyParser parser;
    private MonkeyScanner scanner;
    private Interpreter interpreter;
    private CommonTokenStream tokens = null; //lista de tokens
    private myAntlrErrorHandle handleError;
    private ParseTree tree;


    public TextEditorModel(){

        this.handleError =new myAntlrErrorHandle();

    }

    public Checker getChecker() {
        return checker;
    }

    public Interpreter getInterpreter() {
        return interpreter;
    }

    public void setInterpreter(Interpreter interpreter) {
        this.interpreter = interpreter;
    }

    public void setChecker(Checker checker) {
        this.checker = checker;
    }

    public void setListeners(){
        this.scanner.removeErrorListeners();
        this.scanner.addErrorListener(this.handleError);
        this.parser.removeErrorListeners();
        this.parser.addErrorListener(this.handleError);

    }

    public ParseTree getTree(){
        return this.tree;
    }

    public MonkeyParser getParser(){
        return this.parser;
    }


    // for code interpretation
    public void  codeInterpretation(ParseTree tree,JTextArea ex){
        this.interpreter = new Interpreter(ex);
        this.interpreter.visit(tree);
    }


    public void AnalizeAndExecute(String filePath) throws IOException {
        ANTLRInputStream input= new ANTLRInputStream(new FileReader(filePath));
        this.scanner=new MonkeyScanner(input);
        this.tokens= new CommonTokenStream(this.scanner);
        this.parser= new MonkeyParser(tokens);
        this.setListeners();

        List<Token> lista = (List<Token>) this.scanner.getAllTokens(); //la funcion getAllTokens casi no se usa, esto pone a correr al scanner

        for (Token t : lista)
            //el tipo es el id que se asocia a la expresion regular que defina al texto que contiene el token
            // tambien puede contener la fila y columna en la que se encuentra el texto en el archivo
            System.out.println(t.getType() + ":" + t.getText() + "\n");
        this.scanner.reset(); //vuelve a poner al scanner al inicio del archivo de scanner.tokens
        // para leer nuevamente cuando el parse necesite
        //de los tokens

        //parse program
         this.tree= this.parser.program();

         this.checker= new Checker();

         //semantic checker
         this.checker.visit(this.tree);

    }
}
