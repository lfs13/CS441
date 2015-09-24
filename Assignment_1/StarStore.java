//Louis Seefeld
//CS401
//--------------------------------------------------------------------------------------------------------------//
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.text.*;   //imported class to edit decimals for money

public class StarStore {
	public static void main(String[] args) throws FileNotFoundException {
		boolean looper=true;
		while(looper){
		//Customer Check//
		DecimalFormat df = new DecimalFormat("$##.00");
		System.out.println("Welcome to Buy Stuff (or Buy it Not)!");
		Scanner input = new Scanner(System.in);
		boolean line_check = false;
		String yn = "";

		while (line_check == false) {
			System.out.println("Is there a customer to be waited on? (yes/no)");

			yn = input.next();

			if (yn.equalsIgnoreCase("yes"))
				line_check = true;
			else if (yn.equalsIgnoreCase("no"))
				System.exit(0);
			else
				System.out.println(yn + " is not valid.");
		}
		// End of Customer Check//
		// ------------------------------------------------------------------------------------//
		// Ordering Process//
		System.out.println("Welcome new customer!");
		double total = 0;
		int yoda = 0, movie = 0, lego = 0;
		boolean complete = false;
		boolean discount = false;

		while (!complete) {
			int choice = 0;
			boolean is_int = false;
			System.out.println("What would you like to order?");
			System.out.println("----------------------------------------");
			System.out.println("1) Yoda Action Figure: $10.00 each");
			System.out.println("2) Star Wars Movie Blue Ray: $15.00 each");
			System.out.println("3) Death Star Lego Set: $350.00 each");
			System.out.println("----------------------------------------");
			System.out.println("Enter item number, or 0 to finish.");

			while (is_int==false) {
				if (input.hasNextInt()) {
					choice = input.nextInt();
					input.nextLine();
					if(choice==0||choice==1||choice==2||choice==3)
					    is_int = true;
					else
						System.out.print("Please enter a whole number corresponding to your item choice.");
				} else{
					System.out.print("Please enter a whole number corresponding to your item choice.");
				    input.nextLine();
				}
			}

			if (choice == 1) {
				System.out.println("Current order: " + yoda+ " yoda action figures.");
				System.out.println("How many Yoda Action Figures would you like?(negative number to leave unchanged)");
				int c1 = input.nextInt();
				input.nextLine();
				if (c1 >= 0)
					yoda = c1;
			} else if (choice == 2) {
				System.out.println("Current order: " + movie+ " Star Wars Movie Blue Rays.");
				System.out.println("How many Star Wars Movie Blue Rays would you like?(negative number to leave unchanged)");
				int c2 = input.nextInt();
				input.nextLine();
				if (c2 >= 0)
					movie = c2;
			} else if (choice == 3) {
				System.out.println("Current order: " + lego+ " Death Star Lego Sets.");
				System.out.println("How many Death Star Lego Sets would you like?(negative number to leave unchanged)");
				int c3 = input.nextInt();
				input.nextLine();
				if (c3 >= 0)
					lego = c3;
			} else if (choice == 0 &&(yoda==0 && movie==0 && lego==0)){
				System.out.println("\u001B31;1mYou must order at least 1 item to continue.");
			} else if (choice == 0)
				complete=true;
			  else
				System.out.println("Please enter a valid number.");
		}
		// End of Ordering Process//
		// ------------------------------------------------------------------------------------//
		// Trivia//
		System.out.println("Thank you for your order!");
		System.out.println("If you can correctly answer this random trivia question, a 10% discount will be place on your order total.");
		int rand = (int)(Math.random()*2);
		if (rand == 0) {
			System.out.println("Who is Luke Skywalker's father?");
			String a1 = input.nextLine();
			if (a1.equalsIgnoreCase("darth vader")) {
				System.out.println("Correct!");
				discount = true;
			} else {
				System.out.println("I'm sorry, but your answer is incorrect.");
				discount = false;
			}
		} else {
			System.out.println("What is Darth Vader's real first name?");
			String a2 = input.next();
			if (a2.equalsIgnoreCase("anakin")) {
				System.out.println("Correct!");
				discount = true;
			} else {
				System.out.println("I'm sorry, but your answer is incorrect.");
				discount = false;
			}
		}
		// End of Trivia//
		// ------------------------------------------------------------------------------------//
		// Shipping//
		System.out.println("Please choose your method of shipment:");
		System.out.println("1) Regular shipping [$5.00 per $50.00 of merchandise (rounded up)]");
		System.out.println("2) Express shipping [$10.00 per $50.00 of merchandise (rounded up)]");
		System.out.println("3) Super saver shipping [free]");
		int shipping=input.nextInt();
		//End Shipping//
		// ------------------------------------------------------------------------------------//
		//Total Math//
		double yt=yoda*10.00;
		double mt=movie*15.00;
		double lt=lego*350.00;
		double subtotal=yt+mt+lt;
		double dcost=0.0;
		double scost=0.0;
		
		System.out.println("Order Details:");
		if(yoda>0)
		System.out.println(yoda+ " Yoda Action Figure(s) x $10.00:	   "+df.format(yt));
		if(movie>0)
		System.out.println(movie+" Star Wars Movie Blue Ray(s) x $15.00:    "+df.format(mt));
		if(lego>0)
		System.out.println(lego+ " Death Star Lego Set(s) x $350.00:        "+df.format(lt));
		System.out.println(      "                                           ------------");
		System.out.println(      "Subtotal:                                  "+df.format(subtotal));
		if(discount){
		dcost=(subtotal/10.0);
		System.out.println(      "10% discount:                              "+"("+df.format(dcost)+")");
		}
		int d=(int)(subtotal/50.0);
		
		if(shipping==1){
			if(d==0)
				scost+=5.00;
			else
				scost+=(d*5.00);
			System.out.println(  "Regular Shipping:                          "+df.format(scost));
		}else if(shipping==2){
			if(d==0)
				scost+=10.00;
			else
				scost+=(d*10.00);
			System.out.println(  "Express Shipping:                          "+df.format(scost));
		}else{
			scost+=0.00;
			System.out.println(  "Super Saver Shipping:                      "+df.format(scost));
		}
		System.out.println(      "                                           ------------");
		
		total=subtotal-dcost+scost;
		System.out.println(      "Total:                                     "+df.format(total));
		//End of Total Math//
		// ------------------------------------------------------------------------------------//
		//Credit Card//
		boolean oflength =false;
		String control ="1111111111111111";
		
		while(oflength==false){
		System.out.println("Please enter your 16-digit credit card number.");
		String num = input.next();
		String check = ""+num;
		if(control.length()==check.length())
			oflength=true;
		}
		System.out.println("Thank you for your order!");
		System.out.println("//-------------------------------------------------------------//");
	   
	   }
	}		
}


