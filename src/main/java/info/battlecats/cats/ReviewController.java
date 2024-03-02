package info.battlecats.cats;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CrossOrigin(origins = "*")

public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @RequestMapping("/api/v1/reviews")
    public ResponseEntity<Review> createReview(@RequestBody Map<String,String> payload){
        return  new ResponseEntity<Review>(reviewService.createReview(payload.get("reviewBody"),payload.get("catID")),
        HttpStatus.CREATED);
    }

}
