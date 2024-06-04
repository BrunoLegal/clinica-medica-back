package br.edu.imepac.services;

import br.edu.imepac.dtos.EspecialidadeCreateRequest;
import br.edu.imepac.dtos.EspecialidadeDto;
import br.edu.imepac.models.EspecialidadeModel;
import br.edu.imepac.repositories.EspecialidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EspecialidadeService {
    @Autowired
    private EspecialidadeRepository especialidadeRepository;
    public EspecialidadeDto save(EspecialidadeCreateRequest especialidadeCreateRequest){
        EspecialidadeModel especialidadeModel = new EspecialidadeModel();
        especialidadeModel.setDesc_especialidade(especialidadeCreateRequest.getDesc_especialidade());

        EspecialidadeModel savedEspecialidade = especialidadeRepository.save(especialidadeModel);

        EspecialidadeDto especialidadeDto = new EspecialidadeDto();
        especialidadeDto.setId(savedEspecialidade.getId());
        especialidadeDto.setDesc_especialidade(savedEspecialidade.getDesc_especialidade());

        return especialidadeDto;
    }

    public EspecialidadeDto findById(Long id){
        Optional<EspecialidadeModel> optionalEspecialidadeDto = especialidadeRepository.findById(id);
        if(optionalEspecialidadeDto.isPresent()){
            EspecialidadeModel especialidadeModel = optionalEspecialidadeDto.get();
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidadeModel.getId());
            especialidadeDto.setDesc_especialidade(especialidadeModel.getDesc_especialidade());
            return especialidadeDto;
        }else{
            return null;
        }

    }

    public List<EspecialidadeDto> findAll(){
        List<EspecialidadeModel> listaModel = especialidadeRepository.findAll();
        return listaModel.stream().map(especialidade -> {
            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(especialidade.getId());
            especialidadeDto.setDesc_especialidade(especialidade.getDesc_especialidade());
            return especialidadeDto;
        }).collect(Collectors.toList());

    }

    public void delete(Long id){
        especialidadeRepository.deleteById(id);
    }

    public EspecialidadeDto update (Long id, EspecialidadeDto especialidadeData){
        Optional<EspecialidadeModel> optionalEspecialidadeDto = especialidadeRepository.findById(id);
        if(optionalEspecialidadeDto.isPresent()){
            EspecialidadeModel especialidadeModel = optionalEspecialidadeDto.get();
            especialidadeModel.setDesc_especialidade(especialidadeData.getDesc_especialidade());

            EspecialidadeModel updatedModel = especialidadeRepository.save(especialidadeModel);

            EspecialidadeDto especialidadeDto = new EspecialidadeDto();
            especialidadeDto.setId(updatedModel.getId());
            especialidadeDto.setDesc_especialidade(updatedModel.getDesc_especialidade());
            return especialidadeDto;

        }else{
            return null;
        }

    }


}
