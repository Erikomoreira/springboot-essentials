package br.com.erik.springboot.requests;

import lombok.Data;

@Data
public class AnimePutRequestBody {
    private Long id;
    private String name;
}
