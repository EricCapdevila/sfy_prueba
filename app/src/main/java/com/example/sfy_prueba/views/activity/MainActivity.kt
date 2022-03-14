package com.example.sfy_prueba.views.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sfy_prueba.utils.Constants
import com.example.sfy_prueba.view_model.Factory
import com.example.sfy_prueba.views.fragments.ErrorFragment
import com.example.sfy_prueba.R
import com.example.sfy_prueba.databinding.ActivityMainBinding
import com.example.sfy_prueba.repository.Repository
import com.example.sfy_prueba.repository.models.Photo
import com.example.sfy_prueba.view_model.ErrorHandler
import com.example.sfy_prueba.view_model.ImagesViewModel
import com.example.sfy_prueba.views.fragments.ImageListFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    lateinit var viewModel: ImagesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpViewModel()
        setUpObservers()
        viewModel.getData(this)
    }

    private fun setUpViewModel(){
        viewModel = ViewModelProvider(this, Factory(Repository())).get(ImagesViewModel::class.java)
    }

    private fun setUpObservers(){
        viewModel.photos.observe(this,
            Observer<List<Photo>> {
                goToFragment(ImageListFragment(), Constants.PHOTOS_LIST_FRAGMENT)
            })
        viewModel.errorHandler.observe(this,
            Observer<ErrorHandler> {
                goToFragment(ErrorFragment(), Constants.ERROR_FRAGMENT)
            })
    }

      fun goToFragment(fragment: Fragment, tag: String) {
         removeSplash()
        val transaction = supportFragmentManager.beginTransaction()
        if (supportFragmentManager.findFragmentByTag(tag) != null) {
            transaction.replace(
                binding.activityMainFragmentContainer.id,
                fragment, tag)
        }  else {
            transaction.add(
                binding.activityMainFragmentContainer.id,
                fragment, tag)
        }
        //if it is not first fragment, you can go back from them
        if (supportFragmentManager.fragments.size == 1) transaction.addToBackStack(tag)
        transaction.commit()
    }

    fun removeSplash(){
        setTheme(R.style.sfy_prueba);
    }

}