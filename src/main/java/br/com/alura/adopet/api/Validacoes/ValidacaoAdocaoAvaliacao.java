package br.com.alura.adopet.api.Validacoes;

import br.com.alura.adopet.api.Exception.ValidacaoException;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class ValidacaoAdocaoAvaliacao  implements  ValidacaoIE{

    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private AdocaoRepository adocaoRepository;

    public void validar(SolicitacaoAdocaodto dto){
        Tutor tutor = tutorRepository.getReferenceById(dto.idtutor());
        List<Adocao> adocoes = adocaoRepository.findAll();
        for (Adocao a : adocoes) {
            if (a.getTutor() == tutor && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO) {
                throw new ValidacaoException("Tutor já possui outra adoção aguardando avaliação!");
            }
        }
    }
}
