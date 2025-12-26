package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.OpinionDtoResponse;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Opinion;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.model.OpinionModel;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.repository.OpinionRepository;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.repository.StoryRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service("OpinionServiceImpl")
public class OpinionServiceImpl implements OpinionService {

    private final StoryRepository storyRepository;
    private final OpinionRepository opinionRepository;

    public OpinionServiceImpl(
            StoryRepository storyRepository,
            OpinionRepository opinionRepository) {
        this.storyRepository = storyRepository;
        this.opinionRepository = opinionRepository;
    }

    @Override
    public OpinionDtoResponse createOpinionByStoryId(Long id, OpinionDtoRequest request)
            throws ResourceException {
        try {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.SUCCESS_CREATE_MSG.getMessage())
                    .storyId(id)
                    .opinion(OpinionModel.getModel(
                            addOpinionByStoryId(id, request)))
                    .build();
        } catch (ResourceException e) {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    private Opinion addOpinionByStoryId(Long id, OpinionDtoRequest request) {
        return storyRepository.findById(id).map(story -> {
            Opinion opinion = new Opinion();
            opinion.setConsideration(request.consideration());
            opinion.setStory(story);
            return opinionRepository.save(opinion);
        }).orElseThrow(() ->
                new ResourceException(
                        "Story with id %s has not been found!"
                                .formatted(id)));
    }

    @Override
    public OpinionDtoResponse getAllOpinionsByStoryId(Long id)
            throws ResourceException {
        try {
            if (!storyRepository.existsById(id)) {
                throw new ResourceException(
                        "Story with id %s has not been found!"
                                .formatted(id));
            }
            List<Opinion> list = opinionRepository.findOpinionsByStoryId(id);
            return (!list.isEmpty())
                    ? new OpinionDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .storyId(id)
                    .opinions(OpinionModel.getModel(list))
                    .build()
                    : new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .storyId(id)
                    .opinions(Collections.emptyList())
                    .build();
        } catch (ResourceException e) {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public OpinionDtoResponse getOpinionById(Long id)
            throws ResourceException {
        try {
            Opinion opinion = opinionRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Opinion with id %s has not been found!"
                                            .formatted(id)));
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .opinion(OpinionModel.getModel(opinion))
                    .build();
        } catch (ResourceException e) {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public OpinionDtoResponse updateOpinionById(Long id, OpinionDtoRequest request)
            throws ResourceException {
        try {
            Opinion opinion = opinionRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Opinion with id %s has not been found!"
                                            .formatted(id)));
            opinion.setConsideration(request.consideration());
            Opinion _opinion = opinionRepository.save(opinion);
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .opinion(OpinionModel.getModel(_opinion))
                    .build();
        } catch (ResourceException e) {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public OpinionDtoResponse deleteOpinionById(Long id)
            throws ResourceException {
        try {
            opinionRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Opinion with id %s has not been found!"
                                            .formatted(id)));
            opinionRepository.deleteById(id);
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(OpinionDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new OpinionDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }
}
