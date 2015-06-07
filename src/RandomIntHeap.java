import java.util.Random;

/**
 * Gets a random int out of a limited pool
 */
public class RandomIntHeap
{
	private int[] _array;
	private int _count;

	private Random _rand;


	public RandomIntHeap(int bound, Random randomiser)
	{
		_array = new int[bound];
		_count = bound;
		_rand = randomiser;

		// Fills array
		for(int i = 0; i < bound; i++)
		{
			_array[i] = i;
		}
	}

	// Gets the next random int
	public int getNext()
	{
		// Swaps the randomly selected location with the tail, and decrement count
		// Returns the int at the selected location
		int pos = _rand.nextInt(_count);
		int out = _array[pos];
		_array[pos] = _array[--_count];

		return out;
	}

	// Gets if the heap is empty
	public boolean isEmpty()
	{
		return _count <= 0;
	}
}
