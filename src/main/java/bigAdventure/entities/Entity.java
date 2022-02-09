package bigAdventure.entities;

public abstract class Entity implements Action{
    private String name;
    private int health;
    private int baseDamage;

    public Entity(String name, int health, int baseDamage) {
        this.name = name;
        this.health = health;
        this.baseDamage = baseDamage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    @Override
    public void receiveDamage(int damage) {
        this.setHealth(this.getHealth() - damage);
    }

    @Override
    public boolean isAlive(int hp) {
        if(hp > 0) return false;
        else return true;
    }
}
