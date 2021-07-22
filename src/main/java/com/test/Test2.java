package com.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test2 {

	/*
		[10, 11, 12, 25] = 4
		[11, 12, 25] = 3
		[12, 25] = 2
		[25, 45, 55] = 3
		[45, 55] = 2
	 */

	public static void main(String[] args) {
		List<Double> treeAngles = new ArrayList<Double>(Arrays.asList(10d, 11d, 12d, 25d, 45d, 55d));
		Double alphaAngle = 30d;

		int numberOfTrees = 1;
		Double sumOfAngles = 0d;
		for(int i = 1; i < treeAngles.size() - 1; i++) {
			if((sumOfAngles + (treeAngles.get(i) - treeAngles.get(i-1))) < alphaAngle) {
				sumOfAngles += treeAngles.get(i) - treeAngles.get(i-1);
				numberOfTrees++;
			} else {
				break;
			}
		}

		int aux = 0;
		for (int i = numberOfTrees; i < treeAngles.size() - numberOfTrees; i++) {
			if(numberOfTrees - treeAngles.get(aux) + treeAngles.get(i) > numberOfTrees) {

			}
		}

	}
}
