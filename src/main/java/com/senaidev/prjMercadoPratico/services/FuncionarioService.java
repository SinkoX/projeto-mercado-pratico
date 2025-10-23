package com.senaidev.prjMercadoPratico.services;

import com.senaidev.prjMercadoPratico.entities.Funcionario;
import com.senaidev.prjMercadoPratico.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    //Listar todos os funcionários
    public List<Funcionario> findAll() {
        return funcionarioRepository.findAll();
    }

    // Buscar funcionário por ID
    public Optional<Funcionario> findById(Long id) {
        return funcionarioRepository.findById(id);
    }

    // Buscar por cargo
    public List<Funcionario> findByCargo(String cargo) {
        return funcionarioRepository.findByCargoIgnoreCase(cargo);
    }

    // Buscar por nome 
    public List<Funcionario> findByNome(String nomeFuncionario) {
        return funcionarioRepository.findByNomeFuncionarioIgnoreCase(nomeFuncionario);
    }
    
    // Buscar por cpf
    public List<Funcionario> findByCpfFuncionario(String cpfFuncionario) {
        return funcionarioRepository.findByCpfFuncionario(cpfFuncionario);
    }

    // Cadastrar novo funcionário
    public Funcionario save(Funcionario funcionario) {
        return funcionarioRepository.save(funcionario);
    }

    //Atualizar funcionário
    public Funcionario update(Long id, Funcionario dadosAtualizados) {
        Optional<Funcionario> optionalFuncionario = funcionarioRepository.findById(id);
        if (optionalFuncionario.isPresent()) {
            Funcionario funcionarioExistente = optionalFuncionario.get();
            funcionarioExistente.setNomeFuncionario(dadosAtualizados.getNomeFuncionario());
            funcionarioExistente.setCargo(dadosAtualizados.getCargo());
            funcionarioExistente.setEmailFuncionario(dadosAtualizados.getEmailFuncionario());
            funcionarioExistente.setSenhaFuncionario(dadosAtualizados.getSenhaFuncionario());
            funcionarioExistente.setTelefoneFuncionario(dadosAtualizados.getTelefoneFuncionario());
            return funcionarioRepository.save(funcionarioExistente);
        } else {
            throw new RuntimeException("Funcionário não encontrado com ID: " + id);
        }
    }

    // Deletar funcionário por ID
    public void delete(Long id) {
        funcionarioRepository.deleteById(id);
    }

    // Verificar se e-mail já está cadastrado
    public boolean emailExiste(String emailFuncionario) {
        return funcionarioRepository.existsByEmailFuncionario(emailFuncionario);
    }
}
