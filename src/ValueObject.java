/**
 * Created by Jeff on 29/05/2015.
 */
public class ValueObject
{
	public byte[] data;

	public Item value;

	public ValueObject(byte[] data, Item value)
	{
		this.data = data;
		this.value = value;
	}

	public static void flipAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		byteAt = (byte)(byteAt ^ (1 << whichBit));

		in[whichByte] = byteAt;
	}

	public static boolean bitAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		return (byteAt & (1 << whichBit)) > 0;
	}

	public static void orAt(byte[] in, int at)
	{
		int whichByte = at >> 3;
		int whichBit = at % 8;

		byte byteAt = in[whichByte];
		byteAt = (byte)(byteAt | (1 << whichBit));

		in[whichByte] = byteAt;
	}

}
