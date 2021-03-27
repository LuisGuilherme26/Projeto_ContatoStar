import java.util.*;

class Fone{
    public String label;
    public String number;
    
    public static boolean validate(String number){
        int quant = 0;
        String validar = "1234567890";
        String val[] = new String[10];
        String num[] = new String[number.length()];
        val = validar.split("");
        num = number.split("");
        for (int i = 0; i < num.length; i++) {
            for (int j = 0; j < val.length; j++) {
                if(num[i].equals(val[j])){
                    quant++;
                }
            }
        }
        if(quant < number.length()){
            System.out.println("Numero invalido");
            return false;
        }else{
            return true;
        }
    }

    public Fone(String label, String number) {
        if(validate(number)){
            this.label = label;
            this.number = number;
        }
    }
    public String toString(){
        return label+":"+number;
    }
}

class Contato{
    private String name;
    private ArrayList<Fone> fone;
    
    public void procurarNull(){
        for (int i = 0; i < fone.size(); i++) {
            if(fone.get(i).label == null || fone.get(i).number == null){
                rmFone(i);
            }
        }
    }
    
    public void addFone(String label, String number){
        fone.add(new Fone(label, number));
        procurarNull();
    }
    
    public void rmFone(int index){
        fone.remove(index);
    }

    public Contato(String name, String label, String number) {
        fone = new ArrayList<Fone>();
        fone.add(new Fone(label, number));
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public ArrayList<Fone> getFone() {
        return fone;
    }

    public void setFone(ArrayList<Fone> fone) {
        this.fone = fone;
    }
    
    public String toString(){
        return "Nome:"+name+", Fone:"+fone;
    }
}

class Contatoplus extends Contato{
    boolean starred;

    public Contatoplus(String name, String label, String number) {
        super(name, label, number);
    }
    
    public String toString(){
        return "";
    }
}

class AgendaPlus extends Agenda{
    Map<String, Contatoplus> bookmarks;
    
    Contatoplus percorrer(String value){
        for (int i = 0; i < bookmarks.size(); i++) {
            if(bookmarks.get(i).equals(value)){
                return bookmarks.get(i);
            }
        }
        return null;
    }
    
    void bookmark(String id){
        Contato contato = (Contato) getContacts(id);
        if(percorrer(id) == null){
            bookmarks.put(id, (Contatoplus) contato);
        }else{
            System.out.println("Ja existe esse contato");
        } 
    }
    
    void unBookmark (String id){
        if(percorrer(id) == null){
            System.out.println("Não existe esse Contato");
        }else{
            bookmarks.remove(id);
        }    
    }

    public AgendaPlus() {
        bookmarks = new TreeMap<>();
    }

    public Map<String, Contatoplus> getBookmarks() {
        return bookmarks;
    }
    
    public String toString(){
        return "";
    }
}

public class Agenda{
    private List<Contato> contatos;
    
    public int findContact(String name){
        for (int i = 0; i < contatos.size(); i++) {
            if(contatos.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }
    
    public void addContact(String name, Fone fone){
        if(findContact(name) != -1){
            System.out.println("Ja existe esse nome");
        }else{
            contatos.add(new Contato(name, fone.label, fone.number));   
        }
    }
    
    public boolean rmContact(String name){
        int pos = findContact(name);
        if(pos == -1){
            System.out.println("Não existe esse contato");
            return false;
        }else{
            contatos.remove(pos);
            return true;
        }
    }
    
    public List<Contato> search(String pattern){
        List<Contato> aux = new ArrayList<>();
        for (int i = 0; i < contatos.size(); i++) {
            if (contatos.get(i).toString().contains(pattern)) {
                aux.add(i, contatos.get(i));
            }
        }
        return aux;
    }

    public List<Contato> getContacts(String name) {
        return contatos;
    }
    
    public Agenda(){
        contatos = new ArrayList<Contato>();
    }

    public String toString(){
        return "Contatos: "+ contatos;
    }
    
    public static void main(String[] args) {
        Agenda agenda = new Agenda();
        while(true){
                Scanner tcl = new Scanner(System.in);
                String[] ui = tcl.nextLine().split(" ");
                if(ui[0].equals("add")){
                    agenda.addContact(ui[1],new Fone(ui[2], ui[3]));
                }else if(ui[0].equals("remove")){
                    agenda.rmContact(ui[1]);
                }else if(ui[0].equals("search")){
                    System.out.println(agenda.search(ui[1]));
                }else if(ui[0].equals("show")){
                    System.out.println(agenda);
                }else if(ui[0].equals("stop")){
                    break;
                }
            }
        
    }
    
}
