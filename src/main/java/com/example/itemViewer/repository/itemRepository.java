package com.example.itemViewer.repository;

import com.example.itemViewer.model.item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "items",path = "items")
public interface itemRepository extends JpaRepository<item,Integer> {

    List<item> findByCls(String Cls);
    List<item>findByName(String Name);
    List<item>findByCountry(String Country);
    List<item>findByClsAndCountry(String Cls,String Country);

}
