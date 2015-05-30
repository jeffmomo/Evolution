/**
 * An object which represents a sample
 */
public class ValueObject
{
	// The bits representing the set of items
	public byte[] data;

	// The item representing the cumulation of all items in set
	public Item value;

	public ValueObject(byte[] data, Item value)
	{
		this.data = data;
		this.value = value;
	}

	// Flips a bit at the specified location
	public static void flipAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		byteAt = (byte)(byteAt ^ (1 << whichBit));

		in[whichByte] = byteAt;
	}

	// Gets the value of the bit at location
	public static boolean bitAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		return (byteAt & (1 << whichBit)) > 0;
	}

	// Set bit at location to true
	public static void orAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		byteAt = (byte)(byteAt | (1 << whichBit));

		in[whichByte] = byteAt;
	}

}
