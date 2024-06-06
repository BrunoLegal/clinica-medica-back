package br.edu.imepac.services;

import br.edu.imepac.dtos.MedicoDto;
import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void delete (Long id){
        usuarioRepository.deleteById(id);
    }

    public UsuarioDto save(UsuarioCreateRequest usuarioCreateRequest){
        UsuarioModel usuarioModel = new UsuarioModel();
        usuarioModel.setLogin(usuarioCreateRequest.getLogin());
        usuarioModel.setSenha(usuarioCreateRequest.getSenha());

        UsuarioModel savedUsuario = usuarioRepository.save(usuarioModel);

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setId(savedUsuario.getId());
        usuarioDto.setSenha(savedUsuario.getSenha());
        usuarioDto.setLogin(savedUsuario.getLogin());
        return usuarioDto;
    }

    public UsuarioDto update(UsuarioDto usuarioData, Long id){
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);
        if(usuarioSearch.isPresent()){
            UsuarioModel usuarioModel = usuarioSearch.get();
            usuarioModel.setSenha(usuarioData.getSenha());
            usuarioModel.setLogin(usuarioData.getLogin());

            UsuarioModel updatedUsuario = usuarioRepository.save(usuarioModel);

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setLogin(updatedUsuario.getLogin());
            usuarioDto.setId(updatedUsuario.getId());
            usuarioDto.setSenha(updatedUsuario.getSenha());
            return usuarioDto;
        }else{
            return null;
        }
    }

    public List<UsuarioDto> findAll(){
        List<UsuarioModel> usuarioModels = usuarioRepository.findAll();
        return usuarioModels.stream().map(usuarios -> {
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setId(usuarios.getId());
            usuarioDto.setSenha(usuarios.getSenha());
            usuarioDto.setLogin(usuarios.getLogin());
            return usuarioDto;
        }).collect(Collectors.toList());
    }

    public UsuarioDto findById(Long id){
        Optional<UsuarioModel> usuarioSearch = usuarioRepository.findById(id);
        if(usuarioSearch.isPresent()){
            UsuarioModel usuarioModel = usuarioSearch.get();
            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setLogin(usuarioModel.getLogin());
            usuarioDto.setId(usuarioModel.getId());
            usuarioDto.setSenha(usuarioModel.getSenha());
            return usuarioDto;
        }else{
            return null;
        }
    }


}
