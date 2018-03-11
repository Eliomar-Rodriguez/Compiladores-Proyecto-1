// Generated from C:/Users/anton/Desktop/Compiladores Proyecto 1/Editor_de_texto\Scanner.g4 by ANTLR 4.7
package generated;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class Scanner extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		PyCOMA=1, COMA=2, CIZQ=3, CDER=4, PIZQ=5, PDER=6, LIZQ=7, LDER=8, DOSPUN=9, 
		MENOR=10, MAYOR=11, MENORIGUAL=12, MAYORIGUAL=13, ASSIGN=14, IGUALCOMP=15, 
		SUM=16, SUB=17, MUL=18, DIV=19, LET=20, RETURN=21, LEN=22, FIRST=23, LAST=24, 
		REST=25, PUSH=26, FN=27, PUTS=28, IF=29, ELSE=30, TRUE=31, FALSE=32, ID=33, 
		INTEGER=34, STRING=35, COMMENT=36, LINECOMMENT=37, WS=38;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"PyCOMA", "COMA", "CIZQ", "CDER", "PIZQ", "PDER", "LIZQ", "LDER", "DOSPUN", 
		"MENOR", "MAYOR", "MENORIGUAL", "MAYORIGUAL", "ASSIGN", "IGUALCOMP", "SUM", 
		"SUB", "MUL", "DIV", "LET", "RETURN", "LEN", "FIRST", "LAST", "REST", 
		"PUSH", "FN", "PUTS", "IF", "ELSE", "TRUE", "FALSE", "ID", "INTEGER", 
		"STRING", "COMMENT", "LINECOMMENT", "LETTER", "DIGIT", "WS"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "';'", "','", "'['", "']'", "'('", "')'", "'{'", "'}'", "':'", "'<'", 
		"'>'", "'<='", "'>='", "'='", "'=='", "'+'", "'-'", "'*'", "'/'", "'let'", 
		"'return'", "'len'", "'first'", "'last'", "'rest'", "'push'", "'fn'", 
		"'puts'", "'if'", "'else'", "'true'", "'false'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "PyCOMA", "COMA", "CIZQ", "CDER", "PIZQ", "PDER", "LIZQ", "LDER", 
		"DOSPUN", "MENOR", "MAYOR", "MENORIGUAL", "MAYORIGUAL", "ASSIGN", "IGUALCOMP", 
		"SUM", "SUB", "MUL", "DIV", "LET", "RETURN", "LEN", "FIRST", "LAST", "REST", 
		"PUSH", "FN", "PUTS", "IF", "ELSE", "TRUE", "FALSE", "ID", "INTEGER", 
		"STRING", "COMMENT", "LINECOMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}


	public Scanner(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Scanner.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2(\u00f5\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\13\3\13\3"+
		"\f\3\f\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\20\3\20\3\20\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3"+
		"\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\37\3\37\3\37\3"+
		"\37\3\37\3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\7\"\u00bf\n\"\f"+
		"\"\16\"\u00c2\13\"\3#\3#\7#\u00c6\n#\f#\16#\u00c9\13#\3$\3$\7$\u00cd\n"+
		"$\f$\16$\u00d0\13$\3$\3$\3%\3%\3%\3%\7%\u00d8\n%\f%\16%\u00db\13%\3%\3"+
		"%\3%\3&\3&\3&\3&\7&\u00e4\n&\f&\16&\u00e7\13&\3&\3&\3\'\3\'\3(\3(\3)\6"+
		")\u00f0\n)\r)\16)\u00f1\3)\3)\4\u00ce\u00d9\2*\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M\2"+
		"O\2Q(\3\2\5\3\2\f\f\5\2C\\aac|\5\2\13\f\17\17\"\"\2\u00f9\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2\5U\3\2\2\2\7W\3\2\2\2\tY\3\2\2\2\13"+
		"[\3\2\2\2\r]\3\2\2\2\17_\3\2\2\2\21a\3\2\2\2\23c\3\2\2\2\25e\3\2\2\2\27"+
		"g\3\2\2\2\31i\3\2\2\2\33l\3\2\2\2\35o\3\2\2\2\37q\3\2\2\2!t\3\2\2\2#v"+
		"\3\2\2\2%x\3\2\2\2\'z\3\2\2\2)|\3\2\2\2+\u0080\3\2\2\2-\u0087\3\2\2\2"+
		"/\u008b\3\2\2\2\61\u0091\3\2\2\2\63\u0096\3\2\2\2\65\u009b\3\2\2\2\67"+
		"\u00a0\3\2\2\29\u00a3\3\2\2\2;\u00a8\3\2\2\2=\u00ab\3\2\2\2?\u00b0\3\2"+
		"\2\2A\u00b5\3\2\2\2C\u00bb\3\2\2\2E\u00c3\3\2\2\2G\u00ca\3\2\2\2I\u00d3"+
		"\3\2\2\2K\u00df\3\2\2\2M\u00ea\3\2\2\2O\u00ec\3\2\2\2Q\u00ef\3\2\2\2S"+
		"T\7=\2\2T\4\3\2\2\2UV\7.\2\2V\6\3\2\2\2WX\7]\2\2X\b\3\2\2\2YZ\7_\2\2Z"+
		"\n\3\2\2\2[\\\7*\2\2\\\f\3\2\2\2]^\7+\2\2^\16\3\2\2\2_`\7}\2\2`\20\3\2"+
		"\2\2ab\7\177\2\2b\22\3\2\2\2cd\7<\2\2d\24\3\2\2\2ef\7>\2\2f\26\3\2\2\2"+
		"gh\7@\2\2h\30\3\2\2\2ij\7>\2\2jk\7?\2\2k\32\3\2\2\2lm\7@\2\2mn\7?\2\2"+
		"n\34\3\2\2\2op\7?\2\2p\36\3\2\2\2qr\7?\2\2rs\7?\2\2s \3\2\2\2tu\7-\2\2"+
		"u\"\3\2\2\2vw\7/\2\2w$\3\2\2\2xy\7,\2\2y&\3\2\2\2z{\7\61\2\2{(\3\2\2\2"+
		"|}\7n\2\2}~\7g\2\2~\177\7v\2\2\177*\3\2\2\2\u0080\u0081\7t\2\2\u0081\u0082"+
		"\7g\2\2\u0082\u0083\7v\2\2\u0083\u0084\7w\2\2\u0084\u0085\7t\2\2\u0085"+
		"\u0086\7p\2\2\u0086,\3\2\2\2\u0087\u0088\7n\2\2\u0088\u0089\7g\2\2\u0089"+
		"\u008a\7p\2\2\u008a.\3\2\2\2\u008b\u008c\7h\2\2\u008c\u008d\7k\2\2\u008d"+
		"\u008e\7t\2\2\u008e\u008f\7u\2\2\u008f\u0090\7v\2\2\u0090\60\3\2\2\2\u0091"+
		"\u0092\7n\2\2\u0092\u0093\7c\2\2\u0093\u0094\7u\2\2\u0094\u0095\7v\2\2"+
		"\u0095\62\3\2\2\2\u0096\u0097\7t\2\2\u0097\u0098\7g\2\2\u0098\u0099\7"+
		"u\2\2\u0099\u009a\7v\2\2\u009a\64\3\2\2\2\u009b\u009c\7r\2\2\u009c\u009d"+
		"\7w\2\2\u009d\u009e\7u\2\2\u009e\u009f\7j\2\2\u009f\66\3\2\2\2\u00a0\u00a1"+
		"\7h\2\2\u00a1\u00a2\7p\2\2\u00a28\3\2\2\2\u00a3\u00a4\7r\2\2\u00a4\u00a5"+
		"\7w\2\2\u00a5\u00a6\7v\2\2\u00a6\u00a7\7u\2\2\u00a7:\3\2\2\2\u00a8\u00a9"+
		"\7k\2\2\u00a9\u00aa\7h\2\2\u00aa<\3\2\2\2\u00ab\u00ac\7g\2\2\u00ac\u00ad"+
		"\7n\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af\7g\2\2\u00af>\3\2\2\2\u00b0\u00b1"+
		"\7v\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7w\2\2\u00b3\u00b4\7g\2\2\u00b4"+
		"@\3\2\2\2\u00b5\u00b6\7h\2\2\u00b6\u00b7\7c\2\2\u00b7\u00b8\7n\2\2\u00b8"+
		"\u00b9\7u\2\2\u00b9\u00ba\7g\2\2\u00baB\3\2\2\2\u00bb\u00c0\5M\'\2\u00bc"+
		"\u00bf\5M\'\2\u00bd\u00bf\5O(\2\u00be\u00bc\3\2\2\2\u00be\u00bd\3\2\2"+
		"\2\u00bf\u00c2\3\2\2\2\u00c0\u00be\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1D"+
		"\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c3\u00c7\5O(\2\u00c4\u00c6\5O(\2\u00c5"+
		"\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3\2\2\2\u00c7\u00c8\3\2"+
		"\2\2\u00c8F\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00ce\7$\2\2\u00cb\u00cd"+
		"\13\2\2\2\u00cc\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cf\3\2\2\2"+
		"\u00ce\u00cc\3\2\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2"+
		"\7$\2\2\u00d2H\3\2\2\2\u00d3\u00d4\7\61\2\2\u00d4\u00d5\7,\2\2\u00d5\u00d9"+
		"\3\2\2\2\u00d6\u00d8\13\2\2\2\u00d7\u00d6\3\2\2\2\u00d8\u00db\3\2\2\2"+
		"\u00d9\u00da\3\2\2\2\u00d9\u00d7\3\2\2\2\u00da\u00dc\3\2\2\2\u00db\u00d9"+
		"\3\2\2\2\u00dc\u00dd\7,\2\2\u00dd\u00de\7\61\2\2\u00deJ\3\2\2\2\u00df"+
		"\u00e0\7\61\2\2\u00e0\u00e1\7\61\2\2\u00e1\u00e5\3\2\2\2\u00e2\u00e4\n"+
		"\2\2\2\u00e3\u00e2\3\2\2\2\u00e4\u00e7\3\2\2\2\u00e5\u00e3\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e8\3\2\2\2\u00e7\u00e5\3\2\2\2\u00e8\u00e9\7\f"+
		"\2\2\u00e9L\3\2\2\2\u00ea\u00eb\t\3\2\2\u00ebN\3\2\2\2\u00ec\u00ed\4\62"+
		";\2\u00edP\3\2\2\2\u00ee\u00f0\t\4\2\2\u00ef\u00ee\3\2\2\2\u00f0\u00f1"+
		"\3\2\2\2\u00f1\u00ef\3\2\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3"+
		"\u00f4\b)\2\2\u00f4R\3\2\2\2\n\2\u00be\u00c0\u00c7\u00ce\u00d9\u00e5\u00f1"+
		"\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}