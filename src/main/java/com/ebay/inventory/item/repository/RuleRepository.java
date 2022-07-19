package com.ebay.inventory.item.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ebay.inventory.item.record.RuleRecord;

@Repository
public interface RuleRepository extends JpaRepository<RuleRecord, Long>{

}
