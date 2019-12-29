package org.montadhr.stockManagement.dao;

import org.montadhr.stockManagement.entity.Machine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("MachineDao")
public interface MachineDao extends JpaRepository<Machine, Long> {

}
