package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.service;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoRequest;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.dto.StoryDtoResponse;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Story;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.mapper.StoryMapper;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.model.StoryModel;
import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.repository.StoryRepository;
import com.example.Spring_Boot_Jpa_ER.common.exception.ResourceException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service("StoryServiceImpl")
public class StoryServiceImpl implements StoryService {

    private final StoryMapper storyMapper;
    private final StoryRepository storyRepository;

    public StoryServiceImpl(
            StoryMapper storyMapper,
            StoryRepository storyRepository) {
        this.storyMapper = storyMapper;
        this.storyRepository = storyRepository;
    }

    @Override
    public StoryDtoResponse createStory(StoryDtoRequest request) {
        Story story = storyRepository.save(
                storyMapper.dtoCreateToEntity(request));
        return (story != null)
                ? new StoryDtoResponse.Builder()
                .status(HttpStatus.CREATED.value())
                .reasonPhrase(HttpStatus.CREATED.getReasonPhrase())
                .message(StoryDtoResponse
                        .Message.SUCCESS_CREATE_MSG.getMessage())
                .story(StoryModel.getModel(story))
                .build()
                : new StoryDtoResponse.Builder()
                .status(HttpStatus.NO_CONTENT.value())
                .reasonPhrase(HttpStatus.NO_CONTENT.getReasonPhrase())
                .message(StoryDtoResponse
                        .Message.FAILURE_CREATE_MSG.getMessage())
                .build();
    }

    @Override
    public StoryDtoResponse getAllStories() {
        List<Story> list = storyRepository.findAll();
        if (!list.isEmpty()) {
            List<StoryModel> _list = new ArrayList<>();
            for (Story story : list)
                _list.add(StoryModel.getModel(story));
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StoryDtoResponse
                            .Message.SUCCESS_GET_LIST_MSG.getMessage())
                    .stories(_list)
                    .build();
        } else
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(StoryDtoResponse
                            .Message.FAILURE_GET_LIST_MSG.getMessage())
                    .stories(Collections.emptyList())
                    .build();
    }

    @Override
    public StoryDtoResponse getStoryById(Long id)
            throws ResourceException {
        try {
            Story story = storyRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Story with id %s has not been found!"
                                            .formatted(id)));
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StoryDtoResponse
                            .Message.SUCCESS_GET_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .story(StoryModel.getModel(story))
                    .build();
        } catch (ResourceException e) {
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StoryDtoResponse updateStoryById(Long id, StoryDtoRequest request)
            throws ResourceException {
        try {
            Story story = storyRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Story with id %s has not been found!"
                                            .formatted(id)));
            Story _story = storyRepository.save(storyMapper
                    .dtoUpdateByIdToEntity(request, story));
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StoryDtoResponse
                            .Message.SUCCESS_UPDATE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .story(StoryModel.getModel(_story))
                    .build();
        } catch (ResourceException e) {
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        }
    }

    @Override
    public StoryDtoResponse deleteStoryById(Long id)
            throws ResourceException, DataIntegrityViolationException {
        try {
            storyRepository.findById(id)
                    .orElseThrow(() ->
                            new ResourceException(
                                    "Story with id %s has not been found!"
                                            .formatted(id)));
            // Can throw DataIntegrityViolationException
            storyRepository.deleteById(id);

            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.OK.value())
                    .reasonPhrase(HttpStatus.OK.getReasonPhrase())
                    .message(StoryDtoResponse
                            .Message.SUCCESS_DELETE_BY_ID_MSG.getMessage()
                            .formatted(id))
                    .build();
        } catch (ResourceException e) {
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.NOT_FOUND.value())
                    .reasonPhrase(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .message(e.getMessage())
                    .build();
        } catch (DataIntegrityViolationException e) {
            return new StoryDtoResponse.Builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .reasonPhrase(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .message("Can't delete the Story with id " + id + ". " +
                            "Referential integrity constraint violation. " +
                            "First delete Opinion(s) associated with this Story!")
                    .build();
        }
    }
}
