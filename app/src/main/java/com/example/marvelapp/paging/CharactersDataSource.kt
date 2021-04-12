package com.example.marvelapp.paging

import androidx.paging.PageKeyedDataSource
import com.example.marvelapp.data.model.Character
import com.example.marvelapp.data.retrofit.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import timber.log.Timber

class CharactersDataSource(
    private val api: ApiInterface,
    private val query: String
) : PageKeyedDataSource<Int, Character>() {
    private val job = SupervisorJob()
    private val scope = CoroutineScope(Dispatchers.Default + job)

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, Character>
    ) {
        fetch(0, callback, null)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        fetch(params.key + 1, null, callback)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Character>) {
        fetch(params.key - 1, null, callback)
    }


    private fun fetch(
        pageKey: Int,
        initialCallback: LoadInitialCallback<Int, Character>?,
        callback: LoadCallback<Int, Character>?
    ) {
        scope.launch {
            try {
                val response = api.getCharactersAsync(offset = pageKey * 10, query = query).await()
                when {
                    response.isSuccessful -> {
                        val listing = response.body()
                        val personagens = listing?.data?.results?.map { it }
                        val list = personagens ?: listOf()

                        initialCallback?.onResult(list, null, pageKey)
                        callback?.onResult(list, pageKey)
                    }
                    else -> {
                        Timber.e("DataSource Failed")
                    }
                }
            } catch (e: Exception) {
                Timber.e(e)
            }
        }
    }
}
