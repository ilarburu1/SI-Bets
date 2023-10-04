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
		try {
			Erregistratua erab = new Erregistratua("aa", "bb", "111111111A", 2); 
			Aukera auk = new Aukera(1,"Atletico",2);
			testDA.persist(erab);
			testDA.persist(auk);
			Apustua apustua = new Apustua("erab", 1);
			sut.addBet(auk, apustua);
			assertTrue(true);
		}catch(Exception a) {
			fail("Not yet implemented");
		}
	}

}
