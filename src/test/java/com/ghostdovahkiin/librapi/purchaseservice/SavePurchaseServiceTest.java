package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.SavePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseSaveBuilder.createPurchaseSave;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("SavePurchaseService implementation tests")
public class SavePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private SavePurchaseServiceImpl savePurchaseService;
    @Mock
    private GetUser getUser;
    @Mock
    private GetBook getBook;

    @BeforeEach
    public void setUp() {
        this.savePurchaseService = new SavePurchaseServiceImpl(purchaseRepository, getBook, getUser);
    }

    @Test
    @DisplayName("Should save an Purchase")
    void shouldSavePurchase() {
        this.savePurchaseService.insert(createPurchaseSave().build());
        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();
        assertAll("Purchase",
                () -> assertThat(result.getUser(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                () -> assertThat(result.getBooks(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949,")),
                () -> assertThat(result.getAmountToPay(), is(229.0)),
                () -> assertThat(result.getStatus(), is(Status.CLOSED)),
                () -> assertThat(result.getSpecificID(), is("5edc11dd-2017-4c20-9d89-cc96970435cb"))
        );
    }
}
