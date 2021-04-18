package com.example.marvelapp.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.data.retrofit.ApiInterface
import kotlinx.coroutines.delay
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException

private const val STARTING_PAGE = 0

class CharactersDataSource(
    private val apiInterface: ApiInterface,
    private val query: String
) : PagingSource<Int, Character>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Character> {
        val page = params.key ?: STARTING_PAGE

        return try {
            delay(1000)
            val response =
                if (!query.isEmpty()) apiInterface.getCharacterAsync(page * 10, query).await()
                else apiInterface.getCharactersAsync(page * 10).await()
            val characters = response.body()?.data?.results
            LoadResult.Page(
                data = characters!!,
                prevKey = if (page == STARTING_PAGE) null else page - 1,
                nextKey = if (characters.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }

    }

    override fun getRefreshKey(state: PagingState<Int, Character>): Int? {
        Timber.d(state.toString())
        return state.anchorPosition
    }

}
//class CharactersDataSource @Inject constructor(
//    private val api: ApiInterface,
//    private val query: String
//) : PageKeyedDataSource<Int, Character>() {
//    private val job = SupervisorJob()
//    private val scope = CoroutineScope(Dispatchers.Default + job)
//
//    override fun loadInitial(
//        params: LoadInitialParams<Int>,
//        callback: LoadInitialCallback<Int, Character>
//    ) {
//        fetch(0, callback, null)
//    }
//
//    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
//        fetch(params.key + 1, null, callback)
//    }
//
//    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
//        fetch(params.key - 1, null, callback)
//    }
//
//
//    private fun fetch(
//        pageKey: Int,
//        initialCallback: LoadInitialCallback<Int, Character>?,
//        callback: LoadCallback<Int, Character>?
//    ) {
//        scope.launch {
//            try {
//                val response = api.getCharactersAsync(offset = pageKey * 10, query = query).await()
//                when {
//                    response.isSuccessful -> {
//                        val listing = response.body()
//                        val personagens = listing?.data?.results?.map { it }
//                        val list = personagens ?: listOf()
//
//                        initialCallback?.onResult(list, null, pageKey)
//                        callback?.onResult(list, pageKey)
//                    }
//                    else -> {
//                        Timber.e("DataSource Failed")
//                    }
//                }
//            } catch (e: Exception) {
//                Timber.e(e)
//            }
//        }
//    }
//}
