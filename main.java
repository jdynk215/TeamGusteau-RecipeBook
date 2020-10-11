import java.util.*;
import java.io.*;

class Recipe implements Serializable {
	private static final long serialVersionUID = 1L;
	String name;
	String description;
	ArrayList<String> ingredients;
	ArrayList<String> instructions;

	public Recipe() {
		this.ingredients = new ArrayList<String>();
		this.instructions = new ArrayList<String>();
	}

	public Recipe(String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions) {
		this.name = name;
		this.description = description;
		this.ingredients = ingredients;
		this.instructions = instructions;
	}

	public void recipeRetrieval() {
	}

	public void addRecipe() {
		Scanner input_recipe = new Scanner(System.in);
		System.out.println("\nEnter the name of your recipe:");
		this.name = input_recipe.nextLine();
		//To do: whenever we create a recipe, we have to call the read function to check if the recipe name already exists in the file. 
		System.out.println("\nEnter the description of your recipe:");
		this.description = input_recipe.nextLine();
		System.out.println("\nEnter the number of ingredients you would like to add");

		int num_ingredients = Integer.parseInt(input_recipe.nextLine());
		int counter = 0;
		while (counter < num_ingredients) {
			System.out.println("Add ingredient number:" + (counter + 1));
			String ing_str = input_recipe.nextLine();
			this.ingredients.add(ing_str);
			counter++;
		}

		System.out.println("\nEnter the number of instructions you would like to add");
		int num_instructions = Integer.parseInt(input_recipe.nextLine());
		counter = 0;
		while (counter < num_instructions) {
			System.out.println("Add instruction number:" + (counter + 1));
			String instr_str = input_recipe.nextLine();
			this.instructions.add(instr_str);
			counter++;
		}
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		for (String s : this.ingredients) {
			sb.append(s + " ");
		}
		String str_ingr = sb.toString();
		sb = new StringBuffer();
		for (String x : this.instructions) {
			sb.append((this.instructions.indexOf(x) + 1) + "." + x);
			sb.append("\n");
		}
		String str_instr = sb.toString();

		return "Recipe Name: " + this.name + "\n" + "Recipe Description: " + this.description + "\n"
				+ "Recipe Ingredients: " + str_ingr + "\n" + "Recipe Instructions:\n" + str_instr;

	}

}

public class main {

	static ArrayList<Recipe> menu = new ArrayList<Recipe>();

	public static ArrayList<String> arrayToList(String [] arr){
		ArrayList<String> list = new ArrayList<String>();	
		for(String s: arr){
			list.add(s);
		}
		return list;
	}
	public static void format(String data){
		String[] rows = data.split("\n", 0);
		for(String row: rows){
			String[] cols = row.split(",", 0);
			//System.out.println(row);
			//String name = cols[0];
			Recipe newRecipe = new Recipe(cols[0], cols[1], arrayToList(cols[2].split("-", 0)), arrayToList(cols[3].split("-", 0)));
			menu.add(newRecipe);
		}
	}
	public static void main(String[] args) {
		System.out.println(
				"Welcome to the Team Gusteau Recipe Application! To begin, enter a command or type HELP to see a list of commands:");
		Scanner userInput = new Scanner(System.in);
		String user_command;

		Recipe my_rec;

		while (true) {
			user_command = userInput.nextLine();
			if (user_command.toLowerCase().equals("create")) {
				System.out.println(
						"\nLet's make a new recipe! You'll be prompted to enter several components of your recipe, please hit enter after each entry:");
				my_rec = new Recipe();
				my_rec.addRecipe();
				menu.add(my_rec);
				System.out.println("\n" + my_rec.toString());
				System.out.println("What would you like to do next?");
				try {
    				BufferedWriter writer = new BufferedWriter(new FileWriter("myRecipes.txt", true));
   	 				
   	 			
   	 				StringBuilder sb = new StringBuilder();
					for (String s : my_rec.ingredients) {
						sb.append(s);
						if(my_rec.ingredients.indexOf(s)+1==my_rec.ingredients.size()){
							break;
						}
						else{
							sb.append("-");
						}
					}
					
   	 	
   	 				StringBuilder new_sb = new StringBuilder();
					for (String x : my_rec.instructions) {
						new_sb.append(x);
						if(my_rec.instructions.indexOf(x)+1==my_rec.instructions.size()){
							break;
						}
						else{
							new_sb.append("-");
						}
						
					}
   	 				writer.append(my_rec.name+","+my_rec.description+","+sb.toString()+","+new_sb.toString()+"\n");
    				writer.close();

				} catch (FileNotFoundException e) {
					System.out.println("File not found");
				} catch (IOException e) {
					System.out.println("Error initializing stream");
				}
			}
			
			else if (user_command.toLowerCase().equals("find")) {
				System.out.println(
						"\nLooking for a recipe? Enter 1 to search for a recipe by name, or enter 2 to browse all existing recipes:");
				Scanner userInput1 = new Scanner(System.in);
				String user_command1 = userInput1.nextLine();

				boolean Found = false;
				if (user_command1.toLowerCase().equals("1")) {
					System.out.println("\nPlease enter the recipe name:");
					Scanner userInput2 = new Scanner(System.in);
					String user_command2 = userInput2.nextLine();
					for (Recipe r : menu) {
						if (r.name.toLowerCase().equals(user_command2.toLowerCase())) {
							System.out.println("\nRecipe found!\n");
							System.out.println(r.toString());

							//The Recipe Exploration feature goes here 
							Found = true;

							break;
						}

					}
					if (!Found) {
						System.out.println("\nSorry recipe wasn't found...\n");
					}

				} else if (user_command1.toLowerCase().equals("2")) {
					//System.out.println(menu.size());
					System.out.println("");
					for(int i = 0; i < menu.size(); i++){
						System.out.println(("name: " + menu.get(i).name));
						System.out.println(("description: " + menu.get(i).description) + "\n");
					}
					 //The Recipe Exploration feature goes here 
				}
				System.out.println("What would you like to do next?");
			}
			else if (user_command.toLowerCase().equals("read")) {
				try{
					File myFile = new File("myRecipes.txt");
					Scanner myReader = new Scanner(myFile);
					String data = "";
					while(myReader.hasNextLine())	{
						data += myReader.nextLine() + "\n";
					}
					System.out.println("Recipe data read into program");
					format(data);
				}
				catch (FileNotFoundException e){
				}
				System.out.println("What would you like to do next?");
			}
			else if (user_command.toLowerCase().equals("exit")) {
				System.out.println("\nGoodbye");
				break;
			}
		}

	}
/*
	public static String printIngIns(ArrayList<String> r) {
		StringBuilder sb = new StringBuilder();
		for (String s : r) {
			sb.append(s);
			sb.append("\n");
		}

		return sb.toString();

	}
	*/
}