import java.util.Scanner;

/**
 * 
 */

/**
 * @author jagan
 *
 */
public class ClassOne {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double n1 = 9.9;
		System.out.println("Hello There!");
		System.out.printf("Your mark is : %.2f\n",n1);
		//Demo Scanner Object
		Scanner input =  new Scanner(System.in);
		System.out.println("Please enter any String\n");
		//Reading the input from console
		String textinput = input.nextLine();
		//Displaying the output
		System.out.printf("You have entered: %s\n",textinput);

	}

}
