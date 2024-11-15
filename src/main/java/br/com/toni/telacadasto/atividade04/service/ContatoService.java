package br.com.toni.telacadasto.atividade04.service;

import br.com.toni.telacadasto.atividade04.model.Contato;
import br.com.toni.telacadasto.atividade04.persistence.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContatoService {

    @Autowired
    private ContatoRepository contatoRepository;

    public List<Contato> listarContatos() {
        return contatoRepository.findAll();
    }

    public void salvarContato(Contato contato) {
        contatoRepository.save(contato);
    }

    public void deletarContato(Long id) {
        contatoRepository.deleteById(id);
    }
}