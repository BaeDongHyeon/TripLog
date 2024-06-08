package com.yeogi.triplog.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class CommentTest {

    @Test
    public void givenValidData_whenCreateComment_thenCommentIsCreated() {
        // given
        String content = "Nice post!";
        String author = "Commenter";

        // when
        Comment comment = Comment.builder()
                .content(content)
                .author(author)
                .build();

        // then
        assertThat(comment).isNotNull();
        assertThat(comment.getContent()).isEqualTo(content);
        assertThat(comment.getAuthor()).isEqualTo(author);
        assertThat(comment.getPost()).isNull();
    }

    @Test
    public void givenPostAndComment_whenSetPost_thenCommentHasPost() {
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
        assertThat(comment.getPost()).isEqualTo(post);
    }
}