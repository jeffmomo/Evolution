/**
 * Created by Jeff on 29/05/2015.
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

	public Item clone()
	{
		return new Item(volume, weight, cost, value);
	}

	public void add(Item i2)
	{
		volume += i2.volume;
		weight += i2.weight;
		cost += i2.cost;
		value += i2.value;
	}
	public void subtract(Item i2)
	{
		volume -= i2.volume;
		weight -= i2.weight;
		cost -= i2.cost;
		value -= i2.value;
	}
}
