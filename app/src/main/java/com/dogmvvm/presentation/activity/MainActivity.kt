package com.dogmvvm.presentation.activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.dogmvvm.R
import com.dogmvvm.utility.Resource
import com.dogmvvm.utility.ImageUtil.getPlaceholder
import com.dogmvvm.databinding.ActivityMainBinding
import com.dogmvvm.presentation.viewmodel.DogViewModel
import com.dogmvvm.utility.api_service.Connectivity
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

/* Dog App */

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var viewBinding : ActivityMainBinding? = null
    private val viewModel: DogViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding?.root)

        onSetUpViews()

        /* check internet connection */
        if(Connectivity.hasInternetConnection(this)) {
            doObserveLoadDogImage()
        } else {
            onShowAlert(getString(R.string.no_internet))
        }
    }

    private fun onSetUpViews(){
        /* Refresh page - fetching new image */
        viewBinding?.btnRefresh?.setOnClickListener {
            if(Connectivity.hasInternetConnection(this)) {
                viewModel.onGetDogImage()
            } else{
                onShowAlert(getString(R.string.no_internet))
            }
        }
    }

    private fun doObserveLoadDogImage(){
        viewModel.doObserveDogImage().observe(this){
            when(it){
                is Resource.Loading -> { setProgressState(View.VISIBLE)}
                is Resource.Success -> {
                    setProgressState(View.GONE)
                    it.data?.dogImageUrl?.let { it1 -> setDogImage(it1) }
                }
                is Resource.Error -> {
                    setProgressState(View.GONE)
                    viewBinding?.progressBar?.visibility = View.GONE
                    Log.e("E->", "" + getString(it.resId!!))
                }
            }
        }
    }

    /* set dog image in view holder*/
    private fun setDogImage(dogImageUrl: String) {
        if(dogImageUrl.isNotEmpty()) {
            Picasso.get().load(dogImageUrl).placeholder(getPlaceholder())
                .into(viewBinding?.ivPhoto)
        }
    }

    /* set progressbar state*/
    private fun setProgressState(state: Int) {
        viewBinding?.progressBar?.visibility = state
    }

    private fun onShowAlert(message: String) {
        if(message.isEmpty()) return
        Snackbar.make(viewBinding?.fmPrent!!, message, Snackbar.LENGTH_SHORT).show()
    }

}