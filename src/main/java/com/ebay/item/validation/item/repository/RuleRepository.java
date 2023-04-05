package com.ebay.item.validation.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebay.item.validation.item.record.RuleRecord;

@Repository
public interface RuleRepository extends JpaRepository<RuleRecord, Long>{

}
