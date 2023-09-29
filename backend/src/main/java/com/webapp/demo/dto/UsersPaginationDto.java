package com.webapp.demo.dto;

import com.webapp.demo.model.UserPublicDataOnly;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class UsersPaginationDto {
    List<UserPublicDataOnly> userList;
    int totalPages;
}
