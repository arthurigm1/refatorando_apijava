package br.com.alura.adopet.api.Service;

import br.com.alura.adopet.api.Exception.ValidacaoException;
import br.com.alura.adopet.api.Validacoes.ValidacaoIE;
import br.com.alura.adopet.api.dto.SolicitacaoAdocaodto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AdocaoService {
    @Autowired
    private MailService mailService;

    @Autowired
    private AdocaoRepository repository;
    @Autowired
    private PetRepository petRepository;
    @Autowired
    private TutorRepository tutorRepository;
    @Autowired
    private List<ValidacaoIE> validacaoIES;

    public void solicitar(SolicitacaoAdocaodto dtoadocao) {
        Pet pet = petRepository.getReferenceById(dtoadocao.idPet());
        Tutor tutor = tutorRepository.getReferenceById(dtoadocao.idtutor());

        validacaoIES.forEach(validacao -> validacao.validar(dtoadocao));
for (ValidacaoIE validacao : validacaoIES) {
    validacao.validar(dtoadocao);
}
            Adocao adocao = new Adocao();
            adocao.setTutor(tutor);
            adocao.setPet(pet);
            adocao.setMotivo(dtoadocao.motivo());
            adocao.setData(LocalDateTime.now());
            adocao.setStatus(StatusAdocao.AGUARDANDO_AVALIACAO);

            repository.save(adocao);

            mailService.enviarEmail(
                    adocao.getPet().getAbrigo().getEmail(),
                    "Solicitacao de adocao",
                    "Solicitacao feita com sucesso!");

    }
    public void aprovar(SolicitacaoAdocaodto adocaodto) {
       Adocao adocao = repository.getReferenceById(adocaodto.idAdoc());
        adocao.setStatus(StatusAdocao.APROVADO);
        repository.save(adocao);
        mailService.enviarEmail(
                adocao.getTutor().getEmail(),
                "Adoção aprovada",
                "Parabéns " +adocao.getTutor().getNome() +"!\n\nSua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi aprovada.\nFavor entrar em contato com o abrigo " +adocao.getPet().getAbrigo().getNome() +" para agendar a busca do seu pet.");
    }


    public void reprovar(SolicitacaoAdocaodto adocaodto) {
        Adocao adocao = repository.getReferenceById(adocaodto.idAdoc());
        adocao.setStatus(StatusAdocao.REPROVADO);
        repository.save(adocao);
        mailService.enviarEmail(
                adocao.getTutor().getEmail(),
                "Adoção reprovada",
                "Olá " +adocao.getTutor().getNome() +"!\n\nInfelizmente sua adoção do pet " +adocao.getPet().getNome() +", solicitada em " +adocao.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")) +", foi reprovada pelo abrigo " +adocao.getPet().getAbrigo().getNome() +" com a seguinte justificativa: " +adocao.getJustificativaStatus());
    }


    }


