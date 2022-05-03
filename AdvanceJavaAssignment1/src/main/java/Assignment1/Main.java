package Assignment1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

	static Scanner sc = new Scanner(System.in);
	
	public static List<TShirt> outputList = new ArrayList<TShirt>();
	
	public static List<String> inputList = new ArrayList<String>(4);
	
	public static String filePath = "C:\\Users\\atuljha\\Desktop\\AdvanceJava\\AdvanceJavaAssignment1";

	static boolean canRunThread = true;
	
	
	//creating thread object and overriding run method
	public static Runnable StoreDataToOutputListThread = () -> {
				
				while(canRunThread) {
					try {
						storeDataToOutputList();
						
						Thread.sleep(5000);
					}
					catch(Exception e){
						e.printStackTrace();
						canRunThread = false;
					}
				}
			};
	
	
	public static void main(String[] args) {
		
		boolean canSearch = true;
		
		Thread th = new Thread(StoreDataToOutputListThread);
		th.start();
		
		while(canSearch) {
			
			takeInput();
			
			List<TShirt> resultList = outputList.stream()
												.filter(element -> element.checkAvailability(inputList.get(0), inputList.get(1), inputList.get(2)))
												.collect(Collectors.toList());
			
			if(resultList.size() >0) {
				Comparator<TShirt> com1 = Comparator.comparing(TShirt::getPrice);
				Comparator<TShirt> com2 = Comparator.comparing(TShirt::getRating);
				Comparator<TShirt> com3 = Comparator.comparing(TShirt::getPrice).thenComparing(TShirt::getRating);
				
				String sortBy = inputList.get(3).toLowerCase();
				
				
				switch(sortBy) {
					case "price":
						resultList.sort(com1);
						break;
					case "rating":
						resultList.sort(com2);
						break;
					default:
						resultList.sort(com3);
				}
				
				for(TShirt item : resultList) {
					System.out.println(item.toString());
				}
						
						
						
			}else {
				System.out.println("No T-Shirt is Avaialable!");
			}
			
			System.out.println("\nDo you want to search again? (Y or N)");
			String goAgain = sc.nextLine().toLowerCase();
			
			if(goAgain.equals("n")) {
				stopThread();
				canSearch = false;
				System.out.println("Thank You for Searching.");
			}
		}
		 
													

	}
	
	
	//Method to stop the thread
	public static void stopThread() {
		canRunThread = false;
	}
	
	
	//Method for taking user Input
	public static void takeInput() {
		
		//Clearing the inputList first
		inputList.clear();
		
		//Taking Color Input
		System.out.println("Color: ");
		inputList.add(sc.nextLine());
		
		//Taking Size Input
		System.out.println("Size: S | M | L | XL | XXL");
		inputList.add(sc.nextLine());
		
		//Taking Gender Input
		System.out.println("Gender: M -> Male | F -> Female | U -> Unisex");
		inputList.add(sc.nextLine());
		
		//Taking Output Preference
		System.out.println("Output Preference: \n -> Price \n -> Rating \n -> Both Price and Rating (By Default)");
		inputList.add(sc.nextLine());
		
	}
	
	//Method for storing the CSV file data to outputList
	public static void storeDataToOutputList() {
		CSVData csvData = new CSVData(filePath);
		outputList = csvData.getCsvData();
	}

}
