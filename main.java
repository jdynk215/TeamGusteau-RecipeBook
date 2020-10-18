import java.util.*;
import java.io.*;
import java.nio.file.*;

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

    public static void removeLine(String lineContent)
    {
        try{
            File file = new File("myRecipes.txt");
            File temp = new File("_temp_.txt");
            PrintWriter out = new PrintWriter(new FileWriter(temp));
            Files.lines(file.toPath())
                .filter(line -> !line.contains(lineContent))
                .forEach(out::println);
            out.flush();
            out.close();

            FileReader fr=new FileReader("_temp_.txt");
            FileWriter fw=new FileWriter("myRecipes.txt");
            int c=fr.read();
            while(c!=-1)
            {
                fw.write(c);
                c = fr.read();
            }
            fr.close();
            fw.close();
            temp.delete();
        }
        catch (IOException e) {
                    System.out.println("Error initializing stream");
        }
        
    }

    public void addRecipe() {
        Scanner input_recipe = new Scanner(System.in);
        System.out.println("\nEnter the name of your recipe:");
        this.name = input_recipe.nextLine();
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
            sb.append(s);
            sb.append("\n");
        }
        String str_ingr = sb.toString();
        sb = new StringBuffer();

        // use counter instead of indexOf() which might cause the bug
        int counter = 1;
        for (String x : this.instructions) {
            sb.append((counter) + "." + x);
            sb.append("\n");
            counter++;
        }
        String str_instr = sb.toString();

        return "Recipe Name: " + this.name + "\n" + "Recipe Description: " + this.description + "\n"
                + "\nRecipe Ingredients:\n" + str_ingr + "\n" + "Recipe Instructions:\n" + str_instr;

    }

}

public class main {
    public static void main(String[] args) throws FileNotFoundException {
try {
        Recipe my_rec;
        ArrayList<Recipe> menu = new ArrayList<Recipe>();
        BufferedReader br = new BufferedReader(new FileReader("myRecipes.txt"));
        readRecipeFile(menu, br);

        System.out.println(
                "Welcome to the Team Gusteau Recipe Application! To begin, enter a command or type HELP to see a list of commands:");
        Scanner userInput = new Scanner(System.in);
        String user_command;

        while (true) {
            user_command = userInput.nextLine();
            if (user_command.toLowerCase().equals("create")) {
                System.out.println(
                        "\nLet's make a new recipe! You'll be prompted to enter several components of your recipe, please hit enter after each entry:");
                my_rec = new Recipe();
                my_rec.addRecipe();
                menu.add(my_rec);
                System.out.println("\n" + my_rec.toString());
                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("myRecipes.txt", true));
                    StringBuilder sb = new StringBuilder();
                    for (String s : my_rec.ingredients) {
                        sb.append(s);
                        if (my_rec.ingredients.indexOf(s) + 1 == my_rec.ingredients.size()) {
                            break;
                        } else {
                            sb.append("#");
                        }
                    }
                    StringBuilder new_sb = new StringBuilder();
                    for (String x : my_rec.instructions) {
                        new_sb.append(x);
                        if (my_rec.instructions.indexOf(x) + 1 == my_rec.instructions.size()) {
                            break;
                        } else {
                            new_sb.append("#");
                        }
                    }
                    writer.append(my_rec.name + "_" + my_rec.description + "_" + sb.toString() + "_" + new_sb.toString()
                            + "\n");
                    writer.close();
                } catch (FileNotFoundException e) {
                    System.out.println("File not found");
                } catch (IOException e) {
                    System.out.println("Error initializing stream");
                }
                System.out.println("What would you like to do next?");
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
                            System.out.println(
                                    "\nRecipe found!\nType 1 to see the whole recipe, or type 2 to view the recipe step by step by pressing the Enter key:");

                            String user_command3 = userInput2.nextLine();
                            if (user_command3.toLowerCase().equals("1")) {
                                System.out.println("\n" +r.toString());
                            } else if (user_command3.toLowerCase().equals("2")) {
                                ArrayList<String> steps = new ArrayList<String>();
                                steps = r.instructions;
                                System.out.println("\n"+steps.remove(0));
                                while (steps.size() > 0) {
                                    if (userInput2.nextLine().toLowerCase().equals("")) {
                                        System.out.println(steps.remove(0));
                                    }
                                }
                                System.out.println("\nEnd of recipe\n");
                            }
                            Found = true;

                            break;
                        }
                    }
                    if (!Found) {
                        System.out.println("\nSorry recipe wasn't found...\n");
                    }

                } else if (user_command1.toLowerCase().equals("2")) {
                    System.out.println();
                    printMenu(menu);

                    System.out.println("Please enter the recipe name:");
                    Scanner userInput2 = new Scanner(System.in);
                    String user_command2 = userInput2.nextLine();
                    for (Recipe r : menu) {
                        if (r.name.toLowerCase().equals(user_command2.toLowerCase())) {
                            System.out.println(
                                    "\nRecipe found!\nType 1 to see the whole recipe, or type 2 to view the recipe step by step by pressing the Enter key:");

                            String user_command3 = userInput2.nextLine();
                            if (user_command3.toLowerCase().equals("1")) {
                                System.out.println("\n"+r.toString());
                            } else if (user_command3.toLowerCase().equals("2")) {
                                ArrayList<String> steps = new ArrayList<String>();
                                steps = r.instructions;
                                System.out.println("\n" + steps.remove(0));
                                while (steps.size() > 0) {
                                    if (userInput2.nextLine().toLowerCase().equals("")) {
                                        System.out.println(steps.remove(0));
                                    }
                                }
                                System.out.println("\nEnd of recipe\n");
                            }
                            Found = true;

                            break;
                        }
                    }
                    if (!Found) {
                        System.out.println("\nSorry recipe wasn't found...\n");
                    }

                }

                System.out.println("What would you like to do next?");

            }

