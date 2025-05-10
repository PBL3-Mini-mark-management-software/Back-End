package com.example.backend.controller;

import com.example.backend.model.StockImportDetails;
import com.example.backend.repository.StockImportDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/StockImportDetails")
public class StockImportDetailsController {

    @Autowired
    private StockImportDetailsRepository detailsRepository;

    @GetMapping("/productIds/{importId}")
    public List<String> getProductIdsByImportId(@PathVariable String importId) {
        return detailsRepository.findProductIdsByImportId(importId);
    }

    @GetMapping("/details/{importId}")
    public List<StockImportDetails> getDetailsByImportId(@PathVariable String importId) {
        return detailsRepository.findByImportId(importId);
    }
}
