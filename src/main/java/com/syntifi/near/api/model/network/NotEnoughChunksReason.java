package com.syntifi.near.api.model.network;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
@Getter
@Setter
@JsonTypeName("NotEnoughChunks")
public class NotEnoughChunksReason implements Reason {
    @JsonProperty("produced")
    private long produced;

    @JsonProperty("expected")
    private long expected;
}