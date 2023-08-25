package com.example.guestsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.example.guestsapp.Guests.Guest
import com.example.guestsapp.databinding.ActivityGuestFormBinding
import com.example.guestsapp.repository.GuestRepository

class GuestFormActivity : AppCompatActivity(),View.OnClickListener {
    private lateinit var binding: ActivityGuestFormBinding
    private lateinit var viewModel: GuestFormViewModel
    private var guestId=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGuestFormBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        viewModel = ViewModelProvider(this).get(GuestFormViewModel::class.java)
        var bundle = intent.extras
        if (bundle!=null){
            var guest = Guest(
                bundle.getInt("id"),
                bundle.getString("name")!!,
                bundle.getBoolean("presence")
            )
            guestId=guest.id
            viewModel.current_guest(guest)
        }
        binding.radioPresent.isChecked=true
        observe()
        binding.btnSave.setOnClickListener(this)

    }
    private fun observe(){
        viewModel.guest.observe(this){
            binding.textGuestName.setText(it.name)
            if(it.presence){
                binding.radioPresent.isChecked=true
            }else{
                binding.radioAbsent.isChecked=true
            }
        }
    }

    override fun onClick(view: View) {
        if(view.id == R.id.btn_save){
            if (guestId==0){
            var name = binding.textGuestName.text.toString()
            var presence = binding.radioPresent.isChecked
            var guest:Guest = Guest(0,name,presence)
            viewModel.insert_guest(guest)
            finish()
        }
            else{
            var name = binding.textGuestName.text.toString()
            var presence = binding.radioPresent.isChecked
            var guest:Guest = Guest(guestId,name,presence)
            viewModel.update_guest(guest)
            finish()
        }

        }
    }

}