package com.framework.design;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class CSVReader extends APIBase {

	public String readCSV(String country) throws FileNotFoundException, IOException {

		com.opencsv.CSVReader csvReader = null;

		Properties prop = new Properties();
		prop.load(new FileInputStream(".\\src\\test\\resources\\config.properties"));

		// Creating an instance for the CSVReader and passing the filename along with
		// path as FileReader parameter
		csvReader = new com.opencsv.CSVReader(
				new FileReader(".\\src\\test\\resources\\" + prop.getProperty("CountryCodeFileName")));

		String[] nextRecord;

		try {
			// Reading each and every record of the csv file
			while ((nextRecord = csvReader.readNext()) != null) {

				// checking whether the country name matches with the featurefile input
				if (nextRecord[0].equalsIgnoreCase(country)) {
					// returns the matching country code
					return nextRecord[1];
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
