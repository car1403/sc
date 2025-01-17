package com.hd.order.common;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Body {
    private int state;
    private String result;
    private String massage;
    private Object data;
    private Object error;
    private Object errorCode;
}
