package main;

import main.model.Casse;
import main.model.CasseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class CaseController {
    @Autowired
    private CasseRepository casseRepository;

    @GetMapping("/cases/")
    public List<Casse> list(){
        Iterable<Casse> casseIterable = casseRepository.findAll();
        ArrayList<Casse> casses = new ArrayList<>();
        for (Casse casse : casseIterable) {
            casses.add(casse);
        }
        return casses;
    }

    @PostMapping("/cases/")
    public int add(Casse c){
        return casseRepository.save(c).getId();
    }



    @GetMapping("/cases/{id}")
    public ResponseEntity getCase(@PathVariable int id){
        //Casse c = Storage.getCase(id);
        Optional<Casse> optionalCase = casseRepository.findById(id);
        if(!optionalCase.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return new ResponseEntity(optionalCase.get(), HttpStatus.OK);
    }

    @DeleteMapping("/cases/")
    public void deleteAllCases(){
        casseRepository.deleteAll();
    }

    @DeleteMapping("/cases/{id}")
    public ResponseEntity deleteCurrentCase(@PathVariable int id){
        if(!casseRepository.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        casseRepository.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping("/cases/{id}")
    public ResponseEntity putCurrentCase(@PathVariable int id){

        if(!casseRepository.findById(id).isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        Optional<Casse> optionalCasse = casseRepository.findById(id);
        Casse c = optionalCasse.get();
        //обновить сущность
        casseRepository.save(c);
        return new ResponseEntity(c, HttpStatus.OK);
    }


}
