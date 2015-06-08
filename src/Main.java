import sun.dc.pr.PRError;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

	private static double PRECISION = 1000;


    public static void main(String[] args)
    {

	    int maxWeight, maxVol, maxCost;
	    int maxTimeSeconds;
	    String inputFile;

	    if(args.length == 5)
	    {
		    maxVol = (int)((Double.parseDouble(args[0]) * PRECISION));
		    maxWeight = (int)((Double.parseDouble(args[1]) * PRECISION));
		    maxCost = (int)((Double.parseDouble(args[2]) * PRECISION));
		    maxTimeSeconds = Integer.parseInt(args[3]);
		    inputFile = args[4];
	    }
	    else
	    {
		    System.err.println("Incorrect number of arguments");
		    return;
	    }

	    try
	    {
		    File f = new File(inputFile);

		    BufferedReader br = new BufferedReader(new FileReader(f));

		    List<Item> items = new LinkedList<Item>();
		    String temp;
		    while((temp = br.readLine()) != null)
		    {
			    String[] split = temp.split(" ");
			    items.add(new Item((int)(Double.parseDouble(split[0]) * PRECISION), (int)(Double.parseDouble(split[1]) * PRECISION),(int)(Double.parseDouble(split[2]) * PRECISION), (int)(Double.parseDouble(split[3]) * PRECISION)));
		    }

		    int numItems = items.size();


		    BoatProblem bp = new BoatProblem(new Random(), maxVol, maxWeight, maxCost, items.toArray(new Item[numItems]));

		    SimulatedAnnealing<ValueObject> sa = new SimulatedAnnealing<ValueObject>(numItems / 3, 10000, maxTimeSeconds * 1000, bp);

		    long startTime = new Date().getTime();

		    ValueObject solution = sa.run();

//		    System.err.println("RESULTS");
//
//		    System.err.println(solution.value.volume);
//		    System.err.println(solution.value.weight);
//		    System.err.println(solution.value.cost);
//		    System.err.println(solution.value.value);
//
//		    long endTime = new Date().getTime();
//
//		    double timeCost = (double)(endTime - startTime) / 1000;
//
//		    System.err.println("Total time cost: " + timeCost);

		    for(int g = 0; g < numItems; g++)
		    {
			    if(ValueObject.bitAt(solution.data, g))
				    System.out.println(g + 1);
		    }

		    //System.err.println("Res: " + bp.evaluate(solution) / PRECISION);

	    }
	    catch(IOException e)
	    {
		    e.printStackTrace();
	    }



    }
}
