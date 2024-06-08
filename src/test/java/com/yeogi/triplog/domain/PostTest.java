package com.yeogi.triplog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PostTest {

    @Test
    public void givenValidData_whenCreatePost_thenPostIsCreated() {
        // given
        String title = "Title";
        String content = "Content";
        String author = "Author";

        // when
        Post post = Post.builder()
                .title(title)
                .content(content)
                .build();

        // then
        assertThat(post).isNotNull();
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
        assertThat(post.getViewCount()).isZero();
        assertThat(post.getLikeCount()).isZero();
        assertThat(post.getAttachFile()).isNull();
        assertThat(post.getUploadFiles()).isEmpty();
        assertThat(post.getComments()).isEmpty();
    }

    @Test
    public void givenPost_whenIncrementViewCount_thenViewCountIsIncremented() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        // when
        post.incrementViewCount();

        // then
        assertThat(post.getViewCount()).isEqualTo(1);
    }

    @Test
    public void givenPost_whenIncrementLikeCount_thenLikeCountIsIncremented() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();

        // when
        post.incrementLikeCount();

        // then
        assertThat(post.getLikeCount()).isEqualTo(1);
    }

    @Test
    public void givenPostAndUploadFile_whenAddImageFile_thenFileIsAdded() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();
        UploadFile file = UploadFile.builder()
                .uploadFileName("upload.txt")
                .storeFileName("store.txt")
                .build();

        // when
        post.addUploadFiles(file);

        // then
        assertThat(post.getUploadFiles()).contains(file);
    }

    @Test
    public void givenPostAndComment_whenAddComment_thenCommentIsAdded() {
        // given
        Post post = Post.builder()
                .title("Title")
                .content("Content")
                .build();
        Comment comment = Comment.builder()
                .content("Nice post!")
                .author("Commenter")
                .build();

        // when
        post.addComment(comment);

        // then
        assertThat(post.getComments()).contains(comment);
        assertThat(comment.getPost()).isEqualTo(post);
    }
}
