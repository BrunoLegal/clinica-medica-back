package br.edu.imepac.services;

import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {
    private MedicoRepository medicoRepository;
    public MedicoService(MedicoRepository medicoRepository){
        this.medicoRepository = medicoRepository;
    }

    public MedicoModel salvarMedico(MedicoModel medicoModel){
        return medicoRepository.save(medicoModel);
    }

    public MedicoModel buscaMedicoId(Long id){
        return medicoRepository.findById(id).get();
    }


}
