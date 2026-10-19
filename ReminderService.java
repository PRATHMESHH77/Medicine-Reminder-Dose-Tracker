package medicine;

// Inheritance + interface implementation. Thread is a Java class.
public class ReminderService extends Thread implements ReminderAction, PrintableDetails {
    private Medicine medicine;

    public ReminderService(Medicine medicine) {
        this.medicine = medicine;
    }

    @Override
    public void run() {
        showReminder();
    }

    @Override
    public void showReminder() {
        System.out.println("[Reminder thread] " + medicine.getName()
                + " is scheduled for " + medicine.getTime() + ".");
    }

    @Override
    public void printSummary() {
        System.out.println("Medicine: " + medicine.getName() + ", remaining doses: " + medicine.getRemainingDoses());
    }
}
