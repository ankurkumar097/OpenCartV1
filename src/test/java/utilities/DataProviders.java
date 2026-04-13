package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//DataProvider 1
	@DataProvider (name="LoginData")
	public String [][] getData() throws IOException {
		
		String path = ".\\testData\\Opencart_LoginData.xlsx";					//taking excel file from testData folder		
		ExcelUtility xl = new ExcelUtility(path);								//creating an object for XLUtility
		
		int totalrows= xl.getRowCount("Sheet1");
		int totalcols= xl.getCellCount("Sheet1", 1);
		
		String loginData[][]= new String[totalrows][totalcols];					// created for 2d array to store data
		
		for(int i =1; i<=totalrows; i++) {										//1 // read the data from xl storing it in 2d array
			for(int j=0; j<totalcols; j++) {									//0 // i is rows j is col
				loginData[i-1][j]= xl.getCellData("Sheet1", i, j);				//1,0
			}			
		}	
		return loginData;														// returning two dimention array
	}
	
	//DataProvider 2
	
	//DataProvider 3
	
	//DataProvider 4
}
