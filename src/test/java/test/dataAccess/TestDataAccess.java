package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Admin;
import domain.Aukera;
import domain.Editorea;
import domain.Erregistratua;
import domain.Event;
import domain.Question;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
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

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
		}
		
		
		public void persistErreg(Erregistratua a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				System.out.println("hona bai");
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void persistAdmin(Admin a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				System.out.println("hona bai");
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void persistEditor(Editorea a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				System.out.println("hona bai");
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		
		public void persistAuk(Aukera a) {
			db.getTransaction().begin();
			try {
				db.persist(a);
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		
		public void persistEvent(Event ev) {
			db.getTransaction().begin();
			try {
				db.persist(ev);
				db.getTransaction().commit();
			}catch (Exception e){
				e.printStackTrace();
			}
		}


		public boolean removeErreg(Erregistratua erab) {
			Erregistratua e = db.find(Erregistratua.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeUser(User erab) {
			User e = db.find(User.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeAdmin(Admin erab) {
			Admin e = db.find(Admin.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeEditor(Editorea erab) {
			Editorea e = db.find(Editorea.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeErregistratua(Erregistratua erab) {
			Erregistratua e = db.find(Erregistratua.class, erab);
			if(e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				this.removeUser(erab);
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeAuk(Aukera auk) {
			Aukera a = db.find(Aukera.class, auk);
			if(a!=null) {
				db.getTransaction().begin();
				db.remove(a);
				db.getTransaction().commit();
				return true;
			}else {
				return false;
			}
		}
		
		public boolean removeQuestion(Question q) {
			System.out.println(">> DataAccessTest: removeEvent");
			Question e = db.find(Question.class, q);
			if (e!=null) {
				db.getTransaction().begin();
				db.remove(e);
				db.getTransaction().commit();
				return true;
			} else 
			return false;
	    }
}
