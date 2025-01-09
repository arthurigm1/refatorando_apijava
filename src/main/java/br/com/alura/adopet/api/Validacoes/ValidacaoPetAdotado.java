package br.com.alura.adopet.api.Validacoes;

import br.com.alura.adopet.api.Exception.ValidacaoException;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import jdk.jfr.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPetAdotado implements ValidacaoIE{


    @Autowired
    private PetRepository petRepository;

    public void validar(SolicitacaoAdocaodto dto){
        Pet pet = petRepository.getReferenceById(dto.idPet());
        if (pet.getAdotado() == true) {
            throw new ValidacaoException("Pet já foi adotado!");

        }

    }
}
