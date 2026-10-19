package medicine;

// Abstract class: common details and behaviour for all medicines.
public abstract class Medicine {
    private String name;
    private String time;
    private int totalDoses;
    private int dosesTaken;

    // Default constructor
    public Medicine() {
        this("Unnamed Medicine", "Not set", 1);
    }

    // Parameterized constructor
    public Medicine(String name, String time, int totalDoses) {
        this.name = name;
        this.time = time;
        this.totalDoses = totalDoses;
        this.dosesTaken = 0;
    }

    // Copy constructor
    public Medicine(Medicine other) {
        this(other.name, other.time, other.totalDoses);
        this.dosesTaken = other.dosesTaken;
    }

    // Encapsulation: private data is accessed through public methods.
    public String getName() { return name; }
    public String getTime() { return time; }
    public int getTotalDoses() { return totalDoses; }
    public int getDosesTaken() { return dosesTaken; }
    public int getRemainingDoses() { return totalDoses - dosesTaken; }

    public void setName(String name) { this.name = name; }
    public void setTime(String time) { this.time = time; }
    public void setDosesTaken(int dosesTaken) {
        if (dosesTaken >= 0 && dosesTaken <= totalDoses) {
            this.dosesTaken = dosesTaken;
        }
    }

    // Method overloading: take one dose or more than one dose.
    public void takeDose() throws InvalidDoseException {
        takeDose(1);
    }

    public void takeDose(int count) throws InvalidDoseException {
        if (count <= 0) {
            throw new InvalidDoseException("Dose count must be greater than zero.");
        }
        if (count > getRemainingDoses()) {
            throw new InvalidDoseException("Not enough doses remaining for " + name + ".");
        }
        dosesTaken += count;
    }

    // Protected method: subclasses can use this common display text.
    protected String commonDetails() {
        return "Name: " + name + " | Time: " + time
                + " | Remaining doses: " + getRemainingDoses() + "/" + totalDoses;
    }

    public abstract void displayDetails();
    public abstract String toFileLine();
}
