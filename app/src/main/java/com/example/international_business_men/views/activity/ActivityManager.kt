package com.example.international_business_men.views.activity

import androidx.fragment.app.Fragment

interface ActivityManager {

    fun goToFragment(fragment : Fragment, tag : String)
    fun showToolbar(title : String)
    fun hideToolbar()
}