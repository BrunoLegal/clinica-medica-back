package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ModelMapper modelMapper;

    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto save(UsuarioCreateRequest usuarioCreateRequest){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel = modelMapper.map(usuarioCreateRequest, UsuarioModel.class);

        UsuarioModel savedUsuario = usuarioRepository.save(usuarioModel);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto = modelMapper.map(savedUsuario,UsuarioDto.class);
        return usuarioDto;
    }

    public UsuarioDto update(UsuarioDto usuarioData, Long id){
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);
        if(usuarioSearch.isPresent()){
            UsuarioModel usuarioModel = usuarioSearch.get();
            usuarioModel.setLogin(usuarioData.getLogin());
            usuarioModel.setSenha(usuarioData.getSenha());
            usuarioModel.setCadastroPaciente(usuarioData.getCadastroPaciente());
            usuarioModel.setCadastroFuncionario(usuarioData.getCadastroFuncionario());
            usuarioModel.setCadastroUsuario(usuarioData.getCadastroUsuario());
            usuarioModel.setCadastroEspecialidade(usuarioData.getCadastroEspecialidade());
            usuarioModel.setCadastroConvenio(usuarioData.getCadastroConvenio());
            usuarioModel.setCadastroMedico(usuarioData.getCadastroMedico());
            usuarioModel.setAgendamentoConsulta(usuarioData.getAgendamentoConsulta());
            usuarioModel.setCancelamentoConsulta(usuarioData.getCancelamentoConsulta());
            usuarioModel.setModuloAdministrativo(usuarioData.getModuloAdministrativo());
            usuarioModel.setModuloAgendamento(usuarioData.getModuloAgendamento());
            usuarioModel.setModuloAtendimento(usuarioData.getModuloAtendimento());
            UsuarioModel updatedUsuario = usuarioRepository.save(usuarioModel);
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto = modelMapper.map(updatedUsuario, UsuarioDto.class);
            return usuarioDto;
        }else{
            return null;
        }
    }

    public List<UsuarioDto> findAll(){
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioModels.stream().map(usuarios -> {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto = modelMapper.map(usuarios, UsuarioDto.class);
            return usuarioDto;
        }).collect(Collectors.toList());
    }

    public UsuarioDto findById(Long id){
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);
        if(usuarioSearch.isPresent()){
            UsuarioModel usuarioModel = usuarioSearch.get();
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto = modelMapper.map(usuarioModel, UsuarioDto.class);
            return usuarioDto;
        }else{
            return null;
        }
    }


}
