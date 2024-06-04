package br.edu.imepac.services;

import br.edu.imepac.dtos.UsuarioCreateRequest;
import br.edu.imepac.dtos.UsuarioDto;
import br.edu.imepac.models.MedicoModel;
import br.edu.imepac.models.UsuarioModel;
import br.edu.imepac.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
