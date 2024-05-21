package ma.gest_dentaire.Controller;


import ma.gest_dentaire.model.entity.Patient;
import ma.gest_dentaire.model.entity.Personne;
import ma.gest_dentaire.service.PatientService;
import ma.gest_dentaire.service.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    private PersonneService personneService;

    @PostMapping("/Patient/add")
    public ResponseEntity<Patient> addPatient(@RequestParam Integer personneId, @RequestBody Patient patient) {
        Patient addedPatient = patientService.addPatient(personneId, patient);
        if (addedPatient != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(addedPatient);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
