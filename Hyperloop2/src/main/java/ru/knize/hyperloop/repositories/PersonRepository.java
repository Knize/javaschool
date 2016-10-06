package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.PersonEntity;
import ru.knize.hyperloop.entities.TrafficEntity;

/**
 * Created by knize on 05.10.16.
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Integer> {

    PersonEntity findById(int id);
}