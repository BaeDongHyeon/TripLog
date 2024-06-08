package com.yeogi.triplog.service;

import com.yeogi.triplog.domain.Post;
import com.yeogi.triplog.domain.UploadFile;
import com.yeogi.triplog.dto.PostRequestDTO;
import com.yeogi.triplog.dto.PostResponseDTO;
import com.yeogi.triplog.repository.MemberRepository;
import com.yeogi.triplog.repository.PostRepository;
import com.yeogi.triplog.util.FileStore;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final FileStore fileStore;

    @Transactional
    public Long create(String nickname, PostRequestDTO postRequestDTO) throws IOException {
        memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 닉네임입니다."));

        UploadFile attachFile = fileStore.storeFile(postRequestDTO.getAttachFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(postRequestDTO.getUploadFiles());

        Post post = Post.builder()
                .title(postRequestDTO.getTitle())
                .content(postRequestDTO.getContent())
                .attachFile(attachFile)
                .uploadFiles(uploadFiles)
                .build();

        return postRepository.save(post).getId();
    }

    @Transactional
    public Long update(Long postId, PostRequestDTO postRequestDTO, String nickname) throws IOException {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("수정하려는 게시글을 찾을 수 없습니다."));

        if (!findPost.getMember().getNickname().equals(nickname)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }

        UploadFile attachFile = fileStore.storeFile(postRequestDTO.getAttachFile());
        List<UploadFile> uploadFiles = fileStore.storeFiles(postRequestDTO.getUploadFiles());

        findPost.update(
                postRequestDTO.getTitle(),
                postRequestDTO.getContent(),
                attachFile,
                uploadFiles
        );
        return findPost.getId();
    }

    @Transactional
    public void delete(Long postId, String nickname) {
        Post findPost = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));
        if (findPost.getMember().getNickname().equals(nickname)) {
            throw new IllegalArgumentException("해당 게시글의 작성자가 아닙니다.");
        }
        postRepository.delete(findPost);
    }

    public List<PostResponseDTO> findAllDesc() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .map(PostResponseDTO::new)
                .collect(Collectors.toList());
    }
}
