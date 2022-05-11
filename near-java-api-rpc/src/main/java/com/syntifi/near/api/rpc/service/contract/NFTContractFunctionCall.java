package com.syntifi.near.api.rpc.service.contract;

import com.syntifi.near.api.common.model.common.Base64String;
import com.syntifi.near.api.rpc.model.identifier.Finality;

/**
 *
 */
public class NFTContractFunctionCall extends ContractFunctionCall {

    private static final String NFT_METADATA_METHOD_NAME = "nft_metadata";

    /**
     * @return a builder for NFT metadata
     */
    public static ContractFunctionCallBuilder builderForMetadata() {
        return new ContractFunctionCallBuilder()
                .finality(Finality.OPTIMISTIC)
                .methodName(NFT_METADATA_METHOD_NAME)
                .args(new Base64String(""));
    }
}