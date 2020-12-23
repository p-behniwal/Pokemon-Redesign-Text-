
public class Attack { //Attack class contains everything about an attack, used for easier storage of attacks
    private String name; //stores the names, ec, damage, special. Private because we do not want these fields to be modified by anything
    private int ec;
    private int damage;
    private String special;

    public Attack (String name, int ec, int damage, String special) { //"name": name of attack ; "ec": energy cost ; "damage": damage inflicted by attack ; "special": speciality of attack
        this.name = name;  //Making the params belong to this object
        this.ec = ec;
        this.damage = damage;
        this.special = special;
    }

    public String getName() {  // Getters for the attack
        return name;
    }

    public int getEc() {
        return ec;
    }

    public int getDamage() {
        return damage;
    }

    public String getSpecial(){
        return special;
    }

    public String toString() {  // to String allows for easier printing
        return String.format("║ %-20s ║ %-10d ║ %-10d ║ %-20s ║", name, ec, damage, special);
    }
}

