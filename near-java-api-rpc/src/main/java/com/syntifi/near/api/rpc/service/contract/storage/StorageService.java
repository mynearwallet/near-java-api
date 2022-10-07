package com.syntifi.near.api.rpc.service.contract.storage;

import com.syntifi.near.api.common.model.key.PrivateKey;
import com.syntifi.near.api.common.model.key.PublicKey;
import com.syntifi.near.api.rpc.NearClient;
import com.syntifi.near.api.rpc.model.transaction.TransactionAwait;
import com.syntifi.near.api.rpc.service.contract.common.FunctionCallResult;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethod;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractMethodType;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameter;
import com.syntifi.near.api.rpc.service.contract.common.annotation.ContractParameterType;
import com.syntifi.near.api.rpc.service.contract.storage.model.StorageBalance;

import java.math.BigInteger;

public interface StorageService {
    @ContractMethod(type = ContractMethodType.VIEW, name = "storage_balance_of")
    FunctionCallResult<StorageBalance> getStorageBalanceOf(
            NearClient nearClient,
            String tokenId,
            @ContractParameter("account_id") String accountId
    );

    @ContractMethod(type = ContractMethodType.CALL, name = "storage_deposit")
    TransactionAwait callStorageDeposit(
            NearClient nearClient,
            String tokenId,
            @ContractParameter(value = "amount", type = {ContractParameterType.DEPOSIT}) BigInteger amount,
            @ContractParameter(value = "account_id", type = {ContractParameterType.ARGUMENT}) String receiverId,
            @ContractParameter(value = "registration_only", type = {ContractParameterType.ARGUMENT}) Boolean registrationOnly,
            @ContractParameter(value = "account_id", type = {ContractParameterType.ACCOUNT_ID}) String accountId,
            @ContractParameter(type = ContractParameterType.PUBLIC_KEY) PublicKey publicKey,
            @ContractParameter(type = ContractParameterType.PRIVATE_KEY) PrivateKey privateKey
    );
}
