package br.com.erik.springboot.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AnimePostRequestBody {
    @NotBlank(message = "The name cannot be empty")
    private String name;
}
