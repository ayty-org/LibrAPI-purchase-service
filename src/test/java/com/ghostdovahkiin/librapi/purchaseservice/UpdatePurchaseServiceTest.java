package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.Purchase;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.UpdatePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseBuilder.createPurchase;
import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseUpdateBuilder.createPurchaseUpdate;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.hamcrest.core.Is.is;


@ExtendWith(MockitoExtension.class)
@DisplayName("Verify the implementation of UpdatePurchaseService TESTs")
class UpdatePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private UpdatePurchaseServiceImpl updatePurchaseService;

    @BeforeEach
    public void setUp() {
        this.updatePurchaseService = new UpdatePurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Should update a purchase")
    void shouldUpdatePurchase() {
        when(purchaseRepository.findById(123L)).thenReturn(Optional.of(createPurchase().id(123L).build()));
        updatePurchaseService.update(createPurchaseUpdate().status(Status.FINISHED).build(), 123L);

        ArgumentCaptor<Purchase> purchaseArgumentCaptor = ArgumentCaptor.forClass(Purchase.class);
        verify(purchaseRepository).save(purchaseArgumentCaptor.capture());

        Purchase result = purchaseArgumentCaptor.getValue();

        assertAll("Purchase", () -> assertThat(result.getStatus(), is(Status.FINISHED)));
    }
}
