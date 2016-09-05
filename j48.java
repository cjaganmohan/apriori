package coverageTester;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
public class j48 {

	public static void main(String[] args) throws IOException {

		int batchSize;
		boolean binarySplits = false;
		boolean collapseTree = false;
		float confidenceFactor;
		boolean debug = false;
		boolean doNotCheckCapabilities = false;
		int minNumObj;
		int numDecimalPlaces;
		int numFolds;
		boolean reducedErrorPruning = false;
		boolean saveInstanceData = false;
		int seed;
		boolean subTreeRaising = false;
		boolean unpruned = false;
		boolean useLaplace = false;
		boolean useMDLcorrection = false;
		boolean doNotMakeSplitPointActualValue = false;

		BufferedReader inputFileReader = null;
		//System.out.println("Starting the program");
		File file = new File("/Users/jagan/Desktop/output2.txt");

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
		//FileOutputStream fos = new FileOutputStream(file);
		//PrintStream ps = new PrintStream(fos);

		    try {
				inputFileReader = new BufferedReader(new FileReader("/Users/jagan/Desktop/j48-testset-positive/old-test-cases/csv/J48Positive-2way.csv"));
				//inputFileReader = new BufferedReader(new FileReader("/Users/jagan/Desktop/j48-testset-positive/J48Positive-2way-noconstraints.csv"));
				String line = "";
				String splitBy = ",";
				int tcase = 1;  // test case counter
				//read each line
				try {
					while((line = inputFileReader.readLine())!=null){
						String[] inputValues = line.split(splitBy);
						//System.out.println("Length of values = "+ inputValues.length+ "   "+inputValues[0]);

				//assigning values

						//batchSize
						batchSize = Integer.parseInt(inputValues[0]);

						//binarySplitSize
						if (inputValues[1].equalsIgnoreCase("true") || inputValues[1].equalsIgnoreCase("false")) {
							binarySplits = Boolean.valueOf(inputValues[1]);
						} else {
						  System.out.println("invalid input value");
						}

						//collapseTree
						if (inputValues[2].equalsIgnoreCase("true") || inputValues[2].equalsIgnoreCase("false")) {
							collapseTree = Boolean.valueOf(inputValues[2]);
						} else {
						  System.out.println("invalid input value");
						}

						//confidenceFactor
						confidenceFactor = Float.parseFloat(inputValues[3]);

						//debug
						if (inputValues[4].equalsIgnoreCase("true") || inputValues[4].equalsIgnoreCase("false")) {
							collapseTree = Boolean.valueOf(inputValues[4]);
						} else {
						  System.out.println("invalid input value");
						}

						// do not check capabilities
						if (inputValues[5].equalsIgnoreCase("true") || inputValues[5].equalsIgnoreCase("false")) {
							doNotCheckCapabilities = Boolean.valueOf(inputValues[5]);
							} else {
							  System.out.println("invalid input value");
							}

						//minNumObj
						minNumObj = Integer.parseInt(inputValues[6]);

						//numDecimalPlaces
						numDecimalPlaces = Integer.parseInt(inputValues[7]);

						//numFolds
						numFolds = Integer.parseInt(inputValues[8]);

						// reducedErrorPruning
						if (inputValues[9].equalsIgnoreCase("true") || inputValues[9].equalsIgnoreCase("false")) {
							reducedErrorPruning = Boolean.valueOf(inputValues[9]);
							} else {
							  System.out.println("invalid input value");
							}

						// saveInstanceData
						if (inputValues[10].equalsIgnoreCase("true") || inputValues[10].equalsIgnoreCase("false")) {
							saveInstanceData = Boolean.valueOf(inputValues[10]);
							} else {
							  System.out.println("invalid input value");
							}

						// seed
						seed = Integer.parseInt(inputValues[11]);

						//subTreeRaising
						if (inputValues[12].equalsIgnoreCase("true") || inputValues[12].equalsIgnoreCase("false")) {
							subTreeRaising = Boolean.valueOf(inputValues[12]);
							} else {
							  System.out.println("invalid input value");
							}

						// unPruned
						if (inputValues[13].equalsIgnoreCase("true") || inputValues[13].equalsIgnoreCase("false")) {
							unpruned = Boolean.valueOf(inputValues[13]);
							} else {
							  System.out.println("invalid input value");
							}

						// useLaplace
						if (inputValues[14].equalsIgnoreCase("true") || inputValues[14].equalsIgnoreCase("false")) {
							useLaplace = Boolean.valueOf(inputValues[14]);
							} else {
							  System.out.println("invalid input value");
							}

						// useMDLcorrection
						if (inputValues[15].equalsIgnoreCase("true") || inputValues[15].equalsIgnoreCase("false")) {
							useMDLcorrection = Boolean.valueOf(inputValues[15]);
							} else {
							  System.out.println("invalid input value");
							}

						// doNotMakeSplitPointActualValue
						if (inputValues[16].equalsIgnoreCase("true") || inputValues[16].equalsIgnoreCase("false")) {
							doNotMakeSplitPointActualValue = Boolean.valueOf(inputValues[16]);
							} else {
							  System.out.println("invalid input value");
							}
						outputFile.write("Test case number :"+tcase+"\n");
						System.out.println("Now Running ..... Test Case # :  " + tcase);
						generateJ48Classifier(batchSize,binarySplits,collapseTree,confidenceFactor,debug,doNotCheckCapabilities,minNumObj,numDecimalPlaces,
							numFolds,reducedErrorPruning,saveInstanceData,seed,subTreeRaising,unpruned,useLaplace,useMDLcorrection,doNotMakeSplitPointActualValue);

						// increase counter...
						outputFile.write("Test case number :"+tcase+"\n");
						//System.out.println("Now Running ..... Test Case # :  " + tcase);
						tcase++; //



					} // end of while
					generateJ48Classifier(100,false,true,(float) 0.25,false,false,2,2,
							3,false,false,1,true,false,false,false,false);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					System.out.println("Reached end of file!");
				}

			} catch (FileNotFoundException e) {
				System.out.println("Unable to read the input file");
			}
		/*
		generateJ48Classifier(100,false,true,(float) 0.25,false,false,2,2,
				3,false,false,1,true,false,false,false,false);
		*/
		}

