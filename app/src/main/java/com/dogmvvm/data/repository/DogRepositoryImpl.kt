package com.dogmvvm.data.repository
import com.dogmvvm.data.remote.IDogRemoteApi
import com.dogmvvm.domain.repository.IDogRepository

class DogRepositoryImpl(
    private val api: IDogRemoteApi
) : IDogRepository {
    override suspend fun getDog() = api.getDog()
}