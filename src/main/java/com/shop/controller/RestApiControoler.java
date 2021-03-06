package com.shop.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.shop.entity.CertCity;
import com.shop.repository.CertRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("cert")
public class RestApiControoler {

    @Autowired
    CertRepository certRepository;

    @GetMapping("certname")
    public List<CertCity> city(@RequestParam(defaultValue = "정보처리기사") String certname) throws JsonProcessingException {
        return certRepository.myquerycert_name(certname);
    }
}
