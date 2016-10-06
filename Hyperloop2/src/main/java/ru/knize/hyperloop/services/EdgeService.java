package ru.knize.hyperloop.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.knize.hyperloop.entities.EdgeEntity;
import ru.knize.hyperloop.repositories.EdgeRepository;

import java.util.List;

/**
 * Created by knize on 06.10.16.
 */
@Service
public class EdgeService {

    @Autowired
    EdgeRepository edgeRepository;

    List<EdgeEntity> getEdges(){
        return edgeRepository.findAll();
    }

    List<EdgeEntity> getBranch(){
        return edgeRepository.findAll();
    }

}
