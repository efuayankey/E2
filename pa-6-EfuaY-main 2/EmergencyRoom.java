import java.util.*;

/**
 * EmergencyRoom manages patients, medical records, and prescriptions in a hospital emergency department.
 * The system handles patient triage, treatment scheduling, and operational history tracking.
 * @author Efua Yankey
 * @version 1.0
 */
public class EmergencyRoom {
    private PriorityQueue<Patient> triageQueue;
    private ArrayList<MedicalRecord> medicalRecords;
    private LinkedList<Patient> treatmentRoomQueue;
    private Queue<Prescription> prescriptionQueue;
    private Stack<String> operationHistory;
    private ArrayList<Patient> patientDB;
    
    // constructor - initialize all data structures
    public EmergencyRoom() {
        triageQueue = new PriorityQueue<>();
        medicalRecords = new ArrayList<>();
        treatmentRoomQueue = new LinkedList<>();
        prescriptionQueue = new LinkedList<>();  // LinkedList implements Queue
        operationHistory = new Stack<>();
        patientDB = new ArrayList<>();
    }


    /**
     * gets the number of prescriptions waiting to be filled
     * @return the count of pending prescriptions
     * time complexity: o(1)
     */
    public int getPendingPrescriptionsCount() {
        return prescriptionQueue.size();
}

    /**
     * returns a list of patients in the triage queue ordered by priority
     * @return list of patients sorted by priority (most critical first)
     * time complexity: o(n log n)
     */
    public List<Patient> getTriageWaitingList() {
        List<Patient> result = new ArrayList<>();
        PriorityQueue<Patient> tempQueue = new PriorityQueue<>(triageQueue);
        while (!tempQueue.isEmpty()) {
            result.add(tempQueue.poll());
        }
        return result;
    }

    /**
     * gets all patients currently being treated in treatment rooms
     * @return list of patients in treatment
     * time complexity: o(n)
     */
    public List<Patient> getPatientsInTreatment(){
        return new ArrayList<>(treatmentRoomQueue);
    }

    /**
     * returns the history of all operations performed in reverse order
     * @return list of operation descriptions (most recent first)
     * time complexity: o(n)
     */
    public List<String> getOperationHistory(){
        List<String> result = new ArrayList<>();
        for(int i = operationHistory.size() - 1; i >= 0; i--){
            result.add("ER operation --> " + operationHistory.get(i));
        }
        return result;
    }

    /**
     * admits a new patient to the emergency room and adds them to triage queue
     * @param p the patient to admit
     * time complexity: o(n log n)
     */
    public void admitPatient(Patient p) {
        triageQueue.add(p);
        patientDB.add(p);
        //Collections.sort(patientDB, Comparator.comparing(Patient::getPatientId)); //sort after adding to it -- using lambda expression
        Collections.sort(patientDB, new Comparator<Patient>() {
            public int compare(Patient p1, Patient p2) {
                return p1.getPatientId().compareTo(p2.getPatientId());
            }
        });

        operationHistory.push("Admit Patient:" + p.getPatientId());
        System.out.println("Patient " + p.getPatientId() + " admitted to the emergency room");
}


    /**
     * adds a medical record to the system
     * @param mr the medical record to add
     * time complexity: o(1)
     */
    public void addMedicalRecord(MedicalRecord mr) {
        medicalRecords.add(mr);
        operationHistory.push("Medical Record added:" + mr.getId()); //log to operation history
        System.out.println("Medical Record " + mr.getId() + " added");
}

    /**
     * issues a new prescription and adds it to the queue for filling
     * @param p the prescription to issue
     * time complexity: o(1)
     */
    public void issuePrescription(Prescription p) {
        prescriptionQueue.add(p);
        operationHistory.push("Prescription issued:" + p.getId());
        System.out.println("Prescription " + p.getId() + " issued");
    }

    /**
     * fills the next prescription in the queue and marks it as filled
     * @return the prescription that was filled, or null if queue is empty
     * time complexity: o(1)
     */
    public Prescription fillNextPrescription(){
    Prescription p = prescriptionQueue.poll();
    if (p == null){return null;}
    p.setFilled(true);
    operationHistory.push("Prescription filled:" + p.getId());
    System.out.println("Prescription " + p.getId() + " filled");
    return p;
}

