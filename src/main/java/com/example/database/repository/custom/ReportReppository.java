package com.example.database.repository.custom;

import com.example.database.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportReppository extends JpaRepository<EmployeeEntity, Integer> {
    @Query("""
    SELECT e.eid, e.efirstName, e.elastName, e.eemail, e.edob, e.eno, e.estreet, e.edistrict, e.ecity
    FROM EmployeeEntity e
    WHERE e.branch.bname = :branchName
    """)
    List<Object[]> findEmployeeReports(@Param("branchName") String branchName);

    @Query("""
    SELECT c.cid, c.cfirstName, c.clastName, c.cemail, c.chomeAddress, c.cofficeAddress
    FROM CustomerEntity c
    WHERE c.employee.eid = :employeeId
    """)
    List<Object[]> findCustomerReports(@Param("employeeId") Integer employeeId);

}
