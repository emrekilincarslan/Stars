package com.emrexample.mobile.fragment

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.emrexample.mobile.adapter.StarsRecyclerAdapter
import com.emrexample.mobile.databinding.FragmentStarsBinding
import com.emrexample.mobile.model.Star
import com.emrexample.mobile.viewmodel.SelectionOfStarViewModel
import com.emrexample.mobile.viewmodel.SelectionOfStarViewModel.SelectionOfStarsActionState.ShowSecondaryError
import com.emrexample.mobile.viewmodel.SelectionOfStarViewModel.SelectionOfStarsViewState
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo.*
import kotlinx.android.synthetic.main.github_repo_loading_state.*
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.HONEYCOMB)
class SelectionOfStarFragment : DaggerFragment(), SearchView.OnQueryTextListener{

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var starList:ArrayList<Star> = arrayListOf()

    private val viewModel: SelectionOfStarViewModel by lazy {
        ViewModelProvider(this, viewModelFactory)
            .get(SelectionOfStarViewModel::class.java)
    }


    private val starAdapter: StarsRecyclerAdapter by lazy {
        StarsRecyclerAdapter(this@SelectionOfStarFragment)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        val binding = FragmentStarsBinding.inflate(inflater,container,false)
        context?:return binding.root
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initSearch()
        subscribeToViewState()

    }

    fun onCliked(star: Star) {
        Log.d("csabfevrmuy",star.star_id)
    }

    private fun initSearch() {
        search_view.isIconifiedByDefault = false
        search_view.setOnQueryTextListener(this)
    }

    private fun initRecyclerView() {
        repoRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            RecyclerView.VERTICAL,
            false
        )
        repoRecyclerView.adapter = starAdapter

    }

    private fun subscribeToViewState() {
        viewModel.viewState.observe(this, Observer {
            return@Observer when (it) {
                SelectionOfStarsViewState.Loading -> displayLoading()
                is SelectionOfStarsViewState.ShowStars-> displaySuccess(it.starModel)
                is SelectionOfStarsViewState.ShowError -> displayError()
            }
        })
        viewModel.actionState.observe(this, Observer {
            return@Observer when (it) {
                is ShowSecondaryError -> Toast.makeText(
                    requireContext(), it.errorMessage, Toast.LENGTH_SHORT
                ).show()
            }
        })
    }


    private fun displayLoading() {
        loadingAnimation.visibility = View.VISIBLE
        errorAnimation.visibility = View.GONE
        reloadButton.visibility = View.GONE
        repoRecyclerView.visibility = View.GONE
    }

    private fun displaySuccess(starList: List<Star>) {
        loadingAnimation.visibility = View.GONE
        errorAnimation.visibility = View.GONE
        reloadButton.visibility = View.GONE
        repoRecyclerView.visibility = View.VISIBLE
        this.starList.clear()
        this.starList.addAll(starList)
        starAdapter.setData(starList)
    }

    private fun displayError() {
        loadingAnimation.visibility = View.GONE
        errorAnimation.visibility = View.VISIBLE
        reloadButton.visibility = View.VISIBLE
        repoRecyclerView.visibility = View.GONE

        /**
         * Fetching the data if there is error only if data is not stored
         */
        reloadButton.setOnClickListener { viewModel.getStars() }
    }

    override fun onQueryTextSubmit(query: String?): Boolean { return false }

    override fun onQueryTextChange(query: String): Boolean {

        val filteredModelList: ArrayList<Star> =
            filter(starList, query)
        starAdapter.setData(filteredModelList)
        repoRecyclerView.scrollToPosition(0)
        return true
    }

    @SuppressLint("DefaultLocale")
    private fun filter(
        models: List<Star>,
        query: String
    ): ArrayList<Star> {
        val filteredModelList: ArrayList<Star> = ArrayList()
        for (model in models) {
            if (model.starName.toLowerCase().contains(query.toLowerCase())) {
                filteredModelList.add(model)
            }
        }
        return filteredModelList

    }
}
