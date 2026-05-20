import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.lang.Math;
import java.lang.Integer;

/**
 * Abstract class representing a Virtual Pet.
 * This class provides core functionalities such as aging, hunger management,
 * energy depletion, and interactions like feeding, playing, and sleeping.
 */
public abstract class Pet implements Comparable<Pet>
{
    protected int age; 
    protected String name; 
    protected String type;
    protected static int petTotal;
    protected static ArrayList<Pet> pets = new ArrayList<>();
    protected ScheduledExecutorService scheduler;
    protected ScheduledExecutorService drainTime; 
    protected ScheduledExecutorService ageIncrease; 
    protected int lifeTime;
    protected boolean isAlive;
    protected int thirst;
    protected int happiness;
    protected int hunger; 
    protected int energy;
    protected int HP;

    /**
     * Constructor - Creates a new pet, ensuring no duplicate names exist.
     * Initializes all pet attributes and starts automatic processes (aging, status drain).
     */
    public Pet(String name, String type)
    {
        for (Pet pet: pets)
        {
            if (pet.getName().equals(name))
            {
                System.out.println("This pet has an overlapping name. Please choose another name");
                return;
            }
        }

        this.name = name;
        this.type = type;
        this.thirst = 50;
        this.happiness = 50; 
        this.hunger = 50; 
        this.energy = 50;
        this.HP = 100; 
        this.isAlive = true;
        pets.add(this);
        petTotal++;
        startLifeTimer();
        drainBars();
        growUp();
    }


     /**
     * Starts a life timer for the pet, tracking how long it has been alive.
     * Updates `lifeTime` every second.
     */
    public void startLifeTimer()
    {
        scheduler = Executors.newSingleThreadScheduledExecutor(); 
        scheduler.scheduleAtFixedRate(() -> 
        {
            if (isAlive)
            {
                lifeTime++;
            }

            else
                scheduler.shutdown();
        }, 0, 1, TimeUnit.SECONDS);

    }


    /**
     * Automatically decreases pet stats (hunger, thirst, energy, happiness) over time.
     * If all values reach zero, the pet "dies."
     */
    public void drainBars()
    {
        drainTime = Executors.newSingleThreadScheduledExecutor();
        drainTime.scheduleAtFixedRate(() ->
        {
            if (hunger > 0)
                hunger -= 10;
            if (thirst > 0)
                thirst -= 10; 
            if (happiness > 0)
                happiness -= 10; 
            if (energy > 0)
                energy -= 10;

            hunger = Math.max(0,hunger);
            thirst = Math.max(0,thirst);
            energy = Math.max(0,energy);
            happiness = Math.max(0,happiness);

            if (energy == 0 && thirst == 0 && energy == 0)
            {
                HP = 0; 
                isAlive = false;
                System.out.println("Your pet: " + name + " has passed away...");
                System.out.println(name + " had lived for: " + this.getTime());
                drainTime.shutdown();
            }

        }
        , 0, 120, TimeUnit.SECONDS);
    }


    /**
     * Automatically increases the pet's age every 2 minutes.
     */
    public void growUp()
    {
        ageIncrease = Executors.newSingleThreadScheduledExecutor();
        ageIncrease.scheduleAtFixedRate(() -> 
        {
            if (isAlive)
                age++;
        } , 0, 2, TimeUnit.MINUTES);
    }

    // Abstract methods - Each pet type must implement these actions.
    public abstract void feedFood();
    public abstract void giveWater();
    public abstract void play();
    public abstract void sleep(); 
    public abstract void makeSound();

    // Getter methods for pet attributes
    public int getAge() {return age;}
    public String getName() {return name;}
    public String getType() {return type;}
    public int getNum() {return petTotal;}
    public String getTime() {return lifeTime + " seconds";}
    public int getHappiness() {return happiness;}
    public int getThirst() {return thirst;}
    public int getEnergy() {return energy;}
    public int getHunger() {return hunger;}
    public int getHP() {return HP;}

