package com.dogmvvm.presentation.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.dogmvvm.commons.Resource
import com.dogmvvm.commons.Util.getPlaceholder
import com.dogmvvm.databinding.ActivityMainBinding
import com.dogmvvm.presentation.viewmodel.DogViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewBinding : ActivityMainBinding? = null
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        doObserveLoadDogImage()

        /* Reload page */
        viewBinding?.btnRefresh?.setOnClickListener {
            viewModel.onGetDogImage()
        }
    }

    private fun doObserveLoadDogImage(){
        viewModel.doObserveGogImage().observe(this){
            when(it){
                is Resource.Loading -> { viewBinding?.progressBar?.visibility = View.VISIBLE }
                is Resource.Success -> {
                    viewBinding?.progressBar?.visibility = View.GONE
                    it.data?.dogImageUrl?.let { it1 -> setDogImage(it1) }
                }
                is Resource.Error -> {
                    viewBinding?.progressBar?.visibility = View.GONE
                    Log.e("E->", "" + getString(it.resId!!))
                }
            }
        }
    }

    private fun setDogImage(dogImageUrl: String) {
        if(dogImageUrl.isNotEmpty()) {
            Picasso.get().load(dogImageUrl).placeholder(getPlaceholder())
                .into(viewBinding?.ivPhoto)
        }
    }

}