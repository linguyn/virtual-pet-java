import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * Main class for the Virtual Pet Game.
 * This class manages game flow, user interactions, pet adoption, and pet actions.
 */
public class ProjectOneTester {
    public static void main(String args[])
    {
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║          WELCOME TO VIRTUAL PET        ║");
        System.out.println("╚════════════════════════════════════════╝\n");

        System.out.println("In this game, you can choose your favorite pet and take care of them!");
        System.out.println("Your goal is to keep your pet happy and healthy for as long as possible.");
        System.out.println("To do this, you need to regularly feed, play, and provide water for them.");
        System.out.println("\nGAME RULES");
        System.out.println("1.Every 20 seconds, your pet's hunger, thirst, and energy will decrease.");
        System.out.println("2.If hunger, thirst, and energy all reach 0, your pet will pass away.");
        System.out.println("3.You can feed, give water, play, or let your pet sleep to restore its stats.");
        System.out.println("4.Try to keep your pet alive for as long as possible!");
        System.out.println("NOTE: The more pets you have, the more frequently you should check on them.");
        System.out.println("Try to be as fast as you can\n");
        System.out.println("If you are ready, type 'Start' to start the game");
        
        Scanner intro = new Scanner(System.in);
        Pet newPet = null;


        //Loop until user enters 'Start' to begin the game
        while (true)
        {
            String output = intro.nextLine();
            if (output.equalsIgnoreCase("Start")) 
                break;
            else 
                System.out.println("Please enter 'Start' to start");
        }
        //Pet Adoption - First pet selection
        while (true)
        {
            System.out.println("          Choose your new Pet:          ");
            System.out.println("               1.Dog                ");
            System.out.println("               2.Cat                ");
            System.out.println("               3.Rabbit              ");
            System.out.println("               4.Bird               ");
            System.out.println("               5.Hamster            ");
            System.out.println("               6.Exit               ");
            System.out.println("Enter the number of your choice: ");
            String petChosen = intro.nextLine(); 

            if (petChosen.equals("6"))
            {   System.out.println("No pet selected. Existing game...");
                System.exit(0);
            }

            if (!(petChosen.equals("1") || 
                  petChosen.equals("2") ||
                  petChosen.equals("3") || 
                  petChosen.equals("4") || 
                  petChosen.equals("5")))
            {
                System.out.println("Invalid choice! No pet created");
                continue;
            }


            System.out.println("Enter your pets's name: ");
            String petName = intro.nextLine();
            String type;
            //Create the selected pet
            switch (petChosen) {
                case "1": 
                    type = "Dog";
                    newPet = new Dog(petName, type);
                    break;

                case "2": 
                    type = "Cat";
                    newPet = new Cat(petName, "Cat"); 
                    break; 
                
                case "3": 
                    type = "Rabbit";
                    newPet = new Rabbit(petName, type); 
                    break; 

                case "4": 
                    type = "Bird";
                    newPet = new Bird(petName, type); 
                    break; 

                case "5": 
                    type = "Hamster";
                    newPet = new Hamster(petName, type);
                    break; 
            }

            System.out.println("Pet created!\n" + "Type: " + newPet.getType() + "\nName: " + newPet.getName() + "\nAge: " + newPet.getAge());
            break;
        }

        Scanner inGame = new Scanner(System.in); 

        // Main Game Loop
        while (true)
        {   
            System.out.println("-------------------------------------------------------------------");
            System.out.println("                       " + newPet.getType() +": " + newPet.getName() + "     Age: " + newPet.getAge());
            System.out.println("-------------------------------------------------------------------");
            System.out.println("HP: " + newPet.getHP() + "   Food: " + newPet.getHunger() + 
                               "   Water: " + newPet.getThirst() + "  Happiness: " 
                               + newPet.getHappiness() + "   Energy: " + newPet.getEnergy());
            System.out.println("-------------------------------------------------------------------");
            System.out.println("              1. Feed                           ");
            System.out.println("              2. Give water                     ");
            System.out.println("              3. Play                           ");
            System.out.println("              4. Go to sleep                    ");
            System.out.println("              5. Make sound                     ");
            System.out.println("              6. Adopt                          ");
            System.out.println("              7. View all pets                  ");
            System.out.println("              8. Switch pet                     ");
            System.out.println("              9. Compare pets                   ");
            System.out.println("              10. View current pet's stats      ");
            System.out.println("              11. Exit                           ");
            System.out.println("-------------------------------------------------");

            System.out.println("Enter your option number: "); 
            String option = inGame.nextLine();
            
            if (!(option.equals("1")
                ||option.equals("2")
                ||option.equals("3")
                ||option.equals("4")
                ||option.equals("5")
                ||option.equals("6")
                ||option.equals("7")
                ||option.equals("8")
                ||option.equals("9")
                ||option.equals("10")
                ||option.equals("11")))
            {   System.out.println("Invalid choice!"); 
                continue;  
            }

            if (option.equals("11")) return; 

            //Pet Adoption
            if (option.equals("6"))
            {
                while (true)
                {
                    System.out.println("          Adopt your new Pet:          ");
                    System.out.println("               1.Dog                ");
                    System.out.println("               2.Cat                ");
                    System.out.println("               3.Rabbit              ");
                    System.out.println("               4.Bird               ");
                    System.out.println("               5.Hamster            ");
                    System.out.println("               6.Cancel               ");
                    System.out.println("Enter the number of your choice: ");
                    String adopt = inGame.nextLine();

                    if (adopt.equals("6")) break;

                    if (!(adopt.equals("1") || 
                        adopt.equals("2") ||
                        adopt.equals("3") || 
                        adopt.equals("4") || 
                        adopt.equals("5")))
                    {
                       System.out.println("Invalid choice! No pet created");
                        continue;
                    }


                    System.out.println("Enter your pets's name: ");
                    String petName = inGame.nextLine();
                    String type;

                    Pet adoptedPet;
                    switch (adopt) {
                        case "1": 
                            type = "Dog";
                            adoptedPet = new Dog(petName, type);
                            break;

                        case "2": 
                            type = "Cat";
                            adoptedPet = new Cat(petName, "Cat"); 
                            break; 
                        
                        case "3": 
                            type = "Rabbit";
                            adoptedPet = new Rabbit(petName, type); 
                            break; 

                        case "4": 
                            type = "Bird";
                            adoptedPet = new Bird(petName, type); 
                            break; 

                        case "5": 
                            type = "Hamster";
                            adoptedPet = new Hamster(petName, type);
                            break; 

                        default: 
                            adoptedPet = null;
                    }

                    if (adoptedPet != null)
                    {

                        newPet = adoptedPet;
                        System.out.println("New Pet Added!\n" + "Type: " + newPet.getType() + "   Name: " + newPet.getName() + "   Age: " + newPet.getAge());
                    }

                    System.out.println("Would you like to adopt another pet? (yes/no)");
                    String answer = inGame.nextLine();
                    if (!answer.equalsIgnoreCase("yes")) {
                            break;
                    }
                }
                continue;
            }

            //Handle Player Actions
            switch (option) {
                case "1":
                    newPet.feedFood();
                    break;
            
                case "2": 
                    newPet.giveWater();
                    break;
                
                case "3": 
                    newPet.play();
                    break;

                case "4": 
                    newPet.sleep();
                    break; 

                case "5": 
                    newPet.makeSound();
                    break; 

                case "7": 
                    newPet.getPetList();
                    break;

                case "8": 
                    newPet = Pet.switchPet(inGame, newPet);
                    break;
                
                case "9":
                    Pet.compareWithAnotherPet(inGame, newPet);
                    break;
                case "10": 
                    System.out.println(newPet);
                    break;
            }
        }        

    }
}

