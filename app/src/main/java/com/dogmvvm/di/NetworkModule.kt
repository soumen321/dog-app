package com.dogmvvm.di
import com.dogmvvm.commons.RetrofitContainer
import com.dogmvvm.commons.ServiceUrl.BASEURL
import com.dogmvvm.data.remote.IDogRemoteApi
import com.dogmvvm.data.repository.DogRepositoryImpl
import com.dogmvvm.domain.repository.IDogRepository
import com.dogmvvm.domain.usecase.UseCaseDog
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesRemoteDogApi() : IDogRemoteApi =
        RetrofitContainer.getRetrofitBuilder(BASEURL)
            .build().create(IDogRemoteApi::class.java)

    @Singleton
    @Provides
    fun providesDogRepository(
        api: IDogRemoteApi
    ) : IDogRepository =
        DogRepositoryImpl(api)

    @Singleton
    @Provides
    fun providesGetDogUseCase(
        repo: IDogRepository) = UseCaseDog(repo)

}