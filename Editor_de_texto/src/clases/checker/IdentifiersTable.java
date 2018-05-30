package clases.checker;

import generated.MonkeyParser;
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


    public int getActLevel() {
        return actLevel;
    }

    public void setActLevel(int actLevel) {
        this.actLevel = actLevel;
    }

    public  LinkedList<IdentifierElement> getIDs_table() {
        return IDs_table;
    }

    public IdentifierElement insertar(Token tkn, int type, ParserRuleContext declaracion)
    {
        IdentifierElement element = new IdentifierElement(declaracion,actLevel,tkn,type);
        int j = 0;
        while (j < this.IDs_table.size() && this.IDs_table.get(j).getLevel() == actLevel) {
            if (this.IDs_table.get(j).getToken().getText().equals(tkn.getText())) {
                this.IDs_table.get(j).setType(type); //change the type of the var

                //link pointer
                if (declaracion!=null){
                    ((MonkeyParser.Lt_asign_MkyContext) declaracion).identifier().decl=this.IDs_table.get(j).getDeclaration();
                }
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
        if (this.IDs_table.size()==0){
            return;
        }
        int i=0;
        IdentifierElement element;

        while (i < this.IDs_table.size()){
            element = this.IDs_table.get(i);
            if (element.getLevel() == actLevel){
                IDs_table.remove(i);
                i=i-1;
            }
            i++;
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

    public boolean deleteElement(String name) {
        for (int i = 0; i < this.IDs_table.size(); i++) {
            if (this.IDs_table.get(i).getToken().getText().equals(name)&& this.IDs_table.get(i).getLevel()== this.actLevel) {
                this.IDs_table.remove(i);
                return true;
            }
        }
        return false;
    }

    public void imprimir() {
        System.out.println("****** ESTADO DE TABLA DE SÍMBOLOS PARA VARIABLES NORMALES ******");
        if (!this.IDs_table.isEmpty()) {
            for (IdentifierElement i : this.IDs_table) {
                String nivel = "";
                for (int j = 0; j < i.getLevel(); j++) {
                    nivel += "\t";
                }
                System.out.println(nivel + "Nombre: " + i.getToken().getText() + " - Nivel: " + i.getLevel()+ " - Tipo: "+
                      i.getType());
            }
            System.out.println("------------------------------------------");
        }
        else
            System.out.println("Tabla vacía");
    }

}