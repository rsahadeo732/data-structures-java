package election;

/* 
 * Election Analysis class which parses past election data for the house/senate
 * in csv format, and implements methods which can return information about candidates
 * and nationwide election results. 
 * 
 * It stores the election data by year, state, then election using nested linked structures.
 * 
 * The years field is a Singly linked list of YearNodes.
 * 
 * Each YearNode has a states Circularly linked list of StateNodes
 * 
 * Each StateNode has its own singly linked list of ElectionNodes, which are elections
 * that occured in that state, in that year.
 * 
 * This structure allows information about elections to be stored, by year and state.
 * 
 * @author Colin Sullivan
 */
public class ElectionAnalysis {

    // Reference to the front of the Years SLL
    private YearNode years;

    public YearNode years() {
        return years;
    }

    /*
     * Read through the lines in the given elections CSV file
     * 
     * Loop Though lines with StdIn.hasNextLine()
     * 
     * Split each line with:
     * String[] split = StdIn.readLine().split(",");
     * Then access the Year Name with split[4]
     * 
     * For each year you read, search the years Linked List
     * -If it is null, insert a new YearNode with the read year
     * -If you find the target year, skip (since it's already inserted)
     * 
     * If you don't find the read year:
     * -Insert a new YearNode at the end of the years list with the corresponding year.
     * 
     * @param file String filename to parse, in csv format.
     */
    public void readYears(String file) {
		// WRITE YOUR CODE HERE
    // StdIn.setFile(file);
    // while(StdIn.hasNextLine()){
    //   String [] split = StdIn.readLine().split(",");
    //   int year = Integer.parseInt(split[4]);
      
    //   YearNode yearPtr = years;
    //   boolean yearFound = false;
    //   if(years == null){
    //     years = new YearNode(year);
    //   } else {

    //     yearPtr = years;
    //     yearFound = false;

    //     while(yearPtr.getNext() != null){
    //       if(yearPtr.getYear() == year){
    //         yearFound = true;
    //         break;
    //       }
    //       yearPtr = yearPtr.getNext();
    //     }
    //     if(!yearFound && yearPtr.getYear() !=year){
    //       yearPtr.setNext(new YearNode(year));
    //     }
    //   }
    // }


    //open the csv file for reading
    //StdIn is used to read the file line for line
    StdIn.setFile(file);

    //loops through each line of the csv file
    //until no more lines exist
    while(StdIn.hasNextLine()){

      //Reads a line from the file and splits it into an
      //array based on commas. each element in the array
      //represents a piece of data from the csv file
      String [] split = StdIn.readLine().split(",");

      //extracts the year from the csv line and converts it to integer
      int year = Integer.parseInt(split[4]);

      //a pointer that starts at the head of the linked list
      //of year node (years) and will traverse the list
      //to check if the year already exists
      YearNode yearPtr = years;
      boolean yearFound = false;

      //loops through the linked list of YearNode.
      //yearPtr moves as long as its not pointing at null
      while(yearPtr != null){
        //checks if the current yearnode in the list contains the year
        //we are looking for, if so yearFound is set to true and
        //the loop stops
        if(yearPtr.getYear() == year){
          yearFound = true;
          break;
        }
        //moves the pointer to the next node in the linked list
        //this line traverses the list to see if
        //the year is already present
        yearPtr = yearPtr.getNext();
      }
      //if we did not find the year in the list
      //we need to create a new yearnode for the year
      if(!yearFound){
        //creates a new yearnode with the year we just read from the csv
        YearNode newYear = new YearNode(year);
        
        //if the list is empty, this line sets the new year
        //as the head of the list
        if(years == null){
          years = newYear;
        }else{
          //a temporary pointer that starts at the head of the list (years)
          //and will traverse to the end of the list
          YearNode tempPtr = years;

          //loops through the list until we reach the last node
          //where tempPtr.getNext() == null
          while(tempPtr.getNext() != null){
            tempPtr = tempPtr.getNext();
          }
          //sets the next pointer of the last node in the list
            //to point to the new yearnode
            tempPtr.setNext(newYear);
        }
      }
    }



    }

