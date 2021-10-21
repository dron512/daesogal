package com.shop.repository;

import com.shop.entity.Cert;
import com.shop.entity.CertCity;
import org.json.JSONObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CertRepository extends JpaRepository<Cert, Long> {

    @Query(value = "select a.cert_name, a.take_year, sum(take_number) as take_numbers from cert a " +
            "group by cert_name, take_year " +
            "having cert_name = :cert_name " +
            "order by cert_name", nativeQuery = true)
    List<CertCity> myquerycert_name(String cert_name);

    @Query(value = "select distinct cert_name from cert", nativeQuery = true )
    List<Cert> getCertNames();
}