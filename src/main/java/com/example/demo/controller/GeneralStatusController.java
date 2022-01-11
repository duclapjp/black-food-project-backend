package com.example.demo.controller;

import com.example.demo.model.GeneralStatus;
import com.example.demo.model.Message;
import com.example.demo.service.extend.IGeneralStatusService;
import com.example.demo.service.impl.GeneralStatusService;
import com.example.demo.service.impl.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/generalStatus")
public class GeneralStatusController {


    @Autowired
    private IGeneralStatusService generalStatusService;

    @GetMapping
    public ResponseEntity<List<GeneralStatus>> findAll(){
        return new ResponseEntity<>(generalStatusService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GeneralStatus> findById(@PathVariable Long id){
        return new ResponseEntity<>(generalStatusService.findById(id).get(),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<GeneralStatus> save(@RequestBody GeneralStatus generalStatus){
        return new ResponseEntity<>(generalStatusService.save(generalStatus),HttpStatus.CREATED);
    }
    @PutMapping
    public ResponseEntity<GeneralStatus> update( @RequestBody GeneralStatus generalStatus){
        return new ResponseEntity<>(generalStatusService.save(generalStatus),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> remove(@PathVariable Long id) {
        generalStatusService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
