import java.util.List;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDateTime;

public class EmergencyRoomManager{
    public static void main(String[] args){
        EmergencyRoom er = new EmergencyRoom();

        // Admit multiple patients (10)
        System.out.println("\nTest case 1: Admitting patients to the Emergency Room");
        readPatients(er, "patients.txt");

        // Display triageQueue
        System.out.println("\nTest case 2: List of patients admitted to the ER");
        List<Patient> list = er.getTriageWaitingList();
        print(list.iterator());
        
        // Treat patients in priority order (5 patients)
        System.out.println("\nTest case 3: Moving some patients to the treatment rooms");
        Patient p1 = er.treatNextPatient();
        MedicalRecord mr = new MedicalRecord("MR-001", p1.getPatientId(), LocalDateTime.now(), "Unstable heart beat", "IV fluids", "Blood thiner", "Katherine Bloom");
        er.addMedicalRecord(mr);
        er.issuePrescription(new Prescription("M-001", p1.getPatientId(), p1.getName(), "Blood thiner", "100mg daily"));
        
        Patient p2 = er.treatNextPatient();
        mr = new MedicalRecord("MR-002", p2.getPatientId(), LocalDateTime.now(), "Ashma attack", "oxygen", "ventoline", "Dylan Petro");
        er.addMedicalRecord(mr);
        er.issuePrescription(new Prescription("M-002", p2.getPatientId(), p2.getName(), "ventoline", "two sprays a day"));

        Patient p3 = er.treatNextPatient();
        mr = new MedicalRecord("MR-003", p3.getPatientId(), LocalDateTime.now(), "Heart attack", "clot-busters", "aspirin", "Joe Dawn");
        er.addMedicalRecord(mr);
        er.issuePrescription(new Prescription("M-003", p3.getPatientId(), p3.getName(), "aspirin", "1000mg daily"));
        // waiting for 15 seconds
        sleep(15_000);
        Patient p4 = er.treatNextPatient();
        mr = new MedicalRecord("MR-004", p4.getPatientId(), LocalDateTime.now(), "Ear infection", "IV fluids with antibiotocs", "Zithromax", "Joe Dawn");
        er.addMedicalRecord(mr);
        er.issuePrescription(new Prescription("M-004", p4.getPatientId(), p4.getName(), "Zithromax", "1000mg daily for 5 days"));

        Patient p5 = er.treatNextPatient();
        mr = new MedicalRecord("MR-005", p5.getPatientId(), LocalDateTime.now(), "Ear infection", "IV fluids with antibiotocs", "Pennicillin", "Joe Dawn");
        er.addMedicalRecord(mr);
        er.issuePrescription(new Prescription("M-005", p5.getPatientId(), p5.getName(), "Pennicillin", "1000mg daily"));
        // waiting for 10 seconds
        sleep(10_000);
        
        System.out.println("\nTest case 4: Filling some prescriptions");
        er.fillNextPrescription();
        er.fillNextPrescription();
        er.fillNextPrescription();

        System.out.println("\nTest case 5: Completing the treatment of some patients");
        er.completePatientTreatment(p1.getPatientId());
        er.completePatientTreatment(p2.getPatientId());
        //er.completePatientTreatment(p3.getPatientId());

        // Display statistics 
        System.out.println("\nTest case 6: List of patients waiting in the ER");
        list = er.getTriageWaitingList();
        print(list.iterator());
        System.out.println("\nTest case 7: List of patients waiting for treatment");
        list = er.getPatientsInTreatment();
        print(list.iterator());

        // average waiting times by severity
        List<Pair<String,Double>> wList = er.getAverageWaitingTimeBySeverity();
        System.out.println("\nTest case 8: Average waiting time by severity");
        for(Pair<String,Double> p: wList){
            System.out.println("Average waiting time for patients with severity \"" + p.getFirst() + "\" = " + 
                                String.format("%.2f",p.getSecond()) + " seconds");
        }

        // patients by severity
        List<Pair<String,Integer>> sList = er.getPatientCountBySeverity();
        System.out.println("\nTest case 9: Number of patients by severity");
        for(Pair<String,Integer> p: sList){
            System.out.println("Number of patients with severity \"" + p.getFirst() + "\" = " + p.getSecond());
        }

        // treatment room utilization
        System.out.println("\nTest case 10: Treatment Room Utilization");
        System.out.println("Treatment room utilization = " + String.format("%.2f%%",er.getTreatmentRoomUlization(5)*100));
        
        // demonstrate undo operations
        System.out.println("\nTest case 11: History of operations in the ER");
        List<String> history = er.getOperationHistory();
        print(history.iterator());
        System.out.println("\nTest case 12: Undoing the last five operations");
        er.undoLastOperation();
        er.undoLastOperation();
        er.undoLastOperation();
        er.undoLastOperation();
        er.undoLastOperation();
    }
    /**
     * Method to read data from a text file into the priority queue
     * @param er the emergency room where the patients will be admitted
     * @param filename the name of the file to read from
     */
    public static void readPatients(EmergencyRoom er, String filename){
        try{
            Scanner read = new Scanner(new File(filename));
            while(read.hasNextLine()){
                String line = read.nextLine();
                String[] tokens = line.split(",");
                int age = Integer.parseInt(tokens[2]);
                Patient p = new Patient(tokens[0], tokens[1], age, tokens[3], tokens[4]);
                sleep(1000);
                er.admitPatient(p);
            }
            read.close();
        }
        catch(FileNotFoundException e){
            System.out.println("File not found");
        }
    }
    /**
     * Generic method to print a collection using an iterator
     * @param iter iterator to the collection to print
     */
    public static <E> void print(Iterator<E> iter){
        if(iter == null)
            return;
        while(iter.hasNext()){
            System.out.println(iter.next());
        }
    }
    /**
     * Method to simulate time elapsing
     * @param time is seconds to make the program sleep
     */
    public static void sleep(long time){
        try{
            Thread.sleep(time);
        }
        catch(InterruptedException e){
        }
    }
}