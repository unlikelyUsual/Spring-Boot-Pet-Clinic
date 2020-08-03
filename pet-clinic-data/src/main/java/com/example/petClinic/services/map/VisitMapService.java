package com.example.petClinic.services.map;

import com.example.petClinic.model.Visit;
import com.example.petClinic.services.VisitService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","mapProfile"})
public class VisitMapService extends AbstractMapService<Visit,Long> implements VisitService {

    @Override
    public Set<Visit> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public void delete(Visit object) {
       super.delete(object);
    }

    @Override
    public Visit save(Visit object) {
        return super.save(object);
    }

    @Override
    public Visit findById(Long id) {
        return super.findById(id);
    }
}
