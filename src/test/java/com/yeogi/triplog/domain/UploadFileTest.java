package com.yeogi.triplog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UploadFileTest {

    @Test
    public void givenValidData_whenCreateUploadFile_thenFileIsCreated() {
        // given
        String uploadFileName = "upload.txt";
        String storeFileName = "store.txt";

        // when
        UploadFile file = UploadFile.builder()
                .uploadFileName(uploadFileName)
                .storeFileName(storeFileName)
                .build();

        // then
        assertThat(file).isNotNull();
        assertThat(file.getUploadFileName()).isEqualTo(uploadFileName);
        assertThat(file.getStoreFileName()).isEqualTo(storeFileName);
    }
}