package com.example.kotlinTeam.common.data.dataSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.kotlinTeam.profile.common.Constants.PAGE_SIZE
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.QuerySnapshot
import kotlinx.coroutines.tasks.await

class FirestorePagingSource<T : Any>(
    private val query: Query,
    private val mapper: suspend (DocumentSnapshot) -> T,
    private val filter: (suspend (T) -> Boolean)? = null
) : PagingSource<QuerySnapshot, T>() {

    override fun getRefreshKey(state: PagingState<QuerySnapshot, T>): QuerySnapshot? {
        return null
    }

    override suspend fun load(params: LoadParams<QuerySnapshot>): LoadResult<QuerySnapshot, T> {
        return try {
            val currentPage = params.key ?: query.get().await()
            val lastVisibleProduct = try {
                currentPage.documents[currentPage.size() - 1]
            } catch (e: java.lang.IndexOutOfBoundsException) {
                null
            }
            val nextPage = if (currentPage.isEmpty) null
            else query.startAfter(lastVisibleProduct).get().await()

            val items = mutableListOf<T>()
            for (document in currentPage.documents) {
                if (filter != null) {
                    val filteringItem = mapper(document)
                    if (filter?.let { it(filteringItem) } == true) {
                        items.add(filteringItem)
                    }
                } else {
                    items.add(mapper(document))
                }
            }

            LoadResult.Page(
                data = items,
                prevKey = null,
                nextKey = nextPage
            )
        } catch (e: FirebaseFirestoreException) {
            LoadResult.Error(e)
        }
    }
}