	public static void generateJ48Classifier(int batchSize,
			boolean binarySplits, boolean collapseTree, float confidenceFactor,
			boolean debug, boolean doNotCheckCapabilities, int minNumObj,
			int numDecimalPlaces, int numFolds, boolean reducedErrorPruning,
			boolean saveInstanceData, int seed, boolean subTreeRaising,
			boolean unpruned, boolean useLaplace, boolean useMDLcorrection,
			boolean doNotMakeSplitPointActualValue) {


			Instances data;
			try{
			//data = DataSource.read("/Users/jagan/Desktop/weka/weka-3-6-13/data/weather.numeric.arff");
			data = DataSource.read("/Users/jagan/Downloads/uci_datasets/credit-g.arff");
			data.setClassIndex(data.numAttributes()-1);
			J48 tree = new J48();

			tree.setBatchSize(String.valueOf(batchSize));
			tree.setBinarySplits(binarySplits);
			tree.setCollapseTree(collapseTree);
			tree.setConfidenceFactor(confidenceFactor);
			tree.setDebug(debug);
			tree.setDoNotCheckCapabilities(doNotCheckCapabilities);
			tree.setMinNumObj(minNumObj);
			tree.setNumDecimalPlaces(numDecimalPlaces);
			tree.setNumFolds(numFolds);
			tree.setReducedErrorPruning(reducedErrorPruning);
			tree.setSaveInstanceData(saveInstanceData);
			tree.setSeed(seed);
			tree.setSubtreeRaising(subTreeRaising);
			tree.setUnpruned(unpruned);
			tree.setUseLaplace(useLaplace);
			tree.setUseMDLcorrection(useMDLcorrection);
			tree.setDoNotMakeSplitPointActualValue(doNotMakeSplitPointActualValue);


			tree.buildClassifier(data);
			System.out.println(tree);
			}
			catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("===========");
				System.out.println("Unable to read the dataset or invalid output");
				System.out.println("===========");
			}


	}

}
