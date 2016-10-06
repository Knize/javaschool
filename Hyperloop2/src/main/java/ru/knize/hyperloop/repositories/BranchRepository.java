package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.entities.CapsuleEntity;

/**
 * Created by knize on 04.10.16.
 */
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

    BranchEntity findById(int id);

}