package clases;

import generated.MonkeyParser;
import generated.MonkeyScanner;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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
        ParseTree tree= parser.program();
    }
}
