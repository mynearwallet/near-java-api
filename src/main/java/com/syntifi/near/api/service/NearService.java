package com.syntifi.near.api.service;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.JsonNode;
import com.googlecode.jsonrpc4j.JsonRpcHttpClient;
import com.googlecode.jsonrpc4j.JsonRpcMethod;
import com.googlecode.jsonrpc4j.JsonRpcParam;
import com.googlecode.jsonrpc4j.JsonRpcParamsPassMode;
import com.googlecode.jsonrpc4j.ProxyUtil;
import com.syntifi.near.api.model.identifier.Finality;
import com.syntifi.near.api.service.exception.NearServiceException;
import com.syntifi.near.api.service.exception.NearServiceExceptionResolver;

/**
 * RPC Service for the Near network
 * 
 * @author Alexandre Carvalho
 * @author Andre Bertolace
 * @since 0.0.1
 */
public interface NearService {

    /**
     * Queries network and returns block for given height or hash.
     * 
     * @param finality
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("block")
    public JsonNode getBlock(@JsonRpcParam("finality") Finality finality) throws NearServiceException;

    /**
     * Queries network and returns block for given height or hash.
     * 
     * @param blockHash
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("block")
    public JsonNode getBlock(@JsonRpcParam("block_id") String blockHash) throws NearServiceException;

    /**
     * Queries network and returns block for given height or hash.
     * 
     * @param blockHeight
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("block")
    public JsonNode getBlock(@JsonRpcParam("block_id") long blockHeight) throws NearServiceException;

    /**
     * Returns general status of a given node (sync status, nearcore node version,
     * protocol version, etc), and the current set of validators.
     * 
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("status")
    public JsonNode getNetworkStatus() throws NearServiceException;

    /**
     * Returns the current state of node network connections (active peers,
     * transmitted data, etc.)
     * 
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("network_info")
    public JsonNode getNetworkInfo() throws NearServiceException;

    /**
     * Queries active validators on the network returning details and the state of
     * validation on the blockchain.
     * 
     * @param blockHash
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "validators", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getNetworkValidationStatus(String blockHash) throws NearServiceException;

    /**
     * Queries active validators on the network returning details and the state of
     * validation on the blockchain.
     * 
     * @param blockHeight
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "validators", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getNetworkValidationStatus(long blockHeight) throws NearServiceException;

    /**
     * Returns gas price for a specific block_height or block_hash.
     * - Using [null] will return the most recent block's gas price.
     * 
     * @param blockHash
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "gas_price", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getGasPrice(String blockHash) throws NearServiceException;

    /**
     * Returns gas price for a specific block_height or block_hash.
     * 
     * @param blockHeight
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "gas_price", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getGasPrice(long blockHeight) throws NearServiceException;

    /**
     * Returns current genesis configuration.
     * 
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("EXPERIMENTAL_genesis_config")
    public JsonNode getGenesisConfig() throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     * 
     * @param finality
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    public JsonNode getProtocolConfig(@JsonRpcParam("finality") Finality finality) throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     * 
     * @param blockHash
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    public JsonNode getProtocolConfig(@JsonRpcParam("block_id") String blockHash) throws NearServiceException;

    /**
     * Returns most recent protocol configuration or a specific queried block.
     * Useful for finding current storage and transaction costs.
     * 
     * @param blockHeight
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod("EXPERIMENTAL_protocol_config")
    public JsonNode getProtocolConfig(@JsonRpcParam("block_id") long blockHeight) throws NearServiceException;

    /**
     * Sends a transaction and immediately returns transaction hash.
     * 
     * @param base64EncodedSignedTransaction
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "broadcast_tx_async", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode sendTransactionAsync(String base64EncodedSignedTransaction) throws NearServiceException;

    /**
     * Sends a transaction and waits until transaction is fully complete. (Has a 10
     * second timeout)
     * 
     * @param base64EncodedSignedTransaction
     * @return
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "broadcast_tx_commit", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode sendTransactionAwait(String base64EncodedSignedTransaction) throws NearServiceException;

    /**
     * Queries status of a transaction by hash and returns the final transaction
     * result.
     * 
     * @param transactionHash
     * @param senderAccountId
     * @return the final transaction result
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "tx", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getTransactionStatus(String transactionHash, String senderAccountId) throws NearServiceException;

    /**
     * Queries status of a transaction by hash, returning the final transaction
     * result and details of all receipts.
     * 
     * @param transactionHash
     * @param senderAccountId
     * @return the final transaction result and details of all receipts
     * @throws NearServiceException
     */
    @JsonRpcMethod(value = "EXPERIMENTAL_tx_status", paramsPassMode = JsonRpcParamsPassMode.ARRAY)
    public JsonNode getTransactionStatusWithReceipts(String transactionHash, String senderAccountId)
            throws NearServiceException;

    /**
     * Fetches a receipt by it's ID (as is, without a status or execution outcome)
     * 
     * @param receiptId
     * @return the receipt
     * @throws NearServiceException
     */
    @JsonRpcMethod("EXPERIMENTAL_receipt")
    public JsonNode getTransactionReceipt(@JsonRpcParam("receipt_id") String receiptId) throws NearServiceException;

    /**
     * NearService builder
     * 
     * @param url
     * @return
     * @throws MalformedURLException
     */
    public static NearService usingPeer(String url) throws MalformedURLException {
        Map<String, String> customHeaders = new TreeMap<>();
        customHeaders.put("Content-Type", "application/json");

        JsonRpcHttpClient client = new JsonRpcHttpClient(new URL("https://" + url), customHeaders);

        client.setExceptionResolver(new NearServiceExceptionResolver());

        return ProxyUtil.createClientProxy(NearService.class.getClassLoader(), NearService.class, client);
    }
}
