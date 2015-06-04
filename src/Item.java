/**
 * Represents an item in the boat problem
 */
public class Item
{
	public float value = 0;
	public float volume = 0;
	public float weight = 0;
	public float cost = 0;

	public Item(float volume, float weight, float cost, float value)
	{
		this.value = value;
		this.volume = volume;
		this.weight = weight;
		this.cost = cost;
	}
	public Item()
	{
	}

	// Clones the item
	public Item clone()
	{
		return new Item(volume, weight, cost, value);
	}

	// Adds another item to this
	public void add(Item i2)
	{
		volume += i2.volume;
		weight += i2.weight;
		cost += i2.cost;
		value += i2.value;
	}

	// Subtracts another item from this
	public void subtract(Item i2)
	{
		volume -= i2.volume;
		weight -= i2.weight;
		cost -= i2.cost;
		value -= i2.value;
	}
}
