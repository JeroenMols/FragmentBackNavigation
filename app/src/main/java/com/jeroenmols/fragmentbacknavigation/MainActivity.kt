/*
 * Copyright 2017 Jeroen Mols
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jeroenmols.fragmentbacknavigation

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.framelayout_fragmentcontainer, TodayFragment()).commit()
    }

    fun showUserNameFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_fragmentcontainer, UserNameFragment()).addToBackStack("").commit()
    }

    fun showPasswordFragment() {
        supportFragmentManager.beginTransaction().replace(R.id.framelayout_fragmentcontainer, PasswordFragment()).commit()
    }

    class TodayFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_today, container, false)
            val loginButton = view.findViewById<Button>(R.id.button_today_login)
            loginButton.setOnClickListener({ (activity as MainActivity).showUserNameFragment()});
            activity.title = "Today's events"
            return view;
        }
    }

    class UserNameFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_username, container, false)
            val usernameButton = view.findViewById<Button>(R.id.button_username)
            usernameButton.setOnClickListener({ (activity as MainActivity).showPasswordFragment()});
            activity.title = "Enter username"
            return view;
        }
    }

    class PasswordFragment : Fragment() {

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val view = inflater.inflate(R.layout.fragment_password, container, false)
            val passwordButton = view.findViewById<Button>(R.id.button_password)
            passwordButton.setOnClickListener({
                simulateLogin()
            })
            activity.title = "Enter password"
            return view;
        }

        private fun simulateLogin() {
            Toast.makeText(activity, "logged in success, navigating back", Toast.LENGTH_SHORT).show()
            Handler().postDelayed({ activity.onBackPressed() }, 1000)
        }

    }
}
