package br.com.alura.adopet.api.Validacoes;

import br.com.alura.adopet.api.Exception.ValidacaoException;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ValidacaoAvaliacaoPet implements ValidacaoIE {

    @Autowired
    private PetRepository petRepository;
    @Autowired
    private AdocaoRepository adocaoRepository;
    public void validar(SolicitacaoAdocaodto dto){
        Pet pet = petRepository.getReferenceById(dto.idPet());
        List<Adocao> adocoes = adocaoRepository.findAll();
        for (Adocao a : adocoes) {
            if (a.getPet() == pet && a.getStatus() == StatusAdocao.AGUARDANDO_AVALIACAO)
                throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
            {

            }
        }
    }
}
