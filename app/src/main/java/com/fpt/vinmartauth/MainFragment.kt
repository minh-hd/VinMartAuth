package com.fpt.vinmartauth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.fpt.vinmartauth.login.LoginActivity

/**
 * A simple [Fragment] subclass.
 * Use the [MainFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //like an activity's onCreate method.
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view: View = inflater.inflate(R.layout.fragment_main, container, false)
        val btnSignIn = view.findViewById<Button>(R.id.btnHomeSignIn)
        btnSignIn.setOnClickListener {
            val intent = Intent(this.context, LoginActivity::class.java)
            startActivity(intent)
        }
        // Inflate the layout for this fragment
        return view
    }

    companion object {
        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}