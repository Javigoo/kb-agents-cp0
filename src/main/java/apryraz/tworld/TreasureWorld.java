package apryraz.tworld;



import java.io.IOException;
import org.sat4j.specs.*;
import org.sat4j.minisat.*;
import org.sat4j.reader.*;


/**
  The class for the main program of the Treasure World

**/
public class TreasureWorld {



  /**
   This function should execute the sequence of steps stored in the file fileSteps,
   but only up to numSteps steps. Each step must be executed with function
   runNextStep() of the BarcenasFinder agent.

   @param wDim the dimension of world
   @param tX x coordinate of Barcenas position
   @param tY y coordinate of Barcenas position
   @param numSteps num of steps to perform
   @param fileSteps file name with sequence of steps to perform
   @param filePirates file name with sequence of steps to perform

   **/
  public static void runStepsSequence( int wDim, int tX, int tY,
                                      int numSteps, String fileSteps, String filePirates ) throws
                                 IOException,  ContradictionException, TimeoutException {
    // Make instances of TreasureFinder agent and environment object classes
    TreasureFinder TAgent = new TreasureFinder(wDim) ;
    TreasureWorldEnv EnvAgent = new TreasureWorldEnv(wDim, tX, tY) ;


    // Set environment object, and load list of pirate positions
    EnvAgent.loadPiratesLocations(filePirates);   // <----- Tambien se lo podemos pasar en el constructor.
    TAgent.setEnvironment(EnvAgent);

    // load list of steps into the Finder Agent
    TAgent.loadListOfSteps(numSteps, fileSteps);

    // Execute sequence of steps with the Agent
    for (int stepNum = 0; stepNum < numSteps; stepNum++) {
  		TAgent.runNextStep();
  	}

  }

  /**
  *  This function should load five arguments from the command line:
  *  arg[0] = dimension of the word
  *  arg[1] = x coordinate of treasure position
  *  arg[2] = y coordinate of treasure position
  *  arg[3] = num of steps to perform
  *  arg[4] = file name with sequence of steps to perform
  *  arg[5] = file name with list of pirate positions
  **/
  public static void main ( String[] args) throws ParseFormatException,
          IOException,  ContradictionException, TimeoutException {

    // Here I run a concrete example, but you should read parameters from
    // the command line, as decribed above.
    // runStepsSequence( 4, 3, 3, 5, "tests/steps1.txt", "tests/pirates1.txt"  );
    if (args != null && args.length > 5) {
  			int WorldDim = Integer.parseInt(args[0]);
  			int TreasureX = Integer.parseInt(args[1]);
  			int TreasureY = Integer.parseInt(args[2]);
  			int numSteps = Integer.parseInt(args[3]);
  			String stepsFile = args[4];
        String piratesFile = args[5];
  			runStepsSequence(WorldDim, TreasureX, TreasureY, numSteps, stepsFile, piratesFile);
  	} else {
  			System.out.println("Error: Not enough arguments given");
  	}

  }
}
