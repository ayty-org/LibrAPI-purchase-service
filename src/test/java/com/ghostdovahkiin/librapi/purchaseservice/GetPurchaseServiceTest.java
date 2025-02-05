package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.GetPurchaseServiceImpl;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.utils.PurchaseJoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseReturnBuilder.createPurchaseReturn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static com.ghostdovahkiin.librapi.purchaseservice.builder.UserBuilder.createUser;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Verify the implementation of GetPurchaseService TESTs")
public class GetPurchaseServiceTest {
    @Mock
    private PurchaseJoinRepository purchaseRepository;
    private GetPurchaseServiceImpl listPurchaseService;
    @Mock
    private GetBook getBook;
    @Mock
    private GetUser getUser;

    @BeforeEach
    void setUp() {
        this.listPurchaseService = new GetPurchaseServiceImpl(purchaseRepository, getBook, getUser);
    }

    @Test
    @DisplayName("Should return one purchase")
    void shouldFindOnePurchase() {

        when(purchaseRepository.findAllPurchase(getUser, getBook)).thenReturn(
                Stream.of(createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16a").status(Status.FINISHED).build(),
                        createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16b").status(Status.CLOSED).build(),
                        createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16c").status(Status.COMPLETED).build()).collect(Collectors.toList())
        );

        lenient().when(getUser.findBySpecificID(anyString())).thenReturn(createUser().build());

        PurchaseReturnDTO result = this.listPurchaseService.findByID(123L);

        assertAll("Purchase",
                () -> assertThat(result.getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16a")),
                () -> assertThat(result.getUser().getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                () -> assertThat(result.getStatus(), is(Status.FINISHED)),
                () -> assertThat(result.getBooks().get(0).getSpecificID(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")),
                () -> assertThat(result.getBooks().get(1).getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16s"))
        );
        verify(purchaseRepository).findAllPurchase(getUser, getBook);
    }
}
