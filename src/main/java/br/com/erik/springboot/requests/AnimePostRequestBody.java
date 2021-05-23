package br.com.erik.springboot.requests;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class AnimePostRequestBody {
    @NotBlank(message = "The name cannot be empty")
    private String name;
}
