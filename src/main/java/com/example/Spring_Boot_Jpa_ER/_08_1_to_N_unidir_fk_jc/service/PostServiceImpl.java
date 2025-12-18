package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.PostDtoResponse;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Post;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.mapper.PostMapper;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.model.PostModel;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service("PostServiceImpl")
public class PostServiceImpl implements PostService {

    private final PostMapper postMapper;
    private final PostRepository postRepository;

    public PostServiceImpl(
            PostMapper postMapper,
            PostRepository postRepository) {
        this.postMapper = postMapper;
        this.postRepository = postRepository;
    }

    @Override
    public PostDtoResponse createPost(PostDtoRequest request) {
        Post post = postRepository.save(
                postMapper.dtoCreateToEntity(request));
        return (post != null)
                ? new PostDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .post(PostModel.getModel(post))
                .build()
                : new PostDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public PostDtoResponse getAllPosts() {
        List<Post> list = postRepository.findAll();
        if (!list.isEmpty()) {
            List<PostModel> _list = new ArrayList<>();
            for (Post post : list)
                _list.add(PostModel.getModel(post));
            return new PostDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(PostDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .posts(_list)
                    .build();
        } else
            return new PostDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(PostDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .posts(Collections.emptyList())
                    .build();
    }

    @Override
    public PostDtoResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElse(null);
        return (post != null)
                ? new PostDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .post(PostModel.getModel(post))
                .build()
                : new PostDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public PostDtoResponse updatePostById(Long id, PostDtoRequest request) {
        Optional<Post> postOptional = postRepository.findById(id);
        Post post = postOptional.map(_post ->
                        postRepository.saveAndFlush(
                                postMapper.dtoUpdateByIdToEntity(
                                        id, request, _post)))
                .orElse(null);
        return (post != null)
                ? new PostDtoResponse.Builder()
                .status(HttpStatus.OK.value())
                .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                        .formatted(id))
                .post(PostModel.getModel(post))
                .build()
                : new PostDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }

    @Override
    public PostDtoResponse deletePostById(Long id) {
        if (postRepository.findById(id).isPresent()) {
            postRepository.deleteById(id);
            return new PostDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(PostDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new PostDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(PostDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
