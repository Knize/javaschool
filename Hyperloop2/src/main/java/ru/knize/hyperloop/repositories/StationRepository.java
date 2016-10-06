package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.entities.StationEntity;

/**
 * Created by knize on 04.10.16.
 */
public interface StationRepository extends JpaRepository<StationEntity, Integer> {

    StationEntity findById(int id);

}
