package medicine;

public class Syrup extends Medicine implements ReminderAction {
    private int mlPerDose;

    public Syrup() {
        this("Unnamed Syrup", "Not set", 1, 5);
    }

    public Syrup(String name, String time, int totalDoses, int mlPerDose) {
        super(name, time, totalDoses);
        this.mlPerDose = mlPerDose;
    }

    // Copy constructor
    public Syrup(Syrup other) {
        super(other);
        this.mlPerDose = other.mlPerDose;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Syrup] " + commonDetails() + " | ML per dose: " + mlPerDose);
    }

    @Override
    public void showReminder() {
        System.out.println("Reminder: " + getName() + " is scheduled for " + getTime() + ".");
    }

    @Override
    public String toFileLine() {
        return "SYRUP|" + getName() + "|" + getTime() + "|" + getTotalDoses()
                + "|" + getDosesTaken() + "|" + mlPerDose;
    }
}
