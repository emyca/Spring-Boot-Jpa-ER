package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoRequest;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.dto.CommentDtoResponse;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Comment;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Post;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.model.CommentModel;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.repository.CommentRepository;
import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.repository.PostRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("CommentServiceImpl")
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    public CommentServiceImpl(
            PostRepository postRepository,
            CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDtoResponse createCommentByPostId(Long id, CommentDtoRequest request)
            throws ResourceException {
        try {
            return postRepository.findById(id).map(post -> {
                Comment comment = new Comment();
                comment.setConsideration(request.consideration());
                post.getComments().add(comment);
                return new CommentDtoResponse.Builder()
                        .status(HttpStatus.OK.value())
                        .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                        .message(CommentDtoResponse
                                .Message.SUCCESS_CREATE_MSG.getMessage())
                        .postId(id)
                        .comment(CommentModel.getModel(
                                commentRepository.save(comment)))
                        .build();
            }).orElseThrow(() ->
                    new ResourceException(
                            "Post with id %s has not been found!"
                                    .formatted(id)));
        } catch (ResourceException e) {
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CommentDtoResponse getAllCommentsByPostId(Long id) {
        try {
            Post post = postRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Post with id %s has not been found!"
                                            .formatted(id)));
            List<Comment> list = new ArrayList<>(post.getComments());
            return (!list.isEmpty())
                    ? new CommentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CommentDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .postId(id)
                    .comments(CommentModel.getModel(list))
                    .build()
                    : new CommentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(CommentDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .postId(id)
                    .comments(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CommentDtoResponse getCommentById(Long id) {
        try {
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CommentDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .comment(CommentModel.getModel(
                            commentRepository.findById(id)
                                    .orElseThrow(() ->
                                            new ResourceException(
                                                    "Comment with id %s has not been found!"
                                                            .formatted(id)))))
                    .build();
        } catch (ResourceException e) {
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CommentDtoResponse updateCommentById(Long id, CommentDtoRequest request) {
        try {
            Comment comment = commentRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Comment with id %s has not been found!"
                                            .formatted(id)));
            comment.setConsideration(request.consideration());
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CommentDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .comment(CommentModel.getModel(
                            commentRepository.save(comment)))
                    .build();
        } catch (ResourceException e) {
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public CommentDtoResponse deleteCommentById(Long id) {
        if (commentRepository.findById(id).isPresent()) {
            commentRepository.deleteById(id);
            return new CommentDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(CommentDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        }
        return new CommentDtoResponse.Builder()
                .status(HttpStatus.NOT_FOUND.value())
                .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                .message(CommentDtoResponse
                        .Message.FAILURE_GET_BY_ID_MSG.getMessage()
                        .formatted(id))
                .build();
    }
}
