package clases;

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
                            String msg, RecognitionException e) throws ParseCancellationException {
        if (!REPORT_SYNTAX_ERRORS) {
            return;
        }

        String sourceName;
        System.out.println("string linea provocador");
        System.out.println(recognizer.getInputStream().getSourceName());
        System.out.println("otro provocador");
       // System.out.println(recognizer.getErrorHeader());
        System.out.println("otro intento");
        System.out.println(msg);
        if (recognizer.getGrammarFileName().equals(this.scannerFile)){
            sourceName="Scanner error ";
        }
        else{
            sourceName="Parser error ";
        }
        this.exceptionMessage=sourceName+"in line "+line+":"+charPositionInLine+" "+msg;
        ParseCancellationException newException= new ParseCancellationException(this.exceptionMessage);
        System.out.print("impresion"+"\n");
        System.out.print("impresión después del ciclo" +
                "sdsd");

        StringWriter stackTraceWriter = new StringWriter();
        newException.printStackTrace(new PrintWriter(stackTraceWriter));
        System.out.println(stackTraceWriter.toString());
        System.out.println("throw");
        throw newException;



    }
}

