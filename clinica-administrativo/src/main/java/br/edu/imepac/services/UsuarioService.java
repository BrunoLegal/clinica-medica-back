package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    private static final Logger logger = LoggerFactory.getLogger(FuncionarioService.class);
    @Autowired
    private ModelMapper modelMapper;

    public void delete (Long id){
        logger.info("Deletando usúario com ID: {}", id);
        usuarioRepository.deleteById(id);
        logger.info("Usúario com ID: {} deletado com sucesso", id);

    }

    public UsuarioDto save(UsuarioCreateRequest usuarioCreateRequest){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel = modelMapper.map(usuarioCreateRequest, UsuarioModel.class);

        UsuarioModel savedUsuario = usuarioRepository.save(usuarioModel);
        logger.info("Usúario {} salvo", savedUsuario);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto = modelMapper.map(savedUsuario,UsuarioDto.class);
        return usuarioDto;
    }

    public UsuarioDto update(UsuarioDto usuarioData, Long id){
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);

        logger.info("Buscando usúario com id :{}", id);
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
            logger.info("Usúario de id: {} atualizado", id);
            return usuarioDto;
        }else{
            logger.warn("Usúario de id: {} não encontrado", id);
            return null;
        }
    }

    public List<UsuarioDto> findAll(){
        logger.info("Buscando usúarios: ");
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioModels.stream().map(usuarios -> {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto = modelMapper.map(usuarios, UsuarioDto.class);
            return usuarioDto;
        }).collect(Collectors.toList());
    }

    public UsuarioDto findById(Long id){
        logger.info("Buscando usúario de id: {}", id);
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);
        if(usuarioSearch.isPresent()){
            UsuarioModel usuarioModel = usuarioSearch.get();
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto = modelMapper.map(usuarioModel, UsuarioDto.class);
            logger.info("Usúario de id: {} encontrado:{}", id,usuarioModel);

            return usuarioDto;
        }else{
            logger.warn("Usúario de id:{} não encontrado", id);
            return null;
        }
    }


}
