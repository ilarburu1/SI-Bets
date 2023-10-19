package dataAccess;

//hello
//probaa_amets
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Apustua;
import domain.ApustuaInfo;
import domain.Aukera;
import domain.Editorea;
import domain.Erregistratua;
import domain.Event;
import domain.Mezua;
import domain.MezuaInfo;
import domain.Mugimendua;
import domain.Question;
import domain.User;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccessIsLogin  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();
	

     public DataAccessIsLogin(boolean initializeMode)  {
		
    	System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

 		open(initializeMode);
	}

	public DataAccessIsLogin()  {	
		this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q3;
			Question q4;
			Question q5;
					
			String irabazlea = "¿Quién ganará el partido?";
			String winner = "Who will win the match?";
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion(irabazlea,1);
				ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion(irabazlea,1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion(irabazlea,1);
				ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion(winner,1);
				ev1.addQuestion(winner,2);
				q3=ev11.addQuestion(winner,1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				String irabazlea2 = "Zeinek irabaziko du partidua?";
				q1=ev1.addQuestion(irabazlea2,1);
				ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion(irabazlea2,1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion(irabazlea2,1);
				ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			
			Aukera a1 = new Aukera(1,"Atletico",2);
			Aukera a2 = new Aukera(2,"Athletic",2);
			Aukera aEmpate = new Aukera(7,"Empate",4);
			Aukera a3 = new Aukera(3,"Atletico",2);
			Aukera a4 = new Aukera(4,"Athletic",2);
			Aukera a5 = new Aukera(5,"Malaga",4);
			Aukera a6 = new Aukera(6,"Valencia",5);
			Aukera a8 = new Aukera(8,"2",4);
			Aukera a9 = new Aukera(9,"3",5);
			Aukera a10 = new Aukera(10,"4",6);
			
			q1.newAukera(a1);
			q1.newAukera(a2);
			q3.newAukera(a3);
			q3.newAukera(a4);
			q3.newAukera(aEmpate);
			q4.newAukera(a8);
			q4.newAukera(a9);
			q4.newAukera(a10);
			q5.newAukera(a5);
			q5.newAukera(a6);
			
			
			db.persist(a1);
			db.persist(a2);
			db.persist(a3);
			db.persist(a4);
			db.persist(a5);
			db.persist(a6);
			db.persist(aEmpate);
			db.persist(a8);
			db.persist(a9);
			db.persist(a10);
			
			
			db.persist(q1);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);	
			
			User u = new Erregistratua("intza","1","73262157G",200);
			db.persist(u);
			
			User u2 = new Erregistratua("ibai","1","12345678K",100);
	        db.persist(u2);
	        
			User admin = new Admin("adminAmets","1");
			db.persist(admin);

			User editor1 = new Editorea("editorErik","1");
			db.persist(editor1);
			
			
			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");   
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}

	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
	
	//NIRE METODOA*************************************************************************
	
	
public User isLogin(String log, String password) {
	    
		
		User user = db.find(domain.Erregistratua.class, log );
		db.getTransaction().begin();
		if(user!=null){
			if( ((Erregistratua)user).isBanned()) {
				Date data = new Date();
				if( ((Erregistratua)user).getBanEndDate().compareTo(data)<=0) {
					((Erregistratua)user).setBanned(false);
				}
			}
			db.getTransaction().commit();
			return user;
		}else {
			user = db.find(domain.Editorea.class, log);
			if(user!=null){
				db.getTransaction().commit();
				return user;
			}else {
				user = db.find(domain.Admin.class, log);
			}
		}
		db.getTransaction().commit();
		return user;
	}
    
	
	
}