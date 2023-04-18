import java.util.Random;

public class Powerups {
    
    Powerups()
    {

    }

    public static void generateRandomPowerup(int wallX, int wallY) {

        Random random = new Random();
        int powerupNumber = random.nextInt(3);

        switch(powerupNumber)
        {
            case 0:
                Objects.addObject(wallX, wallY, 1);
                break;
            case 1:
                Objects.addObject(wallX, wallY, 2);
                break;
            case 2:
                Objects.addObject(wallX, wallY, 3);
                break;
            default:
                //System.out.println("try again");
        }
    }
}
