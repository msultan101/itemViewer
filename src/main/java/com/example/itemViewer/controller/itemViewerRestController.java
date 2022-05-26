package com.example.itemViewer.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import com.example.itemViewer.repository.itemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.itemViewer.model.item;


@RestController
@RequestMapping("/api")
public class itemViewerRestController {

    @Autowired
    itemRepository itemRepo;
    @GetMapping(value = "/getItems")
    public ResponseEntity<List<item>> getItems(@RequestParam(required = false) String country ,@RequestParam(required = false) String cls) {
        try {
            List<item> items = new ArrayList<item>();
            if (country == null&& cls == null) {
                items.addAll(itemRepo.findAll());
            }
            else if (country != null&& cls == null) {
                items.addAll(itemRepo.findByCountry(country));
            }
            else if (country == null&& cls != null) {
                items.addAll(itemRepo.findByCls(cls));
            }
            else{
                items.addAll(itemRepo.findByClsAndCountry(cls,country));
            }
            if (items.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/items")
    public ResponseEntity<List<item>> createTutorial(@RequestBody List<item> items) {
        try {
            List<item> _items = new ArrayList<>();
            for(item current_item : items){
                item _item = itemRepo.save(new item(current_item.getName(), current_item.getCls(), current_item.getCountry()));
                _items.add(_item);
            }
            return new ResponseEntity<>(_items, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/items")
    public ResponseEntity<HttpStatus> deleteAllTutorials() {
        try {
            itemRepo.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value = "/getItem/id/{id}")
    public ResponseEntity<item> geIitem(@PathVariable(value = "id") Integer id) {
        Optional<item> itemData = itemRepo.findById(id);
        if (itemData.isPresent()) {
            return new ResponseEntity<>(itemData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/items/{id}")
    public ResponseEntity<item> updateTutorial(@PathVariable("id") Integer id, @RequestBody item Item) {
        Optional<item> itemData = itemRepo.findById(id);
        if (itemData.isPresent()) {
            item _item = itemData.get();
            _item.setName(Item.getName());
            _item.setCountry(Item.getCountry());
            _item.setCls(Item.getCls());
            return new ResponseEntity<>(itemRepo.save(_item), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/items/{id}")
    public ResponseEntity<HttpStatus> deleteTutorial(@PathVariable("id") Integer id) {
        try {
            itemRepo.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping(value = "/getItem/name/{name}")
    public ResponseEntity<item> geIitem(@PathVariable(value = "name") String name) {
        List <item> itemData = itemRepo.findByName(name);
        if (!itemData.isEmpty()) {
            return new ResponseEntity<>(itemData.get(0), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
