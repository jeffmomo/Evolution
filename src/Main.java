import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args)
    {

	    double maxWeight, maxVol, maxCost;
	    int maxTimeSeconds;
	    String inputFile;

	    if(args.length == 5)
	    {
		    maxVol = Double.parseDouble(args[0]);
		    maxWeight = Double.parseDouble(args[1]);
		    maxCost = Double.parseDouble(args[2]);
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
			    items.add(new Item(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
		    }

		    int numItems = items.size();


		    BoatProblem2 bp = new BoatProblem2(new Random(), maxVol, maxWeight, maxCost, items.toArray(new Item[numItems]));

		    SimulatedAnnealing<ValueObject> sa = new SimulatedAnnealing<ValueObject>(numItems / 1, 1000, maxTimeSeconds * 1000, bp);

		    long startTime = new Date().getTime();

		    ValueObject solution = sa.run();

		    System.err.println("RESULTS");
		    System.err.println("Res: " + bp.evaluate(solution));

		    System.err.println(solution.value.volume);
		    System.err.println(solution.value.weight);
		    System.err.println(solution.value.cost);
		    System.err.println(solution.value.value);

		    long endTime = new Date().getTime();

		    double timeCost = (double)(endTime - startTime) / 1000;

		    System.err.println("Total time cost: " + timeCost);

		    for(int g = 0; g < numItems; g++)
		    {
			    if(ValueObject.bitAt(solution.data, g))
				    System.out.println(g + 1);
		    }

	    }
	    catch(IOException e)
	    {
		    e.printStackTrace();
	    }



    }
}
