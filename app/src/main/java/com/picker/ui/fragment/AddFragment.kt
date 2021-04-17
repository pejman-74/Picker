package com.picker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.picker.R
import com.picker.Utils.getCurrentUTCDateTime
import com.picker.Utils.showSnackBar
import com.picker.data.model.PickItem
import com.picker.databinding.FragmentAddBinding
import com.picker.ui.viewmodel.MainViewModel


class AddFragment : Fragment() {

    private var _vBinding: FragmentAddBinding? = null
    private val vBinding: FragmentAddBinding get() = _vBinding!!
    private val vModel: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _vBinding = FragmentAddBinding.inflate(inflater, container, false)
        return vBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.add_menu, menu)
        menu.findItem(R.id.menu_item_save).setOnMenuItemClickListener {
            val title = vBinding.tiTitle.text.toString()
            if (title.isBlank()) {
                requireView().showSnackBar(message = getString(R.string.title_empty))
                return@setOnMenuItemClickListener true
            }
            val description = vBinding.tiDescription.text.toString()
            savePickItem(title, description)
            requireView().showSnackBar(message = getString(R.string.saved))
            true
        }
    }

    private fun savePickItem(title: String, description: String) {
        val pickItem = PickItem(title, description, getCurrentUTCDateTime())
        vModel.savePickItem(pickItem)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vBinding = null

    }
}