package com.ghostdovahkiin.librapi.purchaseservice;

import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseReturnDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseSaveDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.PurchaseUpdateDTO;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.DeletePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.GetPurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.ListPagePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.ListPurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.SavePurchaseService;
import com.ghostdovahkiin.librapi.purchaseservice.purchase.services.UpdatePurchaseService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;

import static com.ghostdovahkiin.librapi.purchaseservice.builder.PurchaseReturnBuilder.createPurchaseReturn;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = PurchaseControllerTest.class)
@DisplayName("Verify all the endpoints utilizing the implemented services")
class PurchaseControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GetPurchaseService getPurchaseService;
    @MockBean
    private ListPagePurchaseService listPagePurchaseService;
    @MockBean
    private ListPurchaseService listPurchaseService;
    @MockBean
    private SavePurchaseService savePurchaseService;
    @MockBean
    private UpdatePurchaseService updatePurchaseService;
    @MockBean
    private DeletePurchaseService deletePurchaseService;
    private static final String URL = "/v1/api/purchases";

    @Test
    @DisplayName("Should delete a purchase")
    void shouldDeletePurchase() throws Exception {
        mockMvc.perform(delete(URL + "/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(deletePurchaseService).delete(anyLong());
    }

    @Test
    @DisplayName("Returns a purchase by ID")
    void shouldReturnPurchase() throws Exception {

        when(getPurchaseService.findByID(anyLong())).thenReturn(createPurchaseReturn().id(1L).build());

        mockMvc.perform(get(URL + "/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.user.name", is("Pedro Henrique")))
                .andExpect(jsonPath("$.user.age", is(21)))
                .andExpect(jsonPath("$.user.phone", is("+5583986862911")))
                .andExpect(jsonPath("$.user.specificID", is("5e914dac-a434-4622-afa6-9284de58d7e3")))
                .andExpect(jsonPath("$.books[0].title", is("Percy Jacksu")))
                .andExpect(jsonPath("$.books[0].availableQuantity", is(500)))
                .andExpect(jsonPath("$.books[0].isbn", is("978-8-5-8057543-9")))
                .andExpect(jsonPath("$.books[0].author", is("John Wick")))
                .andExpect(jsonPath("$.books[0].sellPrice", is(29.5)))
                .andExpect(jsonPath("$.books[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949"))
        );
        verify(getPurchaseService).findByID(anyLong());
    }

    @Test
    @DisplayName("Should return all purchases without pagination")
    void shouldReturnAllPurchases() throws Exception {

        when(listPurchaseService.findAll()).thenReturn(Lists.newArrayList(
                createPurchaseReturn().id(123L).build(), createPurchaseReturn().id(2L).build()
        ));

        mockMvc.perform(get(URL + "/all")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[*]", hasSize(2)))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.user.name", is("Pedro Henrique")))
                .andExpect(jsonPath("$.user.age", is(21)))
                .andExpect(jsonPath("$.user.phone", is("+5583986862911")))
                .andExpect(jsonPath("$.user.specificID", is("5e914dac-a434-4622-afa6-9284de58d7e3")))
                .andExpect(jsonPath("$.books[0].title", is("Percy Jacksu")))
                .andExpect(jsonPath("$.books[0].availableQuantity", is(500)))
                .andExpect(jsonPath("$.books[0].isbn", is("978-8-5-8057543-9")))
                .andExpect(jsonPath("$.books[0].author", is("John Wick")))
                .andExpect(jsonPath("$.books[0].sellPrice", is(29.5)))
                .andExpect(jsonPath("$.books[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949"))
        );

        verify(listPurchaseService).findAll();
    }

    @Test
    @DisplayName("Should return all purchases with pagination")
    void shouldReturnAllPurchasesPaginate() throws Exception {

        Page<PurchaseReturnDTO> purchaseReturnDTOS = new PageImpl<>(Collections.singletonList(createPurchaseReturn().id(1L).build()));
        Pageable pageable = PageRequest.of(0, 2);
        when(listPagePurchaseService.findPurchase(pageable)).thenReturn(purchaseReturnDTOS);

        mockMvc.perform(get(URL + "/?page=0&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].id", is(1)))
                .andExpect(jsonPath("$.content[0].user.name", is("Pedro Henrique")))
                .andExpect(jsonPath("$.content[0].user.age", is(21)))
                .andExpect(jsonPath("$.content[0].user.phone", is("+5583986862911")))
                .andExpect(jsonPath("$.content[0].user.specificID", is("5e914dac-a434-4622-afa6-9284de58d7e3")))
                .andExpect(jsonPath("$.content[0].books[0].title", is("Percy Jacksu")))
                .andExpect(jsonPath("$.content[0].books[0].availableQuantity", is(500)))
                .andExpect(jsonPath("$.content[0].books[0].isbn", is("978-8-5-8057543-9")))
                .andExpect(jsonPath("$.content[0].books[0].author", is("John Wick")))
                .andExpect(jsonPath("$.content[0].books[0].sellPrice", is(29.5)))
                .andExpect(jsonPath("$.content[0].books[0].specificID", is("fe07d7bb-2cac-4c47-b9f0-19aa2df60949"))
        );
        verify(listPagePurchaseService).findPurchase(pageable);
    }

    @Test
    @DisplayName("Inserts an purchase")
    void shouldSavePurchase() throws Exception {
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(readJson("purchaseSaveDTO.json")))
                .andDo(print())
                .andExpect(status().isCreated());

        verify(savePurchaseService).insert(any(PurchaseSaveDTO.class));
    }

    @Test
    @DisplayName("Should edit purchase status")
    void shouldEditPurchase() throws Exception {
        mockMvc.perform(put(URL + "/{id}", 1L)
                .content(readJson("loanUpdate.json"))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(updatePurchaseService).update(any(PurchaseUpdateDTO.class),eq(1L));
    }

    public static String readJson(String file) throws Exception {
        byte[] bytes = Files.readAllBytes(Paths.get("src/test/java/resources/json/" + file).toAbsolutePath());
        return new String(bytes);
    }
}

