import java.util.Random;

/**
 * Created by Jeff on 29/05/2015.
 */
public class SimulatedAnnealing
{
	private Random _randomiser;

	private int _temp;

	private int _maxTime;
	private int _maxTemp;
	private int _tempDelta;
	private int _numSamples;

	private Problem _problem;

	public SimulatedAnnealing(int maxTemp, int tempDelta, int numSamples, int maxTime, Problem problem)
	{
		_maxTemp = maxTemp;
		_maxTime = maxTime;
		_numSamples = numSamples;
		_tempDelta = tempDelta;
		_problem = problem;


	}

	public int run()
	{
		int sample = randomise();

		while(_temp > 0)
		{
			double bestResult = 0;

			for(int i = 0; i < _numSamples; i++)
			{
				int test = _problem.permute(sample);
				for(int j = 1; j < _temp; j++)
					test = _problem.permute(test);

				double eval = _problem.evaluate(test);
				if(eval > bestResult)
				{
					bestResult = eval;
					sample = test;
				}
			}

			_temp -= _tempDelta;
		}

		return sample;
	}

	private int randomise()
	{
		double bestResult = 0;
		int bestSample = 0;

		for(int i = 0; i < _numSamples; i++)
		{
			int sample = _problem.generate();
			double eval = _problem.evaluate(sample);
			if(eval > bestResult)
			{
				bestResult = eval;
				bestSample = sample;
			}
		}
		return (bestSample == 0 ? randomise() : bestSample);
	}

	private int evaluate()
	{

		return 0;
	}
}
