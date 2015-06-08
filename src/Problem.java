/**
 * A model of a problem applicable to simulated annealing
 */
public interface Problem<T>
{

	// Permutes the solution n number of times
	public T permute(T in, int n);
	// Generates a solution
	public T generate();
	// Evalutates the solution to a quantifiable value
	public int evaluate(T in);
	// The minimum solution possible
	public T minimum();
}
