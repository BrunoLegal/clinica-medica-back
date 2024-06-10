package br.edu.imepac.services;

import br.edu.imepac.dtos.FuncionarioCreateRequest;
import br.edu.imepac.dtos.FuncionarioDto;
import br.edu.imepac.dtos.MedicoCreateRequest;
import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.models.FuncionarioModel;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public FuncionarioDto save(FuncionarioCreateRequest funcionarioCreateRequest){
        FuncionarioModel funcionarioModel = new FuncionarioModel();
        medicoModel.setCRM(medicoCreateRequest.getCrm());
        medicoModel.setNome(medicoCreateRequest.getNome());
        medicoModel.setEspecialidade(medicoCreateRequest.getEspecialidade());

        MedicoModel savedMedico = FuncionarioRepository.save(funcionarioModel);

        FuncionarioDto funcionarioDto = new FuncionarioDto();
        medicoDto.setId(savedMedico.getId());
        medicoDto.setNome(savedMedico.getNome());
        medicoDto.setCrm(savedMedico.getCRM());
        medicoDto.setEspecialidade(savedMedico.getEspecialidade());

        return medicoDto;
    }

    public MedicoDto findById(Long id){
        Optional<MedicoModel> optionalMedicoDto = medicoRepository.findById(id);
        if(optionalMedicoDto.isPresent()){
            MedicoModel medicoModel = optionalMedicoDto.get();
            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(medicoModel.getId());
            medicoDto.setNome(medicoModel.getNome());
            medicoDto.setCrm(medicoModel.getCRM());
            medicoDto.setEspecialidade(medicoModel.getEspecialidade());
            return medicoDto;
        }else{
            return null;
        }

    }

    public List<MedicoDto> findAll(){
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

    public void delete(Long id){
        medicoRepository.deleteById(id);
    }

    public MedicoDto update (Long id, MedicoDto medicoData){
        Optional<MedicoModel> optionalMedicoDto = medicoRepository.findById(id);
        if(optionalMedicoDto.isPresent()){
            MedicoModel medicoModel = optionalMedicoDto.get();
            medicoModel.setNome(medicoData.getNome());
            medicoModel.setCRM(medicoData.getCrm());
            medicoModel.setEspecialidade(medicoData.getEspecialidade());

            MedicoModel updatedModel = medicoRepository.save(medicoModel);

            MedicoDto medicoDto = new MedicoDto();
            medicoDto.setId(updatedModel.getId());
            medicoDto.setNome(updatedModel.getNome());
            medicoDto.setCrm(updatedModel.getCRM());
            medicoDto.setEspecialidade(updatedModel.getEspecialidade());
            return medicoDto;

        }else{
            return null;
        }

    }


}
