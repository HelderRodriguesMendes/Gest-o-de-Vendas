package com.curso.api.gestaovendas.exception.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessagesError {
    private String msgUser;
    private String msgDev;
}
