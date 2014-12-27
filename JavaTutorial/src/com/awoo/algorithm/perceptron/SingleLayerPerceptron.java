package com.awoo.algorithm.perceptron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * This class is used to build a single layer Perceptron model
 */
public class SingleLayerPerceptron
{
	private ArrayList<Data> train, test;
	private double[] w;
	private double n, R, error;
	private int D, Y, stopCri;
	private Data current;
	private String type;
	private final int TRAIN = 30, TEST = 120;
	private final String[] types = { "setosa", "versicolor", "virginica" };

	public SingleLayerPerceptron(int n, int s)
	{
		train = new ArrayList<Data>();
		test = new ArrayList<Data>();
		w = new double[5];
		type = types[n];
		stopCri = s;
	}

	public void readData()
	{
		URL u;
		URLConnection uc;
		BufferedReader br;
		String line;
		try
		{
			// data is also available in resoures folder
			u = new URL(
					"http://www.cs.nott.ac.uk/~qiu/Teaching/G53MLE/Data/bezdekIris.data");
			uc = u.openConnection();
			br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			int count = 0;

			while ((line = br.readLine()) != null && !line.equals(""))
			{
				String[] parts = line.split(",");
				Double[] d = { Double.parseDouble(parts[0]),
						Double.parseDouble(parts[1]),
						Double.parseDouble(parts[2]),
						Double.parseDouble(parts[3]) };
				String[] name = parts[4].split("-");
				Data data = new Data(d, name[1]);
				if (count % 50 < 50 * TRAIN / 150)
				{
					train.add(data);
				}
				else
				{
					test.add(data);
				}
				count++;
			}
		}
		catch (MalformedURLException e1)
		{
			e1.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public void start()
	{
		random();
		w[0] = 0.4;
		w[1] = 0.7;
		w[2] = -0.3;
		w[3] = -0.5;
		w[4] = 0.2;
		n = 0.0001;

		train();
	}

	public void random()
	{
		for (int i = 0; i < TRAIN * 3; i++)
		{
			int index = (int) (Math.random() * TRAIN);
			Data temp = train.remove(index);
			train.add(temp);
		}
	}

	public void train()
	{
		int sign = 0;
		int x = 0;
		while (x < stopCri)
		{
			random();
			error = 0;
			for (int i = 0; i < TRAIN; i++)
			{
				repeat(i);
			}
			test();
			if (error == 0)
			{
				sign = 1;
			}
			System.out.println("After: " + (x + 1) + " epochs,  /  error: "
					+ error);
			if (x == stopCri - 1 && sign == 0)
				System.out.println("The type " + type
						+ " is not linear seperatable.");
			if (x == stopCri - 1 && sign == 1)
				System.out.println("The type " + type
						+ " is linear seperatable.");
			x++;
			if (x == stopCri) break;
		}
	}

	public void test()
	{
		int right = 0;
		for (int i = 0; i < TEST; i++)
		{
			current = test.get(i);
			D = calculate(current);
			R = w[0] + w[1] * current.getData(1) + w[2] * current.getData(2)
					+ w[3] * current.getData(3) + w[4] * current.getData(4);
			if (R * D > 0) right++;
		}
		System.out.println("Right: " + right + ", out of samples " + TEST);
	}

	public void repeat(int i)
	{
		current = train.get(i);
		D = calculate(current);
		R = w[0] + w[1] * current.getData(1) + w[2] * current.getData(2) + w[3]
				* current.getData(3) + w[4] * current.getData(4);
		if (R > 0)
			Y = 1;
		else
			Y = -1;

		error += 0.5 * Math.pow((D - Y), 2);
		iterate(current, 0);
		iterate(current, 1);
		iterate(current, 2);
		iterate(current, 3);
	}

	public int calculate(Data d)
	{
		if (d.getType().equals(type))
			return 1;
		else
			return -1;
	}

	public void iterate(Data d, int k)
	{
		w[k] = w[k] + n * (D - Y) * d.getData(k);
	}

	public static void main(String[] args)
	{
		SingleLayerPerceptron slp = new SingleLayerPerceptron(0, 40);
		slp.readData();
		slp.start();
	}
}
