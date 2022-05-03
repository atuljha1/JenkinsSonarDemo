package Assignment1;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvException;

public class CSVData {
	
	String filePath;
	public CSVData(String filePath) {
		this.filePath = filePath;
	}
	
	public List<TShirt> getCsvData(){
		List<TShirt> dataList = new ArrayList<TShirt>();
		
		//Creating File filter
		FileFilter filter = (File pathname) -> {
				return pathname.isFile() && pathname.getName().endsWith(".csv");
			};
		
		//creating array of files
		
		File[] files = new File(filePath).listFiles(filter);
		if(files != null) {
			for(File f : files) {
				try{
					//creating a custom csvParser
					CSVParser csvParser = new CSVParserBuilder().withSeparator('|').build();
					
					//creating a csvFilereader with custom parser
					CSVReader csvReader = new CSVReaderBuilder(new FileReader(f)).withCSVParser(csvParser).withSkipLines(1).build();
					
					for(String[] data : csvReader.readAll()) {
						dataList.add(new TShirt(data));
					}
				}catch(FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (CsvException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		}
		
		return dataList;
	}
	
	
}
