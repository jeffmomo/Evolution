import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
	    try
	    {
		    File f = new File("C:/Users/Jeff/IdeaProjects/Evolution/src/info");

		    BufferedReader br = new BufferedReader(new FileReader(f));
		    int numItems = Integer.parseInt(br.readLine());

		    Item[] items = new Item[numItems];
		    for(int i = 0; i < numItems; i++)
		    {
			    String[] split = br.readLine().split(" ");
			    items[i] = new Item(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
		    }

		    BoatProblem bp = new BoatProblem(new Random(), 100, 100, 100, items);

		    SimulatedAnnealing<ValueObject> sa = new SimulatedAnnealing<ValueObject>(numItems / 5, numItems * 1000, 1000, 1000, bp);

		    long startTime = new Date().getTime();
		    for(int i = 0; i < 1; i++)
		    {
			    ValueObject solution = sa.run();


			    System.out.println("RESULTS");
			    System.out.println(solution.value.value);
			    System.out.println(solution.value.cost);
			    System.out.println(solution.value.volume);
			    System.out.println(solution.value.weight);
			    System.out.println(solution.data);
		    }


		    long endTime = new Date().getTime();

		    double timeCost = (double)(endTime - startTime) / 1000;

		    System.out.println("Total time cost: " + timeCost);

	    }
	    catch(IOException e)
	    {
		    e.printStackTrace();
	    }



    }
}
