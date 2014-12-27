package com.awoo.algorithm.knn;

/**
 * This class is used for storing data for KNN
 */
public class Data
{
	private Double[] data = new Double[4];
	private String type;

	public Data(Double[] d, String s)
	{
		data = d;
		type = s;
	}

	public Double getData(int i)
	{
		if (i == 0)
			return 1.0;
		else
			return data[i - 1];
	}

	public String getType()
	{
		return type;
	}
}
