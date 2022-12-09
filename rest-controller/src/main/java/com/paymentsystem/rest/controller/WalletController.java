package com.paymentsystem.rest.controller;

import com.paymentsystem.rest.dto.Transaction;
import com.paymentsystem.rest.port.WalletRestPort;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = "/api")
@Api(tags = {"wallet"}, value = "Wallet", produces = APPLICATION_JSON_VALUE)
public class WalletController {

    @Autowired
    private WalletRestPort walletRestPort;

    //    @RequestMapping(path = "/wallet", method = RequestMethod.POST)
    @RequestMapping(path = "/wallet/create-transaction", method = RequestMethod.GET)
    @ApiOperation(value = "Add new transaction", nickname = "createTransaction")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = Transaction.class),
            @ApiResponse(code = 500, message = "Error creating bank account")})
    public Transaction createTransaction() throws Exception {
        return walletRestPort.createTransaction();
    }

}
