package com.geeks.noteapp.ui.fragments.note

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.geeks.noteapp.App
import com.geeks.noteapp.R
import com.geeks.noteapp.data.models.NoteModel
import com.geeks.noteapp.databinding.FragmentNoteDetailBinding
import java.util.Calendar

class NoteDetailFragment : Fragment() {

    private lateinit var binding: FragmentNoteDetailBinding
    private var selectedTime: String = ""
    private var selectedColor: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNoteDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedColor = ContextCompat.getColor(requireContext(), R.color.teal_200)
        binding.noteContainer.setBackgroundColor(selectedColor)

        setupListeners()
    }

    private fun setupListeners() {
        binding.btnAddText.setOnClickListener {
            val etTitle = binding.etTitle.text.toString().trim()
            val etDescription = binding.etDescription.text.toString().trim()

            if (etTitle.isEmpty() || etDescription.isEmpty()) {
                return@setOnClickListener
            }

            App.appDatabase?.noteDao()?.insert(NoteModel(
                title = etTitle,
                description = etDescription,
                time = selectedTime,
                color = selectedColor
            ))

            findNavController().navigateUp()
        }

        binding.btnTimePicker.setOnClickListener {
            showTimePicker()
        }

        binding.btnColorPicker.setOnClickListener {
            selectedColor = ContextCompat.getColor(requireContext(), R.color.teal_200)
            binding.noteContainer.setBackgroundColor(selectedColor)
        }
    }

    private fun showTimePicker() {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        val timePickerDialog = TimePickerDialog(requireContext(), { _, selectedHour, selectedMinute ->
            selectedTime = String.format("%02d:%02d", selectedHour, selectedMinute)
            binding.tvTime.text = selectedTime
        }, hour, minute, true)

        timePickerDialog.show()
    }
}