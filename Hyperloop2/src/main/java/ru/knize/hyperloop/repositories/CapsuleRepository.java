package ru.knize.hyperloop.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.CapsuleEntity;


/**
 * Created by knize on 24.09.16.
 */

public interface CapsuleRepository extends JpaRepository<CapsuleEntity, Integer> {

    CapsuleEntity findByCapsuleId(int id);

}