    /*
     * Read through the lines in the given elections CSV file
     * 
     * Loop Though lines with StdIn.hasNextLine()
     * 
     * Split each line with:
     * String[] split = StdIn.readLine().split(",");
     * Then access the State Name with split[1] and the year with split[4]
     * 
     * For each line you read, search the years Linked List for the given year.
     * 
     * In that year, search the states list. If the target state exists, continue
     * onto the next csv line. Else, insert a new state node at the END of that year's
     * states list (aka that years "states" reference will now point to that new node).
     * Remember the states list is circularly linked.
     * 
     * @param file String filename to parse, in csv format.
     */
    public void readStates(String file) {
		// // WRITE YOUR CODE HERE
    StdIn.setFile(file);
    while(StdIn.hasNextLine()){
      String [] split = StdIn.readLine().split(",");
      int year = Integer.parseInt(split[4]);
      String state = split[1];

      YearNode yearPtr = years;

      while(yearPtr !=null && yearPtr.getYear() != year){
        yearPtr = yearPtr.getNext();
      }
      if(yearPtr != null && yearPtr.getYear() == year){
        StateNode statePtr = yearPtr.getStates();

        if(statePtr == null){
          StateNode newState = new StateNode(state, null);
          yearPtr.setStates(newState);
          newState.setNext(newState);
        }else{
          StateNode temp = statePtr;
          boolean stateFound = false;

          do{
            if(temp.getStateName().equals(state)){
              stateFound = true;
              break;
            }
            temp = temp.getNext();
          }while(temp != statePtr);
          if(!stateFound){
            StateNode newState = new StateNode(state, statePtr.getNext());
            statePtr.setNext(newState);
            yearPtr.setStates(newState);
          }
        }
      }
    }

    
    }

    /*
     * Read in Elections from a given CSV file, and insert them in the
     * correct states list, inside the correct year node.
     * 
     * Each election has a unique ID, so multiple people (lines) can be inserted
     * into the same ElectionNode in a single year & state.
     * 
     * Before we insert the candidate, we should check that they dont exist already.
     * If they do exist, instead modify their information new data.
     * 
     * The ElectionNode class contains addCandidate() and modifyCandidate() methods for you to use.
     * 
     * @param file String filename of CSV to read from
     */
    public void readElections(String file) {
		  // WRITE YOUR CODE HERE
      //open the file
      StdIn.setFile(file);
      while(StdIn.hasNextLine()){
        //read and parse the line
        String line = StdIn.readLine();
        String [] split = line.split(",");
        
        //extract the data and store in variables
        int raceID = Integer.parseInt(split[0]);
        String stateName = split[1];
        int officeID = Integer.parseInt(split[2]);
        boolean senate = split[3].equals("U.S. Senate");
        int year = Integer.parseInt(split[4]);
        String candidateName = split[5];
        String party = split[6];
        int votes = Integer.parseInt(split[7]);
        boolean winner = split[8].toLowerCase().equals("true");
        
        //find year node
        YearNode yearPtr = years;
        while(yearPtr != null && yearPtr.getYear() != year){
          yearPtr = yearPtr.getNext();
        }
        
        //if we found the year find the state
        if(yearPtr !=null){
          StateNode statePtr = yearPtr.getStates();
          StateNode tempStatePtr = statePtr;

          do{
            if(tempStatePtr.getStateName().equals(stateName)){
              //found the correct state
              break;
            }
            tempStatePtr = tempStatePtr.getNext();
            //stop when we loop back to start
          }while(tempStatePtr != statePtr);

          if(tempStatePtr != null){
            ElectionNode electionPtr = tempStatePtr.getElections();
            ElectionNode prevElectionPtr = null;

            while(electionPtr != null && electionPtr.getRaceID() != raceID){
              prevElectionPtr = electionPtr;
              electionPtr = electionPtr.getNext();
            }

            if(electionPtr != null){
              if(!electionPtr.isCandidate(candidateName)){
                electionPtr.addCandidate(candidateName, votes, party, winner);
              }else{
                electionPtr.modifyCandidate(candidateName, votes, party);
              }
            }else{
              ElectionNode newElection = new ElectionNode();
              newElection.setRaceID(raceID);
              newElection.setSenate(senate);
              newElection.setoOfficeID(officeID);
              newElection.addCandidate(candidateName, votes, party, winner);

              if(prevElectionPtr == null){
                tempStatePtr.setElections(newElection);
              }else{
                prevElectionPtr.setNext(newElection);
              }
            }
          }
        }

      }
        
    }

    /*
     * DO NOT EDIT
     * 
     * Calls the next method to get the difference in voter turnout between two
     * years
     * 
     * @param int firstYear First year to track
     * 
     * @param int secondYear Second year to track
     * 
     * @param String state State name to track elections in
     * 
     * @return int Change in voter turnout between two years in that state
     */
    public int changeInTurnout(int firstYear, int secondYear, String state) {
        // DO NOT EDIT
        int last = totalVotes(firstYear, state);
        int first = totalVotes(secondYear, state);
        return last - first;
    }

