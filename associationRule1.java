package coverageTester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

import weka.associations.Apriori;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class associationRule1 {

	public static void main(String[] args) throws IOException {

		boolean CAR = false;
		int classIndex;
		double delta;
		boolean dntCheckCap = false;
		double lowerBoundMinSupport;
		int metricType = 0;
		double minMetric;
		int numberOfRules;
		boolean outputItemSets = false ;
		boolean removeAllMissingCol = false;
		double significanceLevel;
		boolean treatZeroAsMissing = false;
		double upperBoundMinSupport;
		boolean verbose = false;

		BufferedReader inputFileReader = null;
		/*
		File file = new File("/Users/jagan/Desktop/output.txt");

		if(!file.exists())
		{
			try {
				file.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Unable to create new file");
			}
		}
		FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
		BufferedWriter outputFile = new BufferedWriter(fileWriter);
		FileOutputStream fos = new FileOutputStream(file);
		PrintStream ps = new PrintStream(fos);
		*/
		    try {
				inputFileReader = new BufferedReader(new FileReader("/Users/jagan/Desktop/association-values-csv.csv"));
				String line = "";
				String splitBy = ",";
				//read each line
				try {
					while((line = inputFileReader.readLine())!=null){
						String[] inputValues = line.split(splitBy);
						//System.out.println("Length of values = "+ inputValues.length+ "   "+inputValues[0]);

				//assigning values
						//CAR
						if (inputValues[0].equalsIgnoreCase("true") || inputValues[0].equalsIgnoreCase("false")) {
						  CAR = Boolean.valueOf(inputValues[0]);
						} else {
						  System.out.println("invalid input value");
						}

						//class index value
						classIndex = Integer.parseInt(inputValues[1]);

						//delta value
						delta = Double.parseDouble(inputValues[2]);

						// do not check capabilities
						if (inputValues[3].equalsIgnoreCase("true") || inputValues[3].equalsIgnoreCase("false")) {
							dntCheckCap = Boolean.valueOf(inputValues[3]);
							} else {
							  System.out.println("invalid input value");
							}

						//lower bound minimum support
						lowerBoundMinSupport = Double.parseDouble(inputValues[4]);

						// metricType - to be filled in later
						if (inputValues[5].equalsIgnoreCase("CONFIDENCE")) {
							metricType = 0;
							}
						if (inputValues[5].equalsIgnoreCase("LIFT")) {
							metricType = 1;
							}
						if (inputValues[5].equalsIgnoreCase("LEVERAGE")) {
							metricType = 2;
							}
						if (inputValues[5].equalsIgnoreCase("CONVICTION")) {
							metricType = 3;
							}


                        //minMetric minimum value
						minMetric = Double.parseDouble(inputValues[6]);

						//number of rules
						numberOfRules = Integer.parseInt(inputValues[7]);

						//outputItemSets
						if (inputValues[8].equalsIgnoreCase("true") || inputValues[8].equalsIgnoreCase("false")) {
							outputItemSets = Boolean.valueOf(inputValues[8]);
							} else {
							  System.out.println("invalid input value");
							}

						//removeAllMissingCol
						if (inputValues[9].equalsIgnoreCase("true") || inputValues[9].equalsIgnoreCase("false")) {
							removeAllMissingCol = Boolean.valueOf(inputValues[9]);
							} else {
							  System.out.println("invalid input value");
							}

						//significanceLevel
						significanceLevel = Double.parseDouble(inputValues[10]);

						//treatZeroAsMissing
						if (inputValues[11].equalsIgnoreCase("true") || inputValues[11].equalsIgnoreCase("false")) {
							treatZeroAsMissing = Boolean.valueOf(inputValues[11]);
							} else {
							  System.out.println("invalid input value");
							}

						//upperBoundMinSupport
						upperBoundMinSupport = Double.parseDouble(inputValues[12]);

						//verbose
						if (inputValues[13].equalsIgnoreCase("true") || inputValues[13].equalsIgnoreCase("false")) {
							verbose = Boolean.valueOf(inputValues[13]);
							} else {
							  System.out.println("invalid input value");
							}

						/*
						System.out.println(CAR+"  "+classIndex+" "+ delta+ " "+dntCheckCap+" "+lowerBoundMinSupport+" "+minMetric+" "+
								numberOfRules+"  "+outputItemSets+" "+removeAllMissingCol+" "+significanceLevel+"  "+treatZeroAsMissing+" "+upperBoundMinSupport+"  "+verbose);

								*/
						generateAssociationRules(CAR,classIndex,delta,dntCheckCap,lowerBoundMinSupport,metricType,minMetric,
								numberOfRules,outputItemSets,removeAllMissingCol,significanceLevel,treatZeroAsMissing,upperBoundMinSupport,verbose);

					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Reached end of file!");
				}

			} catch (FileNotFoundException e) {
				System.out.println("Unable to read the input file");
			}

	}

	private static void generateAssociationRules(boolean cAR, int classIndex,
			double delta, boolean dntCheckCap, double lowerBoundMinSupport,
			int metricType, double minMetric, int numberOfRules, boolean outputItemSets,
			boolean removeAllMissingCol, double significanceLevel,
			boolean treatZeroAsMissing, double upperBoundMinSupport,
			boolean verbose) {


		//int counter  = 1;
		Instances data;
		try {
			data = DataSource.read("/Users/jagan/Desktop/weather.nominal.arff");
			Apriori apriori = new Apriori();

			apriori.setCar(cAR);  //CAR - boolean
			apriori.setClassIndex(classIndex); // class index - int
			apriori.setDelta(delta); // delta - double
			apriori.setDoNotCheckCapabilities(dntCheckCap);// do not check capabilities - boolean
			apriori.setLowerBoundMinSupport(lowerBoundMinSupport); // lower bound min support - double
		//	apriori.setMetricType((new SelectedTag(Integer.parseInt("0")));
			apriori.setMetricType(new weka.core.SelectedTag(metricType,apriori.TAGS_SELECTION));
			apriori.setMinMetric(minMetric); // minMetric - double
			apriori.setNumRules(numberOfRules); //number of rules - int
			apriori.setOutputItemSets(outputItemSets); //output itemsets - boolean
			apriori.setRemoveAllMissingCols(removeAllMissingCol);//remove all missing columns - boolean
			apriori.setSignificanceLevel(significanceLevel); // significance level - double
			apriori.setTreatZeroAsMissing(treatZeroAsMissing);// treat zero as missing - boolean
			apriori.setUpperBoundMinSupport(upperBoundMinSupport); // upper bound minimum support - double
			apriori.setVerbose(verbose); //verbose - false
			System.out.println("*******"+ apriori.getMetricType());
			apriori.buildAssociations(data);
			//System.setOut(ps);
			//display output
			//System.out.println(apriori);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("===========");
			System.out.println("Unable to read the dataset or invalid output");
			System.out.println("===========");
		}
		//return null;




	}


}
