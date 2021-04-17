package com.picker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.picker.R
import com.picker.Utils.showSnackBar
import com.picker.data.model.PickItem
import com.picker.databinding.FragmentHomeBinding
import com.picker.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _vBinding: FragmentHomeBinding? = null
    private val vBinding: FragmentHomeBinding get() = _vBinding!!
    private val vModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vBinding = FragmentHomeBinding.inflate(inflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        vBinding.btnPick.setOnClickListener {
            viewLifecycleOwner.lifecycleScope.launch {
                val randPickItem = vModel.allPickItems.first().randomOrNull()
                if (randPickItem != null) {
                    setupUiWithPickItem(randPickItem)
                } else {
                    requireView().showSnackBar(getString(R.string.db_is_empty))
                }

            }
        }

    }

    private fun setupUiWithPickItem(pickItem: PickItem) {
        vBinding.txtTitle.text = pickItem.title
        vBinding.txtDescription.text = pickItem.description
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
        menu.findItem(R.id.menu_item_add).setOnMenuItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAddFragment())
            true
        }


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vBinding = null
    }
}