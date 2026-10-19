package medicine;

public class Tablet extends Medicine implements ReminderAction {
    private String tabletType;

    public Tablet() {
        this("Unnamed Tablet", "Not set", 1, "General");
    }

    public Tablet(String name, String time, int totalDoses, String tabletType) {
        super(name, time, totalDoses);
        this.tabletType = tabletType;
    }

    // Copy constructor
    public Tablet(Tablet other) {
        super(other);
        this.tabletType = other.tabletType;
    }

    @Override
    public void displayDetails() {
        System.out.println("[Tablet] " + commonDetails() + " | Type: " + tabletType);
    }

    @Override
    public void showReminder() {
        System.out.println("Reminder: " + getName() + " is scheduled for " + getTime() + ".");
    }

    @Override
    public String toFileLine() {
        return "TABLET|" + getName() + "|" + getTime() + "|" + getTotalDoses()
                + "|" + getDosesTaken() + "|" + tabletType;
    }
}
