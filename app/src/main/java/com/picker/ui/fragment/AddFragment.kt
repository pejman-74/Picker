package com.picker.ui.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.picker.R
import com.picker.databinding.FragmentAddBinding


class AddFragment : Fragment() {

    private var _vBinding: FragmentAddBinding? = null
    private val vBinding: FragmentAddBinding get() = _vBinding!!
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
            true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _vBinding = null
    }
}