package bigAdventure.entities;

import java.util.Random;

public class RandomDamageCalculator implements Calculator{
    Random random = new Random();

    @Override
    public int calculateDamage(int baseDamage) {
        return (baseDamage + 10)/(random.nextInt(2) + 1);
    }
}
