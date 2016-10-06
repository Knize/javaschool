package ru.knize.hyperloop.services;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.knize.hyperloop.entities.CapsuleEntity;
import ru.knize.hyperloop.repositories.CapsuleRepository;

import java.util.List;

/**
 * Created by knize on 30.09.16.
 */
@Service
public class CapsuleService {

    @Autowired
    private CapsuleRepository capsuleRepository;

    @Transactional
    public CapsuleEntity getCapsuleById(int capsuleId){
        return capsuleRepository.findByCapsuleId(capsuleId);
    }

    @Transactional
    public List<CapsuleEntity> getCapsuleList() {
        return Lists.newArrayList(capsuleRepository.findAll());
    }

    @Transactional
    public CapsuleEntity addCapsule(int carSlots, int seatsNumber) {
        CapsuleEntity ce = new CapsuleEntity();
        ce.setCarSlots(carSlots);
        ce.setSeatsNumber(seatsNumber);

        ce = capsuleRepository.save(ce);
        capsuleRepository.flush();
        return ce;
    }


}
