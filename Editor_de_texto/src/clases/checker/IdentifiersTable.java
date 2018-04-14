package clases.checker;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import java.util.LinkedList;

public class IdentifiersTable{
    private static LinkedList<IdentifierElement> IDs_table;
    private static int actLevel;

    public IdentifiersTable(){
        this.actLevel = -1;
        this.IDs_table = new LinkedList<IdentifierElement>();
    }

<<<<<<< HEAD
    public int getActLevel() {
        return actLevel;
    }

    public void setActLevel(int actLevel) {
        this.actLevel = actLevel;
    }

    public IdentifierElement insertar(Token tkn, int type, ParserRuleContext declaracion)
    {
        IdentifierElement element = new IdentifierElement(declaracion,actLevel,tkn,type);
        int j = 0;
        while (j < this.IDs_table.size() && this.IDs_table.get(j).getLevel() == actLevel) {
            if (this.IDs_table.get(j).getToken().getText().equals(tkn.getText())) {
=======
    public static IdentifierElement insert(Token tkn, int type, ParserRuleContext declaracion)
    {
        IdentifierElement element = new IdentifierElement(declaracion,actLevel,tkn,type);
        int j = 0;
        while (j < IDs_table.size() && IDs_table.get(j).getLevel() == actLevel) {
            if (IDs_table.get(j).getToken().getText().equals(tkn.getText())) {
                System.out.println("El identificador \"" + tkn.getText() + "\" ya ha sido declarado!!!");
>>>>>>> c3c6dd481367a63d997eeba99f95b724af8bfa45
                return null;
            }
            j++;
        }
        IDs_table.push(element); //deben ser una tabla estilo pila
        return IDs_table.get(0);
    }

    public void OpenScope(){
        this.actLevel++;
    }

    public void closeScope(){
        IdentifierElement element = this.IDs_table.get(0);
        while (element != null && element.getLevel() == actLevel){
            IDs_table.pop();
            if(!this.IDs_table.isEmpty())
                element = this.IDs_table.get(0);
            else
                element= null;
        }
        this.actLevel--;
    }

    public IdentifierElement buscar(String nombre)
    {
        IdentifierElement temporal = null;
        for(IdentifierElement id : this.IDs_table)
            if (id.getToken().getText().equals(nombre)) {
                temporal = id;
                break;
            }
        return temporal;


    }

    public void imprimir() {
        System.out.println("****** ESTADO DE TABLA DE SÍMBOLOS ******");
        if (!this.IDs_table.isEmpty()) {
            for (IdentifierElement i : this.IDs_table) {
                String nivel = "";
                for (int j = 0; j < i.getLevel(); j++) {
                    nivel += "\t";
                }
                System.out.println(nivel + "Nombre: " + i.getToken().getText() + " - Nivel: " + i.getLevel());
            }
            System.out.println("------------------------------------------");
        }
        else
            System.out.println("Tabla vacía");
    }

}