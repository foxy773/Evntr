package com.android.example.evntrapp
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

//lateinit var dataSetEvents: List<Event>

class EventAdapter(var dataSetEvents: List<EventsObject>, val callback: (EventsObject) -> Unit): RecyclerView.Adapter<EventAdapter.EventViewHolder>(){
    inner class EventViewHolder (cellView: View) : RecyclerView.ViewHolder(cellView) {

        var eventName : TextView = cellView.findViewById(R.id.textViewEventName)
        var eventPrice: TextView = cellView.findViewById(R.id.textViewEventPrice)
        var eventPicture: ImageView = cellView.findViewById(R.id.imageViewEvent)
        var eventDate: TextView = cellView.findViewById(R.id.TextviewDate)
        var eventCity: TextView = cellView.findViewById(R.id.textViewCity)
        var eventCreator: TextView = cellView.findViewById(R.id.textViewEventCreator)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {

        val cellView = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_chat_cell_item, parent, false)


        return EventViewHolder(cellView)


    }

    override fun onBindViewHolder(holder: EventViewHolder, position: Int) {

        val EventObject = dataSetEvents[position]


        holder.eventName.text = EventObject.title
        holder.eventPrice.text = "Price: ${EventObject.price.toString()}kr"
        holder.eventCity.text =  "City: ${EventObject.place?.city}"
        holder.eventCreator.text = "Host: ${EventObject.eventCreator?.name}"


if (EventObject.date != null) {
        val dateString = EventObject.date?.replace("00.000Z", "00Z") ?: ""
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
        var dateObject = format.parse(dateString)

        val simpleFormat = SimpleDateFormat("dd.MM.yyyy HH:mm")
        holder.eventDate.text = simpleFormat.format(dateObject)
}
        Picasso.with(holder.eventPicture.context).load(EventObject.thumbnail).into(holder.eventPicture);


    }

    override fun getItemCount(): Int {
        return dataSetEvents.size
    }

    fun updateData(list: List<EventsObject>){
        dataSetEvents = list
        notifyDataSetChanged()

    }
}