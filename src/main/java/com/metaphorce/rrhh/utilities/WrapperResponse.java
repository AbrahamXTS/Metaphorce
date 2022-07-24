package com.metaphorce.rrhh.utilities;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WrapperResponse<T> {
    
    private Boolean ok;
    private String message;
    private T body;
}
