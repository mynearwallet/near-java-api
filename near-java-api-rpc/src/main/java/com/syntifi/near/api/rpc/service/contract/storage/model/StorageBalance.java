package com.syntifi.near.api.rpc.service.contract.storage.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StorageBalance {
    private String total;
    private String available;
}
