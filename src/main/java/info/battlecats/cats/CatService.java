package info.battlecats.cats;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.mongodb.core.query.Criteria.*;

@Service
public class CatService {
    @Autowired
    private  CatRepository catRepository;
    public List<Cat> allCats(){
        return catRepository.findAll();
    }
    public List<Cat> singleCat(String catID){
        return catRepository.findByCatIDOrderByCatID(catID);
    }
    public List<Cat> catsAgainstEnemy(String enemy){
        return catRepository.findByEnemyTypesContainingIgnoreCase(enemy);
    }
    public void setRepository(MongoRepository mongoRepository){
        catRepository = (CatRepository) mongoRepository;
    }
    public CatRepository getRepository(){
        return catRepository;
    }
    public List<Cat> deleteCat(ObjectId id){
        return catRepository.deleteCatById(id);
    }
}
