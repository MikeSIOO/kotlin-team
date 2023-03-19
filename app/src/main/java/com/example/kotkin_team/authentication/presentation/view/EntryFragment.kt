package com.example.kotkin_team.authentication.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kotkin_team.R
import com.google.android.material.button.MaterialButton

class EntryFragment : Fragment(R.layout.fragment_entry) {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_entry, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val signButton = view.findViewById<MaterialButton>(R.id.sign_entry_button)

        signButton.setOnClickListener{
            findNavController().navigate(R.id.action_entryFragment_to_signInFragment)
        }
    }
}
