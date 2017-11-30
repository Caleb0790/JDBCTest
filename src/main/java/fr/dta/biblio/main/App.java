package fr.dta.biblio.main;

import java.util.ArrayList;
import java.util.List;

import fr.dta.biblio.dao.BookDAO;
import fr.dta.biblio.dao.ClientDAO;
import fr.dta.biblio.dao.DataBaseUtils;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	DataBaseUtils.dbInit();
    	
    	List<Book> bl=new ArrayList<>();
    	bl.add(new Book("Les aventures de Julie","Tulie TANE"));
    	bl.add(new Book("Les titis","Paul TITI"));
    	bl.add(new Book("Tutu et moi","Marc MOI"));
    	bl.add(new Book("To toto or not to toto", "Tata TOTO"));
    	
    	for (Book book : bl) {
    		BookDAO.createBook(book);
		}
    	
    	List<Client> cl=new ArrayList<>();
    	cl.add(new Client("Julie","TAMI", Gender.FEMME));
    	cl.add(new Client("Jérémy","TUWALY", Gender.HOMME));
    	
    	for (Client client : cl) {
			ClientDAO.createClient(client);
		}
    	
    	DataBaseUtils.achat(cl.get(1), bl.get(3));
    	DataBaseUtils.achat(cl.get(1), bl.get(0));
    	DataBaseUtils.achat(cl.get(1), bl.get(2));
    	DataBaseUtils.achat(cl.get(0), bl.get(3));
    	DataBaseUtils.achat(cl.get(0), bl.get(1));
    	
    	
        System.out.println( "Hello World!" );
    }
}
