import static org.junit.Assert.*;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Event;
import domain.Erregistratua;
import domain.Aukera;
import domain.Apustua;
import test.dataAccess.TestDataAccess;

public class addBetDAWTest {

	//sut:system under test
		 static DataAccess sut=new DataAccess();
		 
		 //additional operations needed to execute the test 
		 static TestDataAccess testDA=new TestDataAccess();

		private Event ev;
		
		
		@Test
		//DB-n apustua gehitzen da. Jarraitzailerik ez du.
		public void test() {
			Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2); 
			Aukera auk = new Aukera(1,"Atletico",2);
			try {
				testDA.open();
				testDA.persistErreg((Erregistratua) erab);
				testDA.persistAuk((Aukera) auk);
				testDA.close();
				System.out.println("honaino bai");
				Apustua apustua = new Apustua("aa", 1);
				sut.addBet(auk, apustua);
				assertTrue(true);
			}catch(Exception a) {
				 a.printStackTrace();
				fail("Not yet implemented");
			}finally {
				testDA.open();
				testDA.removeErreg(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		@Test
		//DB-n apustua gehitzen da. Jarraitzaile bat du (diruarekin eta baneatu gabe)
		public void test2() {
			Erregistratua erab = new Erregistratua("aaa", "bb", "1111111111A", 2); 
			Erregistratua erab2 = new Erregistratua("bbb", "nn", "2222222222A", 3);
			Aukera auk = new Aukera(1,"Atletico",2);
			erab.addJarraitzailea(erab2.getUser());
			try {
				testDA.open();
				testDA.persistErreg(erab2);
				testDA.persistErreg(erab);
				testDA.persistAuk(auk);
				testDA.close();
				Apustua apustua = new Apustua("aaa", 1);
				sut.addBet(auk, apustua);
				if(erab2.getApustuak().size()==0) {
					assertTrue(true);
				}else {
					fail("Ez du apusturik izan behar");
				}
			}catch(Exception a) {
				a.printStackTrace();
				fail("Errorea");
			}finally {
				testDA.open();
				testDA.removeErreg(erab2);
				testDA.removeErreg(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}
		
		
		@Test
		//DB-n apustua gehitzen da. Jarraitzaile bat du (banneatuta edo dirurik gabe)
		public void test3() {
			Erregistratua erab = new Erregistratua("aaaa", "bb", "11111111A", 2); 
			Erregistratua erab2 = new Erregistratua("bbbb", "nn", "22222222A", 0.5);
			Aukera auk = new Aukera(1,"Atletico",2);
			erab.addJarraitzailea(erab2.getUser());
			try {
				testDA.open();
				testDA.persistErreg(erab2);
				testDA.persistErreg(erab);
				testDA.persistAuk(auk);
				testDA.close();
				Apustua apustua = new Apustua("aaaa", 1);
				sut.addBet(auk, apustua);
				if(erab2.getApustuak().size()==0) {
					assertTrue(true);
				}else {
					fail("Ez du apusturik izan behar");
				}
			}catch(Exception a) {
				a.printStackTrace();
				fail("Errorea");
			}finally {
				testDA.open();
				testDA.removeErreg(erab2);
				testDA.removeErreg(erab);
				testDA.removeAuk(auk);
				testDA.close();
			}
		}


}
