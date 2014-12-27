package com.awoo.algorithm.knn;

/**
 * This class is used to store data for KNN model
 */
public class NearPoint
{
	private String type, ownType;
	private double distance;

	public NearPoint(String s, String t, double d)
	{
		type = s;
		ownType = t;
		distance = d;
	}

	public String getType()
	{
		return type;
	}

	public String getOwnType()
	{
		return ownType;
	}

	public double getDistance()
	{
		return distance;
	}
}
