import java.time.LocalDateTime;

/**
 * MedicalRecord stores detailed medical information for a patient's visit.
 * Records include diagnosis, treatment details, and doctor information for documentation.
 * @author Efua Yankey
 * @version 1.0
 */
public class MedicalRecord{
    private String id;
    private String patientID;
    private LocalDateTime visitDate;
    private String diagnosis;
    private String treatment;
    private String prescribedMedication;
    private String doctorName;

    // Constructor
    public MedicalRecord(String id, String patientID, LocalDateTime visitDate, 
                         String diagnosis, String treatment, 
                         String prescribedMedication, String doctorName) {
        this.id = id;
        this.patientID = patientID;
        this.visitDate = visitDate;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.prescribedMedication = prescribedMedication;
        this.doctorName = doctorName;
    }
    
    // Getters
    public String getId() { return id; }
    public String getPatientID() { return patientID; }
    public LocalDateTime getVisitDate() { return visitDate; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatment() { return treatment; }
    public String getPrescribedMedication() { return prescribedMedication; }
    public String getDoctorName() { return doctorName; }
    
    // Setters
    public void setId(String id) { this.id = id; }
    public void setPatientID(String patientID) { this.patientID = patientID; }
    public void setVisitDate(LocalDateTime visitDate) { this.visitDate = visitDate; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    public void setPrescribedMedication(String prescribedMedication) { 
        this.prescribedMedication = prescribedMedication; 
    }
    public void setDoctorName(String doctorName) { this.doctorName = doctorName; }
    
    // toString - you'll need to implement this based on output format
    public String toString() {
        return "MedicalRecord[ID=" + id + ", Patient=" + patientID + 
            ", Date=" + visitDate + ", Diagnosis=" + diagnosis + 
            ", Treatment=" + treatment + ", Medication=" + prescribedMedication + 
            ", Doctor=" + doctorName + "]";
    }
    }

