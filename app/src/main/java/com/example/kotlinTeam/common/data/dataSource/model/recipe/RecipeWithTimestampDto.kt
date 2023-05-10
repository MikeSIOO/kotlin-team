package com.example.kotlinTeam.common.data.dataSource.model.recipe

import com.google.firebase.Timestamp
import com.google.firebase.firestore.DocumentReference

data class RecipeWithTimestampDto (
    val recipe: DocumentReference? = null,
    val timestamp: Timestamp? = null
)