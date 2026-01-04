package test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.*;

import quality.*;

public class AirQualityTest {
   /*
    * This is a Java Test Class, which uses the JUnit package to create
    * and run tests. You do NOT have to submit this class.
    * 
    * You can fill in these tests in order to evaluate your code. Writing tests
    * is a crucial skill to have as a developer.
    * 
    * How to run?
    * - MAKE SURE you are in the right directory. On the right side of your VS Code
    * Explorer, you should see:
    * AirQuality
    * lib
    * src
    * test
    * input files
    * NOT:
    * AirQuality/CS112/Another Folder Name
    * AirQuality
    * ...
    * Open the INNERMOST AirQuality (case sensitive) using File -> Open Folder.
    * - Right click AirQualityTest.java in the VS Code explorer and select
    * "Run Tests"
    */

   @Test
   public void testAddState() {
       AirQuality test = new AirQuality();
       test.buildTable("addState.csv");
   
       State[] res = test.getHashTable();
   
       // Assertion will fail if index 0 is empty
       assertNotNull(res[0]);
       // Assertion will fail if Minnesota is not the first state name
       assertEquals("Minnesota", res[0].getName());
       // Assertion will fail if next node at index 0 is not null
       assertNull(res[0].getNext());
   
       // Assertion will fail if index 4 is empty
       assertNotNull(res[4]);
       // Assertion will fail if Kentucky is not the first state name at index 4
       assertEquals("Kentucky", res[4].getName());
       // Assertion will fail if next node at index 4 is not null
       assertNull(res[4].getNext());
   
       // Assertion will fail if index 5 is empty
       assertNotNull(res[5]);
       // Assertion will fail if New York is not the first state name at index 5
       assertEquals("New York", res[5].getName());
       // Assertion will fail if New York's next node is null
       assertNotNull(res[5].getNext());
       // Assertion will fail if the second node in the chain is not Louisiana
       assertEquals("Louisiana", res[5].getNext().getName());
       // Assertion will fail if Louisiana's next node is null
       assertNotNull(res[5].getNext().getNext());
       // Assertion will fail if the third node in the chain is not Florida
       assertEquals("Florida", res[5].getNext().getNext().getName());
       // Assertion will fail if Florida's next node is not null (indicating the end of the chain)
       assertNull(res[5].getNext().getNext().getNext());
   
       // Assertion will fail if index 6 is empty
       assertNotNull(res[6]);
       // Assertion will fail if Pennsylvania is not the first state name at index 6
       assertEquals("Pennsylvania", res[6].getName());
       // Assertion will fail if Pennsylvania's next node is null
       assertNotNull(res[6].getNext());
       // Assertion will fail if the second node in the chain is not New Jersey
       assertEquals("New Jersey", res[6].getNext().getName());
       // Assertion will fail if New Jersey's next node is null
       assertNotNull(res[6].getNext().getNext());
       // Assertion will fail if the third node in the chain is not California
       assertEquals("California", res[6].getNext().getNext().getName());
       // Assertion will fail if California's next node is not null (indicating the end of the chain)
       assertNull(res[6].getNext().getNext().getNext());
   
       // Assertion will fail if index 7 is empty
       assertNotNull(res[7]);
       // Assertion will fail if Virginia is not the first state name at index 7
       assertEquals("Virginia", res[7].getName());
       // Assertion will fail if Virginia's next node is null
       assertNotNull(res[7].getNext());
       // Assertion will fail if the second node in the chain is not Texas
       assertEquals("Texas", res[7].getNext().getName());
       // Assertion will fail if Texas's next node is null
       assertNotNull(res[7].getNext().getNext());
       // Assertion will fail if the third node in the chain is not North Dakota
       assertEquals("North Dakota", res[7].getNext().getNext().getName());
       // Assertion will fail if North Dakota's next node is not null (indicating the end of the chain)
       assertNull(res[7].getNext().getNext().getNext());
   
       // Assertion will fail if index 8 is empty
       assertNotNull(res[8]);
       // Assertion will fail if Wyoming is not the first state name at index 8
       assertEquals("Wyoming", res[8].getName());
       // Assertion will fail if next node at index 8 is not null
       assertNull(res[8].getNext());
   
       // Assertion will fail if index 9 is empty
       assertNotNull(res[9]);
       // Assertion will fail if Ohio is not the first state name at index 9
       assertEquals("Ohio", res[9].getName());
       // Assertion will fail if Ohio's next node is null
       assertNotNull(res[9].getNext());
       // Assertion will fail if the second node in the chain is not Colorado
       assertEquals("Colorado", res[9].getNext().getName());
       // Assertion will fail if Colorado's next node is not null (indicating the end of the chain)
       assertNull(res[9].getNext().getNext());
   }   

