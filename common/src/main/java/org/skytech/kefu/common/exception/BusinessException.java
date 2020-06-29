package org.skytech.kefu.common.exception;

import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int code;
    private String message;
}
