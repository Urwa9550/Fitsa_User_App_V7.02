package com.wasisoft.fitsa_user_app.Interfaces;

import com.wasisoft.fitsa_user_app.Model.GeneralUser;
import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;
import com.wasisoft.fitsa_user_app.Model.Trainer;

public interface FetchUserDataListener {

    void onNutritionistDataFetched(Nutritionist_items nutritionist);
    void onTrainerDataFetched(Trainer trainer);
}
