/**
 * Represents an item in the boat problem
 */
public class Item
{
	public double value = 0;
	public double volume = 0;
	public double weight = 0;
	public double cost = 0;

	public Item(double volume, double weight, double cost, double value)
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
