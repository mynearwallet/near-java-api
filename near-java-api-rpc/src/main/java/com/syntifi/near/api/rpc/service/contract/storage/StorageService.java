package com.syntifi.near.api.rpc.service.contract.storage;

import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.storage.model.StorageBalance;

public interface StorageService {
    @ContractMethod(type = ContractMethodType.VIEW, name = "storage_balance_of")
    FunctionCallResult<StorageBalance> getStorageBalanceOf(
            NearClient nearClient,
            String tokenId,
            @ContractParameter("account_id") String accountId
    );
}
