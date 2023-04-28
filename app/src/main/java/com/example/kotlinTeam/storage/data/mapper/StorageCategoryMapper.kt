//package com.example.kotlinTeam.storage.data.mapper
//
//import com.example.kotlinTeam.storage.data.api.model.StorageCategoryDto
//import com.example.kotlinTeam.storage.domain.model.StorageCategory
//import javax.inject.Inject
//
//class StorageCategoryMapper @Inject constructor() {
//    fun map(input: List<StorageCategoryDto>): List<StorageCategory> {
//        return input.map {
//            StorageCategory(
//                id = it.id,
//                name = it.name,
//                image = it.image
//            )
//        }
//    }
//}
