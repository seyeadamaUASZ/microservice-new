package com.sid.gl;

import com.sid.gl.dto.InventoryResponse;
import com.sid.gl.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skucodes){
        log.info("Wait started");
         Thread.sleep(10000);
        log.info("Wait ended");
        return inventoryRepository.findBySkucodeIn(skucodes)
                .stream()
                .map(inventory ->
                    InventoryResponse.builder()
                            .skucode(inventory.getSkucode())
                            .isInStock(inventory.getQuantity() > 0)
                            .build()

                ).toList();

    }
}
