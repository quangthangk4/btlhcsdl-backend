package com.example.database.controller;

import com.example.database.Model.dto.AccountDTO;
import com.example.database.Model.response.BranchResponse;
import com.example.database.Model.response.ReportResponse;
import com.example.database.converter.AccountConverrter;
import com.example.database.entity.AccTypeEntity;
import com.example.database.entity.AccountEntity;
import com.example.database.entity.CustomerEntity;
import com.example.database.repository.CustomerRepository;
import com.example.database.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "http://localhost:3000")
public class CustomerController {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    AccountService accountService;
    @Autowired
    AccTypeService accTypeService;
    @Autowired
    AccountConverrter AccountMapper;
    @Autowired
    EmployeeService employeeService;
    @Autowired
    BranchService branchService;
    @GetMapping("/{cid}/account")
    public ResponseEntity<List<?>> getaccount(@PathVariable("cid") int cid) {
            if(!customerRepository.existsById(cid)){
                return ResponseEntity.notFound().build();
            }
            else{
                 return ResponseEntity.ok(accountService.ListGetAccountById(cid));
            }
    }
    @GetMapping()
    public ResponseEntity<?> getcid() {
            return ResponseEntity.ok().body(customerService.getCustomer());
    }
    @PostMapping("/account")
    public ResponseEntity<String> createAccount(@RequestBody AccountDTO dto) {
        // Tìm CustomerEntity từ database
        CustomerEntity customer = customerService.findById(dto.getCustomerId());
        if (customer == null) {
            return ResponseEntity.badRequest().body("Customer not found");
        }

        // Tạo mới AccTypeEntity từ AccountDTO
        AccTypeEntity accType = new AccTypeEntity();

        // Cập nhật thông tin loại tài khoản (nếu có)
        accType = AccountConverrter.createAccType(dto, accType);
        accTypeService.save(accType);  // Giả sử bạn có một service để lưu

        // Tạo AccountEntity từ DTO
        AccountEntity accountEntity = AccountMapper.toEntity(dto, customer, accType);

        // Lưu thông tin vào cơ sở dữ liệu
        accountService.save(accountEntity);
        return ResponseEntity.ok("Account created successfully!");
    }
    @GetMapping("/service-report/{branch}")
    public List<ReportResponse> getServiceReport(@PathVariable String branch) {
        return  employeeService.generateServiceReport(branch);
    }
    @GetMapping("/branch")
    public List<BranchResponse> getAllBranch(){
        return branchService.getAllBranch();
    }

}
