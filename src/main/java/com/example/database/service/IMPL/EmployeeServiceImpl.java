package com.example.database.service.IMPL;

import com.example.database.Model.dto.CustomerDTO;
import com.example.database.Model.response.ReportResponse;
import com.example.database.repository.EphoneNumberRepository;
import com.example.database.repository.custom.ReportReppository;
import com.example.database.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ReportReppository reportRepository;
    @Autowired
    private EphoneNumberRepository ephoneNumberRepository;

    public List<ReportResponse> generateServiceReport(String branchName) {
        List<Object[]> employeeReports = reportRepository.findEmployeeReports(branchName);
        List<ReportResponse> reportResponses = new ArrayList<>();

        for (Object[] employeeData : employeeReports) {
            Integer employeeId = (Integer) employeeData[0];

            // Lấy thông tin khách hàng liên quan đến nhân viên
            List<Object[]> customerReports = reportRepository.findCustomerReports(employeeId);
            List<CustomerDTO> customerDTOList = new ArrayList<>();
            for (Object[] customerData : customerReports) {
                CustomerDTO customerDTO = new CustomerDTO(
                        (Integer) customerData[0],
                        (String) customerData[1],
                        (String) customerData[2],
                        (String) customerData[3],
                        (String) customerData[4],
                        (String) customerData[5]
                );
                customerDTOList.add(customerDTO);
            }

            Date dateOfBirth = null;
            if (employeeData[4] instanceof java.sql.Timestamp) {
                dateOfBirth = new Date(((java.sql.Timestamp) employeeData[4]).getTime());
            }
            List<String> phoneNumbers = ephoneNumberRepository.findByEmployeeEntity_Eid(employeeId).stream()
                    .map(ephoneNumberEntity -> ephoneNumberEntity.getEphoneNumber())
                    .collect(Collectors.toList());

            // Tạo đối tượng ReportResponse
            ReportResponse reportResponse = new ReportResponse(
                    (Integer) employeeData[0],
                    (String) employeeData[1],
                    (String) employeeData[2],
                    (String) employeeData[3],
                    dateOfBirth,
                    phoneNumbers,
                    (String) employeeData[6],
                    (String) employeeData[7],
                    (String) employeeData[8],
                    customerDTOList
            );

            reportResponses.add(reportResponse);
        }

        return reportResponses;
    }
}
