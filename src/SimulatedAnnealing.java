import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class SimulatedAnnealing<T>
{
	private float _temp;

	private int _maxTime;
	private float _maxTemp;
	private int _numSamples;
	private int _initSamples;

	private Problem<T> _problem;

	public SimulatedAnnealing(float maxTemp, int numSamples, int initialSamples, int maxTime, Problem<T> problem)
	{
		_maxTemp = maxTemp;
		_maxTime = maxTime;
		_numSamples = numSamples;
		_problem = problem;
		_initSamples = initialSamples;


	}

	public T run()
	{
		_temp = _maxTemp;

		T sample = randomise();
		double bestResult = _problem.evaluate(sample);

		while(_temp > 0)
		{
			for(int i = 1; i < _numSamples; i++)
			{
				T test = _problem.permute(sample, (int)_temp);

				double eval = _problem.evaluate(test);
				if(eval > bestResult)
				{
					System.err.println(eval - bestResult);
					bestResult = eval;
					sample = test;
				}
			}

			System.out.println("Best Result at Temp = " + _temp + " is: " + bestResult);
			_temp = _problem.tempFunction(_temp);

		}

		return sample;
	}

	private T randomise()
	{
		double bestResult = 0;
		T bestSample = _problem.minimum();

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

		return (bestResult == 0 ? randomise() : bestSample);
	}

}
