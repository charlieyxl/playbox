package com.awoo.algorithm.knn;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * This class is used to build a K Nearest Neighbour model
 */
public class KNN
{
	private final String[] type = { "setosa", "versicolor", "virginica" };
	private int[][] count;
	private ArrayList<Data> sample, train, test;
	private NearPoint[][] points;
	private Data current;
	private int m, K, decision, allFit;
	private final int TRAIN = 45, TEST = 105;

	public KNN()
	{
		sample = new ArrayList<Data>();
		train = new ArrayList<Data>();
		test = new ArrayList<Data>();
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

			while ((line = br.readLine()) != null && !line.equals(""))
			{
				String[] parts = line.split(",");
				Double[] d = { Double.parseDouble(parts[0]),
						Double.parseDouble(parts[1]),
						Double.parseDouble(parts[2]),
						Double.parseDouble(parts[3]) };
				String[] name = parts[4].split("-");
				Data data = new Data(d, name[1]);
				sample.add(data);
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
		allocate();
		train();
		test();
	}

	public void allocate()
	{
		for (int i = 0; i < 450; i++)
		{
			int index = (int) (Math.random() * 150);
			Data temp = sample.remove(index);
			sample.add(temp);
		}
		for (int i = 0; i < 150; i++)
		{
			if (i < TRAIN)
				train.add(sample.get(i));
			else
				test.add(sample.get(i));
		}
	}

	public void train()
	{
		points = new NearPoint[TRAIN][TRAIN];
		m = 0;
		calDistance(train, TRAIN);
		sort(TRAIN);
		pickK();
		System.out.println(K);
	}

	public void test()
	{
		points = new NearPoint[TEST][TEST];
		m = 0;
		calDistance(test, TEST);
		sort(TEST);
		testK();
	}

	public void calDistance(ArrayList<Data> data, int n)
	{
		while (m < n)
		{
			current = data.get(m);
			for (int i = 0; i < n; i++)
			{
				Data temp = data.get(i);
				double distance = Math.pow(
						(current.getData(1) - temp.getData(1)), 2)
						+ Math.pow((current.getData(2) - temp.getData(2)), 2)
						+ Math.pow((current.getData(3) - temp.getData(3)), 2)
						+ Math.pow((current.getData(4) - temp.getData(4)), 2);
				points[m][i] = new NearPoint(temp.getType(), current.getType(),
						distance);
			}
			m++;
		}
	}

	public void sort(int n)
	{
		for (int i = 0; i < n; i++)
		{
			for (int j = 0; j < n; j++)
			{
				NearPoint temp = null;
				int swap = j;
				for (int k = j + 1; k < n; k++)
				{
					if (points[i][k].getDistance() < points[i][swap]
							.getDistance()) swap = k;
				}
				temp = points[i][swap];
				points[i][swap] = points[i][j];
				points[i][j] = temp;
			}
		}
	}

	public void pickK()
	{
		K = 0;
		count = new int[TRAIN][3];
		int biggest = 0;
		for (int i = 1; i < TRAIN; i++)
		{
			allFit = 0;
			for (int j = 0; j < TRAIN; j++)
			{
				String temp = points[j][i].getType();
				if (temp.equals(type[0]))
					count[j][0]++;
				else if (temp.equals(type[1]))
					count[j][1]++;
				else
					count[j][2]++;
				if (count[j][0] > count[j][1])
				{
					if (count[j][0] > count[j][2])
						decision = 0;
					else
						decision = 2;
				}
				else
				{
					if (count[j][2] > count[j][1])
						decision = 2;
					else
						decision = 1;
				}
				if (type[decision].equals(points[j][i].getOwnType())) allFit++;
			}
			if (allFit > biggest)
			{
				K = i;
				biggest = allFit;
			}
		}
	}

	public void testK()
	{
		count = new int[TEST][3];
		int fit = 0;
		for (int i = 0; i < TEST; i++)
		{
			for (int j = 1; j <= K; j++)
			{
				String thistype = points[i][j].getType();
				if (thistype.equals(type[0]))
					count[i][0]++;
				else if (thistype.equals(type[1]))
					count[i][1]++;
				else
					count[i][2]++;
			}
			if (count[i][0] > count[i][1])
			{
				if (count[i][0] > count[i][2])
					decision = 0;
				else
					decision = 2;
			}
			else
			{
				if (count[i][2] > count[i][1])
					decision = 2;
				else
					decision = 1;
			}
			if (type[decision].equals(points[i][0].getOwnType()))
			{
				fit++;
			}
		}
		System.out.println("The positive test: " + fit);
	}

	public static void main(String[] args)
	{
		KNN knn = new KNN();
		knn.readData();
		knn.start();
	}
}
