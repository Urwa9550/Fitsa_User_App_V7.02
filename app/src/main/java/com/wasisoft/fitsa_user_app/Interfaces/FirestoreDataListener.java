package com.wasisoft.fitsa_user_app.Interfaces;

import com.wasisoft.fitsa_user_app.Model.Nutritionist_items;

import java.util.List;

public interface FirestoreDataListener {

    void onNutritionistDataFetched(List<Nutritionist_items> nutritionistList);

}
