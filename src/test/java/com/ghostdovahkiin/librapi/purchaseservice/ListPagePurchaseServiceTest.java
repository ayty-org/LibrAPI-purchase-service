package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.feign.GetBook;
import com.ghostdovahkiin.librapi.purchaseservice.feign.GetUser;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.Status;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.ListPagePurchaseServiceImpl;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.utils.PurchaseJoinRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseReturnBuilder.createPurchaseReturn;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.anyString;
import static com.ghostdovahkiin.librapi.purchaseservice.builder.UserBuilder.createUser;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("Verify the implementation of ListPagePurchaseService TESTs")
public class ListPagePurchaseServiceTest {
    @Mock
    private PurchaseJoinRepository purchaseRepository;
    private ListPagePurchaseServiceImpl listPurchaseService;
    @Mock
    private GetBook getBook;
    @Mock
    private GetUser getUser;

    @BeforeEach
    void setUp() {
        this.listPurchaseService = new ListPagePurchaseServiceImpl(purchaseRepository, getBook, getUser);
    }

    @Test
    @DisplayName("Should return all purchases with pagination")
    void shouldFindAllPurchases() {

        when(purchaseRepository.findAllPurchase(getUser, getBook)).thenReturn(
                Stream.of(createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16a").status(Status.FINISHED).build(),
                        createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16b").status(Status.CLOSED).build(),
                        createPurchaseReturn().specificID("69661bd1-6092-4068-bd28-c60517f8a16c").status(Status.COMPLETED).build()).collect(Collectors.toList())
        );

        lenient().when(getUser.findBySpecificID(anyString())).thenReturn(createUser().build());

        Page<PurchaseReturnDTO> result = this.listPurchaseService.findPurchase(PageRequest.of(0,2));

        assertAll("Purchase",
                () -> assertThat(result.getNumber(), is(0)),
                () -> assertThat(result.getSize(), is(2)),
                () -> assertThat(result.getContent().get(0).getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16a")),
                () -> assertThat(result.getContent().get(0).getUser().getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16b")),
                () -> assertThat(result.getContent().get(0).getStatus(), is(Status.FINISHED)),
                () -> assertThat(result.getContent().get(0).getBooks().get(0).getSpecificID(), is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949")),
                () -> assertThat(result.getContent().get(0).getBooks().get(1).getSpecificID(), is("69661bd1-6092-4068-bd28-c60517f8a16s"))
        );

    }
}
