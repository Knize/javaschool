package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.BranchEntity;

/**
 * Created by knize on 04.10.16.
 */
public interface BranchRepository extends JpaRepository<BranchEntity, Integer> {

    BranchEntity findById(int id);

}