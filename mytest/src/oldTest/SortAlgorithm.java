package oldTest;

import java.util.ArrayList;
import java.util.List;

public class SortAlgorithm {
	
	public static void main(String[] args) {
		int array[] = {57, 6, 68, 3, 59, 5245, 72, 4, 28, 96, 483, 33};
		//int array[] = {9, 2, 5, 1, 3, 2, 6};
//		insertionSort(array);
//		selectionSort(array);
//		shellSort(array);
//		heapSort(array);
//		bubbleSort(array);
//		quickSort(array);
//		mergeSort(array);
		radixSort(array);
		printArray(array);
	}
	
	private static void printArray(int array[]) {
		for (int i = 0; i < array.length; i++) {
			System.out.print(array[i]+", ");
		}
	}
	
	@SuppressWarnings("unused")
	private static void insertionSort(int [] array) {
		int temp;
		for(int i = 1; i < array.length; i++) {
			for(int j = i; j > 0; j--) {
				if(array[j] <= array[j-1]) {
					temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
				}
			}
		}
		//return array;
	}

	@SuppressWarnings("unused")
	private static void selectionSort(int [] array) {
		int temp;
		for(int i = 0; i < array.length; i++) {
			for(int j = i+1; j < array.length; j++) {
				if (array[i] > array[j]) {
					temp = array[j];
					array[j] = array[i];
					array[i] = temp;
				}
			}
		}
		//return array;
	}
	
	@SuppressWarnings("unused")
	private static void shellSort(int [] array) {
		int temp;
		for (int divisor = array.length / 3; divisor >= 1; divisor /= 2) {
			for (int i = 0; i < array.length-divisor; i++) {
				for (int j = i + divisor; j < array.length; j += divisor) {
					if (array[i] > array[j]) {
						temp = array[j];
						array[j] = array[i];
						array[i] = temp;
					}
				}
			}
		}
		//return array;
	}
	
	@SuppressWarnings("unused")
	private static void bubbleSort(int [] array) {
		int temp;
		for (int i = array.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (array[j+1] < array[j]) {
					temp = array[j];
					array[j] = array[j + 1];
					array[j + 1] = temp;
				}
			}
		}
	}
	
	@SuppressWarnings("unused")
	private static void heapSort(int [] array) {
		int temp;
		heapAdjustment(array, array.length - 1);
		for (int i = array.length - 1; i > 0; i--) {
			temp = array[0];
			array[0] = array[i];
			array[i] = temp;
			heapAdjustment(array, i-1);
		}
		//return array;
	}
	
	@SuppressWarnings("unused")
	private static void heapAdjustment(int [] array, int index) {
		int temp;
		for (int i = index; i > 0; i--) {
			if (array[(i - 1) / 2] < array[i]) {
				temp = array[(i - 1) / 2];
				array[(i - 1) / 2] = array[i];
				array[i] = temp;
			}
		}
		//return array;
	}
	
	@SuppressWarnings("unused")
	private static void quickSort(int [] array) {
		partition(array, 0, array.length-1);
	}
	
	private static void partition(int [] array, int first, int last) {
		if (last > first) {
			int i = first;
			int j = last;
			int key = array[i];
			while (array[j] >= key && j > i)
				j--;
			array[i] = array[j];
			while (array[i] <= key && j > i)
				i++;
			array[j] = array[i];
			array[i] = key;
			if(first < j)
				partition(array, first, j - 1);
			if(last > i)
				partition(array, i + 1, last);
		}
	}
	
	@SuppressWarnings("unused")
	private static void mergeSort(int [] array) {
		mergeAdjustment(array, 0 ,array.length-1);
	}
	
	private static void mergeAdjustment(int [] array, int first, int last) {		
		if(first >= last)
			return;
		int middle = (first + last) >> 1;
		mergeAdjustment(array, first, middle);
		mergeAdjustment(array, middle + 1, last);
		mergeInsert(array, first, middle, last);
	}
	
	private static void mergeInsert(int [] array, int first, int middle, int last) {
		int i = first;
		int j = middle + 1;
		int k = 0;
		int [] temp = new int[last - first + 1];
		while (i <= middle && j <= last) {
			if(array[i] < array[j])
				temp[k++] = array[i++];
			else
				temp[k++] = array[j++];
		}
		while (i <= middle)
			temp[k++] = array[i++];
		while (j <= last)
			temp[k++] = array[j++];
		for(int n = 0; n < k; n++) {
			array[n + first] = temp[n];
		}
		
	}
	
	@SuppressWarnings("unused")
	private static void radixSort(int [] array) {
		int max = findMaxIndex(array, 0, array.length - 1);
		int digit = getDigit(array[max]);
		List<ArrayList<Integer>> queue = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 10; i++) {
			queue.add(new ArrayList<Integer>());
		}
		int last;
		for (int i = 0; i < digit; i++) {
			for (int j = 0; j < array.length; j++) {
				last = array[j] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
				queue.get(last).add(array[j]);
			}
			last = 0;
			while(last < array.length) {
				for (int k = 0; k < 10; k++) {
					for (Integer temp : queue.get(k)) {
						array[last++] = temp.intValue();
					}
					queue.get(k).clear();
				}
			}
		}
		
	}
	
	private static int getDigit(int number) {
		int count = 0;
		while (number > 0) {
			number = number / 10;
			count++;
		}
		return count;
	}
	
	@SuppressWarnings("unused")
	private static int findMaxIndex(int [] array, int begin, int end) {
		int max = array[begin];
		int index = begin;
		for (int i = begin; i <= end; i++) {
			if(max < array[i]) {
				max = array[i];
				index = i;
			}
		}
		return index;
	}
	
	@SuppressWarnings("unused")
	private static int findMinIndex(int [] array, int begin, int end) {
		int min = array[begin];
		int index = begin;
		for (int i = begin; i <= end; i++) {
			if(min > array[i]) {
				min = array[i];
				index = i;
			}
		}
		return index;
	}
}
