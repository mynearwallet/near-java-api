package com.syntifi.near.api.service.exception;

import java.io.Serializable;

import lombok.Data;

/**
 * Json RPC service error data
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Data
public class NearServiceErrorData implements Serializable {
    private String name;
    private NearServiceErrorData cause;
    private int code;
    private String message;
    private Serializable data;
}
