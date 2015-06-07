import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class BoatProblem2 implements Problem<ValueObject>
{
	private Item[] _factors;
	private Random _randomiser;

	private double _maxVol;
	private double _maxWeight;
	private double _maxCost;

	public BoatProblem2(Random randomiser, double maxVol, double maxWeight, double maxCost, Item[] factors)
	{
		_factors = factors;
		_randomiser = randomiser;

		_maxVol = maxVol;
		_maxWeight = maxWeight;
		_maxCost = maxCost;
	}

	// Permutes the ValueObject a number of times
	public ValueObject permute(ValueObject permutantObj, int passes)
	{
		byte[] permuted = permutantObj.data.clone();

		// Permutes it
		for(int i = 0; i < passes; i++)
		{
			// Gets a random location to flip bits
			int changeAt = _randomiser.nextInt(_factors.length);
			ValueObject.flipAt(permuted, changeAt);
		}

		return new ValueObject(permuted, permutantObj.value);
	}

	// Returns the minimum possible sample
	public ValueObject minimum()
	{
		return new ValueObject(new byte[_factors.length / 8 + 1], new Item());
	}


	public ValueObject generate()
	{
		// Creates a new bytearray
		byte[] itemSet = new byte[(_factors.length / 8)+1];
		// Creates a new item to represent the cumulative
		Item outItem = new Item();

		// Loops through the number of items in set
		for(int i = 0; i < _factors.length; i++)
		{
			// If random boolean is true then set the bit to true - but only if its valid
			if(_randomiser.nextBoolean())
			{
				Item item = _factors[i];
				// Checks the validity of including the item at i
				if(outItem.weight + item.weight <= _maxWeight && outItem.volume + item.volume <= _maxVol)
				{
					// If valid then add item to cumulative
					outItem.cost += item.cost;
					outItem.value += item.value;
					outItem.weight += item.weight;
					outItem.volume += item.volume;

					// And sets the bit to true
					ValueObject.orAt(itemSet, i);
				}
			}

		}

		// Returns the sample
		return new ValueObject(itemSet, outItem);
	}

	// Just returns the resell value of the cumulative items
	public double evaluate(ValueObject itemSet)
	{
		Item outItem = new Item();
		byte[] dataset = itemSet.data;

		for(int i = 0; i < _factors.length; i++)
		{
			if(ValueObject.bitAt(dataset, i))
			{
				Item item = _factors[i];

				outItem.cost += item.cost;
				outItem.value += item.value;
				outItem.weight += item.weight;
				outItem.volume += item.volume;

				if(outItem.weight > _maxWeight || outItem.volume > _maxVol)
					return -1;
			}

		}

		return outItem.value - outItem.cost;
	}

}
