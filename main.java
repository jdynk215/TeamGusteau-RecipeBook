import java.util.*;

class Recipe {
    String name; String description; ArrayList<String> ingredients; ArrayList<String> instructions;
    
    public Recipe(){
        this.ingredients = new ArrayList<String>();
         this.instructions = new ArrayList<String>();
    }
    
    public Recipe (String name, String description, ArrayList<String> ingredients, ArrayList<String> instructions){
        this.name = name;
        this.description = description;
        this.ingredients = ingredients;
        this.instructions = instructions;
    }
    public void recipeRetrieval(){}
    public void addRecipe(){
        Scanner input_recipe= new Scanner(System.in);
        System.out.println("Enter the name of your recipe:");
        this.name=input_recipe.nextLine();
        System.out.println("Enter the description of your recipe:");
        this.description=input_recipe.nextLine();
        System.out.println("Enter the number of ingredients you would like to add");
        /*Problem to solve, input with Int causing bug with counter
        int num_ingredients=input_recipe.nextInt();*/
        int counter=0;
        while(counter < 3){
            System.out.println("Add ingredient number:" + (counter+1));
            String ing_str=input_recipe.nextLine();
            this.ingredients.add(ing_str);
            counter++;
        }
        counter = 0;
        /*
         System.out.println("Enter the number of instructions you would like to add");
         int num_instructions = input_recipe.nextInt();
         */
           while(counter < 3){
            System.out.println("Add instruction number:" + (counter+1));
            String instr_str=input_recipe.nextLine();
            this.instructions.add(instr_str);
            counter++;
        }
    }
    
    /*
    name, description, ingredients, instructions
    donut, "a donut making recipe", "dough, sugar, milk, egss"
    */

}


public class main {
    public static void main(String[] args) {
        System.out.println("hello world");
        
        Recipe burgers= new Recipe();
        burgers.addRecipe();
        
        StringBuffer sb = new StringBuffer();
      
      	for (String s : burgers.ingredients) {
      		sb.append(s);
      		sb.append(" ");
      	}
      	String str = sb.toString();
        System.out.println("Recipe Name: "+burgers.name+"\n"+"Recipe Description: "+ burgers.description +"\n"+ "Recipe Ingredients: "+ str +"\n");
        
    }
}
