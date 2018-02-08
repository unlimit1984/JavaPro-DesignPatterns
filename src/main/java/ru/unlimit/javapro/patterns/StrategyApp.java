package ru.unlimit.javapro.patterns;
import java.util.Arrays;
public class StrategyApp {
	public static void main(String[] args) {
		StrategyClient c = new StrategyClient();
		
		int[] arr0 = {1,3,2,1};
		c.setStrategy(new SelectionSort());
		c.executeStrategy(arr0);
		
		int[] arr1 = {11,4,2,7,8,54};
		c.setStrategy(new InsertingSort());
		c.executeStrategy(arr1);
		
		int[] arr2 = {3,-8,2,0,33,1,3,2};
		c.setStrategy(new BubbleSort());
		c.executeStrategy(arr2);
	}
}
//Context
class StrategyClient{
	Sorting strategy;
	public void setStrategy(Sorting strategy) {this.strategy = strategy;}
	public void executeStrategy(int[] arr){strategy.sort(arr);}
}
//Strategy
interface Sorting{
	void sort(int[] arr);
}
//Bubble sorting strategy (Сортировка пузырьком)
class BubbleSort implements Sorting{
	public void sort(int[] arr) {
		System.out.println("Сортировка пузырьком");
		System.out.println("до:\t"+Arrays.toString(arr));
		for(int barier=arr.length-1;barier>=0;barier--){
			for(int i=0;i<barier;i++){
				if(arr[i]>arr[i+1]){
					int tmp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = tmp;
				}
			}
		}
		System.out.println("после:\t"+Arrays.toString(arr));
	}
}
//Selection sorting strategy (Сортировка выборками)
class SelectionSort implements Sorting{
	public void sort(int[] arr) {
		System.out.println("Сортировка выборками");
		System.out.println("до:\t"+Arrays.toString(arr));
		for(int barier=0; barier<arr.length-1; barier++){
			for(int i=barier+1;i<arr.length;i++){
				if(arr[i]<arr[barier]){
					int tmp = arr[i];
					arr[i] = arr[barier];
					arr[barier] = tmp;

				}
			}
		}
		System.out.println("после:\t"+Arrays.toString(arr));
	}
}
//Inserting sorting strategy (Сортировка вставками)
class InsertingSort implements Sorting{
	public void sort(int[] arr) {
		System.out.println("Сортировка вставками");
		System.out.println("до:\t"+Arrays.toString(arr));
		for(int barier = 1; barier < arr.length; barier++){
			int index=barier;
			while(index-1>=0 && arr[index]<arr[index-1]){
				int tmp = arr[index];
				arr[index] = arr[index-1];
				arr[index-1] = tmp;
				index--;
			}
		}
		System.out.println("после:\t"+Arrays.toString(arr));
	}
}