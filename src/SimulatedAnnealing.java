import java.util.Random;

/**
 * Runs simulated annealing on a problem
 */
public class SimulatedAnnealing<T>
{
	private float _temp;

	private long _maxTime;
	private float _maxTemp;
	private int _numSamples;
	private int _initSamples;

	private long _initTime;

	private float _stage;
	private float _curr;

	private Problem<T> _problem;

	public SimulatedAnnealing(float maxTemp, int initialSamples, long maxTimeMilli, Problem<T> problem)
	{
		_maxTemp = maxTemp;
		_maxTime = maxTimeMilli * 1000000;
		_problem = problem;
		_initSamples = 100;
		_initTime = System.nanoTime();

		_stage = 1;
		_curr = _maxTime / 10f;//(int)maxTemp;

		_numSamples = initialSamples;
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
					System.err.println(eval - bestResult);
					bestResult = eval;
					sample = test;
				}

				// Check if time is running out
				if((System.nanoTime() - _initTime) >= _curr)
				{

					//_stage += 1;
//					if(_stage > _maxTemp)
//					{
//						return sample;
//					}

					_curr += ((_maxTime - _curr) / 10) + 1;

					_temp -= (_temp) / 5f ;
					//_initTime += _stage;
					//_temp--; //_problem.tempFunction(_temp);
					//_temp = _problem.tempFunction(_temp);
					System.out.println("Best Result at Temp = " + _temp + " is: " + bestResult);
					//return sample;
				}

		}


		System.err.println((System.nanoTime() - _initTime) / 1000000);
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
