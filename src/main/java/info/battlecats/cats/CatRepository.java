package info.battlecats.cats;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CatRepository extends MongoRepository<Cat, ObjectId> {

    List<Cat> findByCatIDOrderByCatID(String catID);
    List<Cat> findByEnemyTypesContainingIgnoreCase(String enemy);
    List<Cat> deleteCatById(ObjectId id);
}
