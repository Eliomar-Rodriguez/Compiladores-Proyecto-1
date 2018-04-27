package clases;

import generated.MonkeyScanner;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import java.io.StringWriter;
import java.io.PrintWriter;


public class myAntlrErrorHandle extends BaseErrorListener {


    private static final boolean REPORT_SYNTAX_ERRORS = true;
    public final String parserFile="MonkeyParser.g4";
    public final String scannerFile="MonkeyScanner.g4";
    public boolean StringError;
    public String exceptionMessage;


    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol,
                            int line, int charPositionInLine,
                            String msg, RecognitionException e) throws RecognitionException {
        if (!REPORT_SYNTAX_ERRORS) {
            return;
        }

        String sourceName;
        char quote='"';

        if (recognizer.getGrammarFileName().equals(this.scannerFile)){
            sourceName="Scanner error ";
        }
        else{
            sourceName="Parser error ";
        }
        System.out.println("excepcion real message");
        System.out.println(msg);
        //if it's possible that contains a string with out a quote
        if (msg.contains("\"")){
            sourceName+=" ( You could have forgotten to close a string ) ";
        }
        this.exceptionMessage=sourceName+"in line "+line+":"+charPositionInLine+" "+msg;

        ParseCancellationException newException= new ParseCancellationException(this.exceptionMessage);
        throw newException;



    }
}

