package info.battlecats.cats;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping
public class CatController {
    @Autowired
    private CatService catService;

    @GetMapping("/api/v1/cats")
    public ResponseEntity<List<Cat>> getAllcats(){
        return new ResponseEntity<List<Cat>>(catService.allCats(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Cat>> getSingleCat(@PathVariable String id){
        return new ResponseEntity<List<Cat>>(catService.singleCat(id), HttpStatus.OK);
    }

    @GetMapping("/enemy/{enemy}")
    public ResponseEntity<List<Cat>> getCatsAgainstEnemy(@PathVariable String enemy){
        return new ResponseEntity<List<Cat>>(catService.catsAgainstEnemy(enemy), HttpStatus.OK);
    }

    @GetMapping("/delete/{id}")
    public ResponseEntity<List<Cat>> getCatsAgainstEnemy(@PathVariable ObjectId id){
        return new ResponseEntity<List<Cat>>(catService.deleteCat(id), HttpStatus.OK);
    }

}
