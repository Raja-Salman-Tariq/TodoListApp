package com.example.a3v2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.a3v2.adapters.OuterListAdapter
import com.example.a3v2.databinding.FragmentHomeBinding
import com.example.a3v2.db.MyViewModel
import com.example.a3v2.db.ToDoList

class HomeFragment(myViewModel: MyViewModel) : BaseFragment(myViewModel) {

    /*###############################################
    * -----        P R O P E R T I E S         -----*
    * =============================================*/
    private lateinit var binding        :   FragmentHomeBinding
    //-----------------------------------------------


    /*###############################################
    * -----      I N I T   &   S E T U P       -----*
    * =============================================*/
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_home, container, false)
        binding= FragmentHomeBinding.inflate(layoutInflater)

        return view
    }
    //-----------------------------------------------

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleRv()
    }



    /*###############################################
    * -----   c o n v e n i e n c e   f u n    -----*
    * =============================================*/
    private fun handleRv() {
        emptyTxt        =   view?.findViewById(R.id.fragment_home_empty_txt)!!
        recyclerView    = view?.findViewById(R.id.fragment_home_all_lists_rv)!!
//        recyclerView    =   binding.fragmentHomeAllListsRv

        data = mutableListOf()

        recyclerView.layoutManager  =   LinearLayoutManager(context)
        adapter =   OuterListAdapter(requireContext(), this, data, myViewModel)
        recyclerView.adapter        =   adapter

        myViewModel.allActiveLists.observe(viewLifecycleOwner){
                lists   ->  observeHomeFragmentData(lists)
        }
    }
    //-----------------------------------------------
    private fun observeHomeFragmentData(lists   : List<ToDoList>){
        if (lists.isEmpty()){
            emptyTxt.visibility =   View.VISIBLE
        }
        else
            emptyTxt.visibility =   View.GONE
        adapter.setData(lists)
    }
}