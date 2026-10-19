package medicine;

// Multilevel inheritance: Medicine -> Tablet -> ExtendedReleaseTablet.
public class ExtendedReleaseTablet extends Tablet {
    public ExtendedReleaseTablet(String name, String time, int totalDoses, String tabletType) {
        super(name, time, totalDoses, tabletType + " (extended release)");
    }

    @Override
    public void displayDetails() {
        System.out.print("[Extended-release tablet] ");
        super.displayDetails();
    }

    @Override
    public String toFileLine() {
        return "EXTENDED_TABLET|" + getName() + "|" + getTime() + "|" + getTotalDoses()
                + "|" + getDosesTaken() + "|Extended Release";
    }
}