    /*
     * Given a state name, find the total number of votes cast
     * in all elections in that state in the given year and return that number
     * 
     * If no elections occured in that state in that year, return 0
     * 
     * Use the ElectionNode method getVotes() to get the total votes for any single
     * election
     * 
     * @param year The year to track votes in
     * 
     * @param stateName The state to track votes for
     * 
     * @return avg number of votes this state in this year
     */
    public int totalVotes(int year, String stateName) {
      	// WRITE YOUR CODE HERE
        //find the correct year node
        YearNode yearPtr = years;
        while(yearPtr!=null && yearPtr.getYear()!=year){
          yearPtr = yearPtr.getNext();
        }

        //if the year doesnt exist return 0
        if(yearPtr == null){
          System.out.println("YNF: " + year);
          return 0;
        }

        //find the correct state node
        StateNode statePtr = yearPtr.getStates();
        StateNode tempStatePtr = statePtr;

        do{
          if(tempStatePtr.getStateName().equals(stateName)){
            break;
          }
          tempStatePtr = tempStatePtr.getNext();
        }while(tempStatePtr != statePtr);

        //if state doesnt exist return 0
        if (!tempStatePtr.getStateName().equals(stateName)) {
          //System.out.println("State not found: " + stateName +
          //" in year " + year);
          return 0;
      }


        int totalVotes = 0;
        ElectionNode electionPtr = tempStatePtr.getElections();
        while(electionPtr != null){
          totalVotes += electionPtr.getVotes();
          electionPtr = electionPtr.getNext();
        }
        //System.out.println("Total votes for " + stateName + " in " +
        //year + ": " + totalVotes);
      	return totalVotes;
    }

    /*
     * Given a state name and a year, find the average number of votes in that
     * state's elections in the given year
     * 
     * @param year The year to track votes in
     * 
     * @param stateName The state to track votes for
     * 
     * @return avg number of votes this state in this year
     */
    public int averageVotes(int year, String stateName) {
      	// WRITE YOUR CODE HERE
        //find the correct year node
        YearNode yearPtr = years;
        while(yearPtr != null && yearPtr.getYear() != year){
          yearPtr = yearPtr.getNext();
        }

        //if the year doesnt exist
        if(yearPtr == null){
          return 0;
        }

        //find the correct state node
        StateNode statePtr = yearPtr.getStates();
        StateNode tempStatePtr = statePtr;
        do{
          if(tempStatePtr.getStateName().equals(stateName)){
            break;
          }
          tempStatePtr = tempStatePtr.getNext();
        }while( tempStatePtr != statePtr);

        if(tempStatePtr == null || 
        !tempStatePtr.getStateName().equalsIgnoreCase(stateName)){
          return 0;
        }

        //Sum the votes and count the number of elections
        int totalVotes = 0;
        int numberOfElections = 0;
        ElectionNode electionPtr = tempStatePtr.getElections();
        while(electionPtr != null){
          totalVotes += electionPtr.getVotes();
          numberOfElections++;
          electionPtr = electionPtr.getNext();
        }
        if(numberOfElections == 0){
          return 0;
        }

        int avg = totalVotes / numberOfElections;
        //System.out.println("YNFAVG: " + avg);
      	return avg;
    }

    /*
     * Given a candidate name, return the party they FIRST ran with
     * 
     * Search each year node for elections with the given candidate
     * name. Update that party each time you see the candidates name and
     * return the party they FIRST ran with
     * 
     * @param candidateName name to find
     * 
     * @return String party abbreviation
     */
    public String candidatesParty(String candidateName) {
		  // WRITE YOUR CODE HERE
      //traverse the list of yearrs
      YearNode yearPtr = years;
      while(yearPtr != null){
        StateNode statePtr = yearPtr.getStates();
        StateNode tempStatePtr = statePtr;

        //Traverse the circular linked list (states)
        do{
          ElectionNode electionPtr = tempStatePtr.getElections();

          //traverse list of elections
          while(electionPtr != null){
            //if the candidate exists in this election, return party
            if(electionPtr.isCandidate(candidateName)){
              return electionPtr.getParty(candidateName);
            }
            electionPtr = electionPtr.getNext();
          }
          tempStatePtr = tempStatePtr.getNext();
        }while(tempStatePtr != statePtr);
        yearPtr = yearPtr.getNext();
      }
          
        return null;
    }
}