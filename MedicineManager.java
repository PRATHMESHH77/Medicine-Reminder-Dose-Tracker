package medicine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MedicineManager {
    // Array of objects, as required by the syllabus.
    private Medicine[] medicines = new Medicine[50];
    private int count = 0;
    private final String fileName = "medicines.txt";

    public boolean addMedicine(Medicine medicine) {
        if (count >= medicines.length) {
            System.out.println("Medicine list is full.");
            return false;
        }
        medicines[count] = medicine;
        count++;
        return true;
    }

    // Overloaded search methods: search by name or by number.
    public Medicine findMedicine(String name) {
        for (int i = 0; i < count; i++) {
            if (medicines[i].getName().equalsIgnoreCase(name)) return medicines[i];
        }
        return null;
    }

    public Medicine findMedicine(int number) {
        if (number >= 1 && number <= count) return medicines[number - 1];
        return null;
    }

    public int getCount() { return count; }

    public void displayAll() {
        if (count == 0) {
            System.out.println("No medicines added yet.");
            return;
        }
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + ". ");
            medicines[i].displayDetails(); // Runtime polymorphism / overriding
        }
    }

    public void saveToFile() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < count; i++) {
                writer.write(medicines[i].toFileLine());
                writer.newLine();
            }
        }
        System.out.println("Medicine data saved to " + fileName + ".");
    }

    public void loadFromFile() throws IOException {
        File file = new File(fileName);
        if (!file.exists()) return;

        count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null && count < medicines.length) {
                String[] part = line.split("\\|", -1);
                if (part.length != 6) continue;

                Medicine medicine;
                if (part[0].equals("EXTENDED_TABLET")) {
                    medicine = new ExtendedReleaseTablet(part[1], part[2], Integer.parseInt(part[3]), "Extended Release");
                } else if (part[0].equals("TABLET")) {
                    medicine = new Tablet(part[1], part[2], Integer.parseInt(part[3]), part[5]);
                } else if (part[0].equals("SYRUP")) {
                    medicine = new Syrup(part[1], part[2], Integer.parseInt(part[3]), Integer.parseInt(part[5]));
                } else {
                    continue;
                }
                medicine.setDosesTaken(Integer.parseInt(part[4]));
                medicines[count] = medicine;
                count++;
            }
        }
    }
}
