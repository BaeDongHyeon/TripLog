package com.yeogi.triplog.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostRequestDTO {

    private String title;
    private String content;
    private MultipartFile attachFile;
    private List<MultipartFile> uploadFiles;
}
