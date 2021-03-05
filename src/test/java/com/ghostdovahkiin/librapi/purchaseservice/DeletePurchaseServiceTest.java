package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.exceptions.PurchaseNotFoundException;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseRepository;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.DeletePurchaseServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseBuilder.createPurchase;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
@DisplayName("Delete Purchase Tests validation")
class DeletePurchaseServiceTest {
    @Mock
    private PurchaseRepository purchaseRepository;
    private DeletePurchaseServiceImpl deletePurchaseService;

    @BeforeEach
    public void setUp() {
        this.deletePurchaseService = new DeletePurchaseServiceImpl(purchaseRepository);
    }

    @Test
    @DisplayName("Should delete an Purchase")
    void shouldDeletePurchase() {
        lenient().when(purchaseRepository.findById(anyLong())).thenReturn(Optional.of(createPurchase().id(123L).build()));
        purchaseRepository.deleteById(123L);
        verify(purchaseRepository).deleteById(123L);
    }

    @Test
    @DisplayName("Should throw PurchaseNotFound when pass a nonexistent ID")
    void shouldThrowPurchaseNotFoundException() {
        lenient().when(purchaseRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(PurchaseNotFoundException.class, () -> this.deletePurchaseService.delete(1L));
    }
}
