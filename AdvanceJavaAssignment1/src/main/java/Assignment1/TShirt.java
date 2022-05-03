package Assignment1;

public class TShirt {
	private String ID;
	private String name;
	private String color;
	private String GenderRecommendation;
	private String size;
	private Double price;
	private Double rating;
	private String Availability;
	
	public TShirt(String[] data) {
		ID = data[0];
		name = data[1];
		color = data[2];
		GenderRecommendation = data[3];
		size = data[4];
		price = Double.valueOf(data[5]);
		rating = Double.valueOf(data[6]);
		Availability = data[7];
		
	}
	
	public boolean checkAvailability(String inputColor, String inputSize, String inputGender) {
		if(Availability.toLowerCase().equals("n")) {
			return false;
		}
		return (color.toLowerCase().equals(inputColor.toLowerCase()) && size.toLowerCase().equals(inputSize.toLowerCase()) && GenderRecommendation.toLowerCase().equals(inputGender.toLowerCase()));
	}
	
	//Getters and Setters
	public String getID() {
		return ID;
	}
	
	public String getName() {
		return name;
	}
	
	public String getColor() {
		return color;
	}
	
	public String getGenderRecommendation() {
		return GenderRecommendation;
	}
	
	public String getSize() {
		return size;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public Double getRating() {
		return rating;
	}
	
	public String getAvailability() {
		return Availability;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "TShirt{" + " | " +
				"ID: " + ID + " | " +
				"Name: " + name + " | " +
				"Color: " + color + " | " +
				"Gender: " + GenderRecommendation + " | " +
				"Size: " + size + " | " + 
				"Price; " + price + " | " + 
				"Rating: " + rating + " | " + 
				"Availability: " + Availability +
				"}";
	}
}
