package com.example.database.service;

import com.example.database.Model.response.ReportResponse;

import java.util.List;

public interface EmployeeService {
     List<ReportResponse> generateServiceReport(String branch);
}
