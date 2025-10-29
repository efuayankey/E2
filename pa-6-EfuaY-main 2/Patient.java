
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;  

/**
 * Patient represents a patient in the emergency room with personal information and medical details.
 * Patients are prioritized by severity level and arrival time for treatment ordering.
 * @author Efua Yankey
 * @version 1.0
 */
public class Patient implements Comparable<Patient>{
    private static final String [] emergency_severity ={"CRITICAL", "URGENT", "STANDARD", "NON_URGENT"};
    private String id;
    private String name;
    private int age;
    private String severity;
    private String symptoms;
    private LocalDateTime arrivalTime;
    private boolean treated;

    public Patient (String id, String name, int age, String severity, String symptoms){
        this.id = id;
        this.name = name;
        this.age = age;
        this.severity = severity;
        this.symptoms = symptoms;
        this.arrivalTime = LocalDateTime.now(); // system records arrival time NOW
        this.treated = false; 
    }

//getters
    public String getPatientId() { return id; }
    public String getName(){return name;}
    public int getAge(){return age;}
    public String getSeverity(){return severity;}
    public String getSymptoms(){return symptoms;}
    public LocalDateTime getArrivalTime() { return arrivalTime; }
    public boolean isTreated() { return treated; }


    public void setId(String id){this.id = id;}
    public void setName(String name){this.name = name;}
    public void setAge(int age){this.age = age;}
    public void setSeverity(String severity){this.severity = severity;}
    public void setSymptoms(String symptoms){this.symptoms = symptoms;}
    public void setTreated(boolean treated) { this.treated = treated; }

    private int getSeverityLevel(String severity){
        for (int i =0; i < emergency_severity.length; i++){
            if(emergency_severity[i].equals(severity)){
                return i;
            }
        }
        return -1;
    }
    public int compareTo(Patient other){
        int thisSeverityLevel =  getSeverityLevel(this.severity);
        int otherSeverityLevel = getSeverityLevel(other.severity);

        // Compare severity first
        if (thisSeverityLevel < otherSeverityLevel){
            return -1; // this is more critical
        }
        else if (thisSeverityLevel > otherSeverityLevel){
            return 1; // other is more critical
        }
        else{
            // same severity? compare arrival times
            // earlier arrival should come first
            return this.arrivalTime.compareTo(other.arrivalTime);
        }

    }

    /**
     * checks if two patients are equal based on their id
     * @param o the object to compare with
     * @return true if patients have same id, false otherwise
     */
    public boolean equals(Object o){
        if(o instanceof Patient){
            Patient p = (Patient) o;
            return this.id.equals(p.id);
        }
            return false;
    }

    /**
     * returns a formatted string representation of the patient
     * @return formatted string with patient details and arrival time
     */
    public String toString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-10s\t%-30s\t%-5d\t%-10s\t%s", id, name, age, severity, arrivalTime.format(formatter));
    }

    /**
     * calculates how long the patient has been waiting in seconds
     * @return waiting time in seconds from arrival to now
     */
    public long getWaitingTime(){
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(this.arrivalTime, now);
        return duration.toSeconds();
}
}