import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class BoatProblem implements Problem<ValueObject>
{
	private Item[] _factors;
	private Random _randomiser;

	private double _maxVol;
	private double _maxWeight;
	private double _maxCost;

	public BoatProblem(Random randomiser, double maxVol, double maxWeight, double maxCost, Item[] factors)
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
		byte[] permutant = permutantObj.data;
		byte[] permuted = permutant.clone();
		Item permutedItem = permutantObj.value.clone();

		// Permutes it
		for(int i = 0; i < passes; i++)
		{
			// Gets a random location to flip bits
			int changeAt = _randomiser.nextInt(_factors.length);
			// Gets the bit at that location
			boolean oldWasSelected = ValueObject.bitAt(permuted, changeAt);
			// Gets the item corresponding to that location
			Item item = _factors[changeAt];

			// Depending on if item was added or removed (if bit flipped to on or off)
			if(!oldWasSelected)
			{
				// If adding on an item, check if its valid to add it
				if(!(permutedItem.volume + item.volume > _maxVol || permutedItem.weight + item.weight > _maxWeight || permutedItem.cost + item.cost > _maxCost))
				{
					// If valid permutation, then flip the bit and add the item to cumulative item
					ValueObject.flipAt(permuted, changeAt);
					permutedItem.add(item);
				}
				// Else do nothing for this pass
			}
			else
			{
				// If removing an item then just flip the bit and remove item from cumulative item
				ValueObject.flipAt(permuted, changeAt);
				permutedItem.subtract(item);
			}
		}

		return new ValueObject(permuted, permutedItem);
	}

	// Returns the minimum possible sample
	public ValueObject minimum()
	{
		return new ValueObject(new byte[_factors.length / 8 + 1], new Item());
	}

	// Generates a random sample
	public ValueObject generate()
	{
		// Creates a new bytearray
		byte[] itemSet = new byte[(_factors.length / 8)+1];
		// Creates a new item to represent the cumulative
		Item outItem = new Item();

		RandomIntHeap randomIntHeap = new RandomIntHeap(_factors.length, _randomiser);

		// Loops through the number of items in set
		while(!randomIntHeap.isEmpty())
		{
			// Gets the next random location to flip
			int i = randomIntHeap.getNext();

			// If random boolean is true then set the bit to true - but only if its valid
			if(_randomiser.nextBoolean())
			{
				Item item = _factors[i];
				// Checks the validity of including the item at i
				if(outItem.weight + item.weight <= _maxWeight && outItem.volume + item.volume <= _maxVol && outItem.cost + item.cost <= _maxCost)
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
		return itemSet.value.value - itemSet.value.cost;
	}

}
