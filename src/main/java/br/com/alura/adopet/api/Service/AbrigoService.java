package br.com.alura.adopet.api.Service;

import br.com.alura.adopet.api.Exception.ValidacaoException;
import br.com.alura.adopet.api.dto.SolicitacaoAbrigoDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AbrigoService {
    @Autowired
    private AbrigoRepository abrigoRepository;

    public void cadastrar(SolicitacaoAbrigoDto dto) {
        boolean cadastro = abrigoRepository.existsnometelefoneoremail(dto.telefone(), dto.email(), dto.nome());

        if (cadastro) {
              throw  new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
             abrigoRepository.save(new Abrigo(dto));

    }
}