//          else if (user_command.toLowerCase().equals("read")) {
//              try {
//                  FileInputStream fi = new FileInputStream("myRecipes.txt");
//                  ObjectInputStream oi = new ObjectInputStream(fi);
//                  boolean keepReading = true;
//                  try {
//                      while (keepReading) {
//                          Recipe read_rec = (Recipe) oi.readObject();
//                          System.out.println(read_rec.toString());
//                          menu.add(read_rec);
//                          System.out.println("What would you like to do next?");
//                      }
//                  } catch (EOFException e) {
//                      keepReading = false;
//                  }
//                  oi.close();
//                  fi.close();
//              } catch (FileNotFoundException e) {
//                  System.out.println("File not found");
//              } catch (IOException e) {
//                  System.out.println("Error initializing stream");
//              } catch (ClassNotFoundException e) { // TODO Auto-generated catch block
//                  e.printStackTrace();
//              }
//          }

            else if (user_command.toLowerCase().equals("delete")) {
                boolean Found = false;
                System.out.println("\nPlease type the name of the recipe you want to delete.");
                Scanner userInput2 = new Scanner(System.in);
                String user_command2 = userInput2.nextLine();
                for (Recipe r : menu) {
                    if (r.name.toLowerCase().equals(user_command2.toLowerCase())) {
                        
                        Recipe.removeLine(user_command2);
                        menu.remove(r);
                        System.out.println("\nRecipe found and deleted!\n");
                        Found = true;
                        break;
                    }
                }
                if (!Found) {
                    System.out.println("\nSorry recipe wasn't found...\n");
                }
                System.out.println("What would you like to do next?");

            } else if (user_command.toLowerCase().equals("help")) {
                System.out.println("\nHere is a list of our commands:\n");
                System.out.println("create");
                System.out.println(
                        "\tAdd a recipe to your recipe book by inputting a title, description, incredients, and instructions");
                System.out.println("find");
                System.out.println(
                        "\tExplore the recipe book either through searching (1) or through a display of the entire recipe book (2)"
                                + "\n\tAfter a recipe is selected, you can either read the entire recipe (1) or step through the instructions one at a time (2)");
                System.out.println("delete");
                System.out.println("\tRemove a selected recipe from your recipe book. Note that input is case and punctuation sensitive");
                System.out.println("exit");
                System.out.println("\tExit Team Gusteau Recipe Application");


                System.out.println("\nWhat would you like to do next?");

            } else if (user_command.toLowerCase().equals("exit")) {
                System.out.println("\nGoodbye Chef!");
                break;
            } else {
                System.out.println(
                        "\nCommand not found. If you have any issues, try typing HELP for a list of commands!");
                System.out.println("\nWhat would you like to do next?");
            }
        }} catch (FileNotFoundException e) {
            System.out.println("\nLocal myRecipes.txt file wasn't found!\n"
                    + "\nPlease download one from https://github.com/jdynk215/TeamGusteau-RecipeBook\n"
                    + "\n,then place it under the same folder of this program before starting.\n"
                    + "\nLooking forward to seeing you soon.");
        }
    }

    public static void readRecipeFile(ArrayList<Recipe> menu, BufferedReader br) {
        try {
            String st;
            while ((st = br.readLine()) != null) {
                String[] splitline = st.split("_");
                String[] ingred = splitline[2].split("#");
                String[] instruc = splitline[3].split("#");
                Recipe temp = new Recipe();
                temp.name = splitline[0];
                temp.description = splitline[1];
                for (String s : ingred) {
                    temp.ingredients.add(s);
                }
                for (String q : instruc) {
                    temp.instructions.add(q);
                }
                menu.add(temp);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("\nLocal menu loaded...\n");
    }

    public static void printMenu(ArrayList<Recipe> r) {
        StringBuilder sb = new StringBuilder();
        for (Recipe s : r) {
            sb.append(s.name);
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}