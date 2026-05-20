public class Rabbit extends Pet  {

    public Rabbit(String name, String type)
    {
        super(name, type); 
    }

    @Override
    public void feedFood()
    {
        if (hunger < 100)
        {
            hunger += 30; 
            System.out.println("Feeding...");
            System.out.println("^.^");
        }

        else
        {   
            hunger = 100;
            System.out.println("Grunt! Grunt!");
            System.out.println(this.getName() + " " + "is already full");
        }
    }

    @Override
    public void giveWater()
    {
        if (thirst < 100)
        {
            thirst += 30; 
            System.out.println("Drinking...");
            System.out.println("^.^");
        }

        else
        {   
            thirst = 100;
            System.out.println("Grunt! Grunt!");
            System.out.println(this.getName() + " " + "is not thirsty");
        }
    }

    @Override
    public void play()
    {
        if (happiness < 100)
        {
            happiness += 30;
            System.out.println("Playing...");
            System.out.println("^.^");
        }

        else
        {   
            happiness = 100;
            System.out.println("Grunt! Grunt!");
            System.out.println(this.getName() + " " + "is not in the mood");
        }
    }

    @Override
    public void sleep()
    {
        if (energy < 100)
        {
            energy += 30;
            System.out.println("   zzzzzzzzzz");
            System.out.println("-.-");
            
        }

        else
        {   
            energy = 100;
            System.out.println("~.~");
            System.out.println(this.getName() + " " + "has enough sleep");
        }
    }

    @Override
    public void makeSound()
    {
        System.out.println("Purr-purr");
    }
}
