package com.syntifi.near.api.rpc.service.storage;

import com.syntifi.near.api.rpc.service.contract.common.ContractClient;
import com.syntifi.near.api.rpc.service.contract.common.ContractMethodProxyClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.storage.StorageService;
import com.syntifi.near.api.rpc.service.contract.storage.model.StorageBalance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.syntifi.near.api.rpc.NearClientArchivalNetHelper.nearClient;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class StorageServiceTest {

    private static final StorageService service = ContractClient.createClientProxy(
            StorageService.class,
            new ContractMethodProxyClient()
    );

    @BeforeEach
    void wait_for_network() throws InterruptedException {
        Thread.sleep(1000);
    }

    @Test
    void testStorageBalance_should_be_null() {
        String tokenId = "ft.demo.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<StorageBalance> result = service.getStorageBalanceOf(nearClient, tokenId, accountId);
        assertNull(result.getResult());
    }

    @Test
    void testStorageBalance_should_be_not_null() {
        String tokenId = "wrap.testnet";
        String accountId = "syntifi-alice.testnet";
        FunctionCallResult<StorageBalance> result = service.getStorageBalanceOf(nearClient, tokenId, accountId);
        assertNotNull(result.getResult());
    }
}
