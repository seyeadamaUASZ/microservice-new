package com.sid.gl.repositories;

import com.sid.gl.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface InventoryRepository extends JpaRepository<Inventory,Long> {
    boolean findBySkucode(String skucode);
    List<Inventory> findBySkucodeIn(List<String> skucodes);

}
