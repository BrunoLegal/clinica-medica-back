package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    public MedicoDto salvarMedico(MedicoCreateRequest medicoCreateRequest){
        MedicoModel medicoModel = new MedicoModel();
        medicoModel.setCRM(medicoCreateRequest.getCrm());
        medicoModel.setNome(medicoCreateRequest.getNome());
        medicoModel.setEspecialidade(medicoCreateRequest.getEspecialidade());

        MedicoModel savedMedico = medicoRepository.save(medicoModel);

        MedicoDto medicoDto = new MedicoDto();
        medicoDto.setId(savedMedico.getId());
        medicoDto.setNome(savedMedico.getNome());
        medicoDto.setCrm(savedMedico.getCRM());
        medicoDto.setEspecialidade(savedMedico.getEspecialidade());

        return medicoDto;
    }

    public MedicoModel buscaMedicoId(Long id){
        return medicoRepository.findById(id).get();
    }

    public List<MedicoDto> buscaTodosMedicos(){
        List<MedicoModel> listaModel = medicoRepository.findAll();
        return listaModel.stream().map(medico -> {
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setNome(medico.getNome());
            medicoDto.setCrm(medico.getCRM());
            medicoDto.setId(medico.getId());
            medicoDto.setEspecialidade(medico.getEspecialidade());
            return medicoDto;
        }).collect(Collectors.toList());

    }

    public void removerMedico(MedicoModel medicoModel, Long id){
        medicoModel = buscaMedicoId(id);
        medicoRepository.delete(medicoModel);
    }


}