   @Test
   public void testLoadFactor() {
      //fail("This test is not yet implemented. Replace this line with code to implement this test.");
      AirQuality testAQ = new AirQuality();

      State testState = new State("TestState");
      testAQ.addState("TestState,SomeCounty,50,40.0,-74.0");

      for(int i = 0; i<10; i++){
         County county = new County("County" + 1, 40.0, -74.0, null);
         testState.addCounty(county);
      }

      assertTrue(testAQ.checkCountiesHTLoadFactor(testState));
   }

   @Test
   public void testRehash() {
      AirQuality testAQ = new AirQuality();
      State testState = new State ("TestSTate");
      testAQ.addState("TestSTate,Somecounty,50,40.0,-74.0,null");

      for(int i = 0; i<10; i++){
         County county = new County("County" + 1, 40, -74.0, null);
         testState.addCounty(county);
      }

      int initialSize = testState.getCounties().length;
      testAQ.rehash(testState);

      assertEquals(initialSize * 2, testState.getCounties().length);

      for(int i = 0; i<10;i++){
         String countyName = "County" + 1;
         int newIndex = Math.abs(countyName.hashCode()) % testState.getCounties().length;
         assertNotNull(testState.getCounties()[newIndex]);
         assertEquals(countyName, testState.getCounties()[newIndex].getName());
      }
      //fail("This test is not yet implemented. Replace this line with code to implement this test.");
   }

   @Test
   public void testAddCountyAndPollutant() {
      // Instantiate a new AirQuality object.
      AirQuality testAQ = new AirQuality();

      State testState = testAQ.addState("TestState,SomeCounty,50,40,-74.0");

      String inputLine1 = "TestState,CountyA,55,40.1,-74.1,Carbon Monoxide, yellow";
      testAQ.addCountyAndPollutant(testState, inputLine1);

      int countyIndex = Math.abs("CountyA".hashCode()) % testState.getCounties().length;
      County countyA = testState.getCounties()[countyIndex];
      assertNotNull(countyA);
      assertEquals("CountyA", countyA.getName());

      Pollutant pollutant = countyA.getPollutants().get(0);
      assertEquals("Carbon Monoxide", pollutant.getName());
      assertEquals(55, pollutant.getAQI());
      assertEquals("Yellow", pollutant.getColor());

      String inputLine2 = "TestState,CountyA,75,40.1,74.1,Carbon Monoxide,Orange";
      testAQ.addCountyAndPollutant(testState, inputLine2);

      pollutant = countyA.getPollutants().get(0);
      assertEquals(75, pollutant.getAQI());
      assertEquals("Orange", pollutant.getColor());

      // Call buildTable() on rehashCounties.csv.

      // There are two states, you can check Texas' counties.

      // Check that the counties are added to the correct states.
      // - grab the county table from a state by using state.getCounties()
      // - county hash functions are defined by county code % counties table length
      
      // You can also check if pollutants are added to the correct counties.

      // Remove this line once you have written this test.
      //fail("This test is not yet implemented. Replace this line with code to implement this test.");
   }

   @Test
   public void testSetStatesAQIStats() {
      AirQuality testAQ = new AirQuality();
      State testState = testAQ.addState("TestState,CountyA,50,40.1,-74.1,PollutantA,Yellow");
      testAQ.addCountyAndPollutant(testState, "TestState,CountyA,50,40.0,-74.1,PollutantA,Yellow");
      testAQ.addCountyAndPollutant(testState, "TestState,CountyB,75,40.2,-74.2,PollutantB,Orange");
      testAQ.addCountyAndPollutant(testState, "TestState,Countyc,75,40.3,-74.3,PollutantC,Green");

      testAQ.setStatesAQIStats();

      assertEquals(66.67, testState.getAvgAQI(), 0.01);

      assertEquals("CountyB", testState.getHighestAQI().getName());
      assertEquals("CountyA", testState.getLowestAQI().getName());

      // Remove this line once you have written this test.
      //fail("This test is not yet implemented. Replace this line with code to implement this test.");
   }

   @Test
   public void testMeetsThreshold() {
      AirQuality airQuality = new AirQuality();
      airQuality.buildTable("rehashCounties.csv");
      airQuality.setStatesAQIStats();
      
      String stateName = "California";
      String pollutantName = "Nitric oxide (NO)";
      int AQIThreshold = 50;
      
      String[] expectedCountyNames = {"Santa Barbara", "Los Angeles","San Francisco", "Sacramento"};

      ArrayList<County> result = airQuality.meetsThreshold(stateName, pollutantName, AQIThreshold);

      for(int i = 0; i < result.size(); i++){
         County county = result.get(i);
         System.out.println("County: " + county.getName());
         System.out.println("Expected: " + expectedCountyNames[i] + "Actual: " + county.getName());
         assertEquals("Mismatch at index: " + i, expectedCountyNames[i], county.getName());

         //assertEquals(expectedCountyNames[i], county.getName());
      }
      assertEquals("Result size does not match expected size",expectedCountyNames.length, result.size());
     
     
      // Remove this line once you have written this test.
      fail("This test is not yet implemented. Replace this line with code to implement this test.");
   }

}
