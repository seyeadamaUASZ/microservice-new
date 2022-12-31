package com.sid.gl;

import com.sid.gl.dto.InventoryResponse;
import com.sid.gl.repositories.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skucodes){
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