    //Displays the list of all pets owned by the player
    public void getPetList()
    {
        if (pets.isEmpty())
        {
            System.out.println("No pets available");
            return; 
        }

        System.out.println("\n________________________");
        System.out.println("    Your Pets: ");
        System.out.println("_________________________");
        
        for (int i = 0; i < pets.size(); i++)
        {
            Pet currentPet = pets.get(i); 
            System.out.println("[" + (i + 1) + "] " + currentPet.getType() + ": " 
                                + currentPet.getName() + "   Age: " + currentPet.getAge() 
                                + "   Food: " + currentPet.getHunger() + "   Water: " + currentPet.getThirst()
                                + "   Energy: " + currentPet.getEnergy() + "   Happiness: " + currentPet.getHappiness());

        }
    }

    //Allows the player to switch to another pet.
    public static Pet switchPet(Scanner inGame, Pet currentPet) {
        if (pets.size() <= 1) {
            System.out.println("You only have one pet! No switching needed.");
            return currentPet; 
        }

        System.out.println("\n Available Pets:");
        System.out.println("══════════════════════════");

        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.println("[" + (i + 1) + "] " + pet.getType() + " - " + pet.getName());
        }
        System.out.println("══════════════════════════");
        System.out.print("Enter the number of the pet you want to switch to: ");

        try {
            int choice = Integer.parseInt(inGame.nextLine());

            if (choice >= 1 && choice <= pets.size()) {
                currentPet = pets.get(choice - 1); 
                System.out.println("Now taking care of " + currentPet.getName() + " the " + currentPet.getType());
            } else {
                System.out.println("Invalid choice! Please enter a valid pet number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }

        return currentPet; 
    }   

    //Provides a text representation of a pet.
    public String toString()
    {   
        return type + " - " + name +
           " | Age: " + age +
           " | HP: " + HP +
           " | Hunger: " + hunger +
           " | Thirst: " + thirst +
           " | Energy: " + energy +
           " | Happiness: " + happiness;
    }


    //Compares two pets to determine if they are the same based on name and type.
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; 
        if (obj == null || getClass() != obj.getClass()) return false;

        Pet other = (Pet) obj;
        return name.equalsIgnoreCase(other.name) && type.equalsIgnoreCase(other.type);
    }

    //Compares two pets for sorting by type, then by name.
    @Override
    public int compareTo(Pet otherPet) {
        if (otherPet == null) return -1; 

        int typeComparison = this.type.compareToIgnoreCase(otherPet.type);
        if (typeComparison != 0) {
            return typeComparison; 
        }
        return this.name.compareToIgnoreCase(otherPet.name); 
    }


    //Allows the player to compare `newPet` with another pet from the list.
    public static void compareWithAnotherPet(Scanner inGame, Pet newPet) {
        if (pets.size() <= 1) {
            System.out.println("You only have one pet! No comparison needed.");
            return;
        }
    
        System.out.println("\n🐾 Select a pet to compare with " + newPet.getName() + ":");
        for (int i = 0; i < pets.size(); i++) {
            Pet pet = pets.get(i);
            System.out.println("[" + (i + 1) + "] " + pet.getType() + " - " + pet.getName());
        }
    
        System.out.print("Enter the number of the pet: ");
        try {
            int choice = Integer.parseInt(inGame.nextLine());
    
            if (choice >= 1 && choice <= pets.size()) {
                Pet selectedPet = pets.get(choice - 1);
    
                if (newPet.equals(selectedPet)) {
                    System.out.println("" + newPet.getName() + " and " + selectedPet.getName() + " are the same pet.");
                } else {
                    System.out.println("These pets are different.");
                }
    
                int result = newPet.compareTo(selectedPet);
                if (result < 0) {
                    System.out.println(newPet.getName() + " comes before " + selectedPet.getName());
                } else if (result > 0) {
                    System.out.println(newPet.getName() + " comes after " + selectedPet.getName());
                } else {
                    System.out.println("These pets are identical.");
                }
            } else {
                System.out.println("Invalid choice! Please enter a valid pet number.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
    }
    

}