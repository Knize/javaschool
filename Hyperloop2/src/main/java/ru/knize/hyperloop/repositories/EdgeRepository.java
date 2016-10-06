package ru.knize.hyperloop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.entities.PersonEntity;

import java.util.List;

/**
 * Created by knize on 06.10.16.
 */
public interface EdgeRepository extends JpaRepository<EdgeEntity, Integer> {
    EdgeEntity findById(int id);
    List<EdgeEntity> BranchIdEquals(int branchId);
}