package com.android.example.evntrapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.beust.klaxon.Klaxon
import java.util.*


class StartupFragment : Fragment() {

    lateinit var recyclerViewEventBox: RecyclerView
    lateinit var layoutManager: LinearLayoutManager
    lateinit var queue: RequestQueue
    lateinit var adapter: EventAdapter
    var eventList = listOf<EventsObject>()
    lateinit var eventPrice: TextView
    lateinit var eventName: TextView
    lateinit var spinner: Spinner
    lateinit var popularIn: TextView
    lateinit var eventCity: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_startup, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        popularIn = view.findViewById(R.id.textViewPopularIn)
        //var citySelected = spinner.selectedItem

        spinner = view.findViewById(R.id.spinner1)
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

           override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {

                popularIn.text = "Popular in ${spinner.selectedItem.toString()}"
                var spinnerCity = spinner.selectedItem.toString()

               val newList = eventList.filter { it.place?.city == spinnerCity }
                   adapter.updateData(newList)


           }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }


        recyclerViewEventBox = view.findViewById(R.id.RecyclerViewEventBox)
        layoutManager = LinearLayoutManager(activity)
        recyclerViewEventBox.layoutManager = layoutManager

        //todo: Må fylle inn resten

        // callback under:
        adapter = EventAdapter(eventList) { Event ->


            eventPrice.text = Event.price.toString()
            eventName.text = Event.title
            eventCity.text = Event.place?.city




        }


        recyclerViewEventBox.adapter = adapter

        getEventAPI(Volley.newRequestQueue(requireContext())) { results ->
            // hva vi ønsker å gjøre med callback her
            eventList = results?.events ?: listOf()
            adapter.updateData(eventList)

            spinner.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_dropdown_item,
                results?.cities?.map { it.city } ?: listOf())


        }
    }

    fun getEventAPI(requestQueue: RequestQueue, callback: (ResultObject?) -> Unit) {
        //Todo: Fylle inn slik at vi henter API når det er klart.
        // Instantiate the RequestQueue.
        queue = Volley.newRequestQueue(activity)
        val url = "https://8pyl7wp7.api.sanity.io/v2021-10-21/data/query/production?query=%7B%0A%20%20%22events%22%3A%20*%5B_type%20%3D%3D%20%22event%22%5D%20%7C%20order(name%20asc)%20%7B%0A%20%20title%2C%0A%20%20%0A%20%20date%2C%0A%20%20%0A%20%20description%2C%0A%20%20%0A%20%20%22eventCategory%22%3A%20eventCategory%5B%5D-%3E%20category%2C%0A%0A%20%20eventCreator-%3E%20%7B%0A%20%20%20%20name%2C%0A%20%20%20%20%0A%20%20%20%20creatorLogo%20%7B%0A%20%20%20%20%20%20asset-%3E%20%7B%0A%20%20%20%20%20%20%20%20%20%20url%0A%20%20%20%20%20%20%7D%0A%20%20%20%20%7D%0A%20%20%7D%2C%0A%20%20%0A%20%20place%20%7B%0A%20%20%20%20address%2C%0A%20%20%20%20%0A%20%20%20%20%22city%22%3A%20city-%3E%20city%2C%0A%0A%20%20%20%20place%0A%20%20%7D%2C%0A%0A%20%20%22price%22%3A%20price.amount%2C%0A%0A%20%20%22speakers%22%3A%20speaker%5B%5D-%3Espeaker%2C%0A%0A%20%20%22thumbnail%22%3A%20thumbnail.asset-%3Eurl%0A%20%20%7D%2C%0A%0A%20%20%22categories%22%3A%20*%5B_type%20%3D%3D%20%22category%22%5D%20%7C%20order(name%20asc)%20%7B%0A%20%20%20%20category%0A%20%20%7D%2C%0A%0A%20%20%22cities%22%3A%20*%5B_type%20%3D%3D%20%22city%22%5D%20%7C%20order(name%20asc)%20%7B%0A%20%20%20%20city%0A%20%20%7D%2C%0A%0A%7D"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { json ->
                val event = Klaxon().parse<APIObject>(json)
                callback(event?.result)
            },
            {

            })

        // Add the request to the RequestQueue.
        requestQueue.add(stringRequest)
    }
}