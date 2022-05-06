package com.syntifi.near.api.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.syntifi.near.api.model.indexer.AccountIdList;
import com.syntifi.near.api.model.indexer.StakingDeposit;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.Response;

import java.io.IOException;
import java.util.List;

import static com.syntifi.near.api.service.NearIndexerServiceHelper.nearIndexerService;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Basic Service call testing
 *
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public class NearIndexerServiceTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(NearIndexerServiceTest.class);

    @Test
    void getAccountLikelyNFTs_valid() throws IOException {
        Response<AccountIdList> value = nearIndexerService.getAccountLikelyNFTs("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        AccountIdList dappsAccountList = value.body();

        assertNotNull(dappsAccountList);

        dappsAccountList.forEach(i -> LOGGER.debug("{}", i));
    }

    @Test
    void getAccountLikelyFTs_valid() throws IOException {
        Response<AccountIdList> value = nearIndexerService.getAccountLikelyFTs("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        AccountIdList accountIdList = value.body();

        assertNotNull(accountIdList);

        accountIdList.forEach(i -> LOGGER.debug("{}", i));
    }

    @Test
    void getStakingDeposits_valid() throws IOException {
        Response<List<StakingDeposit>> value = nearIndexerService.getStakingDeposits("wallet-test.testnet").execute();

        assertTrue(value.isSuccessful());

        List<StakingDeposit> stakingDeposits = value.body();

        assertNotNull(stakingDeposits);

        stakingDeposits.forEach(i -> LOGGER.debug("deposit: {} validator: {}", i.getDeposit(), i.getValidator()));
    }
}