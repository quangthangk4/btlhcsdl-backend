package com.example.database.converter;


import com.example.database.Model.response.BranchResponse;
import com.example.database.entity.BranchEntity;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
@Component
public class BranchConverter {

    public BranchResponse toDto(BranchEntity entity) {
        BranchResponse dto = new BranchResponse();
        dto.setBname(entity.getBname());
        dto.setBemail(entity.getBemail());
        dto.setBno(entity.getBno());
        dto.setBstreet(entity.getBstreet());
        dto.setBdistrict(entity.getBdistrict());
        dto.setBcity(entity.getBcity());
        dto.setBregion(entity.getBregion());

        // Chuyển đổi danh sách số fax
        if (entity.getBfaxnumber() != null) {
            dto.setBfaxNumbers(entity.getBfaxnumber().stream()
                    .map(bfax -> bfax.getBfax_number()) // Thay "getFaxNumber" bằng tên phương thức tương ứng trong BfaxNumberEntity
                    .collect(Collectors.toList()));
        }

        // Chuyển đổi danh sách số điện thoại
        if (entity.getBphonenumber() != null) {
            dto.setBphoneNumbers(entity.getBphonenumber().stream()
                    .map(bphone -> bphone.getBphoneNumber()) // Thay "getPhoneNumber" bằng tên phương thức tương ứng trong BphoneNumberEntity
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}
