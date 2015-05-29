import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Main {

    public static void main(String[] args)
    {
	    try
	    {
		    File f = new File("C:/Users/Jeff/IdeaProjects/Evolution/src/info.txt");

		    BufferedReader br = new BufferedReader(new FileReader(f));
		    int numItems = Integer.parseInt(br.readLine());

		    Item[] items = new Item[numItems];
		    for(int i = 0; i < numItems; i++)
		    {
			    String[] split = br.readLine().split(" ");
			    items[i] = new Item(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]), Integer.parseInt(split[3]));
		    }

		    BoatProblem bp = new BoatProblem(new Random(), 10000, 10000, 10000, items);

		    SimulatedAnnealing sa = new SimulatedAnnealing(items.length * 2, 1, 100, 1000, bp);

		    int solution = sa.run();
		    double value = bp.evaluate(solution);

		    System.out.println("RESULTS");
		    System.out.println(value);
		    System.out.println(solution);
	    }
	    catch(IOException e)
	    {
		    e.printStackTrace();
	    }



    }
}
