package com.dogmvvm.domain.usecase
import com.dogmvvm.R
import com.dogmvvm.utility.Resource
import com.dogmvvm.domain.model.Dog
import com.dogmvvm.domain.model.DogResponseFilter
import com.dogmvvm.domain.repository.IDogRepository
import org.json.JSONObject
import javax.inject.Inject

class UseCaseDog @Inject constructor(
    private val repository: IDogRepository
) {

    suspend operator fun invoke() : Resource<Dog> {

        try{
            val response = repository.getDog()
            if (response.isSuccessful) {
                response.body()?.let {
                    val jsonObject = JSONObject(response.body().toString())
                    return  Resource.Success(DogResponseFilter.filterDogResponse(jsonObject))
                }
            } else {
                return Resource.Error("", R.string.lbl_something_wrong)
            }

        } catch (e:Exception){
            e.printStackTrace()
            return Resource.Error(e.localizedMessage.orEmpty(), R.string.lbl_something_wrong)
        }
        return Resource.Error("", R.string.lbl_something_wrong)

    }
}