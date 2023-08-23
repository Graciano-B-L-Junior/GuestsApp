package com.example.guestsapp.ui

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.guestsapp.GuestFormActivity
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.Guests.GuestListener
import com.example.guestsapp.databinding.FragmentPresentsBinding
import com.example.guestsapp.recyclerview.GuestAdapter

class PresentsFragment : Fragment() {

    private var _binding: FragmentPresentsBinding? = null
    private lateinit var viewModel: PresentsViewModel
    private var adapter: GuestAdapter = GuestAdapter()
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel =
            ViewModelProvider(this).get(PresentsViewModel::class.java)

        _binding = FragmentPresentsBinding.inflate(inflater, container, false)

        binding.recylerView.layoutManager = LinearLayoutManager(context)
        binding.recylerView.adapter = adapter

        val listener = object: GuestListener {

            override fun onclick(guest: Guest) {
                var name = guest.name
                var presence = guest.presence
                var id = guest.id
                var intent = Intent(context, GuestFormActivity::class.java)
                intent.putExtras(Bundle().apply {
                    putString("name",name)
                    putInt("id",id)
                    putBoolean("presence",presence)
                })
                startActivity(intent)
            }

            override fun onLongClick(guest: Guest) {
                var dialogBuilder = AlertDialog.Builder(context)
                dialogBuilder.setTitle("Removing guest")
                dialogBuilder.setMessage("Do you want delete this guest?")
                dialogBuilder.setNegativeButton("No", DialogInterface.OnClickListener{
                        dialogInterface, i -> dialogInterface.dismiss()

                })
                dialogBuilder.setPositiveButton("Yes", DialogInterface.OnClickListener{
                        dialogInterface, i -> viewModel.removeGuest(guest)
                    dialogInterface.dismiss()
                })
                dialogBuilder.create()
                dialogBuilder.show()
            }

        }
        viewModel.guest.observe(this) {
            adapter.updateGuestList(it,listener)
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getAll()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}