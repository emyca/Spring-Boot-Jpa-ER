package com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.model;

import com.example.Spring_Boot_Jpa_ER._10_N_to_1_unidir_fk_jc.entity.Opinion;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class OpinionModel {
    private Long id;
    private String consideration;

    public static OpinionModel getModel(Opinion opinion) {
        OpinionModel model = new OpinionModel();
        model.setId(opinion.getId());
        model.setConsideration(opinion.getConsideration());
        return model;
    }

    public static List<OpinionModel> getModel(List<Opinion> opinionList) {
        List<OpinionModel> modelList = new ArrayList<>();
        if (opinionList != null) {
            for (Opinion opinion : opinionList) {
                OpinionModel model = new OpinionModel();
                model.setId(opinion.getId());
                model.setConsideration(opinion.getConsideration());
                modelList.add(model);
            }
        }
        return modelList;
    }
}
