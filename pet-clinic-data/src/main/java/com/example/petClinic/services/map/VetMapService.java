package com.example.petClinic.services.map;

import com.example.petClinic.model.Speciality;
import com.example.petClinic.model.Vet;
import com.example.petClinic.services.SpecialityService;
import com.example.petClinic.services.VetService;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Profile({"default","mapProfile"})
public class VetMapService extends AbstractMapService<Vet, Long> implements VetService {

    private final SpecialityService specialityService;

    public VetMapService(SpecialityService specialityService) {
        this.specialityService = specialityService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Vet save(Vet object) {
        object.getSpecialitySet().forEach(speciality -> {
            if(speciality.getId() == null){
                Speciality savedSpeciality = specialityService.save(speciality);
                speciality.setId(savedSpeciality.getId());
            }
        });
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }
}
