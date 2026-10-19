package medicine;

// Runnable is another way to create a task for a thread.
public class DoseReportTask implements Runnable {
    private MedicineManager manager;

    public DoseReportTask(MedicineManager manager) {
        this.manager = manager;
    }

    @Override
    public void run() {
        System.out.println("[Report thread] Total medicines in list: " + manager.getCount());
    }
}
