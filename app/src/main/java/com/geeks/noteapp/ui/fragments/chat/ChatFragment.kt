package com.geeks.noteapp.ui.fragments.chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.geeks.noteapp.R
import com.geeks.noteapp.databinding.FragmentChatBinding
import com.geeks.noteapp.ui.adapter.ChatAdapter
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class ChatFragment : Fragment() {

    private lateinit var binding: FragmentChatBinding
    private var chatAdapter = ChatAdapter()
    private val db = Firebase.firestore
    private lateinit var query: Query
    private lateinit var listener: ListenerRegistration

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentChatBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialize()
        setupListeners()
        observeMessages()
    }

    private fun observeMessages() {
        query = db.collection("user")
        listener = query.addSnapshotListener { value, error ->
            if (error != null) {
                return@addSnapshotListener
            }

            value?.let { listValue ->
                val messages = mutableListOf<String>()
                for (doc in listValue.documents) {
                    val sms = doc.getString("Name")
                    sms?.let { message ->
                        messages.add(message)
                    }
                }
                chatAdapter.submitList(messages)
            }
        }
    }

    private fun initialize() {
        binding.rvChat.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = chatAdapter
        }
    }

    private fun setupListeners() {
        binding.sendBtn.setOnClickListener {
            val user = hashMapOf(
                "Name" to binding.etMessage.text.toString()
            )
            db.collection("users").add(user).addOnSuccessListener { }
            binding.etMessage.text.clear()
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        listener.remove()
    }
}