import java.util.*;

class Recipe {
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
		System.out.println("Enter the name of your recipe:");
		this.name = input_recipe.nextLine();
		System.out.println("Enter the description of your recipe:");
		this.description = input_recipe.nextLine();
		System.out.println("Enter the number of ingredients you would like to add");
		// number of ingredients bug updated
		int num_ingredients = Integer.parseInt(input_recipe.nextLine());
		int counter = 0;
		while (counter < num_ingredients) {
			System.out.println("Add ingredient number:" + (counter + 1));
			String ing_str = input_recipe.nextLine();
			this.ingredients.add(ing_str);
			counter++;
		}
		// same structure applied to number of instructions
		System.out.println("Enter the number of instructions you would like to add");
		int num_instructions = Integer.parseInt(input_recipe.nextLine());
		counter = 0;
		while (counter < num_instructions) {
			System.out.println("Add instruction number:" + (counter + 1));
			String instr_str = input_recipe.nextLine();
			this.instructions.add(instr_str);
			counter++;
		}
	}

	/*
	 * name, description, ingredients, instructions donut, "a donut making recipe",
	 * "dough, sugar, milk, egss"
	 */

}

public class main {
	public static void main(String[] args) {
		System.out.println("hello world");

		Recipe recipeObject = new Recipe();
		recipeObject.addRecipe();

		StringBuffer sb = new StringBuffer();

		StringBuffer sb2 = new StringBuffer();

		for (String s : recipeObject.ingredients) {
			sb.append(s);
			sb.append(" ");
		}
		// let instructions be printed as well
		for (String s : recipeObject.instructions) {
			sb2.append(s);
			sb2.append(" ");
		}

		String str = sb.toString();
		String str2 = sb2.toString();
		System.out
				.println("Recipe Name: " + recipeObject.name + "\n" + "Recipe Description: " + recipeObject.description
						+ "\n" + "Recipe Ingredients: " + str + "\n" + "Recipe Instructions: " + str2 + "\n");

	}
}
