package com.ghostdovahkiin.librapi.purchaseservice.purchase.v1;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.SavePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/purchases")
public class PurchaseController {
    private final SavePurchaseService savePurchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PurchaseSaveDTO purchaseSaveDTO) {
        savePurchaseService.insert(purchaseSaveDTO);
    }
}
