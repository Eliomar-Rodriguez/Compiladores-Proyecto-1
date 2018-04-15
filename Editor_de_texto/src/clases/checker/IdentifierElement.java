package clases.checker;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;

import java.util.LinkedList;

public class IdentifierElement{
    private int level;
    private Token token;
    private int type;
    private int arrayOrHashLiteral;
    private ParserRuleContext declaration;

    public IdentifierElement(ParserRuleContext d, int l, Token t, int ty,int A_oHL){
        this.declaration = d;
        this.level = l;
        this.token = t;
        this.type = ty;
        this.arrayOrHashLiteral= A_oHL; //-1 = no es array ni hash literal, 4= array 5= literal
    }

    public int getArrayOrHashLiteral() {
        return arrayOrHashLiteral;
    }

    public void setArrayOrHashLiteral(int arrayOrHashLiteral) {
        this.arrayOrHashLiteral = arrayOrHashLiteral;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Token getToken() {
        return token;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public ParserRuleContext getDeclaration() {
        return declaration;
    }

    public void setDeclaration(ParserRuleContext declaration) {
        this.declaration = declaration;
    }
}