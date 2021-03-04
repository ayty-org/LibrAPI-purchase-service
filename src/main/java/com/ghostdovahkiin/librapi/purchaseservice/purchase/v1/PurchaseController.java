package com.ghostdovahkiin.librapi.purchaseservice.purchase.v1;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseUpdateDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.DeletePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.GetPurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.ListPagePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.ListPurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.SavePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.UpdatePurchaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/v1/api/purchases")
public class PurchaseController {
    private final SavePurchaseService savePurchaseService;
    private final ListPurchaseService listPurchaseService;
    private final ListPagePurchaseService listPagePurchaseService;
    private final GetPurchaseService getPurchaseService;
    private final DeletePurchaseService deletePurchaseService;
    private final UpdatePurchaseService updatePurchaseService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody PurchaseSaveDTO purchaseSaveDTO) {
        savePurchaseService.insert(purchaseSaveDTO);
    }

    @GetMapping(value = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<PurchaseReturnDTO> findAll(){
        return listPurchaseService.findAll();
    }

    @GetMapping(value = "/")
    @ResponseStatus(HttpStatus.OK)
    public Page<PurchaseReturnDTO> findPageable(Pageable pageable) { return listPagePurchaseService.findPurchase(pageable);}

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PurchaseReturnDTO findOne(@PathVariable("id") Long id) { return getPurchaseService.findByID(id);}

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) { deletePurchaseService.delete(id);}

    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    @PutMapping(value = "/{id}")
    public void update(@Valid @RequestBody PurchaseUpdateDTO purchaseUpdateDTO, @PathVariable Long id) {
        updatePurchaseService.update(purchaseUpdateDTO, id);
    }
}
