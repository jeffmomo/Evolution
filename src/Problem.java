/**
 * Created by Jeff on 29/05/2015.
 */
public interface Problem<T>
{

	public T permute(T in, int passes);
	public T generate();
	public double evaluate(T in);
	public T minimum();
	public float tempFunction(float temp);

}
