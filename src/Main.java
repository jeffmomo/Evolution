import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
	    try
	    {
		    File f = new File("C:/Users/Jeff/IdeaProjects/Evolution/src/test");

		    BufferedReader br = new BufferedReader(new FileReader(f));

		    //Item[] items = new Item[numItems];
		    List<Item> items = new LinkedList<Item>();
		    String temp;
		    while((temp = br.readLine()) != null)
		    {
			    String[] split = temp.split(" ");
			    items.add(new Item(Float.parseFloat(split[0]), Float.parseFloat(split[1]), Float.parseFloat(split[2]), Float.parseFloat(split[3])));
		    }

		    int numItems = items.size();


		    BoatProblem bp = new BoatProblem(new Random(), 50, 50, 50, items.toArray(new Item[numItems]));

		    SimulatedAnnealing<ValueObject> sa = new SimulatedAnnealing<ValueObject>(numItems / 3, 1000, 100000, bp);

		    long startTime = new Date().getTime();

		    ValueObject solution = sa.run();


		    System.err.println("RESULTS");
		    System.err.println("Res: " + bp.evaluate(solution));


		    System.err.println(solution.value.volume);
		    System.err.println(solution.value.weight);
		    System.err.println(solution.value.cost);
		    System.err.println(solution.value.value);

		    //System.out.println(solution.data);

		    long endTime = new Date().getTime();

		    double timeCost = (double)(endTime - startTime) / 1000;

		    System.err.println("Total time cost: " + timeCost);

//		    for(int g = 0; g < numItems; g++)
//		    {
//			    if(ValueObject.bitAt(solution.data, g))
//				    System.out.println(g + 1);
//		    }

	    }
	    catch(IOException e)
	    {
		    e.printStackTrace();
	    }



    }
}
