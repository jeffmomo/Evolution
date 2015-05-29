import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class BoatProblem implements Problem
{
	private Item[] _factors;
	private Random _randomiser;

	private int _maxVol;
	private int _maxWeight;
	private int _maxCost;

	public BoatProblem(Random randomiser, int maxVol, int maxWeight, int maxCost, Item[] factors)
	{
		_factors = factors;
		_randomiser = randomiser;

		_maxVol = maxVol;
		_maxWeight = maxWeight;
		_maxCost = maxCost;
	}

	public int permute(int permutant)
	{
		int changeAt = _randomiser.nextInt(_factors.length);
		return permutant & (~(permutant & (1 << changeAt)));
	}

	public int generate()
	{
		int itemSet = 0;
		for(int i = 0; i < _factors.length; i++)
		{
			itemSet = itemSet | (_randomiser.nextBoolean() ? (1 << i) : (0));
		}

		return itemSet;
	}

	public double evaluate(int itemSet)
	{
		int cost = 0;
		int value = 0;
		int weight = 0;
		int volume = 0;

		for(int i = 0; i < _factors.length && itemSet > 0; i++)
		{
			if((itemSet & (1 << i)) > 0)
			{
				Item item = _factors[i];
				cost += item.cost;
				value += item.value;
				weight += item.weight;
				volume += item.volume;

				if(cost > _maxCost || weight > _maxWeight || volume > _maxVol)
					return -1;
			}

			itemSet = itemSet & ~(1 << i);
		}

		return value;
	}


}
