package com.example.exhibitappcrop2cash.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.exhibitappcrop2cash.R
import com.example.exhibitappcrop2cash.adapter.ExhibitAdapter
import com.example.exhibitappcrop2cash.application.Crop2CashApplication
import com.example.exhibitappcrop2cash.databinding.FragmentExhibitBinding
import com.example.exhibitappcrop2cash.model.ExhibitModelResponseItem
import com.example.exhibitappcrop2cash.util.ApiCallNetworkResource
import com.example.exhibitappcrop2cash.util.ConnectivityLiveData
import com.example.exhibitappcrop2cash.util.observeNetworkConnection
import com.example.exhibitappcrop2cash.viewmodel.ExhibitViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExhibitFragment : Fragment() {

    private lateinit var _binding: FragmentExhibitBinding
    private val binding get() = _binding
    private lateinit var connectivityLiveData: ConnectivityLiveData
    private lateinit var exhibitAdapter: ExhibitAdapter
    private lateinit var exhibitRecyclerView: RecyclerView
    private val vieModel: ExhibitViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentExhibitBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /** calling the functions that's Observing network state **/
        connectivityLiveData = ConnectivityLiveData(Crop2CashApplication.application)
        observeNetworkConnection(connectivityLiveData,viewLifecycleOwner,
            {doThisWhenNetworkIsAvailable()}, {doThisWhenNetworkIsLost()})

        exhibitRecyclerView = binding.parentExhibitRecyclerview
        initRecyclerView()
        vieModel.getExhibitList()
        exhibitDetailsObservers()
        onBackPress()
    }

    /** initialise recyclerview*/
    private fun initRecyclerView(){
        exhibitRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            exhibitAdapter = ExhibitAdapter()
            exhibitRecyclerView.addItemDecoration(DividerItemDecoration(exhibitRecyclerView.context, DividerItemDecoration.VERTICAL))
            adapter = exhibitAdapter
        }
    }



    /** This function observe the get request and update the recycler view **/
    private fun exhibitDetailsObservers(){
        vieModel.exhibitResponseLiveData.observe(viewLifecycleOwner){ it ->
            when (it){
                is  ApiCallNetworkResource.Success -> {
                    it.data?.let {
                        exhibitAdapter.differ.submitList(it)
                        Log.d("MQ", "The result is: $it")
                    }
                }

                is ApiCallNetworkResource.Error -> {
                    it.message.let {
                        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                        Log.d("MQ", "An error occur: $it")
                    }
                }

                is ApiCallNetworkResource .Loading -> {
                    binding.connectionLostImage.visibility = View.INVISIBLE
                    binding.connectionLostText.visibility = View.INVISIBLE

                }
            }
        }
    }

    /** Observing network state **/
    private fun doThisWhenNetworkIsLost() {
        binding.connectionLostImage.visibility = View.VISIBLE
        binding.connectionLostText.visibility = View.VISIBLE
        binding.parentExhibitRecyclerview.visibility = View.INVISIBLE
    }
    private fun doThisWhenNetworkIsAvailable() {
        binding.parentExhibitRecyclerview.visibility = View.VISIBLE
    }



    /**Log out Alert Dialogue */
    private fun showLogOutAlert(){
        val dialogView = layoutInflater.inflate(R.layout.custom_logout_dialog, null)
        val customDialog = activity?.let {
            AlertDialog.Builder(it)
                .setView(dialogView)
                .show()
        }

        val btnProfileLogOutDialog = dialogView.findViewById<Button>(R.id.fragment_quit_btn)
        btnProfileLogOutDialog.setOnClickListener {
            customDialog?.dismiss()
            requireActivity().finish()

        }

        val btnProfileCancelDialog = dialogView.findViewById<Button>(R.id.fragment_cancel_btn)
        btnProfileCancelDialog.setOnClickListener {
            customDialog?.dismiss()
        }
    }




    /** to quit the app **/
    fun onBackPress(){
        val callBack = object: OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                showLogOutAlert()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,callBack)
    }



}