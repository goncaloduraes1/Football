package com.pm.football.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pm.football.R
import com.pm.football.model.User
import com.pm.football.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class AddFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }

        return view
    }

    private fun insertDataToDatabase() {
        val firstName = addFirstName_et.text.toString()
        val lastName = addLastName_et.text.toString()
        val age = addAge_et.text
        val team = addTeam_et.text.toString()
        val position = addPosition_et.text.toString()
        val goal = addGoal_et.text.toString()
        val assist = addAssist_et.text.toString()
        val training = addTraining_et.text.toString()

        if (inputCheck(firstName, lastName, age, team, position, goal, assist, training)){
            //Create User Object
            val user = User(0, firstName, lastName, Integer.parseInt(age.toString()), team, position, goal, assist, training)
            //Add Data to Database
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(), "Successfully added!!", Toast.LENGTH_LONG).show()
            //Navigate Back
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: Editable, team: String, position: String, goal: String, assist: String, training: String): Boolean{
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty() && TextUtils.isEmpty(team) && TextUtils.isEmpty(position) && TextUtils.isEmpty(goal) && TextUtils.isEmpty(assist) && TextUtils.isEmpty(training))
    }

}