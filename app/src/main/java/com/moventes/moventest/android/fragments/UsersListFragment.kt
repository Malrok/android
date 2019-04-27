package com.moventes.moventest.android.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

import com.moventes.moventest.android.R
import com.moventes.moventest.android.adapters.UsersListAdapter
import com.moventes.moventest.android.models.User

class UsersListFragment : Fragment() {
    private var listener: OnFragmentInteractionListener? = null
    private var recycler: RecyclerView? = null
    private var db: FirebaseFirestore? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view?.findViewById(R.id.recycler)
        recycler?.layoutManager = LinearLayoutManager(context)

        this.db
                ?.collection("users")
                ?.get()
                ?.addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        val users = ArrayList<User>()
                        for (document in task.result!!) {
                            users.add(document.toObject(User::class.java))
                        }

                        val adapter = UsersListAdapter(users)

                        recycler?.adapter = adapter
                    } else {
                        Toast.makeText(context, "an error occurred", Toast.LENGTH_SHORT)?.show()
                    }
                })
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement OnFragmentInteractionListener")
        }
        this.db = FirebaseFirestore.getInstance()
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnFragmentInteractionListener {
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
                UsersListFragment().apply {}
    }
}
