package com.yeogi.triplog.dto;

import com.yeogi.triplog.domain.Post;
import com.yeogi.triplog.domain.UploadFile;
import lombok.Getter;

import java.util.List;

@Getter
public class PostResponseDTO {

    private final Long id;
    private final String title;
    private final String author;
    private final String content;
    private final int viewCount;
    private final int likeCount;
    private final UploadFile attachFile;
    private final List<UploadFile> uploadFiles;

    public PostResponseDTO(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
        this.viewCount = post.getViewCount();
        this.likeCount = post.getLikeCount();
        this.author = post.getMember().getNickname();
        this.attachFile = post.getAttachFile();
        this.uploadFiles = post.getUploadFiles();
    }
}
