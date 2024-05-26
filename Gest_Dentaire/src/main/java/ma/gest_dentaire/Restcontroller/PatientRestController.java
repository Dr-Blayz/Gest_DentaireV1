package ma.gest_dentaire.Restcontroller;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Patient")
public class PatientRestController {

    @Autowired
    private PatientService patientService;

    @PostMapping("/add")
    public ResponseEntity<Patient> addPatient(@RequestBody Patient patient) {
        Patient addedPatient = patientService.addPatient(patient);
        if (addedPatient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedPatient);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
