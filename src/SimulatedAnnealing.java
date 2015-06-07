/**
 * Runs simulated annealing on a problem
 */
public class SimulatedAnnealing<T>
{
	private float _temp;

	private int _maxTemp;
	private int _initSamples;

	private long _currentTime;
	private double _timeFactor;

	private Problem<T> _problem;

	public SimulatedAnnealing(int maxTemp, int initialSamples, long maxTimeMilli, Problem<T> problem)
	{
		_maxTemp = maxTemp;
		_problem = problem;
		_initSamples = initialSamples;
		_currentTime = System.nanoTime();

		// Calculates a time factor based on the fact that it takes a longer time per sample at high temperatures
		// Uses the formula x(x + 1) / 2, and matches it up against the max time
		_timeFactor = (maxTimeMilli * 1000000) / ((maxTemp * maxTemp + maxTemp) / 2);

		// Adds the time for the first temp pass
		_currentTime += _timeFactor * maxTemp;
	}

	// Runs the heuristic
	public T run()
	{
		// Resets the temperature
		_temp = _maxTemp;

		// Gets a random sample
		T sample = randomise();
		// Stores the evaluated value of the best current solution
		double bestResult = _problem.evaluate(sample);

		// Repeat until the temperature is zero
		while(_temp > 1)
		{

				// Permutes the problem _temp times
				T test = _problem.permute(sample, (int)(_temp));

				// Evaluates the permuted problem
				double eval = _problem.evaluate(test);
				// And if its better than the best, then store it
				if(eval > bestResult)
				{
					bestResult = eval;
					sample = test;
				}

				// Recalculate temperature when a certain time limit is reached
				if(System.nanoTime() >= _currentTime)
				{
					// Non-linear time allocation for each temp - this is to counteract the fact that permutation takes more time when temp is high
					_temp--;
					_currentTime += _temp * _timeFactor;

					System.err.println("Best Result at Temp = " + _temp + " is: " + bestResult);
				}

		}
		// Returns the best sample generated
		return sample;
	}

	// Generates some random samples and returns the best
	private T randomise()
	{
		double bestResult = 0;
		// Sets a minimum sample that anything is better than
		T bestSample = _problem.minimum();

		// Generates a certain number of samples, evaluates them, and if better than the current best, make it the current best
		for(int i = 0; i < _initSamples; i++)
		{
			T sample = _problem.generate();
			double eval = _problem.evaluate(sample);
			if(eval > bestResult)
			{
				bestResult = eval;
				bestSample = sample;
			}
		}

		// If bad samples generated, then repeat until a useful one is generated
		return (bestResult == 0 ? randomise() : bestSample);
	}

}
