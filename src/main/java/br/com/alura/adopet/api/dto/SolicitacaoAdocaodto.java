package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SolicitacaoAdocaodto(@NotNull Long idPet, Long idtutor, @NotBlank String motivo) {

}
