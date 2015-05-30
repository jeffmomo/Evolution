import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class BoatProblem implements Problem<ValueObject>
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

	public ValueObject permute(ValueObject permutantObj, int passes)
	{
		byte[] permutant = permutantObj.data;
		byte[] permuted = permutant.clone();
		Item permutedItem = permutantObj.value.clone();


		for(int i = 0; i < passes; i++)
		{
			int changeAt = _randomiser.nextInt(_factors.length);
			boolean oldWasSelected = ValueObject.bitAt(permuted, changeAt);
			Item item = _factors[changeAt];

			if(!oldWasSelected)
			{
				if(!(permutedItem.cost + item.cost > _maxCost || permutedItem.volume + item.volume > _maxVol || permutedItem.weight + item.weight > _maxWeight))
				{
					ValueObject.flipAt(permuted, changeAt);
					permutedItem.add(item);
				}
			}
			else
			{
				ValueObject.flipAt(permuted, changeAt);
				permutedItem.subtract(item);
			}
		}

		return new ValueObject(permuted, permutedItem);
	}

	public ValueObject minimum()
	{
		return new ValueObject(new byte[_factors.length / 8 + 1], new Item());
	}

	public ValueObject generate()
	{
		byte[] itemSet = new byte[(_factors.length / 8)+1];

		Item outItem = new Item();

		for(int i = 0; i < _factors.length; i++)
		{
			if(_randomiser.nextBoolean())
			{
				Item item = _factors[i];
				if(outItem.cost + item.cost <= _maxCost && outItem.weight + item.weight <= _maxWeight && outItem.volume + item.volume <= _maxVol)
				{
					outItem.cost += item.cost;
					outItem.value += item.value;
					outItem.weight += item.weight;
					outItem.volume += item.volume;

					ValueObject.orAt(itemSet, i);
					//itemSet = itemSet | (1 << i);
				}
			}

		}

		return new ValueObject(itemSet, outItem);
	}

	public double evaluate(ValueObject itemSet)
	{
		return itemSet.value.value;
	}

	public float tempFunction(float temp)
	{
		//return temp - 1;
		return temp / 1.5f  - 0.1f;
	}


}
