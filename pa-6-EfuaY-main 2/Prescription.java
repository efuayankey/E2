import java.time.LocalDateTime;

/**
 * Prescription represents a medication prescription issued to a patient.
 * Prescriptions track medication details and filling status for pharmacy management.
 * @author Efua Yankey
 * @version 1.0
 */
public class Prescription {
    private String id;
    private String patientID;
    private String patientName;
    private String medication;
    private String dosage;
    private LocalDateTime issuedTime;
    private boolean filled;
    
    // constructor
    public Prescription(String id, String patientID, String patientName, String medication, String dosage) {
        this.id = id;
        this.patientID = patientID;
        this.patientName = patientName;
        this.medication = medication;
        this.dosage = dosage;
        this.issuedTime = LocalDateTime.now();  // auto-initialize
        this.filled = false;  
    }
    
    // getters
    public String getId() { return id; }
    public String getPatientID() { return patientID; }
    public String getPatientName() { return patientName; }
    public String getMedication() { return medication; }
    public String getDosage() { return dosage; }
    public LocalDateTime getIssuedTime() { return issuedTime; }
    public boolean isFilled() { return filled; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public void setPatientName(String patientName) { this.patientName = patientName; }
    public void setMedication(String medication) { this.medication = medication; }
    public void setDosage(String dosage) { this.dosage = dosage; }
    public void setIssuedTime(LocalDateTime issuedTime) { this.issuedTime = issuedTime; }
    public void setFilled(boolean filled) { this.filled = filled; }
    
    // toString
    public String toString() {
        return String.format("Prescription %s for Patient %s (%s) - %s, %s [Filled: %s]", 
                           id, patientID, patientName, medication, dosage, filled);
    }
}