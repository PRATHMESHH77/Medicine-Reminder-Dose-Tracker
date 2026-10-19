package medicine;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        MedicineManager manager = new MedicineManager();
        FileManager fileManager = new FileManager();

        // Restore saved data when the program starts.
        try {
            manager.loadFromFile();
        } catch (IOException | NumberFormatException e) {
            System.out.println("Could not load saved data: " + e.getMessage());
        }

        int choice = 0;
        while (choice != 8) {
            System.out.println("\n=== MEDICINE REMINDER & DOSE TRACKER ===");
            System.out.println("1. Add tablet");
            System.out.println("2. Add syrup");
            System.out.println("3. Display medicines");
            System.out.println("4. Record a dose");
            System.out.println("5. Start reminder thread");
            System.out.println("6. Show report using Runnable thread");
            System.out.println("7. Save data and create byte backup");
            System.out.println("8. Save and exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(input.nextLine().trim());
                switch (choice) {
                    case 1:
                        System.out.print("Medicine name: ");
                        String tabletName = input.nextLine().trim();
                        System.out.print("Time to take (e.g. 9 AM): ");
                        String tabletTime = input.nextLine().trim();
                        System.out.print("Total doses: ");
                        int tabletDoses = Integer.parseInt(input.nextLine().trim());
                        System.out.print("Tablet type: ");
                        String tabletType = input.nextLine().trim();
                        if (tabletDoses <= 0) throw new IllegalArgumentException("Total doses must be positive.");
                        if (tabletType.equalsIgnoreCase("Extended Release")) {
                            manager.addMedicine(new ExtendedReleaseTablet(tabletName, tabletTime, tabletDoses, tabletType));
                        } else {
                            manager.addMedicine(new Tablet(tabletName, tabletTime, tabletDoses, tabletType));
                        }
                        System.out.println("Tablet added.");
                        break;
                    case 2:
                        System.out.print("Medicine name: ");
                        String syrupName = input.nextLine().trim();
                        System.out.print("Time to take (e.g. 9 AM): ");
                        String syrupTime = input.nextLine().trim();
                        System.out.print("Total doses: ");
                        int syrupDoses = Integer.parseInt(input.nextLine().trim());
                        System.out.print("ML per dose: ");
                        int ml = Integer.parseInt(input.nextLine().trim());
                        if (syrupDoses <= 0 || ml <= 0) throw new IllegalArgumentException("Dose values must be positive.");
                        manager.addMedicine(new Syrup(syrupName, syrupTime, syrupDoses, ml));
                        System.out.println("Syrup added.");
                        break;
                    case 3:
                        manager.displayAll();
                        break;
                    case 4:
                        manager.displayAll();
                        System.out.print("Enter medicine number: ");
                        int number = Integer.parseInt(input.nextLine().trim());
                        Medicine selected = manager.findMedicine(number);
                        if (selected == null) {
                            System.out.println("Invalid medicine number.");
                            break;
                        }
                        System.out.print("Number of doses to record (usually 1): ");
                        int doseCount = Integer.parseInt(input.nextLine().trim());
                        try {
                            selected.takeDose(doseCount); // may throw user-defined exception
                            System.out.println("Dose recorded successfully.");
                        } catch (InvalidDoseException e) {
                            System.out.println("Dose error: " + e.getMessage());
                        } finally {
                            System.out.println("Dose-recording operation finished.");
                        }
                        break;
                    case 5:
                        manager.displayAll();
                        System.out.print("Enter medicine number: ");
                        int reminderNumber = Integer.parseInt(input.nextLine().trim());
                        Medicine reminderMedicine = manager.findMedicine(reminderNumber);
                        if (reminderMedicine == null) {
                            System.out.println("Invalid medicine number.");
                        } else {
                            ReminderService reminder = new ReminderService(reminderMedicine);
                            reminder.start();
                            reminder.join(); // wait for thread to finish
                            reminder.printSummary();
                        }
                        break;
                    case 6:
                        Thread reportThread = new Thread(new DoseReportTask(manager));
                        reportThread.start();
                        reportThread.join();
                        break;
                    case 7:
                        manager.saveToFile();
                        fileManager.makeByteBackup("medicines.txt", "medicines_backup.dat");
                        break;
                    case 8:
                        manager.saveToFile();
                        System.out.println("Goodbye!");
                        break;
                    default:
                        System.out.println("Choose a number from 1 to 8.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            } catch (IllegalArgumentException e) {
                System.out.println("Input error: " + e.getMessage());
            } catch (IOException e) {
                System.out.println("File error: " + e.getMessage());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread was interrupted.");
            }
        }
        input.close();
    }
}
