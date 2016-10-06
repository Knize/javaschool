package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.entities.BranchEntity;
import ru.knize.hyperloop.repositories.BranchRepository;

import java.util.List;

/**
 * Created by knize on 04.10.16.
 */
@Service
public class BranchService {
    @Autowired
    BranchRepository branchRepository;

    @Transactional
    public List<BranchEntity> getBranches() {
        return branchRepository.findAll();
    }

    @Transactional
    public BranchEntity getBranchById(int branchId) {
        return branchRepository.findById(branchId);
    }


}
