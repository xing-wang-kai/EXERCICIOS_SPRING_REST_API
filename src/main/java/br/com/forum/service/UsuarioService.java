package br.com.forum.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.forum.model.Usuario;
import br.com.forum.repository.UserRepository;
import br.com.forum.requests.UsuarioRequest;

@Service
public class UsuarioService {

	@Autowired
	private UserRepository userRepository;
	
	public void cadastrar(UsuarioRequest usuarioRequest) {
		
		Usuario usuario = usuarioRequest.toUsuario();
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		usuario.setSenha(encoder.encode(usuario.getSenha()));
		this.userRepository.save(usuario);
		
	}

	public Usuario buscarUsuarioPorId(Long id) {
		
		return this.userRepository.findById(id).get();
	}
}