    /**
     * undoes the last operation performed in the emergency room
     * @return true if operation was undone, false if nothing to undo
     * time complexity: o(1)
     */
    public boolean undoLastOperation() {
    //check if there's anything to undo
    if (operationHistory.isEmpty()) {
        return false;  // nothing to undo
    }
   //pop the last operation from the stack
    String operation = operationHistory.pop();
    System.out.println("Undoing ER operation --> " + operation);
    return true;
}

    /**
     * moves the highest priority patient from triage to treatment room
     * @return the patient moved to treatment, or null if no patients waiting
     * time complexity: o(log n)
     */
    public Patient treatNextPatient(){
        Patient p = triageQueue.poll();  //remove highest priority patient from waiting room
        if (p == null){
            return null;
        }

        int index = Collections.binarySearch(patientDB, p, Comparator.comparing(Patient ::getPatientId));
        if (index >= 0){
            treatmentRoomQueue.add(p);
            operationHistory.push("Treat Patient:" + p.getPatientId());
            return p;
        }
        else{
            return null;
        }
    }

    /**
     * completes treatment for a patient and removes them from treatment room
     * @param patientID the id of the patient whose treatment is complete
     * time complexity: o(n)
     */
    public void completePatientTreatment(String patientID){
        Iterator<Patient> iter = treatmentRoomQueue.iterator();
        while(iter.hasNext()){
            Patient p = iter.next();
            if(p.getPatientId().equals(patientID)){
                iter.remove();
                p.setTreated(true);
                operationHistory.push("Patient treated:" + patientID);
                System.out.println("Patient " + p.getPatientId() + " treatment completed");
                break;
            } 
        }
    }

    /**
     * counts how many patients are waiting for each severity level
     * @return list of pairs with severity and count for each level
     * time complexity: o(n)
     */
    public List<Pair<String, Integer>> getPatientCountBySeverity() {
    HashMap<String, Integer> counts = new HashMap<>();
    for (Patient p : triageQueue) {  //loop through triageQueue and count each severity
        String severity = p.getSeverity();
        counts.put(severity, counts.getOrDefault(severity, 0) + 1);
    }
    // Convert HashMap to List of Pairs in expected order
    List<Pair<String, Integer>> result = new ArrayList<>();
    String[] severityOrder = {"URGENT", "STANDARD", "NON_URGENT"};
    for (String severity : severityOrder) {
        if (counts.containsKey(severity)) {
            result.add(new Pair<>(severity, counts.get(severity)));
        }
    }
    
    return result;
}

    /**
     * calculates what percentage of treatment rooms are currently in use
     * @param rooms total number of treatment rooms available
     * @return utilization as a decimal between 0.0 and 1.0
     * time complexity: o(1)
     */
    public double getTreatmentRoomUtilization(int rooms) {
        return (double) treatmentRoomQueue.size() / rooms;
}

    /**
     * calculates average waiting time for patients grouped by severity level
     * @return list of pairs with severity and average wait time in seconds
     * time complexity: o(n)
     */
    public List<Pair<String, Double>> getAverageWaitingTimeBySeverity() {
        //create HashMaps to track total time and count per severity
        HashMap<String, Long> totalTime = new HashMap<>();
        HashMap<String, Integer> count = new HashMap<>();
        
        //loop through triageQueue
        for (Patient p : triageQueue) {
            String severity = p.getSeverity();
            long waitTime = p.getWaitingTime();
            
            // addto total time for this severity
            totalTime.put(severity, totalTime.getOrDefault(severity, 0L) + waitTime);
            
            // Increment count for this severity
            count.put(severity, count.getOrDefault(severity, 0) + 1);
        }
        
        //calculate averages and create result list in expected order
        List<Pair<String, Double>> result = new ArrayList<>();
        String[] severityOrder = {"URGENT", "STANDARD", "NON_URGENT"};
        for (String severity : severityOrder) {
            if (totalTime.containsKey(severity)) {
                long total = totalTime.get(severity);
                int patientCount = count.get(severity);
                double average = (double) total / patientCount;  // calculate average
                
                result.add(new Pair<>(severity, average));
            }
        }
    
    return result;
}

    }
