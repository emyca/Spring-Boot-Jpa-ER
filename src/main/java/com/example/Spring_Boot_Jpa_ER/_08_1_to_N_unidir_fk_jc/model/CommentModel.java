package com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.model;

import com.example.Spring_Boot_Jpa_ER._08_1_to_N_unidir_fk_jc.entity.Comment;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CommentModel {
    private Long id;
    private String consideration;

    public static CommentModel getModel(Comment comment) {
        CommentModel model = new CommentModel();
        model.setId(comment.getId());
        model.setConsideration(comment.getConsideration());
        return model;
    }

    public static List<CommentModel> getModel(List<Comment> commentList) {
        List<CommentModel> modelList = new ArrayList<>();
        if (commentList != null) {
            for (Comment comment : commentList) {
                CommentModel model = new CommentModel();
                model.setId(comment.getId());
                model.setConsideration(comment.getConsideration());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
